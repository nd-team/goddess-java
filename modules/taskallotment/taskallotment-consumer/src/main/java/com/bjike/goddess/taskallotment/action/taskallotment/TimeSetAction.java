package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.api.TimeSetAPI;
import com.bjike.goddess.taskallotment.bo.TimeSetBO;
import com.bjike.goddess.taskallotment.dto.TimeSetDTO;
import com.bjike.goddess.taskallotment.to.TimeSetTO;
import com.bjike.goddess.taskallotment.vo.TimeSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 标准工时设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置 ]
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
     * @param dto 标准工时设置数据传输
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
     * 添加
     *
     * @param to 标准工时设置传输对象
     * @return class TimeSetVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) TimeSetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TimeSetBO bo = timeSetAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 标准工时设置id
     * @return class TimeSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/timeSet/{id}")
    public Result TimeSet(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TimeSetBO bo = timeSetAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TimeSetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 标准工时设置传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TimeSetTO to, BindingResult result) throws ActException {
        try {
            timeSetAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 标准工时设置id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            timeSetAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 标准工时设置数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TimeSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(timeSetAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}