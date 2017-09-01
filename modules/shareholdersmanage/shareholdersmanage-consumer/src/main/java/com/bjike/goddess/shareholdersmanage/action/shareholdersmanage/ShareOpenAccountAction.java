package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

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
import com.bjike.goddess.shareholdersmanage.api.ShareOpenAccountAPI;
import com.bjike.goddess.shareholdersmanage.bo.ShareOpenAccountBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.excel.ShareOpenAccountExcel;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountBTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenAccountTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenDeleteFileTO;
import com.bjike.goddess.shareholdersmanage.type.TypeName;
import com.bjike.goddess.shareholdersmanage.vo.ShareOpenAccountVO;
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
 * 股东开户
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-18 02:41 ]
 * @Description: [ 股东开户 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("shareopenaccount")
public class ShareOpenAccountAction extends BaseFileAction {
    @Autowired
    private ShareOpenAccountAPI shareOpenAccountAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 列表总条数
     *
     * @param shareOpenAccountDTO 股东开户dto
     * @des 获取所有股东开户总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result countShareOpen(ShareOpenAccountDTO shareOpenAccountDTO) throws ActException {
        try {
            Long count = shareOpenAccountAPI.countShareOpen(shareOpenAccountDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股东开户
     *
     * @param id 股东开户id
     * @return class ShareOpenAccountVO
     * @des 根据id获取股东开户
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ShareOpenAccountVO shareOpenAccountVO = BeanTransform.copyProperties(
                    shareOpenAccountAPI.getOne(id), ShareOpenAccountVO.class);
            return ActResult.initialize(shareOpenAccountVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股东开户列表
     *
     * @param shareOpenAccountDTO 股东开户dto
     * @return class ShareOpenAccountVO
     * @des 获取所有股东开户
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListShareOpen(ShareOpenAccountDTO shareOpenAccountDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareOpenAccountVO> shareOpenAccountVOList = BeanTransform.copyProperties(
                    shareOpenAccountAPI.findList(shareOpenAccountDTO), ShareOpenAccountVO.class, request);
            return ActResult.initialize(shareOpenAccountVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股东开户
     *
     * @param shareOpenAccountTO 股东开户to
     * @return class ShareOpenAccountVO
     * @des 添加股东开户
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addShareOpen(@Validated({ADD.class}) ShareOpenAccountTO shareOpenAccountTO, BindingResult result) throws ActException {
        try {
            ShareOpenAccountBO shareOpenAccountBO = shareOpenAccountAPI.save(shareOpenAccountTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOpenAccountBO, ShareOpenAccountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股东开户
     *
     * @param shareOpenAccountBTO 股东开户数据bo
     * @return class ShareOpenAccountVO
     * @des 编辑股东开户
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) ShareOpenAccountBTO shareOpenAccountBTO, BindingResult result) throws ActException {
        try {
            ShareOpenAccountBO shareOpenAccountBO = shareOpenAccountAPI.edit(shareOpenAccountBTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareOpenAccountBO, ShareOpenAccountVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股东开户
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareOpen(@PathVariable String id) throws ActException {
        try {
            shareOpenAccountAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param shareOpenAccountDTO 股东开户数据dto
     * @return class ShareOpenAccountVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/proCompare")
    public Result proCompare(ShareOpenAccountDTO shareOpenAccountDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareOpenAccountBO> boList = shareOpenAccountAPI.summationShareOpen(shareOpenAccountDTO);
            List<ShareOpenAccountVO> voList = BeanTransform.copyProperties(boList, ShareOpenAccountVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ShareOpenAccountExcel> tos = ExcelUtil.excelToClazz(is, ShareOpenAccountExcel.class, excel);
            List<ShareOpenAccountTO> tocs = new ArrayList<>();
            for (ShareOpenAccountExcel str : tos) {
                ShareOpenAccountTO shareOpenAccountTO = BeanTransform.copyProperties(str, ShareOpenAccountTO.class, "openDate");
                shareOpenAccountTO.setOpenDate(String.valueOf(str.getOpenDate()));
                tocs.add(shareOpenAccountTO);
            }
            //注意序列化
            shareOpenAccountAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param shareOpenAccountDTO 股东开户dto
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ShareOpenAccountDTO shareOpenAccountDTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "股东开户.xlsx";
            super.writeOutFile(response, shareOpenAccountAPI.exportExcel(shareOpenAccountDTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载股东开户模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "股东开户模板.xlsx";
            super.writeOutFile(response, shareOpenAccountAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
    public Result delFile(@Validated(ShareOpenDeleteFileTO.TestDEL.class) ShareOpenDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
    /**
     * 所有的股东名称
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findShareholderName")
    public Result findShareholderName() throws ActException {
        try {
            List<String> shareholderName = new ArrayList<>();
            shareholderName = shareOpenAccountAPI.findShareholderName();
            return ActResult.initialize(shareholderName);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}