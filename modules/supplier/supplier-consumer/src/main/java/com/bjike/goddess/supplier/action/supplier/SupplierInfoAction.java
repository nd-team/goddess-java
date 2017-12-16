package com.bjike.goddess.supplier.action.supplier;

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
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.supplier.api.SupplierInfoAPI;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.excel.SupplierInfoExcel;
import com.bjike.goddess.supplier.to.SiginManageDeleteFileTO;
import com.bjike.goddess.supplier.to.SupplierInfoTO;
import com.bjike.goddess.supplier.vo.SupplierInfoVO;
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
 * 供应商信息管理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplierinfo")
public class SupplierInfoAction extends BaseFileAction {

    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private SupplierInfoAPI supplierInfoAPI;

    /**
     * 根据id查询供应商信息管理
     *
     * @param id 供应商信息管理唯一标识
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/supplierinfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.getOneById(id);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 供应商信息管理dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated SupplierInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = supplierInfoAPI.countSupplierInfo(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 供应商信息管理dto
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated SupplierInfoDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<SupplierInfoBO> boList = supplierInfoAPI.listSupplierInfo(dto);
            List<SupplierInfoVO> voList = BeanTransform.copyProperties(boList, SupplierInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 根据id查询供应商信息管理
//     *
//     * @param id
//     * @return class MarketServeApplyVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/findDetailById/{id}")
//    public Result findDetailById(@PathVariable String id, HttpServletRequest request) throws ActException {
//        try {
//            SupplierInfoBO bo = supplierInfoAPI.checkDetails(id);
//            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
//            return ActResult.initialize(vo);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 添加供应商信息管理
     *
     * @param to 供应商信息管理to信息
     * @return class SupplierInfoVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) SupplierInfoTO to, HttpServletRequest request, BindingResult result) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.addSupplierInfo(to);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除供应商信息管理
     *
     * @param id 供应商信息管理唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            supplierInfoAPI.deleteSupplierInfo(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑市场招待申请
     *
     * @param to 市场招待申请to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) SupplierInfoTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            SupplierInfoBO bo = supplierInfoAPI.editSupplierInfo(to);
            SupplierInfoVO vo = BeanTransform.copyProperties(bo, SupplierInfoVO.class, request);
            return ActResult.initialize(vo);
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
            String path = "/" + id;
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
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 导出Excel
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "供应商信息管理.xlsx";
            super.writeOutFile(response, supplierInfoAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SupplierInfoExcel> tos = ExcelUtil.excelToClazz(is, SupplierInfoExcel.class, excel);
            List<SupplierInfoTO> tocs = new ArrayList<>();
            for (SupplierInfoExcel str : tos) {
                SupplierInfoTO supplierInfoTO = BeanTransform.copyProperties(str, SupplierInfoTO.class,
                        "infoCollectDate", "newSigningDate", "newCutoffDate", "settlementCompleteDate",
                        "uploadBusinLicense", "uploadQualifition", "deterCooper", "payComplete", "infoPerfecting");
                supplierInfoTO.setInfoCollectDate(str.getInfoCollectDate().toString());
                supplierInfoTO.setNewSigningDate(str.getNewSigningDate().toString());
                supplierInfoTO.setNewCutoffDate(str.getNewCutoffDate().toString());
                supplierInfoTO.setSettlementCompleteDate(str.getSettlementCompleteDate().toString());
                supplierInfoTO.setUploadBusinLicense(stringToBool(str.getUploadBusinLicense(),"是否上传营业执照附件"));
                supplierInfoTO.setUploadQualifition(stringToBool(str.getUploadQualifition(),"是否上传资质附件"));
                supplierInfoTO.setDeterCooper(stringToBool(str.getDeterCooper(),"是否确定合作"));
                supplierInfoTO.setPayComplete(stringToBool(str.getPayComplete(),"是否付款完成"));
                supplierInfoTO.setInfoPerfecting(stringToBool(str.getInfoPerfecting(),"供应商信息是否完善"));
                tocs.add(supplierInfoTO);
            }
            //注意序列化
            supplierInfoAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private Boolean stringToBool(String type, String fileName) throws ActException {
        Boolean bool = null;
        if (type != null) {
            switch (type) {
                case "是":
                    bool = true;
                    break;
                case "否":
                    bool = false;
                    break;
                default:
                    throw new ActException(fileName + "格式输入错误,正确格式为(是/否)");

            }
        }
        return bool;
    }

    /**
     * excel模板下载
     *
     * @des 供应商信息管理模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "供应商信息管理模板.xlsx";
            super.writeOutFile(response, supplierInfoAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}