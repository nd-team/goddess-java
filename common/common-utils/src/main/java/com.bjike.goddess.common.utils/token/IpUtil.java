package com.bjike.goddess.common.utils.token;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip地址转换工具
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-27 17:02]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class IpUtil {
    public static long ipToLong(String sip) {
        long[] ip = new long[4];
        int[] pos = new int[3];
        pos[0] = sip.indexOf(".");
        ip[0] = Long.parseLong(sip.substring(0, pos[0]));
        for (int i = 1; i < 3; i++) {
            pos[i] = sip.indexOf(".", pos[i - 1] + 1);
            ip[i] = Long.parseLong(sip.substring(pos[i - 1] + 1, pos[i]));
            if (i == 2) {
                ip[i + 1] = Long.parseLong(sip.substring(pos[i] + 1));
            }
        }
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    public static String longToIp(long longIp) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf(longIp >>> 24));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf(longIp & 0x000000FF));
        return sb.toString();
    }
    public static String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static void main(String args[]) {
        System.out.println(ipToLong("192.168.0.1"));
        System.out.println(ipToLong("192.168.0.11"));
        System.out.println(ipToLong("192.168.0.111"));
        System.out.println(longToIp(3232235631l));
    }
}
