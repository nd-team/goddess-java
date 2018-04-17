package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;

import java.util.List;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 13:59]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CapitalFlowRecordAPI {

    /*
    * 获取资金流水记录列表,更新数据
    * */
    void listRecord() throws SerException;

    /*
    * 删除资金流水记录表
    * */
    void deleteFundRecord()throws SerException;

    /*
    * 全局搜索查询资金流水记录
    * */
    List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto,FundRecordDTO fundRecordDTO)throws SerException;

    /*
    * 查询资金流水总记录数
    * */
    Long countRecord(FundRecordDTO fundRecordDTO) throws SerException;


    /*
    * 处理数据同步记账凭证和资金流水删除地区字段相应数据
    * String id
    * */
    default void deleteArea(String id)throws SerException{
        return;
    }

    /*
    * 获取第一条数据
    * */
    void generateData()throws SerException;







}
