package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyQuestionnaireAPI;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireTO;
import com.bjike.goddess.attainment.vo.SurveyQuestionnaireVO;
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
 * 调研表问题
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:23 ]
 * @Description: [ 调研表问题 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyquestionnaire")
public class SurveyQuestionnaireAct {

    @Autowired
    private SurveyQuestionnaireAPI surveyQuestionnaireAPI;

    /**
     * 保存
     *
     * @param to 调研问题数据传输对象
     * @return class SurveyQuestionnaireVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyQuestionnaireTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireAPI.save(to), SurveyQuestionnaireVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研问题数据传输对象
     * @return class SurveyQuestionnaireVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyQuestionnaireTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireAPI.update(to), SurveyQuestionnaireVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研问题数据id
     * @return class SurveyQuestionnaireVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireAPI.delete(id), SurveyQuestionnaireVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据调研实施查询调研问题
     *
     * @param id 调研实施id
     * @return class SurveyQuestionnaireVO
     * @version v1
     */
    @GetMapping("v1/findByActualize/{id}")
    public Result findByActualize(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireAPI.findByActualize(id), SurveyQuestionnaireVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}