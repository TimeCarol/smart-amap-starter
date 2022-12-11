package com.timecarol.amap.bean.req;

import lombok.Data;

/**
 * url: https://lbs.amap.com/api/webservice/guide/api/ipconfig
 */

@Data
public class IPReqDto {

    /**
     * ip地址
     * 需要搜索的IP地址（仅支持国内）
     * 若用户不填写IP，则取客户http之中的请求来进行定位
     */
    private String ip;

}
