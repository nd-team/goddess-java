package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.GroupRoleAPI;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import com.bjike.goddess.user.vo.rbac.GroupRoleVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组角色操作
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
@RequestMapping("group/role")
public class GroupRoleAct {

    @Autowired
    private GroupRoleAPI groupRoleAPI;

    /**
     * 添加
     * @userToken yes
     * @param groupRoleTO 角色组信息
     * @return class GroupRoleVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) GroupRoleTO groupRoleTO, BindingResult result) throws ActException {
        try {
            GroupRoleBO groupRoleBO = groupRoleAPI.saveByTO(groupRoleTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupRoleBO, GroupRoleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
