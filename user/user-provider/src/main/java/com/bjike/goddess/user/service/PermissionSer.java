package com.bjike.goddess.user.service;


import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.user.dto.PermissionDTO;
import com.bjike.goddess.user.entity.Permission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

}
