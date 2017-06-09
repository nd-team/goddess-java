package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;

import java.util.List;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:46]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InterviewAddressInforAPI {

    /**
     * 根据id查询面试地址信息
     *
     * @param id 面试地址信息唯一标识
     * @return class InterviewAddressInforBO
     * @throws SerException
     */
    InterviewAddressInforBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 面试地址信息dto
     * @throws SerException
     */
    Long count(InterviewAddressInforDTO dto) throws SerException;

    /**
     * 分页查询面试信息地址
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<InterviewAddressInforBO> list(InterviewAddressInforDTO dto) throws SerException;

    /**
     * 保存面试信息地址
     *
     * @param interviewAddressInforTO
     * @return
     * @throws SerException
     */
    InterviewAddressInforBO save(InterviewAddressInforTO interviewAddressInforTO) throws SerException;

    /**
     * 根据id删除面试信息地址
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新面试信息地址
     *
     * @param interviewAddressInforTO
     * @throws SerException
     */
    void update(InterviewAddressInforTO interviewAddressInforTO) throws SerException;
}
