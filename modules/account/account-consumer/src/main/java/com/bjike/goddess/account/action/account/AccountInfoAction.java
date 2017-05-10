package com.bjike.goddess.account.action.account;

import com.bjike.goddess.account.api.AccountInfoAPI;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.vo.AccountInfoVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 明细账信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("account/accountinfo")
public class AccountInfoAction {
    @Autowired
    private AccountInfoAPI accountInfoAPI;
    /**
     * 获取明细账信息
     *
     * @param accountInfoDTO 明细账信息dto
     * @return class AccountInfoVO
     * @des 获取所有明细账信息
     * @version v1
     */
    @GetMapping("v1/listAccountInfo")
    public Result findListAccountInfo(AccountInfoDTO accountInfoDTO) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties
                    (accountInfoAPI.findListAccountInfo(accountInfoDTO),AccountInfoVO.class);
            return ActResult.initialize(accountInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class AccountInfoVO
     * @des 添加明细账信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addAccountInfo(AccountInfoTO accountInfoTO) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.insertAccountInfo(accountInfoTO);
            return ActResult.initialize(accountInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class AccountInfoVO
     * @des 编辑明细账信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editAccountInfo(AccountInfoTO accountInfoTO) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.editAccountInfo(accountInfoTO);
            return ActResult.initialize(accountInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除明细账信息
     *
     * @param id 用户id
     * @des 根据用户id删除明细账信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeAccountInfo(@PathVariable String id) throws ActException {
        try {
            accountInfoAPI.removeAccountInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总明细账信息
     *
     * @des 明细账信息管理
     * @return  class AccountInfoVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collectAccountInfo (String area,String projectName,String projectGroup) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties(
                    accountInfoAPI.collectAccountInfo(area, projectName, projectGroup),AccountInfoVO.class,true);
            return ActResult.initialize(accountInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}