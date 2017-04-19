package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.RoleAPI;
import com.bjike.goddess.user.to.rbac.RoleTO;
import com.bjike.goddess.user.vo.rbac.RoleVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
@LoginAuth
@DefaultProperties
@RestController
@RequestMapping("role")
public class RoleAct {
    @Autowired
    private RoleAPI roleAPI;

    /**
     * 获取角色树结构
     *
     * @param id id不为空时查询下层子节点,参数为空时查询最顶层
     * @return class RoleVO
     * @des 逐层加载, 参考ztree
     * @version v1
     */
    @GetMapping("v1/tree")
    public Result treeData(String id) throws ActException {
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
     * @return class RoleVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) RoleTO roleTO, BindingResult result) throws ActException {
        try {
            RoleVO vo = BeanTransform.copyProperties(roleAPI.save(roleTO), RoleVO.class);

            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除角色
     *
     * @param id 角色唯一标示
     * @des 如该节点存在子节点, 先删除子节点
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            roleAPI.remove(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑角色信息
     *
     * @param roleTO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) RoleTO roleTO, BindingResult result) throws ActException {
        try {
            roleAPI.update(roleTO);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}