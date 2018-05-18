package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 总设置权限配置操作对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-25 02:12 ]
 * @Description: [ 客户权限配置操作对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("usersetpermissionoperate")
public class UserSetPermissionOperateAction {

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    /**
     * 操作总设置的操作权限
     *
     * @des 操作总设置的操作权限
     * @version v1
     */
    @GetMapping("v1/test")
    public Result adminPermission( ) throws ActException {
        try {
            Boolean b  = userSetPermissionAPI.checkSetPermission();
            return ActResult.initialize (b);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}