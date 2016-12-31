package test_java_service;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.bjike.goddess.dbs.jpa.dto.Restrict;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserAndInfo;
import test_java_service.code.service.IUserAndInfoService;
import test_java_service.code.service.IUserInterestSer;
import test_java_service.code.service.IUserSer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserSer userSer;
    @Autowired
    private IUserInterestSer interestSer;
    @Autowired
    private IUserAndInfoService userAndInfoService;


    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setMoney(5000.0);
            user.setAge(55);
            user.setHeight(1.2f);
            user.setNickname("xiaoming");
            user.setSuperMan(true);
            userSer.save(user);
        }
    }


    /**
     * 查询全部
     */
    @Test
    public void findByCis() throws SerException {
        UserDto dto = new UserDto();
        Double[] money = new Double[]{1.11,99.00};
        LocalDateTime[] accessTime = new LocalDateTime[]{LocalDateTime.now().minusDays(100),LocalDateTime.now()};
        dto.getConditions().add(Restrict.between("money",money)); //数值范围查询
        dto.getConditions().add(Restrict.between("accessTime",accessTime)); //时间范围查询
        dto.getConditions().add(Restrict.like("username","黎")); //模糊查询
        dto.getConditions().add(Restrict.eq("username","黎贵钦"));//匹配查询
        dto.getConditions().add(Restrict.in("age",new Integer[]{11,22,33}));//in查询
        dto.getConditions().add(Restrict.eq("group.name","项目组"));//连接查询
        dto.getSorts().put("username","desc"); //正排序
        dto.getSorts().put("group.name","asc");//倒排序
        List<User> users = userSer.findByCis(dto);
    }

    @Test
    public void findBySql() throws SerException {
       String sql = "select a.age,a.height,a.accessTime, a.username,a.password," +
                " b.email  ,b.address,a.money from test_user a " +
                "left join test_userInfo b on a.id = b.user_id";
        String[] fields = new String[]{"age","height","accessTime","username","password","email","address","money"};
        List<UserAndInfo> userAndInfos =  userAndInfoService.findBySql(sql,UserAndInfo.class,fields);
        for(UserAndInfo info:userAndInfos){
            System.out.println(info.getId());
        }
        System.out.println(userAndInfos);
    }



    /**
     * 查询全部
     */
    @Test
    @Transactional
    public void findAll() throws SerException {
        List<User> users = userSer.findAll();
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
        UserDto dto = new UserDto();
        List<User> users = userSer.findByPage(dto);
        if (null != users && users.size() > 0) {
            for (User u : users) {
                System.out.println(u.getUsername());
            }
        }

    }


}
