package com.bjike.goddess.managepromotion.action.managepromotion;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.OverviewSkillLevelAPI;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.OverviewSkillLevelDTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.OverviewSkillLevelTO;
import com.bjike.goddess.managepromotion.vo.OverviewSkillLevelVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import com.bjike.goddess.organize.vo.PositionDetailVO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 技能等级情况概览
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 05:06 ]
 * @Description: [ 技能等级情况概览 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("overviewskilllevel")
public class OverviewSkillLevelAction {
    @Autowired
    private OverviewSkillLevelAPI overviewSkillLevelAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private HierarchyAPI hierarchyAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private RegularizationAPI regularizationAPI;

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

            Boolean isHasPermission = overviewSkillLevelAPI.guidePermission(guidePermissionTO);
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
     * 技能等级情况概览列表总条数
     *
     * @param overviewSkillLevelDTO 技能等级情况概览记录dto
     * @des 获取所有技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OverviewSkillLevelDTO overviewSkillLevelDTO) throws ActException {
        try {
            Long count = overviewSkillLevelAPI.countOverviewSkillLevel(overviewSkillLevelDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个技能等级情况概览
     *
     * @param id
     * @return class OverviewSkillLevelVO
     * @des 获取一个技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/overview/{id}")
    public Result overview(@PathVariable String id) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(overviewSkillLevelBO, OverviewSkillLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 技能等级情况概览列表
     *
     * @param overviewSkillLevelDTO 技能等级情况概览记录dto
     * @return class OverviewSkillLevelVO
     * @des 获取所有技能等级情况概览
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OverviewSkillLevelDTO overviewSkillLevelDTO, HttpServletRequest request) throws ActException {
        try {
            List<OverviewSkillLevelVO> overviewSkillLevelVOS = BeanTransform.copyProperties(
                    overviewSkillLevelAPI.findListOverviewSkillLevel(overviewSkillLevelDTO), OverviewSkillLevelVO.class, request);
            return ActResult.initialize(overviewSkillLevelVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览to
     * @return class OverviewSkillLevelVO
     * @des 添加技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OverviewSkillLevelTO overviewSkillLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.insertOverviewSkillLevel(overviewSkillLevelTO);
            return ActResult.initialize(BeanTransform.copyProperties(overviewSkillLevelBO, OverviewSkillLevelVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑技能等级情况概览
     *
     * @param overviewSkillLevelTO 技能等级情况概览数据to
     * @return class OverviewSkillLevelVO
     * @des 添加技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OverviewSkillLevelTO overviewSkillLevelTO, BindingResult bindingResult) throws ActException {
        try {
            OverviewSkillLevelBO overviewSkillLevelBO = overviewSkillLevelAPI.editOverviewSkillLevel(overviewSkillLevelTO);
            return ActResult.initialize(overviewSkillLevelBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除技能等级情况概览
     *
     * @param id 用户id
     * @des 根据用户id删除技能等级情况概览
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            overviewSkillLevelAPI.removeOverviewSkillLevel(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询地区
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/findArea")
    public Result findArea(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                boList = departmentDetailAPI.findArea();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                boList = departmentDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的岗位详细
     *
     * @return class PositionDetailVO
     * @version v1
     */
    @GetMapping("v1/position")
    public Result position(HttpServletRequest request) throws ActException {
        try {
            List<PositionDetailBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                boList = positionDetailAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, PositionDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体系信息
     *
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/hierarchy")
    public Result hierarchy(HttpServletRequest request) throws ActException {
        try {
            List<HierarchyBO> boList = new ArrayList<>();
            String userToken = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                boList = hierarchyAPI.findStatus();
            }
            return ActResult.initialize(BeanTransform.copyProperties(boList, HierarchyVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/user")
    public Result user(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = new ArrayList<>();
            String userToken=request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                userBOS = positionDetailUserAPI.findUserListInOrgan();
            }
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名查找转正时间
     *
     * @param username 姓名
     * @version v1
     */
    @GetMapping("v1/time")
    public Result time(String username, HttpServletRequest request) throws ActException {
        try {
            String time = null;
            String userToken=request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("regularization")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN,userToken);
                time = regularizationAPI.getTime(username);
            }
            return ActResult.initialize(time);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 年份
     *
     * @version v1
     */
    @GetMapping("v1/year")
    public Result yearList() throws ActException {
        try {
             //获取所有年
            List<String> yearList = new ArrayList<>();
            int year = LocalDate.now().getYear();

            for (int i = year - 5; i <= year + 5; i++) {
                yearList.add(String.valueOf(i));
            }
            return ActResult.initialize(yearList);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }


}