package com.bjike.goddess.staffwelfare.action.staffwelfare;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfare.api.StaffBirthDayAPI;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.to.GuidePermissionTO;
import com.bjike.goddess.staffwelfare.vo.StaffBirthDayVO;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.dto.UserDetailDTO;
import com.bjike.goddess.user.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工生日信息
 *
 * @Author: [Jason]
 * @Date: [17-4-5 上午10:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffbirthday")
public class StaffBirthdayAct {

    @Autowired
    private UserDetailAPI userDetailAPI;

    @Autowired
    private StaffBirthDayAPI staffBirthDayAPI;



    /**
     * 查询员工生日信息
     *
     * @param dto 分页信息
     * @return class StaffBirthDayVO
     * @version v1
     */
    @GetMapping("v1/findStaffBirthInfo")
    public Result findStaffBirthInfo(StaffBirthDayDTO dto) throws ActException {
        try {
            List<StaffBirthDayBO> boList = staffBirthDayAPI.findBirthDay(dto);
            List<StaffBirthDayVO> voList = BeanTransform.copyProperties(boList,StaffBirthDayVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表总条数
     * @param dto 条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffBirthDayDTO dto) throws ActException{
        try {
            Long count = staffBirthDayAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

}
