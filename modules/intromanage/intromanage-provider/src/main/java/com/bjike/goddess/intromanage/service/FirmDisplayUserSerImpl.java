package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.intromanage.dto.FirmDisplayUserDTO;
import com.bjike.goddess.intromanage.entity.FirmDisplayUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 公司简介显示用户名称集合业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 09:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class FirmDisplayUserSerImpl extends ServiceImpl<FirmDisplayUser, FirmDisplayUserDTO> implements FirmDisplayUserSer {

}