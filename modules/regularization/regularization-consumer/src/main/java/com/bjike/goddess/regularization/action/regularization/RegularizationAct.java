package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.bonus.bo.ScoreBO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.regularization.bo.ManagementScoreBO;
import com.bjike.goddess.regularization.bo.RegularizationBO;
import com.bjike.goddess.regularization.dto.RegularizationDTO;
import com.bjike.goddess.regularization.excel.SonPermissionObject;
import com.bjike.goddess.regularization.to.*;
import com.bjike.goddess.regularization.vo.ManagementScoreVO;
import com.bjike.goddess.regularization.vo.RegularizationVO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.entity.EntryBasicInfo;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 员工转正
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-15 05:43 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("regularization")
public class RegularizationAct {

    @Autowired
    private RegularizationAPI regularizationAPI;
    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;
    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = regularizationAPI.sonPermission();
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

            Boolean isHasPermission = regularizationAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询员工转正
     *
     * @param id 员工转正唯一标识
     * @return class RegularizationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/regularization/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            RegularizationBO bo = regularizationAPI.findById(id);
            RegularizationVO vo = BeanTransform.copyProperties(bo, RegularizationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 员工转正dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated RegularizationDTO dto, BindingResult result) throws ActException {
        try {
            Long count = regularizationAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询员工转正
     *
     * @param dto 员工转正dto
     * @return class RegularizationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated RegularizationDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<RegularizationBO> boList = regularizationAPI.list(dto);
            List<RegularizationVO> voList = BeanTransform.copyProperties(boList, RegularizationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加员工转正
     *
     * @param to 员工转正to
     * @return class RegularizationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) RegularizationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            RegularizationBO bo = regularizationAPI.save(to);
            RegularizationVO vo = BeanTransform.copyProperties(bo, RegularizationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除员工转正
     *
     * @param id 员工转正唯一标识
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable(value = "id") String id) throws ActException {
        try {
            regularizationAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑员工转正
     *
     * @param to 员工转正to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) RegularizationTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理层评分
     *
     * @param id 员工转正唯一标识
     * @param to 管理层评分to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/manageScore/{id}")
    public Result manageScore(@PathVariable(value = "id") String id, @Validated(value = {ManagementScoreTO.IManagementScore.class}) ManagementScoreTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.manageScore(id, to);
            return new ActResult("manageScore success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看管理层评分
     *
     * @param id 员工转正唯一标识
     * @return class ManagementScoreVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/checkManageScore/{id}")
    public Result checkManageScore(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            List<ManagementScoreBO> boList = regularizationAPI.checkManageScore(id);
            List<ManagementScoreVO> voList = BeanTransform.copyProperties(boList, ManagementScoreVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 决策层评价
     *
     * @param id                    员工转正唯一标识
     * @param decisionLevelEvaluate 决策层评价
     * @param decisionLevelRank     决策层评分等级
     * @param decisionLevelScore    决策层具体评分
     * @version v1
     */
    @PutMapping("v1/decisionLevelEvaluate/{id}")
    public Result decisionLevelEvaluate(@PathVariable(value = "id") String id, @RequestParam(value = "decisionLevelEvaluate") String decisionLevelEvaluate, @RequestParam(value = "decisionLevelRank") String decisionLevelRank, @RequestParam(value = "decisionLevelScore") Integer decisionLevelScore) throws ActException {
        try {
            regularizationAPI.decisionLevelEvaluate(id, decisionLevelEvaluate, decisionLevelRank, decisionLevelScore);
            return new ActResult("decisionLevelEvaluate success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 规划模块补充
     *
     * @param to 规划模块补充to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/planModuleSupply")
    public Result planModuleSupply(@Validated(value = {PlanModuleSupplyTO.PlanModuleSupply.class}) PlanModuleSupplyTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.planModuleSupply(to);
            return new ActResult("planModuleSupply success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预算模块补充
     *
     * @param id 员工转正唯一标识
     * @param budgetPositiveComment 预算模块转正意见
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/budgetModuleSupply/{id}")
    public Result budgetModuleSupply(@PathVariable(value = "id") String id, @RequestParam(value = "budgetPositiveComment") String budgetPositiveComment) throws ActException {
        try {
            regularizationAPI.budgetModuleSupply(id, budgetPositiveComment);
            return new ActResult("budgetModuleSupply success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审批
     *
     * @param to 总经办审批to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/zjbApproval")
    public Result zjbApproval(@Validated(value = {ZjbApprovalTO.ZjbApproval.class}) ZjbApprovalTO to, BindingResult result) throws ActException {
        try {
            regularizationAPI.zjbApproval(to);
            return new ActResult("zjbApproval success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            if(moduleAPI.isCheck("organize")) {
                detail = regularizationAPI.findAddAllDetails();
            }
            return ActResult.initialize(detail);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加中所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/allArea")
    public Result allArea() throws ActException {
        try {
            List<AreaBO> area = new ArrayList<>(0);
            if(moduleAPI.isCheck("organize")) {
                area = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            if(moduleAPI.isCheck("organize")) {
                getPerson = regularizationAPI.findallMonUser();
            }
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有岗位层级
     *
     * @version v1
     */
    @GetMapping("v1/allHierarchy")
    public Result allHierarchy() throws ActException {
        try {
            List<OpinionBO> thawOpinion = new ArrayList<>();
            if(moduleAPI.isCheck("organize")) {
                thawOpinion = arrangementAPI.findThawOpinion();
            }
            return ActResult.initialize(thawOpinion);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名获取员工编号
     *
     * @version v1
     */
    @GetMapping("v1/allEntryNum")
    public Result allEntryNum(@RequestParam String name) throws ActException {
        try {
            List<String> enNum = new ArrayList<>();
            if(moduleAPI.isCheck("archive")) {
                List<EntryBasicInfoBO> entryBasicInfoVOS = entryBasicInfoAPI.getEntryBasicInfoByName(name);
                if (entryBasicInfoVOS != null && entryBasicInfoVOS.size() > 0) {
                    for (EntryBasicInfoBO entryBasicInfoVO : entryBasicInfoVOS) {
                        String num = entryBasicInfoVO.getEmployeeID();
                        if (StringUtils.isNotBlank(num)) {
                            enNum.add(num);
                        }
                    }
                }
            }
            return ActResult.initialize(enNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加时链接数据
     * @return class RegularizationVO
     * @version v1
     */
    @GetMapping("v1/addReturn")
    public Result addReturn(@RequestParam String name, @RequestParam String empNumer, HttpServletRequest request) throws ActException {
        try {
            RegularizationBO regularizationBO = new RegularizationBO();
            if(moduleAPI.isCheck("archive")){
                regularizationBO = regularizationAPI.findAddRusult(name,empNumer);
            }
            if(moduleAPI.isCheck("bonus")){
                ScoreBO scoreBO = disciplineRecordAPI.getRePuTotal(name);
                regularizationBO.setAwardSoce(Integer.parseInt(new java.text.DecimalFormat("0").format(scoreBO.getRewardTotal())));
                regularizationBO.setPenaltyScore(Integer.parseInt(new java.text.DecimalFormat("0").format(scoreBO.getPushTotal())));
            }
            RegularizationVO regularizationVO = BeanTransform.copyProperties(regularizationBO,RegularizationVO.class,request);
            return ActResult.initialize(regularizationVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}