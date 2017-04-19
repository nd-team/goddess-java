package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.bo.rbac.PermissionTreeBO;
import com.bjike.goddess.user.dto.rbac.PermissionDTO;
import com.bjike.goddess.user.entity.rbac.GroupRole;
import com.bjike.goddess.user.entity.rbac.Permission;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.entity.rbac.RolePermission;
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
    private RolePermissionSer rolePermissionSer;

    @Override
    public List<PermissionBO> findByUserId(String userId) throws SerException {
        String table_permission = super.getTableName(Permission.class);
        String table_role = super.getTableName(Role.class);
        String table_rolePermission = super.getTableName(RolePermission.class);
        String table_groupRole = super.getTableName(GroupRole.class);

        StringBuilder sb = new StringBuilder();
        String[] fields = new String[]{"id", "name", "resource", "hasChild"};
        sb.append("  SELECT  DISTINCT a.id,a.name,a.resource,a.is_hasChild as hasChild FROM ");
        sb.append(table_permission);
        sb.append(" a,( ");
//     #角色所有权限id
        sb.append("  SELECT a.permission_id as id FROM  ");
        sb.append(table_rolePermission);
        sb.append(" a WHERE a.role_id IN ");
//    #某用户所有角色
        sb.append(" (SELECT DISTINCT id FROM (SELECT b.id from rbac_user_role a , ");
        sb.append(table_role);
        sb.append("  b WHERE a.role_id = b.id and a.user_id= '");
        sb.append(userId);
        sb.append("'");
        sb.append(" UNION ALL ");
        sb.append(" SELECT c.id FROM ");
        sb.append(table_groupRole);
        sb.append(" c,");
        sb.append(table_role);
        sb.append(" d  WHERE c.role_id =d.id)f ) ) b WHERE a.id=b.id ");
        String sql = sb.toString();
        List<PermissionBO> permissions = super.findBySql(sql, PermissionBO.class, fields);
        return permissions;
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

        List<Permission> children = getChild(id);
        if (null != children && children.size() > 0) {
            throw new SerException("该记录存在子节点数据,请先删除子节点!");
        }

        Permission permission = super.findById(id);
        Permission parent = permission.getParent();
        if (null != parent) {
            children = getChild(parent.getId());
            parent.setHasChild(children.size() != 0);
            super.update(parent);
        }
        super.remove(id);
    }

    @Override
    public PermissionBO save(PermissionTO permissionTO) throws SerException {
        Permission permission = BeanTransform.copyProperties(permissionTO, Permission.class, true);
        if (StringUtils.isNotBlank(permissionTO.getParentId())) {
            PermissionDTO dto = new PermissionDTO();
            dto.getConditions().add(Restrict.eq("id", permissionTO.getParentId()));
            Permission parent = findOne(dto);
            if (null != parent) {
                permission.setParent(parent);
                parent.setHasChild(true); //更新父类子节点字段为true
                super.update(parent);
            }
        }
        super.save(permission);
        return BeanTransform.copyProperties(permission, PermissionBO.class);
    }

    @Override
    public void update(PermissionTO permissionTO) throws SerException {
        Permission permission = super.findById(permissionTO.getId());
        Permission old_parent = permission.getParent();
        String parentId = permissionTO.getParentId();
        Permission new_parent = null;
        if (StringUtils.isNotBlank(parentId)) {
            PermissionDTO dto = new PermissionDTO();
            dto.getConditions().add(Restrict.eq("id", permissionTO.getParentId()));
            new_parent = super.findOne(dto);
        }

        //更新
        permission.setModifyTime(LocalDateTime.now());
        permission.setName(permissionTO.getName());
        permission.setResource(permissionTO.getResource());
        permission.setParent(new_parent);
        super.update(permission);


        if (null != new_parent) {
            new_parent.setHasChild(true);
            super.update(new_parent);
        }

        /**
         * 维护该节点与其父类是否有子节点
         */
        if (null != old_parent) { //以前有父节点,删除父节点后,检查其未更改前的父节点是否还存在子节点
            List<Permission> children = getChild(old_parent.getId()); //查询以未更改前其父类的所有子节点
            old_parent.setHasChild(children.size() != 0);
            super.update(old_parent);
        }


    }

    @Override
    public List<Permission> getChild(String id) throws SerException {
        PermissionDTO dto = new PermissionDTO();
        dto.getConditions().add(Restrict.eq("parent.id", id));
        List<Permission> children = findByCis(dto);
        return children;
    }

}
