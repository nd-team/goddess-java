package com.bjike.goddess.qualifications.action.qualifications;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.api.QualificationsHandlePlanAPI;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;
import com.bjike.goddess.qualifications.vo.QualificationsHandlePlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 资质办理计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("qualifications/qualificationshandleplan")
public class QualificationsHandlePlanAction {

    @Autowired
    private QualificationsHandlePlanAPI qualificationsHandlePlanAPI;

    /**
     * 保存
     *
     * @param to 资质办理计划传输对象
     * @return class QualificationsHandlePlanVO
     * @version v1
     */
    @PostMapping("V1/save")
    public Result save(@Validated(ADD.class) QualificationsHandlePlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandlePlanAPI.save(to), QualificationsHandlePlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 资质办理计划传输对象
     * @return class QualificationsHandlePlanVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QualificationsHandlePlanTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandlePlanAPI.update(to), QualificationsHandlePlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 资质办理计划id
     * @return class QualificationsHandlePlanVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandlePlanAPI.delete(id), QualificationsHandlePlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据资质办理查询计划
     *
     * @param handle_id 资质办理ID
     * @return class QualificationsHandlePlanVO
     * @version v1
     */
    @GetMapping("v1/findByHandle/{handle_id}")
    public Result findByHandle(@PathVariable String handle_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qualificationsHandlePlanAPI.findByHandle(handle_id), QualificationsHandlePlanVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}