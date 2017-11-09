package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.overtime.OverWorkAPI;
import com.bjike.goddess.attendance.dto.overtime.OverWorkRestDayDTO;
import com.bjike.goddess.attendance.vo.OverWorkRestDayVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 剩余加班天数
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 剩余加班天数 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("overworkrestday")
public class OverWorkRestDayAction {

    @Autowired
    private OverWorkAPI overWorkAPI;

    /**
     * 总条数
     *
     * @param overWorkRestDayDTO 剩余加班信息dto
     * @des 获取所有剩余加班信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OverWorkRestDayDTO overWorkRestDayDTO) throws ActException {
        try {
            Long count = overWorkAPI.countRestDay(overWorkRestDayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param overWorkRestDayDTO 剩余加班信息dto
     * @return class OverWorkRestDayVO
     * @des 获取所有剩余加班信息, 当前进去只能看见截至本月的加班数据，想要看其他月份剩余的加班天数就选一个日期(年月框)条件查询
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListOverWork(OverWorkRestDayDTO overWorkRestDayDTO, BindingResult bindingResult) throws ActException {
        try {
            List<OverWorkRestDayVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.listRestDay(overWorkRestDayDTO), OverWorkRestDayVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}