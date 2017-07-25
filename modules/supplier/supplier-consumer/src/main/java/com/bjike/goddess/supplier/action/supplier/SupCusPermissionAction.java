package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.supplier.api.SupCusPermissionAPI;
import com.bjike.goddess.supplier.bo.SupCusOperateBO;
import com.bjike.goddess.supplier.bo.SupCusPermissionBO;
import com.bjike.goddess.supplier.dto.SupCusPermissionDTO;
import com.bjike.goddess.supplier.to.SupCusPermissionTO;
import com.bjike.goddess.supplier.vo.SupCusOperateVO;
import com.bjike.goddess.supplier.vo.SupCusPermissionVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户权限配置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supcuspermission")
public class SupCusPermissionAction {

    @Autowired
    private SupCusPermissionAPI supCusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param supCusPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SupCusPermissionDTO supCusPermissionDTO) throws ActException {
        try {
            Long count = supCusPermissionAPI.countPermission(supCusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class SupCusPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SupCusPermissionBO temp = supCusPermissionAPI.getOneById(id);
            SupCusPermissionVO supCusPermissionVOList = BeanTransform.copyProperties(
                    temp , SupCusPermissionVO.class);
            if( null != temp   ){
                List<SupCusOperateBO> cboList  = temp.getSupCusOperateBO();
                List<SupCusOperateVO> cvoList = new ArrayList<>();
                if( cboList != null && cboList.size()>0 ){
                    cvoList = BeanTransform.copyProperties(
                            cboList , SupCusOperateVO.class);
                }
                supCusPermissionVOList.setCusOperateVO( cvoList );
            }
            return ActResult.initialize(supCusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取类型下对象
     *
     * @param id 客户权限信息id
     * @return class SupCusPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(supCusPermissionAPI.listOperateById(id),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param supCusPermissionDTO 客户权限信息dto
     * @param request      前端过滤参数
     * @return class SupCusPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(SupCusPermissionDTO supCusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SupCusPermissionBO> boList = supCusPermissionAPI.list(supCusPermissionDTO);
            List<SupCusPermissionVO> supCusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str->{
                SupCusPermissionVO temp = BeanTransform.copyProperties( str, SupCusPermissionVO.class, request);
                List<SupCusOperateVO> covo = new ArrayList<>();
                if(null != str.getSupCusOperateBO()){
                    covo = BeanTransform.copyProperties(str.getSupCusOperateBO(),SupCusOperateVO.class);
                }
                temp.setCusOperateVO( covo );
                supCusPermissionVOList.add( temp );
            });

            return ActResult.initialize(supCusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 编辑客户权限
     *
     * @param supCusPermissionTO 客户权限基本信息数据bo
     * @return class SupCusPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated SupCusPermissionTO supCusPermissionTO) throws ActException {
        try {
            SupCusPermissionBO supCusPermissionBO1 = supCusPermissionAPI.edit(supCusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(supCusPermissionBO1, SupCusPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}