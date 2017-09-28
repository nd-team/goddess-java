package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.AjoafinancelendmoneyAPI;
import com.bjike.goddess.lendreimbursement.entity.Ajoafinancelendmoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 报销借款老系统
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-09-07 04:23 ]
 * @Description: [ 报销借款老系统 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ajoafinancelendmoney")
public class AjoafinancelendmoneyAction {

    @Autowired
    private AjoafinancelendmoneyAPI ajoafinancelendmoneyAPI;

    /**
     * 还款记录生成记账凭证
     *
     * @return class Ajoafinancelendmoney
     * @des 借款记录生成记账凭证
     * @version v1
     */
    @GetMapping("v1/list")
    public Result listVoucherReturn( ) throws ActException {
        try {
            List<Ajoafinancelendmoney> applyLendVOList =
            ajoafinancelendmoneyAPI.listMoney();
            return ActResult.initialize(applyLendVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}