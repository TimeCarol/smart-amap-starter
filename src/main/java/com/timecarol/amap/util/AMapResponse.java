package com.timecarol.amap.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Data
public class AMapResponse {

    /**
     * 返回结果状态值，值为0或1，0表示请求失败；1表示请求成功
     */
    private String status;

    /**
     * 返回状态说明，status为0时，info返回错误原因；否则返回“OK”
     */
    private String info;

    /**
     * 错误码
     */
    private String infocode;

    private Map<String, Object> data;

    public Boolean isSuccess() {
        return "1".equals(status) || "OK".equals(info) || "10000".equals(infocode);
    }

    public AMapResponse(String response) {
        if (Objects.nonNull(response) && !response.isEmpty()) {
            Map<String, Object> res = JSON.parseObject(response, new TypeReference<Map<String, Object>>() {
            });
            if (Objects.nonNull(res)) {
                this.status = Optional.ofNullable(res.get("status")).map(Object::toString).orElse(null);
                this.info = Optional.ofNullable(res.get("info")).map(Object::toString).orElse(null);
                this.infocode = Optional.ofNullable(res.get("infocode")).map(Object::toString).orElse(null);
                if (res.size() > 3) {
                    this.data = new LinkedHashMap<>();
                    res.forEach((k, v) -> {
                        if (!("status".equals(k) || "info".equals(k) || "infocode".equals(k))) {
                            this.data.put(k, v);
                        }
                    });
                }
            }
        }
    }

    public String toErrorMsg() {
        if (isSuccess()) {
            return "成功";
        }
        return "请求失败, 错误码: " + infocode + ", 错误信息: " + info + ", 请参考 https://lbs.amap.com/api/webservice/guide/tools/info/ 查看错误描述";
    }

    public <T> T getData(Class<T> clazz) {
        return Objects.isNull(this.data) ? null : JSON.parseObject(JSON.toJSONString(this.data), clazz);
    }

    public <T> T getData(TypeReference<T> type) {
        return Objects.isNull(this.data) ? null : JSON.parseObject(JSON.toJSONString(this.data), type);
    }

}
