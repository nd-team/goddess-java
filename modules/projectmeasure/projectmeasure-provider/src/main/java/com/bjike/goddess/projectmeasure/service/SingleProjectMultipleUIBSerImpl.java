package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectmeasure.dto.SingleProjectMultipleUIBDTO;
import com.bjike.goddess.projectmeasure.entity.SingleProjectMultipleUIB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 单个项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:51 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class SingleProjectMultipleUIBSerImpl extends ServiceImpl<SingleProjectMultipleUIB, SingleProjectMultipleUIBDTO> implements SingleProjectMultipleUIBSer {

}