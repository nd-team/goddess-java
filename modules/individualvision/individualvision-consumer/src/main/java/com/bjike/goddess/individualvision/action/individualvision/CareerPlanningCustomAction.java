package com.bjike.goddess.individualvision.action.individualvision;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.api.CareerPlanningCustomAPI;
import com.bjike.goddess.individualvision.bo.CareerPlanningCustomBO;
import com.bjike.goddess.individualvision.dto.CareerPlanningCustomDTO;
import com.bjike.goddess.individualvision.to.CareerPlanningCustomTO;
import com.bjike.goddess.individualvision.vo.CareerPlanningCustomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 职业规划定制
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:45 ]
 * @Description: [ 职业规划定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("individualvision/careerplanningcustom")
public class CareerPlanningCustomAction {
    @Autowired
    private CareerPlanningCustomAPI careerPlanningCustomAPI;
    /**
     * 获取职业规划定制
     *
     * @param careerPlanningCustomDTO 职业规划定制dto
     * @return class CareerPlanningCustomVO
     * @des 获取所有职业规划定制
     * @version v1
     */
    @GetMapping("v1/listCareerPlanningCustom")
    public Result findListCareerPlanningCustom(CareerPlanningCustomDTO careerPlanningCustomDTO) throws ActException {
        try {
            List<CareerPlanningCustomVO> careerPlanningCustomVOS = BeanTransform.copyProperties
                    (careerPlanningCustomAPI.findListCareerPlanningCustom(careerPlanningCustomDTO),CareerPlanningCustomVO.class);
            return ActResult.initialize(careerPlanningCustomVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加职业规划定制
     *
     * @param careerPlanningCustomTO 职业规划定制数据to
     * @return class CareerPlanningCustomVO
     * @des 添加职业规划定制
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws ActException {
        try {
            CareerPlanningCustomBO careerPlanningCustomBO = careerPlanningCustomAPI.insertCareerPlanningCustom(careerPlanningCustomTO);
            return ActResult.initialize(careerPlanningCustomBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑职业规划定制
     *
     * @param careerPlanningCustomTO 职业规划定制数据to
     * @return class CareerPlanningCustomVO
     * @des 编辑职业规划定制
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws ActException {
        try {
            CareerPlanningCustomBO careerPlanningCustomBO = careerPlanningCustomAPI.editCareerPlanningCustom(careerPlanningCustomTO);
            return ActResult.initialize(careerPlanningCustomBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除职业规划定制
     *
     * @param id 用户id
     * @des 根据用户id删除职业规划定制记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeCareerPlanningCustom(String id) throws ActException {
        try {
            careerPlanningCustomAPI.removeCareerPlanningCustom(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 发送邮件
     *
     * @param careerPlanningCustomTO 发送邮件数据to
     * @return class CareerPlanningCustomVO
     * @des 发送邮件职业规划定制
     * @version v1
     */
    @PostMapping("v1/send")
    public Result sendCareerPlanningCustom(@Validated CareerPlanningCustomTO careerPlanningCustomTO) throws ActException {
        try {
            CareerPlanningCustomBO careerPlanningCustomBO = careerPlanningCustomAPI.sendCareerPlanningCustom(careerPlanningCustomTO);
            return ActResult.initialize(careerPlanningCustomBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}