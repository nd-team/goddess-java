package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.EmployeeFunctionLevelAPI;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.EmployeePromotedTO;
import com.bjike.goddess.managepromotion.vo.EmployeeFunctionLevelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工职能定级
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("employeefunctionlevel")
public class EmployeeFunctionLevelAction {
    @Autowired
    private EmployeeFunctionLevelAPI employeeFunctionLevelAPI;
    /**
     * 员工职能定级列表总条数
     *
     * @param employeeFunctionLevelDTO 员工职能定级记录dto
     * @des 获取所有员工职能定级
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws ActException {
        try {
            Long count = employeeFunctionLevelAPI.countEmployeeFunctionLevel(employeeFunctionLevelDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个员工职能定级
     *
     * @param id
     * @return class EmployeeFunctionLevelVO
     * @des 获取一个员工职能定级
     * @version v1
     */
    @GetMapping("v1/emp/{id}")
    public Result emp(@PathVariable String id) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(employeeFunctionLevelBO, EmployeeFunctionLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 员工职能定级列表
     *
     * @param employeeFunctionLevelDTO 员工职能定级记录dto
     * @return class EmployeeFunctionLevelVO
     * @des 获取所有员工职能定级
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmployeeFunctionLevelDTO employeeFunctionLevelDTO, HttpServletRequest request) throws ActException {
        try {
            List<EmployeeFunctionLevelVO> employeeFunctionLevelVOS = BeanTransform.copyProperties(
                    employeeFunctionLevelAPI.findListEmployeeFunctionLevel(employeeFunctionLevelDTO), EmployeeFunctionLevelVO.class, request);
            return ActResult.initialize(employeeFunctionLevelVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级to
     * @return class EmployeeFunctionLevelVO
     * @des 添加员工职能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.insertSkillGrading(employeeFunctionLevelTO);
            return ActResult.initialize(employeeFunctionLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工职能定级
     *
     * @param employeeFunctionLevelTO 员工职能定级数据to
     * @return class EmployeeFunctionLevelVO
     * @des 添加员工职能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            EmployeeFunctionLevelBO employeeFunctionLevelBO = employeeFunctionLevelAPI.editEmployeeFunctionLevel(employeeFunctionLevelTO);
            return ActResult.initialize(employeeFunctionLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工职能定级
     *
     * @param id 用户id
     * @des 根据用户id删除员工职能定级
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            employeeFunctionLevelAPI.removeEmployeeFunctionLevel(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 技能等级情况概览
     *
     * @param employeeFunctionLevelTO 技能等级情况概览to
     * @return class OverviewSkillLevelVO
     * @des 技能等级情况概览
     * @version v1
     */
    @PostMapping("v1/skill")
    public Result skill(@Validated EmployeeFunctionLevelTO employeeFunctionLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = employeeFunctionLevelAPI.skill(employeeFunctionLevelTO);
            return ActResult.initialize(overviewSkillLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}