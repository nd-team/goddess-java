package com.bjike.goddess.royalty.action.royalty;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.royalty.api.DepartmentBetAPI;
import com.bjike.goddess.royalty.bo.DepartmentBetABO;
import com.bjike.goddess.royalty.dto.DepartmentBetBDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDDTO;
import com.bjike.goddess.royalty.dto.DepartmentBetDTO;
import com.bjike.goddess.royalty.entity.DepartmentBet;
import com.bjike.goddess.royalty.to.DepartmentBetATO;
import com.bjike.goddess.royalty.to.DepartmentBetTO;
import com.bjike.goddess.royalty.to.GuidePermissionTO;
import com.bjike.goddess.royalty.to.SystemBetATO;
import com.bjike.goddess.royalty.vo.DepartmentBetAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 部门间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 02:16 ]
 * @Description: [ 部门间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("departmentbet")
public class DepartmentBetAction {
    @Autowired
    private DepartmentBetAPI departmentBetAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = departmentBetAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 部门间对赌表列表总条数
     *
     * @param dto 部门间对赌表dto
     * @des 获取所有部门间对赌表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DepartmentBetDDTO dto) throws ActException {
        try {
            Long count = departmentBetAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个部门间对赌表
     *
     * @param id
     * @return class DepartmentBetAVO
     * @des 获取一个部门间对赌表
     * @version v1
     */
    @GetMapping("v1/department/{id}")
    public Result department(@PathVariable String id) throws ActException {
        try {
            DepartmentBetABO departmentBetABO = departmentBetAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(departmentBetABO, DepartmentBetAVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门间对赌表列表
     *
     * @param dto 部门间对赌表dto
     * @des 获取所有部门间对赌表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DepartmentBetDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(departmentBetAPI.list(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加部门间对赌表
     *
     * @param departmentBetATO 部门间对赌表to
     * @des 添加部门间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated DepartmentBetATO departmentBetATO, BindingResult bindingResult) throws ActException {
        try {
            departmentBetAPI.insert(departmentBetATO);
            return ActResult.initialize("insert success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑部门间对赌表
     *
     * @param departmentBetATO 部门间对赌表数据to
     * @des 编辑部门间对赌表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated DepartmentBetATO departmentBetATO, BindingResult bindingResult) throws ActException {
        try {
            departmentBetAPI.edit(departmentBetATO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除部门间对赌表
     *
     * @param id 用户id
     * @des 根据用户id删除部门间对赌表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            departmentBetAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}