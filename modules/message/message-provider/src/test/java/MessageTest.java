import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.dto.MessageDTO;
import com.bjike.goddess.message.entity.Message;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.redis.client.RedisClient;
import com.bjike.goddess.user.api.rbac.GroupAPI;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.to.rbac.GroupTO;
import message_code.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * @Author: [liguiqin]
 * @Date: [2017-02-28 11:03]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class MessageTest {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    RedisClient redisClient;

    @Test
    public void sendMessage()throws Exception{
        MessageTO messageTO = new MessageTO();
        messageTO.setRangeType(RangeType.PUB);
        messageTO.setContent("xxxx");
        messageTO.setTitle("测试消息");
        messageTO.setSenderName("lgq");
        messageTO.setSenderId("56913117-0a7d-431a-bdb0-b0c0f0f88c23");
      //  messageAPI.send(messageTO);
        System.out.println("all message:"+ redisClient.getAllMap("message"));
        System.out.println("user message:"+redisClient.getList("56913117-0a7d-431a-bdb0-b0c0f0f88c23"+"_message"));
    }

    @Test
    public void list()throws Exception{
        MessageDTO dto = new MessageDTO();
        dto.setUserId("56913117-0a7d-431a-bdb0-b0c0f0f88c23");
        System.out.println(JSON.toJSONString(messageAPI.list(dto)));
    }

    @Test
    public void read()throws Exception{
        String messageId = "3c7a975c-26b7-4a7f-837c-178bd0ae60d7";
        messageAPI.read(messageId);
        System.out.println("user message:"+redisClient.getList("56913117-0a7d-431a-bdb0-b0c0f0f88c23"+"_message"));

    }

    @Test
    public void edit()throws Exception{
        MessageTO to = new MessageTO();
        to.setId("3c7a975c-26b7-4a7f-837c-178bd0ae60d7");
        to.setTitle("lalala");
        messageAPI.edit(to);
        System.out.println("redis message:"+redisClient.getMap("message","3c7a975c-26b7-4a7f-837c-178bd0ae60d7"));
        MessageDTO dto = new MessageDTO();
        dto.setUserId("56913117-0a7d-431a-bdb0-b0c0f0f88c23");
        System.out.println("mysql message:"+JSON.toJSONString(messageAPI.list(dto)));
    }

    @Test
    public void del()throws Exception{
        String messageId = "3c7a975c-26b7-4a7f-837c-178bd0ae60d7";
        messageAPI.remove(messageId);
        MessageDTO dto = new MessageDTO();
        dto.setUserId("56913117-0a7d-431a-bdb0-b0c0f0f88c23");
        System.out.println("mysql message:"+JSON.toJSONString(messageAPI.list(dto)));
        System.out.println("redis message:"+redisClient.getMap("message","3c7a975c-26b7-4a7f-837c-178bd0ae60d7"));
    }
}
