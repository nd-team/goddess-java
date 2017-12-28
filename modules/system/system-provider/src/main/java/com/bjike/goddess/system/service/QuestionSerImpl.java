package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.bo.AuswerBO;
import com.bjike.goddess.system.bo.QuestionBO;
import com.bjike.goddess.system.bo.QuestionBO1;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.entity.Auswer;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.entity.Question;
import com.bjike.goddess.system.to.AuswerTO;
import com.bjike.goddess.system.to.QuestionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:48 ]
 * @Description: [ da业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class QuestionSerImpl extends ServiceImpl<Question, QuestionDTO> implements QuestionSer {
    @Autowired
    private AuswerSer auswerSer;

    @Override
    public Long count(QuestionDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public QuestionBO getOne(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            Question question = super.findById(id);
            return BeanTransform.copyProperties(question, QuestionBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<QuestionBO> list(QuestionDTO dto) throws SerException {
        List<Question> list = super.findByCis(dto, true);
        List<QuestionBO> boList = new ArrayList<>();
        for (Question question : list) {
            AuswerDTO answerDTO = new AuswerDTO();
            answerDTO.getConditions().add(Restrict.eq("question.id", question.getId()));
            QuestionBO questionBO = BeanTransform.copyProperties(question, QuestionBO.class);
            questionBO.setAnswerCount(auswerSer.count(answerDTO));
            boList.add(questionBO);
        }
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QuestionBO insert(QuestionTO to) throws SerException {
        Question question = BeanTransform.copyProperties(to, QuestionBO.class, true);
        question.setCreateTime(LocalDateTime.now());
        super.save(question);
        return BeanTransform.copyProperties(question, QuestionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QuestionBO edit(QuestionTO to) throws SerException {
        Question question = super.findById(to.getId());
        LocalDateTime createTime = question.getCreateTime();
        String name = question.getName();
        FeatureList featureList = question.getFeatureList();
        question = BeanTransform.copyProperties(to, Question.class, true);
        question.setCreateTime(createTime);
        question.setName(name);
        question.setFeatureList(featureList);
        question.setModifyTime(LocalDateTime.now());
        super.update(question);
        return BeanTransform.copyProperties(question, QuestionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public QuestionBO1 detail(String id) throws SerException {
        Question question = super.findById(id);
        if (question == null) {
            throw new SerException("该对象不存在");
        }
        AuswerDTO auswerDTO = new AuswerDTO();
        auswerDTO.getConditions().add(Restrict.eq("question.id", id));
        List<Auswer> auswers = auswerSer.findByCis(auswerDTO);
        List<AuswerBO> auswerBOS=BeanTransform.copyProperties(auswers,AuswerBO.class);
        QuestionBO1 helpBO=BeanTransform.copyProperties(question,QuestionBO1.class);
        helpBO.setAuswerBOS(auswerBOS);
        return helpBO;
    }

    @Override
    public void answer(String id, AuswerTO auswerTO) throws SerException {
        Question question = super.findById(id);
        if (question == null) {
            throw new SerException("不存在");
        }
        Auswer auswer = BeanTransform.copyProperties(auswerTO, Auswer.class, true);
        auswer.setQuestion(question);
        auswerSer.save(auswer);

    }
}