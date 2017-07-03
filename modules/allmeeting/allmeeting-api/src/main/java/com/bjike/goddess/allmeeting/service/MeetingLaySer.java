package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.allmeeting.bo.MeetingLayBO;
import com.bjike.goddess.allmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.allmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.allmeeting.entity.MeetingLay;
import com.bjike.goddess.allmeeting.entity.MeetingTopic;
import com.bjike.goddess.allmeeting.to.MeetingLayTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 会议层面业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingLaySer extends Ser<MeetingLay, MeetingLayDTO> {

    /**
     * 新增会议层面
     *
     * @param to 会议层面
     * @return 会议层面
     */
    MeetingLayBO insertModel(MeetingLayTO to) throws SerException;

    /**
     * 编辑会议层面
     *
     * @param to 会议层面
     * @return 会议层面
     */
    MeetingLayBO updateModel(MeetingLayTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingLayBO> pageList(MeetingLayDTO dto) throws SerException;

    List<MeetingLayBO> lays() throws SerException;

    void delete(String id) throws SerException;
}