import org.junit.Test;
import org.junit.runner.RunWith;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.User;
import com.bjike.goddess.user.common.entity.UserLoginLog;
import com.bjike.goddess.user.common.enums.LoginType;
import com.bjike.goddess.user.common.service.IUserLoginLogSer;
import com.bjike.goddess.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.ApplicationConfiguration;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-28 15:37]
 * @Description: [用户登录日志测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserLoginLogTest {

    @Autowired
    private IUserSer userSer;

    @Autowired
    private IUserLoginLogSer userLoginLogSer;

    @Test
    public void addLoginLog() throws SerException {
        User user = userSer.findByUsername("liguiqin");
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setLoginAddress("广州20");
        loginLog.setLoginIp("192.168.1.1");
        loginLog.setLoginType(LoginType.APP);
        loginLog.setUser(user);
        loginLog.setId("111111");
        userLoginLogSer.save(loginLog);

    }
}
