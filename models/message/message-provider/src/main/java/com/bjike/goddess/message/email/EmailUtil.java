package com.bjike.goddess.message.email;

import com.bjike.goddess.common.api.exception.SerException;
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

    public static void SendMail(Email em) throws SerException {
        if (StringUtils.isBlank(em.getContent()) ||
                (null == em.getReceiver() && em.getReceiver().size() == 0)
                || StringUtils.isBlank(em.getSubject()) || null == em.getSender()) {
            throw new SerException("收件人/发送人/主题/内容 不能为空!");
        }
        EmailGenerate emailGenerate = new EmailGenerate();
        try {
            if (emailGenerate.sendHtmlEMail(em)) {
                CONSOLE.info("邮件发送成功!");
            } else {
                CONSOLE.info("邮件发送失败!");
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());

        }

    }

}