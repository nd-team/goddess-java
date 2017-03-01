package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.sto.rbac.RoleSTO;
import com.bjike.goddess.user.sto.rbac.RoleTreeSTO;

import java.util.List;

/**
 * 角色业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RoleAPI extends SerAPI<Role, RoleDTO> {
    /**
     * 逐层查询,逐层加载
     * @param id
     * @return
     */
    default List<RoleTreeSTO> treeData(String id)throws SerException{
        return null;
    }


    default RoleSTO saveRole(Role role) throws SerException {
       return  null;
    }

}
