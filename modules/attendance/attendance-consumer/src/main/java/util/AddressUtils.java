package util;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.token.Address;
import com.bjike.goddess.common.utils.token.AddressResult;
import com.bjike.goddess.common.utils.token.IpUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 根据IP地址获取详细的地域信息
 *
 * @Author: [chenjunhao]
 * @Date: [2017-09-23 11:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AddressUtils {
    //    /**
//     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
//     * @param encodingString 服务器端请求编码。如GBK,UTF-8等
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public String getAddresses(String content, String encodingString)
//            throws Exception {
//        // 这里调用pconline的接口
//        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
//        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
//        String returnStr = this.getResult(urlStr, content, encodingString);
//        if (returnStr != null) {
//            // 处理返回的省市区信息
////            System.out.println(returnStr);
//            String[] temp = returnStr.split(",");
//            if (temp.length < 3) {
//                return "0";//无效IP，局域网测试
//            }
//            String region = (temp[5].split(":"))[1].replaceAll("\"", "");
//            region = decodeUnicode(region);// 省份
//
//            String country = "";
//            String area = "";
//            // String region = "";
//            String city = "";
//            String county = "";
//            String isp = "";
//            for (int i = 0; i < temp.length; i++) {
//                switch (i) {
////                    case 1:
////                        country = (temp[i].split(":"))[2].replaceAll("\"", "");
////                        country = decodeUnicode(country);// 国家
////                        break;
////                    case 3:
////                        area = (temp[i].split(":"))[1].replaceAll("\"", "");
////                        area = decodeUnicode(area);// 地区
////                        break;
//                    case 5:
//                        region = (temp[i].split(":"))[1].replaceAll("\"", "");
//                        region = decodeUnicode(region);// 省份
//                        break;
//                    case 7:
//                        city = (temp[i].split(":"))[1].replaceAll("\"", "");
//                        city = decodeUnicode(city);// 市区
//                        break;
////                    case 9:
////                        county = (temp[i].split(":"))[1].replaceAll("\"", "");
////                        county = decodeUnicode(county);// 地区
////                        break;
////                    case 11:
////                        isp = (temp[i].split(":"))[1].replaceAll("\"", "");
////                        isp = decodeUnicode(isp); // ISP公司
////                        break;
//                }
//            }
//
////            System.out.println(country + "=" + area + "=" + region + "=" + city + "=" + county + "=" + isp);
//            return region+city;
//        }
//        return null;
//    }
//
//    /**
//     * @param urlStr   请求的地址
//     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
//     * @param encoding 服务器端请求编码。如GBK,UTF-8等
//     * @return
//     */
//    private String getResult(String urlStr, String content, String encoding) throws Exception{
//        URL url = null;
//        HttpURLConnection connection = null;
//        try {
//            url = new URL(urlStr);
//            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
////            connection.setConnectTimeout(1);// 设置连接超时时间，单位毫秒
////            connection.setReadTimeout(1);// 设置读取数据超时时间，单位毫秒
//            connection.setDoOutput(true);// 是否打开输出流 true|false
//            connection.setDoInput(true);// 是否打开输入流true|false
//            connection.setRequestMethod("POST");// 提交方法POST|GET
//            connection.setUseCaches(false);// 是否缓存true|false
//            connection.connect();// 打开连接端口
//            DataOutputStream out = new DataOutputStream(connection
//                    .getOutputStream());// 打开输出流往对端服务器写数据
//            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
//            out.flush();// 刷新
//            out.close();// 关闭输出流
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
//            // ,以BufferedReader流来读取
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//            reader.close();
//            return buffer.toString();
//        } catch (IOException e) {
////            if (e.toString().contains("java.net.SocketTimeoutException")){
////                throw new Exception("连接超时");
////            }
//        }
//        return null;
//    }
//
//    /**
//     * unicode 转换成 中文
//     *
//     * @param theString
//     * @return
//     * @author fanhui 2007-3-15
//     */
//    public static String decodeUnicode(String theString) {
//        char aChar;
//        int len = theString.length();
//        StringBuffer outBuffer = new StringBuffer(len);
//        for (int x = 0; x < len; ) {
//            aChar = theString.charAt(x++);
//            if (aChar == '\\') {
//                aChar = theString.charAt(x++);
//                if (aChar == 'u') {
//                    int value = 0;
//                    for (int i = 0; i < 4; i++) {
//                        aChar = theString.charAt(x++);
//                        switch (aChar) {
//                            case '0':
//                            case '1':
//                            case '2':
//                            case '3':
//                            case '4':
//                            case '5':
//                            case '6':
//                            case '7':
//                            case '8':
//                            case '9':
//                                value = (value << 4) + aChar - '0';
//                                break;
//                            case 'a':
//                            case 'b':
//                            case 'c':
//                            case 'd':
//                            case 'e':
//                            case 'f':
//                                value = (value << 4) + 10 + aChar - 'a';
//                                break;
//                            case 'A':
//                            case 'B':
//                            case 'C':
//                            case 'D':
//                            case 'E':
//                            case 'F':
//                                value = (value << 4) + 10 + aChar - 'A';
//                                break;
//                            default:
//                                throw new IllegalArgumentException(
//                                        "Malformed      encoding.");
//                        }
//                    }
//                    outBuffer.append((char) value);
//                } else {
//                    if (aChar == 't') {
//                        aChar = '\t';
//                    } else if (aChar == 'r') {
//                        aChar = '\r';
//                    } else if (aChar == 'n') {
//                        aChar = '\n';
//                    } else if (aChar == 'f') {
//                        aChar = '\f';
//                    }
//                    outBuffer.append(aChar);
//                }
//            } else {
//                outBuffer.append(aChar);
//            }
//        }
//        return outBuffer.toString();
//    }
//
//    // 测试
    public static void main(String[] args) {
        AddressUtils addressUtils = new AddressUtils();
        // 测试ip 219.136.134.157 中国=华南=广东省=广州市=越秀区=电信
        String ip = "125.70.11.136";
        String address = "";
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            System.out.println(str);
            String ss = JSON.toJSONString(str);
            AddressResult result = JSON.parseObject(str, AddressResult.class);
//            System.out.println(ss);
//            System.out.println(JSON.toJSONString(result));
            if ("0".equals(result.getCode())) {
                Address ad = JSON.parseObject(result.getData(), Address.class);
                System.out.println(ad.getRegion() + ad.getCity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(address);
        // 输出结果为：广东省,广州市,越秀区
    }

    public Result listSecond(HttpServletRequest request) throws ActException {
        String addr = "";
        String ip = IpUtil.getIp(request);
        try {
            String str = getJsonContent("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            System.out.println(str);
            String ss = JSON.toJSONString(str);
            AddressResult result = JSON.parseObject(str, AddressResult.class);
//            System.out.println(ss);
//            System.out.println(JSON.toJSONString(result));
            if ("0".equals(result.getCode())) {
                Address ad = JSON.parseObject(result.getData(), Address.class);
                addr = ad.getRegion() + ad.getCity();
            } else {
                addr = "IP地址有误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActException("获取IP地址异常：" + e.getMessage());
        }

        return ActResult.initialize(addr);

    }


    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(10000);
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
}
