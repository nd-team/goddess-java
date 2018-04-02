package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.AuditingByReimDTO;
import com.bjike.goddess.lendreimbursement.entity.AuditingByReim;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * 报销-审核详情业务实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 05:03 ]
 * @Description: [ 报销-审核详情业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class AuditingByReimSerImpl extends ServiceImpl<AuditingByReim, AuditingByReimDTO> implements AuditingByReimSer {

}