package test_java_service.code.dao;


import com.bjike.goddess.dbs.jpa.dao.MyRep;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.entity.UserGroup;

/**
 * Created by lgq on 16-10-13.
 */
public interface IUserGroupRep extends MyRep<UserGroup,UserGroupDto> {

    /**

     * @param name
     * @return
     */

    UserGroup findByName(String name);
}
