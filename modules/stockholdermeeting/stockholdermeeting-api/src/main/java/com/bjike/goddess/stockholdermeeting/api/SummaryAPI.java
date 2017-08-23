package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.SummaryBO;
import com.bjike.goddess.stockholdermeeting.dto.ExcelDTO;
import com.bjike.goddess.stockholdermeeting.dto.SummaryDTO;
import com.bjike.goddess.stockholdermeeting.excel.SummaryExcel;
import com.bjike.goddess.stockholdermeeting.to.ExportExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryExcelTO;
import com.bjike.goddess.stockholdermeeting.to.SummaryTO;

import java.util.List;

/**
 * 股东大会纪要业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 06:13 ]
 * @Description: [ 股东大会纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummaryAPI {
    /**
     * 添加
     *
     * @param to 股东大会纪要to
     * @return
     * @throws SerException
     */
    SummaryBO save(SummaryTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 股东大会纪要to
     * @throws SerException
     */
    void edit(SummaryTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 股东大会纪要dto
     * @return
     * @throws SerException
     */
    List<SummaryBO> list(SummaryDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    SummaryBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto 股东大会纪要dto
     * @return
     * @throws SerException
     */
    Long countNum(SummaryDTO dto) throws SerException;

    /**
     * 导出excel
     *
     * @param excelDTO 导出excel条件
     * @return
     * @throws SerException
     */
    byte[] exportExcel(ExcelDTO excelDTO) throws SerException;

    /**
     * 导入excel
     *
     * @param toList 股东大会纪要excelTO集合
     * @throws SerException
     */
    void leadExcel(List<SummaryExcelTO> toList) throws SerException;

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    void thaw(String id) throws SerException;
}