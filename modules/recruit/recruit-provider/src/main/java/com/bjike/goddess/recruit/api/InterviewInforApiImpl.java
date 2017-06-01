package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.service.InterviewInforSer;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("interviewInforApiImpl")
public class InterviewInforApiImpl implements InterviewInforAPI {

     @Autowired
     private InterviewInforSer interviewInforSer;

    /**
     * 根据id查询面试信息
     *
     * @param id 面试信息唯一标识
     * @return class InterviewInforBO
     * @throws SerException
     */
    @Override
    public InterviewInforBO findById(String id) throws SerException {
        InterviewInfor model = interviewInforSer.findById(id);
        return BeanTransform.copyProperties(model, InterviewInforBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 面试信息dto
     * @throws SerException
     */
    @Override
    public Long count(InterviewInforDTO dto) throws SerException {
        return interviewInforSer.count(dto);
    }


    /**
     * 分页查询面试信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException {
        return interviewInforSer.list(dto);
    }

    /**
     * 保存面试信息
     *
     * @param interviewInforTO
     * @return
     * @throws SerException
     */
    @Override
    public InterviewInforBO save(InterviewInforTO interviewInforTO) throws SerException {
        return interviewInforSer.save(interviewInforTO);
    }

    /**
     * 根据id删除面试信息
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        interviewInforSer.remove(id);
    }

    /**
     * 更新面试信息
     *
     * @param interviewInforTO
     * @throws SerException
     */
    @Override
    public void update(InterviewInforTO interviewInforTO) throws SerException {
        interviewInforSer.update(interviewInforTO);
    }
}
