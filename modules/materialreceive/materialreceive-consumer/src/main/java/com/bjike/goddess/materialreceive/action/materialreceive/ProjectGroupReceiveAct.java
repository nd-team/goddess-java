package com.bjike.goddess.materialreceive.action.materialreceive;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialreceive.api.ProjectGroupReceiveAPI;
import com.bjike.goddess.materialreceive.bo.ProjectGroupReceiveBO;
import com.bjike.goddess.materialreceive.dto.ProjectGroupReceiveDTO;
import com.bjike.goddess.materialreceive.excel.SonPermissionObject;
import com.bjike.goddess.materialreceive.to.GuidePermissionTO;
import com.bjike.goddess.materialreceive.to.ProjectGroupReceiveTO;
import com.bjike.goddess.materialreceive.vo.ProjectGroupReceiveVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目组领用归还登记
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-24 05:52 ]
 * @Description: [ 项目组领用归还登记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectgroupreceive")
public class ProjectGroupReceiveAct {

    @Autowired
    private ProjectGroupReceiveAPI projectGroupReceiveAPI;

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

            List<SonPermissionObject> hasPermissionList = projectGroupReceiveAPI.sonPermission();
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

            Boolean isHasPermission = projectGroupReceiveAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询项目组领用归还登记
     *
     * @param id 项目组领用归还登记唯一标识
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/groupreceive/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectGroupReceiveBO bo = projectGroupReceiveAPI.findById(id);
            ProjectGroupReceiveVO vo = BeanTransform.copyProperties(bo, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 项目组领用归还登记dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated ProjectGroupReceiveDTO dto, BindingResult result) throws ActException {
        try {
            Long count = projectGroupReceiveAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询项目组领用归还登记
     *
     * @param dto 项目组领用归还登记dto
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated ProjectGroupReceiveDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProjectGroupReceiveBO> boList = projectGroupReceiveAPI.list(dto);
            List<ProjectGroupReceiveVO> voList = BeanTransform.copyProperties(boList, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目组领用归还登记
     *
     * @param to 项目组领用归还登记to
     * @return class ProjectGroupReceiveVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProjectGroupReceiveTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ProjectGroupReceiveBO bo = projectGroupReceiveAPI.save(to);
            ProjectGroupReceiveVO vo = BeanTransform.copyProperties(bo, ProjectGroupReceiveVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除项目组领用归还登记
     *
     * @param id 项目组领用归还登记唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectGroupReceiveAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资领用
     *
     * @param to 物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProjectGroupReceiveTO to, BindingResult result) throws ActException {
        try {
            projectGroupReceiveAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 归还物资
     *
     * @param to 物资领用to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/returnmaterial")
    public Result returnMaterial(@Validated(ProjectGroupReceiveTO.RETURNMATERIAL.class) ProjectGroupReceiveTO to, BindingResult result) throws ActException {
        try {
            projectGroupReceiveAPI.returnMaterial(to);
            return new ActResult("returnmaterial success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}