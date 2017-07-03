package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireUserBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireUser;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireUserTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 问卷调查历史记录业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyQuestionnaireUserSerImpl extends ServiceImpl<SurveyQuestionnaireUser, SurveyQuestionnaireUserDTO> implements SurveyQuestionnaireUserSer {

    @Autowired
    private SurveyActualizeSer surveyActualizeSer;
    @Autowired
    private UserAPI userAPI;

    private SurveyQuestionnaireUserBO transformBO(SurveyQuestionnaireUser entity) {
        SurveyQuestionnaireUserBO bo = BeanTransform.copyProperties(entity, SurveyQuestionnaireUserBO.class);
        bo.setActualizeId(entity.getActualize().getId());
        bo.setQuestionnaireName(entity.getActualize().getQuestionnaire());
        return bo;
    }

    private List<SurveyQuestionnaireUserBO> transformBOList(List<SurveyQuestionnaireUser> list) {
        List<SurveyQuestionnaireUserBO> bos = new ArrayList<>(list.size());
        for (SurveyQuestionnaireUser entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireUserBO save(SurveyQuestionnaireUserTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SurveyQuestionnaireUser entity = BeanTransform.copyProperties(to, SurveyQuestionnaireUser.class);
        entity.setUser(user.getUsername());
        entity.setActualize(surveyActualizeSer.findById(to.getActualizeId()));
        if (null == entity.getActualize())
            throw new SerException("调研实施对象不存在,无法保存");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireUserBO delete(String id) throws SerException {
        SurveyQuestionnaireUser entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyQuestionnaireUserBO> findByActualize(String actualizeId) throws SerException {
        SurveyQuestionnaireUserDTO dto = new SurveyQuestionnaireUserDTO();
        dto.getConditions().add(Restrict.eq("actualize.id", actualizeId));
        List<SurveyQuestionnaireUser> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}