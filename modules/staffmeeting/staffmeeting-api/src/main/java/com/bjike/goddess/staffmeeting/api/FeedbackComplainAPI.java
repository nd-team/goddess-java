package com.bjike.goddess.staffmeeting.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.excel.SonPermissionObject;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;

import java.util.List;

/**
 * 通告反馈投诉业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FeedbackComplainAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 新增通告反馈投诉
     *
     * @param to 通告反馈投诉
     * @return 通告反馈投诉
     * @throws SerException
     */
    FeedbackComplainBO add(FeedbackComplainTO to) throws SerException;

    /**
     * 编辑通告反馈投诉
     *
     * @param to 通告反馈投诉
     * @return 通告反馈投诉
     */
    FeedbackComplainBO edit(FeedbackComplainTO to) throws SerException;

    /**
     * 冻结通告反馈投诉
     *
     * @param id 通告反馈投诉Id
     */
    void freeze(String id) throws SerException;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return 分页结果集
     */
    List<FeedbackComplainBO> pageList(FeedbackComplainDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(FeedbackComplainDTO dto) throws SerException;

    /**
     * 根据Id查询通告反馈投诉
     *
     * @param id 通告反馈投诉Id
     * @return 通告反馈投诉
     */
    FeedbackComplainBO findById(String id) throws SerException;

    void unfreeze(String id) throws SerException;
}