package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.MarketServeApplyAPI;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 市场招待申请
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:37:08.048 ]
 * @Description: [ 市场招待申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketserveapply")
public class MarketServeApplyAct {

    @Autowired
    private MarketServeApplyAPI marketServeApplyAPI;

    /**
     * 根据id查询市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @return class MarketServeApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/marketserveapply/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MarketServeApplyBO bo = marketServeApplyAPI.findById(id);
            MarketServeApplyVO vo = BeanTransform.copyProperties(bo, MarketServeApplyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 市场招待申请dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MarketServeApplyDTO dto, BindingResult result) throws ActException {
        try {
            Long count = marketServeApplyAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 市场招待申请dto
     * @return class MarketServeApplyVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MarketServeApplyDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<MarketServeApplyBO> boList = marketServeApplyAPI.list(dto);
            List<MarketServeApplyVO> voList = BeanTransform.copyProperties(boList, MarketServeApplyVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加市场招待申请
     *
     * @param to 市场招待申请to信息
     * @return class MarketServeApplyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MarketServeApplyTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MarketServeApplyBO bo = marketServeApplyAPI.save(to);
            MarketServeApplyVO vo = BeanTransform.copyProperties(bo, MarketServeApplyVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除市场招待申请
     *
     * @param id 市场招待申请唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            marketServeApplyAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待申请
     *
     * @param to 市场招待申请to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MarketServeApplyTO to, BindingResult result) throws ActException {
        try {
            marketServeApplyAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部资金模块意见
     *
     * @param to 市场招待申请to信息
     * @throws ActException
     * @version v1
     */
    /**
     * 运营商务部资金模块意见
     *
     * @param id                市场招待申请唯一标识
     * @param fundModuleOpinion 运营商务部资金模块意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/fundModuleOpinion/{id}")
    public Result fundModuleOpinion(@PathVariable String id, @RequestParam(value = "fundModuleOpinion") String fundModuleOpinion) throws ActException {
        try {
            marketServeApplyAPI.fundModuleOpinion(id, fundModuleOpinion);
            return new ActResult("fundModuleOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层审核意见
     *
     * @param id                    市场招待申请唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PatchMapping("v1/executiveOpinion/{id}")
    public Result executiveOpinion(@PathVariable String id, @RequestParam(value = "executiveAuditOpinion") AuditType executiveAuditOpinion) throws ActException {
        try {
            marketServeApplyAPI.executiveOpinion(id, executiveAuditOpinion);
            return new ActResult("executiveOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/addCustomerInfo")
    public Result addClientInfo(@Validated({ADD.class}) CustomerInfoTO to, BindingResult result) throws ActException {
        try {
            marketServeApplyAPI.addClientInfo(to);
            return ActResult.initialize("addcustomerinfo success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/editCustomerInfo")
    public Result editClientInfo(@Validated(value = {CustomerInfoTO.EditCustomer.class}) CustomerInfoTO to, BindingResult result) throws ActException {
        try {
            marketServeApplyAPI.editClientInfo(to);
            return ActResult.initialize("editClientInfo success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}