package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.TransferTypeMonthSumAPI;
import com.bjike.goddess.materialsummary.bo.TransferTypeMonthSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeMonthSumDTO;
import com.bjike.goddess.materialsummary.to.TransferTypeMonthSumTO;
import com.bjike.goddess.materialsummary.vo.TransferTypeMonthSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调动类型月汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型月汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transfertypemonthsum")
public class TransferTypeMonthSumAct {

    @Autowired
    private TransferTypeMonthSumAPI transferTypeMonthSumAPI;

    /**
     * 根据id查询调动类型日汇总记录
     *
     * @param id 调动类型日汇总记录唯一标识
     * @return class TransferTypeMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transfertypemonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferTypeMonthSumBO bo = transferTypeMonthSumAPI.findById(id);
            TransferTypeMonthSumVO vo = BeanTransform.copyProperties(bo, TransferTypeMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 调动类型日汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TransferTypeMonthSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferTypeMonthSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 调动类型日汇总记录dto
     * @return class TransferTypeMonthSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TransferTypeMonthSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferTypeMonthSumBO> boList = transferTypeMonthSumAPI.list(dto);
            List<TransferTypeMonthSumVO> voList = BeanTransform.copyProperties(boList, TransferTypeMonthSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调动类型日汇总记录
     *
     * @param to 调动类型日汇总记录to信息
     * @return class TransferTypeMonthSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TransferTypeMonthSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TransferTypeMonthSumBO bo = transferTypeMonthSumAPI.save(to);
            TransferTypeMonthSumVO vo = BeanTransform.copyProperties(bo, TransferTypeMonthSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除调动类型日汇总记录
     *
     * @param id 调动类型日汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transferTypeMonthSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调动类型日汇总记录
     *
     * @param to 调动类型日汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TransferTypeMonthSumTO to, BindingResult result) throws ActException {
        try {
            transferTypeMonthSumAPI.update(to);
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
            List<TransferTypeMonthSumBO> listBO = transferTypeMonthSumAPI.summary();
            List<TransferTypeMonthSumVO> listVO = BeanTransform.copyProperties(listBO, TransferTypeMonthSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}