package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ArchiveAccessAPI;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.excel.ArchiveAccessImportExcel;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.vo.ArchiveAccessVO;
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
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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
 * 档案调阅
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("archiveaccess")
public class ArchiveAccessAct extends BaseFileAction {

    @Autowired
    private ArchiveAccessAPI archiveAccessAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = archiveAccessAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = archiveAccessAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArchiveAccessTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.save(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ArchiveAccessTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.update(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 档案调阅数据id
     * @return class ArchiveAccessVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.delete(id), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param to 档案调阅传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @PutMapping("v1/audit/{id}")
    public Result audit(AccessAuditTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.audit(to), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 档案调阅数据传输对象
     * @return class ArchiveAccessVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArchiveAccessDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.maps(dto), ArchiveAccessVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return class ArchiveAccessVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(archiveAccessAPI.getById(id), ArchiveAccessVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(ArchiveAccessDTO dto) throws ActException {
        try {
            return ActResult.initialize(archiveAccessAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param id 档案调阅数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/ArchiveAccess/" + id;
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
     * @param id 档案调阅数据id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/ArchiveAccess/" + id;
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
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
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
     * @param paths 多文件信息路径
     * @version v1
     */
    @PostMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), paths);
            return new ActResult("delFile success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(HttpServletResponse response, ArchiveAccessDTO dto) throws ActException {
        try {
            String fileName = "档案调阅.xlsx";
            super.writeOutFile(response, archiveAccessAPI.exportExcel(dto), fileName);
            return ActResult.initialize("导出成功");
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
            String fileName = "excel模板下载.xlsx";
            super.writeOutFile(response, archiveAccessAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ArchiveAccessImportExcel> tos = ExcelUtil.excelToClazz(is, ArchiveAccessImportExcel.class, excel);
            List<ArchiveAccessTO> archiveAccessTOs = new ArrayList<>(0);
            if (null != tos && tos.size() > 0) {
                for (ArchiveAccessImportExcel excel1 : tos) {
                    ArchiveAccessTO to = BeanTransform.copyProperties(excel1, ArchiveAccessTO.class, false);
                    to.setAccessNames(excel1.getAccess().split(","));
                    archiveAccessTOs.add(to);
                }
            }
            archiveAccessAPI.upload(archiveAccessTOs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出时获取的姓名
     *
     * @version v1
     */
    @GetMapping("v1/findUserName")
    public Result findUserName() throws ActException {
        try {
            List<String> list = archiveAccessAPI.findUserName();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}