package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.customer.dto.EquipmentEnvironmentInfoDTO;
import com.bjike.goddess.customer.entity.EquipmentEnvironmentInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 设备搭建环境信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-12-28 09:34 ]
 * @Description: [ 设备搭建环境信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class EquipmentEnvironmentInfoSerImpl extends ServiceImpl<EquipmentEnvironmentInfo, EquipmentEnvironmentInfoDTO> implements EquipmentEnvironmentInfoSer {

}