package com.river.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: he.feng
 * \* Date: 2017/9/30
 * \* Description:
 * \
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * The constant IPV4_LOCAL.
     */
    private static final String IPV4_LOCAL = "127.0.0.1";
    /**
     * The constant IPV6_LOCAL.
     */
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";
    /**
     * The constant IP_UNKNOWN.
     */
    private static final String IP_UNKNOWN = "unknown";

    /**
     * object转json
     *
     * @param obj the obj
     * @return string
     */
    public String generateResponse(Object obj) {
        return toJsonStr(obj);
    }

    public <T> String toJsonStr(T t) {
        String jsonString = JSONObject.toJSONString(t);
        logger.info("接口返回数据【" + jsonString + "】");
        return jsonString;
    }

    /**
     * Gets the ip addr. 获取访问者真实IP地址
     *
     * @param request the request
     * @return the ip addr
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals(IPV4_LOCAL) || ip.equals(IPV6_LOCAL)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    logger.error("未知主机异常:", e);
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StringUtils.isNotBlank(ip) && ip.contains(",")) { // "***.***.***.***".length()
            ip = ip.substring(0, ip.indexOf(','));
        }
        return ip;
    }
}
