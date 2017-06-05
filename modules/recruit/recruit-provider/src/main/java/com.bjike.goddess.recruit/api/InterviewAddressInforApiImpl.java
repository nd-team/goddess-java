package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.service.InterviewAddressInforSer;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("interviewAddressInforApiImpl")
public class InterviewAddressInforApiImpl implements InterviewAddressInforAPI {

    @Autowired
    private InterviewAddressInforSer interviewAddressInforSer;

    /**
     * 分页查询面试地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<InterviewAddressInforBO> list(InterviewAddressInforDTO dto) throws SerException {
        return interviewAddressInforSer.list(dto);
    }

    /**
     * 保存面试地址信息
     *
     * @param interviewAddressInforTO
     * @return
     * @throws SerException
     */
    @Override
    public InterviewAddressInforBO save(InterviewAddressInforTO interviewAddressInforTO) throws SerException {
        return interviewAddressInforSer.save(interviewAddressInforTO);
    }

    /**
     * 根据id删除面试地址信息
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        interviewAddressInforSer.remove(id);
    }

    /**
     * 更新面试地址信息
     *
     * @param interviewAddressInforTO
     * @throws SerException
     */
    @Override
    public void update(InterviewAddressInforTO interviewAddressInforTO) throws SerException {
        interviewAddressInforSer.update(interviewAddressInforTO);
    }
}
