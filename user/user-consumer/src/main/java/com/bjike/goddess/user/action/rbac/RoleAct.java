package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.service.rbac.PermissionAPI;
import com.bjike.goddess.user.service.rbac.RoleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("rbac/role")
public class RoleAct {
    @Autowired
    private RoleAPI roleAPI;

    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            return ActResult.initialize(roleAPI.treeData(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}