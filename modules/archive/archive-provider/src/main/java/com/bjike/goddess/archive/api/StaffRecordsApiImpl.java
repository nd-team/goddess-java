package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.PerBO;
import com.bjike.goddess.archive.bo.StaffNameBO;
import com.bjike.goddess.archive.bo.StaffRecords1BO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.service.StaffRecordsSer;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工档案业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffRecordsApiImpl")
public class StaffRecordsApiImpl implements StaffRecordsAPI {

    @Autowired
    private StaffRecordsSer staffRecordsSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return staffRecordsSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffRecordsSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void upload(List<StaffRecordsExcelTO> toList) throws SerException {
        staffRecordsSer.upload(toList);
    }

    @Override
    public StaffRecordsBO findByName(String username) throws SerException {
        return staffRecordsSer.findByName(username);
    }

    @Override
    public StaffRecordsBO findByNumber(String serialNumber) throws SerException {
        return staffRecordsSer.findByNumber(serialNumber);
    }

    @Override
    public List<StaffRecordsBO> maps(StaffRecordsDTO dto) throws SerException {
        return staffRecordsSer.maps(dto);
    }

    @Override
    public StaffRecordsBO getById(String id) throws SerException {
        return staffRecordsSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return staffRecordsSer.getTotal();
    }

    @Override
    public List<StaffNameBO> getName() throws SerException {
        return staffRecordsSer.getName();
    }

    @Override
    public List<PerBO> getPerBO(String name) throws SerException {
        return staffRecordsSer.getPerBO(name);
    }

    @Override
    public List<StaffRecordsBO> listEmployee() throws SerException {
        return staffRecordsSer.listEmployee();
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return staffRecordsSer.templateExcel();
    }

    @Override
    public void dimissionUpload(List<StaffRecords1ExcelTO> toList) throws SerException {
        staffRecordsSer.dimissionUpload(toList);
    }

    @Override
    public List<StaffRecords1BO> dimissionMaps(StaffRecordsDTO dto) throws SerException {
        return staffRecordsSer.dimissionMaps(dto);
    }

    @Override
    public Long count() throws SerException {
        return staffRecordsSer.count();
    }

    @Override
    public byte[] templateDimissionExcel() throws SerException {
        return staffRecordsSer.templateDimissionExcel();
    }

    @Override
    public List<StaffRecordsBO> findByMonth(Integer month) throws SerException {
        return staffRecordsSer.findByMonth(month);
    }

    @Override
    public StaffRecordsBO getByName(String name) throws SerException {
        return staffRecordsSer.getByName(name);
    }
}