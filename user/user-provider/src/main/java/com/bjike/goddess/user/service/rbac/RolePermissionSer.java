package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.rbac.RolePermissionDTO;
import com.bjike.goddess.user.entity.rbac.RolePermission;
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
@Service("rolePermissionSer")
public class RolePermissionSer extends ServiceImpl<RolePermission, RolePermissionDTO> implements RolePermissionAPI {
    @Override
    public List<RolePermission> findByRoleIds(String... ids) throws SerException {
        RolePermissionDTO dto = new RolePermissionDTO();
        dto.getConditions().add(Restrict.in("role.id", ids));
        dto.getConditions().add(Restrict.eq("permission.status", Status.THAW));
        List<RolePermission> list =  super.findByCis(dto);
        return list;
    }
}
