package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.SkillPromotionApplyAPI;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;
import com.bjike.goddess.managepromotion.vo.SkillPromotionApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 技能晋升申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("skillpromotionapply")
public class SkillPromotionApplyAction {
    @Autowired
    private SkillPromotionApplyAPI skillPromotionApplyAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = skillPromotionApplyAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 技能晋升申请列表总条数
     *
     * @param skillPromotionApplyDTO 技能晋升申请记录dto
     * @des 获取所有技能晋升申请
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SkillPromotionApplyDTO skillPromotionApplyDTO) throws ActException {
        try {
            Long count = skillPromotionApplyAPI.countSkillPromotionApply(skillPromotionApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个技能晋升申请
     *
     * @param id
     * @return class SkillPromotionApplyVO
     * @des 获取一个技能晋升申请
     * @version v1
     */
    @GetMapping("v1/skill/{id}")
    public Result skill(@PathVariable String id) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能晋升申请列表
     *
     * @param skillPromotionApplyDTO 技能晋升申请记录dto
     * @return class SkillPromotionApplyVO
     * @des 获取所有技能晋升申请
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SkillPromotionApplyDTO skillPromotionApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<SkillPromotionApplyVO> skillPromotionApplyVOS = BeanTransform.copyProperties(
                    skillPromotionApplyAPI.findListSkillPromotionApply(skillPromotionApplyDTO), SkillPromotionApplyVO.class, request);
            return ActResult.initialize(skillPromotionApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请晋升
     *
     * @param skillPromotionApplyTO 申请晋升to
     * @return class SkillPromotionApplyVO
     * @des 申请晋升
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SkillPromotionApplyTO skillPromotionApplyTO, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.insertSkillPromotionApply(skillPromotionApplyTO);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO,SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合素养补充
     *
     * @param skillPromotionApplyTO 综合素养补充数据to
     * @return class SkillPromotionApplyVO
     * @des 综合素养补充
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SkillPromotionApplyTO skillPromotionApplyTO, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.editSkillPromotionApply(skillPromotionApplyTO);
            return ActResult.initialize(skillPromotionApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 模块负责人审核
     *
     * @param to 技能等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/headAudit")
    public Result headAudit(@Validated(SkillPromotionApplyTO.head.class) SkillPromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO =
                    skillPromotionApplyAPI.headAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 运营商务部预算模块审核
     *
     * @param to 技能等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/budgetAudit")
    public Result budgetAudit(@Validated(SkillPromotionApplyTO.budget.class) SkillPromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.budgetAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param to 技能等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/projectManagerAudit")
    public Result projectManagerAudit(@Validated(SkillPromotionApplyTO.projectManager.class) SkillPromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.projectManagerAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 综合资源部规划模块审核
     *
     * @param to 技能等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/planAudit")
    public Result planAudit(@Validated(SkillPromotionApplyTO.plan.class) SkillPromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.planAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param to 技能等级晋升申请to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/generalManagerAudit")
    public Result generalManagerAudit(@Validated(SkillPromotionApplyTO.manager.class) SkillPromotionApplyTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillPromotionApplyBO skillPromotionApplyBO = skillPromotionApplyAPI.generalManagerAudit(to);
            return ActResult.initialize(BeanTransform.copyProperties(skillPromotionApplyBO, SkillPromotionApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}