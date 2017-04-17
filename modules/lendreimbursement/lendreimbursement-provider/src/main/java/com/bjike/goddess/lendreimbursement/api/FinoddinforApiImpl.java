package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.FinoddinforBO;
import com.bjike.goddess.lendreimbursement.dto.FinoddinforDTO;
import com.bjike.goddess.lendreimbursement.entity.Finoddinfor;
import com.bjike.goddess.lendreimbursement.service.FinoddinforSer;
import com.bjike.goddess.lendreimbursement.to.FinoddinforTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销单号管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("finoddinforApiImpl")
public class FinoddinforApiImpl implements FinoddinforAPI {

    @Autowired
    private FinoddinforSer finoddinforSer;

    @Override
    public Long countFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        return finoddinforSer.count(finoddinforDTO);
    }

    @Override
    public List<FinoddinforBO> listFinoddinfor(FinoddinforDTO finoddinforDTO) throws SerException {
        return finoddinforSer.listFinoddinfor(finoddinforDTO);
    }

    @Override
    public FinoddinforBO addFinoddinfor(FinoddinforTO finoddinforTO) throws SerException {
        return finoddinforSer.addFinoddinfor(finoddinforTO);
    }



    @Override
    public void deleteFinoddinfor(String id) throws SerException {
        finoddinforSer.deleteFinoddinfor(id);
    }

    @Override
    public void congealFinoddinfor(String id) throws SerException {
        finoddinforSer.congealFinoddinfor(id);
    }

    @Override
    public void thawFinoddinfor(String id) throws SerException {
        finoddinforSer.thawFinoddinfor(id);
    }

    @Override
    public String getMinRunNum() throws SerException {
        return finoddinforSer.getMinRunNum();
    }
}