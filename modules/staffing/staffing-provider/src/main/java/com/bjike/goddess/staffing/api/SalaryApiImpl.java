package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.SalaryBO;
import com.bjike.goddess.staffing.dto.SalaryDTO;
import com.bjike.goddess.staffing.service.SalarySer;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.to.SalaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 工资区间业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 01:50 ]
 * @Description: [ 工资区间业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("salaryApiImpl")
public class SalaryApiImpl implements SalaryAPI {
    @Autowired
    private SalarySer salarySer;

    @Override
    public List<SalaryBO> list(SalaryDTO dto) throws SerException {
        return salarySer.list(dto);
    }

    @Override
    public SalaryBO save(SalaryTO to) throws SerException {
        return salarySer.save(to);
    }

    @Override
    public void edit(SalaryTO to) throws SerException {
        salarySer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        salarySer.delete(id);
    }

    @Override
    public SalaryBO findByID(String id) throws SerException {
        return salarySer.findByID(id);
    }

    @Override
    public Long count(SalaryDTO dto) throws SerException {
        return salarySer.count(dto);
    }

    @Override
    public Set<String> sal() throws SerException {
        return salarySer.sal();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return salarySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salarySer.guidePermission(guidePermissionTO);
    }
}