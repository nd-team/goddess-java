package com.bjike.goddess.projectprocing.action.projectprocing;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.api.ProjectSettlementFollowAPI;
import com.bjike.goddess.projectprocing.bo.ProjectSettlementFollowBO;
import com.bjike.goddess.projectprocing.dto.ProjectSettlementFollowDTO;
import com.bjike.goddess.projectprocing.to.ProjectSettlementFollowTO;
import com.bjike.goddess.projectprocing.utils.CollectData;
import com.bjike.goddess.projectprocing.vo.ProjectSettlementFollowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目结算跟进
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:49 ]
 * @Description: [ 项目结算跟进 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectprocing/projectsettlementfollow")
public class ProjectSettlementFollowAction {


    @Autowired
    private ProjectSettlementFollowAPI projectSettlementFollowAPI;

    /**
     * 项目结算跟进列表总条数
     *
     * @param customerBaseInfoDTO 项目结算跟进信息dto
     * @des 获取所有项目结算跟进信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectSettlementFollowDTO customerBaseInfoDTO) throws ActException {
        try {
            Long count = projectSettlementFollowAPI.countProjectSettlementFollow(customerBaseInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目结算跟进列表
     *
     * @param projectSettlementFollowDTO 项目结算跟进信息dto
     * @return class ProjectSettlementFollowVO
     * @des 获取所有项目结算跟进信息
     * @version v1
     */
    @GetMapping("v1/listProjectSettlementFollow")
    public Result findListProjectSettlementFollow(ProjectSettlementFollowDTO projectSettlementFollowDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectSettlementFollowVO> projectSettlementFollowVOList = BeanTransform.copyProperties(
                    projectSettlementFollowAPI.listProjectSettlementFollow(projectSettlementFollowDTO), ProjectSettlementFollowVO.class, true);
            return ActResult.initialize(projectSettlementFollowVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目结算跟进
     *
     * @param projectSettlementFollowTO 项目结算跟进基本信息数据to
     * @return class ProjectSettlementFollowVO
     * @des 添加项目结算跟进
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addProjectSettlementFollow(@Validated({ProjectSettlementFollowTO.TESTAddAndEdit.class}) ProjectSettlementFollowTO projectSettlementFollowTO, BindingResult bindingResult) throws ActException {
        try {
            ProjectSettlementFollowBO projectSettlementFollowBO1 = projectSettlementFollowAPI.addProjectSettlementFollow(projectSettlementFollowTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectSettlementFollowBO1, ProjectSettlementFollowVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目结算跟进
     *
     * @param projectSettlementFollowTO 项目结算跟进基本信息数据bo
     * @return class ProjectSettlementFollowVO
     * @des 添加项目结算跟进
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editProjectSettlementFollow(@Validated({ProjectSettlementFollowTO.TESTAddAndEdit.class}) ProjectSettlementFollowTO projectSettlementFollowTO) throws ActException {
        try {
            ProjectSettlementFollowBO projectSettlementFollowBO1 = projectSettlementFollowAPI.editProjectSettlementFollow(projectSettlementFollowTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectSettlementFollowBO1, ProjectSettlementFollowVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目结算跟进信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProjectSettlementFollow(@PathVariable String id) throws ActException {
        try {
            projectSettlementFollowAPI.deleteProjectSettlementFollow(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param projectSettlementFollowDTO 项目结算跟进信息dto
     * @return class ProjectSettlementFollowVO
     * @des 获取所有项目结算跟进信息
     * @version v1
     */
    @GetMapping("v1/searchInfo")
    public Result searchInfo(ProjectSettlementFollowDTO projectSettlementFollowDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ProjectSettlementFollowVO> projectSettlementFollowVOList = BeanTransform.copyProperties(
                    projectSettlementFollowAPI.searchListProjectSettlementFollow(projectSettlementFollowDTO), ProjectSettlementFollowVO.class, true);
            return ActResult.initialize(projectSettlementFollowVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @des 导入项目结算跟进
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel() throws ActException {
        return null;
    }

    /**
     * 导出
     *
     * @des 导出项目结算跟进
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel() throws ActException {
        return null;
    }

    /**
     * 附件上传
     *
     * @des 附件上传项目结算跟进
     * @version v1
     */
    @PostMapping("v1/uplode")
    public Result uplodeFile() throws ActException {
        return null;
    }

    /**
     * 汇总
     *
     * @param projectSettlementFollowDTO 项目结算跟进信息dto
     * @return class CollectData
     * @des 汇总项目结算跟进信息
     * @version v1
     */
    @GetMapping("v1/collectSettle")
    public Result collectSettle(ProjectSettlementFollowDTO projectSettlementFollowDTO) throws ActException  {

        try {
            List<CollectData> collectDataList = projectSettlementFollowAPI.collect(projectSettlementFollowDTO);
            return ActResult.initialize(collectDataList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }
}