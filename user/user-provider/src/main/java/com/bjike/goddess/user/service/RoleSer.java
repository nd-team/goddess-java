package com.bjike.goddess.user.service;


import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.RoleDTO;
import com.bjike.goddess.user.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service("roleSer")
public class RoleSer extends ServiceImpl<Role, RoleDTO> implements RoleAPI {

}
