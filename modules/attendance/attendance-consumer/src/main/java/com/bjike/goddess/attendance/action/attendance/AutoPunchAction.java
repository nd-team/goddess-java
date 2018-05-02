package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.AutoPunchAPI;
import com.bjike.goddess.attendance.bo.AutoPunchBO;
import com.bjike.goddess.attendance.to.AutoPunchTO;
import com.bjike.goddess.attendance.vo.AutoPunchVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 自动打卡
 *
 * @Author: [chenjunhao]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("autopunch")
public class AutoPunchAction {
    @Autowired
    private AutoPunchAPI autoPunchAPI;

    /**
     * 启用自动打卡
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/start")
    public Result start(@Validated(ADD.class) AutoPunchTO to, BindingResult result) throws ActException {
        try {
            autoPunchAPI.start(to);
            return new ActResult("启用成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭自动打卡
     *
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/close")
    public Result close() throws ActException {
        try {
            autoPunchAPI.close();
            return new ActResult("关闭成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看当前用户的自动打卡状态
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/current/stauts")
    public Result currentStauts() throws ActException {
        try {
            return ActResult.initialize(autoPunchAPI.currentStauts());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前用户设置的上下班时间
     *
     * @return class AutoPunchVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/get")
    public Result get(HttpServletRequest request) throws ActException {
        try {
            AutoPunchBO bo = autoPunchAPI.get();
            return ActResult.initialize(BeanTransform.copyProperties(bo, AutoPunchVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}