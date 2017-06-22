package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.MeetingDiscussionBO;
import com.bjike.goddess.staffmeeting.to.MeetingDiscussionTO;

import java.util.List;

/**
 * 交流讨论接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingDiscussionAPI {

    /**
     * 新增交流讨论
     *
     * @param to 交流讨论
     * @return 交流讨论
     * @throws SerException
     */
    MeetingDiscussionBO add(MeetingDiscussionTO to) throws SerException;

    /**
     * 交流讨论列表
     *
     * @param summaryId 纪要Id
     * @return
     * @throws SerException
     */
    List<MeetingDiscussionBO> listBySummaryId(String summaryId) throws SerException;
}