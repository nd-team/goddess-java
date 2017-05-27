package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.dto.SkillAnalyseDTO;
import com.bjike.goddess.attainment.service.SkillAnalyseSer;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 技能分析表业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("skillAnalyseApiImpl")
public class SkillAnalyseApiImpl implements SkillAnalyseAPI {

    @Autowired
    private SkillAnalyseSer skillAnalyseSer;

    @Override
    public SkillAnalyseBO save(SkillAnalyseTO to) throws SerException {
        return skillAnalyseSer.save(to);
    }

    @Override
    public SkillAnalyseBO update(SkillAnalyseTO to) throws SerException {
        return skillAnalyseSer.update(to);
    }

    @Override
    public SkillAnalyseBO delete(String id) throws SerException {
        return skillAnalyseSer.delete(id);
    }

    @Override
    public List<SkillAnalyseBO> maps(SkillAnalyseDTO dto) throws SerException {
        return skillAnalyseSer.maps(dto);
    }

    @Override
    public SkillAnalyseBO getById(String id) throws SerException {
        return skillAnalyseSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return skillAnalyseSer.getTotal();
    }
}