package com.bjike.goddess.message.api;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.message.to.email.Email;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-24 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("emailApiImpl")
public class EmailApiImpl implements EmailAPI {
    private static final Logger CONSOLE = LoggerFactory.getLogger(EmailApiImpl.class);

    @Autowired
    private Environment env;

    @Override
    public void send(Email em) throws SerException {
        try {
            HtmlEmail email = new HtmlEmail();
            initHtmlEmail(email, em);
            initHtmlEmailInfo(email, em);
            email.send();
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }

    }

    private void initHtmlEmail(HtmlEmail email, Email em) {
        try {
            String username = StringUtils.isNotBlank(em.getUsername()) ? em.getUsername() : env.getProperty("email.username");
            String password = StringUtils.isNotBlank(em.getPassword()) ? em.getPassword() : env.getProperty("email.password");
            password = new String(new BASE64Decoder().decodeBuffer(password));
            String host = StringUtils.isNotBlank(em.getHost()) ? em.getHost() : env.getProperty("email.host");
            String sender = StringUtils.isNotBlank(em.getSender()) ? em.getSender() : env.getProperty("email.sender");
            String senderName = StringUtils.isNotBlank(em.getSenderName()) ? em.getSenderName() : env.getProperty("email.senderName");
            em.setSender(sender);
            email.setFrom(sender, senderName);   // 发送人
            email.setSubject(em.getSubject());   // 标题
            email.setHtmlMsg(em.getContent()); // 邮件内容
            email.setAuthentication(username, password);
            email.setHostName(host);
            email.setCharset("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private void initHtmlEmailInfo(HtmlEmail email, Email em) throws Exception {


        List<EmailAttachment> accessories = null; //  初始化邮件附件列表
        if (null != em.getAppendixPath()) {// 发送带附件的邮件
            accessories = new ArrayList<>();
            for (String path : em.getAppendixPath()) {
                AddAccessories(path, accessories);
            }
        }
        for (String receiver : em.getReceiver()) {// 接收人
            if (StringUtils.isNotBlank(receiver) && Validator.isEmail(receiver)) {
                email.addTo(receiver, getUserName(receiver));
            } else {
                if (StringUtils.isNotBlank(receiver)) {
                    CONSOLE.info("收件人邮箱格式不正确！");
                }
            }
        }
        if (null != em.getCc_address()) {// 抄送
            for (String cc : em.getCc_address()) {
                email.addCc(cc, cc);
            }
        }
        if (null != em.getBcc_address()) {
            for (String bcc : em.getBcc_address()) {
                email.addBcc(bcc, bcc); // 密送
            }
        }
        if (null != accessories) {// 附件
            for (EmailAttachment att : accessories) {
                email.attach(att);
            }
        }

        if (null != em.getImgMap()) {//图片
            URL url = null;
            String cid = null;
            MimeBodyPart text = new MimeBodyPart(); // 准备邮件正文数据
            text.setContent(em.getContent(), "text/html;charset=UTF-8");
            for (Map.Entry<String, String> m : em.getImgMap().entrySet()) {
                if (m.getValue().split("http")[0].equals("")) { //如果是网络路径
                    url = new URL(m.getValue());
                    cid = email.embed(url, m.getKey());
                    em.setContent(em.getContent().replaceAll(m.getKey(), cid));
                } else {// 准备图片数据
                    MimeBodyPart image = new MimeBodyPart();
                    DataHandler dh = new DataHandler(new FileDataSource(m.getValue()));
                    image.setDataHandler(dh);
                    image.setContentID(m.getKey());
                    MimeMultipart mm = new MimeMultipart(); // 描述数据关系
                    mm.addBodyPart(text);
                    mm.addBodyPart(image);
                    mm.setSubType("related");
                    email.addPart(mm);
                }
            }
        }
        email.setHtmlMsg(em.getContent());
        //email.addHeader("Disposition-Notification-To", "xinaml@qq.com");
    }


    /**
     * 添加附件
     *
     * @param path
     */
    private void AddAccessories(String path, List<EmailAttachment> accessories) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        if (path.split("http")[0].equals("")) {//是否为远程文件
            attachment.setURL(new URL(path));
        } else {
            attachment.setPath(path);
        }
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        // 文件描述
        attachment.setDescription("venustech");
        try {
            attachment.setName(MimeUtility.encodeText(path.substring(path.lastIndexOf("/") + 1)));// 解决中文字符问题
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        accessories.add(attachment);
    }

    /**
     * 截取邮件地址前面部分作为收件人名称
     *
     * @param username
     * @return
     */
    private String getUserName(String username) {
        return username.split("@")[0];
    }


}
