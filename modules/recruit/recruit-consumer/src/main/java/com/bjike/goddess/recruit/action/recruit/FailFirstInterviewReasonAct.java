package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FailFirstInterviewReasonAPI;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.vo.FailFirstInterviewReasonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 10:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("recruit/failFirstInterviewReason")
public class FailFirstInterviewReasonAct {

    @Autowired
    private FailFirstInterviewReasonAPI failFirstInterviewReasonAPI;

    /**
     * 获取列表
     *
     * @param dto 未应约初试原因传输对象
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FailFirstInterviewReasonDTO dto) throws ActException {
        try {
            List<FailFirstInterviewReasonBO> boList = failFirstInterviewReasonAPI.list(dto);
            List<FailFirstInterviewReasonVO> voList = BeanTransform.copyProperties(boList, FailFirstInterviewReasonVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未应约初试原因
     *
     * @param failFirstInterviewReasonTO 未应约初试原因to信息
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws ActException {
        try {
            FailFirstInterviewReasonBO bo = failFirstInterviewReasonAPI.save(failFirstInterviewReasonTO);
            FailFirstInterviewReasonVO vo = BeanTransform.copyProperties(bo, FailFirstInterviewReasonVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            failFirstInterviewReasonAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑未应约初试原因
     *
     * @param failFirstInterviewReasonTO 未应约初试原因to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws ActException {
        try {
            failFirstInterviewReasonAPI.update(failFirstInterviewReasonTO);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
