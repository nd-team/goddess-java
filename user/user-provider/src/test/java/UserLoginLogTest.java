import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.service.UserAPI;
import com.bjike.goddess.user.service.UserLoginLogAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:37]
 * @Description: [用户登录日志测试]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserLoginLogTest {


    @Autowired
    private UserLoginLogAPI userLoginLogAPI;
    @Autowired
    private UserAPI userAPI;

    @Test
    public void addLoginLog() throws SerException {
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setLoginAddress("20");
        loginLog.setLoginIp("192.168.1.1");
        loginLog.setLoginType(LoginType.MOBILE);
        loginLog.setId("111111");
        User user = userAPI.findByAccountNumber("liguiqin");
        loginLog.setUser(user);
        userLoginLogAPI.save(loginLog);

    }

    @Test
    public void find() throws SerException {

        List<UserLoginLog> logs = userLoginLogAPI.findAll();
        System.out.println(logs);

    }
}
