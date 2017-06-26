package com.bjike.goddess.democraticmeet.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.democraticmeet.bo.SummaryBO;
import com.bjike.goddess.democraticmeet.dto.SummaryDTO;
import com.bjike.goddess.democraticmeet.to.ExportExcelCondition;
import com.bjike.goddess.democraticmeet.excel.SummaryExcelConvert;
import com.bjike.goddess.democraticmeet.to.SummaryTO;

import java.util.List;

/**
 * 会议纪要业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-06-03 11:22 ]
 * @Description: [ 会议纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummaryAPI {

    /**
     * 会议纪要列表总条数
     *
     */
    default Long countSummary(SummaryDTO summaryDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取会议纪要列表
     * @return class SummaryBO
     */
    default SummaryBO getOneById(String id) throws SerException {return null;}

    /**
     * 会议纪要列表
     * @return class SummaryBO
     */
    default List<SummaryBO> listSummary(SummaryDTO summaryDTO) throws SerException {return null;}
    /**
     *  添加
     * @param summaryTO 会议纪要信息
     * @return class SummaryBO
     */
    default SummaryBO addSummary(SummaryTO summaryTO) throws SerException { return null;}

    /**
     *  编辑
     * @param summaryTO 会议纪要信息
     * @return class SummaryBO
     */
    default SummaryBO editSummary(SummaryTO summaryTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteSummary(String id ) throws SerException {return;};

    /**
     * 导入Excel
     *
     * @param toList
     * @throws SerException
     */
    void leadExcel(List<SummaryExcelConvert> toList) throws SerException;

    /**
     * 导出Excel
     *
     * @param to
     * @throws SerException
     */
    byte[] exportReport(ExportExcelCondition to) throws SerException;





}