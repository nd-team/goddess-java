package com.bjike.goddess.staffmove.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmove.bo.StaffMoveIntendCaseBO;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.service.StaffMoveIntendCaseSer;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveIntendCaseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人员调动意愿情况业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffMoveIntendCaseApiImpl")
public class StaffMoveIntendCaseApiImpl implements StaffMoveIntendCaseAPI {
    @Autowired
    private StaffMoveIntendCaseSer staffMoveIntendCaseSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return staffMoveIntendCaseSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffMoveIntendCaseSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(StaffMoveIntendCaseDTO dto) throws SerException {
        return staffMoveIntendCaseSer.count(dto);
    }

    @Override
    public StaffMoveIntendCaseBO getOne(String id) throws SerException {
        return staffMoveIntendCaseSer.getOne(id);
    }

    @Override
    public List<StaffMoveIntendCaseBO> list(StaffMoveIntendCaseDTO dto) throws SerException {
        return staffMoveIntendCaseSer.list(dto);
    }

    @Override
    public StaffMoveIntendCaseBO insert(StaffMoveIntendCaseTO to) throws SerException {
        return staffMoveIntendCaseSer.insert(to);
    }

    @Override
    public StaffMoveIntendCaseBO edit(StaffMoveIntendCaseTO to) throws SerException {
        return staffMoveIntendCaseSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        staffMoveIntendCaseSer.remove(id);
    }
    @Override
    public List<String> getName() throws SerException {
        return staffMoveIntendCaseSer.getName();
    }
    @Override
    public StaffMoveIntendCaseBO importExcel(List<StaffMoveIntendCaseTO> caseTOS) throws SerException {
        return staffMoveIntendCaseSer.importExcel(caseTOS);
    }

    @Override
    public byte[] exportExcel(StaffMoveIntendCaseDTO dto) throws SerException {
        return staffMoveIntendCaseSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return staffMoveIntendCaseSer.templateExport();
    }
}