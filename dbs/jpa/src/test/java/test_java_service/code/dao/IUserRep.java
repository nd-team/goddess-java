package test_java_service.code.dao;


import com.bjike.goddess.dbs.jpa.dao.MyRep;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.QueryHints;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;

import javax.persistence.QueryHint;
/**
 * Created by lgq on 16-10-13.
 */
public interface IUserRep extends MyRep<User,UserDto> {
    /**
     * 此处使用的是spring-data-jpa接口,不需要对接口进行实现,jpa可根据命名自动进行数据的查询
     * jpa接口规范：http://docs.spring.io/spring-data/jpa/docs/1.11.0.M1/reference/html/
     * @param username 用户名
     * @return 用户信息
     */

    @Cacheable("daoCache")
    User findByUsername(String username);

    /**
     * 查询缓存
     * @param nickname
     * @return
     */
    @QueryHints(value={@QueryHint(name="org.hibernate.cacheable",value="true")})
    User findByNickname(String nickname);
}
