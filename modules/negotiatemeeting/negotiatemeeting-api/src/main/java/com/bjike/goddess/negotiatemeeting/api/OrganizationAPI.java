package com.bjike.goddess.negotiatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.negotiatemeeting.bo.OrganizationBO;
import com.bjike.goddess.negotiatemeeting.dto.OrganizationDTO;
import com.bjike.goddess.negotiatemeeting.to.OrganizationTO;

import java.util.List;
import java.util.Set;

/**
 * 协商会议组织内容业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-31 03:30 ]
 * @Description: [ 协商会议组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OrganizationAPI {
    /**
     * 添加
     *
     * @param to 协商会议组织内容to
     * @return
     * @throws SerException
     */
    OrganizationBO save(OrganizationTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 协商会议组织内容to
     * @throws SerException
     */
    void edit(OrganizationTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 协商会议组织内容dto
     * @return
     * @throws SerException
     */
    List<OrganizationBO> list(OrganizationDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    OrganizationBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(OrganizationDTO dto) throws SerException;

    /**
     * 通过岗位查找员工
     *
     * @param positionId 岗位id
     * @return
     * @throws SerException
     */
    List<String> findPeople(String[] positionId) throws SerException;

    /**
     * 查找所有会议编号
     *
     * @throws SerException
     */
    Set<String> allMeetingsNumber() throws SerException;

    /**
     * 查找所有会议形式
     *
     * @throws SerException
     */
    Set<String> allMeetingFormats() throws SerException;

    /**
     * 通过会议层面查找主持人
     *
     * @param meetingLevel 会议层面
     * @return
     * @throws SerException
     */
    Set<String> findHosts(String meetingLevel) throws SerException;
}