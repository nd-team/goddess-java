package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyQuestionnaireUserAPI;
import com.bjike.goddess.attainment.to.SurveyQuestionnaireUserTO;
import com.bjike.goddess.attainment.vo.SurveyQuestionnaireUserVO;
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
 * 问卷调查历史记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:31 ]
 * @Description: [ 问卷调查历史记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attainment/surveyquestionnaireuser")
public class SurveyQuestionnaireUserAction {

    @Autowired
    private SurveyQuestionnaireUserAPI surveyQuestionnaireUserAPI;

    /**
     * 保存
     *
     * @param to 问卷调查历史记录数据传输对象
     * @return class SurveyQuestionnaireUserVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyQuestionnaireUserTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireUserAPI.save(to), SurveyQuestionnaireUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 问卷调查历史记录数据id
     * @return class SurveyQuestionnaireUserVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireUserAPI.delete(id), SurveyQuestionnaireUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据实施记录查询问卷调查历史记录
     *
     * @param actualize_id 实施记录数据id
     * @return class SurveyQuestionnaireUserVO
     * @version v1
     */
    @GetMapping("v1/findByActualize/{actualize_id}")
    public Result findByActualize(@PathVariable String actualize_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyQuestionnaireUserAPI.findByActualize(actualize_id), SurveyQuestionnaireUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}