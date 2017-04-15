package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.BusinessCourseAPI;
import com.bjike.goddess.marketdevelopment.to.BusinessCourseTO;
import com.bjike.goddess.marketdevelopment.vo.BusinessCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 业务方向科目
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesscourse")
public class BusinessCourseAction {

    @Autowired
    private BusinessCourseAPI businessCourseAPI;

    /**
     * 保存业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) BusinessCourseTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.save(to), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) BusinessCourseTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.update(to), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(BusinessCourseTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.congeal(to), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(BusinessCourseTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.thaw(to), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除业务方向科目数据
     *
     * @param to 业务方向科目传输对象
     * @return class BusinessCourseVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(BusinessCourseTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.delete(to), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询对应业务类型的业务方向科目数据
     *
     * @param type_id 业务类型ID
     * @return class BusinessCourseVO
     * @version v1
     */
    @GetMapping("v1/findByType/{type_id}")
    public Result findByType(@PathVariable String type_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.findByType(type_id), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常数据的业务方向科目数据
     *
     * @return class BusinessCourseVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(businessCourseAPI.findThaw(), BusinessCourseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}