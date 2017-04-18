package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.to.RegularizationTO;
import com.bjike.goddess.regularization.vo.ManagementScoreVO;
import com.bjike.goddess.regularization.vo.RegularizationVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@DefaultProperties
public class RegularizationAct {

    @Autowired
    private RegularizationAPI regularizationAPI;

    /**
     * 分页查询员工转正
     *
     * @param dto 员工转正dto
     * @param result
     * @return class RegularizationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated RegularizationDTO dto, BindingResult result) throws ActException {
        try {
            List<RegularizationBO> boList = regularizationAPI.list(dto);
            List<RegularizationVO> voList = BeanTransform.copyProperties(boList, RegularizationVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工转正
     *
     * @param to 员工转正to
     * @param result
     * @return class RegularizationVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RegularizationTO to, BindingResult result) throws ActException {
        try {
            RegularizationBO bo = regularizationAPI.save(to);
            RegularizationVO vo = BeanTransform.copyProperties(bo, RegularizationVO.class);
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
    public Result delete(@PathVariable String id) throws ActException {
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
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated RegularizationTO to, BindingResult result) throws ActException {
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
     * @param to 员工转正to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/manageScore")
    public Result manageScore(@Validated RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.manageScore(to);
            return new ActResult("manageScore success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看管理层评分
     *
     * @param to 员工转正to
     * @param result
     * @return class ManagementScoreVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkManageScore")
    public Result checkManageScore(@Validated RegularizationTO to, BindingResult result) throws ActException {
        try {
            List<ManagementScoreBO> boList = regularizationAPI.checkManageScore(to);
            List<ManagementScoreVO> voList = BeanTransform.copyProperties(boList, ManagementScoreVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层评价
     *
     * @param to 员工转正to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/decisionLevelEvaluate")
    public Result decisionLevelEvaluate(@Validated RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.decisionLevelEvaluate(to);
            return new ActResult("decisionLevelEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块补充
     *
     * @param to 员工转正to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/planModuleSupply")
    public Result planModuleSupply(@Validated RegularizationTO to, BindingResult result) throws ActException {
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
     * @param to 员工转正to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/budgetModuleSupply")
    public Result budgetModuleSupply(@Validated RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.budgetModuleSupply(to);
            return new ActResult("budgetModuleSupply success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审批
     *
     * @param to 员工转正to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/zjbApproval")
    public Result zjbApproval(@Validated RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.zjbApproval(to);
            return new ActResult("zjbApproval success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}