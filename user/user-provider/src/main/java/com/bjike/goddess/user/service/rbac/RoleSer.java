package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.sto.rbac.RoleTreeSTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("roleSer")
public class RoleSer extends ServiceImpl<Role, RoleDTO> implements RoleAPI {
    @Override
    public List<RoleTreeSTO> treeData(String id) throws SerException {
        RoleDTO dto = new RoleDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Role> roles = super.findByCis(dto);
        List<RoleTreeSTO> roleTreeSTOS = new ArrayList<>(roles.size());
        roles.stream().forEach(role -> {
            RoleTreeSTO sto = new RoleTreeSTO();
            sto.setName(role.getName());
            sto.setId(role.getId());
            sto.setParent(null == role.getParent());
            roleTreeSTOS.add(sto);
        });
        return roleTreeSTOS;
    }
}
