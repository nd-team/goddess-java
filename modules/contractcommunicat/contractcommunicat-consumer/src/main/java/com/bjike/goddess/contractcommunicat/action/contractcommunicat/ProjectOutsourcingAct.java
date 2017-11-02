package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.vo.BaseInfoManageVO;
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
import com.bjike.goddess.contractcommunicat.api.ProjectOutsourcingAPI;
import com.bjike.goddess.contractcommunicat.dto.ProjectOutsourcingDTO;
import com.bjike.goddess.contractcommunicat.excel.ProjectOutsourcingExcel;
import com.bjike.goddess.contractcommunicat.to.CollectConditionTO;
import com.bjike.goddess.contractcommunicat.to.CommunicateDeleteFileTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.ProjectOutsourcingTO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingCollectVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
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
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = projectOutsourcingAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 内部项目名称列表
     *
     * @return class InProjectsVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {
        try {
            List<InProjectsVO> vo = BeanTransform.copyProperties(projectOutsourcingAPI.projects(), InProjectsVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询项目外包洽谈
     *
     * @param id 项目外包洽谈id
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(projectOutsourcingAPI.findById(id), ProjectOutsourcingVO.class, request);
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
            if (dto.getCommunicateUser() != null) {
                dto.getConditions().add(Restrict.like("communicateUser", dto.getCommunicateUser()));
            }
            if (dto.getCommunicateObj() != null) {
                dto.getConditions().add(Restrict.like("communicateObj", dto.getCommunicateObj()));
            }
            if (dto.getCommunicateResult() != null) {
                dto.getConditions().add(Restrict.eq("projectResult", dto.getCommunicateResult()));
            }

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
            String path = "/outsource/" + id;
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
            String path = "/outsource/" + id;
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
     * 导入Excel
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/lead")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ProjectOutsourcingExcel> tos = ExcelUtil.excelToClazz(is, ProjectOutsourcingExcel.class, excel);
            List<ProjectOutsourcingTO> toList = BeanTransform.copyProperties(tos, ProjectOutsourcingTO.class);
            projectOutsourcingAPI.leadExcel(toList);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param contractInProject 内部项目名称
     * @param startDate         开始日期
     * @param endDate           结束日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(String contractInProject, String startDate, String endDate, HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目外包洽谈.xlsx";
            super.writeOutFile(response, projectOutsourcingAPI.exportExcel(contractInProject, startDate, endDate), fileName);
            return new ActResult(0, "导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出Excel模板
     *
     * @version v1
     */
    @GetMapping("v1/module")
    public Result exportModule(HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目外包洽谈模板.xlsx";
            super.writeOutFile(response, projectOutsourcingAPI.exportExcelModule(), fileName);
            return new ActResult(0, "导出成功");
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

//    /**
//     * 查询所有的内部项目名称
//     * @return class MarketInfoVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/find/project")
//    public Result findProject() throws ActException{
//        try {
//            List<MarketInfoBO> boList = projectOutsourcingAPI.findProject();
//            List<MarketInfoVO> voList = BeanTransform.copyProperties(boList,MarketInfoVO.class);
//            return ActResult.initialize(voList);
//        }catch(SerException e){
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 获取所有合同外部项目名称和合同项目外部编号
     * @return class BaseInfoManageVO
     * @throws SerException
     * @version v1
     */
    @GetMapping("v1/list/baseInfoManage")
    public Result listBaseInfoManage() throws ActException{
        try {
            List<BaseInfoManageBO> boList = projectOutsourcingAPI.listBaseInfoManage();
            List<BaseInfoManageVO> voList = BeanTransform.copyProperties(boList,BaseInfoManageVO.class);
            return ActResult.initialize(voList);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }


}