package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.service.StaffBirthDaySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haikuang on 17-8-17.
 */
@Service("staffBirthDayApiImpl")
public class StaffBirthDayApiImpl implements StaffBirthDayAPI{

    @Autowired
    private StaffBirthDaySer staffBirthDaySer;
    @Override
    public List<StaffBirthDayBO> findBirthDay(StaffBirthDayDTO dto) throws SerException {
        return staffBirthDaySer.findBirthDay(dto);
    }

    @Override
    public Long count(StaffBirthDayDTO dto) throws SerException {
        return staffBirthDaySer.count(dto);
    }
}
