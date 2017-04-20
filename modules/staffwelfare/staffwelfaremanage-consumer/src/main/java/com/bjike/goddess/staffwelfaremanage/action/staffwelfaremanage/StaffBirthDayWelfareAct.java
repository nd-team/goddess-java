package com.bjike.goddess.staffwelfaremanage.action.staffwelfaremanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffwelfaremanage.api.StaffBirthDayWelfareAPI;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfaremanage.vo.StaffBirthDayWelfareVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 员工生日福利记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-07 10:49 ]
 * @Description: [ 员工生日福利记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffbirthdaywelfare")
public class StaffBirthDayWelfareAct {

    @Autowired
    private StaffBirthDayWelfareAPI staffBirthDayWelfareAPI;

    /**
     * 分页查询员工生日福利记录
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(StaffBirthDayWelfareDTO dto) throws ActException {
        try {
            List<StaffBirthDayWelfareVO> voList = BeanTransform.copyProperties(staffBirthDayWelfareAPI.pageList(dto), StaffBirthDayWelfareVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询生日祝福语答谢语
     *
     * @param userID 生日员工id
     * @version v1
     */
    @GetMapping("v1/birthdayDetail")
    public Result birthdayDetail(String userID) throws ActException {
        // TODO: 17-4-8  
        return ActResult.initialize("success");
       /* try {
            List<StaffBirthDayWelfareVO> voList = BeanTransform.copyProperties(staffBirthDayWelfareAPI.birthdayDetail(userID), StaffBirthDayWelfareVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }*/
    }

}