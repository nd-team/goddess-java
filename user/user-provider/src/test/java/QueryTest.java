import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.utils.PasswordHash;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.service.UserAPI;
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
 * @Author: [liguiqin]
 * @Date: [2016-12-01 11:42]
 * @Description: [查询测试]
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
    private UserAPI userAPI;



    @Test
    public void init() throws Exception {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword(PasswordHash.createHash("123456"));
            user.setPhone("13457910241");
            user.setNickname("xiaoming");
            user.setEmployeeNumber("111111");
            userAPI.save(user);

    }


    /**
     * 查询全部
     */
    @Test
    public void findByCis() throws SerException {
        UserDTO dto = new UserDTO();
        Double[] money = new Double[]{1.11,99.00};
        LocalDateTime[] accessTime = new LocalDateTime[]{LocalDateTime.now().minusDays(100),LocalDateTime.now()};
        dto.getConditions().add(Restrict.between("accessTime",accessTime)); //时间范围查询
        dto.getConditions().add(Restrict.like("username","li")); //模糊查询
        dto.getConditions().add(Restrict.eq("username","liguiqin"));//匹配查询
//        dto.getConditions().add(Restrict.in("age",new Integer[]{11,22,33}));//in查询
//        dto.getConditions().add(Restrict.eq("group.name","项目组"));//连接查询
        dto.getSorts().add("username=desc"); //正排序
//        dto.getSorts().add("group.name=asc");//倒排序
        List<User> users = userAPI.findByCis(dto);
    }

    @Test
    public void findBySql() throws SerException {
       String sql = "select  a.username,a.password," +
                " b.address from user a " +
                "left join user_detail b on a.id = b.user_id";
        String[] fields = new String[]{"username","password","address"};
        List<User> users =  userAPI.findBySql(sql,User.class,fields);
        for(User info:users){
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


}
