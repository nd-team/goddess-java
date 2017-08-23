package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.GroupByInsurerAPI;
import com.bjike.goddess.businsurance.bo.GroupByInsurerBO;
import com.bjike.goddess.businsurance.dto.GroupByInsurerDTO;
import com.bjike.goddess.businsurance.to.GroupByInsurerTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.vo.GroupByInsurerVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 团体意外险被保险人被保险人信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:07 ]
 * @Description: [ 团体意外险被保险人被保险人信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("groupbyinsurer")
public class GroupByInsurerAction {

    @Autowired
    private GroupByInsurerAPI groupByInsurerAPI;

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

            Boolean isHasPermission = groupByInsurerAPI.guidePermission(guidePermissionTO);
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
     *  列表总条数
     *
     * @param groupByInsurerDTO  团体意外险被保险人信息dto
     * @des 获取所有团体意外险被保险人信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(GroupByInsurerDTO groupByInsurerDTO) throws ActException {
        try {
            Long count = groupByInsurerAPI.countGroupByInsurer(groupByInsurerDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param groupByInsurerDTO 团体意外险被保险人信息dto
     * @des 获取所有团体意外险被保险人信息
     * @return  class GroupByInsurerVO
     * @version v1
     */
    @GetMapping("v1/listGroupByInsurer")
    public Result findList(GroupByInsurerDTO groupByInsurerDTO, BindingResult bindingResult) throws ActException {
        try {
            List<GroupByInsurerVO> groupByInsurerVOList = BeanTransform.copyProperties(
                    groupByInsurerAPI.listGroupByInsurer(groupByInsurerDTO), GroupByInsurerVO.class, true);
            return ActResult.initialize(groupByInsurerVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param groupByInsurerTO 团体意外险被保险人基本信息数据to
     * @des 添加团体意外险被保险人
     * @return  class GroupByInsurerVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(GroupByInsurerTO.TestAdd.class) GroupByInsurerTO groupByInsurerTO, BindingResult bindingResult) throws ActException {
        try {
            GroupByInsurerBO groupByInsurerBO1 = groupByInsurerAPI.addGroupByInsurer(groupByInsurerTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupByInsurerBO1,GroupByInsurerVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param groupByInsurerTO 团体意外险被保险人基本信息数据bo
     * @des 编辑团体意外险被保险人
     * @return  class GroupByInsurerVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(GroupByInsurerTO.TestAdd.class) GroupByInsurerTO groupByInsurerTO) throws ActException {
        try {
            GroupByInsurerBO groupByInsurerBO1 = groupByInsurerAPI.editGroupByInsurer(groupByInsurerTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupByInsurerBO1,GroupByInsurerVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除团体意外险被保险人信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupByInsurerAPI.deleteGroupByInsurer(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 一个被保险人信息
     *
     * @param id id
     * @des 根据id查看详细
     * @return  class GroupByInsurerVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            GroupByInsurerBO busInsuranceBO1 = groupByInsurerAPI.getGroupByInsurer(id);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,GroupByInsurerVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }







}