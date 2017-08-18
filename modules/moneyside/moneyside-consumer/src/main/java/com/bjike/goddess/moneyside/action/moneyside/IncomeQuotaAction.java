package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.IncomeQuotaAPI;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.bo.IncomeQuotaBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.dto.IncomeQuotaDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
import com.bjike.goddess.moneyside.to.IncomeQuotaTO;
import com.bjike.goddess.moneyside.vo.IncomeDistributionVO;
import com.bjike.goddess.moneyside.vo.IncomeQuotaVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收益分配额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:28 ]
 * @Description: [ 收益分配额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("incomequota")
public class IncomeQuotaAction {
    @Autowired
    private IncomeQuotaAPI incomeQuotaAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = incomeQuotaAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 收益分配额列表总条数
     *
     * @param incomeQuotaDTO 收益分配额dto
     * @des 获取所有收益分配额总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IncomeQuotaDTO incomeQuotaDTO) throws ActException {
        try {
            Long count = incomeQuotaAPI.countIncomeQuota(incomeQuotaDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收益分配额
     *
     * @param id
     * @return class IncomeQuotaVO
     * @des 获取一个收益分配额
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            IncomeQuotaBO incomeQuotaBO = incomeQuotaAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(incomeQuotaBO, IncomeQuotaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收益分配额列表
     *
     * @param incomeQuotaDTO 收益分配额dto
     * @return class IncomeQuotaVO
     * @des 获取所有收益分配额
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IncomeQuotaDTO incomeQuotaDTO, HttpServletRequest request) throws ActException {
        try {
            List<IncomeQuotaVO> incomeQuotaVOS = BeanTransform.copyProperties
                    (incomeQuotaAPI.findListIncomeQuota(incomeQuotaDTO), IncomeQuotaVO.class, request);
            return ActResult.initialize(incomeQuotaVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加收益分配额
     *
     * @param incomeQuotaTO 收益分配额数据to
     * @return class IncomeQuotaVO
     * @des 添加收益分配额
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) IncomeQuotaTO incomeQuotaTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeQuotaBO incomeQuotaBO = incomeQuotaAPI.insertIncomeQuota(incomeQuotaTO);
            return ActResult.initialize(incomeQuotaBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑收益分配额
     *
     * @param incomeQuotaTO 收益分配额数据to
     * @return class IncomeQuotaVO
     * @des 编辑收益分配额
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IncomeQuotaTO incomeQuotaTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeQuotaBO incomeQuotaBO = incomeQuotaAPI.editIncomeQuota(incomeQuotaTO);
            return ActResult.initialize(incomeQuotaBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除收益分配额
     *
     * @param id 用户id
     * @des 根据用户id删除收益分配额记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            incomeQuotaAPI.removeIncomeQuota(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}