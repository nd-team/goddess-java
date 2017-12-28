package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.service.SurveyActualizeSer;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调研实施记录业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("surveyActualizeApiImpl")
public class SurveyActualizeApiImpl implements SurveyActualizeAPI {
    @Autowired
    private SurveyActualizeSer surveyActualizeSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return surveyActualizeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return surveyActualizeSer.guidePermission(guidePermissionTO);
    }


    @Override
    public SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        return surveyActualizeSer.save(to);
    }

    @Override
    public SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        return surveyActualizeSer.update(to);
    }

    @Override
    public SurveyActualizeBO delete(String id) throws SerException {
        return surveyActualizeSer.delete(id);
    }

    @Override
    public SurveyActualizeBO over(String id) throws SerException {
        return surveyActualizeSer.over(id);
    }

    @Override
    public List<SurveyActualizeBO> maps(SurveyActualizeDTO dto) throws SerException {
        return surveyActualizeSer.maps(dto);
    }

    @Override
    public SurveyActualizeBO getById(String id) throws SerException {
        return surveyActualizeSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return surveyActualizeSer.getTotal();
    }

    @Override
    public List<String> getName() throws SerException {
        return surveyActualizeSer.getName();
    }
}