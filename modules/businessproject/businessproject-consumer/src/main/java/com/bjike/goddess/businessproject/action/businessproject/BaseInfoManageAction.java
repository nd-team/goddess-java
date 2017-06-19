package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.BaseInfoManageAPI;
import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.businessproject.dto.BaseInfoManageDTO;
import com.bjike.goddess.businessproject.excel.BaseInfoManageExcel;
import com.bjike.goddess.businessproject.excel.BaseInfoManageLeadExcel;
import com.bjike.goddess.businessproject.to.BaseInfoManageTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.businessproject.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businessproject.vo.BaseInfoManageVO;
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
 * 商务项目合同基本信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:34:51.350 ]
 * @Description: [ 商务项目合同基本信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("baseinfomanage")
public class BaseInfoManageAction extends BaseFileAction {

    @Autowired
    private BaseInfoManageAPI baseInfoManageAPI;
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

            Boolean isHasPermission = baseInfoManageAPI.guidePermission(guidePermissionTO);
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
     * @param baseInfoManageDTO 基本信息信息dto
     * @des 获取所有基本信息信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BaseInfoManageDTO baseInfoManageDTO) throws ActException {
        try {
            Long count = baseInfoManageAPI.countBaseInfoManage(baseInfoManageDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个基本信息
     *
     * @param id 项目基本信息信息id
     * @return class BaseInfoManageVO
     * @des 根据id获取项目基本信息信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            BaseInfoManageVO projectCarryVO = BeanTransform.copyProperties(
                    baseInfoManageAPI.getOneById(id), BaseInfoManageVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目合同列表
     *
     * @param baseInfoManageDTO 项目合同信息dto
     * @return class BaseInfoManageVO
     * @des 获取所有项目合同信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListBaseInfoManage(BaseInfoManageDTO baseInfoManageDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<BaseInfoManageVO> baseInfoManageVOList = BeanTransform.copyProperties(
                    baseInfoManageAPI.listBaseInfoManage(baseInfoManageDTO), BaseInfoManageVO.class, request);
            return ActResult.initialize(baseInfoManageVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加项目合同
     *
     * @param baseInfoManageTO 项目合同基本信息数据to
     * @return class BaseInfoManageVO
     * @des 添加项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({BaseInfoManageTO.TestAdd.class}) BaseInfoManageTO baseInfoManageTO) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.addBaseInfoManage(baseInfoManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目合同
     *
     * @param baseInfoManageTO 项目合同基本信息数据bo
     * @return class BaseInfoManageVO
     * @des 添加项目合同
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({BaseInfoManageTO.TestEdit.class}) BaseInfoManageTO baseInfoManageTO) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.editBaseInfoManage(baseInfoManageTO);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目合同信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            baseInfoManageAPI.deleteBaseInfoManage(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取项目合同
     *
     * @param innerProjectNum 内部项目编号
     * @return class BaseInfoManageVO
     * @des 根据内部项目编号获取项目合同
     * @version v1
     */
    @GetMapping("v1/getByInnerProjectNum")
    public Result getBaseInfoManage(String innerProjectNum) throws ActException {
        try {
            BaseInfoManageBO baseInfoManageBO1 = baseInfoManageAPI.getInfoByInnerProjectNum(innerProjectNum);
            return ActResult.initialize(BeanTransform.copyProperties(baseInfoManageBO1, BaseInfoManageVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取内部项目编号
     *
     * @des 获取内部项目编号
     * @version v1
     */
    @GetMapping("v1/getInnerNum")
    public Result getInnerNum() throws ActException {
        try {
            List<String> list = baseInfoManageAPI.getInnerNum();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有甲方公司
     *
     * @des 获取所有甲方公司
     * @version v1
     */
    @GetMapping("v1/listCompany")
    public Result listCompany() throws ActException {
        try {
            List<String> list = baseInfoManageAPI.listFirstCompany();

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
            String path = "/businessproject/baseInfoManage/" + id;
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
            String path = "/businessproject/baseInfoManage/" + id;
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
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
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
            return ActResult.initialize(baseInfoManageAPI.allInnerProjects());
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
            List<BaseInfoManageLeadExcel> toList = ExcelUtil.excelToClazz(is, BaseInfoManageLeadExcel.class, excel);
            List<BaseInfoManageTO> tos = new ArrayList<BaseInfoManageTO>();
            for (BaseInfoManageLeadExcel to : toList) {
                BaseInfoManageTO baseInfoManageTO = BeanTransform.copyProperties(to, BaseInfoManageTO.class);
                baseInfoManageTO.setSiginTime(String.valueOf(to.getSiginTime()));
                baseInfoManageTO.setStartProjectTime(String.valueOf(to.getStartProjectTime()));
                baseInfoManageTO.setEndProjectTime(String.valueOf(to.getEndProjectTime()));
                tos.add(baseInfoManageTO);
            }
            baseInfoManageAPI.leadExcel(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     * chenjunhao
     *
     * @param dto 商务项目合同基本信息管理信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(BaseInfoManageDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目合同基本信息管理.xlsx";
            super.writeOutFile(response, baseInfoManageAPI.exportExcel(dto), fileName);
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
    @LoginAuth
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "商务项目合同基本信息管理导入excel模板.xlsx";
            super.writeOutFile(response, baseInfoManageAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}