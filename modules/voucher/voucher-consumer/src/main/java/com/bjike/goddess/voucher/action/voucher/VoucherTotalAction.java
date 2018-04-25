package com.bjike.goddess.voucher.action.voucher;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.voucher.api.VoucherTotalAPI;
import com.bjike.goddess.voucher.dto.VoucherTotalDTO;
import com.bjike.goddess.voucher.vo.VoucherTotalGoldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 记账凭证合计金额
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:35 ]
 * @Description: [ 记账凭证合计金额 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("vouchertotal")
public class VoucherTotalAction {
//    @Autowired
//    private VoucherTotalAPI voucherTotalAPI;
//    @PostMapping("v1/findAlltoPage")
//    public Result findAlltoPage(VoucherTotalDTO dto)throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(voucherTotalAPI.vb(dto), VoucherTotalGoldVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}