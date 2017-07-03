package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyQuestionnaireOptionAPI;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireOptionTO;
import com.bjike.goddess.attainment.vo.SurveyQuestionnaireOptionVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 调研表问题选项
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:26 ]
 * @Description: [ 调研表问题选项 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyquestionnaireoption")
public class SurveyQuestionnaireOptionAct {

    @Autowired
    private SurveyQuestionnaireOptionAPI surveyQuestionnaireOptionAPI;

    /**
     * 添加
     *
     * @param to 调研问题选项传输对象
     * @return class SurveyQuestionnaireOptionVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyQuestionnaireOptionTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionAPI.save(to), SurveyQuestionnaireOptionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研问题选项传输对象
     * @return class SurveyQuestionnaireOptionVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyQuestionnaireOptionTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionAPI.update(to), SurveyQuestionnaireOptionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研问题选项数据id
     * @return class SurveyQuestionnaireOptionVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionAPI.delete(id), SurveyQuestionnaireOptionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据调研问题查询选项
     *
     * @param id 调研问题id
     * @return class SurveyQuestionnaireOptionVO
     * @version v1
     */
    @GetMapping("v1/findByQuestion")
    public Result findByQuestion(String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireOptionAPI.findByQuestion(id), SurveyQuestionnaireOptionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}