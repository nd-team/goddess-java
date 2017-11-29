package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.SubsidySchemeBO;
import com.bjike.goddess.assistance.dto.SubsidySchemeDTO;
import com.bjike.goddess.assistance.entity.SubsidyScheme;
import com.bjike.goddess.assistance.service.SubsidySchemeSer;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SubsidySchemeTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司补助方案业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("subsidySchemeApiImpl")
public class SubsidySchemeApiImpl implements SubsidySchemeAPI {
    @Autowired
    private SubsidySchemeSer subsidySchemeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return subsidySchemeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return subsidySchemeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        return subsidySchemeSer.countSubsidy(subsidySchemeDTO);
    }

    @Override
    public SubsidySchemeBO getOneById(String id) throws SerException {
        return subsidySchemeSer.getOneById(id);
    }

    @Override
    public List<SubsidySchemeBO> listSubsidy(SubsidySchemeDTO subsidySchemeDTO) throws SerException {
        return subsidySchemeSer.listSubsidy(subsidySchemeDTO);
    }

    @Override
    public SubsidySchemeBO addSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        return subsidySchemeSer.addSubsidy(subsidySchemeTO);
    }

    @Override
    public SubsidySchemeBO editSubsidy(SubsidySchemeTO subsidySchemeTO) throws SerException {
        return subsidySchemeSer.editSubsidy(subsidySchemeTO);
    }

    @Override
    public void deleteSubsidy(String id) throws SerException {
        subsidySchemeSer.deleteSubsidy(id);
    }

    @Override
    public void manageAudit(SubsidySchemeTO subsidySchemeTO) throws SerException {
        subsidySchemeSer.manageAudit(subsidySchemeTO);
    }
}