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
import com.bjike.goddess.managepromotion.bo.CalculateBO;
import com.bjike.goddess.managepromotion.bo.SkillGradingABO;
import com.bjike.goddess.managepromotion.dto.SkillGradingADTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingCDTO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGradingA;
import com.bjike.goddess.managepromotion.excel.SonPermissionObject;
import com.bjike.goddess.managepromotion.to.CalculateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillGradingATO;
import com.bjike.goddess.managepromotion.vo.CalculateVO;
import com.bjike.goddess.managepromotion.vo.SkillGradingAVO;
import com.bjike.goddess.managepromotion.vo.SkillVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = skillGradingAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = skillGradingAPI.guidePermission(guidePermissionTO);
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
     * 技能定级列表总条数
     *
     * @param skillGradingCDTO 技能定级记录dto
     * @des 获取所有技能定级
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SkillGradingCDTO skillGradingCDTO) throws ActException {
        try {
            Long count = skillGradingAPI.countSkillGrading(skillGradingCDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个技能定级
     *
     * @param id
     * @return class SkillGradingAVO
     * @des 获取一个技能定级
     * @version v1
     */
    @GetMapping("v1/skill/{id}")
    public Result skill(@PathVariable String id) throws ActException {
        try {
            SkillGradingABO skillGradingABO = skillGradingAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(skillGradingABO, SkillGradingAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能定级列表
     *
     * @param skillGradingADTO 技能定级记录dto
     * @des 获取所有技能定级
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SkillGradingADTO skillGradingADTO, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(skillGradingAPI.findListSkillGrading(skillGradingADTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加技能定级
     *
     * @param skillGradingATO 技能定级to
     * @des 添加技能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SkillGradingATO skillGradingATO, BindingResult bindingResult) throws ActException {
        try {
            skillGradingAPI.insertSkillGrading(skillGradingATO);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑技能定级
     *
     * @param skillGradingATO 技能定级数据to
     * @des 添加技能定级
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SkillGradingATO skillGradingATO, BindingResult bindingResult) throws ActException {
        try {
            skillGradingAPI.editSkillGrading(skillGradingATO);
            return ActResult.initialize("edit success");
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
    /**
     * 计算
     *
     * @param to to
     * @return class SkillVO
     * @des 计算
     * @version v1
     */
    @PostMapping("v1/calculate")
    public Result calculate(CalculateTO to) throws ActException {
        try {
            List<SkillVO> calculateVOS = BeanTransform.copyProperties(skillGradingAPI.calculate(to),SkillVO.class);
            return ActResult.initialize(calculateVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}