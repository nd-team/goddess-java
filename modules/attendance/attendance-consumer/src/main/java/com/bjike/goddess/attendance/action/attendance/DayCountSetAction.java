package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.DayCountSetAPI;
import com.bjike.goddess.attendance.bo.DayCountSetBO;
import com.bjike.goddess.attendance.dto.DayCountSetDTO;
import com.bjike.goddess.attendance.to.DayCountSetTO;
import com.bjike.goddess.attendance.vo.DayCountSetVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 日报汇总设置
 *
 * @Author: [chenjunhao]
 * @Date: [ 2017-11-03 04:20 ]
 * @Description: [ 日报汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("daycountset")
public class DayCountSetAction {
    @Autowired
    private DayCountSetAPI dayCountSetAPI;

    /**
     * 列表
     *
     * @param dto 日报汇总设置数据传输
     * @return class DayCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DayCountSetDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DayCountSetBO> list = dayCountSetAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DayCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 日报汇总设置传输对象
     * @return class DayCountSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DayCountSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DayCountSetBO bo = dayCountSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DayCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 日报汇总设置id
     * @return class DayCountSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/day/count/set/{id}")
    public Result DayCountSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DayCountSetBO bo = dayCountSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DayCountSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 日报汇总设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DayCountSetTO to, BindingResult result) throws ActException {
        try {
            dayCountSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 日报汇总设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            dayCountSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 日报汇总设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DayCountSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(dayCountSetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 定时检测发送
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/send")
    public Result send() throws ActException {
        try {
            dayCountSetAPI.send();
            return new ActResult("aa");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}