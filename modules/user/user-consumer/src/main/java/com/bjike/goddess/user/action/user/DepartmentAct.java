package com.bjike.goddess.user.action.user;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.to.DepartmentTO;
import com.bjike.goddess.user.vo.DepartmentVO;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 部门操作
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@LoginAuth
@RestController
@RequestMapping("department")
public class DepartmentAct {

    @Autowired
    private DepartmentAPI departmentAPI;


    /**
     * 获取部门树结构
     *
     * @param id 通id不为空时查询下层子节点,参数为空时查询最顶层
     * @return class DepartmentVO
     * @userToken yes
     * @version v1
     */
    @GetMapping("v1/tree")
    public Result treeData(String id, HttpServletRequest request) throws ActException {
        try {
            List<DepartmentVO> vos = BeanTransform.copyProperties(departmentAPI.treeData(id), DepartmentVO.class, request);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加部门
     *
     * @param departmentTO 部门信息
     * @return class DepartmentVO
     * @userToken yes
     * @des 返回部门信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DepartmentTO departmentTO, BindingResult result) throws ActException {
        try {
            DepartmentVO vo = BeanTransform.copyProperties(departmentAPI.save(departmentTO), DepartmentVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除部门
     *
     * @param id 部门唯一标示
     * @userToken yes
     * @des 如该节点存在子节点, 先删除子节点
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            departmentAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑部门信息
     *
     * @param departmentTO 部门信息
     * @userToken yes
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DepartmentTO departmentTO, BindingResult result) throws ActException {
        try {
            departmentAPI.update(departmentTO);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}