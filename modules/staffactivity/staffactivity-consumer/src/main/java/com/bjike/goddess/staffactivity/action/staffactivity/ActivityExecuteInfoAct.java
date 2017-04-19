package com.bjike.goddess.staffactivity.action.staffactivity;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffactivity.api.ActivityExecuteInfoAPI;
import com.bjike.goddess.staffactivity.bo.ActivityExecuteInfoBO;
import com.bjike.goddess.staffactivity.dto.ActivityExecuteInfoDTO;
import com.bjike.goddess.staffactivity.to.ActivityExecuteInfoTO;
import com.bjike.goddess.staffactivity.vo.ActivityExecuteInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动执行信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:09 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("activityexecuteinfo")
public class ActivityExecuteInfoAct {

    @Autowired
    private ActivityExecuteInfoAPI activityExecuteInfoAPI;

    /**
     * 分页查询活动执行信息
     *
     * @param dto 活动执行信息dto
     * @return class ActivityExecuteInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ActivityExecuteInfoDTO dto) throws ActException {
        try {
            List<ActivityExecuteInfoBO> boList = activityExecuteInfoAPI.list(dto);
            List<ActivityExecuteInfoVO> voList = BeanTransform.copyProperties(boList, ActivityExecuteInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加活动执行信息
     *
     * @param to 活动执行信息to
     * @return class ActivityExecuteInfoVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ActivityExecuteInfoTO to) throws ActException {
        try {
            ActivityExecuteInfoBO bo = activityExecuteInfoAPI.save(to);
            ActivityExecuteInfoVO vo = BeanTransform.copyProperties(bo, ActivityExecuteInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除活动执行信息
     *
     * @param id 活动执行信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            activityExecuteInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑活动执行信息
     *
     * @param to 活动执行信息to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(ActivityExecuteInfoTO to) throws ActException {
        try {
            activityExecuteInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}