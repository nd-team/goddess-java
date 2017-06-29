package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendCopyDTO;
import com.bjike.goddess.lendreimbursement.entity.ApplyLendCopy;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 申请借款副本业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:02 ]
 * @Description: [ 申请借款副本业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ApplyLendCopySerImpl extends ServiceImpl<ApplyLendCopy, ApplyLendCopyDTO> implements ApplyLendCopySer {

}