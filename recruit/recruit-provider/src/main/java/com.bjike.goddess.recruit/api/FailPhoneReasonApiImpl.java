package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailPhoneReasonBO;
import com.bjike.goddess.recruit.dto.FailPhoneReasonDTO;
import com.bjike.goddess.recruit.entity.FailPhoneReason;
import com.bjike.goddess.recruit.to.FailPhoneReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:26]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("failPhoneReasonApiImpl")
public class FailPhoneReasonApiImpl implements FailPhoneReasonAPI {

    /**
     * 分页查询未成功通话原因
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<FailPhoneReasonBO> list(FailPhoneReasonDTO dto) throws SerException {
        return null;
    }

    @Override
    public FailPhoneReasonBO save(FailPhoneReasonTO failPhoneReasonTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(FailPhoneReasonTO failPhoneReasonTO) throws SerException {

    }
}
