package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.dto.Restrict;
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
import com.bjike.goddess.contractcommunicat.api.ProjectContractAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectContractDTO;
import com.bjike.goddess.contractcommunicat.enums.QuartzCycleType;
import com.bjike.goddess.contractcommunicat.excel.ProjectContractExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.CommunicateDeleteFileTO;
import com.bjike.goddess.contractcommunicat.to.ProjectContractTO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractColelctVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectContractVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("contract")
public class ProjectContractAct extends BaseFileAction {

    @Autowired
    private ProjectContractAPI projectContractAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 内部项目名称列表
     *
     * @return class InProjectsVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {
        try {
            List<InProjectsVO> vo = BeanTransform.copyProperties(projectContractAPI.projects(), InProjectsVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.findById(id), ProjectContractVO.class, request);
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
    public Result count(ProjectContractDTO dto) throws ActException {
        try {
            if (!StringUtils.isEmpty(dto.getCommunicateUser())) {
                dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
            }
            if (!StringUtils.isEmpty(dto.getCommunicateObj())) {
                dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
            }
            if (dto.getCommunicateResult() != null) {
                dto.getConditions().add(Restrict.eq("projectResult", dto.getCommunicateResult()));
            }
            Long count = projectContractAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectContractVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProjectContractTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.saveProjectContract(to), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectContractVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProjectContractTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProjectContractVO vo = BeanTransform.copyProperties(projectContractAPI.editProjectContract(to), ProjectContractVO.class, request);
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
            projectContractAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/contract/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/contract/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("下载成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(CommunicateDeleteFileTO.TestDEL.class) CommunicateDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/lead")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ProjectContractExcel> tos = ExcelUtil.excelToClazz(is, ProjectContractExcel.class, excel);
            List<ProjectContractTO> toList = BeanTransform.copyProperties(tos, ProjectContractTO.class);
            projectContractAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出Excel
     *
     * @param contractInProject 内部项目名称Z
     * @param startDate         开始时间
     * @param endDate           结束时间
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(String contractInProject, String startDate, String endDate, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目承包洽谈.xlsx";
            super.writeOutFile(response, projectContractAPI.exportExcel(contractInProject, startDate, endDate), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 模糊查询条件
     * @return class ProjectContractVO
     * @version v1
     */
    @PostMapping("v1/list")
    public Result pageList(ProjectContractDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProjectContractVO> vo = BeanTransform.copyProperties(projectContractAPI.pageList(dto), ProjectContractVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param to 模糊查询条件
     * @return class ProjectContractColelctVO
     * @version v1
     */
    @PostMapping("v1/collect")
    public Result collect(CollectConditionTO to, HttpServletRequest request) throws ActException {

        try {
            List<ProjectContractColelctVO> vo = BeanTransform.copyProperties(projectContractAPI.collect(to), ProjectContractColelctVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置汇总周期
     *
     * @param cycleType 周期类型
     * @version v1
     */
    @LoginAuth
    @GetMapping("cycle")
    public Result setCollectSend(QuartzCycleType cycleType) throws ActException {

        try {
            projectContractAPI.setCollectSend(cycleType);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}