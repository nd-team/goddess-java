package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.projectmeasure.api.ProjectBasicInfoAPI;
import com.bjike.goddess.projectmeasure.api.ProjectOtherDemandAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectEvaluateResultBO;
import com.bjike.goddess.projectmeasure.bo.ProjectOtherDemandBO;
import com.bjike.goddess.projectmeasure.dto.ProjectOtherDemandDTO;
import com.bjike.goddess.projectmeasure.excel.SonPermissionObject;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectOtherDemandTO;
import com.bjike.goddess.projectmeasure.vo.ProjectEvaluateResultVO;
import com.bjike.goddess.projectmeasure.vo.ProjectOtherDemandVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 其他需求界面
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-12 04:21 ]
 * @Description: [ 其他需求界面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectotherdemand")
public class ProjectOtherDemandAct {

    @Autowired
    private ProjectOtherDemandAPI projectOtherDemandAPI;

    @Autowired
    private ProjectBasicInfoAPI projectBasicInfoAPI;

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

            List<SonPermissionObject> hasPermissionList = projectOtherDemandAPI.sonPermission();
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

            Boolean isHasPermission = projectOtherDemandAPI.guidePermission(guidePermissionTO);
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
     * 查询所有的项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findProjectName")
    public Result findProjectName() throws ActException {
        try {
            List<String> projectNames = projectBasicInfoAPI.findAllProjectNames();
            return ActResult.initialize(projectNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加其他需求界面数据
     *
     * @param projectOtherDemandTO
     * @return class ProjectOtherDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ProjectOtherDemandTO projectOtherDemandTO, BindingResult result) throws ActException {
        try {
            ProjectOtherDemandBO projectOtherDemandBO = projectOtherDemandAPI.add(projectOtherDemandTO);
            ProjectOtherDemandVO projectOtherDemandVO = BeanTransform.copyProperties(projectOtherDemandBO, ProjectOtherDemandVO.class);
            return ActResult.initialize(projectOtherDemandVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑其他需求界面数据
     *
     * @param projectOtherDemandTO
     * @param request
     * @return class ProjectOtherDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/update")
    public Result update(@Validated(value = {EDIT.class}) ProjectOtherDemandTO projectOtherDemandTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectOtherDemandBO projectOtherDemandBO = projectOtherDemandAPI.edit(projectOtherDemandTO);
            ProjectOtherDemandVO projectOtherDemandVO = BeanTransform.copyProperties(projectOtherDemandBO, ProjectOtherDemandVO.class, request);
            return ActResult.initialize(projectOtherDemandVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除其他需求界面数据
     *
     * @param id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectOtherDemandAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 输出评估结果
     *
     * @param request
     * @return class ProjectEvaluateResultVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/evaluateResult")
    public Result evaluateResult(ProjectOtherDemandDTO demandDTO,HttpServletRequest request) throws ActException {
        try {
            List<ProjectEvaluateResultBO> projectEvaluateResultBOS = projectOtherDemandAPI.findEvaluateResult(demandDTO);
            List<ProjectEvaluateResultVO> projectEvaluateResultVOS = BeanTransform.copyProperties(projectEvaluateResultBOS, ProjectEvaluateResultVO.class, request);
            return ActResult.initialize(projectEvaluateResultVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询其他需求界面数据
     *
     * @param id 其他需求界面数据唯一标识
     * @return class ProjectOtherDemandVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/projectOtherDemand/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            ProjectOtherDemandBO projectOtherDemandBO = projectOtherDemandAPI.findById(id);
            ProjectOtherDemandVO projectOtherDemandVO = BeanTransform.copyProperties(projectOtherDemandBO, ProjectOtherDemandVO.class, request);
            return ActResult.initialize(projectOtherDemandVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param projectOtherDemandDTO 其他需求界面数据dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated ProjectOtherDemandDTO projectOtherDemandDTO, BindingResult result) throws ActException {
        try {
            Long count = projectOtherDemandAPI.count(projectOtherDemandDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询其他需求界面数据
     *
     * @param projectOtherDemandDTO 其他需求界面数据传输对象
     * @return class ProjectOtherDemandVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectOtherDemandDTO projectOtherDemandDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProjectOtherDemandBO> projectOtherDemandBOS = projectOtherDemandAPI.findProjectOtherDemand(projectOtherDemandDTO);
            List<ProjectOtherDemandVO> projectOtherDemandVOS = BeanTransform.copyProperties(projectOtherDemandBOS, ProjectOtherDemandVO.class, request);
            return ActResult.initialize(projectOtherDemandVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}