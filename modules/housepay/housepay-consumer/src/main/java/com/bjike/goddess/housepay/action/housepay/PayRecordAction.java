package com.bjike.goddess.housepay.action.housepay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.api.PayRecordAPI;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.vo.AreaCollectVO;
import com.bjike.goddess.housepay.vo.PayRecordVO;
import com.bjike.goddess.housepay.vo.ProjectCollectVO;
import com.bjike.goddess.housepay.vo.WaitPayVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 已付款记录
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("payrecord")
public class PayRecordAction {
    @Autowired
    private PayRecordAPI payRecordAPI;
    /**
     * 已付款记录列表总条数
     *
     * @param payRecordDTO 已付款记录dto
     * @des 获取所有已付款记录
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PayRecordDTO payRecordDTO) throws ActException {
        try {
            Long count = payRecordAPI.countPayRecord(payRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个已付款记录
     *
     * @param id
     * @return class PayRecordVO
     * @des 获取一个已付款记录
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            PayRecordBO payRecordBO = payRecordAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(payRecordBO, PayRecordVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 已付款记录列表
     *
     * @param payRecordDTO 已付款记录dto
     * @return class PayRecordVO
     * @des 获取所有已付款记录
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(PayRecordDTO payRecordDTO, HttpServletRequest request) throws ActException {
        try {
            List<PayRecordVO> payRecordVOS = BeanTransform.copyProperties(
                    payRecordAPI.findListPayRecord(payRecordDTO), PayRecordVO.class, request);
            return ActResult.initialize(payRecordVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除已付款记录
     *
     * @param id 用户id
     * @des 根据用户id删除已付款记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            payRecordAPI.removePayRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总已付款记录
     *
     * @param areas 地区
     * @return class AreaCollectVO
     * @des 汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectArea")
    public Result collectArea(@RequestParam String[] areas) throws ActException {
        try {
            List<AreaCollectVO> areaCollectVOS = BeanTransform.copyProperties(
                    payRecordAPI.collectArea(areas),AreaCollectVO.class);
            return ActResult.initialize(areaCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取地区
     *
     * @des 获取地区集合
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas() throws ActException {
        try {
            List<String> areasList = payRecordAPI.getAreas();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 汇总已付款记录
     *
     * @param projects 项目
     * @return class ProjectCollectVO
     * @des 汇总已付款记录
     * @version v1
     */
    @GetMapping("v1/collectProject")
    public Result collectProject(@RequestParam String[] projects) throws ActException {
        try {
            List<ProjectCollectVO> projectCollectVOS = BeanTransform.copyProperties(
                    payRecordAPI.collectProject(projects),ProjectCollectVO.class);
            return ActResult.initialize(projectCollectVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目
     *
     * @des 获取项目集合
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects() throws ActException {
        try {
            List<String> projectsList = payRecordAPI.getProject();
            return ActResult.initialize(projectsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}