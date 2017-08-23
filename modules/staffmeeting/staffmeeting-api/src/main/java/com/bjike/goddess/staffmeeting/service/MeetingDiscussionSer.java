package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.staffmeeting.dto.MeetingDiscussionDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingDiscussion;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;

import java.util.List;

/**
 * 通告反馈投诉业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingDiscussionSer extends Ser<MeetingDiscussion, MeetingDiscussionDTO> {

    /**
     * 新增意见讨论
     *
     * @param to 意见讨论
     * @return 意见讨论
     */
    MeetingDiscussionBO insertModel(MeetingDiscussionTO to) throws SerException;

    /**
     * 根据纪要Id查询讨论列表
     *
     * @param summaryId 纪要id
     * @return 讨论列表
     * @throws SerException
     */
    List<MeetingDiscussionBO> listBySummaryId(String summaryId) throws SerException;

    /**
     * 根据纪要id查询交流讨论
     * @param summaryId 纪要Id
     */
    MeetingDiscussionBO discussFind(String summaryId) throws SerException;
}