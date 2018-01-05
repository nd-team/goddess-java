package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.MeetingLayBO;
import com.bjike.goddess.staffmeeting.dto.MeetingLayDTO;
import com.bjike.goddess.staffmeeting.entity.MeetingLay;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingLayTO;

import java.util.List;

/**
 * 会议层面业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MeetingLaySer extends Ser<MeetingLay, MeetingLayDTO> {

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

    /**
     * 根据id来删除
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 查询所有岗位
     */
    String[] findPosition() throws SerException;
}