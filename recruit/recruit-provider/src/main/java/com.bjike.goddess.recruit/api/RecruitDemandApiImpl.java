package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:34]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitDemandApiImpl")
public class RecruitDemandApiImpl implements RecruitDemandAPI {
    @Override
    public List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException {
        return null;
    }

    @Override
    public RecruitDemandBO save(RecruitDemandTO recruitDemandTO) throws SerException {
        return null;
    }


    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(RecruitDemandBO recruitDemandBO) throws SerException {

    }
}
