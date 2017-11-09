package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateSetBO;
import com.bjike.goddess.attendance.dto.VacateSetDTO;
import com.bjike.goddess.attendance.service.VacateSetSer;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.to.VacateSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("vacateSetApiImpl")
public class VacateSetApiImpl implements VacateSetAPI {
    @Autowired
    private VacateSetSer vacateSetSer;

    @Override
    public List<VacateSetBO> list(VacateSetDTO dto) throws SerException {
        return vacateSetSer.list(dto);
    }

    @Override
    public void save(VacateSetTO to) throws SerException {
        vacateSetSer.save(to);
    }

    @Override
    public void edit(VacateSetTO to) throws SerException {
        vacateSetSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        vacateSetSer.delete(id);
    }

    @Override
    public VacateSetBO findByID(String id) throws SerException {
        return vacateSetSer.findByID(id);
    }

    @Override
    public Long count(VacateSetDTO dto) throws SerException {
        return vacateSetSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return vacateSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return vacateSetSer.guidePermission(guidePermissionTO);
    }
}