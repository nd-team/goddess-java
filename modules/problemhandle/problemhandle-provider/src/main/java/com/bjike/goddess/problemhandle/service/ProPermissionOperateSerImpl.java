package com.bjike.goddess.problemhandle.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.problemhandle.dto.ProPermissionOperateDTO;
import com.bjike.goddess.problemhandle.entity.ProPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 问题权限配置操作对象业务实现
 *
 * @Author: [ xiahzili ]
 * @Date: [ 2017-05-26 02:12 ]
 * @Description: [ 问题权限配置操作对象业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "problemhandleSerCache")
@Service
public class ProPermissionOperateSerImpl extends ServiceImpl<ProPermissionOperate, ProPermissionOperateDTO> implements ProPermissionOperateSer {

}