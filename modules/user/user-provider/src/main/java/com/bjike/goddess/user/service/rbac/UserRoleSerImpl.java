package com.bjike.goddess.user.service.rbac;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.bo.rbac.UserRoleBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.rbac.RoleDTO;
import com.bjike.goddess.user.dto.rbac.UserRoleDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.entity.rbac.UserRole;
import com.bjike.goddess.user.service.UserSer;
import com.bjike.goddess.user.to.rbac.UserRoleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRoleSerImpl extends ServiceImpl<UserRole, UserRoleDTO> implements UserRoleSer {

    @Autowired
    private UserSer userSer;
    @Autowired
    private RoleSer roleSer;

    @Cacheable
    @Override
    public List<UserRole> findByUserId(String userId) throws SerException {
        UserRoleDTO dto = new UserRoleDTO();
        dto.getConditions().add(Restrict.eq("user.id", userId));
        dto.getConditions().add(Restrict.eq("role.status", Status.THAW));
        return findByCis(dto);
    }

    @Override
    public UserRoleBO saveByTO(UserRoleTO userRoleTO) throws SerException {
        UserDTO userDTO = new UserDTO();
        userDTO.getConditions().add(Restrict.eq("id", userRoleTO.getUserId()));
        User user = userSer.findOne(userDTO);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.getConditions().add(Restrict.eq("id", userRoleTO.getRoleId()));
        Role role = roleSer.findOne(roleDTO);
        if (null == user) {
            throw new SerException("用户不存在!");
        }
        if (null == role) {
            throw new SerException("角色不存在!");
        }
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        super.save(userRole);
        UserRoleBO userRoleBO = new UserRoleBO();
        userRoleBO.setId(userRole.getId());
        userRoleBO.setRoleId(role.getId());
        userRoleBO.setUserId(user.getId());
        return userRoleBO;
    }
}
