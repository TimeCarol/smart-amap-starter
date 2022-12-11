package com.timecarol.amap.bean.res;

import lombok.Data;

/**
 * url: https://lbs.amap.com/api/webservice/guide/api/ipconfig
 */

@Data
public class IPResDto {

    /**
     * 省份名称
     * 若为直辖市则显示直辖市名称
     * 如果在局域网 IP网段内, 则返回"局域网"
     * 非法IP以及国外IP则返回空
     */
    private String province;

    /**
     * 城市的adcode编码
     */
    private String adcode;

    /**
     * 城市名称
     * 若为直辖市则显示直辖市名称
     * 如果为局域网网段内IP或者非法IP或国外IP, 则返回空
     */
    private String city;

    /**
     * 所在城市范围的左下右上对标对
     * 所在城市范围的左下右上对标对
     */
    private String rectangle;

}
