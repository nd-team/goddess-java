package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.LendAuditDetailDTO;
import com.bjike.goddess.lendreimbursement.service.LendAuditDetailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 借款审核人员业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:06 ]
 * @Description: [ 借款审核人员业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("lendAuditDetailApiImpl")
public class LendAuditDetailApiImpl implements LendAuditDetailAPI {

    @Autowired
    private LendAuditDetailSer lendAuditDetailSer;


    @Override
    public Long countDetail(LendAuditDetailDTO lendAuditDetailDTO) throws SerException {
        return lendAuditDetailSer.countDetail(lendAuditDetailDTO);
    }

    @Override
    public List<LendAuditDetailBO> listLendAuditDetail(LendAuditDetailDTO lendAuditDetailDTO) throws SerException {
        return lendAuditDetailSer.listLendAuditDetail(lendAuditDetailDTO);
    }
}