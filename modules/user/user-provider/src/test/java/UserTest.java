import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.service.UserDetailSer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

import java.util.List;

/**
 * 用户业务测试
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserTest {

    @Autowired
    private UserSer userAPI;
    @Autowired
    CacheManager cacheManager;


    /**
     * 查询全部,缓存测试
     */
    @Test
    public void findAll() throws SerException {
        List<User> users = userAPI.findAll();
        System.out.println(users);
    }

    /**
     * 通过用户姓名邮件手机号查找用户
     *
     * @throws SerException
     */
    @Test
    public void verifyByAccountNumber() throws SerException {
        System.out.println(null != userAPI.findByAccountNumber("liguiqin"));

    }

    @Autowired
    private UserDetailSer userDetailAPI;

    @Test
    public void addUserDetails() throws SerException {


    }

}
