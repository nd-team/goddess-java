package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailInviteReasonBO;
import com.bjike.goddess.recruit.dto.FailInviteReasonDTO;
import com.bjike.goddess.recruit.to.FailInviteReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("failInviteReasonApiImpl")
public class FailInviteReasonApiImpl implements FailInviteReasonAPI {


    @Override
    public List<FailInviteReasonBO> list(FailInviteReasonDTO failInviteReasonDTO) throws SerException {
        return null;
    }

    @Override
    public FailInviteReasonBO save(FailInviteReasonTO failInviteReasonTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(FailInviteReasonTO failInviteReasonTO) throws SerException {

    }
}
