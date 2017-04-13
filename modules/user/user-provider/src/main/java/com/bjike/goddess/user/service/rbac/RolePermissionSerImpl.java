package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.bo.rbac.RolePermissionBO;
import com.bjike.goddess.user.dto.rbac.PermissionDTO;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.dto.rbac.RolePermissionDTO;
import com.bjike.goddess.user.entity.rbac.Permission;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.entity.rbac.RolePermission;
import com.bjike.goddess.user.to.rbac.RolePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色资源业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class RolePermissionSerImpl extends ServiceImpl<RolePermission, RolePermissionDTO> implements RolePermissionSer {

    @Autowired
    private RoleSer roleSer;
    @Autowired
    private PermissionSer permissionSer;

    @Override
    public List<RolePermission> findByRoleIds(String... ids) throws SerException {
        RolePermissionDTO dto = new RolePermissionDTO();
        dto.getConditions().add(Restrict.in("role.id", ids));
        dto.getConditions().add(Restrict.eq("permission.status", Status.THAW));
        List<RolePermission> list = super.findByCis(dto);
        return list;
    }

    @Override
    public RolePermissionBO saveByTO(RolePermissionTO rolePermissionTO) throws SerException {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.getConditions().add(Restrict.eq("id", rolePermissionTO.getPermissionId()));
        Permission permission = permissionSer.findOne(permissionDTO);
        if (null == permission) {
            throw new SerException("权限信息不存在");
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.getConditions().add(Restrict.eq("id", rolePermissionTO.getRoleId()));
        Role role = roleSer.findOne(roleDTO);
        if (null == role) {
            throw new SerException("角色信息不存在");
        }
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermission(permission);
        rolePermission.setRole(role);
        super.save(rolePermission);
        RolePermissionBO rolePermissionBO = new RolePermissionBO();
        rolePermissionBO.setId(rolePermission.getId());
        rolePermissionBO.setPermissionId(permission.getId());
        rolePermissionBO.setRoleId(role.getId());
        return rolePermissionBO;
    }
}
