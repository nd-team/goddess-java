package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.entity.FailFirstInterviewReason;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 08:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FailFirstInterviewReasonSerImpl extends ServiceImpl<FailFirstInterviewReason, FailFirstInterviewReasonDTO> implements FailFirstInterviewReasonSer {

    /**
     * 分页查询未应约初试原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FailFirstInterviewReasonBO> list(FailFirstInterviewReasonDTO dto) throws SerException {
        List<FailFirstInterviewReason> list = super.findByPage(dto);
        List<FailFirstInterviewReasonBO> listBO = BeanTransform.copyProperties(list, FailFirstInterviewReasonBO.class);
        return listBO;
    }

    /**
     * 保存未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public FailFirstInterviewReasonBO save(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {
        FailFirstInterviewReason failFirstInterviewReason = BeanTransform.copyProperties(failFirstInterviewReasonTO, FailFirstInterviewReason.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        FailFirstInterviewReasonBO bo = BeanTransform.copyProperties(failFirstInterviewReason, FailFirstInterviewReasonBO.class);
        return bo;
    }

    /**
     * 更新未应约初试原因
     *
     * @param failFirstInterviewReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {
        FailFirstInterviewReason entity = BeanTransform.copyProperties(failFirstInterviewReasonTO, FailFirstInterviewReason.class, true);
        super.update(entity);
    }

    /**
     * 删除未应约初试原因
     *
     * @param entity
     * @throws SerException
     */
    @Override
    public void remove(FailFirstInterviewReason entity) throws SerException {
        super.remove(entity);
    }
}
