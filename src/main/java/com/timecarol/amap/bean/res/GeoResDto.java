package com.timecarol.amap.bean.res;

import lombok.Data;

import java.util.List;

@Data
public class GeoResDto {

    /**
     * 地理编码信息列表
     */
    private List<Object> geocodes;

    @Data
    public static class GeoCodeInfoDto {

        /**
         * 结构化地址
         */
        private String formatted_address;

        /**
         * 国家
         * 国家地址默认返回中国
         */
        private String country;

        /**
         * 地址所在省份名
         * 例如: 北京市。此处需要注意的是，中国的四大直辖市也算作省级单位
         */
        private String province;

        /**
         * 城市编码
         * 例如: 010
         */
        private String citycode;

        /**
         * 地址所在城市名
         * 例如: 北京市
         */
        private String city;

        /**
         * 地址所在的区
         * 例如: 朝阳区
         */
        private String district;

        /**
         * 区域编码
         * 例如: 110101
         */
        private String adcode;

        /**
         * 门牌
         * 例如: 6号
         */
        private String number;

        /**
         * 坐标点
         * 经度, 维度
         */
        private String location;

        /**
         * 匹配级别
         * 国家、省、市、区县、开发区、乡镇、村庄、热点商圈、兴趣点、门牌号、单元号、道路、道路交叉路口、公交站台、地铁站、未知
         */
        private String level;

    }

}
