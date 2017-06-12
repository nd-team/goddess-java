package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class NotEntryReasonSerImpl extends ServiceImpl<NotEntryReason, NotEntryReasonDTO> implements NotEntryReasonSer {

    /**
     * 分页查询未入职原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException {
        List<NotEntryReason> list = super.findByPage(dto);
        List<NotEntryReasonBO> listBO = BeanTransform.copyProperties(list, NotEntryReasonBO.class);
        return listBO;
    }

    /**
     * 保存未入职原因
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public NotEntryReasonBO save(NotEntryReasonTO to) throws SerException {
        NotEntryReason failFirstInterviewReason = BeanTransform.copyProperties(to, NotEntryReason.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        NotEntryReasonBO bo = BeanTransform.copyProperties(failFirstInterviewReason, NotEntryReasonBO.class);
        return bo;
    }

    /**
     * 更新未入职原因
     *
     * @param to 未入职原因to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(NotEntryReasonTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            NotEntryReason model = super.findById(to.getId());
            if (model != null) {
                updateNotEntryReason(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新未入职原因
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateNotEntryReason(NotEntryReasonTO to, NotEntryReason model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除未入职原因
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(NotEntryReason entity) throws SerException {
        super.remove(entity);
    }
}
