package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.MeetingSummaryBO;
import com.bjike.goddess.staffmeeting.dto.MeetingSummaryDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingSummary;
import com.bjike.goddess.staffmeeting.to.MeetingSummaryTO;

import java.util.List;

/**
 * 员工代表大会纪要业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingSummarySer extends Ser<MeetingSummary, MeetingSummaryDTO> {

    /**
     * 根据Id查询纪要并填充属性
     *
     * @param id 纪要Id
     * @return 纪要详细信息
     */
    MeetingSummaryBO findAndSet(String id) throws SerException;

    /**
     * 更新会议纪要
     *
     * @param to 会议纪要
     * @return 会议纪要
     */
    MeetingSummaryBO updateModel(MeetingSummaryTO to) throws SerException;

    /**
     * 冻结会议纪要
     *
     * @param id 会议纪要Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingSummaryBO> pageList(MeetingSummaryDTO dto) throws SerException;

    void unFreeze(String id) throws SerException;
}