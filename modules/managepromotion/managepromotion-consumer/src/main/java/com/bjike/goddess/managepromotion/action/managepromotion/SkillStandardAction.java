package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.SkillStandardAPI;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillStandardBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.dto.SkillStandardDTO;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;
import com.bjike.goddess.managepromotion.vo.EmployeeFunctionLevelVO;
import com.bjike.goddess.managepromotion.vo.SkillStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 技能评定标准
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("skillstandard")
public class SkillStandardAction {
    @Autowired
    private SkillStandardAPI skillStandardAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = skillStandardAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能评定标准列表总条数
     *
     * @param dto 技能评定标准记录dto
     * @des 获取所有技能评定标准
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SkillStandardDTO dto) throws ActException {
        try {
            Long count = skillStandardAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个技能评定标准
     *
     * @param id
     * @return class SkillStandardVO
     * @des 获取一个技能评定标准
     * @version v1
     */
    @GetMapping("v1/standard/{id}")
    public Result standard(@PathVariable String id) throws ActException {
        try {
            SkillStandardBO bo = skillStandardAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, SkillStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能评定标准列表
     *
     * @param dto 技能评定标准记录dto
     * @return class SkillStandardVO
     * @des 获取所有技能评定标准
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SkillStandardDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SkillStandardVO> skillStandardVOS = BeanTransform.copyProperties(
                    skillStandardAPI.list(dto), SkillStandardVO.class, request);
            return ActResult.initialize(skillStandardVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加技能评定标准
     *
     * @param to 技能评定标准to
     * @return class SkillStandardVO
     * @des 添加技能评定标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(SkillStandardTO.TestAdd.class) SkillStandardTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillStandardBO bo = skillStandardAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,SkillStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑技能评定标准
     *
     * @param to 技能评定标准数据to
     * @return class SkillStandardVO
     * @des 编辑技能评定标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(SkillStandardTO.TestEdit.class) SkillStandardTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillStandardBO bo = skillStandardAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo,SkillStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除技能评定标准
     *
     * @param id 用户id
     * @des 根据用户id删除技能评定标准
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            skillStandardAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}