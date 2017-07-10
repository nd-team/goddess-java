package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.contacts.api.ContactPermissionAPI;
import com.bjike.goddess.contacts.bo.ContactOperateBO;
import com.bjike.goddess.contacts.bo.ContactPermissionBO;
import com.bjike.goddess.contacts.dto.ContactPermissionDTO;
import com.bjike.goddess.contacts.to.ContactPermissionTO;
import com.bjike.goddess.contacts.vo.ContactOperateVO;
import com.bjike.goddess.contacts.vo.ContactPermissionVO;
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
@RequestMapping("cuspermission")
public class ContactPermissionAction {

    @Autowired
    private ContactPermissionAPI contactPermissionAPI;

    /**
     * 列表总条数
     *
     * @param contactPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContactPermissionDTO contactPermissionDTO) throws ActException {
        try {
            Long count = contactPermissionAPI.countPermission(contactPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class ContactPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ContactPermissionBO temp = contactPermissionAPI.getOneById(id);
            ContactPermissionVO contactPermissionVOList = BeanTransform.copyProperties(
                    temp , ContactPermissionVO.class);
            if( null != temp   ){
                List<ContactOperateBO> cboList  = temp.getCusOperateBO();
                List<ContactOperateVO> cvoList = new ArrayList<>();
                if( cboList != null && cboList.size()>0 ){
                    cvoList = BeanTransform.copyProperties(
                            cboList , ContactOperateVO.class);
                }
                contactPermissionVOList.setCusOperateVO( cvoList );
            }
            return ActResult.initialize(contactPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取类型下对象
     *
     * @param id 客户权限信息id
     * @return class ContactPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(contactPermissionAPI.listOperateById(id),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param contactPermissionDTO 客户权限信息dto
     * @param request      前端过滤参数
     * @return class ContactPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListContactPermission(ContactPermissionDTO contactPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ContactPermissionBO> boList = contactPermissionAPI.list(contactPermissionDTO);
            List<ContactPermissionVO> contactPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str->{
                ContactPermissionVO temp = BeanTransform.copyProperties( str, ContactPermissionVO.class, request);
                List<ContactOperateVO> covo = new ArrayList<>();
                if(null != str.getCusOperateBO()){
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(),ContactOperateVO.class);
                }
                temp.setCusOperateVO( covo );
                contactPermissionVOList.add( temp );
            });

            return ActResult.initialize(contactPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * 编辑客户权限
     *
     * @param contactPermissionTO 客户权限基本信息数据bo
     * @return class ContactPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editContactPermission(@Validated ContactPermissionTO contactPermissionTO) throws ActException {
        try {
            ContactPermissionBO contactPermissionBO1 = contactPermissionAPI.edit(contactPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(contactPermissionBO1, ContactPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}