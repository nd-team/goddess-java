package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.FailFirstInterviewReasonBO;
import com.bjike.goddess.recruit.dto.FailFirstInterviewReasonDTO;
import com.bjike.goddess.recruit.to.FailFirstInterviewReasonTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:07]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("failFirstInterviewReasonApiImpl")
public class FailFirstInterviewReasonApiImpl implements FailFirstInterviewReasonAPI {
    @Override
    public List<FailFirstInterviewReasonBO> list(FailFirstInterviewReasonDTO dto) throws SerException {
        return null;
    }

    @Override
    public FailFirstInterviewReasonBO save(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(FailFirstInterviewReasonTO failFirstInterviewReasonTO) throws SerException {

    }
}
