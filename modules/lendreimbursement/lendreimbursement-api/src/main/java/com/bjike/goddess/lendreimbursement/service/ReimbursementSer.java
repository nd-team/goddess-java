package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.dto.ReimbursementDTO;
import com.bjike.goddess.lendreimbursement.entity.MyPage;
import com.bjike.goddess.lendreimbursement.entity.Reimbursement;

import java.io.IOException;

/**
 * 报销业务接口
 *
 * @Author: [ wany ]
 * @Date: [ 2018-03-12 03:02 ]
 * @Description: [ 报销业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReimbursementSer extends Ser<Reimbursement, ReimbursementDTO> {

    void reimbursementAdd(String data) throws IOException, SerException;

    MyPage getReimbursementPage(Integer pageNum, String progress);

    void reimbursementEdit(String data, String source) throws IOException, SerException;

    void reimbursementDel(String id) throws SerException;

    Reimbursement getReimbursementById(String id) throws SerException;
}