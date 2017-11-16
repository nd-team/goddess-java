package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.*;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.service.StaffRecordsSer;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsTO;
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
    public Long getTotal(StaffRecordsDTO dto) throws SerException {
        return staffRecordsSer.getTotal(dto);
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
    public Long count(StaffRecordsDTO dto) throws SerException {
        return staffRecordsSer.count(dto);
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

    @Override
    public CurrentMessageBO findCurrentMessage() throws SerException {
        return staffRecordsSer.findCurrentMessage();
    }

    @Override
    public void add(StaffRecordsTO to) throws SerException {
        staffRecordsSer.add(to);
    }

    @Override
    public void edit(StaffRecordsTO to) throws SerException {
        staffRecordsSer.edit(to);
    }

    @Override
    public StaffRecordsBO findEntity(String id) throws SerException {
        return staffRecordsSer.findEntity(id);
    }

    @Override
    public void delete(String id) throws SerException {
        staffRecordsSer.delete(id);
    }

    @Override
    public byte[] exportExcel(StaffRecordsDTO dto) throws SerException {
        return staffRecordsSer.exportExcel(dto);
    }

    @Override
    public void freeze(String id) throws SerException {
        staffRecordsSer.freeze(id);
    }

    @Override
    public void thaw(String id) throws SerException {
        staffRecordsSer.thaw(id);
    }

    @Override
    public List<StaffRecordsCollectBO> dayCollect(String day) throws SerException {
        return staffRecordsSer.dayCollect(day);
    }

    @Override
    public List<StaffRecordsCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return staffRecordsSer.weekCollect(year, month, week);
    }

    @Override
    public List<StaffRecordsCollectBO> monthCollect(String month) throws SerException {
        return staffRecordsSer.monthCollect(month);
    }

    @Override
    public List<StaffRecordsCollectBO> totalCollect() throws SerException {
        return staffRecordsSer.totalCollect();
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        return staffRecordsSer.figureShowDay(day);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return staffRecordsSer.figureShowWeek(year, month, week);
    }

    @Override
    public OptionBO figureShowMonth(String month) throws SerException {
        return staffRecordsSer.figureShowMonth(month);
    }

    @Override
    public OptionBO figureShowAll() throws SerException {
        return staffRecordsSer.figureShowAll();
    }

    @Override
    public StaffRecordsDataBO findDataByName(String name) throws SerException {
        return staffRecordsSer.findDataByName(name);
    }
}