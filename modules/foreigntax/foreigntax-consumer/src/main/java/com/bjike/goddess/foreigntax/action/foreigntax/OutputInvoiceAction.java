package com.bjike.goddess.foreigntax.action.foreigntax;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.OutputInvoiceAPI;
import com.bjike.goddess.foreigntax.bo.OutputInvoiceBO;
import com.bjike.goddess.foreigntax.dto.OutputInvoiceDTO;
import com.bjike.goddess.foreigntax.to.DeleteFileTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.OutputInvoiceTO;
import com.bjike.goddess.foreigntax.vo.OutputInvoiceVO;
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
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 销项发票
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:43 ]
 * @Description: [ 销项发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("outputinvoice")
public class OutputInvoiceAction extends BaseFileAction{
    @Autowired
    private OutputInvoiceAPI outputInvoiceAPI;
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

            Boolean isHasPermission = outputInvoiceAPI.guidePermission(guidePermissionTO);
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
     * 销项发票列表总条数
     *
     * @param dto 销项发票dto
     * @des 获取所有销项发票总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(OutputInvoiceDTO dto) throws ActException {
        try {
            Long count = outputInvoiceAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个销项发票
     *
     * @param id
     * @return class OutputInvoiceVO
     * @des 获取一个销项发票
     * @version v1
     */
    @GetMapping("v1/output/{id}")
    public Result output(@PathVariable String id) throws ActException {
        try {
            OutputInvoiceBO bo = outputInvoiceAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OutputInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 销项发票列表
     *
     * @param dto 销项发票dto
     * @return class OutputInvoiceVO
     * @des 获取所有销项发票
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OutputInvoiceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OutputInvoiceVO> outputInvoiceVOS = BeanTransform.copyProperties
                    (outputInvoiceAPI.list(dto), OutputInvoiceVO.class, request);
            return ActResult.initialize(outputInvoiceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加销项发票
     *
     * @param to 销项发票数据to
     * @return class OutputInvoiceVO
     * @des 添加销项发票
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) OutputInvoiceTO to, BindingResult bindingResult) throws ActException {
        try {
            OutputInvoiceBO bo = outputInvoiceAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OutputInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑销项发票
     *
     * @param to 销项发票数据to
     * @return class OutputInvoiceVO
     * @des 编辑销项发票
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) OutputInvoiceTO to, BindingResult bindingResult) throws ActException {
        try {
            OutputInvoiceBO bo = outputInvoiceAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OutputInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除销项发票
     *
     * @param id 用户id
     * @des 根据用户id删除销项发票记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            outputInvoiceAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param year
     * @param month
     * @return class OutputInvoiceVO
     * @throws ActException
     * @des 根据月份汇总
     * @version v1
     */
    @GetMapping("v1/collect/{year}/{month}")
    public Result collect(@PathVariable Integer year, @PathVariable Integer month) throws ActException {
        try {
            List<OutputInvoiceBO> outputInvoiceBOS = outputInvoiceAPI.collect(year, month);
            return ActResult.initialize(BeanTransform.copyProperties(outputInvoiceBOS, OutputInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 年份
     *
     * @version v1
     */
    @GetMapping("v1/year")
    public Result yearList() throws ActException {
        try {
            //获取所有年
            List<Integer> yearList = new ArrayList<>();
            int year = LocalDate.now().getYear();

            for (int i = year - 5; i <= year + 5; i++) {
                yearList.add(i);
            }
            return ActResult.initialize(yearList);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 销项发票
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
     * @param id 销项发票id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /bidding/id/....
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
     * @param deleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DeleteFileTO.TestDEL.class) DeleteFileTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
}