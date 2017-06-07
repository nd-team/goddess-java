package com.bjike.goddess.materialsummary.action.materialsummary;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialsummary.api.TransferHandlerYearSumAPI;
import com.bjike.goddess.materialsummary.bo.TransferHandlerYearSumBO;
import com.bjike.goddess.materialsummary.dto.TransferHandlerYearSumDTO;
import com.bjike.goddess.materialsummary.to.TransferHandlerYearSumTO;
import com.bjike.goddess.materialsummary.vo.TransferHandlerYearSumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 调动经手人年汇总记录
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:56 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transferhandleryearsum")
public class TransferHandlerYearSumAct {

    @Autowired
    private TransferHandlerYearSumAPI transferHandlerYearSumAPI;

    /**
     * 根据id查询调动经手人年汇总记录
     *
     * @param id 调动经手人年汇总记录唯一标识
     * @return class TransferHandlerYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/transferhandlermonthsum/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerYearSumBO bo = transferHandlerYearSumAPI.findById(id);
            TransferHandlerYearSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 调动经手人年汇总记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated TransferHandlerYearSumDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferHandlerYearSumAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 调动经手人年汇总记录dto
     * @return class TransferHandlerYearSumVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated TransferHandlerYearSumDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferHandlerYearSumBO> boList = transferHandlerYearSumAPI.list(dto);
            List<TransferHandlerYearSumVO> voList = BeanTransform.copyProperties(boList, TransferHandlerYearSumVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加调动经手人年汇总记录
     *
     * @param to 调动经手人年汇总记录to信息
     * @return class TransferHandlerYearSumVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) TransferHandlerYearSumTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TransferHandlerYearSumBO bo = transferHandlerYearSumAPI.save(to);
            TransferHandlerYearSumVO vo = BeanTransform.copyProperties(bo, TransferHandlerYearSumVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除调动经手人年汇总记录
     *
     * @param id 调动经手人年汇总记录唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            transferHandlerYearSumAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑调动经手人年汇总记录
     *
     * @param to 调动经手人年汇总记录to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) TransferHandlerYearSumTO to, BindingResult result) throws ActException {
        try {
            transferHandlerYearSumAPI.update(to);
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
            List<TransferHandlerYearSumBO> listBO = transferHandlerYearSumAPI.summary();
            List<TransferHandlerYearSumVO> listVO = BeanTransform.copyProperties(listBO, TransferHandlerYearSumVO.class, request);
            return ActResult.initialize(listVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}