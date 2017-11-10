package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmove.bo.OptionBO;
import com.bjike.goddess.staffmove.bo.StaffMoveCollectBO;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementABO;
import com.bjike.goddess.staffmove.bo.StaffMoveImplementBBO;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementADTO;
import com.bjike.goddess.staffmove.service.StaffMoveImplementASer;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementATO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementBTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人员调动实施业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffMoveImplementAApiImpl")
public class StaffMoveImplementAApiImpl implements StaffMoveImplementAAPI {
    @Autowired
    private StaffMoveImplementASer staffMoveImplementASer;

    @Override
    public Boolean sonPermission() throws SerException {
        return staffMoveImplementASer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffMoveImplementASer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(StaffMoveImplementADTO adto) throws SerException {
        return staffMoveImplementASer.count(adto);
    }


    @Override
    public StaffMoveImplementBBO getOneB(String id) throws SerException {
        return staffMoveImplementASer.getOneB(id);
    }

    @Override
    public StaffMoveImplementABO getOneA(String id) throws SerException {
        return staffMoveImplementASer.getOneA(id);
    }

    @Override
    public List<StaffMoveImplementABO> list(StaffMoveImplementADTO dto) throws SerException {
        return staffMoveImplementASer.list(dto);
    }

    @Override
    public StaffMoveImplementABO insert(StaffMoveImplementATO to) throws SerException {
        return staffMoveImplementASer.insert(to);
    }

    @Override
    public void edit(StaffMoveImplementATO to) throws SerException {
        staffMoveImplementASer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        staffMoveImplementASer.remove(id);
    }

    @Override
    public void staffMove(StaffMoveImplementATO to) throws SerException {
        staffMoveImplementASer.staffMove(to);
    }

    @Override
    public void originalAudit(StaffMoveImplementBTO to) throws SerException {
        staffMoveImplementASer.originalAudit(to);
    }

    @Override
    public void transferAudit(StaffMoveImplementBTO to) throws SerException {
        staffMoveImplementASer.transferAudit(to);
    }

    @Override
    public void generalAudit(StaffMoveImplementBTO to) throws SerException {
        staffMoveImplementASer.generalAudit(to);
    }

    @Override
    public void solve(StaffMoveImplementBTO to) throws SerException {
        staffMoveImplementASer.solve(to);
    }

    @Override
    public List<StaffMoveCollectBO> weekStaff(Integer year, Integer month, Integer week) throws SerException {
        return staffMoveImplementASer.weekStaff(year, month, week);
    }

    @Override
    public List<StaffMoveCollectBO> monthStaff(Integer year, Integer month) throws SerException {
        return staffMoveImplementASer.monthStaff(year, month);
    }

    @Override
    public List<StaffMoveCollectBO> totalStaff(String time) throws SerException {
        return staffMoveImplementASer.totalStaff(time);
    }

    @Override
    public OptionBO weekStaffFigure(Integer year, Integer month, Integer week) throws SerException {
        return staffMoveImplementASer.weekStaffFigure(year, month, week);
    }

    @Override
    public OptionBO monthStaffFigure(Integer year, Integer month) throws SerException {
        return staffMoveImplementASer.monthStaffFigure(year, month);
    }

    @Override
    public OptionBO totalStaffFigure(String time) throws SerException {
        return staffMoveImplementASer.totalStaffFigure(time);
    }
}