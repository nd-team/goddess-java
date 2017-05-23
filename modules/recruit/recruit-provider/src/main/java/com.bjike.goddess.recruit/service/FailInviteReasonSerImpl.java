package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.entity.FailInviteReason;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FailInviteReasonBO> list(FailInviteReasonDTO dto) throws SerException {
        List<FailInviteReason> list = super.findByPage(dto);
        List<FailInviteReasonBO> listBO = BeanTransform.copyProperties(list, FailInviteReasonBO.class);
        return listBO;
    }

    /**
     * 保存未邀约成功原因
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public FailInviteReasonBO save(FailInviteReasonTO to) throws SerException {
        FailInviteReason model = BeanTransform.copyProperties(to, FailInviteReason.class, true);
        model = super.save(model);
        FailInviteReasonBO bo = BeanTransform.copyProperties(model, FailInviteReasonBO.class);
        return bo;
    }

    /**
     * 更新未邀约成功原因
     *
     * @param to 未邀约成功原因to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(FailInviteReasonTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            FailInviteReason model = super.findById(to.getId());
            if (model != null) {
                updateFailInviteReason(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新未邀约成功原因
     *
     * @param to 未邀约成功原因to
     * @param model 未邀约成功原因实体
     */
    private void updateFailInviteReason(FailInviteReasonTO to, FailInviteReason model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除未邀约成功原因
     * 
     * @param id
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
