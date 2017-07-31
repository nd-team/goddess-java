package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectmeasure.dto.MultipleProjectSingleUIBDTO;
import com.bjike.goddess.projectmeasure.entity.MultipleProjectSingleUIB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 多项目单个界面业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class MultipleProjectSingleUIBSerImpl extends ServiceImpl<MultipleProjectSingleUIB, MultipleProjectSingleUIBDTO> implements MultipleProjectSingleUIBSer {

}