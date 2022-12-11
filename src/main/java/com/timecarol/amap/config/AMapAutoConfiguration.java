package com.timecarol.amap.config;

import com.alibaba.fastjson.JSON;
import com.timecarol.amap.bean.AMapProperties;
import com.timecarol.amap.service.AMapService;
import com.timecarol.amap.service.impl.AMapServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@EnableConfigurationProperties(AMapProperties.class)
@ConditionalOnProperty(prefix = "amap", name = "enable", havingValue = "true", matchIfMissing = false)
@Configuration
@Slf4j
public class AMapAutoConfiguration {

    AMapProperties aMapProperties;

    RestTemplate restTemplate;

    public AMapAutoConfiguration(AMapProperties properties, RestTemplateBuilder builder) {
        this.aMapProperties = properties;
        this.restTemplate = buildRestTemplate(builder);
        log.info("高德地图配置初始化成功, properties:{}", JSON.toJSONString(properties));
    }

    private RestTemplate buildRestTemplate(RestTemplateBuilder builder) {
        log.info("properties:{}", JSON.toJSONString(aMapProperties));
        return builder
                .setConnectTimeout(Duration.ofMillis(aMapProperties.getConnectionTimeout())) //设置连接超时时间为15秒
                .setReadTimeout(Duration.ofMillis(15000)) //设置读取超时时间为15秒
                .rootUri(aMapProperties.getBaseUrl()) //设置根路径
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON.toString()) //设置默认请求方式
                .additionalMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8), new ByteArrayHttpMessageConverter()) //设置Http消息转换器
                .errorHandler(new ResponseErrorHandler() {
                    private Logger log = LoggerFactory.getLogger(this.getClass());

                    @Override
                    public boolean hasError(ClientHttpResponse response) throws IOException {
                        return !HttpStatus.OK.equals(response.getStatusCode());
                    }

                    @Override
                    public void handleError(ClientHttpResponse response) throws IOException {
                        try {
                            InputStream in = response.getBody();
                            byte[] bytes = new byte[in.available()];
                            in.read(bytes);
                            log.error("请求失败, 状态码: {}, headers:{}, body:{}", response.getRawStatusCode(), response.getHeaders(), new String(bytes, StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            log.error("请求失败, 状态码: {}, headers:{}, error:{}", response.getRawStatusCode(), response.getHeaders(), e);
                            throw e;
                        }
                    }
                })
                .interceptors(new ClientHttpRequestInterceptor() {
                    private Logger log = LoggerFactory.getLogger(this.getClass());

                    @Override
                    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                        Instant start = Instant.now();
                        String bodyContent = "empty";
                        if (body.length > 0) {
                            bodyContent = new String(body, StandardCharsets.UTF_8);
                        }
                        log.info("请求开始, Method:{}, URI:{}, Headers:{}, body:{}, start:{}", request.getMethodValue(), request.getURI(), request.getHeaders(), bodyContent, start.toEpochMilli());
                        ClientHttpResponse execute = execution.execute(request, body);
                        Duration duration = Duration.between(start, Instant.now());
                        log.info("请求结束, ResponseStatus:{}, ResponseHeaders:{}, 耗时:{}毫秒, start:{}", execute.getStatusCode(), execute.getHeaders(), duration.toMillis(), start.toEpochMilli());
                        return execute;
                    }
                })
                .build();
    }

    @Bean
    public AMapService aMapService() {
        return new AMapServiceImpl(this.aMapProperties, this.restTemplate);
    }

}
