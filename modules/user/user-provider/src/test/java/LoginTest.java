import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.service.UserLoginSer;
import com.bjike.goddess.user.to.UserLoginTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

/**
 * 用户登陆业务测试
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class LoginTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private UserLoginSer userLoginAPI;


    @Test
    public void loginUser() throws SerException {
        UserLoginTO to = new UserLoginTO();
        to.setAccount("liguiqin0");
        to.setPassword("123456");
        userLoginAPI.login(to);
    }

}
