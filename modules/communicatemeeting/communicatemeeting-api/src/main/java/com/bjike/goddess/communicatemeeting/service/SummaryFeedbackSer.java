package com.bjike.goddess.communicatemeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.communicatemeeting.bo.SummaryFeedbackBO;
import com.bjike.goddess.communicatemeeting.dto.SummaryFeedbackDTO;
import com.bjike.goddess.communicatemeeting.entity.SummaryFeedback;
import com.bjike.goddess.communicatemeeting.to.SummaryFeedbackTO;

import java.util.List;

/**
 * 纪要反馈投诉业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:39 ]
 * @Description: [ 纪要反馈投诉业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SummaryFeedbackSer extends Ser<SummaryFeedback, SummaryFeedbackDTO> {
    /**
     * 添加
     *
     * @param to 纪要反馈投诉to
     * @return
     * @throws SerException
     */
    SummaryFeedbackBO save(SummaryFeedbackTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 纪要反馈投诉to
     * @throws SerException
     */
    void edit(SummaryFeedbackTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    List<SummaryFeedbackBO> list(SummaryFeedbackDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    SummaryFeedbackBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(SummaryFeedbackDTO dto) throws SerException;
}