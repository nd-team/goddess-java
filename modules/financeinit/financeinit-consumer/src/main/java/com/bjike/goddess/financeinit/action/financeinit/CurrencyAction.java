package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.CurrencyAPI;
import com.bjike.goddess.financeinit.bo.CurrencyBO;
import com.bjike.goddess.financeinit.dto.CurrencyDTO;
import com.bjike.goddess.financeinit.to.CurrencyTO;
import com.bjike.goddess.financeinit.vo.CurrencyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 币别
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:53 ]
 * @Description: [ 币别 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("financeinit/currency")
public class CurrencyAction {


    @Autowired
    private CurrencyAPI currencyAPI;

    /**
     * 币别列表
     *
     * @param currencyDTO 币别信息dto
     * @des 获取所有币别信息
     * @return  class CurrencyVO
     * @version v1
     */
    @GetMapping("v1/listCurrency")
    public Result findListCurrency(CurrencyDTO currencyDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CurrencyVO> currencyVOList = BeanTransform.copyProperties(
                    currencyAPI.listCurrency(currencyDTO), CurrencyVO.class);
            return ActResult.initialize(currencyVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加币别
     *
     * @param currencyTO 币别基本信息数据to
     * @des 添加币别
     * @return  class CurrencyVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCurrency(@Validated CurrencyTO currencyTO, BindingResult bindingResult) throws ActException {
        try {
            CurrencyBO currencyBO1 = currencyAPI.addCurrency(currencyTO);
            return ActResult.initialize(BeanTransform.copyProperties(currencyBO1,CurrencyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑币别
     *
     * @param currencyTO 币别基本信息数据bo
     * @des 添加币别
     * @return  class CurrencyVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCurrency(@Validated CurrencyTO currencyTO) throws ActException {
        try {
            CurrencyBO currencyBO1 = currencyAPI.editCurrency(currencyTO);
            return ActResult.initialize(BeanTransform.copyProperties(currencyBO1,CurrencyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除币别信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCurrency(@PathVariable String id) throws ActException {
        try {
            currencyAPI.deleteCurrency(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }
}