package com.bjike.goddess.staffmeeting.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffmeeting.bo.FeedbackComplainBO;
import com.bjike.goddess.staffmeeting.dto.FeedbackComplainDTO;
import com.bjike.goddess.staffmeeting.entity.FeedbackComplain;
import com.bjike.goddess.staffmeeting.to.FeedbackComplainTO;

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
public interface FeedbackComplainSer extends Ser<FeedbackComplain, FeedbackComplainDTO> {

    /**
     * 新增通告反馈投诉
     *
     * @param to 通告反馈投诉
     * @return 通告反馈投诉
     */
    FeedbackComplainBO insertModel(FeedbackComplainTO to) throws SerException;

    /**
     * 更新通告反馈投诉
     *
     * @param to 通告反馈投诉
     * @return 通告反馈投诉
     */
    FeedbackComplainBO updateModel(FeedbackComplainTO to) throws SerException;

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
     * 解冻
     *
     * @param id
     * @throws SerException
     */
    void unfreeze(String id) throws SerException;
}