package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.TransferHandlerMonthSumAPI;
import com.bjike.goddess.materialsummary.bo.TransferHandlerMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerMonthSumDTO;
import com.bjike.goddess.materialsummary.to.TransferHandlerMonthSumTO;
import com.bjike.goddess.materialsummary.vo.TransferHandlerMonthSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调动经手人月汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:55 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transferhandlermonthsum")
public class TransferHandlerMonthSumAct {

    @Autowired
    private TransferHandlerMonthSumAPI transferHandlerMonthSumAPI;

    /**
     * 根据id查询调动经手人月汇总记录
     *
     * @param id 调动经手人月汇总记录唯一标识
     * @return class TransferHandlerMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transferhandlermonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerMonthSumBO bo = transferHandlerMonthSumAPI.findById(id);
            TransferHandlerMonthSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 调动经手人月汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TransferHandlerMonthSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferHandlerMonthSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 调动经手人月汇总记录dto
     * @return class TransferHandlerMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TransferHandlerMonthSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferHandlerMonthSumBO> boList = transferHandlerMonthSumAPI.list(dto);
            List<TransferHandlerMonthSumVO> voList = BeanTransform.copyProperties(boList, TransferHandlerMonthSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调动经手人月汇总记录
     *
     * @param to 调动经手人月汇总记录to信息
     * @return class TransferHandlerMonthSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TransferHandlerMonthSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerMonthSumBO bo = transferHandlerMonthSumAPI.save(to);
            TransferHandlerMonthSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除调动经手人月汇总记录
     *
     * @param id 调动经手人月汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transferHandlerMonthSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调动经手人月汇总记录
     *
     * @param to 调动经手人月汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TransferHandlerMonthSumTO to, BindingResult result) throws ActException {
        try {
            transferHandlerMonthSumAPI.update(to);
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
            List<TransferHandlerMonthSumBO> listBO = transferHandlerMonthSumAPI.summary();
            List<TransferHandlerMonthSumVO> listVO = BeanTransform.copyProperties(listBO, TransferHandlerMonthSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}