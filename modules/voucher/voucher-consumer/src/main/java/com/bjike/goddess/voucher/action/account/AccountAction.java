package com.bjike.goddess.voucher.action.account;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.voucher.api.VoucherGenerateAPI;
import com.bjike.goddess.voucher.bo.AnalysisBO;
import com.bjike.goddess.voucher.bo.OptionBO;
import com.bjike.goddess.voucher.bo.PartBO;
import com.bjike.goddess.voucher.bo.VoucherGenerateBO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateExportDTO;
import com.bjike.goddess.voucher.enums.ExportStatus;
import com.bjike.goddess.voucher.excel.SonPermissionObject;
import com.bjike.goddess.voucher.excel.VoucherTemplateImportExcel;
import com.bjike.goddess.voucher.to.AnalysisTO;
import com.bjike.goddess.voucher.to.GuidePermissionTO;
import com.bjike.goddess.voucher.to.VoucherFileTO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import com.bjike.goddess.voucher.vo.AccountInfoVO;
import com.bjike.goddess.voucher.vo.AnalysisVO;
import com.bjike.goddess.voucher.vo.OptionVO;
import com.bjike.goddess.voucher.vo.VoucherGenerateVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 明细账
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 明细账 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("account")
public class AccountAction extends BaseFileAction {

    @Autowired
    private VoucherGenerateAPI voucherGenerateAPI;
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

            List<SonPermissionObject> hasPermissionList = voucherGenerateAPI.sonPermissionAccount();
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

            Boolean isHasPermission = voucherGenerateAPI.guidePermission(guidePermissionTO);
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
     * 明细账列表
     *
     * @param dto
     * @return class AccountInfoVO
     * @throws ActException
     * @des 根据日期或地区或项目名称或项目组部门或科目进行列表查看
     * @version v1
     */
    @GetMapping("v1/account")
    public Result account(VoucherGenerateDTO dto) throws ActException {
        try {
            List<AccountInfoVO> accountInfoVOS = BeanTransform.copyProperties(voucherGenerateAPI.accountCollect(dto), AccountInfoVO.class);
            for (AccountInfoVO accountInfoVO : accountInfoVOS) {
                accountInfoVO.setId(UUID.randomUUID().toString());
            }
            return ActResult.initialize(accountInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 明细账
     * @des 导出明细账
     * @version v1
     */
    @GetMapping("v1/exportAccount")
    public Result exportReport(VoucherGenerateDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "明细账.xlsx";
            super.writeOutFile(response, voucherGenerateAPI.exportExcelAccount(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有已过账的地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountArea")
    public Result accountArea() throws ActException {
        try {
            List<String> areaList = voucherGenerateAPI.accountArea();
            return ActResult.initialize(areaList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的项目名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountProjectName")
    public Result accountProjectName() throws ActException {
        try {
            List<String> projectNameList = voucherGenerateAPI.accountProjectName();
            return ActResult.initialize(projectNameList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的项目组部门
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountProjectGroup")
    public Result accountProjectGroup() throws ActException {
        try {
            List<String> projectGroupList = voucherGenerateAPI.accountProjectGroup();
            return ActResult.initialize(projectGroupList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有已过账的一级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/accountSubject")
    public Result accountSubject() throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.accountSubject();
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有已过账的根据一级科目获取二级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/subSubject")
    public Result subSubject(String firstSubject) throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.subSubject(firstSubject);
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有已过账的根据一级科目二级科目获取三级科目
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/thirdSubject")
    public Result thirdSubject(String firstSubject,String subSubject) throws ActException {
        try {
            List<String> subjectList = voucherGenerateAPI.thirdSubject(firstSubject, subSubject);
            return ActResult.initialize(subjectList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
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
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /voucher/id/....
            String path = "/" + id;
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(VoucherFileTO.TestDEL.class) VoucherFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}