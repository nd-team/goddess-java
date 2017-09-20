package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.SubsidySchemeAPI;
import com.bjike.goddess.assistance.bo.SubsidySchemeBO;
import com.bjike.goddess.assistance.dto.SubsidySchemeDTO;
import com.bjike.goddess.assistance.entity.SubsidyScheme;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SubsidySchemeTO;
import com.bjike.goddess.assistance.vo.SubsidySchemeVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司补助方案
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("subsidyscheme")
public class SubsidySchemeAction {
    @Autowired
    private SubsidySchemeAPI subsidySchemeAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = subsidySchemeAPI.guidePermission(guidePermissionTO);
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
     * 总条数
     *
     * @param subsidySchemeDTO 公司补助方案dto
     * @des 获取所有公司补助方案总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SubsidySchemeDTO subsidySchemeDTO) throws ActException {
        try {
            Long count = subsidySchemeAPI.countSubsidy(subsidySchemeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个
     *
     * @param id 公司补助方案id
     * @return class SubsidySchemeVO
     * @des 一个工公司补助方案
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SubsidySchemeVO subsidySchemeVO = BeanTransform.copyProperties(
                    subsidySchemeAPI.getOneById(id), SubsidySchemeVO.class);
            return ActResult.initialize(subsidySchemeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param subsidySchemeDTO 公司补助方案信息dto
     * @return class SubsidySchemeVO
     * @des 获取所有公司补助方案信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSubsidy(SubsidySchemeDTO subsidySchemeDTO, BindingResult bindingResult) throws ActException {
        try {
            List<SubsidySchemeVO> ageAssistVOList = BeanTransform.copyProperties(
                    subsidySchemeAPI.listSubsidy(subsidySchemeDTO), SubsidySchemeVO.class);
            return ActResult.initialize(ageAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param subsidySchemeTO 公司补助方案基本信息数据to
     * @return class SubsidySchemeVO
     * @des 添加公司补助方案
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSubsidy(@Validated({ADD.class}) SubsidySchemeTO subsidySchemeTO, BindingResult bindingResult) throws ActException {
        try {
            SubsidySchemeBO subsidySchemeBO = subsidySchemeAPI.addSubsidy(subsidySchemeTO);
            return ActResult.initialize(BeanTransform.copyProperties(subsidySchemeBO, SubsidySchemeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param subsidySchemeTO 公司补助方案基本信息数据bo
     * @return class SubsidySchemeVO
     * @des 添加公司补助方案
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSubsidy(@Validated({EDIT.class}) SubsidySchemeTO subsidySchemeTO) throws ActException {
        try {
            SubsidySchemeBO subsidySchemeBO = subsidySchemeAPI.editSubsidy(subsidySchemeTO);
            return ActResult.initialize(BeanTransform.copyProperties(subsidySchemeBO, SubsidySchemeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司补助方案信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSubsidy(@PathVariable String id) throws ActException {
        try {
            subsidySchemeAPI.deleteSubsidy(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
    /**
     * 总经理审核
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/genManage")
    public Result genManage(@Validated(value = {SubsidySchemeTO.testAudit.class}) SubsidySchemeTO to, BindingResult result) throws ActException {
        try {
            subsidySchemeAPI.manageAudit(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}