package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.SenioritySubsidiesStandardAPI;
import com.bjike.goddess.assistance.bo.SenioritySubsidiesStandardBO;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesStandardDTO;
import com.bjike.goddess.assistance.entity.SenioritySubsidiesStandard;
import com.bjike.goddess.assistance.service.SenioritySubsidiesStandardSer;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesStandardTO;
import com.bjike.goddess.assistance.vo.SenioritySubsidiesStandardVO;
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
 * 工龄补助标准
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:07 ]
 * @Description: [ 工龄补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("senioritysubsidiesstandard")
public class SenioritySubsidiesStandardAction {
    @Autowired
    private SenioritySubsidiesStandardAPI senioritySubsidiesStandardAPI;
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

            Boolean isHasPermission = senioritySubsidiesStandardAPI.guidePermission(guidePermissionTO);
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
     * @param senioritySubsidiesStandardDTO 工龄补助标准dto
     * @des 获取所有工龄补助标准总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO) throws ActException {
        try {
            Long count = senioritySubsidiesStandardAPI.countSenior(senioritySubsidiesStandardDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个工龄补助标准
     *
     * @param id 工龄补助标准id
     * @return class SenioritySubsidiesStandardVO
     * @des 一个工龄补助标准
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SenioritySubsidiesStandardVO senioritySubsidiesStandardVO = BeanTransform.copyProperties(
                    senioritySubsidiesStandardAPI.getOneById(id), SenioritySubsidiesStandardVO.class);
            return ActResult.initialize(senioritySubsidiesStandardVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param senioritySubsidiesStandardDTO 工龄补助标准信息dto
     * @return class SenioritySubsidiesStandardVO
     * @des 获取所有工龄补助标准信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSubsidy(SenioritySubsidiesStandardDTO senioritySubsidiesStandardDTO, BindingResult bindingResult) throws ActException {
        try {
            List<SenioritySubsidiesStandardVO> ageAssistVOList = BeanTransform.copyProperties(
                    senioritySubsidiesStandardAPI.listSenior(senioritySubsidiesStandardDTO), SenioritySubsidiesStandardVO.class);
            return ActResult.initialize(ageAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param senioritySubsidiesStandardTO 工龄补助标准基本信息数据to
     * @return class SenioritySubsidiesStandardVO
     * @des 添加工龄补助标准
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSubsidy(@Validated({ADD.class}) SenioritySubsidiesStandardTO senioritySubsidiesStandardTO, BindingResult bindingResult) throws ActException {
        try {
            SenioritySubsidiesStandardBO senioritySubsidiesStandardBO = senioritySubsidiesStandardAPI.addSenior(senioritySubsidiesStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(senioritySubsidiesStandardBO, SenioritySubsidiesStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param senioritySubsidiesStandardTO 工龄补助标准数据bo
     * @return class SenioritySubsidiesStandardVO
     * @des 添加公司补助方案
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSubsidy(@Validated({EDIT.class}) SenioritySubsidiesStandardTO senioritySubsidiesStandardTO) throws ActException {
        try {
            SenioritySubsidiesStandardBO senioritySubsidiesStandardBO = senioritySubsidiesStandardAPI.editSenior(senioritySubsidiesStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(senioritySubsidiesStandardBO, SenioritySubsidiesStandardVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除工龄补助标准记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSubsidy(@PathVariable String id) throws ActException {
        try {
            senioritySubsidiesStandardAPI.deleteSubsidy(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}