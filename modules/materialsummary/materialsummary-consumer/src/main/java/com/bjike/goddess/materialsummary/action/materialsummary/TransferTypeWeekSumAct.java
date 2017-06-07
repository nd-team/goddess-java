package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.TransferTypeWeekSumAPI;
import com.bjike.goddess.materialsummary.bo.TransferTypeWeekSumBO;
import com.bjike.goddess.materialsummary.dto.TransferTypeWeekSumDTO;
import com.bjike.goddess.materialsummary.to.TransferTypeWeekSumTO;
import com.bjike.goddess.materialsummary.vo.TransferTypeWeekSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调动类型周汇总
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:43 ]
 * @Description: [ 调动类型周汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transfertypeweeksum")
public class TransferTypeWeekSumAct {

    @Autowired
    private TransferTypeWeekSumAPI transferTypeWeekSumAPI;

    /**
     * 根据id查询调动类型周汇总记录
     *
     * @param id 调动类型周汇总记录唯一标识
     * @return class TransferTypeWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transfertypeweeksum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferTypeWeekSumBO bo = transferTypeWeekSumAPI.findById(id);
            TransferTypeWeekSumVO vo = BeanTransform.copyProperties(bo, TransferTypeWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 调动类型周汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TransferTypeWeekSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferTypeWeekSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 调动类型周汇总记录dto
     * @return class TransferTypeWeekSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TransferTypeWeekSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferTypeWeekSumBO> boList = transferTypeWeekSumAPI.list(dto);
            List<TransferTypeWeekSumVO> voList = BeanTransform.copyProperties(boList, TransferTypeWeekSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调动类型周汇总记录
     *
     * @param to 调动类型周汇总记录to信息
     * @return class TransferTypeWeekSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TransferTypeWeekSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TransferTypeWeekSumBO bo = transferTypeWeekSumAPI.save(to);
            TransferTypeWeekSumVO vo = BeanTransform.copyProperties(bo, TransferTypeWeekSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除调动类型周汇总记录
     *
     * @param id 调动类型周汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transferTypeWeekSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调动类型周汇总记录
     *
     * @param to 调动类型周汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TransferTypeWeekSumTO to, BindingResult result) throws ActException {
        try {
            transferTypeWeekSumAPI.update(to);
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
            List<TransferTypeWeekSumBO> listBO = transferTypeWeekSumAPI.summary();
            List<TransferTypeWeekSumVO> listVO = BeanTransform.copyProperties(listBO, TransferTypeWeekSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}