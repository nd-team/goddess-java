package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.*;
import com.bjike.goddess.attainment.dto.SurveyPlanDTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyActualizesTO;
import com.bjike.goddess.attainment.to.SurveyPlanTO;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUsersTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyPlanAPI {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 调研计划传输对象
     * @return
     * @throws SerException
     */
    default SurveyPlanBO save(SurveyPlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研计划传输对象
     * @return
     * @throws SerException
     */
    default SurveyPlanBO update(SurveyPlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研计划数据id
     * @return
     * @throws SerException
     */
    default SurveyPlanBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据需求查询调研计划
     *
     * @param demand_id 调研需求数据id
     * @return
     * @throws SerException
     */
    default List<SurveyPlanBO> findByDemand(String demand_id) throws SerException {
        return null;
    }

    /**
     * 根据id获取数据传输对象
     *
     * @param id 调研计划数据id
     * @return
     * @throws SerException
     */
    default SurveyPlanBO findBOById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研计划数据传输对象
     * @return
     * @throws SerException
     */
    default List<SurveyPlanBO> maps(SurveyPlanDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 获取调研计划
     */
    default List<SurPlanbo> getSurveyPlan() throws SerException {
        return null;
    }

    /**
     * 建立问卷
     */
    default List<SurveyActualizesBO> questionnaire(SurveyActualizesTO to) throws SerException {
        return null;
    }

    /**
     * 查看问卷
     */
    default List<SurveyQuestionnairesBO> getQuestionnaire(String id) throws SerException {
        return null;
    }

    /**
     * 问卷调研
     */
    default List<SurveyQuestionnaireOptionUsersBO> editQuestionnaire(SurveyQuestionnaireOptionUsersTO to) throws SerException {
        return null;
    }
}