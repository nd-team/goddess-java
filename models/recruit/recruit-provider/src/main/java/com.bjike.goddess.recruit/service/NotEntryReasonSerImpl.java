package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import org.springframework.stereotype.Service;

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
    public List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException {
        List<NotEntryReason> notEntryReasonList = super.findByCis(dto, Boolean.TRUE);
        List<NotEntryReasonBO> notEntryReasonBOList = BeanTransform.copyProperties(notEntryReasonList, NotEntryReasonBO.class, true);
        return notEntryReasonBOList;
    }

    /**
     * 保存未入职原因
     *
     * @param notEntryReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public NotEntryReasonBO save(NotEntryReasonTO notEntryReasonTO) throws SerException {
        NotEntryReason notEntryReason = BeanTransform.copyProperties(notEntryReasonTO, NotEntryReason.class, true);
        notEntryReason = super.save(notEntryReason);
        NotEntryReasonBO notEntryReasonBO = BeanTransform.copyProperties(notEntryReason, NotEntryReasonBO.class, true);
        return notEntryReasonBO;
    }

    /**
     * 更新未入职原因
     *
     * @param notEntryReasonTO
     * @throws SerException
     */
    @Override
    public void update(NotEntryReasonTO notEntryReasonTO) throws SerException {
        NotEntryReason notEntryReason = BeanTransform.copyProperties(notEntryReasonTO, NotEntryReason.class, true);
        super.update(notEntryReason);
    }
}
