package com.bjike.goddess.staffwelfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayWelfareBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.service.StaffBirthDayWelfareSer;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工生日福利记录业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffBirthDayWelfareApiImpl")
public class StaffBirthDayWelfareApiImpl implements StaffBirthDayWelfareAPI {


    @Autowired
    private StaffBirthDayWelfareSer staffBirthDayWelfareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return staffBirthDayWelfareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffBirthDayWelfareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<StaffBirthDayWelfareBO> pageList(StaffBirthDayWelfareDTO dto) throws SerException {
        return staffBirthDayWelfareSer.pageList(dto);
    }
}