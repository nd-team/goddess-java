package com.bjike.goddess.version.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.version.bo.VersionBO;
import com.bjike.goddess.version.dto.AnswerDTO;
import com.bjike.goddess.version.dto.HelpDTO;
import com.bjike.goddess.version.dto.VersionDTO;
import com.bjike.goddess.version.entity.Answer;
import com.bjike.goddess.version.entity.Help;
import com.bjike.goddess.version.entity.Version;
import com.bjike.goddess.version.to.HelpTO;
import com.bjike.goddess.version.to.VersionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 版本信息业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "versionSerCache")
@Service
public class VersionSerImpl extends ServiceImpl<Version, VersionDTO> implements VersionSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private HelpSer helpSer;
    @Autowired
    private AnswerSer answerSer;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public VersionBO save(VersionTO to) throws SerException {
        Version entity = BeanTransform.copyProperties(to, Version.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, VersionBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(VersionTO to) throws SerException {
        Version entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        String module = entity.getModule();
        entity = BeanTransform.copyProperties(to, Version.class, true);
        entity.setCreateTime(a);
        entity.setModule(module);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<VersionBO> list(VersionDTO dto) throws SerException {
        String module = dto.getModule();
        dto.getConditions().add(Restrict.eq("module", module));
        dto.getSorts().add("createTime=desc");
        List<Version> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, VersionBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        Version entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        HelpDTO helpDTO=new HelpDTO();
        helpDTO.getConditions().add(Restrict.eq("version.id",id));
        List<Help> helps=helpSer.findByCis(helpDTO);
        for (Help help:helps){
            String help_id=help.getId();
            AnswerDTO answerDTO=new AnswerDTO();
            answerDTO.getConditions().add(Restrict.eq("help.id",help_id));
            List<Answer> answers=answerSer.findByCis(answerDTO);
            answerSer.remove(answers);
            helpSer.remove(help_id);
        }
        super.remove(id);
    }

    @Override
    public VersionBO findByID(String id) throws SerException {
        Version entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, VersionBO.class);
    }

    @Override
    public Long count(VersionDTO dto) throws SerException {
        String module = dto.getModule();
        dto.getConditions().add(Restrict.eq("module", module));
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void ask(String id, HelpTO helpTO) throws SerException {
        UserBO userBO = userAPI.currentUser();
        Version version = super.findById(id);
        if (version == null) {
            throw new SerException("该对象不存在");
        }
        Help help = BeanTransform.copyProperties(helpTO, Help.class, true);
        help.setName(userBO.getUsername());
        help.setVersion(version);
        helpSer.save(help);
    }

    @Override
    public String findDetail(String id) throws SerException {
        Version version = super.findById(id);
        if (version == null) {
            throw new SerException("该对象不存在");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div><div align=\"center\"><strong>制度制定/修订序言模板</strong></div>");
        sb.append("<strong>制度名：</strong>" + version.getName() + "</br>");
        sb.append("<strong>版本号：</strong>" + version.getVersion() + "</br>");
        sb.append("<strong>采纳意见：</strong>" + version.getAdvice() + "</br>");
        sb.append("<strong>制定/修订人：</strong>" + version.getDesigner() + "</br>");
        sb.append("<strong>责任审核：</strong>" + version.getAudit() + "</br>");
        sb.append("<strong>制定/修订背景：</strong>" + version.getBackground() + "</br>");
        sb.append("<strong>制作/修订内容：</strong>" + version.getContent() + "</div>");
        return sb.toString();
    }
}