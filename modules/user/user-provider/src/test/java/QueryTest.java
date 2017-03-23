import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.UserSer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import user_common_code.AppConfig;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 查询测试
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-01 11:42]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class QueryTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private UserSer userAPI;


    @Test
    public void init() throws Exception {
        User user = new User();
        user.setUsername("黎");
        user.setPassword(PasswordHash.createHash("123458"));
        user.setPhone("13457910208");
        user.setNickname("xiaoming萨斯");
        user.setEmployeeNumber("1111128");
        userAPI.save(user);

    }


    /**
     * 查询全部
     */
    @Test
    public void findByCis() throws SerException {
        UserDTO dto = new UserDTO();
        Double[] money = new Double[]{1.11, 99.00};
        LocalDateTime[] accessTime = new LocalDateTime[]{LocalDateTime.now().minusDays(100), LocalDateTime.now()};
        dto.getConditions().add(Restrict.between("accessTime", accessTime)); //时间范围查询
        dto.getConditions().add(Restrict.like("username", "li")); //模糊查询
        dto.getConditions().add(Restrict.like("status", Status.valueOf("THAW"))); //模糊查询
        dto.getConditions().add(Restrict.eq("username", "liguiqin"));//匹配查询
//        dto.getConditions().add(Restrict.in("age",new Integer[]{11,22,33}));//in查询
//        dto.getConditions().add(Restrict.eq("group.name","项目组"));//连接查询
        dto.getSorts().add("username=desc"); //正排序
//        dto.getSorts().add("group.name=asc");//倒排序
        List<User> users = userAPI.findByCis(dto);
    }

    @Test
    public void findBySql() throws SerException {
        String sql = "select  a.username,a.password," +
                " a.status from user a " ;

        String[] fields = new String[]{"username", "password", "status"};
        List<User> users = userAPI.findBySql(sql, User.class, fields);
        for (User info : users) {
            System.out.println(info.getId());
        }
    }


    /**
     * 查询全部
     */
    @Test
    @Transactional
    public void findAll() throws SerException {
        List<User> users = userAPI.findAll();
        if (null != users && users.size() > 0) {
            for (User u : users) {
                System.out.println(u.getUsername());
            }
        }
    }


    /**
     * 分页查询,可带条件及排序
     */
    @Test
    public void findByPage() throws SerException {
        UserDTO dto = new UserDTO();
        List<User> users = userAPI.findByPage(dto);
        if (null != users && users.size() > 0) {
            for (User u : users) {
                System.out.println(u.getUsername());
            }
        }

    }


    @Test
    public void update() throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.eq("username","liguiqin8"));
        User user = userAPI.findOne(dto);
        user.setModifyTime(LocalDateTime.now());
        userAPI.update(user);

    }




}
