package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyQuestionnaireOptionUserAPI;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionUserTO;
import com.bjike.goddess.attainment.vo.SurveyQuestionnaireOptionUserVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 问卷填写信息表
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:35 ]
 * @Description: [ 问卷填写信息表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyquestionnaireoptionuser")
public class SurveyQuestionnaireOptionUserAct {

    @Autowired
    private SurveyQuestionnaireOptionUserAPI surveyQuestionnaireOptionUserAPI;

    /**
     * 保存
     *
     * @param to 问卷填写信息传输对象
     * @return class SurveyQuestionnaireOptionUserVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyQuestionnaireOptionUserTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionUserAPI.save(to), SurveyQuestionnaireOptionUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 问卷填写信息数据id
     * @return class SurveyQuestionnaireOptionUserVO
     * @version v1
     */
    @PutMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionUserAPI.delete(id), SurveyQuestionnaireOptionUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据选项查询问卷填写记录
     *
     * @param option_id 调研问卷选项数据id
     * @return class SurveyQuestionnaireOptionUserVO
     * @version v1
     */
    @GetMapping("v1/findByOption/{option_id}")
    public Result findByOption(@PathVariable String option_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionUserAPI.findByOption(option_id), SurveyQuestionnaireOptionUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}