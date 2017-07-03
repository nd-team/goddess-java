package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.financeinit.service.AccountSer;
import com.bjike.goddess.financeinit.to.AccountTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 账户来源
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("account")
public class AccountAction {

    @Autowired
    private AccountAPI accountAPI;


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

            Boolean isHasPermission = accountAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param customerBaseInfoDTO 账户来源信息dto
     * @des 获取所有账户来源信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = accountAPI.countAccount(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个账户来源
     *
     * @param id 项目账户来源信息id
     * @des 根据id获取项目账户来源信息
     * @return  class AccountVO
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            AccountVO projectCarryVO = BeanTransform.copyProperties(
                    accountAPI.getOneById(id), AccountVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 账户来源列表
     *
     * @param accountDTO 账户来源信息dto
     * @return class AccountVO
     * @des 获取所有账户来源信息
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(AccountDTO accountDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<AccountVO> accountVOList = BeanTransform.copyProperties(
                    accountAPI.listAccount(accountDTO), AccountVO.class , request);
            return ActResult.initialize(accountVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加账户来源
     *
     * @param accountTO 账户来源基本信息数据to
     * @return class AccountVO
     * @des 添加账户来源
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated AccountTO accountTO, BindingResult bindingResult) throws ActException {
        try {
            AccountBO accountBO1 = accountAPI.addAccount(accountTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountBO1, AccountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑账户来源
     *
     * @param accountTO 账户来源基本信息数据bo
     * @return class AccountVO
     * @des 添加账户来源
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated AccountTO accountTO,BindingResult bindingResult) throws ActException {
        try {
            AccountBO accountBO1 = accountAPI.editAccount(accountTO);
            return ActResult.initialize(BeanTransform.copyProperties(accountBO1, AccountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除账户来源信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            accountAPI.deleteAccount(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取二级级别
     *
     * @param categoryDTO 类别信息dto
     * @des 获取所有该级别类别下的所有二级级别
     * @version v1
     */
    @GetMapping("v1/listSecondCategory")
    public Result listSecondCategory(@Validated({AccountDTO.TESTFirst.class}) AccountDTO categoryDTO, BindingResult bindingResult) throws ActException {
        try {
            List<String> list = accountAPI.getSecondSubject(categoryDTO);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取三级级别
     *
     * @param categoryDTO 类别信息dto
     * @des 获取所有该级别类别下的所有三级级别
     * @version v1
     */
    @GetMapping("v1/listThirdCategory")
    public Result listThirdCategory(@Validated({AccountDTO.TESTSearchSecond.class}) AccountDTO categoryDTO, BindingResult bindingResult) throws ActException {
        try {
            List<String> list = accountAPI.getThirdSubject(categoryDTO);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}