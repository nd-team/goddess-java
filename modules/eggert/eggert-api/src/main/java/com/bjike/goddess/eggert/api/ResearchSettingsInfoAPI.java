package com.bjike.goddess.eggert.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.eggert.bo.ExamQuestionsBO;
import com.bjike.goddess.eggert.bo.ResearchSettingsInfoBO;
import com.bjike.goddess.eggert.dto.ResearchSettingsInfoDTO;
import com.bjike.goddess.eggert.to.ExamQuestionsTO;
import com.bjike.goddess.eggert.to.ResearchSettingsInfoTO;

import java.util.List;

/**
 * 调研设置信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-31 05:22 ]
 * @Description: [ 调研设置信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ResearchSettingsInfoAPI {
    /**
     * 获取调研设置信息
     *
     * @param researchSettingsInfoDTO 调研设置信息dto
     * @return class researchSettingsInfoBO
     * @throws SerException
     */
    default List<ResearchSettingsInfoBO> findListResearchSettingsInfo(ResearchSettingsInfoDTO researchSettingsInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加调研设置信息
     *
     * @param researchSettingsInfoTO 调研设置信息数据to
     * @throws SerException
     */
    default ResearchSettingsInfoBO insertResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑调研设置信息
     *
     * @param researchSettingsInfoTO 调研设置信息数据to
     * @return class researchSettingsInfoBO
     * @throws SerException
     */
    default ResearchSettingsInfoBO editResearchSettingsInfo(ResearchSettingsInfoTO researchSettingsInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除调研设置信息
     *
     * @param id
     * @throws SerException
     */
    default void removeResearchSettingsInfo(String id) throws SerException {

    }
    /**
     * 设置考题
     *
     * @param examQuestionsTO 设置考题数据to
     * @return class ExamQuestionsBO
     * @throws SerException
     */
    default ExamQuestionsBO setExamQuestions(ExamQuestionsTO examQuestionsTO) throws SerException {
        return null;
    }

}