package com.timecarol.amap.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 请求高德地图API的工具类
 */
@Slf4j
public final class AMapUtil {

    private AMapUtil() {}

    public static String KEY = "";

    public static String PRIVATE_KEY = "";

    public static Boolean ENABLE_SIGNATURE = Boolean.FALSE;

    public static Map<String, Object> beanToMap(Object obj) {
        return Objects.isNull(obj) ? null : JSON.parseObject(JSON.toJSONString(obj), new TypeReference< Map<String, Object>>(){});
    }

    public static <T> T mapToBean(Map<String, Objects> map, Class<T> clazz) {
        return Objects.isNull(map) ? null : JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * 将对象转为 url参数 的方法, 如果设置了 PRIVATE_KEY 会自动添加 sig 参数
     * @return 返回url参数, 直接拼接在url后面
     */
    public static String beanToOrderUrl(Object obj) {
        Map<String, Object> map = beanToMap(obj);
        if (Objects.isNull(map) || Objects.isNull(KEY) || KEY.isEmpty()) {
            return "";
        }
        map.put("key", KEY);
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        List<String> keyValue = new ArrayList<>(keys.length);
        for (String key : keys) {
            Object val = map.get(key);
            if (Objects.nonNull(val)) {
                keyValue.add(key + "=" + map.get(key));
            }
        }
        String originUrl = "?" + String.join("&", keyValue);
        if (!ENABLE_SIGNATURE) {
            return originUrl;
        }
        String sig = md5(originUrl.substring(1) + PRIVATE_KEY);
        map.put("sig", sig);
        keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        keyValue = new ArrayList<>(keys.length);
        for (String key : keys) {
            Object val = map.get(key);
            if (Objects.nonNull(val)) {
                keyValue.add(key + "=" + map.get(key));
            }
        }
        return "?" + String.join("&", keyValue);
    }

    public static String md5(String value) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes(StandardCharsets.UTF_8));
            byte b[] = md.digest();
            int d;
            for (int i = 0; i < b.length; i++) {
                d = b[i];
                if (d < 0) {
                    d = b[i] & 0xff;
                }
                if (d < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(d));
            }
        } catch (Exception e) {
            throw new RuntimeException(value + "生成md5失败: " + e.getLocalizedMessage());
        }
        return sb.toString();
    }

}
