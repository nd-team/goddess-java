package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.VacateAPI;
import com.bjike.goddess.attendance.to.VacateTO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vacate")
public class VacateAction {
    @Autowired
    private VacateAPI vacateAPI;

    /**
     * 获取请假天数
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/get/time")
    public Result getTime(VacateTO to) throws ActException {
        try {
            return ActResult.initialize(vacateAPI.getTime(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}