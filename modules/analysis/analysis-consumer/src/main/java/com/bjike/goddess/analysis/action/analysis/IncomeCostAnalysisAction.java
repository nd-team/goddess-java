package com.bjike.goddess.analysis.action.analysis;

import com.bjike.goddess.analysis.api.IncomeCostAnalysisAPI;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.excel.SonPermissionObject;
import com.bjike.goddess.analysis.to.*;
import com.bjike.goddess.analysis.vo.CollectVO;
import com.bjike.goddess.analysis.vo.IncomeCostAnalysisVO;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 收入成本分析
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("incomecostanalysis")
public class IncomeCostAnalysisAction {
    @Autowired
    private IncomeCostAnalysisAPI incomeCostAnalysisAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = incomeCostAnalysisAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = incomeCostAnalysisAPI.guidePermission(guidePermissionTO);
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
     * 收入成本分析列表总条数
     *
     * @param incomeCostAnalysisDTO 收入成本分析dto
     * @des 获取所有收入成本分析
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws ActException {
        try {
            Long count = incomeCostAnalysisAPI.countIncomeCostAnalysis(incomeCostAnalysisDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收入成本分析
     *
     * @param id
     * @return class IncomeCostAnalysisVO
     * @des 获取一个收入成本分析
     * @version v1
     */
    @GetMapping("v1/analysis/{id}")
    public Result analysis(@PathVariable String id) throws ActException {
        try {
            IncomeCostAnalysisBO incomeCostAnalysisBO = incomeCostAnalysisAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(incomeCostAnalysisBO, IncomeCostAnalysisVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收入成本分析列表
     *
     * @param dto 收入成本分析dto
     * @return class IncomeCostAnalysisVO
     * @des 获取所有收入成本分析
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IncomeCostAnalysisDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<IncomeCostAnalysisVO> incomeCostAnalysisVOS = BeanTransform.copyProperties(
                    incomeCostAnalysisAPI.findListIncomeCostAnalysis(dto), IncomeCostAnalysisVO.class, request);
            return ActResult.initialize(incomeCostAnalysisVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加收入成本分析
//     *
//     * @param incomeCostAnalysisTO 收入成本分析to
//     * @return class IncomeCostAnalysisVO
//     * @des 添加收入成本分析
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(ADD.class) IncomeCostAnalysisTO incomeCostAnalysisTO, BindingResult bindingResult) throws ActException {
//        try {
//            IncomeCostAnalysisBO incomeCostAnalysisBO = incomeCostAnalysisAPI.insertIncomeCostAnalysis(incomeCostAnalysisTO);
//            return ActResult.initialize(incomeCostAnalysisBO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 编辑收入成本分析
     *
     * @param incomeCostAnalysisTO 收入成本分析数据to
     * @return class IncomeCostAnalysisVO
     * @des 编辑收入成本分析
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IncomeCostAnalysisTO incomeCostAnalysisTO, BindingResult bindingResult) throws ActException {
        try {
            incomeCostAnalysisAPI.editIncomeCostAnalysis(incomeCostAnalysisTO);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除收入成本分析
     *
     * @param id 用户id
     * @des 根据用户id删除收入成本分析记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            incomeCostAnalysisAPI.removeIncomeCostAnalysis(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 地区汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/areaCollect")
    public Result areaCollect(@Validated({AreaTO.Collect.class}) AreaTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(incomeCostAnalysisAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/departmentCollect")
    public Result departmentCollect(@Validated({DepartmentTO.Collect.class}) DepartmentTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(incomeCostAnalysisAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 时间汇总
     *
     * @param to 汇总条件
     * @return class CollectVO
     * @version v1
     */
    @GetMapping("v1/date")
    public Result date(@Validated({DateTO.Collect.class}) DateTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            CollectTO collectTO = BeanTransform.copyProperties(to, CollectTO.class);
            List<CollectVO> voList = BeanTransform.copyProperties(incomeCostAnalysisAPI.collect(collectTO), CollectVO.class, request);
            return ActResult.initialize(voList);
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
    @GetMapping("v1/area")
    public Result area() throws ActException {
        try {
            List<String> areasList = incomeCostAnalysisAPI.getArea();
            return ActResult.initialize(areasList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取部门
     *
     * @des 获取部门集合
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department() throws ActException {
        try {
            List<String> departmentsList = incomeCostAnalysisAPI.getDepartment();
            return ActResult.initialize(departmentsList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}