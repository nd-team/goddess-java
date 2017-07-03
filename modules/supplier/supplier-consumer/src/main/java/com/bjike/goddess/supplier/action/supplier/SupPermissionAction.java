package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.supplier.api.SupPermissionAPI;
import com.bjike.goddess.supplier.bo.SupOperateBO;
import com.bjike.goddess.supplier.bo.SupPermissionBO;
import com.bjike.goddess.supplier.dto.SupPermissionDTO;
import com.bjike.goddess.supplier.to.SupPermissionTO;
import com.bjike.goddess.supplier.vo.SupOperateVO;
import com.bjike.goddess.supplier.vo.SupPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商权限配置
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 10:43 ]
 * @Description: [ 供应商权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("suppermission")
public class SupPermissionAction {

    @Autowired
    private SupPermissionAPI cusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 供应商权限信息dto
     * @des 获取所有供应商权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SupPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = cusPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个供应商权限
     *
     * @param id 供应商权限信息id
     * @return class SupPermissionVO
     * @des 根据id获取所有供应商权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SupPermissionBO temp = cusPermissionAPI.getOneById(id);
            SupPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp, SupPermissionVO.class);
            if (null != temp) {
                List<SupOperateBO> cboList = temp.getSupOperateBOs();
                List<SupOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, SupOperateVO.class);
                }
                cusPermissionVOList.setSupOperateVO(cvoList);
            }
            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类型下对象
     *
     * @param id 供应商权限信息id
     * @return class SupPermissionVO
     * @des 根据id获取所有供应商权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(cusPermissionAPI.listOperateById(id), OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 供应商权限列表
     *
     * @param cusPermissionDTO 供应商权限信息dto
     * @param request          前端过滤参数
     * @return class SupPermissionVO
     * @des 获取所有供应商权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSupPermission(SupPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SupPermissionBO> boList = cusPermissionAPI.list(cusPermissionDTO);
            List<SupPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                SupPermissionVO temp = BeanTransform.copyProperties(str, SupPermissionVO.class, request);
                List<SupOperateVO> covo = new ArrayList<>();
                if (null != str.getSupOperateBOs()) {
                    covo = BeanTransform.copyProperties(str.getSupOperateBOs(), SupOperateVO.class);
                }
                temp.setSupOperateVO(covo);
                cusPermissionVOList.add(temp);
            });

            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑供应商权限
     *
     * @param cusPermissionTO 供应商权限基本信息数据bo
     * @return class SupPermissionVO
     * @des 编辑供应商权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit/{id}")
    public Result editSupPermission(@Validated SupPermissionTO cusPermissionTO, BindingResult result) throws ActException {
        try {
            SupPermissionBO cusPermissionBO1 = cusPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, SupPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}