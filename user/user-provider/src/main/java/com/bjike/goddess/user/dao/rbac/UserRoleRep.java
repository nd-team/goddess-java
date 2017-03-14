package com.bjike.goddess.user.dao.rbac;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.rbac.UserRoleDTO;
import com.bjike.goddess.user.entity.rbac.UserRole;

/**
 * 用户角色持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRoleRep extends JpaRep<UserRole, UserRoleDTO> {

}
