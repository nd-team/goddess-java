package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MarPermissionAPI;
import com.bjike.goddess.marketdevelopment.bo.MarOperateBO;
import com.bjike.goddess.marketdevelopment.bo.MarPermissionBO;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionDTO;
import com.bjike.goddess.marketdevelopment.to.MarPermissionTO;
import com.bjike.goddess.marketdevelopment.vo.MarOperateVO;
import com.bjike.goddess.marketdevelopment.vo.MarPermissionVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 市场计划进度管理权限配置
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marpermission")
public class MarPermissionAction {

    @Autowired
    private MarPermissionAPI cusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 市场计划进度管理权限信息dto
     * @des 获取所有市场计划进度管理权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MarPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = cusPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个市场计划进度管理权限
     *
     * @param id 市场计划进度管理权限信息id
     * @return class MarPermissionVO
     * @des 根据id获取所有市场计划进度管理权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            MarPermissionBO temp = cusPermissionAPI.getOneById(id);
            MarPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp, MarPermissionVO.class);
            if (null != temp) {
                List<MarOperateBO> cboList = temp.getMarOperateBOs();
                List<MarOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, MarOperateVO.class);
                }
                cusPermissionVOList.setMarOperateVO(cvoList);
            }
            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类型下对象
     *
     * @param id 市场计划进度管理权限信息id
     * @return class MarPermissionVO
     * @des 根据id获取所有市场计划进度管理权限获取类型下对象
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
     * 市场计划进度管理权限列表
     *
     * @param cusPermissionDTO 市场计划进度管理权限信息dto
     * @param request          前端过滤参数
     * @return class MarPermissionVO
     * @des 获取所有市场计划进度管理权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListMarPermission(MarPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MarPermissionBO> boList = cusPermissionAPI.list(cusPermissionDTO);
            List<MarPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                MarPermissionVO temp = BeanTransform.copyProperties(str, MarPermissionVO.class, request);
                List<MarOperateVO> covo = new ArrayList<>();
                if (null != str.getMarOperateBOs()) {
                    covo = BeanTransform.copyProperties(str.getMarOperateBOs(), MarOperateVO.class);
                }
                temp.setMarOperateVO(covo);
                cusPermissionVOList.add(temp);
            });

            return ActResult.initialize(cusPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑市场计划进度管理权限
     *
     * @param cusPermissionTO 市场计划进度管理权限基本信息数据bo
     * @return class MarPermissionVO
     * @des 编辑市场计划进度管理权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit/{id}")
    public Result editMarPermission(@Validated MarPermissionTO cusPermissionTO, BindingResult result) throws ActException {
        try {
            MarPermissionBO cusPermissionBO1 = cusPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, MarPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}