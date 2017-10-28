package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.DispatchSheetAPI;
import com.bjike.goddess.businessproject.bo.DispatchSheetBO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.excel.DispatchSheetExcel;
import com.bjike.goddess.businessproject.to.DispatchSheetTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businessproject.vo.DispatchSheetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务项目派工单信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 10:06 ]
 * @Description: [ 商务项目派工单信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dispatchsheet")
public class DispatchSheetAction extends BaseFileAction {
    @Autowired
    private DispatchSheetAPI dispatchSheetAPI;
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

            Boolean isHasPermission = dispatchSheetAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param dispatchSheetDTO 派工单信息信息dto
     * @des 获取所有派工单信息信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DispatchSheetDTO dispatchSheetDTO) throws ActException {
        try {
            Long count = dispatchSheetAPI.countDispatchSheet(dispatchSheetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个派工单信息
     *
     * @param id 项目派工单信息信息id
     * @return class DispatchSheetVO
     * @des 根据id获取项目派工单信息信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            DispatchSheetVO projectCarryVO = BeanTransform.copyProperties(
                    dispatchSheetAPI.getOneById(id), DispatchSheetVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目派工单列表
     *
     * @param dispatchSheetDTO 项目派工单信息dto
     * @return class DispatchSheetVO
     * @des 获取所有项目派工单信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListDispatchSheet(DispatchSheetDTO dispatchSheetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DispatchSheetVO> dispatchSheetVOList = BeanTransform.copyProperties(
                    dispatchSheetAPI.listDispatchSheet(dispatchSheetDTO), DispatchSheetVO.class, request);
            return ActResult.initialize(dispatchSheetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目派工单
     *
     * @param dispatchSheetTO 项目派工单基本信息数据to
     * @return class DispatchSheetVO
     * @des 添加项目派工单
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDispatchSheet(@Validated(DispatchSheetTO.TestAdd.class) DispatchSheetTO dispatchSheetTO ,BindingResult bindingResult) throws ActException {
        try {
            DispatchSheetBO dispatchSheetBO1 = dispatchSheetAPI.addDispatchSheet(dispatchSheetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dispatchSheetBO1, DispatchSheetVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目派工单
     *
     * @param dispatchSheetTO 项目派工单基本信息数据bo
     * @return class DispatchSheetVO
     * @des 添加项目派工单
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editDispatchSheet(@Validated(DispatchSheetTO.TestAdd.class) DispatchSheetTO dispatchSheetTO) throws ActException {
        try {
            DispatchSheetBO dispatchSheetBO1 = dispatchSheetAPI.editDispatchSheet(dispatchSheetTO);
            return ActResult.initialize(BeanTransform.copyProperties(dispatchSheetBO1, DispatchSheetVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目派工单信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDispatchSheet(@PathVariable String id) throws ActException {
        try {
            dispatchSheetAPI.deleteDispatchSheet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有地区
     *
     * @des 获取所有项目派工单信息地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea() throws ActException {
        try {
            List<String> list = dispatchSheetAPI.listArea();

            return ActResult.initialize(list);
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
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/businessproject/dispatchsheet/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/businessproject/dispatchsheet/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
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
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO,HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 查找所有内部项目名称
     * chenjunhao
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allInnerProjects")
    public Result allInnerProjects() throws ActException {
        try {
            return ActResult.initialize(dispatchSheetAPI.allInnerProjects());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     * chenjunhao
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
            List<DispatchSheetExcel> toList = ExcelUtil.excelToClazz(is, DispatchSheetExcel.class, excel);
            List<DispatchSheetTO> tos = new ArrayList<DispatchSheetTO>();
            for (DispatchSheetExcel d : toList) {
                DispatchSheetTO dispatchSheetTO = BeanTransform.copyProperties(d, DispatchSheetTO.class);
                dispatchSheetTO.setSiginTime(String.valueOf(d.getSiginTime()));
                dispatchSheetTO.setStartProjectTime(String.valueOf(d.getStartProjectTime()));
                dispatchSheetTO.setEndProjectTime(String.valueOf(d.getEndProjectTime()));
                tos.add(dispatchSheetTO);
            }
            dispatchSheetAPI.leadExcel(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     * chenjunhao
     *
     * @param dto 商务项目派工单信息管理信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(DispatchSheetDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目派工单信息管理.xlsx";
            super.writeOutFile(response, dispatchSheetAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导出导入的excel模板
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目派工单信息管理导入excel模板.xlsx";
            super.writeOutFile(response, dispatchSheetAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}