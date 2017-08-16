package com.bjike.goddess.projectmeasure.action.projectmeasure;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.api.MarketInfoAPI;
import com.bjike.goddess.projectmeasure.api.ProjectBasicInfoAPI;
import com.bjike.goddess.projectmeasure.bo.ProjectBasicInfoBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.service.ProjectBasicInfoSer;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectBasicInfoTO;
import com.bjike.goddess.projectmeasure.vo.ProjectBasicInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目基本信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:07 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectbasicinfo")
public class ProjectBasicInfoAct {

    @Autowired
    private ProjectBasicInfoAPI projectBasicInfoAPI;
    @Autowired
    private MarketInfoAPI marketInfoAPI;
    @Autowired
    private ModuleAPI moduleAPI;


    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = projectBasicInfoAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据id查询项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @return class ProjectBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/projectbasicinfo/{id}")
    public Result findById(@PathVariable(value = "id") String id, HttpServletRequest request) throws ActException {
        try {
            ProjectBasicInfoBO bo = projectBasicInfoAPI.getOne(id);
            ProjectBasicInfoVO vo = BeanTransform.copyProperties(bo, ProjectBasicInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目基本信息dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated ProjectBasicInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectBasicInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目基本信息
     *
     * @param dto 项目基本信息传输对象
     * @return class ProjectBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectBasicInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectBasicInfoBO> boList = projectBasicInfoAPI.list(dto);
            List<ProjectBasicInfoVO> voList = BeanTransform.copyProperties(boList, ProjectBasicInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目基本信息
     *
     * @param to 项目基本信息to信息
     * @return class ProjectBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) ProjectBasicInfoTO to, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            ProjectBasicInfoBO bo = projectBasicInfoAPI.save(to);
            ProjectBasicInfoVO vo = BeanTransform.copyProperties(bo, ProjectBasicInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目基本信息
     *
     * @param id 项目基本信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectBasicInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目基本信息
     *
     * @param to 项目基本信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) ProjectBasicInfoTO to, BindingResult result) throws ActException {
        try {
            projectBasicInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加编辑中所有的项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findMarket/projectName")
    public Result findMarketPname() throws ActException {
        try {
            List<String> projectName = new ArrayList<>();
            if(moduleAPI.isCheck("market")){
                projectName = marketInfoAPI.getProjectName();
            }
            return ActResult.initialize(projectName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}