package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.ProjectAcceptanceAPI;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.dto.ProjectAcceptanceDTO;
import com.bjike.goddess.projectprocing.to.ProjectAcceptanceTO;
import com.bjike.goddess.projectprocing.vo.ProjectAcceptanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目验收情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprocing/projectacceptance")
public class ProjectAcceptanceAction {


    @Autowired
    private ProjectAcceptanceAPI projectAcceptanceAPI;

    /**
     *  项目验收情况列表总条数
     *
     * @param customerBaseInfoDTO  项目验收情况信息dto
     * @des 获取所有项目验收情况信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectAcceptanceDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = projectAcceptanceAPI.countProjectAcceptance(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目验收情况列表
     *
     * @param projectAcceptanceDTO 项目验收情况信息dto
     * @des 获取所有项目验收情况信息
     * @return  class ProjectAcceptanceVO
     * @version v1
     */
    @GetMapping("v1/listProjectAcceptance")
    public Result findListProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectAcceptanceVO> projectAcceptanceVOList = BeanTransform.copyProperties(
                    projectAcceptanceAPI.listProjectAcceptance(projectAcceptanceDTO), ProjectAcceptanceVO.class, true);
            return ActResult.initialize(projectAcceptanceVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目验收情况
     *
     * @param projectAcceptanceTO 项目验收情况基本信息数据to
     * @des 添加项目验收情况
     * @return  class ProjectAcceptanceVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProjectAcceptance(@Validated({ProjectAcceptanceTO.TESTAddAndEdit.class}) ProjectAcceptanceTO projectAcceptanceTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectAcceptanceBO projectAcceptanceBO1 = projectAcceptanceAPI.addProjectAcceptance(projectAcceptanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectAcceptanceBO1,ProjectAcceptanceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目验收情况
     *
     * @param projectAcceptanceTO 项目验收情况基本信息数据bo
     * @des 添加项目验收情况
     * @return  class ProjectAcceptanceVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editProjectAcceptance(@Validated({ProjectAcceptanceTO.TESTAddAndEdit.class}) ProjectAcceptanceTO projectAcceptanceTO) throws ActException {
        try {
            ProjectAcceptanceBO projectAcceptanceBO1 = projectAcceptanceAPI.editProjectAcceptance(projectAcceptanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectAcceptanceBO1,ProjectAcceptanceVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目验收情况信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectAcceptance(@PathVariable String id) throws ActException {
        try {
            projectAcceptanceAPI.deleteProjectAcceptance(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param projectAcceptanceDTO 项目验收情况信息dto
     * @des 获取所有项目验收情况信息
     * @return  class ProjectAcceptanceVO
     * @version v1
     */
    @GetMapping("v1/searchInfo")
    public Result searchInfo(ProjectAcceptanceDTO projectAcceptanceDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectAcceptanceVO> projectAcceptanceVOList = BeanTransform.copyProperties(
                    projectAcceptanceAPI.searchListProjectAcceptance(projectAcceptanceDTO), ProjectAcceptanceVO.class, true);
            return ActResult.initialize(projectAcceptanceVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @des 导入项目验收情况
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel( ) throws ActException {
        return  null;
    }

    /**
     * 导出
     *
     * @des 导出项目验收情况
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel( ) throws ActException {
        return  null;
    }

    /**
     * 附件上传
     *
     * @des 附件上传项目验收情况
     * @version v1
     */
    @PostMapping("v1/uplode")
    public Result uplodeFile( ) throws ActException {
        return  null;
    }
}