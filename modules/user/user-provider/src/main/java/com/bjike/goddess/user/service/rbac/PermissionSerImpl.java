package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.bo.rbac.PermissionTreeBO;
import com.bjike.goddess.user.dto.rbac.PermissionDTO;
import com.bjike.goddess.user.entity.rbac.*;
import com.bjike.goddess.user.to.rbac.PermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限认证业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class PermissionSerImpl extends ServiceImpl<Permission, PermissionDTO> implements PermissionSer {
    @Autowired
    private UserRoleSer userRoleSer;
    @Autowired
    private GroupUserSer groupUserSer;
    @Autowired
    private GroupRoleSer groupRoleSer;
    @Autowired
    private RolePermissionSer rolePermissionSer;

    @Override
    public List<PermissionBO> findByUserId(String userId) throws SerException {
        Set<String> role_ids = allRoleId(userId);
        //查询角色资源权限
        Set<Permission> permissions = new HashSet<>();
        if (0 < role_ids.size()) {
            List<RolePermission> rolePermissions = rolePermissionSer.findByRoleIds(role_ids.toArray(new String[role_ids.size()]));
            rolePermissions.stream().forEach(rolePermission -> {
                permissions.add(rolePermission.getPermission());
            });
        }
        return BeanTransform.copyProperties(permissions, PermissionBO.class);
    }

    @Override
    public List<PermissionBO> findPermissions(String userId) throws SerException {
        Set<String> role_ids = allRoleId(userId);
        //查询角色资源权限
        List<PermissionBO> permissionList = null;
        if (0 < role_ids.size()) {
            List<RolePermission> rolePermissions = rolePermissionSer.findByRoleIds(role_ids.toArray(new String[role_ids.size()]));
            Set<Permission> permissionSet = new HashSet<>();
            for (RolePermission rolePermission : rolePermissions) { // 去重
                permissionSet.add(rolePermission.getPermission());
            }
            permissionList = new ArrayList<>(permissionSet.size());
            for (Permission permission : permissionSet) {
                PermissionBO bo = new PermissionBO();
                bo.setId(permission.getId());
                bo.setName(permission.getName());
                bo.setResource(permission.getResource());
                bo.setHasChild(0 < this.getChild(permission.getId()).size());
                permissionList.add(bo);
            }
        }
        return permissionList;
    }

    private Set<String> allRoleId(String userId) throws SerException {
        //通过角色(用户角色,组角色)查询其拥有的权限
        Set<String> role_ids = new HashSet<>();
        List<UserRole> userRoles = userRoleSer.findByUserId(userId);
        List<GroupUser> groupUsers = groupUserSer.findByUserId(userId);
        List<GroupRole> groupRoles = null;
        if (null != groupUsers && groupUsers.size() > 0) { //组角色
            List<String> group_ids = new ArrayList<>(groupUsers.size());
            groupUsers.stream().forEach(groupUser -> {
                group_ids.add(groupUser.getGroup().getId());
            });
            groupRoles = groupRoleSer.findByGroupIds(group_ids.toArray(new String[group_ids.size()]));
        }
        if (null != userRoles && userRoles.size() > 0) { //用户角色
            userRoles.stream().forEach(userRole -> {
                role_ids.add(userRole.getRole().getId());
            });
        }
        if (null != groupRoles && groupRoles.size() > 0) {
            groupRoles.stream().forEach(groupRole -> {
                role_ids.add(groupRole.getRole().getId());
            });
        }
        return role_ids;
    }

    @Override
    public List<PermissionBO> findByRoleId(String roleId) throws SerException {
        List<RolePermission> rolePermissions = rolePermissionSer.findByRoleIds(roleId);
        Set<Permission> permissions = new HashSet<>();
        rolePermissions.stream().forEach(rolePermission -> {
            permissions.add(rolePermission.getPermission());
        });
        return BeanTransform.copyProperties(permissions, PermissionBO.class);
    }

    @Override
    public List<PermissionTreeBO> treeData(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Permission> permissions = super.findByCis(dto);
        List<PermissionTreeBO> permissionTreeBOS = new ArrayList<>(permissions.size());
        permissions.stream().forEach(permission -> {
            PermissionTreeBO bo = new PermissionTreeBO();
            bo.setName(permission.getName());
            bo.setId(permission.getId());
            bo.setParent(null == permission.getParent());
            permissionTreeBOS.add(bo);
        });

        return permissionTreeBOS;
    }

    @Override
    public void remove(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Permission> children = findByCis(dto);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }
        super.remove(id);
    }

    @Override
    public PermissionBO save(PermissionTO permissionTO) throws SerException {
        Permission permission = BeanTransform.copyProperties(permissionTO, Permission.class, true);
        super.save(permission);

        return BeanTransform.copyProperties(permission, PermissionBO.class);
    }

    @Override
    public void update(PermissionTO permissionTO) throws SerException {
        Permission permission = super.findById(permissionTO.getId());
        BeanTransform.copyProperties(permissionTO, permission, true);
        permission.setModifyTime(LocalDateTime.now());
        super.update(permission);
    }

    @Override
    public List<Permission> getChild(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Permission> children = findByCis(dto);
        return children;
    }

}
