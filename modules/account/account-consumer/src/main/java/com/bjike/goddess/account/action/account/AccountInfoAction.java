package com.bjike.goddess.account.action.account;

import com.bjike.goddess.account.api.AccountInfoAPI;
import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.vo.AccountCollectVO;
import com.bjike.goddess.account.vo.AccountInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("accountinfo")
public class AccountInfoAction {
    @Autowired
    private AccountInfoAPI accountInfoAPI;

    /**
     * 明细账信息列表总条数
     *
     * @param accountInfoDTO 明细账信息dto
     * @des 获取所有明细账信息
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AccountInfoDTO accountInfoDTO) throws ActException {
        try {
            Long count = accountInfoAPI.countAccountInfo(accountInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个明细账信息
     *
     * @param id
     * @return class AccountInfoVO
     * @des 获取一个明细账信息
     * @version v1
     */
    @GetMapping("v1/account/{id}")
    public Result account(@PathVariable String id) throws ActException {
        try {
            AccountInfoBO accountInfoBO = accountInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(accountInfoBO, AccountInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 明细账信息列表
     *
     * @param accountInfoDTO 明细账信息dto
     * @return class AccountInfoVO
     * @des 获取所有明细账信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(AccountInfoDTO accountInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties
                    (accountInfoAPI.findListAccountInfo(accountInfoDTO), AccountInfoVO.class, request);
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
    public Result add(@Validated(ADD.class) AccountInfoTO accountInfoTO, BindingResult bindingResult) throws ActException {
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
    public Result edit(@Validated(EDIT.class) AccountInfoTO accountInfoTO, BindingResult bindingResult) throws ActException {
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
    public Result delete(@PathVariable String id) throws ActException {
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
     * @param areas 地区
     * @return class AccountCollectVO
     * @des 汇总明细账信息
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam String[] areas) throws ActException {
        try {
            List<AccountCollectVO> accountCollectVOS = BeanTransform.copyProperties(
                    accountInfoAPI.collectAccountInfo(areas), AccountCollectVO.class);
            return ActResult.initialize(accountCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areasList = accountInfoAPI.getArea();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}