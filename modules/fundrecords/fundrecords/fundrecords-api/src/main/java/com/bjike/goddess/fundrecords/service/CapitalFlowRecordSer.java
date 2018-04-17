package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;

import java.util.List;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:00]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CapitalFlowRecordSer extends Ser<FundRecord,FundRecordDTO> {

    /*
    * 获取资金流水记录列表
    * */
     void listRecord() throws SerException;

    /*
    * 删除资金流水记录
    * */
    void deleteFundRecord()throws SerException;

    /*
    * 全局搜索查询资金流水记录
    * */
    List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto,FundRecordDTO fundRecordDTO) throws SerException;

    /*
    * 查询资金流水总记录数
    * */
    Long countRecord(FundRecordDTO dto)throws SerException;

    /*
    * 处理数据同步记账凭证和资金流水删除地区字段相应数据
    * */
    default void deleteArea(String id) throws SerException{
        return;
    }

    /*
    * 查询第一条数据
    * */
    void generateData()throws SerException;


}
