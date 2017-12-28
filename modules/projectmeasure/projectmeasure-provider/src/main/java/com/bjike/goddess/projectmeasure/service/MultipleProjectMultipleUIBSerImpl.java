package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectMultipleUIBDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectMultipleUIB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 多项目多个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectMultipleUIBSerImpl extends ServiceImpl<MultipleProjectMultipleUIB, MultipleProjectMultipleUIBDTO> implements MultipleProjectMultipleUIBSer {

}