package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.DimensionSetAPI;
import com.bjike.goddess.balancecard.bo.DimensionSetBO;
import com.bjike.goddess.balancecard.dto.DimensionSetDTO;
import com.bjike.goddess.balancecard.to.DimensionSetTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.vo.DimensionSetVO;
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
 * 维度设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:01 ]
 * @Description: [ 维度设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dimensionset")
public class DimensionSetAction {

    @Autowired
    private DimensionSetAPI dimensionSetAPI;

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

            Boolean isHasPermission = dimensionSetAPI.guidePermission(guidePermissionTO);
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
     * @param dimensionSetDTO  维度信息dto
     * @des 获取所有维度信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DimensionSetDTO dimensionSetDTO) throws ActException {
        try {
            Long count = dimensionSetAPI.countDimensionSet(dimensionSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 维度列表
     *
     * @param dimensionSetDTO 维度信息dto
     * @param request 前端过滤参数
     * @des 获取所有维度信息
     * @return  class DimensionSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListDimensionSet(DimensionSetDTO dimensionSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DimensionSetVO> dimensionSetVOList = BeanTransform.copyProperties(
                    dimensionSetAPI.listDimensionSet(dimensionSetDTO), DimensionSetVO.class, request);
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个维度
     *
     * @param id 维度信息id
     * @des 获取所有维度信息
     * @return  class DimensionSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            DimensionSetVO dimensionSetVOList = BeanTransform.copyProperties(
                    dimensionSetAPI.getOneById( id), DimensionSetVO.class);
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加维度
     *
     * @param dimensionSetTO 维度基本信息数据to
     * @des 添加维度
     * @return  class DimensionSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDimensionSet(@Validated DimensionSetTO dimensionSetTO, BindingResult bindingResult) throws ActException {
        try {
            DimensionSetBO dimensionSetBO1 = dimensionSetAPI.addDimensionSet(dimensionSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dimensionSetBO1,DimensionSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑维度
     *
     * @param dimensionSetTO 维度基本信息数据bo
     * @des 编辑维度
     * @return  class DimensionSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editDimensionSet(@Validated DimensionSetTO dimensionSetTO) throws ActException {
        try {
            DimensionSetBO dimensionSetBO1 = dimensionSetAPI.editDimensionSet(dimensionSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dimensionSetBO1,DimensionSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除维度信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDimensionSet(@PathVariable String id) throws ActException {
        try {
            dimensionSetAPI.deleteDimensionSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 获取所有维度
     *
     * @des 获取所有维度
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName( ) throws ActException {
        try {
            List<String> dimensionSetVOList = dimensionSetAPI.listName( ) ;
            return ActResult.initialize(dimensionSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}