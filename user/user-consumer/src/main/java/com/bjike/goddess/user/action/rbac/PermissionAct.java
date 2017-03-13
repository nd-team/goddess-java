package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.PermissionAPI;
import com.bjike.goddess.user.to.rbac.PermissionTO;
import com.bjike.goddess.user.vo.rbac.PermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 获取权限资源树结构
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @des 逐层加载, 参考ztree
     * @version v1
     */
    @GetMapping("v1/treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            List<PermissionVO> vos = BeanTransform.copyProperties(permissionAPI.treeData(id), PermissionVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资源
     *
     * @param permissionTO 新的资源信息
     * @des 返回持久化的的资源信息
     * @version v1
     */
    @PostMapping("v1/add")
    public ActResult add(PermissionTO permissionTO) throws ActException {
        try {
            return ActResult.initialize(permissionAPI.save(permissionTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除权限资源
     *
     * @param id 权限资源唯一标示
     * @des 如该节点存在子节点,先删除子节点
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            permissionAPI.remove(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}