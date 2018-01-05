package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.communicatemeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.communicatemeeting.excel.MeetingSummaryExcel;
import com.bjike.goddess.communicatemeeting.to.MeetingSummaryTO;

import java.util.List;
import java.util.Set;

/**
 * 交流会纪要业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:32 ]
 * @Description: [ 交流会纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingSummaryAPI {
    /**
     * 添加
     *
     * @param to 交流会纪要to
     * @return
     * @throws SerException
     */
    MeetingSummaryBO save(MeetingSummaryTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 交流会纪要to
     * @throws SerException
     */
    void edit(MeetingSummaryTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 交流会纪要dto
     * @return
     * @throws SerException
     */
    List<MeetingSummaryBO> list(MeetingSummaryDTO dto) throws SerException;

    /**
     * 通用id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    MeetingSummaryBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto 交流会纪要dto
     * @return
     * @throws SerException
     */
    Long countNum(MeetingSummaryDTO dto) throws SerException;

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
     * @param id id
     * @return
     * @throws SerException
     */
    byte[] exportExcel(String id) throws SerException;

    /**
     * 导入excel
     *
     * @param toList 交流会纪要excel集合
     * @throws SerException
     */
    void leadExcel(List<MeetingSummaryExcel> toList) throws SerException;
}