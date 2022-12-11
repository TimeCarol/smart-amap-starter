package com.timecarol.amap.service;

import com.timecarol.amap.bean.req.*;
import com.timecarol.amap.bean.res.*;
import com.timecarol.amap.util.AMapResponse;
import org.springframework.http.HttpMethod;

public interface AMapService {

    /**
     * IP查询
     * url: https://lbs.amap.com/api/webservice/guide/api/ipconfig
     */
    String IP_URL = "/v3/ip";

    /**
     * 距离测量
     * url: https://lbs.amap.com/api/webservice/guide/api/direction#t9
     */
    String DISTANCE_URL = "/v3/distance";

    /**
     * 地理编码
     * url: https://lbs.amap.com/api/webservice/guide/api/georegeo#geo_list
     */
    String GEO_URL = "/v3/geocode/geo";

    /**
     * 天气查询
     * url: https://lbs.amap.com/api/webservice/guide/api/weatherinfo/#t1
     */
    String WEATHER_URL = "/v3/weather/weatherInfo";

    /**
     * 驾车路线规划
     * url: https://lbs.amap.com/api/webservice/guide/api/newroute
     */
    String DIRECTION_DRIVING_URL = "/v5/direction/driving";

    /**
     * 查询IP地址所在地
     */
    IPResDto queryIp(IPReqDto dto);

    /**
     * 距离测量
     */
    DistanceResDto queryDistance(DistanceReqDto dto);

    /**
     * 地理编码
     */
    GeoResDto queryGeo(GeoReqDto dto);

    /**
     * 天气查询
     */
    WeatherResDto queryWeather(WeatherReqDto dto);

    /**
     * 驾车路线规划
     */
    DirectionDrivingResDto queryDirectionDriving(DirectionDrivingReqDto dto);

    /**
     * 扩展请求, 存在新接口时可使用该方法发送请求
     * @param url 请求url相对路径
     * @param param 请求参数
     * @return 统一响应
     */
    AMapResponse getResponse(String url, Object param, HttpMethod method);

}
