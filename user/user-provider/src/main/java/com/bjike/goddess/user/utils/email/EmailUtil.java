package com.bjike.goddess.user.utils.email;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件发送工具
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-29 17:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class EmailUtil {
    private static Logger CONSOLE = LoggerFactory.getLogger(EmailUtil.class);

    public static void SendMail(Email em) throws Exception {
        if (StringUtils.isBlank(em.getContent()) ||
                (null == em.getReceiver() && em.getReceiver().size() == 0)
                || StringUtils.isBlank(em.getSubject()) || null == em.getSender()) {
            throw new Exception("收件人/发送人/主题/内容 不能为空!");
        }
        EmailGenerate emailGenerate = new EmailGenerate();
        if (emailGenerate.sendHtmlEMail(em)) {
            CONSOLE.info("邮件发送成功!");
        } else {
            CONSOLE.info("邮件发送失败!");
        }
    }

}