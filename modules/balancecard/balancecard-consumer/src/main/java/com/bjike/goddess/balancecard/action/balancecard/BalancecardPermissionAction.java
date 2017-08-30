package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.BalancecardPermissionAPI;
import com.bjike.goddess.balancecard.bo.BalancecardOperateBO;
import com.bjike.goddess.balancecard.bo.BalancecardPermissionBO;
import com.bjike.goddess.balancecard.dto.BalancecardPermissionDTO;
import com.bjike.goddess.balancecard.to.BalancecardPermissionTO;
import com.bjike.goddess.balancecard.vo.BalancecardOperateVO;
import com.bjike.goddess.balancecard.vo.BalancecardPermissionVO;
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
 * 平衡计分卡权限配置
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 平衡计分卡权限配置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("balancecardpermission")
public class BalancecardPermissionAction {

    @Autowired
    private BalancecardPermissionAPI cusPermissionAPI;

    /**
     * 列表总条数
     *
     * @param cusPermissionDTO 平衡计分卡权限信息dto
     * @des 获取所有平衡计分卡权限信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BalancecardPermissionDTO cusPermissionDTO) throws ActException {
        try {
            Long count = cusPermissionAPI.countPermission(cusPermissionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个平衡计分卡权限
     *
     * @param id 平衡计分卡权限信息id
     * @return class BalancecardPermissionVO
     * @des 根据id获取所有平衡计分卡权限信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            BalancecardPermissionBO temp = cusPermissionAPI.getOneById(id);
            BalancecardPermissionVO BalancecardPermissionVOList = BeanTransform.copyProperties(
                    temp, BalancecardPermissionVO.class);
            if (null != temp) {
                List<BalancecardOperateBO> cboList = temp.getCusOperateBO();
                List<BalancecardOperateVO> cvoList = new ArrayList<>();
                if (cboList != null && cboList.size() > 0) {
                    cvoList = BeanTransform.copyProperties(
                            cboList, BalancecardOperateVO.class);
                }
                BalancecardPermissionVOList.setCusOperateVO(cvoList);
            }
            return ActResult.initialize(BalancecardPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取类型下对象
     *
     * @param id 平衡计分卡权限信息id
     * @return class BalancecardPermissionVO
     * @des 根据id获取所有平衡计分卡权限获取类型下对象
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
     * 平衡计分卡权限列表
     *
     * @param cusPermissionDTO 平衡计分卡权限信息dto
     * @param request          前端过滤参数
     * @return class BalancecardPermissionVO
     * @des 获取所有平衡计分卡权限信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCusPermission(BalancecardPermissionDTO cusPermissionDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<BalancecardPermissionBO> boList = cusPermissionAPI.list(cusPermissionDTO);
            List<BalancecardPermissionVO> BalancecardPermissionVOList = new ArrayList<>();
            boList.stream().forEach(str -> {
                BalancecardPermissionVO temp = BeanTransform.copyProperties(str, BalancecardPermissionVO.class, request);
                List<BalancecardOperateVO> covo = new ArrayList<>();
                if (null != str.getCusOperateBO()) {
                    covo = BeanTransform.copyProperties(str.getCusOperateBO(), BalancecardOperateVO.class);
                }
                temp.setCusOperateVO(covo);
                BalancecardPermissionVOList.add(temp);
            });

            return ActResult.initialize(BalancecardPermissionVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑平衡计分卡权限
     *
     * @param cusPermissionTO 平衡计分卡权限基本信息数据bo
     * @return class BalancecardPermissionVO
     * @des 编辑平衡计分卡权限
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusPermission(@Validated BalancecardPermissionTO cusPermissionTO) throws ActException {
        try {
            BalancecardPermissionBO cusPermissionBO1 = cusPermissionAPI.edit(cusPermissionTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusPermissionBO1, BalancecardPermissionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}