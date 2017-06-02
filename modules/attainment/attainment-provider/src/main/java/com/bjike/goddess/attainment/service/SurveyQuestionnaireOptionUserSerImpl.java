package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SurveyQuestionnaireOptionUserBO;
import com.bjike.goddess.attainment.dto.SurveyQuestionnaireOptionUserDTO;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOption;
import com.bjike.goddess.attainment.entity.SurveyQuestionnaireOptionUser;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUserTO;
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
 * 问卷填写信息表业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyQuestionnaireOptionUserSerImpl extends ServiceImpl<SurveyQuestionnaireOptionUser, SurveyQuestionnaireOptionUserDTO> implements SurveyQuestionnaireOptionUserSer {

    @Autowired
    private SurveyQuestionnaireOptionSer optionSer;
    @Autowired
    private UserAPI userAPI;

    private SurveyQuestionnaireOptionUserBO transformBO(SurveyQuestionnaireOptionUser entity) {
        SurveyQuestionnaireOptionUserBO bo = BeanTransform.copyProperties(entity, SurveyQuestionnaireOptionUserBO.class);
        bo.setOption_id(entity.getOption().getId());
        bo.setOptionName(entity.getOption().getContent());
        return bo;
    }

    private List<SurveyQuestionnaireOptionUserBO> transformBOList(List<SurveyQuestionnaireOptionUser> list) {
        List<SurveyQuestionnaireOptionUserBO> bos = new ArrayList<>(list.size());
        for (SurveyQuestionnaireOptionUser entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireOptionUserBO save(SurveyQuestionnaireOptionUserTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SurveyQuestionnaireOptionUser entity = new SurveyQuestionnaireOptionUser();
        SurveyQuestionnaireOption option = optionSer.findById(to.getOption_id());
        if (null == option)
            throw new SerException("选项对象不存在,无法保存");
        entity.setUser(user.getUsername());
        entity.setOption(option);
        entity.setTableName(entity.getOption().getQuestionnaire().getQuestionnaire());
        super.save(entity);
        option.setBallot(option.getBallot() + 1);
        optionSer.update(option);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyQuestionnaireOptionUserBO delete(String id) throws SerException {
        SurveyQuestionnaireOptionUser entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyQuestionnaireOptionUserBO> findByOption(String option_id) throws SerException {
        SurveyQuestionnaireOptionUserDTO dto = new SurveyQuestionnaireOptionUserDTO();
        dto.getConditions().add(Restrict.eq("option.id", option_id));
        List<SurveyQuestionnaireOptionUser> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}