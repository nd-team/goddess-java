package test_java_service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.dbs.common.dto.Restrict;
import com.bjike.goddess.dbs.common.exception.SerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserGroup;
import test_java_service.code.service.IUserGroupSer;
import test_java_service.code.service.IUserSer;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ManyToOne {
    /**
     * 双方共同维护关系
     * ManyToOne 指定 many 一方是不能独立存在的，否则存在孤儿数据
     * 一般一个group都要存在用户
     */

    @Autowired
    private IUserSer userSer;

    @Autowired
    private IUserGroupSer userGroupSer;

    @Before
    public void initGroup() throws SerException {
        List<UserGroup> userGroups = userGroupSer.findAll();
        if (null==userGroups) {
            UserGroup group1 = new UserGroup();
            group1.setName("用户组1");
            group1.setCreateTime(LocalDateTime.now());
            UserGroup group2 = new UserGroup();
            group2.setName("用户组2");
            group2.setCreateTime(LocalDateTime.now());
            userGroupSer.save(Arrays.asList(group1, group2));
        }

        if (null == userSer.findByUsername("liguiqin77")) {
            User user = new User();
            user.setUsername("liguiqin77");
            user.setPassword("123456");
            user.setMoney(5000.0);
            userSer.save(user);
        }
    }


    /**
     * 设置用户组到用户
     */
    @Test
    public void addGroupForUser() throws SerException {
        User user = userSer.findByUsername("liguiqin77");
        if(null!=user){
            user.setPassword("123456");
            user.setMoney(5000.0);
            UserGroup userGroup = userGroupSer.findByName("用户组2");
            user.setGroup(userGroup);
            userSer.update(user);
            System.out.println(JSON.toJSONString(user));
        }

    }


    /**
     * 删除被引用的用户组
     */
    @Test
    public void delGroupForUser() throws SerException {
        UserDto dto = new UserDto();
        dto.getConditions().add(Restrict.eq("group.name","用户组2"));
        List<User> users = userSer.findByCis(dto); //查询所有用户组2 的用户
        if(null!=users && users.size()>0){
            for (User user : users) {
                user.setGroup(null);
            }
            userSer.update(users);

        }

        UserGroup userGroup = userGroupSer.findByName("用户组2");
        userGroupSer.remove(userGroup);
    }


}
