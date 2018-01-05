package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.LendPermissionAPI;
import com.bjike.goddess.lendreimbursement.bo.LendOperateBO;
import com.bjike.goddess.lendreimbursement.bo.LendPermissionBO;
import com.bjike.goddess.lendreimbursement.dto.LendPermissionDTO;
import com.bjike.goddess.lendreimbursement.to.LendPermissionTO;
import com.bjike.goddess.lendreimbursement.vo.LendOperateVO;
import com.bjike.goddess.lendreimbursement.vo.LendPermissionVO;
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
@RequestMapping("lendPermission")
public class LendPermissionAct {

    @Autowired
    private LendPermissionAPI lendPermissionAPI;

    /**
     * 列表总条数
     *
     * @param dto 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LendPermissionDTO dto) throws ActException {
        try {
            Long count = lendPermissionAPI.countPermission(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class LendPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            LendPermissionBO temp = lendPermissionAPI.getOneById(id);
            LendPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp , LendPermissionVO.class);
            if( null != temp   ){
                List<LendOperateBO> cboList  = temp.getCusOperateBO();
                List<LendOperateVO> cvoList = new ArrayList<>();
                if( cboList != null && cboList.size()>0 ){
                    cvoList = BeanTransform.copyProperties(
                            cboList , LendOperateVO.class);
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
     * @return class LendPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(lendPermissionAPI.listOperateById(id),OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param dto 客户权限信息dto
     * @param request      前端过滤参数
     * @return class LendPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(LendPermissionDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<LendPermissionBO> boList = lendPermissionAPI.list(dto);
            List<LendPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str->{
                LendPermissionVO temp = BeanTransform.copyProperties( str, LendPermissionVO.class, request);
                List<LendOperateVO> covo = new ArrayList<>();
                if(null != str.getCusOperateBO()){
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(),LendOperateVO.class);
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
     * @param to 客户权限基本信息数据bo
     * @return class LendPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated LendPermissionTO to) throws ActException {
        try {
            LendPermissionBO cusPermissionBO1 = lendPermissionAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, LendPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}