package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.ReimburseAnalisisorBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseAnalisisorDTO;
import com.bjike.goddess.lendreimbursement.service.ReimburseAnalisisorSer;
import com.bjike.goddess.lendreimbursement.to.ReimburseAnalisisorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销分析人员表业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:51 ]
 * @Description: [ 报销分析人员表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("reimburseAnalisisorApiImpl")
public class ReimburseAnalisisorApiImpl implements ReimburseAnalisisorAPI {

    @Autowired
    private ReimburseAnalisisorSer reimburseAnalisisorSer;


    @Override
    public Long countReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        return reimburseAnalisisorSer.countReimburseAnalisisor(reimburseAnalisisorDTO);
    }

    @Override
    public List<ReimburseAnalisisorBO> listReimburseAnalisisor(ReimburseAnalisisorDTO reimburseAnalisisorDTO) throws SerException {
        return reimburseAnalisisorSer.listReimburseAnalisisor(reimburseAnalisisorDTO);
    }

    @Override
    public ReimburseAnalisisorBO addReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        return reimburseAnalisisorSer.addReimburseAnalisisor(reimburseAnalisisorTO);
    }

    @Override
    public ReimburseAnalisisorBO editReimburseAnalisisor(ReimburseAnalisisorTO reimburseAnalisisorTO) throws SerException {
        return reimburseAnalisisorSer.editReimburseAnalisisor(reimburseAnalisisorTO);
    }

    @Override
    public void deleteReimburseAnalisisor(String id) throws SerException {
        reimburseAnalisisorSer.deleteReimburseAnalisisor(id);
    }
}