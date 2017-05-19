package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.AccountInfoManagementAPI;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;
import com.bjike.goddess.foreigntax.vo.AccountInfoManagementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 外账资料管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountinfomanagement")
public class AccountInfoManagementAction {
    @Autowired
    private AccountInfoManagementAPI accountInfoManagementAPI;
    /**
     * 外账资料管理列表总条数
     *
     * @param accountInfoManagementDTO 外账资料管理dto
     * @des 获取所有外账资料管理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountInfoManagementDTO accountInfoManagementDTO) throws ActException {
        try {
            Long count = accountInfoManagementAPI.countAccountInfoManagement(accountInfoManagementDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 一个外账资料管理
     *
     * @param id
     * @return class AccountInfoManagementVO
     * @des 获取一个外账资料管理
     * @version v1
     */
    @GetMapping("v1/account/{id}")
    public Result account(@PathVariable String id) throws ActException {
        try {
            AccountInfoManagementBO accountInfoManagementBO = accountInfoManagementAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(accountInfoManagementBO, AccountInfoManagementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 外账资料管理列表
     *
     * @param accountInfoManagementDTO 外账资料管理dto
     * @return class AccountInfoManagementVO
     * @des 获取所有外账资料管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AccountInfoManagementDTO accountInfoManagementDTO, HttpServletRequest request) throws ActException {
        try {
            List<AccountInfoManagementVO> accountInfoManagementVOS = BeanTransform.copyProperties
                    (accountInfoManagementAPI.findListAccountInfoManagement(accountInfoManagementDTO),AccountInfoManagementVO.class,request);
            return ActResult.initialize(accountInfoManagementVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加外账资料管理
     *
     * @param accountInfoManagementTO 外账资料管理数据to
     * @return class AccountInfoManagementVO
     * @des 添加外账资料管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) AccountInfoManagementTO accountInfoManagementTO, BindingResult bindingResult) throws ActException {
        try {
            AccountInfoManagementBO accountInfoManagementBO = accountInfoManagementAPI.insertAccountInfoManagement(accountInfoManagementTO);
            return ActResult.initialize(accountInfoManagementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑外账资料管理
     *
     * @param accountInfoManagementTO 外账资料管理数据to
     * @return class AccountInfoManagementVO
     * @des 编辑外账资料管理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) AccountInfoManagementTO accountInfoManagementTO,BindingResult bindingResult) throws ActException {
        try {
            AccountInfoManagementBO accountInfoManagementBO = accountInfoManagementAPI.editAccountInfoManagement(accountInfoManagementTO);
            return ActResult.initialize(accountInfoManagementBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除外账资料管理
     *
     * @param id 用户id
     * @des 根据用户id删除外账资料管理记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            accountInfoManagementAPI.removeAccountInfoManagement(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            accountInfoManagementAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            accountInfoManagementAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

}