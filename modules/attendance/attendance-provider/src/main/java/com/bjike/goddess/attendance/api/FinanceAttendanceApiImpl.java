package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.FinanceAttendanceBO;
import com.bjike.goddess.attendance.bo.FinanceCountBO;
import com.bjike.goddess.attendance.bo.FinanceCountFieldBO;
import com.bjike.goddess.attendance.dto.FinanceAttendanceDTO;
import com.bjike.goddess.attendance.entity.PageUtils;
import com.bjike.goddess.attendance.service.FinanceAttendanceSer;
import com.bjike.goddess.attendance.to.FinanceAttendanceTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务出勤表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 04:09 ]
 * @Description: [ 财务出勤表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("financeAttendanceApiImpl")
public class FinanceAttendanceApiImpl implements FinanceAttendanceAPI {
    @Autowired
    private FinanceAttendanceSer financeAttendanceSer;

    @Override
    public List<FinanceAttendanceBO> list(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.list(dto);
    }

    @Override
    public void apply(FinanceAttendanceTO to) throws SerException {
        financeAttendanceSer.apply(to);
    }

    @Override
    public List<FinanceAttendanceBO> aduitList(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.aduitList(dto);
    }

    @Override
    public Long aduitListNum(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.aduitListNum(dto);
    }

    @Override
    public FinanceAttendanceBO one(String id) throws SerException {
        return financeAttendanceSer.one(id);
    }

    @Override
    public void audit(FinanceAttendanceTO to) throws SerException {
        financeAttendanceSer.audit(to);
    }

    @Override
    public List<FinanceCountFieldBO> fields(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.fields(dto);
    }

    @Override
    public List<FinanceCountBO> financeCount(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.financeCount(dto);
    }

    @Override
    public Double vacateDay(String name, String date) throws SerException {
        return financeAttendanceSer.vacateDay(name, date);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return financeAttendanceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return financeAttendanceSer.guidePermission(guidePermissionTO);
    }

    @Override
    public byte[] excelExport(FinanceAttendanceDTO dto) throws SerException {
        return financeAttendanceSer.excelExport(dto).toByteArray();
    }

    @Override
    public PageUtils findAll(String pageNum, String pageSize, String name) throws SerException {
        return financeAttendanceSer.findAll(pageNum,pageSize,name);
    }

    @Override
    public void save(FinanceAttendanceDTO dto) throws SerException {
        financeAttendanceSer.save(dto);
    }

    @Override
    public void delete(String[] ids) throws SerException {
        financeAttendanceSer.delete(ids);
    }

    @Override
    public FinanceAttendanceBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(financeAttendanceSer.findById(id),FinanceAttendanceBO.class);
    }

    @Override
    public void Update(FinanceAttendanceDTO dto) throws SerException {
        financeAttendanceSer.Update(dto);
    }

}