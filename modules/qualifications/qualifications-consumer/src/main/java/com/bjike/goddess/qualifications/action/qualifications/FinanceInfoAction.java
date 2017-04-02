package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.FinanceInfoAPI;
import com.bjike.goddess.qualifications.to.FinanceInfoTO;
import com.bjike.goddess.qualifications.vo.FinanceInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 财务资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/financeinfo")
public class FinanceInfoAction {

    @Autowired
    private FinanceInfoAPI financeInfoAPI;

    /**
     * 保存
     *
     * @param to 财务资料传输对象
     * @return class FinanceInfoVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) FinanceInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeInfoAPI.save(to), FinanceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 财务资料传输对象
     * @return class FinanceInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) FinanceInfoTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeInfoAPI.update(to), FinanceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 财务资料id
     * @return class FinanceInfoVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeInfoAPI.delete(id), FinanceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部
     *
     * @return class FinanceInfoVO
     * @version v1
     */
    @GetMapping("v1/all")
    public Result all() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(financeInfoAPI.all(), FinanceInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}