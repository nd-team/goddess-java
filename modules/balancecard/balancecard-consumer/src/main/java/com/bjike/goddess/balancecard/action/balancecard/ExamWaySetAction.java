package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.ExamWaySetAPI;
import com.bjike.goddess.balancecard.bo.ExamWaySetBO;
import com.bjike.goddess.balancecard.dto.ExamWaySetDTO;
import com.bjike.goddess.balancecard.to.ExamWaySetTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.vo.ExamWaySetVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 考核方式设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 考核方式设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("examwayset")
public class ExamWaySetAction {

    @Autowired
    private ExamWaySetAPI examWaySetAPI;

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

            Boolean isHasPermission = examWaySetAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param examWaySetDTO  考核方式信息dto
     * @des 获取所有考核方式信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ExamWaySetDTO examWaySetDTO) throws ActException {
        try {
            Long count = examWaySetAPI.countExamWaySet(examWaySetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 考核方式列表
     *
     * @param examWaySetDTO 考核方式信息dto
     * @param request 前端过滤参数
     * @des 获取所有考核方式信息
     * @return  class ExamWaySetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListExamWaySet(ExamWaySetDTO examWaySetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ExamWaySetVO> examWaySetVOList = BeanTransform.copyProperties(
                    examWaySetAPI.listExamWaySet(examWaySetDTO), ExamWaySetVO.class, request);
            return ActResult.initialize(examWaySetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个考核方式
     *
     * @param id 考核方式信息id
     * @des 获取所有考核方式信息
     * @return  class ExamWaySetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            ExamWaySetVO examWaySetVOList = BeanTransform.copyProperties(
                    examWaySetAPI.getOneById( id), ExamWaySetVO.class);
            return ActResult.initialize(examWaySetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加考核方式
     *
     * @param examWaySetTO 考核方式基本信息数据to
     * @des 添加考核方式
     * @return  class ExamWaySetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addExamWaySet(@Validated ExamWaySetTO examWaySetTO, BindingResult bindingResult) throws ActException {
        try {
            ExamWaySetBO examWaySetBO1 = examWaySetAPI.addExamWaySet(examWaySetTO);
            return ActResult.initialize(BeanTransform.copyProperties(examWaySetBO1,ExamWaySetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑考核方式
     *
     * @param examWaySetTO 考核方式基本信息数据bo
     * @des 编辑考核方式
     * @return  class ExamWaySetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editExamWaySet(@Validated ExamWaySetTO examWaySetTO) throws ActException {
        try {
            ExamWaySetBO examWaySetBO1 = examWaySetAPI.editExamWaySet(examWaySetTO);
            return ActResult.initialize(BeanTransform.copyProperties(examWaySetBO1,ExamWaySetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除考核方式信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteExamWaySet(@PathVariable String id) throws ActException {
        try {
            examWaySetAPI.deleteExamWaySet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 获取所有考核方式
     *
     * @des 获取所有考核方式
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName( ) throws ActException {
        try {
            List<String> dimensionSetVOList = examWaySetAPI.listName( ) ;
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}