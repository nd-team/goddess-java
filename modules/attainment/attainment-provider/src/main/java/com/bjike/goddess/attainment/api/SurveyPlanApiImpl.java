package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyPlanBO;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.service.SurveyPlanSer;
import com.bjike.goddess.attainment.to.SurveyPlanTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研计划业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyPlanApiImpl")
public class SurveyPlanApiImpl implements SurveyPlanAPI {

    @Autowired
    private SurveyPlanSer surveyPlanSer;

    @Override
    public SurveyPlanBO save(SurveyPlanTO to) throws SerException {
        return surveyPlanSer.save(to);
    }

    @Override
    public SurveyPlanBO update(SurveyPlanTO to) throws SerException {
        return surveyPlanSer.update(to);
    }

    @Override
    public SurveyPlanBO delete(String id) throws SerException {
        return surveyPlanSer.delete(id);
    }

    @Override
    public List<SurveyPlanBO> findByDemand(String demand_id) throws SerException {
        return surveyPlanSer.findByDemand(demand_id);
    }

    @Override
    public SurveyPlanBO findBOById(String id) throws SerException {
        return surveyPlanSer.findBOById(id);
    }

    @Override
    public List<SurveyPlanBO> maps(SurveyPlanDTO dto) throws SerException {
        return surveyPlanSer.maps(dto);
    }

    @Override
    public Long getTotal() throws SerException {
        return surveyPlanSer.getTotal();
    }
}