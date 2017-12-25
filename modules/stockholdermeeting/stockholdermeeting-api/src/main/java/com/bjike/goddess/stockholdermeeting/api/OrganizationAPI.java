package com.bjike.goddess.stockholdermeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.dto.OrganizationDTO;
import com.bjike.goddess.stockholdermeeting.to.OrganizationTO;

import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:46 ]
 * @Description: [ 股东大会组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OrganizationAPI {
    /**
     * 添加
     *
     * @param to 股东大会组织内容to
     * @return
     * @throws SerException
     */
    OrganizationBO save(OrganizationTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 股东大会组织内容to
     * @throws SerException
     */
    void edit(OrganizationTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 股东大会组织内容dto
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
     * @param positionIds 岗位id数组
     * @return
     * @throws SerException
     */
    List<String> findPeople(String[] positionIds) throws SerException;

    /**
     * 查找所有会议编号
     *
     * @throws SerException
     */
    Set<String> allMeetingsNumbers() throws SerException;

    /**
     * 查找所有会议形式
     *
     * @throws SerException
     */
    Set<String> allMeetingFormats() throws SerException;

    /**
     * 冻结
     *
     * @param id
     * @throws SerException
     */
    void freeze(String id) throws SerException;

    /**
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    void thaw(String id) throws SerException;
}