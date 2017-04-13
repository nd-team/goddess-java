package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.dto.rbac.GroupDTO;
import com.bjike.goddess.user.dto.rbac.GroupRoleDTO;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.entity.rbac.Group;
import com.bjike.goddess.user.entity.rbac.GroupRole;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service
public class GroupRoleSerImpl extends ServiceImpl<GroupRole, GroupRoleDTO> implements GroupRoleSer {
    @Autowired
    private GroupSer groupSer;
    @Autowired
    private RoleSer roleSer;

    @Override
    public List<GroupRole> findByGroupIds(String... ids) throws SerException {
        GroupRoleDTO dto = new GroupRoleDTO();
        dto.getConditions().add(Restrict.in("group.id", ids));
        dto.getConditions().add(Restrict.eq("role.status", Status.THAW));
        return findByCis(dto);
    }

    @Override
    public GroupRoleBO saveByTO(GroupRoleTO groupRoleTO) throws SerException {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.getConditions().add(Restrict.eq("id", groupRoleTO.getGroupId()));
        Group group = groupSer.findOne(groupDTO);
        if (null == group) {
            throw new SerException("组信息不存在");
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.getConditions().add(Restrict.eq("id", groupRoleTO.getRoleId()));
        Role role = roleSer.findOne(roleDTO);
        if (null == role) {
            throw new SerException("角色信息不存在");
        }
        GroupRole groupRole = new GroupRole();
        groupRole.setGroup(group);
        groupRole.setRole(role);
        super.save(groupRole);
        GroupRoleBO groupRoleBO = new GroupRoleBO();
        groupRoleBO.setId(groupRole.getId());
        groupRoleBO.setGroupId(group.getId());
        groupRoleBO.setRoleId(role.getId());
        return groupRoleBO;
    }
}
