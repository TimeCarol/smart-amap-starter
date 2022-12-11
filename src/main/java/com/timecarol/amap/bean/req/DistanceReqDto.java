package com.timecarol.amap.bean.req;

import lombok.Data;

/**
 * url: https://lbs.amap.com/api/webservice/guide/api/direction#t9
 */

@Data
public class DistanceReqDto {

    /**
     * 出发点
     * 支持100个坐标对，坐标对见用“| ”分隔；经度和纬度用","分隔
     */
    private String origins;

    /**
     * 目的地
     * 规则： lon，lat（经度，纬度）， “,”分割
     * 如117.500244, 40.417801     经纬度小数点不超过6位
     */
    private String destination;

    /**
     * 路径计算的方式和方法
     * 0：直线距离
     * 1：驾车导航距离（仅支持国内坐标）。
     * 必须指出，当为1时会考虑路况，故在不同时间请求返回结果可能不同。
     * 此策略和驾车路径规划接口的 strategy=0策略基本一致，策略为“ 速度优先，此路线不一定距离最短 ”
     * 由于算法差异，无法保证距离测量结果与路径规划结果完全一致。若需要实现高德地图客户端效果，可以考虑使用驾车路径规划接口
     * 3：步行规划距离（仅支持5km之间的距离）
     */
    private Integer type;

}
