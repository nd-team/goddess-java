package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.archive.api.RotainCusPermissionAPI;
import com.bjike.goddess.archive.bo.CusOperateBO;
import com.bjike.goddess.archive.bo.RotainCusPermissionBO;
import com.bjike.goddess.archive.dto.RotainCusPermissionDTO;
import com.bjike.goddess.archive.to.RotainCusPermissionTO;
import com.bjike.goddess.archive.vo.CusOperateVO;
import com.bjike.goddess.archive.vo.RotainCusPermissionVO;
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
@RequestMapping("rotaincuspermission")
public class RotainCusPermissionAction {

    @Autowired
    private RotainCusPermissionAPI rotainCusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RotainCusPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = rotainCusPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class RotainCusPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            RotainCusPermissionBO temp = rotainCusPermissionAPI.getOneById(id);
            RotainCusPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp , RotainCusPermissionVO.class);
            if( null != temp   ){
                List<CusOperateBO> cboList  = temp.getCusOperateBO();
                List<CusOperateVO> cvoList = new ArrayList<>();
                if( cboList != null && cboList.size()>0 ){
                    cvoList = BeanTransform.copyProperties(
                            cboList , CusOperateVO.class);
                }
                cusPermissionVOList.setCusOperateVO( cvoList );
            }
            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取类型下对象
     *
     * @param id 客户权限信息id
     * @return class RotainCusPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(rotainCusPermissionAPI.listOperateById(id),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @param request      前端过滤参数
     * @return class RotainCusPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(RotainCusPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<RotainCusPermissionBO> boList = rotainCusPermissionAPI.list(cusPermissionDTO);
            List<RotainCusPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str->{
                RotainCusPermissionVO temp = BeanTransform.copyProperties( str, RotainCusPermissionVO.class, request);
                List<CusOperateVO> covo = new ArrayList<>();
                if(null != str.getCusOperateBO()){
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(),CusOperateVO.class);
                }
                temp.setCusOperateVO( covo );
                cusPermissionVOList.add( temp );
            });

            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 编辑客户权限
     *
     * @param cusPermissionTO 客户权限基本信息数据bo
     * @return class RotainCusPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated RotainCusPermissionTO cusPermissionTO) throws ActException {
        try {
            RotainCusPermissionBO cusPermissionBO1 = rotainCusPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, RotainCusPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}