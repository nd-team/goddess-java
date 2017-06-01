package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.DesginBO;
import com.bjike.goddess.communicatemeeting.dto.DesginDTO;
import com.bjike.goddess.communicatemeeting.to.DesginTO;

import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 03:31 ]
 * @Description: [ 交流会组织内容设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DesginAPI {
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
     * 根据会议议题和会议层面查找所有未冻结的计划参会岗位
     *
     * @param meetingTopic 会议议题
     * @param meetingLevel 会议层面
     * @return
     * @throws SerException
     */
    Set<String> normalPlanJobs(String meetingTopic, String meetingLevel) throws SerException;

    /**
     * 根据会议议题和会议层面查找所有未冻结的计划会议时间
     *
     * @param meetingTopic
     * @param meetingLevel
     * @return
     * @throws SerException
     */
    Set<String> normalPlanTimes(String meetingTopic, String meetingLevel) throws SerException;

    /**
     * 根据会议议题和会议层面查找所有冻结的计划参会岗位
     *
     * @param meetingTopic
     * @param meetingLevel
     * @return
     * @throws SerException
     */
    Set<String> freezePlanJobs(String meetingTopic, String meetingLevel) throws SerException;

    /**
     * 根据会议议题和会议层面查找所有冻结的计划会议时间
     *
     * @param meetingTopic
     * @param meetingLevel
     * @return
     * @throws SerException
     */
    Set<String> freezePlanTimes(String meetingTopic, String meetingLevel) throws SerException;

    /**
     * 根据会议层面获取会议编号
     *
     * @param meetingLevel 会议层面
     * @return
     * @throws SerException
     */
    String findNumber(String meetingLevel) throws SerException;

    /**
     * 冻结计划参会岗位
     *
     * @param id 交流会组织内容设计id
     * @throws SerException
     */
    void freezeJob(String id) throws SerException;

    /**
     * 解冻计划参会岗位
     *
     * @param id 交流会组织内容设计id
     * @throws SerException
     */
    void thawJob(String id) throws SerException;

    /**
     * 编辑计划参会岗位
     *
     * @param to 交流会组织内容设计to
     * @throws SerException
     */
    void editJob(DesginTO to) throws SerException;

    /**
     * 冻结计划会议时间
     *
     * @param id 交流会组织内容设计id
     * @throws SerException
     */
    void freezeTime(String id) throws SerException;

    /**
     * 解冻计划会议时间
     *
     * @param id 交流会组织内容设计id
     * @throws SerException
     */
    void thawTime(String id) throws SerException;

    /**
     * 编辑计划会议时间
     *
     * @param to 交流会组织内容设计to
     * @throws SerException
     */
    void editTime(DesginTO to) throws SerException;

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
}