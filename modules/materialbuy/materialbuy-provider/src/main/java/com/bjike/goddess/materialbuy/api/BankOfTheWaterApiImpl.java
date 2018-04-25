package com.bjike.goddess.materialbuy.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.materialbuy.bo.BankOfTheWaterBO;
import com.bjike.goddess.materialbuy.bo.ExcelTitleBO;
import com.bjike.goddess.materialbuy.dto.BankOfTheWaterDTO;
import com.bjike.goddess.materialbuy.service.BankOfTheWaterSer;
import com.bjike.goddess.materialbuy.to.BankOfTheWaterTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 银行流水业务接口实现
 *
 * @Author: [ yangruizhen ]
 * @Date: [ 2018-04-04 09:39 ]
 * @Description: [ 银行流水业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankOfTheWaterApiImpl")
public class BankOfTheWaterApiImpl implements BankOfTheWaterAPI {
    @Autowired
    private BankOfTheWaterSer bankOfTheWaterSer;


    public BankOfTheWaterSer getBankOfTheWaterSer() {
        return bankOfTheWaterSer;
    }

    public void setBankOfTheWaterSer(BankOfTheWaterSer bankOfTheWaterSer) {
        this.bankOfTheWaterSer = bankOfTheWaterSer;
    }

    @Override
    public List<BankOfTheWaterBO> listPath(BankOfTheWaterDTO dto) throws SerException {
        return bankOfTheWaterSer.listPath(dto);
    }

    @Override
    public List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException {
        return bankOfTheWaterSer.check(inputStreams);
    }

    @Override
    public void upload(BankOfTheWaterTO to) throws SerException {
        bankOfTheWaterSer.upload(to);
    }

    @Override
    public List<BankOfTheWaterBO> bankSummary(BankOfTheWaterDTO dto) throws SerException {
        return bankOfTheWaterSer.bankSummary(dto);
    }

    @Override
    public List<BankOfTheWaterBO> banksGlobally() throws SerException {
        return bankOfTheWaterSer.banksGlobally();
    }

}