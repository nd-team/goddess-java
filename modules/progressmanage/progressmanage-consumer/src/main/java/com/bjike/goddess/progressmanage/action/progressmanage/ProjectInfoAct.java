package com.bjike.goddess.progressmanage.action.progressmanage;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.progressmanage.api.ProjectInfoAPI;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.to.ProjectInfoTO;
import com.bjike.goddess.progressmanage.vo.ProjectInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目信息
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:41 ]
 * @Description: [ 项目信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectinfo")
public class ProjectInfoAct {

    @Autowired
    private ProjectInfoAPI projectInfoAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;


    /**
     * 项目组或部门下拉列表
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/groups")
    public Result tables(HttpServletRequest request) throws ActException {

        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findAllOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目信息
     *
     * @param to 项目信息
     * @return class ProjectInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectInfoVO vo = BeanTransform.copyProperties(projectInfoAPI.add(to), ProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目信息
     *
     * @param to 项目信息
     * @return class ProjectInfoVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectInfoVO vo = BeanTransform.copyProperties(projectInfoAPI.edit(to), ProjectInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目信息
     *
     * @param id 项目承包洽谈ID
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectInfoAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class ProjectInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(ProjectInfoDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProjectInfoVO> voList = BeanTransform.copyProperties(projectInfoAPI.pageList(dto), ProjectInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
}