package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmove.bo.StaffMovementApplyBO;
import com.bjike.goddess.staffmove.dto.StaffMovementApplyDTO;
import com.bjike.goddess.staffmove.entity.StaffMovementApply;
import com.bjike.goddess.staffmove.excel.SonPermissionObject;
import com.bjike.goddess.staffmove.service.StaffMovementApplySer;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMovementApplyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 人员调动申请业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-22 04:40 ]
 * @Description: [ 人员调动申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffMovementApplyApiImpl")
public class StaffMovementApplyApiImpl implements StaffMovementApplyAPI {
    @Autowired
    private StaffMovementApplySer staffMovementApplySer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return staffMovementApplySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffMovementApplySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        return staffMovementApplySer.countStaffMovementApply(staffMovementApplyDTO);
    }
    @Override
    public StaffMovementApplyBO getOne(String id) throws SerException {
        return staffMovementApplySer.getOne(id);
    }

    @Override
    public List<StaffMovementApplyBO> findListStaffMovementApply(StaffMovementApplyDTO staffMovementApplyDTO) throws SerException {
        return staffMovementApplySer.findListStaffMovementApply(staffMovementApplyDTO);
    }

    @Override
    public StaffMovementApplyBO insertStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        return staffMovementApplySer.insertStaffMovementApply(staffMovementApplyTO);
    }

    @Override
    public StaffMovementApplyBO editStaffMovementApply(StaffMovementApplyTO staffMovementApplyTO) throws SerException {
        return staffMovementApplySer.editStaffMovementApply(staffMovementApplyTO);
    }

    @Override
    public void removeStaffMovementApply(String id) throws SerException {
        staffMovementApplySer.removeStaffMovementApply(id);
    }
    @Override
    public StaffMovementApplyBO planAudit(StaffMovementApplyTO to) throws SerException {
        return staffMovementApplySer.planAudit(to);
    }
    @Override
    public StaffMovementApplyBO budgetAudit(StaffMovementApplyTO to) throws SerException {
        return staffMovementApplySer.budgetAudit(to);
    }
    @Override
    public StaffMovementApplyBO originalAudit(StaffMovementApplyTO to) throws SerException {
        return staffMovementApplySer.originalAudit(to);
    }
    @Override
    public StaffMovementApplyBO transferAudit(StaffMovementApplyTO to) throws SerException {
        return staffMovementApplySer.transferAudit(to);
    }
    @Override
    public StaffMovementApplyBO generalAudit(StaffMovementApplyTO to) throws SerException {
        return staffMovementApplySer.generalAudit(to);
    }
}