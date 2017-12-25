package com.bjike.goddess.royalty.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.royalty.dto.DepartmentBetBDTO;
import com.bjike.goddess.royalty.entity.DepartmentBetB;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 部门间对赌表B业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:13 ]
 * @Description: [ 部门间对赌表B业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "royaltySerCache")
@Service
public class DepartmentBetBSerImpl extends ServiceImpl<DepartmentBetB, DepartmentBetBDTO> implements DepartmentBetBSer {

}