package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.RoleDTO;
import com.bjike.goddess.user.entity.Role;
import com.bjike.goddess.user.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class RoleSerImpl extends ServiceImpl<Role, RoleDTO> implements IRoleSer {

    @Autowired
    private IUserRoleSer userRoleSer;

    @Transactional
    @Override
    public Role save(Role role) throws SerException {
        String parentId = role.getParent().getId();
        if (StringUtils.isNotBlank(parentId)) { //假如有父节点
            Role parent = findById(parentId);
            if (null != parent) {
                role.setParent(parent);
            }
        }
        return super.save(role);
    }

    @Transactional
    @Override
    public void update(Role role) throws SerException {
        Role parent = role.getParent(); //
        if (null != parent) {
            if (role.getId().equals(role.getParent().getId())) {
                throw new SerException("上级不能选择自己");
            }
            if (isChildren(parent.getId(), role)) {
                throw new SerException("上级不能为自己的下级");
            }
            parent = findById(role.getParent().getId());//更新父节点
        }
        role.setParent(parent);
        super.update(role);

    }

    @Cacheable
    @Override
    public List<Role> findChildByParentId(String parentId) throws SerException {
        RoleDTO dto = new RoleDTO();
        dto.getConditions().add(Restrict.eq("role.id",parentId));
        return findByCis(dto);
    }


    /**
     * @param parentId 要更改的父亲节点
     * @param current   当前节点
     * @return
     * @throws SerException
     */
    private boolean isChildren(String parentId, Role current) throws SerException {
        //查询当前节点的孩子节点
        List<Role> children = this.findChildByParentId(current.getId());
        if (null != children && children.size() > 0) {
            if (children.stream().anyMatch(permission -> permission.getId().equals(parentId))) {
                return Boolean.TRUE;
            }
        }

        for (Role role : children) { //递归遍历所有子孙节点
            if (isChildren(parentId, role)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Cacheable()
    @Override
    public Set<Role> findChildByRoleId(String roleId) throws SerException {
        Set<Role> allChild = new HashSet<>();
        initAllChild(roleId, allChild);
        return allChild;
    }

    private void initAllChild(String roleId, Set<Role> allChild) throws SerException {
        //查询当前节点的所有孩子节点
        Set<Role> temp_child = new HashSet<>();
        List<Role> children = this.findChildByParentId(roleId);
        if (null != children && children.size() > 0) {
            children.stream().forEach(role -> {
                if (role.getParent().getId().equals(roleId)) {
                    temp_child.add(role);
                }
            });
        }

        if (null != temp_child && temp_child.size() > 0) {
            allChild.addAll(temp_child);
            for (Role child : temp_child) {
                initAllChild(child.getId(), allChild);
            }
        }
    }

    @Cacheable
    @Override
    public Set<Role> findRoleByUserId(String userId) throws SerException {
        Set<Role> roles = new HashSet<>();
        List<UserRole> userRoles = userRoleSer.findByUserId(userId);
        if (null != userRoles && userRoles.size() > 0) {
            userRoles.stream().forEach(userRole -> {
                roles.add(userRole.getRole());
            });
        }
        return roles;
    }
}
