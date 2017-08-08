package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.staffshares.api.PurchaseAPI;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 干股申购表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("purchase")
public class PurchaseAction {
    @Autowired
    private PurchaseAPI purchaseAPI;

    /**
     * 编辑
     *
     * @param to 干股申购传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.update(to);
            return ActResult.initialize("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 撤销
     *
     * @param id id
     * @des 根据id撤销干股申购记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            purchaseAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 干股申购传输对象
     * @version v1
     */
    @PutMapping("v1/examine/{id}")
    public Result examine(PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.examine(to);
            return ActResult.initialize("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}