package com.bjike.goddess.allmeeting.api;

import com.bjike.goddess.allmeeting.bo.MeetingTopicBO;
import com.bjike.goddess.allmeeting.dto.MeetingTopicDTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.to.MeetingTopicTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface MeetingTopicAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 新增议题管理
     *
     * @param to 议题信息
     * @return 议题信息
     */
    MeetingTopicBO add(MeetingTopicTO to) throws SerException;

    /**
     * 编辑议题管理
     *
     * @param to 议题信息
     * @return 议题信息
     */
    MeetingTopicBO edit(MeetingTopicTO to) throws SerException;

    /**
     * 删除议题管理
     *
     * @param id 议题信息id
     */
    void delete(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<MeetingTopicBO> pageList(MeetingTopicDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(MeetingTopicDTO dto) throws SerException;

    /**
     * 根据id查询议题
     *
     * @param id 议题id
     * @return 议题信息
     */
    MeetingTopicBO findById(String id) throws SerException;

    /**
     * 查询会议议题列表
     * @return
     * @throws SerException
     */
    List<MeetingTopicBO> topics() throws SerException;
}