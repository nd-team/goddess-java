package com.bjike.goddess.annual.api;

import com.bjike.goddess.annual.bo.AnnualApplyBO;
import com.bjike.goddess.annual.dto.AnnualApplyDTO;
import com.bjike.goddess.annual.service.AnnualApplySer;
import com.bjike.goddess.annual.to.AnnualApplyAuditTo;
import com.bjike.goddess.annual.to.AnnualApplyTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 年假申请业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:13 ]
 * @Description: [ 年假申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("annualApplyApiImpl")
public class AnnualApplyApiImpl implements AnnualApplyAPI {

    @Autowired
    private AnnualApplySer annualApplySer;

    @Override
    public Boolean sonPermission() throws SerException {
        return annualApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return annualApplySer.guidePermission(guidePermissionTO);
    }

    @Override
    public AnnualApplyBO save(AnnualApplyTO to) throws SerException {
        return annualApplySer.save(to);
    }

    @Override
    public AnnualApplyBO delete(AnnualApplyTO to) throws SerException {
        return annualApplySer.delete(to);
    }

    @Override
    public AnnualApplyBO audit(AnnualApplyAuditTo to) throws SerException {
        return annualApplySer.audit(to);
    }

    @Override
    public List<AnnualApplyBO> findByUsername(String username) throws SerException {
        return annualApplySer.findByUsername(username);
    }

    @Override
    public List<AnnualApplyBO> findByInfo(String info_id) throws SerException {
        return annualApplySer.findByInfo(info_id);
    }

    @Override
    public List<AnnualApplyBO> maps(AnnualApplyDTO dto) throws SerException {
        return annualApplySer.maps(dto);
    }

    @Override
    public AnnualApplyBO getById(String id) throws SerException {
        return annualApplySer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return annualApplySer.getTotal();
    }

    @Override
    public String getStartTime() throws SerException {
        return annualApplySer.getStartTime();
    }
}