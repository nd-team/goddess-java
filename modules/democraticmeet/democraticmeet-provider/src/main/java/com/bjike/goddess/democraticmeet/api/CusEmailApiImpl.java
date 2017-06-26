package com.bjike.goddess.democraticmeet.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.democraticmeet.bo.CusEmailBO;
import com.bjike.goddess.democraticmeet.dto.CusEmailDTO;
import com.bjike.goddess.democraticmeet.service.CusEmailSer;
import com.bjike.goddess.democraticmeet.to.CusEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户邮件发送定制业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 客户邮件发送定制业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cusEmailApiImpl")
public class CusEmailApiImpl implements CusEmailAPI {

    @Autowired
    private CusEmailSer cusEmailSer;

    @Override
    public Long countCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return cusEmailSer.countCusEmail( cusEmailDTO);
    }

    @Override
    public List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return cusEmailSer.listCusEmail( cusEmailDTO );
    }

    @Override
    public CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {
        return cusEmailSer.addCusEmail( cusEmailTO);
    }

    @Override
    public CusEmailBO editCusEmail(CusEmailTO cusEmailTO) throws SerException {
        return cusEmailSer.editCusEmail(cusEmailTO);
    }

    @Override
    public void deleteCusEmail(String id) throws SerException {
        cusEmailSer.deleteCusEmail(id);
    }

    @Override
    public CusEmailBO getCusEmailById(String id) throws SerException {
        return cusEmailSer.getCusEmailById(id);
    }
}