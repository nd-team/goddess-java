package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.stockholdermeeting.bo.SummarySonBO;
import com.bjike.goddess.stockholdermeeting.dto.SummarySonDTO;
import com.bjike.goddess.stockholdermeeting.entity.SummarySon;
import com.bjike.goddess.stockholdermeeting.to.SummarySonTO;

import java.util.List;

/**
 * 股东大会纪要子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-07 10:53 ]
 * @Description: [ 股东大会纪要子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummarySonSer extends Ser<SummarySon, SummarySonDTO> {
    /**
     * 添加
     *
     * @param to 股东大会纪要子表to
     * @return
     * @throws SerException
     */
    SummarySonBO save(SummarySonTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 股东大会纪要子表to
     * @throws SerException
     */
    void edit(SummarySonTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 股东大会纪要子表dto
     * @return
     * @throws SerException
     */
    List<SummarySonBO> list(SummarySonDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    SummarySonBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto 股东大会纪要子表dto
     * @return
     * @throws SerException
     */
    Long countNum(SummarySonDTO dto) throws SerException;

    /**
     * 通过会议纪要通过会议纪要子表信息
     *
     * @param summaryId 会议纪要id
     * @return
     * @throws SerException
     */
    List<SummarySon> findBySummaryId(String summaryId) throws SerException;

    /**
     * 修改发言内容
     *
     * @param to
     * @throws SerException
     */
    void editSpeak(SummarySonTO to) throws SerException;
}