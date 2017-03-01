package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.entity.rbac.Permission;
import com.bjike.goddess.user.service.rbac.PermissionAPI;
import com.bjike.goddess.user.service.rbac.RoleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限资源操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("rbac/permission")
public class PermissionAct {
    @Autowired
    private PermissionAPI permissionAPI;

    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            return ActResult.initialize(permissionAPI.treeData(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("add")
    public ActResult add(Permission permission) throws ActException {
        try {
            return ActResult.initialize(permissionAPI.savePermission(permission));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PostMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            permissionAPI.delete(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}