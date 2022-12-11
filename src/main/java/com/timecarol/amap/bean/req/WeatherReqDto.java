package com.timecarol.amap.bean.req;

import lombok.Data;

@Data
public class WeatherReqDto {

    /**
     * 城市编码
     * 输入城市的adcode, adcode信息可参考<a href="https://lbs.amap.com/api/webservice/download">城市编码表</a>
     */
    private String city;

    /**
     * 气象类型
     * 可选值: base/all
     * base: 返回实况天气
     * all: 返回预报天气
     */
    private String extensions;

}
