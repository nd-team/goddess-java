package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.businessproject.api.DispatchSheetAPI;
import com.bjike.goddess.businessproject.api.SiginManageAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.taskallotment.vo.ProjectVO;
import com.bjike.goddess.taskallotment.vo.SonPermissionObject;
import com.bjike.goddess.taskallotment.vo.TableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目列表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("project")
public class ProjectAction {
    @Autowired
    private ProjectAPI projectAPI;
    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private SiginManageAPI siginManageAPI;
    @Autowired
    private DispatchSheetAPI dispatchSheetAPI;
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

            List<SonPermissionObject> hasPermissionList = projectAPI.sonPermission();
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

            Boolean isHasPermission = projectAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 项目列表数据传输
     * @return class ProjectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ProjectBO> list = projectAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 项目列表传输对象
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ProjectTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            projectAPI.save(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 项目列表id
     * @return class ProjectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/project/{id}")
    public Result Project(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectBO bo = projectAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ProjectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 项目列表传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ProjectTO to, BindingResult result) throws ActException {
        try {
            projectAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 项目列表id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 项目列表数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectDTO dto) throws ActException {
        try {
            return ActResult.initialize(projectAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据表id获取项目表
     *
     * @param id 表id
     * @return class TableVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/table/{id}")
    public Result table(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TableBO tableBO = projectAPI.table(id);
            return ActResult.initialize(BeanTransform.copyProperties(tableBO, TableVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑表
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit/table")
    public Result editTable(@Validated(TableTO.EDITTABLE.class) TableTO to, BindingResult result) throws ActException {
        try {
            projectAPI.editTable(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有内部项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/inner/project")
    public Result innerProject() throws ActException {
        try {
            return ActResult.initialize(baseInfoManageAPI.allInnerProjects());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/outer/project")
    public Result outerProject() throws ActException {
        try {
            return ActResult.initialize(baseInfoManageAPI.outerProjects());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有立项情况
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/make/project")
    public Result makeProjects() throws ActException {
        try {
            return ActResult.initialize(siginManageAPI.makeProjects());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有派工单号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/nums")
    public Result nums() throws ActException {
        try {
            return ActResult.initialize(dispatchSheetAPI.nums());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有地区
     *
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result areas(HttpServletRequest request) throws ActException {
        try {
            List<AreaBO> list = departmentDetailAPI.findArea();
            return ActResult.initialize(BeanTransform.copyProperties(list, AreaVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有部门
     *
     * @return class DepartmentDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/departs")
    public Result departs(HttpServletRequest request) throws ActException {
        try {
            List<DepartmentDetailBO> list = departmentDetailAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(list, DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}