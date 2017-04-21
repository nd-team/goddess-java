package com.bjike.goddess.capability.api;

import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.dto.CollectEmailDTO;
import com.bjike.goddess.capability.service.CollectEmailSer;
import com.bjike.goddess.capability.to.CollectEmailTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务能力邮件业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 商务项目合同邮件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("collectEmailApiImpl")
public class CollectEmailApiImpl implements CollectEmailAPI {

    @Autowired
    private CollectEmailSer collectEmailSer;

    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.counts(collectEmailDTO);
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        return collectEmailSer.getOne(id);
    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        return collectEmailSer.listCollectEmail( collectEmailDTO );
    }

    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.addCollectEmail( collectEmailTO);
    }

    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return collectEmailSer.editCollectEmail(collectEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        collectEmailSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        collectEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        collectEmailSer.thawCollectEmail(id);
    }

    @Override
    public List<CollectEmailBO> collectCompanyEmail(String[] companyOrName) throws SerException {
        return collectEmailSer.collectCompanyEmail(companyOrName);
    }

    @Override
    public List<CollectEmailBO> collectSelfEmail(String[] name) throws SerException {
        return collectEmailSer.collectSelfEmail(name);
    }

    @Override
    public List<CollectEmailBO> collectCooperEmail(String[] companyOrName) throws SerException {
        return collectEmailSer.collectCooperEmail(companyOrName);
    }
}