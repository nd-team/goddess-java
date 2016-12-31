package com.bjike.goddess.user.common.service;


import com.bjike.goddess.dbs.jpa.dto.Restrict;
import com.bjike.goddess.dbs.jpa.enums.Status;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.UserRoleDto;
import com.bjike.goddess.user.common.entity.UserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */

@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRoleSerImpl extends ServiceImpl<UserRole, UserRoleDto> implements IUserRoleSer {

    @Cacheable
    @Override
    public List<UserRole> findByUserId(String userId) throws SerException {
        UserRoleDto dto = new UserRoleDto();
        dto.getConditions().add(Restrict.eq("user.id",userId));
        dto.getConditions().add(Restrict.eq("role.status",Status.THAW));
        return findByCis(dto);
    }
}
