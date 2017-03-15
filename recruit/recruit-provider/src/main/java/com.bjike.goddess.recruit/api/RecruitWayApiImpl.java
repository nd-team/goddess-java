package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitWayApiImpl")
public class RecruitWayApiImpl implements RecruitWayAPI {
    @Override
    public List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException {
        return null;
    }

    @Override
    public RecruitWayBO save(RecruitWayTO recruitWayTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(RecruitWayTO recruitWayTO) throws SerException {

    }
}
