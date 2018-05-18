package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordCollectDTO;
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
public interface CapitalFlowRecordSer extends Ser<FundRecord, FundRecordDTO> {

    /**
     * 获取资金流水记录列表
     * @param dto
     * @throws SerException
     */
    List<FundRecordBO> listRecord(FundRecordDTO dto) throws SerException;

    /**
     * 删除资金流水记录
     * @throws SerException
     */
    void deleteFundRecord() throws SerException;

    /**
     * 全局搜索查询资金流水记录
     * @param dto
     * @param fundRecordDTO
     * @return
     * @throws SerException
     */
    List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto, FundRecordDTO fundRecordDTO) throws SerException;

    /**
     * 查询资金流水总记录数
     * @param dto
     * @throws SerException
     */
    Long countRecord(FundRecordDTO dto) throws SerException;

    /**
     * 处理数据同步记账凭证和资金流水删除地区字段相应数据
     * @param id
     * @throws SerException
     */
    default void deleteArea(String id) throws SerException {
        return;
    }

    /**
     * 查询第一条数据
     * @throws SerException
     */
    void generateData() throws SerException;

    /**
     * 获取记账凭证中删除的id
     * @param id
     * @throws SerException
     */
    void deleteVoucherGenerate(String id) throws SerException;

    /**
     * 定时器(定时获取记账凭证的数据,并存进模块)
     *
     * @throws SerException
     */
    void listRecordTask() throws SerException;


    /**
     * 获取开账时间财务初始化基本参数的账套会计期间启用日期xiexiaoting
     *
     * @throws SerException
     */
    default List<BaseParameterBO> listBaseDate(BaseParameterDTO dto) throws SerException {
        return null;
    }

    /**
     * 汇总导出
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(CapitalFlowRecordCollectDTO dto)throws SerException;


}
