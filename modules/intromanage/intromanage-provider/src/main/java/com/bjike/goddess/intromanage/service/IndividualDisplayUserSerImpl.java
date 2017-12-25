package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.intromanage.dto.IndividualDisplayUserDTO;
import com.bjike.goddess.intromanage.entity.IndividualDisplayUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 个人简介显示用户名称集合业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 04:12 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class IndividualDisplayUserSerImpl extends ServiceImpl<IndividualDisplayUser, IndividualDisplayUserDTO> implements IndividualDisplayUserSer {

}