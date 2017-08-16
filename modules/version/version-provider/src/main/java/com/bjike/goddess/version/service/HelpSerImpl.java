package com.bjike.goddess.version.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.version.bo.AnswerBO;
import com.bjike.goddess.version.bo.HelpBO;
import com.bjike.goddess.version.bo.HelpBO1;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.dto.VersionDTO;
import com.bjike.goddess.version.entity.Answer;
import com.bjike.goddess.version.entity.Help;
import com.bjike.goddess.version.entity.Version;
import com.bjike.goddess.version.to.AnswerTO;
import com.bjike.goddess.version.to.HelpTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 帮助与解答业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:07 ]
 * @Description: [ 帮助与解答业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "versionSerCache")
@Service
public class HelpSerImpl extends ServiceImpl<Help, HelpDTO> implements HelpSer {
    @Autowired
    private AnswerSer answerSer;
    @Autowired
    private VersionSer versionSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public HelpBO save(HelpTO to) throws SerException {
        Help entity = BeanTransform.copyProperties(to, Help.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, HelpBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(HelpTO to) throws SerException {
        Help entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        String name = entity.getName();
        Version version = entity.getVersion();
        entity = BeanTransform.copyProperties(to, Help.class, true);
        entity.setCreateTime(a);
        entity.setName(name);
        entity.setVersion(version);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<HelpBO> list(HelpDTO dto) throws SerException {
        String module = dto.getModule();
        VersionDTO versionDTO = new VersionDTO();
        versionDTO.getConditions().add(Restrict.eq("module", module));
        List<Version> versions = versionSer.findByCis(versionDTO);
        Set<String> set = new HashSet<>();
        for (Version version : versions) {
            set.add(version.getId());
        }
        if (!set.isEmpty()) {
            dto.getConditions().add(Restrict.in("version.id", set));
        }else {
            dto.getConditions().add(Restrict.in("version.id", "saf"));
        }
        dto.getSorts().add("createTime=desc");
        List<Help> list = super.findByCis(dto, true);
        List<HelpBO> boList = new ArrayList<>();
        for (Help help : list) {
            AnswerDTO answerDTO = new AnswerDTO();
            answerDTO.getConditions().add(Restrict.eq("help.id", help.getId()));
            HelpBO helpBO = BeanTransform.copyProperties(help, HelpBO.class);
            helpBO.setAnswerCount(answerSer.count(answerDTO));
            boList.add(helpBO);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        Help entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public HelpBO findByID(String id) throws SerException {
        Help entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, HelpBO.class);
    }

    @Override
    public Long count(HelpDTO dto) throws SerException {
        String module = dto.getModule();
        VersionDTO versionDTO = new VersionDTO();
        versionDTO.getConditions().add(Restrict.eq("module", module));
        List<Version> versions = versionSer.findByCis(versionDTO);
        Set<String> set = new HashSet<>();
        for (Version version : versions) {
            set.add(version.getId());
        }
        if (!set.isEmpty()) {
            dto.getConditions().add(Restrict.in("version.id", set));
        } else {
            dto.getConditions().add(Restrict.in("version.id", "q1"));
        }
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void answer(String id, AnswerTO answerTO) throws SerException {
        Help help = super.findById(id);
        if (help == null) {
            throw new SerException("该对象不存在");
        }
        Answer answer = BeanTransform.copyProperties(answerTO, Answer.class, true);
        answer.setHelp(help);
        answerSer.save(answer);
    }

    @Override
    public HelpBO1 findDetail(String id) throws SerException {
        Help help = super.findById(id);
        if (help == null) {
            throw new SerException("该对象不存在");
        }
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.getConditions().add(Restrict.eq("help.id", id));
        List<Answer> answers = answerSer.findByCis(answerDTO);
        List<AnswerBO> answerBOs=BeanTransform.copyProperties(answers,AnswerBO.class);
        HelpBO1 helpBO=BeanTransform.copyProperties(help,HelpBO1.class);
        helpBO.setAnswers(answerBOs);
        return helpBO;
    }
}