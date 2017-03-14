package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import org.springframework.stereotype.Service;

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
    public List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException {
        List<FailPhoneReason> failPhoneReasonList = super.findByCis(dto, Boolean.TRUE);
        List<FailPhoneReasonBO> failPhoneReasonBOList = BeanTransform.copyProperties(failPhoneReasonList, FailPhoneReasonBO.class, true);
        return failPhoneReasonBOList;
    }

    /**
     * 保存未成功通话原因
     *
     * @param failPhoneReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public FailPhoneReasonBO save(FailPhoneReasonTO failPhoneReasonTO) throws SerException {
        FailPhoneReason failPhoneReason = BeanTransform.copyProperties(failPhoneReasonTO, FailPhoneReason.class, true);
        failPhoneReason = super.save(failPhoneReason);
        FailPhoneReasonBO failPhoneReasonBO = BeanTransform.copyProperties(failPhoneReason, FailPhoneReasonBO.class, true);
        return failPhoneReasonBO;
    }

    /**
     * 更新未成功通话原因
     *
     * @param failPhoneReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailPhoneReasonTO failPhoneReasonTO) throws SerException {
        FailPhoneReason failPhoneReason = BeanTransform.copyProperties(failPhoneReasonTO, FailPhoneReason.class, true);
        super.update(failPhoneReason);
    }

    /**
     * 根据id删除未成功通话原因
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
