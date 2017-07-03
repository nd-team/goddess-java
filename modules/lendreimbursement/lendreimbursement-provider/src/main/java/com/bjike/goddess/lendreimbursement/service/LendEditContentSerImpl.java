package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.LendEditContentDTO;
import com.bjike.goddess.lendreimbursement.entity.LendEditContent;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 借款审核直接修改业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:09 ]
 * @Description: [ 借款审核直接修改业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class LendEditContentSerImpl extends ServiceImpl<LendEditContent, LendEditContentDTO> implements LendEditContentSer {

}