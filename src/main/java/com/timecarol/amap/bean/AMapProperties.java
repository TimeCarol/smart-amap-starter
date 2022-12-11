package com.timecarol.amap.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "amap")
public class AMapProperties {

   /**
    * 基础url
    */
   private String baseUrl = "";

   /**
    * 密钥
    */
   private String key = "";

   /**
    * 数字签名加密用的私钥
    */
   private String privateKey = "";

   /**
    * 是否开启数字签名
    */
   private Boolean digitalSignatureEnable = Boolean.FALSE;

   /**
    * 连接超时时间
    */
   private Long connectionTimeout = 15000L;

   /**
    * 读取超时时间
    */
   private Long readTimeout = 15000L;

}
