package com.timecarol.amap.bean.res;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResDto {

    /**
     * 实况天气数据信息
     */
    private List<LiveWeatherInfoDto> lives;

    /**
     * 预报天气信息数据
     */
    private List<ForecastWeatherInfoDto> forecasts;

    @Data
    public static class LiveWeatherInfoDto {

        /**
         * 省份名
         */
        private String province;

        /**
         * 城市名
         */
        private String city;

        /**
         * 区域编码
         */
        private String adcode;

        /**
         * 天气现象(汉字描述)
         */
        private String weather;

        /**
         * 实时气温, 单位: 摄氏度
         */
        private String temperature;

        /**
         * 风向描述
         */
        private String winddirection;

        /**
         * 风力级别, 单位: 级
         */
        private String windpower;

        /**
         * 空气湿度
         */
        private String humidity;

        /**
         * 数据发布的时间
         */
        private String reporttime;
    }

    @Data
    public static class ForecastWeatherInfoDto {

        /**
         * 城市名称
         */
        private String city;

        /**
         * 城市编码
         */
        private String adcode;

        /**
         * 省份名称
         */
        private String province;

        /**
         * 预报发布时间
         */
        private String reporttime;

        /**
         * 预报数据list结构, 元素cast, 按顺序为当天、第二天、第三天的预报数据
         */
        private List<CastWeatherInfoDto> casts;

        @Data
        public static class CastWeatherInfoDto {

            /**
             * 日期
             */
            private String date;

            /**
             * 星期几
             */
            private String week;

            /**
             * 白天天气现象
             */
            private String dayweather;

            /**
             * 晚上天气现象
             */
            private String nightweather;

            /**
             * 白天温度
             */
            private String daytemp;

            /**
             * 晚上温度
             */
            private String nighttemp;

            /**
             * 白天风向
             */
            private String daywind;

            /**
             * 晚上风向
             */
            private String nightwind;

            /**
             * 白天风力
             */
            private String daypower;

            /**
             * 晚上风力
             */
            private String nightpower;

        }

    }

}
