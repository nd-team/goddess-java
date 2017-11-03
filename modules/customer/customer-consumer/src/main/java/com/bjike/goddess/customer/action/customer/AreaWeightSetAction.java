package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.AreaWeightSetAPI;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.to.AreaWeightSetTO;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;
import com.bjike.goddess.customer.vo.AreaWeightSetVO;
import com.bjike.goddess.customer.vo.CustomerBaseInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

/**
 * 地区权重设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("areaweightset")
public class AreaWeightSetAction {
    @Autowired
    private AreaWeightSetAPI areaWeightSetAPI;

    /**
     * 地区权重设置列表总条数
     *
     * @param areaWeightSetDTO 地区权重设置dto
     * @des 获取所有地区权重设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AreaWeightSetDTO areaWeightSetDTO) throws ActException {
        try {
            Long count = areaWeightSetAPI.countAreaWeight(areaWeightSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 地区权重设置列表
     *
     * @param areaWeightSetDTO 地区权重设置dto
     * @return class AreaWeightSetVO
     * @des 获取所有地区权重设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(AreaWeightSetDTO areaWeightSetDTO) throws ActException {
        try {

            List<AreaWeightSetBO> areaWeightSetBOList = areaWeightSetAPI.listAreaWeight(areaWeightSetDTO);
            List<AreaWeightSetVO> areaWeightSetVOList = BeanTransform.copyProperties(areaWeightSetBOList,AreaWeightSetVO.class);
            return ActResult.initialize(areaWeightSetVOList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加地区权重设置
     *
     * @param areaWeightSetTO 地区权重设置数据to
     * @return class AreaWeightSetVO
     * @des 添加地区权重设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAreaWeight(@Validated(ADD.class) AreaWeightSetTO areaWeightSetTO, BindingResult bindingResult) throws ActException {
        try {

            AreaWeightSetBO areaWeightSetBO = areaWeightSetAPI.addAreaWeight(areaWeightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(areaWeightSetBO, AreaWeightSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑地区权重设置
     *
     * @param areaWeightSetTO 地区权重设置数据bo
     * @return class AreaWeightSetVO
     * @des 添加地区权重设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAreaWeight(@Validated(EDIT.class) AreaWeightSetTO areaWeightSetTO) throws ActException {
        try {

            AreaWeightSetBO areaWeightSetBO = areaWeightSetAPI.editAreaWeight(areaWeightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(areaWeightSetBO, AreaWeightSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除地区权重设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAreaWeight(@PathVariable String id) throws ActException {
        try {

            areaWeightSetAPI.deleteAreaWeight(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}