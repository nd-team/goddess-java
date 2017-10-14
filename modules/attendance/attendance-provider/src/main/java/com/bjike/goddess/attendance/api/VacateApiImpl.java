package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateBO;
import com.bjike.goddess.attendance.dto.VacateConDTO;
import com.bjike.goddess.attendance.service.VacateSer;
import com.bjike.goddess.attendance.to.VacateTO;
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
}