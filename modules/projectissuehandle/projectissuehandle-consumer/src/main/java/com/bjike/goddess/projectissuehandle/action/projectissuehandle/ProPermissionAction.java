package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.projectissuehandle.api.ProPermissionAPI;
import com.bjike.goddess.projectissuehandle.bo.ProOperateBO;
import com.bjike.goddess.projectissuehandle.bo.ProPermissionBO;
import com.bjike.goddess.projectissuehandle.dto.ProPermissionDTO;
import com.bjike.goddess.projectissuehandle.to.ProPermissionTO;
import com.bjike.goddess.projectissuehandle.vo.ProOperateVO;
import com.bjike.goddess.projectissuehandle.vo.ProPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题权限配置
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-26 05:43 ]
 * @Description: [ 问题权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("propermission")
public class ProPermissionAction {

    @Autowired
    private ProPermissionAPI proPermissionAPI;

    /**
     * 列表总条数
     *
     * @param proPermissionDTO 问题权限信息dto
     * @des 获取所有问题权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProPermissionDTO proPermissionDTO) throws ActException {
        try {
            Long count = proPermissionAPI.countPermission(proPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个问题权限
     *
     * @param id 问题权限信息id
     * @return class ProPermissionVO
     * @des 根据id获取所有问题权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ProPermissionBO temp = proPermissionAPI.getOneById(id);
            ProPermissionVO proPermissionVOList = BeanTransform.copyProperties(
                    temp, ProPermissionVO.class);
            if (null != temp) {
                List<ProOperateBO> cboList = temp.getProOperateBO();
                List<ProOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, ProOperateVO.class);
                }
                proPermissionVOList.setProOperateVO(cvoList);
            }
            return ActResult.initialize(proPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类型下对象
     *
     * @param id 问题权限信息id
     * @return class ProPermissionVO
     * @des 根据id获取所有问题权限获取类型下对象
     * @version v1
     */
    @GetMapping("v1/listOperateById/{id}")
    public Result listOperateById(@PathVariable String id) throws ActException {
        try {
            List<OpinionVO> list = BeanTransform.copyProperties(proPermissionAPI.listOperateById(id), OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 问题权限列表
     *
     * @param proPermissionDTO 问题权限信息dto
     * @param request          前端过滤参数
     * @return class ProPermissionVO
     * @des 获取所有问题权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProPermission(ProPermissionDTO proPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProPermissionBO> boList = proPermissionAPI.list(proPermissionDTO);
            List<ProPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                ProPermissionVO temp = BeanTransform.copyProperties(str, ProPermissionVO.class, request);
                List<ProOperateVO> covo = new ArrayList<>();
                if (null != str.getProOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getProOperateBO(), ProOperateVO.class);
                }
                temp.setProOperateVO(covo);
                cusPermissionVOList.add(temp);
            });

            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑问题权限
     *
     * @param proPermissionTO 问题权限基本信息数据bo
     * @return class ProPermissionVO
     * @des 编辑问题权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProPermission(@Validated ProPermissionTO proPermissionTO) throws ActException {
        try {
            ProPermissionBO proPermissionBO = proPermissionAPI.edit(proPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(proPermissionBO, ProPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}