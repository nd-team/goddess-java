package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.PlanSonBO;
import com.bjike.goddess.staffing.dto.PlanSonDTO;
import com.bjike.goddess.staffing.service.PlanSonSer;
import com.bjike.goddess.staffing.to.PlanSonTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人数配置-计划子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:58 ]
 * @Description: [ 人数配置-计划子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("planSonApiImpl")
public class PlanSonApiImpl implements PlanSonAPI {
    @Autowired
    private PlanSonSer planSonSer;

    @Override
    public List<PlanSonBO> list(PlanSonDTO dto) throws SerException {
        return planSonSer.list(dto);
    }

    @Override
    public PlanSonBO save(PlanSonTO to) throws SerException {
        return planSonSer.save(to);
    }

    @Override
    public void edit(PlanSonTO to) throws SerException {
        planSonSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        planSonSer.delete(id);
    }

    @Override
    public PlanSonBO findByID(String id) throws SerException {
        return planSonSer.findByID(id);
    }

    @Override
    public Long count(PlanSonDTO dto) throws SerException {
        return planSonSer.count(dto);
    }
}