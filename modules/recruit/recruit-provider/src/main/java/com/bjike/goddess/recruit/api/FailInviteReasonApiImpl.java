package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.entity.FailInviteReason;
import com.bjike.goddess.recruit.service.FailInviteReasonSer;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 未邀约成功原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("failInviteReasonApiImpl")
public class FailInviteReasonApiImpl implements FailInviteReasonAPI {

    @Autowired
    private FailInviteReasonSer failInviteReasonSer;

    /**
     * 根据id查询未邀约成功原因
     *
     * @param id 未邀约成功原因唯一标识
     * @return class FailInviteReasonBO
     * @throws SerException
     */
    @Override
    public FailInviteReasonBO findById(String id) throws SerException {
        FailInviteReason model = failInviteReasonSer.findById(id);
        return BeanTransform.copyProperties(model, FailInviteReasonBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 未邀约成功原因dto
     * @throws SerException
     */
    @Override
    public Long count(FailInviteReasonDTO dto) throws SerException {
        return failInviteReasonSer.count(dto);
    }

    /**
     * 分页查询未邀约成功原因
     *
     * @param failInviteReasonDTO
     * @return
     * @throws SerException
     */
    @Override
    @Transactional
    public List<FailInviteReasonBO> list(FailInviteReasonDTO failInviteReasonDTO) throws SerException {
        return failInviteReasonSer.list(failInviteReasonDTO);
    }

    /**
     * 保存未邀约成功原因
     *
     * @param failInviteReasonTO
     * @return
     * @throws SerException
     */
    @Override
    @Transactional
    public FailInviteReasonBO save(FailInviteReasonTO failInviteReasonTO) throws SerException {
        return failInviteReasonSer.save(failInviteReasonTO);
    }

    /**
     * 根据id删除未邀约成功原因
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        failInviteReasonSer.remove(id);
    }

    /**
     * 更新未邀约成功原因
     *
     * @param failInviteReasonTO
     * @throws SerException
     */
    @Override
    public void update(FailInviteReasonTO failInviteReasonTO) throws SerException {
        failInviteReasonSer.update(failInviteReasonTO);
    }
}
