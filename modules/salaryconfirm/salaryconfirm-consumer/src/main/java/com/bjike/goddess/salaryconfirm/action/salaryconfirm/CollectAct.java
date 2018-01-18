package com.bjike.goddess.salaryconfirm.action.salaryconfirm;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.salaryconfirm.api.SalaryconfirmAPI;
import com.bjike.goddess.salaryconfirm.to.ConditionTO;
import com.bjike.goddess.salaryconfirm.to.GuidePermissionTO;
import com.bjike.goddess.salaryconfirm.vo.*;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 汇总分析
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 汇总分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
public class CollectAct {

    @Autowired
    private SalaryconfirmAPI salaryconfirmAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailUserAPI detailUserAPI;


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

            Boolean isHasPermission = salaryconfirmAPI.guidePermission(guidePermissionTO);
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
     * 地区列表查询
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("condition/v1/areas")
    public Result allAreas(HttpServletRequest request) throws ActException {
        try {
            List<AreaVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 用户列表查询
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("condition/v1/users")
    public Result allUsers(HttpServletRequest request) throws ActException {
        try {
            List<UserVO> voList = BeanTransform.copyProperties(detailUserAPI.findUserList(), UserVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门列表查询
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("condition/v1/departments")
    public Result allDepartments(HttpServletRequest request) throws ActException {
        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findThawOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @return class AreaCollectVO
     * @version v1
     */
    @GetMapping("area/v1/collect")
    public Result areaCollect(@RequestParam Integer year, @RequestParam Integer month, String area, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setArea(area);
            to.setType("area");
            List<AreaCollectVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.collectByCondition(to), AreaCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区分析
     *
     * @return class AreaAnalyzeVO
     * @version v1
     */
    @GetMapping("area/v1/analyze")
    public Result areaAnalyze(@RequestParam Integer year, @RequestParam Integer month, String area, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setArea(area);
            List<AreaAnalyzeVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.analyzeByArea(to, "area"), AreaAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门汇总
     *
     * @return class DepartmentCollectVO
     * @version v1
     */
    @GetMapping("department/v1/collect")
    public Result departmentCollect(@RequestParam Integer year, @RequestParam Integer month, String department, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setDepartment(department);
            to.setType("department");
            List<DepartmentCollectVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.collectByCondition(to), DepartmentCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门分析
     *
     * @return class DepartmentAnalyzeVO
     * @version v1
     */
    @GetMapping("department/v1/analyze")
    public Result departmentAnalyze(@RequestParam Integer year, @RequestParam Integer month, String department, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setDepartment(department);
            List<DepartmentAnalyzeVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.analyzeByDepart(to, "department"), DepartmentAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人汇总
     *
     * @return class UserCollectVO
     * @version v1
     */
    @GetMapping("user/v1/collect")
    public Result userCollect(@RequestParam Integer year, @RequestParam Integer month, String userName, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setUserName(userName);
            to.setType("person");
            List<UserCollectVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.collectByCondition(to), UserCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人分析
     *
     * @param year     年份
     * @param month    月份
     * @param userName 姓名
     * @return class UserAnalyzeVO
     * @version v1
     */
    @GetMapping("user/v1/analyze")
    public Result userAnalyze(@RequestParam Integer year, @RequestParam Integer month, String userName, HttpServletRequest request) throws ActException {
        try {
            ConditionTO to = new ConditionTO();
            to.setYear(year);
            to.setMonth(month);
            to.setUserName(userName);
            List<UserAnalyzeVO> voList = BeanTransform.copyProperties(salaryconfirmAPI.analyzeByName(to, "name"), UserAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
