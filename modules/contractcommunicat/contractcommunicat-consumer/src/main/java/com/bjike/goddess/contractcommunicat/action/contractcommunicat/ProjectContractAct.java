package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.ProjectContractAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目承包洽谈
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-17T17:21:34.919 ]
 * @Description: [ 项目承包洽谈 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectcontract")
public class ProjectContractAct {

    @Autowired
    private ProjectContractAPI projectContractAPI;

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(ProjectContractTO to ,BindingResult bindingResult) throws ActException {
        try {
            ProjectContractVO voList = BeanTransform.copyProperties(projectContractAPI.saveProjectContract(to), ProjectContractVO.class);
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
    public Result edit(ProjectContractTO to , BindingResult bindingResult) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.editProjectContract(to), ProjectContractVO.class);
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
            projectContractAPI.delete(id);
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
    public Result pageList(ProjectContractDTO dto) throws ActException {

        try {
            List<ProjectContractVO> vo = BeanTransform.copyProperties(projectContractAPI.pageList(dto), ProjectContractVO.class);
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
    public Result collect(ProjectContractDTO dto) throws ActException {

        try {
            List<ProjectContractVO> vo = BeanTransform.copyProperties(projectContractAPI.collect(dto), ProjectContractVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("setCollectSend")
    public Result setCollectSend(QuartzCycleType cycleType) throws ActException {

        try {
            projectContractAPI.setCollectSend(cycleType);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}