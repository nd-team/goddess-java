package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitProBO;
import com.bjike.goddess.recruit.to.RecruitProTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘方案
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 17:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("recruitProApiImpl")
public class RecruitProApiImpl implements RecruitProAPI {
    @Override
    public List<RecruitProBO> list(RecruitProTO dto) throws SerException {
        return null;
    }

    @Override
    public RecruitProBO save(RecruitProTO recruitProTO) throws SerException {
        return null;
    }

    @Override
    public void remove(String id) throws SerException {

    }

    @Override
    public void update(RecruitProTO recruitProTO) throws SerException {

    }

    @Override
    public void yyEdit(RecruitProTO recruitProTO, Boolean pass) throws SerException {

    }

    @Override
    public void managerEdit(RecruitProTO recruitProTO, Boolean pass) throws SerException {

    }
}
