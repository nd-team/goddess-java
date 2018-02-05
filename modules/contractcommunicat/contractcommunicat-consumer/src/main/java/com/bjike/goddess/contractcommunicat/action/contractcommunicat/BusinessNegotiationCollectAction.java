package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.BusinessNegotiationCollectAPI;
import com.bjike.goddess.contractcommunicat.bo.BNCollectEchartBO;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationCollectBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationCollectDTO;
import com.bjike.goddess.contractcommunicat.enums.CollectTimeType;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.BNCollectEchartVO;
import com.bjike.goddess.contractcommunicat.vo.BusinessNegotiationCollectVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 商务洽谈汇总
 * @Author: [caiwenxian]
 * @Date: [2018-01-02 16:05]
 * @Description: [ 商务洽谈汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("businessNegotiationCollect")
public class BusinessNegotiationCollectAction {

    @Autowired
    BusinessNegotiationCollectAPI businessNegotiationCollectAPI;

    @Autowired
    DepartmentDetailAPI departmentDetailAPI;

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

            Boolean isHasPermission = businessNegotiationCollectAPI.guidePermission(guidePermissionTO);
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

//    /**
//     * 查询总记录数
//     *
//     * @param dto 查询条件
//     * @version v1
//     */
//    @GetMapping("v1/count")
//    public Result count(BusinessNegotiationCollectDTO dto) throws ActException {
//        try {
//            Long count = businessNegotiationCollectAPI.count(dto);
//            return ActResult.initialize(count);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 查询日汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param time 时间　'2017-12-12'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String businessType, String area, String department, String problemBelong, String time) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.DAY, time);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询周汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param year 年　'2017'
     * @param month 月　'12'
     * @param week 周　'1'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(String businessType, String area, String department, String problemBelong, String year, String month, String week) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.WEEK, year, month, week);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询月汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param year 年　'2017'
     * @param month 月　'12'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(String businessType, String area, String department, String problemBelong, String year, String month) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.MONTH, year, month);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询季度汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param year 年　'2017'
     * @param quarter 季度　'1'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/quarterCollect")
        public Result quarterCollect(String businessType, String area, String department, String problemBelong, String year, String quarter) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.QUART, year, quarter);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    /**
     * 查询年汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param year 年　'2017'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/yearCollect")
    public Result yearCollect(String businessType, String area, String department, String problemBelong, String year) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.YEAR, year);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询累计汇总列表
     *
     * @param businessType 业务类型
     * @param area 地区
     * @param department 项目组/部门
     * @param problemBelong 问题归属
     * @param time 年　'2017-12-12'
     * @return class BusinessNegotiationCollectVO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(String businessType, String area, String department, String problemBelong, String time) throws ActException {
        try {
            BusinessNegotiationCollectDTO dto = new BusinessNegotiationCollectDTO(businessType, area, department, problemBelong);
            checkCollectParams(dto);
            List<BusinessNegotiationCollectBO> bos = businessNegotiationCollectAPI.listBusinessNegotiationCollect(dto, CollectTimeType.TOTAL, time);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BusinessNegotiationCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询日汇总图表展示
     *
     * @param time 时间　'2017-12-12'
     * @version v1
     */
    @GetMapping("v1/dayEchartCollect")
    public Result dayEchartCollect(String time) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.DAY, time);
            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询周汇总图表展示
     *
     * @param year 年　'2017'
     * @param month 月　'12'
     * @param week 周　'1'
     * @version v1
     */
    @GetMapping("v1/weekEchartCollect")
    public Result weekEchartCollect(String year, String month, String week) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.WEEK, year, month, week);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询月汇总图表展示
     *
     * @param year 年　'2017'
     * @param month 月　'12'
     * @version v1
     */
    @GetMapping("v1/monthEchartCollect")
    public Result monthEchartCollect(String year, String month) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.MONTH, year, month);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询季度汇总图表展示
     * @param year 年　'2017'
     * @param quarter 季度　'1'
     * @version v1
     */
    @GetMapping("v1/quarterEchartCollect")
    public Result quarterEchartCollect(String year, String quarter) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.QUART, year, quarter);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询年汇总图表展示
     *
     * @param year 年　'2017'
     * @version v1
     */
    @GetMapping("v1/yearEchartCollect")
    public Result yearEchartCollect(String year) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.YEAR, year);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询累计汇总图表展示
     *
     * @param time 年　'2017-12-12'
     * @version v1
     */
    @GetMapping("v1/totalEchartCollect")
    public Result totalEchartCollect(String time) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchart(CollectTimeType.TOTAL, time);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈日汇总图表展示
     *
     * @param time 年　'2017-12-12'
     * @version v1
     */
    @GetMapping("v1/dayEchartCollectByType")
    public Result dayEchartCollectByType(@RequestParam() String businessType, String time) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.DAY, time);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈周汇总图表展示
     *
     * @param year 年　'2017'
     * @param year 月　'12'
     * @param year 周　'1'
     * @version v1
     */
    @GetMapping("v1/weekEchartCollectByType")
    public Result weekEchartCollectByType(@RequestParam String businessType, String year, String month, String week) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.WEEK, year, month, week);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈月汇总图表展示
     *
     * @param year 年　'2017'
     * @param month 月　'12'
     * @version v1
     */
    @GetMapping("v1/monthEchartCollectByType")
    public Result monthEchartCollectByType(@RequestParam() String businessType, String year, String month) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.MONTH, year, month);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈季度汇总图表展示
     *
     * @param year 年　'2017'
     * @param quarter 季度　'1'
     * @version v1
     */
    @GetMapping("v1/quarterEchartCollectByType")
    public Result quarterEchartCollectByType(@RequestParam() String businessType, String year, String quarter) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.QUART, year, quarter);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈年汇总图表展示
     *
     * @param year 年　'2017'
     * @version v1
     */
    @GetMapping("v1/yearEchartCollectByType")
    public Result yearEchartCollectByType(@RequestParam() String businessType, String year) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.YEAR, year);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询xx业务类型问题归属商务洽谈累计汇总图表展示
     *
     * @param time 截止时间　'2017-12-12'
     * @version v1
     */
    @GetMapping("v1/totalEchartCollectByType")
    public Result totalEchartCollectByType(@RequestParam(required = true) String businessType, String time) throws ActException {
        try {
            BNCollectEchartBO bos = businessNegotiationCollectAPI.listCollectEchartByType(businessType, CollectTimeType.TOTAL, time);

            return ActResult.initialize(BeanTransform.copyProperties(bos, BNCollectEchartVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取业务类型
     *
     * @version v1
     */
    @GetMapping("v1/businessType")
    public Result businessType() throws ActException {
        try {
            Set<String> set = businessNegotiationCollectAPI.listBusinessType();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取全部部门
     *
     * @return class DepartmentDetailBO
     * @version v1
     */
    @GetMapping("v1/allDepartments")
    public Result allDepartment() throws ActException {
        try {
            List<DepartmentDetailBO> bos = departmentDetailAPI.view(new DepartmentDetailDTO());

            return ActResult.initialize(BeanTransform.copyProperties(bos, DepartmentDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取地区
     *
     * @version v1
     */
    @GetMapping("v1/area")
    public Result listArea() throws ActException {
        try {
            List<AreaBO> bos = departmentDetailAPI.findArea();
            if (null == bos) {
                return ActResult.initialize(null);
            }
            Set<String> set = new HashSet<>();
            for (AreaBO bo : bos) {
                set.add(bo.getArea());
            }
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取问题归属
     *
     * @version v1
     */
    @GetMapping("v1/problemBelong")
    public Result listProblemBelong() throws ActException {


        return ActResult.initialize(null);
    }


    private void checkCollectParams(BusinessNegotiationCollectDTO dto) throws ActException{
        if (StringUtils.isBlank(dto.getBusinessType())) {
            throw new ActException("业务类型不能为空");
        }
        if (StringUtils.isNotBlank(dto.getProblemBelong()) && StringUtils.isBlank(dto.getArea()) && StringUtils.isBlank(dto.getDepartment())) {
            throw new ActException("地区、项目组/部门不能为空不能为空");
        }
        if (StringUtils.isNotBlank(dto.getDepartment()) && StringUtils.isBlank(dto.getArea())) {
            throw new ActException("地区");
        }
    }
}
