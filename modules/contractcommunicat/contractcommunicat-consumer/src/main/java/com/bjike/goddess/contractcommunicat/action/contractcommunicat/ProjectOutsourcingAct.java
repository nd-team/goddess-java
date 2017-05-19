package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.contractcommunicat.api.ProjectOutsourcingAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectOutsourcingExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.ExportExcelTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingCollectVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("outsource")
public class ProjectOutsourcingAct extends BaseFileAction {

    @Autowired
    private ProjectOutsourcingAPI projectOutsourcingAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 根据id查询项目承包洽谈
     *
     * @param id 项目承包洽谈id
     * @return class ProjectContractVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectOutsourcingAPI.findById(id), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectOutsourcingDTO dto) throws ActException {
        try {
            Long count = projectOutsourcingAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectOutsourcingTO to, BindingResult bindingResult) throws ActException {
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
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectOutsourcingTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(projectOutsourcingAPI.editProjectOutsource(to), ProjectOutsourcingVO.class, request);
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
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
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
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            String path = "/outsource";
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ProjectOutsourcingExcel> tos = ExcelUtil.excelToClazz(is, ProjectOutsourcingExcel.class, excel);
            List<ProjectOutsourcingTO> toList = BeanTransform.copyProperties(tos,ProjectOutsourcingTO.class);
            projectOutsourcingAPI.leadExcel(toList);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param to 导出条件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/exportExcel")
    public Result exportExcel(ExportExcelTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目外包洽谈.xlsx";
            super.writeOutFile(response, projectOutsourcingAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 模糊查询条件
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @PostMapping("v1/list")
    public Result pageList(ProjectOutsourcingDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProjectOutsourcingVO> vo = BeanTransform.copyProperties(projectOutsourcingAPI.pageList(dto), ProjectOutsourcingVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总分页查询
     *
     * @param to 模糊查询条件
     * @return class ProjectOutsourcingCollectVO
     * @version v1
     */
    @PostMapping("v1/collect")
    public Result collect(CollectConditionTO to) throws ActException {

        try {
            List<ProjectOutsourcingCollectVO> vo = BeanTransform.copyProperties(projectOutsourcingAPI.collect(to), ProjectOutsourcingCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总周期
     *
     * @param cycleType 周期类型
     * @version v1
     */
    @LoginAuth
    @GetMapping("cycle")
    public Result setCollectSend(QuartzCycleType cycleType) throws ActException {

        try {
            projectOutsourcingAPI.setCollectSend(cycleType);
            return new ActResult("success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}