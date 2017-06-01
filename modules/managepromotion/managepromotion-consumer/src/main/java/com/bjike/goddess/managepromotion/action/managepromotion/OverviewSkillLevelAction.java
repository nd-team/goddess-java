package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.OverviewSkillLevelAPI;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;
import com.bjike.goddess.managepromotion.vo.EmployeeFunctionLevelVO;
import com.bjike.goddess.managepromotion.vo.OverviewSkillLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 技能等级情况概览
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("overviewskilllevel")
public class OverviewSkillLevelAction {
    @Autowired
    private OverviewSkillLevelAPI overviewSkillLevelAPI;
    /**
     * 技能等级情况概览列表总条数
     *
     * @param overviewSkillLevelDTO 技能等级情况概览记录dto
     * @des 获取所有技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OverviewSkillLevelDTO overviewSkillLevelDTO) throws ActException {
        try {
            Long count = overviewSkillLevelAPI.countOverviewSkillLevel(overviewSkillLevelDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个技能等级情况概览
     *
     * @param id
     * @return class OverviewSkillLevelVO
     * @des 获取一个技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/overview/{id}")
    public Result overview(@PathVariable String id) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(overviewSkillLevelBO, OverviewSkillLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能等级情况概览列表
     *
     * @param overviewSkillLevelDTO 技能等级情况概览记录dto
     * @return class OverviewSkillLevelVO
     * @des 获取所有技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OverviewSkillLevelDTO overviewSkillLevelDTO, HttpServletRequest request) throws ActException {
        try {
            List<OverviewSkillLevelVO> overviewSkillLevelVOS = BeanTransform.copyProperties(
                    overviewSkillLevelAPI.findListOverviewSkillLevel(overviewSkillLevelDTO), OverviewSkillLevelVO.class, request);
            return ActResult.initialize(overviewSkillLevelVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览to
     * @return class OverviewSkillLevelVO
     * @des 添加技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OverviewSkillLevelTO overviewSkillLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.insertOverviewSkillLevel(overviewSkillLevelTO);
            return ActResult.initialize(overviewSkillLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览数据to
     * @return class OverviewSkillLevelVO
     * @des 添加技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OverviewSkillLevelTO overviewSkillLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.editOverviewSkillLevel(overviewSkillLevelTO);
            return ActResult.initialize(overviewSkillLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除技能等级情况概览
     *
     * @param id 用户id
     * @des 根据用户id删除技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            overviewSkillLevelAPI.removeOverviewSkillLevel(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}