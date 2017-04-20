package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyDemandAPI;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.attainment.vo.SurveyDemandVO;
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
 * 调研需求
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveydemand")
public class SurveyDemandAct {

    @Autowired
    private SurveyDemandAPI surveyDemandAPI;

    /**
     * 保存
     *
     * @param to 调研需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyDemandTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.save(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyDemandTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.update(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研需求数据id
     * @return class SurveyDemandVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.delete(id), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭
     *
     * @param to 关闭需求传输对象
     * @return class SurveyDemandVO
     * @version v1
     */
    @PutMapping("v1/close/{id}")
    public Result close(@Validated(EDIT.class) CloseDemandTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.close(to), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询调研需求
     *
     * @param status 调研状态
     * @return class SurveyDemandVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(SurveyStatus status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyDemandAPI.findByStatus(status), SurveyDemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}