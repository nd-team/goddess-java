package com.bjike.goddess.attendance.push;

import com.bjike.goddess.attendance.push.android.AndroidUnicast;
import com.bjike.goddess.attendance.push.ios.IOSUnicast;

/**
 * 友盟消息推送
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-10 16:06]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Push {
    private static String appkey = null;   //友盟对应用的唯一标识，注册时获取
    private static String appMasterSecret = null;   //服务器秘钥，注册时获取
    private static PushClient client = new PushClient();

    /**
     * 友盟安卓消息单播推送
     *
     * @param deviceToken 某用户装置的标识
     * @param ticker      通知栏提示文字
     * @param title       通知标题
     * @param text        通知内容
     * @return
     * @throws Exception
     */
    public static Boolean androidUnicast(String deviceToken, String ticker, String title, String text) throws Exception {
        AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
        unicast.setDeviceToken(deviceToken);
        unicast.setTicker(ticker);
        unicast.setTitle(title);
        unicast.setText(text);
        unicast.goAppAfterOpen();
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO set 'production_mode' to 'true' if your app is under production mode
        //正式模式     setTestMode为测试模式
        unicast.setProductionMode();
        unicast.setExtraField("test", "push");
        return client.send(unicast);
    }

    /**
     * 友盟ios消息单播推送
     *
     * @param deviceToken 某用户装置的标识
     * @param alert       通知内容
     * @return
     * @throws Exception
     */
    public static Boolean iosUnicast(String deviceToken, String alert) throws Exception {
        IOSUnicast unicast = new IOSUnicast(appkey, appMasterSecret);
        unicast.setDeviceToken(deviceToken);
        unicast.setAlert(alert);
        unicast.setBadge(0);
        unicast.setSound("default");
        // TODO set 'production_mode' to 'true' if your app is under production mode
        //正式模式     setTestMode为测试模式        unicast.setProductionMode();
        // Set customized fields
        unicast.setCustomizedField("test", "push");
        return client.send(unicast);
    }
}
