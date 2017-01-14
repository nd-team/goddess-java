import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.UserAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.AppConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户业务测试]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CacheTest {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    CacheManager cacheManager;
    private List<Cache> caches = new ArrayList<>();

    @Before
    public void allCaches(){
        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String name : cacheNames) {
            Cache cache = cacheManager.getCache(name);
            caches.add(cache);
        }
    }

    /**
     * 查询出来的是否为缓存对象
     * 假如是缓存对象。则对象是相等的
     */
    @Test
    public void isSameObject() throws SerException {
        User user1 = userAPI.findByUsername("liguiqin");
        User user2 = userAPI.findByUsername("liguiqin");

        assert (user1 == user2); //对象相同
        User user3 = userAPI.findByPhone("13457910241"); //通过号码查询(第一次查询，不是缓存对象，不相同)
        assert (user2 != user3);

    }
    @Test
    public void allCache() throws SerException {
        for(Cache cache : caches){
            System.out.println(cache.getNativeCache());
        }

    }


    @Test
    public void add() throws SerException {
        List<User> users = new ArrayList<>();
        try {
            User user = new User();
            user.setUsername("666hqw_test");
            user.setPassword(PasswordHash.createHash("123456"));
            user.setPhone("1809791024");
            users.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userAPI.save(users);

    }

}
