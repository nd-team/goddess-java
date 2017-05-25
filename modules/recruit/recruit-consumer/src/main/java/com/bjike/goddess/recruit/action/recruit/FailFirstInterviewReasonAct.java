package com.bjike.goddess.recruit.action.recruit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.api.FailFirstInterviewReasonAPI;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import com.bjike.goddess.recruit.vo.FailFirstInterviewReasonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("failFirstInterviewReason")
public class FailFirstInterviewReasonAct {

    @Autowired
    private FailFirstInterviewReasonAPI failFirstInterviewReasonAPI;

    /**
     * 根据id查询未应约初试原因
     *
     * @param id 未应约初试原因唯一标识
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/failFirstInterviewReason/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FailFirstInterviewReasonBO bo = failFirstInterviewReasonAPI.findById(id);
            FailFirstInterviewReasonVO vo = BeanTransform.copyProperties(bo, FailFirstInterviewReasonVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 未应约初试原因dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated FailFirstInterviewReasonDTO dto, BindingResult result) throws ActException {
        try {
            Long count = failFirstInterviewReasonAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 未应约初试原因传输对象
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FailFirstInterviewReasonDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FailFirstInterviewReasonBO> boList = failFirstInterviewReasonAPI.list(dto);
            List<FailFirstInterviewReasonVO> voList = BeanTransform.copyProperties(boList, FailFirstInterviewReasonVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加未应约初试原因
     *
     * @param to 未应约初试原因to信息
     * @return class FailFirstInterviewReasonVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) FailFirstInterviewReasonTO to, HttpServletRequest request) throws ActException {
        try {
            FailFirstInterviewReasonBO bo = failFirstInterviewReasonAPI.save(to);
            FailFirstInterviewReasonVO vo = BeanTransform.copyProperties(bo, FailFirstInterviewReasonVO.class, request);
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
    @LoginAuth
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
     * @param to 未应约初试原因to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FailFirstInterviewReasonTO to) throws ActException {
        try {
            failFirstInterviewReasonAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
