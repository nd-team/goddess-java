package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.intromanage.dto.IndividualDisplayFieldDTO;
import com.bjike.goddess.intromanage.entity.IndividualDisplayField;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 个人简介显示字段业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 11:01 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class IndividualDisplayFieldSerImpl extends ServiceImpl<IndividualDisplayField, IndividualDisplayFieldDTO> implements IndividualDisplayFieldSer {

}