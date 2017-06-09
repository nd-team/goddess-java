package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.TransferHandlerWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.TransferHandlerWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerWeekSumDTO;
import com.bjike.goddess.materialsummary.to.TransferHandlerWeekSumTO;
import com.bjike.goddess.materialsummary.vo.TransferHandlerWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调动经手人周汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transferhandlerweeksum")
public class TransferHandlerWeekSumAct {

    @Autowired
    private TransferHandlerWeekSumAPI transferHandlerWeekSumAPI;

    /**
     * 根据id查询调动经手人周汇总记录
     *
     * @param id 调动经手人周汇总记录唯一标识
     * @return class TransferHandlerWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transferhandlermonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerWeekSumBO bo = transferHandlerWeekSumAPI.findById(id);
            TransferHandlerWeekSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 调动经手人周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TransferHandlerWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferHandlerWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 调动经手人周汇总记录dto
     * @return class TransferHandlerWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TransferHandlerWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferHandlerWeekSumBO> boList = transferHandlerWeekSumAPI.list(dto);
            List<TransferHandlerWeekSumVO> voList = BeanTransform.copyProperties(boList, TransferHandlerWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调动经手人周汇总记录
     *
     * @param to 调动经手人周汇总记录to信息
     * @return class TransferHandlerWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TransferHandlerWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerWeekSumBO bo = transferHandlerWeekSumAPI.save(to);
            TransferHandlerWeekSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除调动经手人周汇总记录
     *
     * @param id 调动经手人周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transferHandlerWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调动经手人周汇总记录
     *
     * @param to 调动经手人周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TransferHandlerWeekSumTO to, BindingResult result) throws ActException {
        try {
            transferHandlerWeekSumAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * @param request
     * @return
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summary")
    public Result summary(HttpServletRequest request) throws ActException {
        try {
            List<TransferHandlerWeekSumBO> listBO = transferHandlerWeekSumAPI.summary();
            List<TransferHandlerWeekSumVO> listVO = BeanTransform.copyProperties(listBO, TransferHandlerWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}