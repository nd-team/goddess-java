package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.RolePermissionAPI;
import com.bjike.goddess.user.bo.rbac.RolePermissionBO;
import com.bjike.goddess.user.to.rbac.RolePermissionTO;
import com.bjike.goddess.user.vo.rbac.RolePermissionVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色权限操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:44]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@LoginAuth
@DefaultProperties
@RestController
@RequestMapping("role/permission")
public class RolePermissionAct {

    @Autowired
    private RolePermissionAPI rolePermissionAPI;

    /**
     * 添加
     *
     * @param rolePermissionTO 角色权限信息
     * @return class RolePermissionVO
     * @userToken yes
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RolePermissionTO rolePermissionTO, BindingResult result) throws ActException {
        try {
            RolePermissionBO rolePermissionBO = rolePermissionAPI.saveByTO(rolePermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(rolePermissionBO, RolePermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
