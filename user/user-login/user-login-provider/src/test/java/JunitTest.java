import com.dounine.corgi.context.ApplicationContext;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import org.junit.Test;
import com.bjike.goddess.user.common.utils.cookie.CookieOperate;
import com.bjike.goddess.user.login.dto.UserLoginDto;
import com.bjike.goddess.user.login.service.IUserLoginSer;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_login_code.ApplicationConfiguration;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户登陆业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest extends AbstractJUnit4SpringContextTests {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserLoginSer userLoginSer;


    @Before
    public void initContext() {
        ApplicationContext.setApplicationContext(super.applicationContext);
    }

    @Test
    public void loginUser()throws SerException{
        UserLoginDto dto = new UserLoginDto();
        dto.setAccount("liguiqin0");
        dto.setPassword("123456");
        userLoginSer.login(dto);
        CookieOperate cookieOperate = new CookieOperate();
    }




}
