package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.service.CusEmailSer;
import com.bjike.goddess.customer.to.CusEmailTO;
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
    public void congealCusEmail(String id) throws SerException {
        cusEmailSer.congealCusEmail(id);
    }

    @Override
    public void thawCusEmail(String id) throws SerException {
        cusEmailSer.thawCusEmail(id);
    }

    @Override
    public List<CusEmailBO> collectCusEmail(String[] works) throws SerException {
        return cusEmailSer.collectCusEmail(works);
    }
}