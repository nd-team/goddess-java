package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.PerformanceScoreAPI;
import com.bjike.goddess.regularization.bo.PerformanceScoreBO;
import com.bjike.goddess.regularization.dto.PerformanceScoreDTO;
import com.bjike.goddess.regularization.to.PerformanceScoreTO;
import com.bjike.goddess.regularization.vo.PerformanceScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 工作表现评分
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 04:55 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("performancescore")
public class PerformanceScoreAct {

    @Autowired
    private PerformanceScoreAPI performanceScoreAPI;

    /**
     * 分页查询工作表现评分
     *
     * @param dto 工作表现评分dto
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PerformanceScoreDTO dto) throws ActException {
        try {
            List<PerformanceScoreBO> boList = performanceScoreAPI.list(dto);
            List<PerformanceScoreVO> voList = BeanTransform.copyProperties(boList, PerformanceScoreVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作表现评分
     *
     * @param to 工作表现评分to
     * @return class PerformanceScoreVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) PerformanceScoreTO to, BindingResult result) throws ActException {
        try {
            PerformanceScoreBO bo = performanceScoreAPI.save(to);
            PerformanceScoreVO vo = BeanTransform.copyProperties(bo, PerformanceScoreVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除工作表现评分
     *
     * @param id 工作表现评分唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            performanceScoreAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作表现评分
     *
     * @param to 工作表现评分to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(PerformanceScoreTO to) throws ActException {
        try {
            performanceScoreAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}