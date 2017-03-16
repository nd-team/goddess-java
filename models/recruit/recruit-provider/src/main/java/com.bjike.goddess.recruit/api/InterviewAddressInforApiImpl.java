package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;
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
    @Override
    public List<InterviewAddressInforBO> list(InterviewAddressInforDTO dto) throws SerException {
        return null;
    }

    @Override
    public InterviewAddressInforBO save(InterviewAddressInforTO interviewAddressInforTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(InterviewAddressInforTO interviewAddressInforTO) throws SerException {

    }
}
