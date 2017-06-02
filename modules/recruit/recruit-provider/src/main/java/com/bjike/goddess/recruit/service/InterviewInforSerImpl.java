package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        List<InterviewInfor> list = super.findByPage(dto);
        List<InterviewInforBO> listBO = BeanTransform.copyProperties(list, InterviewInforBO.class);
        return listBO;
    }

    /**
     * 保存面试信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public InterviewInforBO save(InterviewInforTO to) throws SerException {
        InterviewInfor interviewInfor = BeanTransform.copyProperties(to, InterviewInfor.class, true);
        interviewInfor = super.save(interviewInfor);
        InterviewInforBO interviewInforBO = BeanTransform.copyProperties(interviewInfor, InterviewInforBO.class);
        return interviewInforBO;
    }

    /**
     * 更新面试信息
     *
     * @param to 面试信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InterviewInforTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InterviewInfor model = super.findById(to.getId());
            if (model != null) {
                updateInterviewInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新面试信息
     *
     * @param to 面试信息to
     * @param model 面试信息实体
     */
    private void updateInterviewInfor(InterviewInforTO to, InterviewInfor model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除面试信息
     *
     * @param id 面试信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
