package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitPlan;
import com.bjike.goddess.recruit.service.RecruitPlanSer;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RecruitPlanSer recruitPlanSer;

    /**
     * 根据id查询招聘计划
     *
     * @param id 招聘计划唯一标识
     * @return class RecruitPlanBO
     * @throws SerException
     */
    @Override
    public RecruitPlanBO findById(String id) throws SerException {
        RecruitPlan model = recruitPlanSer.findById(id);
        return BeanTransform.copyProperties(model, RecruitPlanBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 招聘计划dto
     * @throws SerException
     */
    @Override
    public Long count(RecruitPlanDTO dto) throws SerException {
        return recruitPlanSer.count(dto);
    }

    /**
     * 分页查询招聘计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException {
        return recruitPlanSer.list(dto);
    }

    /**
     * 保存招聘计划
     *
     * @param recruitPlanTO
     * @return
     * @throws SerException
     */
    @Override
    public RecruitPlanBO save(RecruitPlanTO recruitPlanTO) throws SerException {
        return recruitPlanSer.save(recruitPlanTO);
    }

    /**
     * 根据id删除招聘计划
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        recruitPlanSer.remove(id);
    }

    /**
     * 更新招聘计划
     *
     * @param recruitPlanTO
     * @throws SerException
     */
    @Override
    public void update(RecruitPlanTO recruitPlanTO) throws SerException {
        recruitPlanSer.update(recruitPlanTO);
    }
}
