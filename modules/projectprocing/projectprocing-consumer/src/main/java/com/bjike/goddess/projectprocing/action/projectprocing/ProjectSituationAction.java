package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.ProjectSituationAPI;
import com.bjike.goddess.projectprocing.bo.ProjectSituationBO;
import com.bjike.goddess.projectprocing.dto.ProjectSituationDTO;
import com.bjike.goddess.projectprocing.to.ProjectSituationTO;
import com.bjike.goddess.projectprocing.vo.ProjectSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目情况
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprocing/projectsituation")
public class ProjectSituationAction {


    @Autowired
    private ProjectSituationAPI projectSituationAPI;

    /**
     *  项目情况列表总条数
     *
     * @param customerBaseInfoDTO  项目情况信息dto
     * @des 获取所有项目情况信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectSituationDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = projectSituationAPI.countProjectSituation(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目情况列表
     *
     * @param projectSituationDTO 项目情况信息dto
     * @des 获取所有项目情况信息
     * @return  class ProjectSituationVO
     * @version v1
     */
    @GetMapping("v1/listProjectSituation")
    public Result findListProjectSituation(ProjectSituationDTO projectSituationDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectSituationVO> projectSituationVOList = BeanTransform.copyProperties(
                    projectSituationAPI.listProjectSituation(projectSituationDTO), ProjectSituationVO.class, true);
            return ActResult.initialize(projectSituationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目情况
     *
     * @param projectSituationTO 项目情况基本信息数据to
     * @des 添加项目情况
     * @return  class ProjectSituationVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProjectSituation(@Validated({ProjectSituationTO.TESTAddAndEdit.class}) ProjectSituationTO projectSituationTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectSituationBO projectSituationBO1 = projectSituationAPI.addProjectSituation(projectSituationTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectSituationBO1,ProjectSituationVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目情况
     *
     * @param projectSituationTO 项目情况基本信息数据bo
     * @des 添加项目情况
     * @return  class ProjectSituationVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editProjectSituation(@Validated({ProjectSituationTO.TESTAddAndEdit.class}) ProjectSituationTO projectSituationTO) throws ActException {
        try {
            ProjectSituationBO projectSituationBO1 = projectSituationAPI.editProjectSituation(projectSituationTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectSituationBO1,ProjectSituationVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目情况信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectSituation(@PathVariable String id) throws ActException {
        try {
            projectSituationAPI.deleteProjectSituation(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param projectSituationDTO 项目情况信息dto
     * @des 获取所有项目情况信息
     * @return  class ProjectSituationVO
     * @version v1
     */
    @GetMapping("v1/searchInfo")
    public Result searchInfo(ProjectSituationDTO projectSituationDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectSituationVO> projectSituationVOList = BeanTransform.copyProperties(
                    projectSituationAPI.searchListProjectSituation(projectSituationDTO), ProjectSituationVO.class, true);
            return ActResult.initialize(projectSituationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @des 导入项目情况
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel( ) throws ActException {
        return  null;
    }

    /**
     * 导出
     *
     * @des 导出项目情况
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel( ) throws ActException {
        return  null;
    }

    /**
     * 附件上传
     *
     * @des 附件上传项目情况
     * @version v1
     */
    @PostMapping("v1/uplode")
    public Result uplodeFile( ) throws ActException {
        return  null;
    }

}