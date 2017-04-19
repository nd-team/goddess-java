package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.entity.DeviceType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 设备类型业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "materialbuySerCache")
@Service
public class DeviceTypeSerImpl extends ServiceImpl<DeviceType, DeviceTypeDTO> implements DeviceTypeSer {

}