package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.TargetInformationAPI;
import com.bjike.goddess.marketdevelopment.to.TargetInformationTO;
import com.bjike.goddess.marketdevelopment.vo.TargetInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 确定目标信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:12 ]
 * @Description: [ 确定目标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("targetinformation")
public class TargetInformationAction {

    @Autowired
    private TargetInformationAPI targetInformationAPI;

    /**
     * 保存确定目标信息数据
     *
     * @param to 确定目标信息传输对象
     * @return class TargetInformationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) TargetInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.save(to), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改确定目标信息数据
     *
     * @param to 确定目标信息传输对象
     * @return class TargetInformationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) TargetInformationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.update(to), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除确定目标信息数据
     *
     * @param to 确定目标信息传输对象
     * @return class TargetInformationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(TargetInformationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.delete(to), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型查询确定目标信息数据
     *
     * @param type 业务类型
     * @return class TargetInformationVO
     * @version v1
     */
    @GetMapping("v1/findByType")
    public Result findByType(String type) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.findByType(type), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务方向科目查询确定目标信息数据
     *
     * @param course 业务方向科目
     * @return class TargetInformationVO
     * @version v1
     */
    @GetMapping("v1/findByCourse")
    public Result findByCourse(String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.findByCourse(course), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和方向科目查询确定目标信息数据
     *
     * @param type   业务类型
     * @param course 业务方向科目
     * @return class TargetInformationVO
     * @version v1
     */
    @GetMapping("v1/findByCourseType")
    public Result findByCourseType(String type, String course) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.findByCourseType(type, course), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区查询确定目标数据
     *
     * @param area 地区
     * @return class TargetInformationVO
     * @version v1
     */
    @GetMapping("v1/findByArea/{area}")
    public Result findByArea(@PathVariable String area) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(targetInformationAPI.findByArea(area), TargetInformationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}