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

    /**
     * 异步获取权限资源树结构,逐层加载,参考ztree
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @return 树结构数据
     * @throws ActException
     */
    @GetMapping("treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            return ActResult.initialize(permissionAPI.treeData(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资源
     *
     * @param permission 新的资源信息
     * @return 持久化的的资源信息
     * @throws ActException
     */
    @PostMapping("add")
    public ActResult add(Permission permission) throws ActException {
        try {
            return ActResult.initialize(permissionAPI.savePermission(permission));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除权限资源(如该节点存在子节点,先删除子节点)
     *
     * @param id 权限资源唯一标示
     * @return
     * @throws ActException
     */
    @DeleteMapping("delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            permissionAPI.delete(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}