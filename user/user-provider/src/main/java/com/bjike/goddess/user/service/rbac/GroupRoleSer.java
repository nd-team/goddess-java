package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.rbac.GroupRoleDTO;
import com.bjike.goddess.user.entity.rbac.GroupRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组角色业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 14:09]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("groupRoleSer")
public class GroupRoleSer extends ServiceImpl<GroupRole, GroupRoleDTO> implements GroupRoleAPI {
    @Override
    public List<GroupRole> findByGroupIds(String... ids) throws SerException {
        GroupRoleDTO dto = new GroupRoleDTO();
        dto.getConditions().add(Restrict.in("group.id", ids));
        dto.getConditions().add(Restrict.eq("role.status", Status.THAW));
        return findByCis(dto);
    }
}
