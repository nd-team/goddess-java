package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.TimeCriteriaSetAPI;
import com.bjike.goddess.regularization.bo.TimeCriteriaSetBO;
import com.bjike.goddess.regularization.dto.TimeCriteriaSetDTO;
import com.bjike.goddess.regularization.entity.TimeCriteriaSet;
import com.bjike.goddess.regularization.to.TimeCriteriaSetTO;
import com.bjike.goddess.regularization.vo.TimeCriteriaSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 时间条件设置
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("timecriteriaset")
public class TimeCriteriaSetAct {

    @Autowired
    private TimeCriteriaSetAPI timeCriteriaSetAPI;

    /**
     * 分页查询时间条件设置
     *
     * @param dto 时间条件设置dto
     * @return class TimeCriteriaSetVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TimeCriteriaSetDTO dto) throws ActException {
        try {
            List<TimeCriteriaSetBO> boList = timeCriteriaSetAPI.list(dto);
            List<TimeCriteriaSetVO> voList = BeanTransform.copyProperties(boList, TimeCriteriaSetVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加时间条件设置
     *
     * @param to 时间条件设置to
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) TimeCriteriaSetTO to, BindingResult result) throws ActException {
        try {
            TimeCriteriaSetBO bo = timeCriteriaSetAPI.save(to);
            TimeCriteriaSetVO vo = BeanTransform.copyProperties(bo, TimeCriteriaSetVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除时间条件设置
     *
     * @param id 时间条件设置唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            timeCriteriaSetAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑时间条件设置
     *
     * @param to 时间条件设置to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(TimeCriteriaSetTO to) throws ActException {
        try {
            timeCriteriaSetAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}