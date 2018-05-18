package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.BaseParameterBO;
import com.bjike.goddess.financeinit.dto.BaseParameterDTO;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordCollectDTO;
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

    /**
     * 获取资金流水记录列表更新数据xiexiaoting
     * @param dto
     * @throws SerException
     */
    List<FundRecordBO> listRecord(FundRecordDTO dto) throws SerException;

    /**
     * 删除资金流水记录表
     * @throws SerException
     */
    void deleteFundRecord()throws SerException;

    /**
     * 全局搜索查询资金流水记录xiexiaoting
     * @param dto
     * @param fundRecordDTO
     * @throws SerException
     */
    List<FundRecordBO> searchByCondition(CapitalFlowRecordDTO dto, FundRecordDTO fundRecordDTO)throws SerException;

    /**
     * 查询资金流水总记录数xiexiaoting
     * @param fundRecordDTO
     * @throws SerException
     */
    Long countRecord(FundRecordDTO fundRecordDTO) throws SerException;


    /**
     * 处理数据同步记账凭证和资金流水删除地区字段相应数据xiexiaoting
     * @param id
     * @throws SerException
     */
    default void deleteArea(String id)throws SerException{
        return;
    }

    /**
     * 获取第一条数据
     * @throws SerException
     */
    void generateData()throws SerException;


    /**
     * 获取开账时间财务初始化基本参数的账套会计期间启用日期xiexiaoting
     * @param dto
     * @throws SerException
     */
    default List<BaseParameterBO> listBaseDate(BaseParameterDTO dto)throws SerException{
        return null;
    }

    /**
     * 汇总导出
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(CapitalFlowRecordCollectDTO dto)throws SerException;











}
