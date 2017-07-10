package com.bjike.goddess.fundcheck.action.fundcheck;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundcheck.api.AccountBalanceAPI;
import com.bjike.goddess.fundcheck.entity.AccountBalance;
import com.bjike.goddess.fundcheck.to.AccountBalanceCollectTO;
import com.bjike.goddess.fundcheck.to.AccountBalanceTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.vo.AccountBalanceVO;
import com.bjike.goddess.fundcheck.vo.AccountIncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 账上余额
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("accountbalance")
public class AccountBalanceAction {
    @Autowired
    private AccountBalanceAPI accountBalanceAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = accountBalanceAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param to 账上余额to
     * @return class AccountBalanceVO
     * @des 汇总账上余额
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated AccountBalanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<AccountBalanceVO> accountIncomeVOS = BeanTransform.copyProperties(accountBalanceAPI.collect(to), AccountBalanceVO.class);
            return ActResult.initialize(accountIncomeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}