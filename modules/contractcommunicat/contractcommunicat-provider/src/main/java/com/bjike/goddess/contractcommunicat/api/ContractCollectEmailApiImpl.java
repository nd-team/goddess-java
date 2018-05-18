package com.bjike.goddess.contractcommunicat.api;


import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractcommunicat.bo.CollectEmailBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectContractCollectBO;
import com.bjike.goddess.contractcommunicat.bo.ProjectOutsourcingCollectBO;
import com.bjike.goddess.contractcommunicat.dto.CollectEmailDTO;
import com.bjike.goddess.contractcommunicat.entity.CollectEmail;
import com.bjike.goddess.contractcommunicat.service.ContractCollectEmailSer;
import com.bjike.goddess.contractcommunicat.to.CollectEmailTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目商务洽谈合同邮件业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.889 ]
 * @Description: [ 商务项目合同邮件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractCollectEmailApiImpl")
public class ContractCollectEmailApiImpl implements ContractCollectEmailAPI {

    @Autowired
    private ContractCollectEmailSer contractCollectEmailSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return contractCollectEmailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return contractCollectEmailSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        return contractCollectEmailSer.counts(collectEmailDTO);
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        return contractCollectEmailSer.getOne(id);
    }
    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        return contractCollectEmailSer.listCollectEmail( collectEmailDTO );
    }

    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return contractCollectEmailSer.addCollectEmail( collectEmailTO);
    }

    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        return contractCollectEmailSer.editCollectEmail(collectEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        contractCollectEmailSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        contractCollectEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        contractCollectEmailSer.thawCollectEmail(id);
    }

    @Override
    public List<ProjectContractCollectBO> gatherPb(CollectEmail to) throws SerException {
        return contractCollectEmailSer.gatherPb(to);
    }

    @Override
    public List<ProjectOutsourcingCollectBO> gatherPc(CollectEmail to) throws SerException {
        return contractCollectEmailSer.gatherPc(to);
    }


    @Override
    public void checkSendEmail() throws SerException {
        contractCollectEmailSer.checkSendEmail();
    }


}