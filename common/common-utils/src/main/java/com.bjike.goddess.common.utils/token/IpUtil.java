package com.bjike.goddess.common.utils.token;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.utils.string.Address;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * 获取真实ip地址
     *
     * @param request
     * @return
     */
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

    /**
     * 获取ip地址所在城市
     *
     * @param ip
     * @return
     */
    public static String getAddress(String ip) throws IOException {
        String address = null;
        String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + ip;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        HttpEntity resEntity = response.getEntity();
        if (resEntity != null) {
            String res = EntityUtils.toString(resEntity, "UTF-8");
           Address ad =  JSON.parseObject(res,Address.class);
            return ad.getCountry()+"/"+ad.getProvince()+"/"+ad.getCity();
        }
        return address;
    }

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


}
