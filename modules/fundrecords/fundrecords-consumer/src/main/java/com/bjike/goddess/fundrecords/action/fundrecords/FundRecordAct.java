package com.bjike.goddess.fundrecords.action.fundrecords;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundrecords.api.FundRecordAPI;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundrecord")
public class FundRecordAct {

    @Autowired
    private FundRecordAPI fundRecordAPI;

    /**
     * 新增资金流水
     *
     * @param to 资金流水信息
     * @return class FundRecordVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FundRecordTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO voList = BeanTransform.copyProperties(fundRecordAPI.add(to), FundRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金流水
     *
     * @param to 资金流水信息
     * @return class FundRecordVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FundRecordTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO vo = BeanTransform.copyProperties(fundRecordAPI.edit(to), FundRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金流水
     *
     * @param id 资金流水ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundRecordAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class FundRecordVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(FundRecordDTO dto) throws ActException {
        try {
            List<FundRecordVO> voList = BeanTransform.copyProperties(fundRecordAPI.pageList(dto), FundRecordVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundRecordDTO dto) throws ActException {
        try {
            Long count = fundRecordAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询资金流水
     *
     * @param id 资金流水id
     * @return class FundRecordVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO vo = BeanTransform.copyProperties(fundRecordAPI.findById(id), FundRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class MonthCollectVO
     * @version v1
     */
    @GetMapping("v1/month")
    public Result month(@RequestParam Integer year, @RequestParam Integer month) throws ActException {
        try {
            MonthCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.month(year, month), MonthCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 条件汇总
     *
     * @param to 汇总条件
     * @return class ConditionCollectVO
     * @version v1
     */
    @GetMapping("v1/condition")
    public Result condition(@Validated({CollectTO.Collect.class}) CollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<ConditionCollectVO> vo = BeanTransform.copyProperties(fundRecordAPI.condition(to), ConditionCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区分析
     *
     * @param year  年份
     * @param month 月份
     * @param area  地区
     * @return class AreaAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/area")
    public Result areaAnalyze(@RequestParam Integer year, @RequestParam Integer month, String area, HttpServletRequest request) throws ActException {
        try {
            List<AreaAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.areaAnalyze(year, month, area), AreaAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组分析
     *
     * @param year  年份
     * @param month 月份
     * @param group 地区
     * @return class GroupAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/group")
    public Result groupAnalyze(@RequestParam Integer year, @RequestParam Integer month, String group, HttpServletRequest request) throws ActException {
        try {
            List<GroupAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.groupAnalyze(year, month, group), GroupAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目分析
     *
     * @param year    年份
     * @param month   月份
     * @param project 地区
     * @return class ProjectAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/project")
    public Result projectAnalyze(@RequestParam Integer year, @RequestParam Integer month, String project, HttpServletRequest request) throws ActException {
        try {
            List<ProjectAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.projectAnalyze(year, month, project), ProjectAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}