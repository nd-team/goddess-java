package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class InterviewInforSerImpl extends ServiceImpl<InterviewInfor, InterviewInforDTO> implements InterviewInforSer {

    /**
     * 分页查询面试信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<InterviewInforBO> list(InterviewInforDTO dto) throws SerException {
        List<InterviewInfor> interviewInforList = super.findByCis(dto);
        List<InterviewInforBO> interviewInforBOList = BeanTransform.copyProperties(interviewInforList, InterviewInforBO.class, true);
        return interviewInforBOList;
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
        InterviewInfor interviewInfor = BeanTransform.copyProperties(interviewInforTO, InterviewInfor.class, true);
        interviewInfor = super.save(interviewInfor);
        InterviewInforBO interviewInforBO = BeanTransform.copyProperties(interviewInfor, InterviewInforBO.class, true);
        return interviewInforBO;
    }

    /**
     * 更新面试信息
     *
     * @param interviewInforTO
     * @throws SerException
     */
    @Override
    public void update(InterviewInforTO interviewInforTO) throws SerException {
        InterviewInfor interviewInfor = BeanTransform.copyProperties(interviewInforTO, InterviewInfor.class, true);
        super.update(interviewInfor);
    }
}
