package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.StayUtilitiesAPI;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
import com.bjike.goddess.rentutilitiespay.vo.StayUtilitiesVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 员工住宿水电费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("stayutilities")
public class StayUtilitiesAction {
    @Autowired
    private StayUtilitiesAPI stayUtilitiesAPI;
    /**
     * 获取员工住宿水电费
     *
     * @param stayUtilitiesDTO 员工住宿水电费dto
     * @return class StayUtilitiesVO
     * @des 获取所有员工住宿水电费
     * @version v1
     */
    @GetMapping("v1/listStayUtilities")
    public Result findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws ActException {
        try {
            List<StayUtilitiesVO> stayUtilitiesVOS = BeanTransform.copyProperties
                    (stayUtilitiesAPI.findListStayUtilities(stayUtilitiesDTO),StayUtilitiesVO.class);
            return ActResult.initialize(stayUtilitiesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesVO
     * @des 添加员工住宿水电费
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addStayUtilities(@Validated StayUtilitiesTO stayUtilitiesTO) throws ActException {
        try {
            StayUtilitiesBO stayUtilitiesBO = stayUtilitiesAPI.insertStayUtilities(stayUtilitiesTO);
            return ActResult.initialize(stayUtilitiesBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工住宿水电费
     *
     * @param stayUtilitiesTO 员工住宿水电费数据to
     * @return class StayUtilitiesVO
     * @des 编辑员工住宿水电费
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editStayUtilities(@Validated StayUtilitiesTO stayUtilitiesTO) throws ActException {
        try {
            StayUtilitiesBO stayUtilitiesBO = stayUtilitiesAPI.editStayUtilities(stayUtilitiesTO);
            return ActResult.initialize(stayUtilitiesBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除员工住宿水电费
     *
     * @param id 用户id
     * @des 根据用户id删除员工住宿水电费记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result removeStayUtilities(@PathVariable String id) throws ActException {
        try {
            stayUtilitiesAPI.removeStayUtilities(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总
     *
     * @param name 名字
     * @des 根据名字汇总
     * @return  class StayUtilitiesVO
     * @version v1
     */
    @GetMapping("v1/collectName")
    public Result collectName ( @NotBlank String[] name ) throws ActException {
        try {
            List<StayUtilitiesVO> stayUtilitiesVOS = BeanTransform.copyProperties(
                    stayUtilitiesAPI.collectName(name),StayUtilitiesBO.class,true);
            return ActResult.initialize(stayUtilitiesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}