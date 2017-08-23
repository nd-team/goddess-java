package com.bjike.goddess.user.dao.rbac;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.rbac.RolePermissionDTO;
import com.bjike.goddess.user.entity.rbac.RolePermission;

/**
 * 角色资源持久化
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 15:33]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RolePermissionRep extends JpaRep<RolePermission, RolePermissionDTO> {
}
