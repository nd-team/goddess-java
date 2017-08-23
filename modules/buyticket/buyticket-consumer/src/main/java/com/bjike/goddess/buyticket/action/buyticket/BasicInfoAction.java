package com.bjike.goddess.buyticket.action.buyticket;

import com.bjike.goddess.buyticket.api.BasicInfoAPI;
import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.dto.BasicInfoDTO;
import com.bjike.goddess.buyticket.to.BasicInfoTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
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
import java.util.ArrayList;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = basicInfoAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
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

    /**
     * 查询所有购票原因
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findticketCause")
    public Result findticketCause() throws ActException {
        try {
            List<String> ticketCause = new ArrayList<>();
            ticketCause = basicInfoAPI.findAllTicketCause();
            return ActResult.initialize(ticketCause);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有车票类型
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findTicketType")
    public Result findTicketType() throws ActException {
        try {
            List<String> ticketType = new ArrayList<>();
            ticketType = basicInfoAPI.findAllTicketType();
            return ActResult.initialize(ticketType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有购买方式
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findBuyPattern")
    public Result findBuyPattern() throws ActException {
        try {
            List<String> buyPattern = new ArrayList<>();
            buyPattern = basicInfoAPI.findAllBuyPattern();
            return ActResult.initialize(buyPattern);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有汇总类型
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findSummaryType")
    public Result findSummaryType() throws ActException {
        try {
            List<String> summaryType = new ArrayList<>();
            summaryType = basicInfoAPI.findAllSummaryType();
            return ActResult.initialize(summaryType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有汇总周期
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findSummaryCycle")
    public Result findSummaryCycle() throws ActException {
        try {
            List<String> summaryCycle = new ArrayList<>();
            summaryCycle = basicInfoAPI.findAllSummaryCycle();
            return ActResult.initialize(summaryCycle);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有数据汇总呈现类型
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/finDataAggregationType")
    public Result findApplyAreas() throws ActException {
        try {
            List<String> dataAggregationType = new ArrayList<>();
            dataAggregationType = basicInfoAPI.findAllDataAggregationType();
            return ActResult.initialize(dataAggregationType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}