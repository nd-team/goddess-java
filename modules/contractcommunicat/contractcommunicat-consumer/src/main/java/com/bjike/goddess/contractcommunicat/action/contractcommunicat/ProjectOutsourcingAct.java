package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.ProjectOutsourcingAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目外包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-18T09:24:12.806 ]
 * @Description: [ 项目外包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectoutsourcing")
public class ProjectOutsourcingAct {

    @Autowired
    private ProjectOutsourcingAPI projectOutsourcingAPI;

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProjectOutsourcingTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectOutsourcingVO voList = BeanTransform.copyProperties(projectOutsourcingAPI.saveProjectOutsource(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(ProjectOutsourcingTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(projectOutsourcingAPI.editProjectOutsource(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目承包洽谈
     *
     * @param id 项目承包洽谈ID
     * @version v1
     */
    @GetMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            projectOutsourcingAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) {
        // TODO: 17-3-20
        return null;
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) {
        // TODO: 17-3-20
        return null;
    }

    /**
     * 导出Excel
     *
     * @version v1
     */
    @PostMapping("v1/exportExcel")
    public Result exportExcel() {
        // TODO: 17-3-20
        return null;
    }

    /**
     * 分页查询
     *
     * @param dto 模糊查询条件
     * @version v1
     */
    @PostMapping("v1/pageList")
    public Result pageList(ProjectOutsourcingDTO dto) throws ActException {

        try {
            List<ProjectOutsourcingVO> vo = BeanTransform.copyProperties(projectOutsourcingAPI.pageList(dto), ProjectOutsourcingVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页汇总查询
     *
     * @param dto 模糊查询条件
     * @version v1
     */
    @PostMapping("v1/collect")
    public Result collect(ProjectOutsourcingDTO dto) throws ActException {

        try {
            List<ProjectOutsourcingVO> vo = BeanTransform.copyProperties(projectOutsourcingAPI.collect(dto), ProjectOutsourcingVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("setCollectSend")
    public Result setCollectSend(QuartzCycleType cycleType) throws ActException {

        try {
            projectOutsourcingAPI.setCollectSend(cycleType);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}