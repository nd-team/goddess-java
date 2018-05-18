package com.bjike.goddess.businessprojectmanage.action.businessprojectmanage;

import com.bjike.goddess.businessprojectmanage.api.CusPermissionAPI;
import com.bjike.goddess.businessprojectmanage.bo.CusOperateBO;
import com.bjike.goddess.businessprojectmanage.bo.CusPermissionBO;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionDTO;
import com.bjike.goddess.businessprojectmanage.excel.SonPermissionObject;
import com.bjike.goddess.businessprojectmanage.to.CusPermissionTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.vo.CusOperateVO;
import com.bjike.goddess.businessprojectmanage.vo.CusPermissionVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户权限设置
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cuspermission")
public class CusPermissionAction {

    @Autowired
    private CusPermissionAPI cusPermissionAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 项目实施进度权限列表总条数
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/bus/count")
    public Result busCountPermission(CusPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = cusPermissionAPI.busCountPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施进度权限列表
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @param request          前端过滤参数
     * @return class CusPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/bus/list")
    public Result busList(CusPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CusPermissionBO> boList = cusPermissionAPI.busList(cusPermissionDTO);
            List<CusPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                CusPermissionVO temp = BeanTransform.copyProperties(str, CusPermissionVO.class, request);
                List<CusOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), CusOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                cusPermissionVOList.add(temp);
            });

            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CusPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = cusPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限信息id
     * @return class CusPermissionVO
     * @des 根据id获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CusPermissionBO temp = cusPermissionAPI.getOneById(id);
            CusPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp, CusPermissionVO.class);
            if (null != temp) {
                List<CusOperateBO> cboList = temp.getCusOperateBO();
                List<CusOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, CusOperateVO.class);
                }
                cusPermissionVOList.setCusOperateVO(cvoList);
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
     * @return class CusPermissionVO
     * @des 根据id获取所有客户权限获取类型下对象
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
     * 客户权限列表
     *
     * @param cusPermissionDTO 客户权限信息dto
     * @param request          前端过滤参数
     * @return class CusPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(CusPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CusPermissionBO> boList = cusPermissionAPI.list(cusPermissionDTO);
            List<CusPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                CusPermissionVO temp = BeanTransform.copyProperties(str, CusPermissionVO.class, request);
                List<CusOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), CusOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                cusPermissionVOList.add(temp);
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
     * @return class CusPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated CusPermissionTO cusPermissionTO) throws ActException {
        try {
            CusPermissionBO cusPermissionBO1 = cusPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, CusPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}