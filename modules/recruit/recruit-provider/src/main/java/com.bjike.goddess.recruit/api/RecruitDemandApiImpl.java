package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.service.RecruitDemandSer;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RecruitDemandSer recruitDemandSer;

    /**
     * 分页查询招聘需求
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException {
        return recruitDemandSer.list(dto);
    }

    /**
     * 保存招聘需求
     *
     * @param recruitDemandTO
     * @return
     * @throws SerException
     */
    @Override
    public RecruitDemandBO save(RecruitDemandTO recruitDemandTO) throws SerException {
        return recruitDemandSer.save(recruitDemandTO);
    }

    /**
     * 根据id删除
     *
     * @param id 招聘需求id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        recruitDemandSer.remove(id);
    }

    /**
     * 更新招聘需求
     *
     * @param recruitDemandTO
     * @throws SerException
     */
    @Override
    public void update(RecruitDemandTO recruitDemandTO) throws SerException {
        recruitDemandSer.update(recruitDemandTO);
    }

}
