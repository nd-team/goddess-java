package com.bjike.goddess.materialbuy.action.materialbuy;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.materialbuy.Excel.BankOfTheWaterExcel;
import com.bjike.goddess.materialbuy.api.BankOfTheWaterAPI;
import com.bjike.goddess.materialbuy.to.BankOfTheWaterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 银行流水
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bankofthewater")
public class BankOfTheWaterAction extends BaseFileAction {
    @Autowired
    private BankOfTheWaterAPI bankOfTheWaterAPI;
//
// bankOfTheWaterAPI   @PostMapping("v1/importExcel")
//    public Result importExcel(HttpServletRequest request) throws ActException {
//        try {
//            List<InputStream> inputStreams = super.getInputStreams(request);
//            InputStream is = inputStreams.get(1);
//            Excel excel = new Excel(0, 1);
//            List<BankOfTheWaterExcel> tos = ExcelUtil.excelToClazz(is, BankOfTheWaterExcel.class, excel);
//            List<BankOfTheWaterTO> tocs = new ArrayList<>();
//            List<BankOfTheWaterTO> toList = BeanTransform.copyProperties(tos, BankOfTheWaterTO.class);
//            bankOfTheWaterAPI.importExcel(toList);
//            return new ActResult("导入成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
}