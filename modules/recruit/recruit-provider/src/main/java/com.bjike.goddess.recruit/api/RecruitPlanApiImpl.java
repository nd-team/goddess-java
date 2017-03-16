package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:37]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitPlanApiImpl")
public class RecruitPlanApiImpl implements RecruitPlanAPI {
    @Override
    public List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException {
        return null;
    }

    @Override
    public RecruitPlanBO save(RecruitPlanTO recruitPlanTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(RecruitPlanTO recruitPlanTO) throws SerException {

    }
}
