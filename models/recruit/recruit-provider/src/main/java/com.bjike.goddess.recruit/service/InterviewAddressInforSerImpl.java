package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.entity.InterviewAddressInfor;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:05]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class InterviewAddressInforSerImpl extends ServiceImpl<InterviewAddressInfor, InterviewAddressInforDTO> implements InterviewAddressInforSer {

    /**
     * 分页查询面试信息地址
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<InterviewAddressInforBO> list(InterviewAddressInforDTO dto) throws SerException {
        List<InterviewAddressInfor> interviewAddressInforList = super.findByCis(dto, Boolean.TRUE);
        List<InterviewAddressInforBO> interviewAddressInforBOList = BeanTransform.copyProperties(interviewAddressInforList, InterviewAddressInforBO.class, true);
        return interviewAddressInforBOList;
    }

    /**
     * 保存面试信息地址
     *
     * @param interviewAddressInforTO
     * @return
     * @throws SerException
     */
    @Override
    public InterviewAddressInforBO save(InterviewAddressInforTO interviewAddressInforTO) throws SerException {
        InterviewAddressInfor interviewAddressInfor = BeanTransform.copyProperties(interviewAddressInforTO, InterviewAddressInfor.class, true);
        interviewAddressInfor = super.save(interviewAddressInfor);
        InterviewAddressInforBO interviewAddressInforBO = BeanTransform.copyProperties(interviewAddressInfor, InterviewAddressInforBO.class, true);
        return interviewAddressInforBO;
    }

    /**
     * 更新面试信息地址
     *
     * @param interviewAddressInforTO
     * @throws SerException
     */
    @Override
    public void update(InterviewAddressInforTO interviewAddressInforTO) throws SerException {
        InterviewAddressInfor interviewAddressInfor = BeanTransform.copyProperties(interviewAddressInforTO, InterviewAddressInfor.class, true);
        super.update(interviewAddressInfor);
    }
}
