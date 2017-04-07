package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyPlanAuditAPI;
import com.bjike.goddess.attainment.to.SurveyPlanAuditTO;
import com.bjike.goddess.attainment.vo.SurveyPlanAuditVO;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 调研计划审核记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:47 ]
 * @Description: [ 调研计划审核记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attainment/surveyplanaudit")
public class SurveyPlanAuditAction {

    @Autowired
    private SurveyPlanAuditAPI surveyPlanAuditAPI;

    /**
     * 更新
     *
     * @param to 调研计划审核传输对象
     * @return class SurveyPlanAuditVO
     * @version v1
     */
    @PutMapping("v1/update")
    public Result update(@Validated(EDIT.class) SurveyPlanAuditTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAuditAPI.update(to), SurveyPlanAuditVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研计划审核数据id
     * @return class SurveyPlanAuditVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAuditAPI.delete(id), SurveyPlanAuditVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据调研计划查询审核数据
     *
     * @param plan_id 调研计划数据id
     * @return class SurveyPlanAuditVO
     * @version v1
     */
    @GetMapping("v1/findByPlan/{plan_id}")
    public Result findByPlan(@PathVariable String plan_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAuditAPI.findByPlan(plan_id), SurveyPlanAuditVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}