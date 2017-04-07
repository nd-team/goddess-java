package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.intromanage.dto.FirmDisplayFieldDTO;
import com.bjike.goddess.intromanage.entity.FirmDisplayField;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 公司简介显示字段业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 10:15 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class FirmDisplayFieldSerImpl extends ServiceImpl<FirmDisplayField, FirmDisplayFieldDTO> implements FirmDisplayFieldSer {

}