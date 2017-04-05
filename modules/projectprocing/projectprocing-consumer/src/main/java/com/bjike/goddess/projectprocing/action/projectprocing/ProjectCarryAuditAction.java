package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.ProjectCarryAuditAPI;
import com.bjike.goddess.projectprocing.bo.ProjectCarryAuditBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryAuditDTO;
import com.bjike.goddess.projectprocing.to.ProjectCarryAuditTO;
import com.bjike.goddess.projectprocing.vo.ProjectCarryAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目实施审核(针对没派工单情况)
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprocing/projectcarryaudit")
public class ProjectCarryAuditAction {


    @Autowired
    private ProjectCarryAuditAPI projectCarryAuditAPI;

    /**
     *  项目实施审核列表总条数
     *
     * @param customerBaseInfoDTO  项目实施审核信息dto
     * @des 获取所有项目实施审核信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectCarryAuditDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = projectCarryAuditAPI.countProjectCarryAudit(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施审核列表
     *
     * @param projectCarryAuditDTO 项目实施审核信息dto
     * @des 获取所有项目实施审核信息
     * @return  class ProjectCarryAuditVO
     * @version v1
     */
    @GetMapping("v1/listProjectCarryAudit")
    public Result findListProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectCarryAuditVO> projectCarryAuditVOList = BeanTransform.copyProperties(
                    projectCarryAuditAPI.listProjectCarryAudit(projectCarryAuditDTO), ProjectCarryAuditVO.class, true);
            return ActResult.initialize(projectCarryAuditVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目实施审核
     *
     * @param projectCarryAuditTO 项目实施审核基本信息数据to
     * @des 添加项目实施审核
     * @return  class ProjectCarryAuditVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProjectCarryAudit(@Validated({ProjectCarryAuditTO.TESTAddAndEdit.class}) ProjectCarryAuditTO projectCarryAuditTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectCarryAuditBO projectCarryAuditBO1 = projectCarryAuditAPI.addProjectCarryAudit(projectCarryAuditTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectCarryAuditBO1,ProjectCarryAuditVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目实施审核
     *
     * @param projectCarryAuditTO 项目实施审核基本信息数据bo
     * @des 添加项目实施审核
     * @return  class ProjectCarryAuditVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editProjectCarryAudit(@Validated({ProjectCarryAuditTO.TESTAddAndEdit.class}) ProjectCarryAuditTO projectCarryAuditTO) throws ActException {
        try {
            ProjectCarryAuditBO projectCarryAuditBO1 = projectCarryAuditAPI.editProjectCarryAudit(projectCarryAuditTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectCarryAuditBO1,ProjectCarryAuditVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目实施审核信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarryAudit(@PathVariable String id) throws ActException {
        try {
            projectCarryAuditAPI.deleteProjectCarryAudit(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param projectCarryAuditDTO 项目实施审核信息dto
     * @des 获取所有项目实施审核信息
     * @return  class ProjectCarryAuditVO
     * @version v1
     */
    @GetMapping("v1/searchInfo")
    public Result searchInfo(ProjectCarryAuditDTO projectCarryAuditDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectCarryAuditVO> projectCarryAuditVOList = BeanTransform.copyProperties(
                    projectCarryAuditAPI.searchListProjectCarryAudit(projectCarryAuditDTO), ProjectCarryAuditVO.class, true);
            return ActResult.initialize(projectCarryAuditVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @des 导入项目实施审核
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel( ) throws ActException {
        return  null;
    }

    /**
     * 导出
     *
     * @des 导出项目实施审核
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel( ) throws ActException {
        return  null;
    }

    /**
     * 附件上传
     *
     * @des 附件上传项目实施审核
     * @version v1
     */
    @PostMapping("v1/uplode")
    public Result uplodeFile( ) throws ActException {
        return  null;
    }
}