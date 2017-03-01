package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.dto.rbac.PermissionDTO;
import com.bjike.goddess.user.entity.rbac.*;
import com.bjike.goddess.user.sto.rbac.PermissionSTO;
import com.bjike.goddess.user.sto.rbac.PermissionTreeSTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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
@Service("permissionSer")
public class PermissionSer extends ServiceImpl<Permission, PermissionDTO> implements PermissionAPI {
    @Autowired
    private UserRoleAPI userRoleAPI;
    @Autowired
    private GroupUserAPI groupUserAPI;
    @Autowired
    private GroupRoleAPI groupRoleAPI;
    @Autowired
    private RolePermissionAPI rolePermissionAPI;

    @Override
    public List<PermissionSTO> findByUserId(String userId) throws SerException {
        //通过角色(用户角色,组角色)查询其拥有的权限
        Set<String> role_ids = new HashSet<>();
        List<UserRole> userRoles = userRoleAPI.findByUserId(userId);
        List<GroupUser> groupUsers = groupUserAPI.findByUserId(userId);
        List<GroupRole> groupRoles = null;
        if (null != groupUsers && groupUsers.size() > 0) { //组角色
            List<String> group_ids = new ArrayList<>(groupUsers.size());
            groupUsers.stream().forEach(groupUser -> {
                group_ids.add(groupUser.getGroup().getId());
            });
            groupRoles = groupRoleAPI.findByGroupIds(group_ids.toArray(new String[group_ids.size()]));
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
        //查询角色资源权限
        Set<Permission> permissions = new HashSet<>();
        if (0 < role_ids.size()) {
            List<RolePermission> rolePermissions = rolePermissionAPI.findByRoleIds(role_ids.toArray(new String[role_ids.size()]));
            rolePermissions.stream().forEach(rolePermission -> {
                permissions.add(rolePermission.getPermission());
            });
        }
        return BeanTransform.copyProperties(permissions, PermissionSTO.class);
    }

    @Override
    public List<PermissionSTO> findByRoleId(String roleId) throws SerException {
        List<RolePermission> rolePermissions = rolePermissionAPI.findByRoleIds(roleId);
        Set<Permission> permissions = new HashSet<>();
        rolePermissions.stream().forEach(rolePermission -> {
            permissions.add(rolePermission.getPermission());
        });
        return BeanTransform.copyProperties(permissions, PermissionSTO.class);
    }

    @Override
    public List<PermissionTreeSTO> treeData(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        if (StringUtils.isNotBlank(id)) {
            dto.getConditions().add(Restrict.eq("parent.id", id)); //查询该父节点下的子节点
        } else {
            dto.getConditions().add(Restrict.isNull("parent.id")); //查找根节点
        }
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));

        List<Permission> permissions = super.findByCis(dto);
        List<PermissionTreeSTO> permissionTreeSTOS = new ArrayList<>(permissions.size());
        permissions.stream().forEach(permission -> {
            PermissionTreeSTO sto = new PermissionTreeSTO();
            sto.setName(permission.getName());
            sto.setId(permission.getId());
            sto.setParent(null == permission.getParent());
            permissionTreeSTOS.add(sto);
        });

        return permissionTreeSTOS;
    }

    @Override
    public void delete(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        dto.getConditions().add(Restrict.eq("parent.id",id));
        List<Permission> children = findByCis(dto);
        if(null!=children && children.size()>0){
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }
        super.delete(id);
    }

    @Override
    public PermissionDTO savePermission(Permission permission) throws SerException {
        return BeanTransform.copyProperties(super.save(permission),PermissionSTO.class);
    }
}
