package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.SummaryBO;
import com.bjike.goddess.negotiatemeeting.dto.SummaryDTO;
import com.bjike.goddess.negotiatemeeting.dto.SummarySonDTO;
import com.bjike.goddess.negotiatemeeting.excel.SummaryExcel;
import com.bjike.goddess.negotiatemeeting.to.SummaryTO;

import java.util.List;
import java.util.Set;

/**
 * 协商会议纪要业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:49 ]
 * @Description: [ 协商会议纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummaryAPI {
    /**
     * 添加
     *
     * @param to 交流会纪要to
     * @return
     * @throws SerException
     */
    SummaryBO save(SummaryTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 交流会纪要to
     * @throws SerException
     */
    void edit(SummaryTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 交流会纪要dto
     * @return
     * @throws SerException
     */
    List<SummaryBO> list(SummaryDTO dto) throws SerException;

    /**
     * 通用id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    SummaryBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto 交流会纪要dto
     * @return
     * @throws SerException
     */
    Long countNum(SummaryDTO dto) throws SerException;

    /**
     * 查找所有会议编号
     *
     * @return
     * @throws SerException
     */
    Set<String> meetingNumbers() throws SerException;

    /**
     * 导出excel
     *
     * @param id     会议纪要id
     * @param summaryId 会议纪要子id
     * @return
     * @throws SerException
     */
    byte[] exportExcel(String id, String summaryId) throws SerException;

    /**
     * 导入excel
     *
     * @param toList 交流会纪要excel集合
     * @throws SerException
     */
    void leadExcel(List<SummaryExcel> toList) throws SerException;
}