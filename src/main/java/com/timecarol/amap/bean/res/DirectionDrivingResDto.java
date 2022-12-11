package com.timecarol.amap.bean.res;

import lombok.Data;

import java.util.List;

@Data
public class DirectionDrivingResDto {

    /**
     * 路径规划方案总数
     */
    private Integer count;

    /**
     * 返回的规划方案列表
     */
    private List<DirectionDrivingRouteDto> route;

    @Data
    public static class DirectionDrivingRouteDto {

        /**
         * 起点经纬度
         */
        private String origin;

        /**
         * 终点经纬度
         */
        private String destination;

        /**
         * 预计出租车费用, 单位: 元
         */
        private String taxi_cost;

        /**
         * 算路方案详情
         */
        private List<DirectionDrivingPathDto> paths;

        @Data
        public static class DirectionDrivingPathDto {

            /**
             * 方案距离, 单位: 米
             */
            private String distance;

            /**
             * 0 代表限行已规避或未限行, 即该路线没有限行路段
             * 1 代表限行无法规避, 即该路线有限行路段
             */
            private Integer restriction;

            /**
             * 路线分段
             */
            private List<DirectionDrivingStepDto> steps;

            @Data
            public static class DirectionDrivingStepDto {

                /**
                 * 行驶指示
                 */
                private String instruction;

                /**
                 * 进入道路方向
                 */
                private String orientation;

                /**
                 * 分段道路名称
                 */
                private String road_name;

                /**
                 * 分段距离信息
                 */
                private String step_distance;
            }

        }

    }

}
