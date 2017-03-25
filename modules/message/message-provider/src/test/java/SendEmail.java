import com.bjike.goddess.message.api.EmailAPI;
import com.bjike.goddess.message.to.email.Email;
import message_code.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * @ProjectName: [ ISSP智能服务共享平台 ]
 * @Package: [ oa.jpa.mails ]
 * @Author: [ liguiqin ]
 * @CreateTime: [ 2016年2月23日 下午5:24:28 ]
 * @Copy: [ bjike.com ]
 * @Version: [ v1.0 ]
 * @Description: [ 发送邮件实例 ]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SendEmail {
    @Autowired
    private EmailAPI emailAPI;

    /**  发送人，发送人姓名，登录账号，密码，邮箱服务器，为默认设置无需设置
     * @throws Exception
     */
    @Test
    public void sendEmail() throws Exception {

        //设置邮件要发送的内容以及标题信息
        Email email = new Email("这叫下午福利茶时间  ", " 这是一张图片<img src='cid:imageName1' />第二张图片<img src='cid:imageName2' /><strong>资源部有人吗</strong>测试  ！");
        //初始化接收人列表
        email.setReceiver(new String[]{"liguiqin_aj@163.com", "xinaml@qq.com"});

        emailAPI.send(email);
    }
}
