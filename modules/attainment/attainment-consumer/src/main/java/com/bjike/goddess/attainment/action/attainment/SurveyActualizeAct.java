package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.SurveyActualizeAPI;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.attainment.vo.SurveyActualizeVO;
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
 * 调研实施记录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("surveyactualize")
public class SurveyActualizeAct {

    @Autowired
    private SurveyActualizeAPI surveyActualizeAPI;

    /**
     * 保存
     *
     * @param to 调研实施记录传输对象
     * @return class SurveyActualizeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SurveyActualizeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.save(to), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研实施记录传输对象
     * @return class SurveyActualizeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SurveyActualizeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.update(to), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研实施记录数据id
     * @return class SurveyActualizeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.delete(id), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结束调研
     *
     * @param id 调研实施记录数据id
     * @return class SurveyActualizeVO
     * @version v1
     */
    @PutMapping("v1/over/{id}")
    public Result over(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(surveyActualizeAPI.over(id), SurveyActualizeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}