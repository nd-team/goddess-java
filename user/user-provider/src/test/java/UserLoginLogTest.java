import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.UserLoginLog;
import com.bjike.goddess.user.enums.LoginType;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.service.UserLoginLogSer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户登录日志测试
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:37]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserLoginLogTest {


    @Autowired
    private UserLoginLogSer userLoginLogAPI;
    @Autowired
    private UserSer userAPI;

    @Test
    public void addLoginLog() throws SerException {
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setLoginAddress("20a地区");
        loginLog.setLoginIp("192.168.1.1");
        loginLog.setLoginType(LoginType.MOBILE);
        loginLog.setId("11s1111");
        loginLog.setLoginTime(LocalDateTime.now());
        UserBO userBO = userAPI.findByAccountNumber("liguiqin");

    }

    @Test
    public void find() throws SerException {

        List<UserLoginLog> logs = userLoginLogAPI.findAll();
        System.out.println(logs);

    }
}
