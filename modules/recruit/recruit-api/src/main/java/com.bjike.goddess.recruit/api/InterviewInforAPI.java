package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:47]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InterviewInforAPI {

    /**
     * 分页查询面试信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException;

    /**
     * 保存面试信息
     *
     * @param interviewInforTO
     * @return
     * @throws SerException
     */
    InterviewInforBO save(InterviewInforTO interviewInforTO) throws SerException;

    /**
     * 根据id删除面试信息
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新面试信息
     *
     * @param interviewInforTO
     * @throws SerException
     */
    void update(InterviewInforTO interviewInforTO) throws SerException;
}
