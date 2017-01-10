package test_java_service.code.service;


import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.common.service.IService;
import org.springframework.cache.annotation.Cacheable;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;

/**
 * Created by huanghuanlai on 16/5/24.
 */
public interface IUserSer extends IService<User, UserDto> {

    @Cacheable("serviceCache")
    User findByUsername(String username) throws SerException;

    User findByNickname(String nickname) throws SerException;

}
