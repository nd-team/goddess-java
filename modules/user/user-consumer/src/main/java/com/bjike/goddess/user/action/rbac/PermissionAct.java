package com.bjike.goddess.user.action.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.rbac.PermissionAPI;
import com.bjike.goddess.user.to.rbac.PermissionTO;
import com.bjike.goddess.user.vo.rbac.PermissionVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@LoginAuth
@DefaultProperties
@RestController
@RequestMapping("permission")
public class PermissionAct {
    @Autowired
    private PermissionAPI permissionAPI;

    /**
     * 获取权限资源树
     *
     * @param id id不为空时查询下层子节点,参数为空时查询最顶层
     * @return class PermissionVO
     * @userToken yes
     * @des 逐层加载, 参考ztree
     * @version v1
     */
    @GetMapping("v1/tree")
    public Result treeData(String id, HttpServletRequest request) throws ActException {
        try {
            List<PermissionVO> vos = BeanTransform.copyProperties(permissionAPI.treeData(id), PermissionVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资源
     *
     * @param permissionTO 新的资源信息
     * @return class PermissionVO
     * @userToken yes
     * @des 返回资源信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) PermissionTO permissionTO, BindingResult result) throws ActException {
        try {
            PermissionVO vo = BeanTransform.copyProperties(permissionAPI.save(permissionTO), PermissionVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除权限资源
     *
     * @param id 权限资源唯一标示
     * @userToken yes
     * @des 如该节点存在子节点, 先删除子节点
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            permissionAPI.remove(id);
            return new ActResult("delete is success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑权限资源信息
     *
     * @param permissionTO
     * @userToken yes
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) PermissionTO permissionTO, BindingResult result) throws ActException {
        try {
            permissionAPI.update(permissionTO);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}