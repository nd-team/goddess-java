package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.NotEntryReasonBO;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.to.NotEntryReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("notEntryReasonApiImpl")
public class NotEntryReasonApiImpl implements NotEntryReasonAPI {
    @Override
    public List<NotEntryReasonBO> list(NotEntryReasonDTO dto) throws SerException {
        return null;
    }

    @Override
    public NotEntryReasonBO save(NotEntryReasonTO notEntryReasonTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(NotEntryReasonTO notEntryReasonTO) throws SerException {

    }
}
