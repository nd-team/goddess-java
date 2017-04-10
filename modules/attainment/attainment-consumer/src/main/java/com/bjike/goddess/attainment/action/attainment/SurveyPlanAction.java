package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyPlanAPI;
import com.bjike.goddess.attainment.to.SurveyPlanTO;
import com.bjike.goddess.attainment.vo.SurveyPlanVO;
import com.bjike.goddess.common.api.entity.ADD;
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
 * 调研计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyplan")
public class SurveyPlanAction {

    @Autowired
    private SurveyPlanAPI surveyPlanAPI;

    /**
     * 添加
     *
     * @param to 调研计划传输对象
     * @return class SurveyPlanVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAPI.save(to), SurveyPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研计划传输对象
     * @return class SurveyPlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyPlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAPI.update(to), SurveyPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研计划数据id
     * @return class SurveyPlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAPI.delete(id), SurveyPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据需求查询调研计划
     *
     * @param demand_id 调研需求数据id
     * @return class SurveyPlanVO
     * @version v1
     */
    @GetMapping("v1/findByDemand/{demand_id}")
    public Result findByDemand(@PathVariable String demand_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyPlanAPI.findByDemand(demand_id), SurveyPlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}