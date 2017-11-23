package com.bjike.goddess.lendreimbursement.action.lendreimbursement.olddata;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.lendreimbursement.api.olddata.AjoaLendAPI;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaLend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 老系统的借款
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ajoalend")
public class AjoaLendAction {
    @Autowired
    private AjoaLendAPI ajoaLendAPI;

    /**
     * 辅助新系统导老系统数据
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/oldImport")
    public Result oldImport( ) throws ActException {
        try {
            ajoaLendAPI.importLendOldData( );

            return new ActResult("import success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }


    }
}