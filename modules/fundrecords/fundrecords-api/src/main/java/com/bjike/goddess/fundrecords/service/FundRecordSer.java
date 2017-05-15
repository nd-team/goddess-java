package com.bjike.goddess.fundrecords.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundrecords.bo.AnalyzeBO;
import com.bjike.goddess.fundrecords.bo.ConditionCollectBO;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.bo.MonthCollectBO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;

import java.util.List;

/**
 * 资金流水业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FundRecordSer extends Ser<FundRecord, FundRecordDTO> {

    /**
     * 新增补填资金流水
     *
     * @param to 资金流水
     * @return 资金流水
     */
    FundRecordBO insertModel(FundRecordTO to) throws SerException;

    /**
     * 编辑补填资金流水
     *
     * @param to 资金流水
     * @return 资金流水
     */
    FundRecordBO updateModel(FundRecordTO to) throws SerException;

    /**
     * 删除补填资金流水
     *
     * @param id 补填资金流水
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询资金流水
     *
     * @param dto 分页条件
     * @return 资金流水结果集
     */
    List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException;

    /**
     * 查询所有记录
     *
     * @param dto 分页条件
     * @return 所有记录
     */
    List<FundRecordBO> findAllBO(FundRecordDTO dto, VoucherGenerateDTO generateDTO) throws SerException;

    /**
     * 月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return 月汇总结果集
     */
    MonthCollectBO month(Integer year, Integer month) throws SerException;

    /**
     * 条件汇总
     *
     * @param to 汇总条件
     * @return 汇总结果集
     */
    List<ConditionCollectBO> condition(CollectTO to) throws SerException;

    /**
     * 分析
     *
     * @param to 分析条件
     * @return 分析结果集
     */
    AnalyzeBO analyze(CollectTO to) throws SerException;
}