package com.bjike.goddess.allmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.allmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.allmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.allmeeting.entity.MeetingTopic;
import com.bjike.goddess.allmeeting.to.MeetingTopicTO;

import java.util.List;

/**
 * 议题管理业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-24 04:49 ]
 * @Description: [ 议题管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingTopicSer extends Ser<MeetingTopic, MeetingTopicDTO> {

    /**
     * 新增议题
     *
     * @param to 议题信息
     * @return 议题信息
     */
    MeetingTopicBO insertModel(MeetingTopicTO to) throws SerException;

    /**
     * 编辑议题
     *
     * @param to 议题信息
     * @return 议题信息
     */
    MeetingTopicBO updateModel(MeetingTopicTO to) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingTopicBO> pageList(MeetingTopicDTO dto) throws SerException;

    /**
     * 根据层面查询议题
     *
     * @param layId 层面Id
     * @return 分页结果集
     */
    MeetingTopicBO findByLay(String layId) throws SerException;

    void delete(String id) throws SerException;
}