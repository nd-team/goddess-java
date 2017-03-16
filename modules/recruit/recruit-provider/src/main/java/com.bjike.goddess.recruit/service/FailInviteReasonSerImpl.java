package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.entity.FailInviteReason;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 08:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class FailInviteReasonSerImpl extends ServiceImpl<FailInviteReason, FailInviteReasonDTO> implements FailInviteReasonSer {

    /**
     * 分页查询未成功邀约原因
     *
     * @param failInviteReasonDTO
     * @return
     * @throws SerException
     */
    @Override
    public List<FailInviteReasonBO> list(FailInviteReasonDTO failInviteReasonDTO) throws SerException {
        List<FailInviteReason> failInviteReasonList = super.findByCis(failInviteReasonDTO);
        List<FailInviteReasonBO> failInviteReasonBOList = BeanTransform.copyProperties(failInviteReasonList, FailInviteReasonBO.class, true);
        return failInviteReasonBOList;
    }

    /**
     * 保存未邀约成功原因
     *
     * @param failInviteReasonTO
     * @return
     * @throws SerException
     */
    @Override
    public FailInviteReasonBO save(FailInviteReasonTO failInviteReasonTO) throws SerException {
        FailInviteReason failInviteReason = BeanTransform.copyProperties(failInviteReasonTO, FailInviteReason.class, true);
        failInviteReason = super.save(failInviteReason);
        FailInviteReasonBO failInviteReasonBO = BeanTransform.copyProperties(failInviteReason, FailInviteReasonBO.class, true);
        return failInviteReasonBO;
    }

    /**
     * 更新未邀约成功原因
     *
     * @param failInviteReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailInviteReasonTO failInviteReasonTO) throws SerException {
        FailInviteReason failInviteReason = BeanTransform.copyProperties(failInviteReasonTO, FailInviteReason.class, true);
        super.update(failInviteReason);
    }
}
