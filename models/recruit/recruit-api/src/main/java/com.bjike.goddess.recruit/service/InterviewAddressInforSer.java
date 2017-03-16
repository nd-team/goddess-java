package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.entity.InterviewAddressInfor;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;

import java.util.List;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface InterviewAddressInforSer extends Ser<InterviewAddressInfor, InterviewAddressInforDTO> {

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
