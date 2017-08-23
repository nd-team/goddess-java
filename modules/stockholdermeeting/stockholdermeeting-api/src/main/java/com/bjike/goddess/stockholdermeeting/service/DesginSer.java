package com.bjike.goddess.stockholdermeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.stockholdermeeting.bo.DesginBO;
import com.bjike.goddess.stockholdermeeting.dto.DesginDTO;
import com.bjike.goddess.stockholdermeeting.entity.Desgin;
import com.bjike.goddess.stockholdermeeting.to.DesginTO;

import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:33 ]
 * @Description: [ 股东大会组织内容设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DesginSer extends Ser<Desgin, DesginDTO> {
    /**
     * 查找所有会议议题
     *
     * @return
     * @throws SerException
     */
    Set<String> meetingTopics() throws SerException;

    /**
     * 根据会议议题查找所有会议层面
     *
     * @param meetingTopic 会议议题
     * @return
     * @throws SerException
     */
    Set<String> meetingLevels(String meetingTopic) throws SerException;

    /**
     * 根据会议议题查找议题包含内容
     *
     * @param meetingTopic 会议议题
     * @return
     * @throws SerException
     */
    String topicContent(String meetingTopic) throws SerException;

    /**
     * 根据会议议题和会议层面查找计划会议时间
     *
     * @param meetingTopic
     * @param meetingLevel
     * @return
     * @throws SerException
     */
    String planTime(String meetingTopic, String meetingLevel) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    DesginBO findByID(String id) throws SerException;

    /**
     * 列表
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    List<DesginBO> list(DesginDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(DesginDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to to
     * @return
     * @throws SerException
     */
    DesginBO save(DesginTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to to
     * @throws SerException
     */
    void edit(DesginTO to) throws SerException;

    /**
     * 根据会议议题查找会议议题关联的功能
     *
     * @param meetingTopic 会议议题
     * @return
     * @throws SerException
     */
    String function(String meetingTopic) throws SerException;
}