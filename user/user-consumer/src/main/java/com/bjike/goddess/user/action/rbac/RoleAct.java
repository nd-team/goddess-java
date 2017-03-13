package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.RoleAPI;
import com.bjike.goddess.user.bo.rbac.RoleBO;
import com.bjike.goddess.user.service.rbac.RoleSer;
import com.bjike.goddess.user.to.rbac.RoleTO;
import com.bjike.goddess.user.vo.rbac.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 异步获取角色树结构,逐层加载,参考ztree
     *
     * @param id 通过自身id查询下层子节点,参数为空时查询最顶层
     * @return 树结构数据
     * @version v1
     */
    @GetMapping("v1/treeData")
    public ActResult treeData(String id) throws ActException {
        try {
            List<RoleVO> vos = BeanTransform.copyProperties(roleAPI.treeData(id), RoleVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加角色
     *
     * @param roleTO 新的角色信息
     * @return 持久化的角色信息
     * @version v1
     */
    @PostMapping("v1/add")
    public ActResult add(RoleTO roleTO) throws ActException {
        try {
            return ActResult.initialize(roleAPI.save(roleTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除角色(如该节点存在子节点,先删除子节点)
     *
     * @param id 角色唯一标示
     * @return
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public ActResult delete(@PathVariable String id) throws ActException {
        try {
            roleAPI.remove(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}