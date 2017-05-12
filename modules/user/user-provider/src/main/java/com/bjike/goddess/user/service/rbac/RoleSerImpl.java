package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.rbac.RoleBO;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.to.rbac.RoleTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public List<RoleBO> treeData(String id) throws SerException {
        RoleDTO dto = new RoleDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Role> roles = super.findByCis(dto);

        return BeanTransform.copyProperties(roles, RoleBO.class);
    }

    @Override
    public void remove(String id) throws SerException {
        List<Role> children = getChild(id);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }

        Role department = super.findById(id);
        Role parent = department.getParent();
        if (null != parent) {
            children = getChild(parent.getId());
            parent.setHasChild(children.size() != 0);
            super.update(parent);
        }
        super.remove(id);
    }

     @Transactional
    @Override
    public RoleBO save(RoleTO roleTO) throws SerException {
        Role role = BeanTransform.copyProperties(roleTO, Role.class, true);
        if (StringUtils.isNotBlank(roleTO.getParentId())) {
            RoleDTO dto = new RoleDTO();
            dto.getConditions().add(Restrict.eq("id", roleTO.getParentId()));
            Role parent = findOne(dto);
            if (null != parent) {
                role.setParent(parent);
                parent.setHasChild(true); //更新父类子节点字段为true
                super.update(parent);
            }
        }
        role.setHasChild(false);
        super.save(role);
        RoleBO bo = BeanTransform.copyProperties(role, RoleBO.class);
        return bo;
    }

    @Override
    public void update(RoleTO roleTO) throws SerException {
        Role role = super.findById(roleTO.getId());
        Role old_parent = role.getParent();
        String parentId = roleTO.getParentId();
        Role new_parent = null;
        if (StringUtils.isNotBlank(parentId)) {
            RoleDTO dto = new RoleDTO();
            dto.getConditions().add(Restrict.eq("id", roleTO.getParentId()));
            new_parent = super.findOne(dto);
        }

        //更新
        role.setModifyTime(LocalDateTime.now());
        role.setName(roleTO.getName());
        role.setParent(new_parent);
        role.setModifyTime(LocalDateTime.now());
        super.update(role);


        if (null != new_parent) {
            new_parent.setHasChild(true);
            super.update(new_parent);
        }

        /**
         * 维护该节点与其父类是否有子节点
         */
        if (null != old_parent) { //以前有父节点,删除父节点后,检查其未更改前的父节点是否还存在子节点
            List<Role> children = getChild(old_parent.getId()); //查询以未更改前其父类的所有子节点
            old_parent.setHasChild(children.size() != 0);
            super.update(old_parent);
        }

    }

    private List<Role> getChild(String id) throws SerException {
        RoleDTO dto = new RoleDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Role> children = findByCis(dto);
        return children;
    }

}
