package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.service.SurveyDemandSer;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研需求业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyDemandApiImpl")
public class SurveyDemandApiImpl implements SurveyDemandAPI {

    @Autowired
    private SurveyDemandSer surveyDemandSer;

    @Override
    public SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        return surveyDemandSer.save(to);
    }

    @Override
    public SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        return surveyDemandSer.update(to);
    }

    @Override
    public SurveyDemandBO delete(String id) throws SerException {
        return surveyDemandSer.delete(id);
    }

    @Override
    public SurveyDemandBO close(CloseDemandTO to) throws SerException {
        return surveyDemandSer.close(to);
    }

    @Override
    public List<SurveyDemandBO> findByStatus(SurveyStatus status) throws SerException {
        return surveyDemandSer.findByStatus(status);
    }

    @Override
    public List<SurveyDemandBO> maps(SurveyDemandDTO dto) throws SerException {
        return surveyDemandSer.maps(dto);
    }

    @Override
    public SurveyDemandBO getById(String id) throws SerException {
        return surveyDemandSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return surveyDemandSer.getTotal();
    }
}