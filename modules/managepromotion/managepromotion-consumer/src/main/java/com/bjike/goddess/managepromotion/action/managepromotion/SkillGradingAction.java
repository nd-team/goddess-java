package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.SkillGradingAPI;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import com.bjike.goddess.managepromotion.to.SkillGradingTO;
import com.bjike.goddess.managepromotion.vo.EmployeeFunctionLevelVO;
import com.bjike.goddess.managepromotion.vo.SkillGradingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 技能定级
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("skillgrading")
public class SkillGradingAction {
    @Autowired
    private SkillGradingAPI skillGradingAPI;
    /**
     * 技能定级列表总条数
     *
     * @param skillGradingDTO 技能定级记录dto
     * @des 获取所有技能定级
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SkillGradingDTO skillGradingDTO) throws ActException {
        try {
            Long count = skillGradingAPI.countSkillGrading(skillGradingDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个技能定级
     *
     * @param id
     * @return class SkillGradingVO
     * @des 获取一个技能定级
     * @version v1
     */
    @GetMapping("v1/skill/{id}")
    public Result skill(@PathVariable String id) throws ActException {
        try {
            SkillGradingBO skillGradingBO = skillGradingAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(skillGradingBO, SkillGradingVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能定级列表
     *
     * @param skillGradingDTO 技能定级记录dto
     * @return class SkillGradingVO
     * @des 获取所有技能定级
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SkillGradingDTO skillGradingDTO, HttpServletRequest request) throws ActException {
        try {
            List<SkillGradingVO> skillGradingVOS = BeanTransform.copyProperties(
                    skillGradingAPI.findListSkillGrading(skillGradingDTO), SkillGradingVO.class, request);
            return ActResult.initialize(skillGradingVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加技能定级
     *
     * @param skillGradingTO 技能定级to
     * @return class SkillGradingVO
     * @des 添加技能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SkillGradingTO skillGradingTO, BindingResult bindingResult) throws ActException {
        try {
            SkillGradingBO skillGradingBO = skillGradingAPI.insertSkillGrading(skillGradingTO);
            return ActResult.initialize(skillGradingBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑技能定级
     *
     * @param skillGradingTO 技能定级数据to
     * @return class SkillGradingVO
     * @des 添加技能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SkillGradingTO skillGradingTO, BindingResult bindingResult) throws ActException {
        try {
            SkillGradingBO skillGradingBO = skillGradingAPI.editSkillGrading(skillGradingTO);
            return ActResult.initialize(skillGradingBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除技能定级
     *
     * @param id 用户id
     * @des 根据用户id删除技能定级
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            skillGradingAPI.removeSkillGrading(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}