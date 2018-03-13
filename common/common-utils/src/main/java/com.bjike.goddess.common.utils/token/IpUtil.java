package com.bjike.goddess.common.utils.token;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * ip地址工具
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
        String ipAddress = request.getHeader("X-Forwarded-For");

        System.out.print("X-Real-IP:"+request.getHeader("X-Real-IP"));
        System.out.print("x-real-ip:"+request.getHeader("x-real-ip"));
        System.out.print("X-Forwarded-For:"+request.getHeader("X-Forwarded-For"));
        System.out.print("x-forwarded-for:"+request.getHeader("x-forwarded-for"));

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
            System.out.print("Proxy-Client-IP:"+ipAddress);
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
            System.out.print("WL-Proxy-Client-IP:"+ipAddress);
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Real-IP");
            System.out.print("X-Real-IP:"+ipAddress);
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Forward-For");
            System.out.print("X-Forward-IP:"+ipAddress);
        }

        ipAddress = request.getRemoteAddr();
        System.out.print("getRemoteAddr:"+ipAddress);

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
     * @param IP
     * @return
     */
    public static String getAddress(String IP) {
        String resout = "";
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + IP);
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            int code = (Integer) obj.get("code");
            if (code == 0) {
                resout = obj2.get("country") + "/" + obj2.get("area") + "/" + obj2.get("city") + "/" + obj2.get("isp");
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {

            e.printStackTrace();
            resout = "获取IP地址异常：" + e.getMessage();
        }
        return resout;

    }

    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
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
