package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.HolidayWorkPlanAPI;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.vo.HolidayWorkPlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 节假日工作安排
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("holidayworkplan")
public class HolidayWorkPlanAction {

    @Autowired
    private HolidayWorkPlanAPI holidayWorkPlanAPI;

    /**
     *  节假日工作安排列表总条数
     *
     * @param holidayWorkPlanDTO  节假日工作安排dto
     * @des 获取所有节假日工作安排信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(HolidayWorkPlanDTO.TESTFindDetail.class) HolidayWorkPlanDTO holidayWorkPlanDTO) throws ActException {
        try {
            Long count = holidayWorkPlanAPI.countHolidayWorkPlan(holidayWorkPlanDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    /**
     * 查看节假日工作安排
     *
     * @param holidayWorkPlanDTO 节假日工作安排dto
     * @des 查看各节假日工作安排
     * @return  class HolidayWorkPlanVO
     * @version v1
     */
    @GetMapping("v1/getHoliDetail")
    public Result getHolidayWorkPlanDetail (@Validated(HolidayWorkPlanDTO.TESTFindDetail.class) HolidayWorkPlanDTO holidayWorkPlanDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<HolidayWorkPlanVO> holidayWorkPlanVOS = BeanTransform.copyProperties(
                    holidayWorkPlanAPI.listHolidayWorkPlan(holidayWorkPlanDTO), HolidayWorkPlanVO.class, request);
            return ActResult.initialize(holidayWorkPlanVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}