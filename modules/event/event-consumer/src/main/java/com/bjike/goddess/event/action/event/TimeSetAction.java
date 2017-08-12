package com.bjike.goddess.event.action.event;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.event.api.TimeSetAPI;
import com.bjike.goddess.event.bo.TimeSetBO;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.to.TimeSetTO;
import com.bjike.goddess.event.vo.TimeSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提醒间隔时间设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("timeset")
public class TimeSetAction {
    @Autowired
    private TimeSetAPI timeSetAPI;

    /**
     * 列表
     *
     * @param dto 提醒间隔时间设置数据传输
     * @return class TimeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TimeSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TimeSetBO> list = timeSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取一个提醒间隔时间设置
     *
     * @param dto dto
     * @return class TimeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/timeSet")
    public Result timeSet(@Validated(TimeSetDTO.ONE.class) TimeSetDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TimeSetBO bo = timeSetAPI.timeSet(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 提醒间隔时间设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(TimeSetTO.EDIT.class) TimeSetTO to, BindingResult result) throws ActException {
        try {
            timeSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/freeze")
    public Result freeze(@Validated(TimeSetDTO.ONE.class) TimeSetDTO dto, BindingResult result) throws ActException {
        try {
            timeSetAPI.freeze(dto);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/thaw")
    public Result thaw(@Validated(TimeSetDTO.ONE.class) TimeSetDTO dto, BindingResult result) throws ActException {
        try {
            timeSetAPI.thaw(dto);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}