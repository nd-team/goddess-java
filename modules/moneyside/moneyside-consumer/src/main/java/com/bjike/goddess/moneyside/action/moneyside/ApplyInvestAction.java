package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.ApplyInvestAPI;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.to.*;
import com.bjike.goddess.moneyside.vo.ApplyInvestVO;
import com.bjike.goddess.moneyside.vo.CollectApplyInvestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 申请投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:23 ]
 * @Description: [ 申请投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("applyinvest")
public class ApplyInvestAction {

//    @Autowired
//    private ApplyInvestAPI applyInvestAPI;
//
//    /**
//     * 申请投资列表总条数
//     *
//     * @param applyInvestDTO 申请投资dto
//     * @des 获取所有申请投资总条数
//     * @version v1
//     */
//    @GetMapping("v1/count")
//    public Result count(ApplyInvestDTO applyInvestDTO) throws ActException {
//        try {
//            Long count = applyInvestAPI.countApplyInvest(applyInvestDTO);
//            return ActResult.initialize(count);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 一个申请投资
//     *
//     * @param id
//     * @return class ApplyInvestVO
//     * @des 获取一个申请投资
//     * @version v1
//     */
//    @GetMapping("v1/apply/{id}")
//    public Result apply(@PathVariable String id) throws ActException {
//        try {
//            ApplyInvestBO applyInvestBO = applyInvestAPI.getOne(id);
//            return ActResult.initialize(BeanTransform.copyProperties(applyInvestBO, ApplyInvestVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 申请投资
//     *
//     * @param applyInvestDTO 申请投资dto
//     * @return class ApplyInvestVO
//     * @des 获取所有申请投资
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result list(ApplyInvestDTO applyInvestDTO, HttpServletRequest request) throws ActException {
//        try {
//            List<ApplyInvestVO> applyInvestVOS = BeanTransform.copyProperties
//                    (applyInvestAPI.findListApplyInvest(applyInvestDTO), ApplyInvestVO.class, request);
//            return ActResult.initialize(applyInvestVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 添加申请投资
//     *
//     * @param applyInvestTO 申请投资数据to
//     * @return class ApplyInvestVO
//     * @des 添加申请投资
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(ADD.class) ApplyInvestTO applyInvestTO, BindingResult bindingResult) throws ActException {
//        try {
//            ApplyInvestBO applyInvestBO = applyInvestAPI.insertApplyInvest(applyInvestTO);
//            return ActResult.initialize(applyInvestBO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 编辑申请投资
//     *
//     * @param applyInvestTO 申请投资数据to
//     * @return class ApplyInvestVO
//     * @des 编辑申请投资
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/edit")
//    public Result edit(@Validated(EDIT.class) ApplyInvestTO applyInvestTO, BindingResult bindingResult) throws ActException {
//        try {
//            ApplyInvestBO applyInvestBO = applyInvestAPI.editApplyInvest(applyInvestTO);
//            return ActResult.initialize(applyInvestBO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除申请投资
//     *
//     * @param id 用户id
//     * @des 根据用户id删除申请投资记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            applyInvestAPI.removeApplyInvest(id);
//            return new ActResult("delete success");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//    /**
//     * 投资人汇总
//     *
//     * @param to 汇总条件
//     * @return class CollectApplyInvestVO
//     * @version v1
//     */
//    @GetMapping("v1/investor")
//    public Result investor(@Validated({InvestorTO.Collect.class}) InvestorTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            CollectApplyInvestTO collectTO = BeanTransform.copyProperties(to, CollectApplyInvestTO.class);
//            List<CollectApplyInvestVO> voList = BeanTransform.copyProperties(applyInvestAPI.collectApplyInvest(collectTO), CollectApplyInvestVO.class, request);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//    /**
//     * 投资对象汇总
//     *
//     * @param to 汇总条件
//     * @return class CollectApplyInvestVO
//     * @version v1
//     */
//    @GetMapping("v1/investObject")
//    public Result investObject(@Validated({InvestObjectTO.Collect.class}) InvestObjectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            CollectApplyInvestTO collectTO = BeanTransform.copyProperties(to, CollectApplyInvestTO.class);
//            List<CollectApplyInvestVO> voList = BeanTransform.copyProperties(applyInvestAPI.collectApplyInvest(collectTO), CollectApplyInvestVO.class, request);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//    /**
//     * 投资日期汇总
//     *
//     * @param to 汇总条件
//     * @return class CollectApplyInvestVO
//     * @version v1
//     */
//    @GetMapping("v1/investDate")
//    public Result investDate(@Validated({InvestDateTO.Collect.class}) InvestDateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            CollectApplyInvestTO collectTO = BeanTransform.copyProperties(to, CollectApplyInvestTO.class);
//            List<CollectApplyInvestVO> voList = BeanTransform.copyProperties(applyInvestAPI.collectApplyInvest(collectTO), CollectApplyInvestVO.class, request);
//            return ActResult.initialize(voList);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


}