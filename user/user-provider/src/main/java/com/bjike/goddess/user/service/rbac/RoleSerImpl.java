package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.bo.rbac.RoleBO;
import com.bjike.goddess.user.bo.rbac.RoleTreeBO;
import com.bjike.goddess.user.to.rbac.RoleTO;
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
@Service
public class RoleSerImpl extends ServiceImpl<Role, RoleDTO> implements RoleSer {
    @Override
    public List<RoleTreeBO> treeData(String id) throws SerException {
        RoleDTO dto = new RoleDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Role> roles = super.findByCis(dto);
        List<RoleTreeBO> roleTreeBOS = new ArrayList<>(roles.size());
        roles.stream().forEach(role -> {
            RoleTreeBO bo = new RoleTreeBO();
            bo.setName(role.getName());
            bo.setId(role.getId());
            bo.setParent(null == role.getParent());
            roleTreeBOS.add(bo);
        });
        return roleTreeBOS;
    }

    @Override
    public void remove(String id) throws SerException {
        RoleDTO dto = new RoleDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Role> children = findByCis(dto);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }
        super.remove(id);
    }

    @Override
    public RoleBO save(RoleTO roleTO) throws SerException {
        Role role = BeanTransform.copyProperties(roleTO, Role.class, true);
        super.save(role);
        return BeanTransform.copyProperties(role,RoleBO.class);
    }
}
