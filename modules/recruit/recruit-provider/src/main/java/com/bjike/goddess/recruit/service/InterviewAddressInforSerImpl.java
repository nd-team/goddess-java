package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.InterviewAddressInforBO;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.InterviewAddressInforDTO;
import com.bjike.goddess.recruit.entity.InterviewAddressInfor;
import com.bjike.goddess.recruit.to.InterviewAddressInforTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
     * 分页查询面试地址信息
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<InterviewAddressInforBO> list(InterviewAddressInforDTO dto) throws SerException {
        List<InterviewAddressInfor> list = super.findByPage(dto);
        List<InterviewAddressInforBO> listBO = BeanTransform.copyProperties(list, InterviewAddressInforBO.class);
        return listBO;
    }

    /**
     * 保存面试地址信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public InterviewAddressInforBO save(InterviewAddressInforTO to) throws SerException {
        InterviewAddressInfor failFirstInterviewReason = BeanTransform.copyProperties(to, InterviewAddressInfor.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        InterviewAddressInforBO bo = BeanTransform.copyProperties(failFirstInterviewReason, InterviewAddressInforBO.class);
        return bo;
    }

    /**
     * 更新面试地址信息
     *
     * @param to 面试地址信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(InterviewAddressInforTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            InterviewAddressInfor model = super.findById(to.getId());
            if (model != null) {
                updateInterviewAddressInfor(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新面试地址信息
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateInterviewAddressInfor(InterviewAddressInforTO to, InterviewAddressInfor model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除面试地址信息
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(InterviewAddressInfor entity) throws SerException {
        super.remove(entity);
    }
}
