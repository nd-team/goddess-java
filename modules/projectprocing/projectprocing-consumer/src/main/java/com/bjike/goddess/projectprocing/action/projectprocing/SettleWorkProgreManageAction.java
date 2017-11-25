package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.SettleWorkProgreManageAPI;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.to.CompletionStatusTO;
import com.bjike.goddess.projectprocing.vo.PersonalTasksSummVO;
import com.bjike.goddess.projectprocing.vo.SettleWorkProgreManageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 结算工作进度管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("settleworkprogremanage")
public class SettleWorkProgreManageAction {
    @Autowired
    private SettleWorkProgreManageAPI settleWorkProgreManageAPI;
    /**
     * 结算工作进度管理总条数
     *
     * @param settleWorkProgreManageDTO 结算工作进度管理dto
     * @des 获取所有结算工作进度管理总条数
     * @version v1
     */
    @GetMapping("v1/count")

    public Result count(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws ActException {
        try {
            Long count = settleWorkProgreManageAPI.countSettleWork(settleWorkProgreManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个结算工作进度管理
     *
     * @param id 结算工作进度管理id
     * @return class SettleWorkProgreManageVO
     * @des 根据id获取结算工作进度管理
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SettleWorkProgreManageVO settleWorkProgreManageVO = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.getOneById(id), SettleWorkProgreManageVO.class, true);
            return ActResult.initialize(settleWorkProgreManageVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 结算工作进度管理列表
     *
     * @param settleWorkProgreManageDTO 结算工作进度管理dto
     * @return class SettleWorkProgreManageVO
     * @des 获取所有结算工作进度管理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectCarry(SettleWorkProgreManageDTO settleWorkProgreManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<SettleWorkProgreManageVO> settleWorkProgreManageVOList = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.listSettleWork(settleWorkProgreManageDTO), SettleWorkProgreManageVO.class, request);
            return ActResult.initialize(settleWorkProgreManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据外包单位获取列表
     *
     * @param outUnit 外包单位
     * @return class SettleWorkProgreManageVO
     * @des 根据外包单位获取列表
     * @version v1
     */
    @GetMapping("v1/list/outUnit")
    public Result findListByOutUnit(String outUnit, HttpServletRequest request) throws ActException {
        try {
            List<SettleWorkProgreManageVO> settleWorkProgreManageVOList = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.listWorkByOutUnit(outUnit), SettleWorkProgreManageVO.class, request);
            return ActResult.initialize(settleWorkProgreManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 重新分配负责人
     *
     * @param id id
     * @param responsible 负责人
     * @des 重新分配负责人
     * @version v1
     */
    @PostMapping("v1/redistribution")
    public Result redistribution(@RequestParam String id,@RequestParam String responsible, HttpServletRequest request) throws ActException {
        try {
            settleWorkProgreManageAPI.redistribution(id,responsible);
            return new ActResult("redistribution sucess");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 填写完成情况
     *
     * @param completionStatusTO 填写完成情况
     * @des 填写完成情况
     * @version v1
     */
    @PostMapping("v1/fullFinishStatus")
    public Result fullFinishStatus(@Validated(ADD.class) CompletionStatusTO completionStatusTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            settleWorkProgreManageAPI.fullFinishStatus(completionStatusTO);
            return new ActResult("redistribution sucess");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 结算工作进度日汇总
     *
     * @param summDate 日期
     * @return class PersonalTasksSummVO
     * @des 结算工作进度日汇总
     * @version v1
     */
    @GetMapping("v1/personalSumm/day")
    public Result personalSummDay(String summDate, HttpServletRequest request) throws ActException {
        try {
            PersonalTasksSummVO personalTasksSummVO = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.personalSummDay(summDate), PersonalTasksSummVO.class, request);
            return ActResult.initialize(personalTasksSummVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 结算工作进度周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class PersonalTasksSummVO
     * @des 结算工作进度周汇总
     * @version v1
     */
    @GetMapping("v1/personalSumm/week")
    public Result personalSummWeek(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            PersonalTasksSummVO personalTasksSummVO = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.personalSummWeek(year,month,week), PersonalTasksSummVO.class, request);
            return ActResult.initialize(personalTasksSummVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 结算工作进度月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class PersonalTasksSummVO
     * @des 结算工作进度周汇总
     * @version v1
     */
    @GetMapping("v1/personalSumm/month")
    public Result personalSummMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            PersonalTasksSummVO personalTasksSummVO = BeanTransform.copyProperties(
                    settleWorkProgreManageAPI.personalSummMonth(year,month), PersonalTasksSummVO.class, request);
            return ActResult.initialize(personalTasksSummVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}