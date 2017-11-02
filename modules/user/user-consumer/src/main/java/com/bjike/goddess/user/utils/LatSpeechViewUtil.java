package com.bjike.goddess.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * Created by tanghaixiang on 17-10-18.
 * 讯飞AIUI语音识别转文字，只限制60秒的语音且语音格式为.pcm格式的语音
 */
public class LatSpeechViewUtil {

    private static Logger logger = LoggerFactory.getLogger(LatSpeechViewUtil.class);

    private static final String APPID = "59e6dcf8";//讯飞开放平台注册申请应用的应用ID(APPID)
    private static final String APIKEY = "de304237bfb44a6c8678c455f93f2448";//ApiKey创建应用时自动生成
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    public static String recognize (InputStream is ){
        String xAppid = APPID;
        System.out.println("X-Appid:" + xAppid);
        long time = System.currentTimeMillis() / 1000;
        String curTime = String.valueOf(time);
        System.out.println("X-CurTime:" + curTime);
        String xParam = "{\"auf\":\"16k\",\"aue\":\"raw\",\"scene\":\"main\"}";
        String xParamBase64 = getBase64(xParam);
        System.out.println("X-Param:" + xParamBase64);

        //获取流文件
        byte[] bytes = new byte[0];
        String fileData = "";
        try {
            bytes = IOUtils.toByteArray(is);
            fileData = Base64.encodeBase64String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileData = "data=" + fileData;

        //语音识别
        String jsonStr = recognizePcm( curTime, xParamBase64,xAppid,  fileData );
        //{"code":"00000","desc":"成功","data":{"ret":0,"sid":"watf0ff9700@ch47760d4235da477600","result":"这几天真是倒霉呀。"}
        JSONObject jsonObject = JSON.parseObject( jsonStr );
        JSONObject sonJsonStr =(JSONObject) jsonObject.get("data");
        if( null == sonJsonStr ){
            return "";
        }
        String result = (String)sonJsonStr.get("result");
        return result;
    }

    public static String recognizePcm(String curTime,String xParamBase64, String xAppid, String fileData ){
        //ApiKey创建应用时自动生成
//        String apiKey = APIKEY;
        String token = APIKEY + curTime + xParamBase64 + fileData;
        String xCheckSum = md5Encode(token);
        System.out.println("X-CheckSum:" + xCheckSum);
        String resBody = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            String url = "http://api.xfyun.cn/v1/aiui/v1/iat";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
//            conn.setReadTimeout(2000);
//            conn.setConnectTimeout(1000);
            conn.setReadTimeout(200000000);
            conn.setConnectTimeout(200000000);
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("X-Appid", xAppid);
            conn.setRequestProperty("X-CurTime", curTime);
            conn.setRequestProperty("X-Param", xParamBase64);
            conn.setRequestProperty("X-CheckSum", xCheckSum);
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded; charset=utf-8");
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(fileData);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            // 将返回的输入流转换成字符串
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            in = new BufferedReader(inputStreamReader);
            String line;
            while ((line = in.readLine()) != null) {
                resBody += line;
            }
            System.out.println("result body :" + resBody);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return resBody;
    }

    /**
     * Base64加密
     * @author jlchen4
     * @date 2017年9月16日 下午3:45:30
     * @param str	加密字符串
     * @return
     */
    public static String getBase64(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        try {
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            str = new String(encodeBase64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * md5加密
     * @author jlchen4
     * @date 2017年9月16日 下午3:44:46
     * @param source	加密字符串
     * @return
     */
    public static String md5Encode(String source) {
        String result = null;
        try {
            result = source;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes("utf-8"));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());
        } catch (Exception e) {
            logger.error("Md5 Exception!", e);
        }
        return result;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
