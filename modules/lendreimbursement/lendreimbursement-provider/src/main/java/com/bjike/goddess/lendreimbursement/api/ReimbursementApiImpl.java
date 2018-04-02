package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import com.bjike.goddess.lendreimbursement.entity.Reimbursement;
import com.bjike.goddess.lendreimbursement.service.ReimbursementSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 报销业务接口实现
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("reimbursementApiImpl")
public class ReimbursementApiImpl implements ReimbursementAPI {

    @Autowired
    private ReimbursementSer reimbursementSer;

    @Override
    public void reimbursementAdd(String data) throws IOException, SerException {
        reimbursementSer.reimbursementAdd(data);
    }

    @Override
    public MyPage getReimbursementPage(Integer pageNum,String progress) {
        return reimbursementSer.getReimbursementPage(pageNum, progress);
    }

    @Override
    public void reimbursementEdit(String data, String source) throws IOException, SerException {
        reimbursementSer.reimbursementEdit(data, source);
    }

    @Override
    public void reimbursementDel(String id) throws SerException {
        reimbursementSer.reimbursementDel(id);
    }

    @Override
    public Reimbursement getReimbursementById(String id) throws SerException {
        return reimbursementSer.getReimbursementById(id);
    }

}