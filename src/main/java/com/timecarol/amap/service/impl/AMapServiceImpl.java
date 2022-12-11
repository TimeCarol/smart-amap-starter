package com.timecarol.amap.service.impl;

import com.alibaba.fastjson.JSON;
import com.timecarol.amap.bean.AMapProperties;
import com.timecarol.amap.bean.req.*;
import com.timecarol.amap.bean.res.*;
import com.timecarol.amap.service.AMapService;
import com.timecarol.amap.util.AMapResponse;
import com.timecarol.amap.util.AMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Slf4j
public class AMapServiceImpl implements AMapService {

    AMapProperties properties;

    RestTemplate restTemplate;

    public AMapServiceImpl(AMapProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
        AMapUtil.KEY = properties.getKey();
        AMapUtil.PRIVATE_KEY = properties.getPrivateKey();
        AMapUtil.ENABLE_SIGNATURE = properties.getDigitalSignatureEnable();
    }


    @Override
    public IPResDto queryIp(IPReqDto dto) {
        return sendRequest(AMapService.IP_URL, dto, IPResDto.class, HttpMethod.POST);
    }

    @Override
    public DistanceResDto queryDistance(DistanceReqDto dto) {
        return sendRequest(AMapService.DISTANCE_URL, dto, DistanceResDto.class, HttpMethod.POST);
    }

    @Override
    public GeoResDto queryGeo(GeoReqDto dto) {
        return sendRequest(AMapService.GEO_URL, dto, GeoResDto.class, HttpMethod.GET);
    }

    @Override
    public WeatherResDto queryWeather(WeatherReqDto dto) {
        return sendRequest(AMapService.WEATHER_URL, dto, WeatherResDto.class, HttpMethod.GET);
    }

    @Override
    public DirectionDrivingResDto queryDirectionDriving(DirectionDrivingReqDto dto) {
        return sendRequest(AMapService.DIRECTION_DRIVING_URL, dto, DirectionDrivingResDto.class, HttpMethod.POST);
    }

    @Override
    public AMapResponse getResponse(String url, Object param, HttpMethod method) {
        ResponseEntity<String> response;
        if (HttpMethod.POST.equals(method)) {
            response = restTemplate.postForEntity(url + AMapUtil.beanToOrderUrl(param), null, String.class);
        } else if (HttpMethod.GET.equals(method)) {
            response = restTemplate.getForEntity(url + AMapUtil.beanToOrderUrl(param), String.class);
        } else {
            throw new RuntimeException("Http Method that are not currently supported.");
        }
        return new AMapResponse(response.getBody());
    }

    private <T> T sendRequest(String url, Object param, Class<T> resClazz, HttpMethod method) {
        AMapResponse aMapResponse = getResponse(url, param, method);
        if (aMapResponse.isSuccess()) {
            T data = aMapResponse.getData(resClazz);
            log.info("请求成功, url:{}, param:{}, resObj:{}", url, JSON.toJSONString(param), JSON.toJSONString(data));
            return data;
        }
        throw new RuntimeException(aMapResponse.toErrorMsg());
    }

}
