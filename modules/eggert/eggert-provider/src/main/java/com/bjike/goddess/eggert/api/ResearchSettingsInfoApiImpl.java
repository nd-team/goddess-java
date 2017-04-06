package com.bjike.goddess.eggert.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.bo.ResearchSettingsInfoBO;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.entity.ExamQuestions;
import com.bjike.goddess.eggert.service.ResearchSettingsInfoSer;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import com.bjike.goddess.eggert.to.ResearchSettingsInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 调研设置信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("researchSettingsInfoApiImpl")
public class ResearchSettingsInfoApiImpl implements ResearchSettingsInfoAPI {
    @Autowired
    private ResearchSettingsInfoSer researchSettingsInfoSer;

    @Override
    public List<ResearchSettingsInfoBO> findListResearchSettingsInfo(ResearchSettingsInfoDTO researchSettingsInfoDTO) throws SerException {
        return researchSettingsInfoSer.findListResearchSettingsInfo(researchSettingsInfoDTO);
    }

    @Override
    public ResearchSettingsInfoBO insertResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        return researchSettingsInfoSer.insertResearchSettingsInfo(researchSettingsInfoTO);
    }

    @Override
    public ResearchSettingsInfoBO editResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        return researchSettingsInfoSer.editResearchSettingsInfo(researchSettingsInfoTO);
    }

    @Override
    public void removeResearchSettingsInfo(String id) throws SerException {
        researchSettingsInfoSer.removeResearchSettingsInfo(id);
    }
    @Override
    public ExamQuestionsBO setExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        return researchSettingsInfoSer.setExamQuestions(examQuestionsTO);
    }
}