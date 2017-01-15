package com.bjike.goddess.user.service;


import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.UserRoleDTO;
import com.bjike.goddess.user.entity.UserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRoleSer extends ServiceImpl<UserRole, UserRoleDTO> implements UserRoleAPI {

    @Cacheable
    @Override
    public List<UserRole> findByUserId(String userId) throws SerException {
        UserRoleDTO dto = new UserRoleDTO();
        dto.getConditions().add(Restrict.eq("user.id",userId));
        dto.getConditions().add(Restrict.eq("role.status", Status.THAW));
        return findByCis(dto);
    }
}
