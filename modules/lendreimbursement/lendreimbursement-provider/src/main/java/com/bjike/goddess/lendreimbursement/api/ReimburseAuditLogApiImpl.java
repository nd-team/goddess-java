package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAuditLogBO;
import com.bjike.goddess.lendreimbursement.service.ReimburseAuditLogSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销审核日志表业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("reimburseAuditLogApiImpl")
public class ReimburseAuditLogApiImpl implements ReimburseAuditLogAPI {

    @Autowired
    private ReimburseAuditLogSer reimburseAuditLogSer;

    @Override
    public List<ReimburseAuditLogBO> listReimburseAuditLogByRid(String reimburseId) throws SerException {
        return reimburseAuditLogSer.listReimburseAuditLogByRid(reimburseId );
    }
}