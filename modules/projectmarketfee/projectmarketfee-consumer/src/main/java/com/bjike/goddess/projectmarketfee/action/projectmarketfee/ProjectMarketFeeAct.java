package com.bjike.goddess.projectmarketfee.action.projectmarketfee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.api.ProjectMarketFeeAPI;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeBO;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeDTO;
import com.bjike.goddess.projectmarketfee.to.ProjectMarketFeeTO;
import com.bjike.goddess.projectmarketfee.vo.ProjectMarketFeeCountVO;
import com.bjike.goddess.projectmarketfee.vo.ProjectMarketFeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目前期的市场活动费
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-10 10:34 ]
 * @Description: [ 项目前期的市场活动费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectmarketfee")
public class ProjectMarketFeeAct {
    @Autowired
    private ProjectMarketFeeAPI projectMarketFeeAPI;

    /**
     * 查找
     *
     * @param dto     项目前期的市场活动费分页信息
     * @param request 请求对象
     * @return class ProjectMarketFeeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectMarketFeeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeBO> list = projectMarketFeeAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMarketFeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一级科目汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/firstSubjectCount/{startTime}/{endTime}")
    public Result firstSubjectCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> bo = projectMarketFeeAPI.firstSubjectCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 二级科目汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/secondSubjectCount/{startTime}/{endTime}")
    public Result secondSubjectCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> bo = projectMarketFeeAPI.secondSubjectCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 三级科目汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/thirdSubjectCount/{startTime}/{endTime}")
    public Result thirdSubjectCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> bo = projectMarketFeeAPI.thirdSubjectCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaCount/{startTime}/{endTime}")
    public Result areaCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> list = projectMarketFeeAPI.areaCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroupCount/{startTime}/{endTime}")
    public Result projectGroupCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> list = projectMarketFeeAPI.projectGroupCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目名称汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param request   请求对象
     * @return class ProjectMarketFeeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectNameCount/{startTime}/{endTime}")
    public Result projectNameCount(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeCountBO> list = projectMarketFeeAPI.projectNameCount(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMarketFeeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找汇总信息对应的明细
     *
     * @param to      项目前期的市场活动费汇总明细信息
     * @param request 请求对象
     * @return class ProjectMarketFeeVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/findDetail")
    public Result findDetail(ProjectMarketFeeTO to, HttpServletRequest request) throws ActException {
        try {
            List<ProjectMarketFeeBO> list = projectMarketFeeAPI.findDetail(to);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectMarketFeeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}