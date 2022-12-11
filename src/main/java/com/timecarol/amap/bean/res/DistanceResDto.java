package com.timecarol.amap.bean.res;

import lombok.Data;

import java.util.List;

/**
 * url: https://lbs.amap.com/api/webservice/guide/api/direction#t9
 */

@Data
public class DistanceResDto {

    /**
     * 距离信息列表
     */
    private List<DistanceInfoDto> results;

    @Data
    public static class DistanceInfoDto {

        /**
         * 起点坐标, 起点坐标序列号(从1开始)
         */
        private String origin_id;

        /**
         * 终点坐标, 终点坐标序列号(从1开始)
         */
        private String dest_id;

        /**
         * 路径距离, 单位: 米
         */
        private String distance;

        /**
         * 预计行驶时间, 单位: 秒
         */
        private String duration;

    }

}
