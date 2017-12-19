package com.bjike.goddess.businessprojectmanage.action.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.api.CusPermissionAPI;
import com.bjike.goddess.businessprojectmanage.api.NotificationFormulaAPI;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.to.NotificationFormulaTO;
import com.bjike.goddess.businessprojectmanage.vo.NotificationFormulaVO;
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
 * 通报机制制定
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("notificationformula")
public class NotificationFormulaAction {

    @Autowired
    NotificationFormulaAPI notificationFormulaAPI;
    @Autowired
    CusPermissionAPI cusPermissionAPI;

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

            Boolean isHasPermission = cusPermissionAPI.guidePermission(guidePermissionTO);
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
     * 获取通报列表
     *
     * @version v1
     * @return class NotificationFormulaVO
     */
//    @LoginAuth
    @GetMapping("v1/list")
    public Result list() throws ActException{
        try {
            List<NotificationFormulaVO> list = BeanTransform.copyProperties(notificationFormulaAPI.list(), NotificationFormulaVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加通报
     *
     * @param to to
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/add")
    public Result add(NotificationFormulaTO to, BindingResult bindingResult) throws ActException{
        try {
            notificationFormulaAPI.add(to);
            return new ActResult("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑通报
     *
     * @param to to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(NotificationFormulaTO to) throws ActException{
        try {
            notificationFormulaAPI.update(to);
            return new ActResult("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除通报
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete")
    public Result delete(String id) throws ActException{
        try {
            notificationFormulaAPI.delete(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 邮件汇总
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail() throws ActException {
        try {
            notificationFormulaAPI.checkSendEmail( );
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}