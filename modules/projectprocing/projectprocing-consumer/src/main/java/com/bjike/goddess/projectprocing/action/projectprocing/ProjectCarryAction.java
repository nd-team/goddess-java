package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.ProjectCarryAPI;
import com.bjike.goddess.projectprocing.bo.ProjectCarryBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.to.ProjectCarryTO;
import com.bjike.goddess.projectprocing.vo.ProjectCarryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 项目实施
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprocing/projectcarry")
public class ProjectCarryAction {


    @Autowired
    private ProjectCarryAPI projectCarryAPI;

    /**
     *  项目实施列表总条数
     *
     * @param customerBaseInfoDTO  项目实施信息dto
     * @des 获取所有项目实施信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectCarryDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = projectCarryAPI.countProjectCarry(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目实施列表
     *
     * @param projectCarryDTO 项目实施信息dto
     * @des 获取所有项目实施信息
     * @return  class ProjectCarryVO
     * @version v1
     */
    @GetMapping("v1/listProjectCarry")
    public Result findListProjectCarry(ProjectCarryDTO projectCarryDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectCarryVO> projectCarryVOList = BeanTransform.copyProperties(
                    projectCarryAPI.listProjectCarry(projectCarryDTO), ProjectCarryVO.class, true);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目实施
     *
     * @param projectCarryTO 项目实施基本信息数据to
     * @des 添加项目实施
     * @return  class ProjectCarryVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProjectCarry(@Validated({ProjectCarryTO.TESTAddAndEdit.class}) ProjectCarryTO projectCarryTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectCarryBO projectCarryBO1 = projectCarryAPI.addProjectCarry(projectCarryTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectCarryBO1,ProjectCarryVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目实施
     *
     * @param projectCarryTO 项目实施基本信息数据bo
     * @des 添加项目实施
     * @return  class ProjectCarryVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editProjectCarry(@Validated({ProjectCarryTO.TESTAddAndEdit.class}) ProjectCarryTO projectCarryTO) throws ActException {
        try {
            ProjectCarryBO projectCarryBO1 = projectCarryAPI.editProjectCarry(projectCarryTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectCarryBO1,ProjectCarryVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目实施信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectCarry(@PathVariable String id) throws ActException {
        try {
            projectCarryAPI.deleteProjectCarry(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param projectCarryDTO 项目实施信息dto
     * @des 获取所有项目实施信息
     * @return  class ProjectCarryVO
     * @version v1
     */
    @GetMapping("v1/searchInfo")
    public Result searchInfo(ProjectCarryDTO projectCarryDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectCarryVO> projectCarryVOList = BeanTransform.copyProperties(
                    projectCarryAPI.searchListProjectCarry(projectCarryDTO), ProjectCarryVO.class, true);
            return ActResult.initialize(projectCarryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @des 导入项目实施
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel( ) throws ActException {
        return  null;
    }

    /**
     * 导出
     *
     * @des 导出项目实施
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel( ) throws ActException {
        return  null;
    }

    /**
     * 附件上传
     *
     * @des 附件上传项目实施
     * @version v1
     */
    @PostMapping("v1/uplode")
    public Result uplodeFile( ) throws ActException {
        return  null;
    }


    /**
     * 工程开展中问题处理汇总表
     *
     * @des 工程开展中问题处理汇总表(关联项目终问题受理和处理的汇总表)
     * @return  class ProjectCarryVO
     * @version v1
     */
    @GetMapping("v1/relationCollect")
    public Result relationCollect(ProjectCarryDTO projectCarryDTO) throws ActException {
       return null;
    }

}