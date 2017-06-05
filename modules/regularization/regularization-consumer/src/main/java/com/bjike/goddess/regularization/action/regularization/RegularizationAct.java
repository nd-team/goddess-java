package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.to.ManagementScoreTO;
import com.bjike.goddess.regularization.to.PlanModuleSupplyTO;
import com.bjike.goddess.regularization.to.RegularizationTO;
import com.bjike.goddess.regularization.to.ZjbApprovalTO;
import com.bjike.goddess.regularization.vo.ManagementScoreVO;
import com.bjike.goddess.regularization.vo.RegularizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工转正
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("regularization")
public class RegularizationAct {

    @Autowired
    private RegularizationAPI regularizationAPI;

    /**
     * 根据id查询员工转正
     *
     * @param id 员工转正唯一标识
     * @return class RegularizationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/regularization/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RegularizationBO bo = regularizationAPI.findById(id);
            RegularizationVO vo = BeanTransform.copyProperties(bo, RegularizationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 员工转正dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated RegularizationDTO dto, BindingResult result) throws ActException {
        try {
            Long count = regularizationAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询员工转正
     *
     * @param dto 员工转正dto
     * @return class RegularizationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RegularizationDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RegularizationBO> boList = regularizationAPI.list(dto);
            List<RegularizationVO> voList = BeanTransform.copyProperties(boList, RegularizationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RegularizationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RegularizationBO bo = regularizationAPI.save(to);
            RegularizationVO vo = BeanTransform.copyProperties(bo, RegularizationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable(value = "id") String id) throws ActException {
        try {
            regularizationAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工转正
     *
     * @param to 员工转正to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理层评分
     *
     * @param id 员工转正唯一标识
     * @param to 管理层评分to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/manageScore/{id}")
    public Result manageScore(@PathVariable(value = "id") String id, @Validated(value = {ManagementScoreTO.IManagementScore.class}) ManagementScoreTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.manageScore(id, to);
            return new ActResult("manageScore success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看管理层评分
     *
     * @param id 员工转正唯一标识
     * @return class ManagementScoreVO
     * @throws ActException
     */
    @GetMapping("v1/checkManageScore/{id}")
    public Result checkManageScore(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            List<ManagementScoreBO> boList = regularizationAPI.checkManageScore(id);
            List<ManagementScoreVO> voList = BeanTransform.copyProperties(boList, ManagementScoreVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层评价
     *
     * @param id                    员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank     决策层评分等级
     * @param decisionLevelScore    决策层具体评分
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/decisionLevelEvaluate/{id}")
    public Result decisionLevelEvaluate(@PathVariable(value = "id") String id, @RequestParam(value = "decisionLevelEvaluate") String decisionLevelEvaluate, @RequestParam(value = "decisionLevelRank") String decisionLevelRank, @RequestParam(value = "decisionLevelScore") Integer decisionLevelScore) throws ActException {
        try {
            regularizationAPI.decisionLevelEvaluate(id, decisionLevelEvaluate, decisionLevelRank, decisionLevelScore);
            return new ActResult("decisionLevelEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块补充
     *
     * @param to 规划模块补充to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/planModuleSupply")
    public Result planModuleSupply(@Validated(value = {PlanModuleSupplyTO.PlanModuleSupply.class}) PlanModuleSupplyTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.planModuleSupply(to);
            return new ActResult("planModuleSupply success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算模块补充
     *
     * @param id 员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @throws ActException
     */
    @PutMapping("v1/budgetModuleSupply")
    public Result budgetModuleSupply(@PathVariable(value = "id") String id, @RequestParam(value = "budgetPositiveComment") String budgetPositiveComment) throws ActException {
        try {
            regularizationAPI.budgetModuleSupply(id, budgetPositiveComment);
            return new ActResult("budgetModuleSupply success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/zjbApproval")
    public Result zjbApproval(@Validated(value = {ZjbApprovalTO.ZjbApproval.class}) ZjbApprovalTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.zjbApproval(to);
            return new ActResult("zjbApproval success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}