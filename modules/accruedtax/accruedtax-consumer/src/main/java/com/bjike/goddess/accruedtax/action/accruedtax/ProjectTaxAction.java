package com.bjike.goddess.accruedtax.action.accruedtax;

import com.bjike.goddess.accruedtax.api.ProjectTaxAPI;
import com.bjike.goddess.accruedtax.bo.ProjectTaxBO;
import com.bjike.goddess.accruedtax.dto.ProjectTaxDTO;
import com.bjike.goddess.accruedtax.to.ProjectTaxTO;
import com.bjike.goddess.accruedtax.vo.ProjectTaxVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 项目上税金
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:18 ]
 * @Description: [ 项目上税金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projecttax")
public class ProjectTaxAction {

    @Autowired
    private ProjectTaxAPI projectTaxAPI;

    /**
     *  列表总条数
     *
     * @param projectTaxDTO  项目上税金信息dto
     * @des 获取所有项目上税金信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectTaxDTO projectTaxDTO) throws ActException {
        try {
            Long count = projectTaxAPI.countProjectTax(projectTaxDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目上税金列表
     *
     * @param projectTaxDTO 项目上税金信息dto
     * @param request 前端过滤参数
     * @des 获取所有项目上税金信息
     * @return  class ProjectTaxVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListProjectTax(ProjectTaxDTO projectTaxDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ProjectTaxVO> projectTaxVOList = BeanTransform.copyProperties(
                    projectTaxAPI.listProjectTax(projectTaxDTO), ProjectTaxVO.class, request);
            return ActResult.initialize(projectTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目上税金
     *
     * @param projectTaxTO 项目上税金基本信息数据to
     * @des 添加项目上税金
     * @return  class ProjectTaxVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addProjectTax(@Validated ProjectTaxTO projectTaxTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectTaxBO projectTaxBO1 = projectTaxAPI.addProjectTax(projectTaxTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectTaxBO1,ProjectTaxVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目上税金
     *
     * @param projectTaxTO 项目上税金基本信息数据bo
     * @des 编辑项目上税金
     * @return  class ProjectTaxVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editProjectTax(@Validated ProjectTaxTO projectTaxTO) throws ActException {
        try {
            ProjectTaxBO projectTaxBO1 = projectTaxAPI.editProjectTax(projectTaxTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectTaxBO1,ProjectTaxVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目上税金信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectTax(@PathVariable String id) throws ActException {
        try {
            projectTaxAPI.deleteProjectTax(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }



    /**
     * 根据项目组汇总
     *
     * @param projectTaxDTO 应交税金信息dto
     * @des 根据项目组汇总
     * @return  class ProjectTaxVO
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result ctProject(ProjectTaxDTO projectTaxDTO) throws ActException {
        try {
            List<ProjectTaxVO> projectTaxVOList = BeanTransform.copyProperties(
                    projectTaxAPI.collectCompany(projectTaxDTO), ProjectTaxVO.class);
            return ActResult.initialize(projectTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据税种汇总
     *
     * @param projectTaxDTO 应交税金信息dto
     * @des 根据税种汇总
     * @return  class ProjectTaxVO
     * @version v1
     */
    @GetMapping("v1/ctTaxType")
    public Result ctTaxType(ProjectTaxDTO projectTaxDTO) throws ActException {
        try {
            List<ProjectTaxVO> projectTaxVOList = BeanTransform.copyProperties(
                    projectTaxAPI.collectTaxType(projectTaxDTO), ProjectTaxVO.class);
            return ActResult.initialize(projectTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有项目组
     *
     * @des 查找汇总所有项目组
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result listProject( ) throws ActException {
        try {
            List<String> list = projectTaxAPI.listProject( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有税种
     *
     * @des 查找汇总所有税种
     * @version v1
     */
    @GetMapping("v1/listTaxType")
    public Result listTaxType( ) throws ActException {
        try {
            List<String> list = projectTaxAPI.listTaxType( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    
}