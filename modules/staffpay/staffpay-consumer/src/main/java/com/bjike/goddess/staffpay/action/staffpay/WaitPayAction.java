package com.bjike.goddess.staffpay.action.staffpay;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffpay.api.WaitPayAPI;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.enums.FindType;
import com.bjike.goddess.staffpay.excel.SonPermissionObject;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;
import com.bjike.goddess.staffpay.vo.WaitPayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAction {
    @Autowired
    private WaitPayAPI waitPayAPI;
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

            List<SonPermissionObject> hasPermissionList = waitPayAPI.sonPermission();
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

            Boolean isHasPermission = waitPayAPI.guidePermission(guidePermissionTO);
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
     * 等待付款列表总条数
     *
     * @param waitPayDTO 等待付款记录dto
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WaitPayDTO waitPayDTO) throws ActException {
        try {
            waitPayDTO.getConditions().add(Restrict.eq("findType", FindType.WAIT));
            Long count = waitPayAPI.countWaitPay(waitPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个等待付款
     *
     * @param id
     * @return class WaitPayVO
     * @des 获取一个等待付款
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(waitPayBO, WaitPayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 等待付款列表
     *
     * @param waitPayDTO 等待付款记录dto
     * @return class WaitPayVO
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WaitPayDTO waitPayDTO, HttpServletRequest request) throws ActException {
        try {
            waitPayDTO.getConditions().add(Restrict.eq("findType", FindType.WAIT));
            List<WaitPayVO> waitPayVOS = BeanTransform.copyProperties(
                    waitPayAPI.findListWaitPay(waitPayDTO), WaitPayVO.class, request);
            return ActResult.initialize(waitPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加等待付款
     *
     * @param waitPayTO 等待付款to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.insertWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayVO
     * @des 添加等待付款
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WaitPayTO waitPayTO, BindingResult bindingResult) throws ActException {
        try {
            WaitPayBO waitPayBO = waitPayAPI.editWaitPay(waitPayTO);
            return ActResult.initialize(waitPayBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除等待付款
     *
     * @param id 用户id
     * @des 根据用户id删除等待付款
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.removeWaitPay(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 第一次付款
     *
     * @param id 付款id
     * @des 第一次付款
     * @version v1
     */
    @PatchMapping("v1/firstPay/{id}")
    public Result firstPay(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.firstPay(id);
            return ActResult.initialize("付款成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 已付款
     *
     * @param id 付款id
     * @des 已付款
     * @version v1
     */
    @PatchMapping("v1/secondPay/{id}")
    public Result secondPay(@PathVariable String id) throws ActException {
        try {
            waitPayAPI.secondPay(id);
            return ActResult.initialize("付款成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}