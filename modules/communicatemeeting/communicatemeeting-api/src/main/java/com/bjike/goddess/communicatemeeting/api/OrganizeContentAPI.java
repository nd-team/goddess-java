package com.bjike.goddess.communicatemeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.communicatemeeting.bo.OrganizeContentBO;
import com.bjike.goddess.communicatemeeting.dto.OrganizeContentDTO;
import com.bjike.goddess.communicatemeeting.to.OrganizeContentTO;

import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface OrganizeContentAPI {
    /**
     * 添加
     *
     * @param to 交流会组织内容to
     * @return
     * @throws SerException
     */
    OrganizeContentBO save(OrganizeContentTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 交流会组织内容to
     * @throws SerException
     */
    void edit(OrganizeContentTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto 交流会组织内容dto
     * @return
     * @throws SerException
     */
    List<OrganizeContentBO> list(OrganizeContentDTO dto) throws SerException;

    /**
     * 通过id查找
     *
     * @param id id
     * @return
     * @throws SerException
     */
    OrganizeContentBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    Long countNum(OrganizeContentDTO dto) throws SerException;

    /**
     * 更新主持人
     *
     * @param id   id
     * @param host 主持人
     * @throws SerException
     */
    void updateHost(String id, String host) throws SerException;

    /**
     * 查找对应岗位的所有员工
     *
     * @param jobs 岗位数组
     * @return
     * @throws SerException
     */
    Set<String> findPeoples(String... jobs) throws SerException;

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
}