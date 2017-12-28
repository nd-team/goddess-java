package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAuditLogDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseAuditLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销审核日志表业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "lendreimbursementSerCache")
@Service
public class ReimburseAuditLogSerImpl extends ServiceImpl<ReimburseAuditLog, ReimburseAuditLogDTO> implements ReimburseAuditLogSer {

    @Override
    public List<ReimburseAuditLogBO> listReimburseAuditLogByRid(String reimburseId) throws SerException {
        ReimburseAuditLogDTO dto = new ReimburseAuditLogDTO();
        dto.getConditions().add(Restrict.eq("reimrecordId",reimburseId));
        List<ReimburseAuditLog> list = super.findByCis( dto );
        List<ReimburseAuditLogBO> boList = BeanTransform.copyProperties( list,ReimburseAuditLogBO.class);
        return boList;
    }
}