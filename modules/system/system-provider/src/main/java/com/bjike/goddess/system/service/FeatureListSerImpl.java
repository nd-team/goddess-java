package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.dto.QuestionDTO;
import com.bjike.goddess.system.entity.Auswer;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.entity.Question;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.QuestionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能列表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class FeatureListSerImpl extends ServiceImpl<FeatureList, FeatureListDTO> implements FeatureListSer {
    @Autowired
    private QuestionSer questionSer;
    @Autowired
    private AuswerSer auswerSer;
    @Autowired
    private UserAPI userAPI;

    @Override
    public Long count(FeatureListDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public FeatureListBO getOne(String id) throws SerException {
        FeatureList featureList = super.findById(id);
        return BeanTransform.copyProperties(featureList, FeatureListBO.class);
    }

    @Override
    public List<FeatureListBO> list(FeatureListDTO dto) throws SerException {
        List<FeatureList> featureLists = super.findByCis(dto);
        List<FeatureListBO> featureListBOS = BeanTransform.copyProperties(featureLists, FeatureListBO.class);
        return featureListBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FeatureListBO insert(FeatureListTO to) throws SerException {
        FeatureList featureList = BeanTransform.copyProperties(to, FeatureList.class, true);
        featureList.setCreateTime(LocalDateTime.now());
        super.save(featureList);
        return BeanTransform.copyProperties(featureList, FeatureListBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FeatureListBO edit(FeatureListTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            FeatureList featureList = super.findById(to.getId());
            LocalDateTime createTime = featureList.getCreateTime();
            featureList = BeanTransform.copyProperties(to, FeatureList.class, true);
            featureList.setCreateTime(createTime);
            featureList.setModifyTime(LocalDateTime.now());
            super.update(featureList);
            return BeanTransform.copyProperties(featureList, FeatureListBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        FeatureList entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        QuestionDTO dto = new QuestionDTO();
        dto.getConditions().add(Restrict.eq("featureList.id", id));
        List<Question> questions = questionSer.findByCis(dto);
        for (Question question : questions) {
            String help_id = question.getId();
            AuswerDTO auswerDTO = new AuswerDTO();
            auswerDTO.getConditions().add(Restrict.eq("question.id", help_id));
            List<Auswer> answers = auswerSer.findByCis(auswerDTO);
            auswerSer.remove(answers);
            questionSer.remove(help_id);
        }
        super.remove(id);

    }


    @Override
    public void ask(String id, QuestionTO questionTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        FeatureList entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        Question help = BeanTransform.copyProperties(questionTO, Question.class, true);
        help.setName(userBO.getUsername());
        help.setFeatureList(entity);
        questionSer.save(help);
    }

    @Override
    public FeatureListBO findDetail(String id) throws SerException {
        FeatureList entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, FeatureListBO.class);
    }

    @Override
    public List<String> getFeatureName() throws SerException {
        List<FeatureList> featureLists = super.findAll();
        if (null != featureLists && featureLists.size() > 0) {
            List<String> list = featureLists.stream().map(FeatureList::getName).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public String getPurpose(String name) throws SerException {
        if (StringUtils.isNotBlank(name)) {
            FeatureListDTO dto = new FeatureListDTO();
            dto.getConditions().add(Restrict.eq("name", name));
            List<FeatureList> featureLists = super.findByCis(dto);
            if (null != featureLists && featureLists.size() > 0) {
                FeatureList featureList = featureLists.get(0);
                return featureList.getPurpose();
            }
        }
        return null;
    }

    @Override
    public String getVersion(String name) throws SerException {
        if (StringUtils.isNotBlank(name)) {
            FeatureListDTO dto = new FeatureListDTO();
            dto.getConditions().add(Restrict.eq("name", name));
            List<FeatureList> featureLists = super.findByCis(dto);
            if (null != featureLists && featureLists.size() > 0) {
                FeatureList featureList = featureLists.get(0);
                return featureList.getVersion();
            }
        }
        return null;
    }
}