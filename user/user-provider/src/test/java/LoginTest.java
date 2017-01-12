import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.dto.UserLoginDTO;
import com.bjike.goddess.user.service.IUserLoginSer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.ApplicationConfiguration;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户登陆业务测试]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class LoginTest{

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserLoginSer userLoginSer;


    @Test
    public void loginUser()throws SerException {
        UserLoginDTO dto = new UserLoginDTO();
        dto.setAccount("liguiqin0");
        dto.setPassword("123456");
        userLoginSer.login(dto);
    }

}
