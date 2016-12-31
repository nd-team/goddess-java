package test_java_service.code.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_java_service.code.dao.IUserRep;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;

import javax.annotation.PostConstruct;

/**
 * Created by lgq on 16-10-13.
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements IUserSer {

    @Autowired
    private IUserRep userRep;

    @PostConstruct
    public void start(){
        System.out.println("UserServiceImpl is init!");
    }

    @Override
    public User findByUsername(String username) throws SerException {
        return userRep.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) throws SerException {
        return userRep.findByNickname(nickname);
    }

}
