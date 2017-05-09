package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.UserRoleAPI;
import com.bjike.goddess.user.bo.rbac.UserRoleBO;
import com.bjike.goddess.user.to.rbac.UserRoleTO;
import com.bjike.goddess.user.vo.rbac.UserRoleVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色操作
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
@RequestMapping("user-role")
public class UserRoleAct {

    @Autowired
    private UserRoleAPI userRoleAPI;

    /**
     * 添加用户角色
     *
     * @param userRoleTO 用户角色信息
     * @return class UserRoleVO
     * @userToken yes
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) UserRoleTO userRoleTO, BindingResult result) throws ActException {
        try {
            UserRoleBO userRoleBO = userRoleAPI.saveByTO(userRoleTO);
            return ActResult.initialize(BeanTransform.copyProperties(userRoleBO, UserRoleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
