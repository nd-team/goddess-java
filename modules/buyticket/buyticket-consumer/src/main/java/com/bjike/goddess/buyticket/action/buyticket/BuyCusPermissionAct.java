package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.buyticket.api.BuyCusPermissionAPI;
import com.bjike.goddess.buyticket.bo.BuyCusOperateBO;
import com.bjike.goddess.buyticket.bo.BuyCusPermissionBO;
import com.bjike.goddess.buyticket.dto.BuyCusPermissionDTO;
import com.bjike.goddess.buyticket.to.BuyCusPermissionTO;
import com.bjike.goddess.buyticket.vo.BuyCusOperateVO;
import com.bjike.goddess.buyticket.vo.BuyCusPermissionVO;
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
@RequestMapping("buycuspermission")
public class BuyCusPermissionAct {

    @Autowired
    private BuyCusPermissionAPI buycusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param dto 客户权限信息dto
     * @des 获取所有客户权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BuyCusPermissionDTO dto) throws ActException {
        try {
            Long count = buycusPermissionAPI.countPermission(dto);
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
            BuyCusPermissionBO temp = buycusPermissionAPI.getOneById(id);
            BuyCusPermissionVO cusPermissionVOList = BeanTransform.copyProperties(
                    temp, BuyCusPermissionVO.class);
            if (null != temp) {
                List<BuyCusOperateBO> cboList = temp.getCusOperateBO();
                List<BuyCusOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, BuyCusOperateVO.class);
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
            List<OpinionVO> list = BeanTransform.copyProperties(buycusPermissionAPI.listOperateById(id), OpinionVO.class);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户权限列表
     *
     * @param dto     客户权限信息dto
     * @param request 前端过滤参数
     * @return class CusPermissionVO
     * @des 获取所有客户权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(BuyCusPermissionDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<BuyCusPermissionBO> boList = buycusPermissionAPI.list(dto);
            List<BuyCusPermissionVO> cusPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                BuyCusPermissionVO temp = BeanTransform.copyProperties(str, BuyCusPermissionVO.class, request);
                List<BuyCusOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), BuyCusOperateVO.class);
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
     * @param to 客户权限基本信息数据bo
     * @return class CusPermissionVO
     * @des 编辑客户权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated BuyCusPermissionTO to) throws ActException {
        try {
            BuyCusPermissionBO cusPermissionBO1 = buycusPermissionAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, BuyCusPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}