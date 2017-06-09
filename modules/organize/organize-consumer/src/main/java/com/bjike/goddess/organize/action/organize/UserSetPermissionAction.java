package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.UserSetOperateBO;
import com.bjike.goddess.organize.bo.UserSetPermissionBO;
import com.bjike.goddess.organize.dto.UserSetPermissionDTO;
import com.bjike.goddess.organize.to.UserSetPermissionTO;
import com.bjike.goddess.organize.vo.UserSetOperateVO;
import com.bjike.goddess.organize.vo.UserSetPermissionVO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 总设置权限配置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 总设置权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("usersetpermission")
public class UserSetPermissionAction {

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 操作总设置的操作权限
     *
     * @des 操作总设置的操作权限
     * @version v1
     */
    @GetMapping("v1/adminPermission")
    public Result adminPermission( ) throws ActException {
        try {
            UserBO userBO = userSetPermissionAPI.adminPermission( );
            String userName = userBO.getUsername();
            if( !"admin".equals( userName.toLowerCase())){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 总设置权限信息dto
     * @des 获取所有总设置权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(UserSetPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = userSetPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个总设置权限
     *
     * @param id 总设置权限信息id
     * @return class CusPermissionVO
     * @des 根据id获取所有总设置权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            UserSetPermissionBO temp = userSetPermissionAPI.getOneById(id);
            UserSetPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp , UserSetPermissionVO.class);
            if( null != temp   ){
                List<UserSetOperateBO> cboList  = temp.getCusOperateBO();
                List<UserSetOperateVO> cvoList = new ArrayList<>();
                if( cboList != null && cboList.size()>0 ){
                    cvoList = BeanTransform.copyProperties(
                            cboList , UserSetOperateVO.class);
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
     * @param id 总设置权限信息id
     * @return class CusPermissionVO
     * @des 根据id获取所有总设置权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(userSetPermissionAPI.listOperateById(id),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总设置权限列表
     *
     * @param cusPermissionDTO 总设置权限信息dto
     * @param request      前端过滤参数
     * @return class CusPermissionVO
     * @des 获取所有总设置权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(UserSetPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<UserSetPermissionBO> boList = userSetPermissionAPI.list(cusPermissionDTO);
            List<UserSetPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str->{
                UserSetPermissionVO temp = BeanTransform.copyProperties( str, UserSetPermissionVO.class, request);
                List<UserSetOperateVO> covo = new ArrayList<>();
                if(null != str.getCusOperateBO()){
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(),UserSetOperateVO.class);
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
     * 编辑总设置权限
     *
     * @param cusPermissionTO 总设置权限基本信息数据bo
     * @return class CusPermissionVO
     * @des 编辑总设置权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated UserSetPermissionTO cusPermissionTO) throws ActException {
        try {
            UserSetPermissionBO cusPermissionBO1 = userSetPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, UserSetPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}