package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordCollectDTO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.service.CapitalFlowRecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:03]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("capitalFlowRecordApiImpl")
public class CapitalFlowRecordApiImpl implements CapitalFlowRecordAPI {

    @Autowired
    private CapitalFlowRecordSer capitalFlowRecordSer;

//    @Autowired
//    private VoucherGenerateSer voucherGenerateSer;


    /*
    * 获取资金流水记录列表功能
    * */
    @Override
    public List<FundRecordBO> listRecord(FundRecordDTO dto) throws SerException {
        capitalFlowRecordSer.listRecord(dto);
        return null;
    }

    /*
    * 删除资金流水记录
    * */
    @Override
    public void deleteFundRecord() throws SerException {
        return;
    }

    /*
    * 全局搜索分页查询资金流水记录
    * */
    @Override
    public List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto, FundRecordDTO fundRecordDTO) throws SerException {
        return capitalFlowRecordSer.searchByCondition(dto, fundRecordDTO);
    }

    /*
    * 查询资金流水总记录数
    * */
    @Override
    public Long countRecord(FundRecordDTO dto) throws SerException {
        return capitalFlowRecordSer.countRecord(dto);
    }

    /*
    * 处理数据同步记账凭证和资金流水删除地区字段相应数据
    * */
    @Override
    public void deleteArea(String id) throws SerException {
//        voucherGenerateSer.deleteVoucherGenerate(id);
        capitalFlowRecordSer.deleteVoucherGenerate(id);
        return;
    }

    /*
    * 查询第一条数据
    * */
    @Override
    public void generateData() throws SerException {
        capitalFlowRecordSer.generateData();
        return;
    }


    /**
     * 获取开账时间财务初始化基本参数的账套会计期间启用日期
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<BaseParameterBO> listBaseDate(BaseParameterDTO dto) throws SerException {
        return capitalFlowRecordSer.listBaseDate(dto);
    }

    @Override
    public byte[] exportExcel(CapitalFlowRecordCollectDTO dto) throws SerException {
        return new byte[0];
    }


}
