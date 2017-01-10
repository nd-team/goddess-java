package test_java_service;

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
import test_java_service.code.service.IUserSer;

/**
 * Created by lgq on 16-10-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class OneToOne {

    /**
     * 主控方来维持对象关系
     * mappedBy = "service" User 为主控方，维持userInfo（被控方关系）
     */

    @Autowired
    private IUserSer userSer;


    @Before
    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setMoney(5000.0);
            user.setAge(55);
            user.setHeight(1.2f);
        }
    }


    @Test
    public void findByEmail() throws SerException {
        UserDto dto = new UserDto();
        dto.getConditions().add(Restrict.eq("userInfo.email","xinaml@qq.com"));
        System.out.println(userSer.findOne(dto));

    }



}
