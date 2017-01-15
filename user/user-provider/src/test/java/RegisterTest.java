import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.ext.UserRegisterDTO;
import com.bjike.goddess.user.service.UserRegisterAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户注册业务测试]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class RegisterTest {

    /**
     * 基础增删改查，批量操作等
     */

    private UserRegisterAPI userRegisterAPI;


    @Test
    public void existUsername() throws SerException {
        boolean users = userRegisterAPI.existUsername("liguiqin");
        System.out.println(users);
    }

    @Test
    public void sendCodeToPhone() throws SerException {
        String phone = "123456";
        userRegisterAPI.verifyAndSendCode(phone);
    }

    @Test
    public void verifyCodeAndReg() throws SerException {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setPhoneCode("123");
        userRegisterAPI.verifyCodeAndReg(dto);
    }

    @Test
    public void saveUser() throws SerException {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setPhoneCode("123");
        userRegisterAPI.verifyCodeAndReg(dto);
    }


}
