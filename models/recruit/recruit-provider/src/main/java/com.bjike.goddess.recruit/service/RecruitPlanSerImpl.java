package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitPlan;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitPlanSerImpl extends ServiceImpl<RecruitPlan, RecruitPlanDTO> implements RecruitPlanSer {

    /**
     * 分页查询计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException {
        List<RecruitPlan> recruitPlanList = super.findByCis(dto);
        List<RecruitPlanBO> recruitWayBOList = BeanTransform.copyProperties(recruitPlanList, RecruitPlanBO.class, true);
        return recruitWayBOList;
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
        RecruitPlan recruitPlan = BeanTransform.copyProperties(recruitPlanTO, RecruitPlan.class, true);
        recruitPlan = super.save(recruitPlan);
        RecruitPlanBO recruitPlanBO = BeanTransform.copyProperties(recruitPlan, RecruitWayBO.class, true);
        return recruitPlanBO;
    }

    /**
     * 更新招聘计划
     *
     * @param recruitPlanTO
     * @throws SerException
     */
    @Override
    public void update(RecruitPlanTO recruitPlanTO) throws SerException {
        RecruitPlan recruitPlan = BeanTransform.copyProperties(recruitPlanTO, RecruitPlan.class, true);
        super.update(recruitPlan);
    }

    /**
     * 根据id删除招聘计划
     *
     * @param id
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
