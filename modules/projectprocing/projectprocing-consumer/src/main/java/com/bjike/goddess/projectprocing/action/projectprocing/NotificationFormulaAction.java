package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.NotificationFormulaAPI;
import com.bjike.goddess.projectprocing.bo.NotificationFormulaBO;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NotificationFormulaTO;
import com.bjike.goddess.projectprocing.vo.NotificationFormulaVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通报机制制定
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("notificationformula")
public class NotificationFormulaAction {

    @Autowired
    private NotificationFormulaAPI notificationFormulaAPI;

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

            Boolean isHasPermission = notificationFormulaAPI.guidePermission(guidePermissionTO);
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
     * 通报机制制定总条数
     *
     * @param notificationFormulaDTO 通报机制制定dto
     * @des 获取所有通报机制制定总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(NotificationFormulaDTO notificationFormulaDTO) throws ActException {
        try {
            Long count = notificationFormulaAPI.countNotifi(notificationFormulaDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个通报机制制定
     *
     * @param id 通报机制制定id
     * @return class NotificationFormulaVO
     * @des 根据id获取通报机制制定
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            NotificationFormulaVO nodeHeadersCustomVO = BeanTransform.copyProperties(
                    notificationFormulaAPI.getOne(id), NotificationFormulaVO.class, true);
            return ActResult.initialize(nodeHeadersCustomVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 通报机制制定列表
     *
     * @param notificationFormulaDTO 通报机制制定dto
     * @return class NotificationFormulaVO
     * @des 获取通报机制制定
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(NotificationFormulaDTO notificationFormulaDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<NotificationFormulaVO> notificationFormulaVOS = BeanTransform.copyProperties(
                    notificationFormulaAPI.listCollectEmail(notificationFormulaDTO), NotificationFormulaVO.class, request);
            return ActResult.initialize(notificationFormulaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加通报机制制定
     *
     * @param notificationFormulaTO 通报机制制定数据to
     * @return class NotificationFormulaVO
     * @des 添加通报机制制定
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ADD.class}) NotificationFormulaTO notificationFormulaTO, BindingResult bindingResult) throws ActException {
        try {
            NotificationFormulaBO notificationFormulaBO = notificationFormulaAPI.addCollectEmail(notificationFormulaTO);
            return ActResult.initialize(BeanTransform.copyProperties(notificationFormulaBO, NotificationFormulaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑通报机制制定
     *
     * @param notificationFormulaTO 通报机制制定bo
     * @return class NotificationFormulaVO
     * @des 添加通报机制制定
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({EDIT.class}) NotificationFormulaTO notificationFormulaTO) throws ActException {
        try {
            NotificationFormulaBO notificationFormulaBO = notificationFormulaAPI.editCollectEmail(notificationFormulaTO);
            return ActResult.initialize(BeanTransform.copyProperties(notificationFormulaBO, NotificationFormulaVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除通报机制制定
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            notificationFormulaAPI.deleteCollectEmail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 检测发送邮件
     *
     * @des 检测发送邮件
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail( HttpServletRequest request) throws ActException {
        try {
            notificationFormulaAPI.checkEmail();
            return new ActResult("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}