package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.CasualtyPurchasingListAPI;
import com.bjike.goddess.businsurance.bo.CasualtyPurchasingListBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingListDTO;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingListExcel;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingListTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businsurance.vo.CasualtyPurchasingListVO;
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
 * 团体意外险购买名单
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("casualtypurchasinglist")
public class CasualtyPurchasingListAction extends BaseFileAction {
    @Autowired
    private CasualtyPurchasingListAPI casualtyPurchasingListAPI;
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
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = casualtyPurchasingListAPI.guidePermission(guidePermissionTO);
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
     * 总条数
     *
     * @param casualtyPurchasingListDTO 团体意外险购买名单dto
     * @des 获取所有团体意外险购买名单总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws ActException {
        try {
            Long count = casualtyPurchasingListAPI.countCasualty(casualtyPurchasingListDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个团体意外险购买名单
     *
     * @param id id
     * @return class CasualtyPurchasingListVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/casualty/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CasualtyPurchasingListBO bo = casualtyPurchasingListAPI.getOneCasualty(id);
            CasualtyPurchasingListVO vo = BeanTransform.copyProperties(bo, CasualtyPurchasingListVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 团体意外险购买名单列表
     *
     * @param casualtyPurchasingListDTO 团体意外险购买名单dto
     * @return class CasualtyPurchasingListVO
     * @des 获取所有团体意外险购买名单
     * @version v1
     */
    @GetMapping("v1/listCarInsure")
    public Result findList(CasualtyPurchasingListDTO casualtyPurchasingListDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CasualtyPurchasingListVO> casualtyPurchasingListVOS = BeanTransform.copyProperties(
                    casualtyPurchasingListAPI.listCasualty(casualtyPurchasingListDTO), CasualtyPurchasingListVO.class, true);
            return ActResult.initialize(casualtyPurchasingListVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param casualtyPurchasingListTO 团体意外险购买名单数据to
     * @return class CasualtyPurchasingListVO
     * @des 添加 团体意外险购买名单
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CasualtyPurchasingListTO casualtyPurchasingListTO, BindingResult bindingResult) throws ActException {
        try {
            CasualtyPurchasingListBO casualtyPurchasingListBO = casualtyPurchasingListAPI.addCasualty(casualtyPurchasingListTO);
            return ActResult.initialize(BeanTransform.copyProperties(casualtyPurchasingListBO, CasualtyPurchasingListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param casualtyPurchasingListTO 团体意外险购买名单数据bo
     * @return class CasualtyPurchasingListVO
     * @des 编辑团体意外险购买名单
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CasualtyPurchasingListTO casualtyPurchasingListTO) throws ActException {
        try {
            CasualtyPurchasingListBO casualtyPurchasingListBO = casualtyPurchasingListAPI.editCasualty(casualtyPurchasingListTO);
            return ActResult.initialize(BeanTransform.copyProperties(casualtyPurchasingListBO, CasualtyPurchasingListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除团体意外险购买名单
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            casualtyPurchasingListAPI.deleteCasualty(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
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
            List<CasualtyPurchasingListExcel> tos = ExcelUtil.excelToClazz(is, CasualtyPurchasingListExcel.class, excel);
            List<CasualtyPurchasingListTO> tocs = new ArrayList<>();
            for (CasualtyPurchasingListExcel str : tos) {
                CasualtyPurchasingListTO casualtyPurchasingListTO = BeanTransform.copyProperties(str, CasualtyPurchasingListTO.class, "effectiveDate", "surrInsurApplyDate", "birthDate");
                casualtyPurchasingListTO.setEffectiveDate(String.valueOf(str.getEffectiveDate()));
                casualtyPurchasingListTO.setSurrInsurApplyDate(String.valueOf(str.getEffectiveDate()));
                casualtyPurchasingListTO.setBirthDate(String.valueOf(str.getBirthDate()));
                tocs.add(casualtyPurchasingListTO);
            }
            //注意序列化
            casualtyPurchasingListAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出团体意外险购买名单
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "团体意外险购买名单.xlsx";
            super.writeOutFile(response, casualtyPurchasingListAPI.exportExcel(), fileName);
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
     * @des 下载模板团体意外险购买名单
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "团体意外险购买名单模板.xlsx";
            super.writeOutFile(response, casualtyPurchasingListAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
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
    public Result list(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
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
    public Result download(@RequestParam String path, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ActException {
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
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, javax.servlet.http.HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
}