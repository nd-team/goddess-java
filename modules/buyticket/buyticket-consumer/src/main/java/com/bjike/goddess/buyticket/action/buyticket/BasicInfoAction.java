package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BasicInfoAPI;
import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.buyticket.vo.BasicInfoVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 基本信息设置
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:14 ]
 * @Description: [ 基本信息设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("basicinfo")
public class BasicInfoAction {
    @Autowired
    private BasicInfoAPI basicInfoAPI;

    /**
     * 基本信息设置列表总条数
     *
     * @param basicInfoDTO 基本信息设置dto
     * @des 获取所有基本信息设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BasicInfoDTO basicInfoDTO) throws ActException {
        try {
            Long count = basicInfoAPI.countBasicInfo(basicInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个基本信息设置
     *
     * @param id
     * @return class BasicInfoVO
     * @des 获取一个基本信息设置
     * @version v1
     */
    @GetMapping("v1/basic/{id}")
    public Result basic(@PathVariable String id) throws ActException {
        try {
            BasicInfoBO basicInfoBO = basicInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(basicInfoBO, BasicInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 基本信息设置列表
     *
     * @param basicInfoDTO 基本信息设置dto
     * @return class BasicInfoVO
     * @des 获取所有基本信息设置
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BasicInfoDTO basicInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<BasicInfoVO> basicInfoVOS = BeanTransform.copyProperties
                    (basicInfoAPI.findListBasicInfo(basicInfoDTO), BasicInfoVO.class, request);
            return ActResult.initialize(basicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加基本信息设置
     *
     * @param basicInfoTO 基本信息设置数据to
     * @return class BasicInfoVO
     * @des 添加基本信息设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BasicInfoTO basicInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BasicInfoBO basicInfoBO = basicInfoAPI.insertBasicInfo(basicInfoTO);
            return ActResult.initialize(basicInfoTO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑基本信息设置
     *
     * @param basicInfoTO 基本信息设置数据to
     * @return class BasicInfoVO
     * @des 编辑基本信息设置
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBasicInfo(@Validated(EDIT.class) BasicInfoTO basicInfoTO, BindingResult bindingResult) throws ActException {
        try {
            BasicInfoBO basicInfoBO = basicInfoAPI.editBasicInfo(basicInfoTO);
            return ActResult.initialize(basicInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除基本信息设置
     *
     * @param id 用户id
     * @des 根据用户id删除基本信息设置记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            basicInfoAPI.removeBasicInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}