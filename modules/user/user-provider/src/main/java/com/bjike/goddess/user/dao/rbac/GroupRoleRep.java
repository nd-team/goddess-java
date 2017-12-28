package com.bjike.goddess.user.dao.rbac;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.user.dto.rbac.GroupRoleDTO;
import com.bjike.goddess.user.entity.rbac.GroupRole;

/**
 * 组角色持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 13:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupRoleRep extends JpaRep<GroupRole, GroupRoleDTO> {
}
