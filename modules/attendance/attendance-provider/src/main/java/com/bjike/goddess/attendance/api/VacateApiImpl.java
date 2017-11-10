package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.bo.VacateCountBO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.dto.VacateDTO;
import com.bjike.goddess.attendance.service.VacateSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.attendance.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假管理业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("vacateApiImpl")
public class VacateApiImpl implements VacateAPI {
    @Autowired
    private VacateSer vacateSer;

    @Override
    public Double getTime(VacateTO to) throws SerException {
        return vacateSer.getTime(to);
    }

    @Override
    public List<VacateBO> findByCon(VacateConDTO vacateConDTO) throws SerException {
        return vacateSer.findByCon(vacateConDTO);
    }

    @Override
    public List<VacateBO> list(VacateDTO dto) throws SerException {
        return vacateSer.list(dto);
    }

    @Override
    public void save(VacateTO to) throws SerException {
        vacateSer.save(to);
    }

    @Override
    public void fill(VacateTO to) throws SerException {
        vacateSer.fill(to);
    }

    @Override
    public void delete(String id) throws SerException {
        vacateSer.delete(id);
    }

    @Override
    public VacateBO findByID(String id) throws SerException {
        return vacateSer.findByID(id);
    }

    @Override
    public Long count(VacateDTO dto) throws SerException {
        return vacateSer.count(dto);
    }

    @Override
    public List<VacateBO> auditList(VacateDTO dto) throws SerException {
        return vacateSer.auditList(dto);
    }

    @Override
    public Long auditListCount(VacateDTO dto) throws SerException {
        return vacateSer.auditListCount(dto);
    }

    @Override
    public void audit(VacateTO to) throws SerException {
        vacateSer.audit(to);
    }

    @Override
    public VacateCountBO vacateCount(VacateDTO dto) throws SerException {
        return vacateSer.vacateCount(dto);
    }

    @Override
    public Double currentVacateTime(String start, String end, String date) throws SerException {
        return vacateSer.currentVacateTime(start, end, date);
    }

    @Override
    public List<VacateBO> listPhone(VacateDTO dto) throws SerException {
        return vacateSer.listPhone(dto);
    }

    @Override
    public VacateBO findByIDPhone(String id) throws SerException {
        return vacateSer.findByIDPhone(id);
    }

    @Override
    public List<VacateBO> auditListPhone(VacateDTO dto) throws SerException {
        return vacateSer.auditListPhone(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return vacateSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return vacateSer.guidePermission(guidePermissionTO);
    }
}