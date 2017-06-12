package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FailPhoneReasonSerImpl extends ServiceImpl<FailPhoneReason, FailPhoneReasonDTO> implements FailPhoneReasonSer {

    /**
     * 分页查询未成功通话原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException {
        List<FailPhoneReason> list = super.findByPage(dto);
        List<FailPhoneReasonBO> listBO = BeanTransform.copyProperties(list, FailPhoneReasonBO.class);
        return listBO;
    }

    /**
     * 保存未成功通话原因
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public FailPhoneReasonBO save(FailPhoneReasonTO to) throws SerException {
        FailPhoneReason failFirstInterviewReason = BeanTransform.copyProperties(to, FailPhoneReason.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        FailPhoneReasonBO bo = BeanTransform.copyProperties(failFirstInterviewReason, FailPhoneReasonBO.class);
        return bo;
    }

    /**
     * 更新未成功通话原因
     *
     * @param to 未成功通话原因to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(FailPhoneReasonTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            FailPhoneReason model = super.findById(to.getId());
            if (model != null) {
                updateFailPhoneReason(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新未成功通话原因
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateFailPhoneReason(FailPhoneReasonTO to, FailPhoneReason model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除未成功通话原因
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(FailPhoneReason entity) throws SerException {
        super.remove(entity);
    }
}
