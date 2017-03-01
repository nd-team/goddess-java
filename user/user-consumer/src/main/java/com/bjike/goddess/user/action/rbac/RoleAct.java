package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.entity.rbac.Role;
import com.bjike.goddess.user.service.rbac.PermissionAPI;
import com.bjike.goddess.user.service.rbac.RoleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("add")
    public ActResult add(Role role) throws ActException {
        try {
            return ActResult.initialize(roleAPI.saveRole(role));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            roleAPI.delete(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}