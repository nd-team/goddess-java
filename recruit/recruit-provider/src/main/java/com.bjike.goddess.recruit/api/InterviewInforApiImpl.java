package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("interviewInforApiImpl")
public class InterviewInforApiImpl implements InterviewInforAPI {
    @Override
    public List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException {
        return null;
    }

    @Override
    public InterviewInforBO save(InterviewInforTO interviewInforTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }
}
