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
import com.bjike.goddess.foreigntax.api.IncomeInvoiceAPI;
import com.bjike.goddess.foreigntax.bo.IncomeInvoiceBO;
import com.bjike.goddess.foreigntax.dto.IncomeCollectDTO;
import com.bjike.goddess.foreigntax.dto.IncomeInvoiceDTO;
import com.bjike.goddess.foreigntax.to.DeleteFileTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.IncomeInvoiceTO;
import com.bjike.goddess.foreigntax.vo.IncomeInvoiceVO;
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
import java.util.List;

/**
 * 进项发票
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:45 ]
 * @Description: [ 进项发票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("incomeinvoice")
public class IncomeInvoiceAction extends BaseFileAction {
    @Autowired
    private IncomeInvoiceAPI incomeInvoiceAPI;
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

            Boolean isHasPermission = incomeInvoiceAPI.guidePermission(guidePermissionTO);
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
     * 进项发票列表总条数
     *
     * @param dto 进项发票dto
     * @des 获取所有进项发票总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IncomeInvoiceDTO dto) throws ActException {
        try {
            Long count = incomeInvoiceAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个进项发票
     *
     * @param id
     * @return class IncomeInvoiceVO
     * @des 获取一个进项发票
     * @version v1
     */
    @GetMapping("v1/income/{id}")
    public Result income(@PathVariable String id) throws ActException {
        try {
            IncomeInvoiceBO bo = incomeInvoiceAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, IncomeInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 进项发票列表
     *
     * @param dto 进项发票dto
     * @return class OutputInvoiceVO
     * @des 获取所有进项发票
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IncomeInvoiceDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<IncomeInvoiceVO> incomeInvoiceVOS = BeanTransform.copyProperties
                    (incomeInvoiceAPI.list(dto), IncomeInvoiceVO.class, request);
            return ActResult.initialize(incomeInvoiceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加进项发票
     *
     * @param to 进项发票数据to
     * @return class IncomeInvoiceVO
     * @des 添加进项发票
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) IncomeInvoiceTO to, BindingResult bindingResult) throws ActException {
        try {
            IncomeInvoiceBO bo = incomeInvoiceAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, IncomeInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑进项发票
     *
     * @param to 进项发票数据to
     * @return class IncomeInvoiceVO
     * @des 编辑进项发票
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IncomeInvoiceTO to, BindingResult bindingResult) throws ActException {
        try {
            IncomeInvoiceBO bo = incomeInvoiceAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, IncomeInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除进项发票
     *
     * @param id 用户id
     * @des 根据用户id删除进项发票记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            incomeInvoiceAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param dto
     * @return class IncomeInvoiceVO
     * @des 根据开始时间结束时间汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@Validated(IncomeCollectDTO.collect.class) IncomeCollectDTO dto) throws ActException {
        try {
            List<IncomeInvoiceBO> incomeInvoiceBOS = incomeInvoiceAPI.collect(dto);
            return ActResult.initialize(BeanTransform.copyProperties(incomeInvoiceBOS, IncomeInvoiceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 勾选汇总
     *
     * @param dto
     * @return class IncomeInvoiceVO
     * @des 根据开始时间结束时间勾选汇总
     * @version v1
     */
    @GetMapping("v1/checkCollect")
    public Result checkCollect(IncomeInvoiceDTO dto) throws ActException {
        try {
            List<IncomeInvoiceVO> incomeInvoiceVOS = BeanTransform.copyProperties(incomeInvoiceAPI.checkCollect(dto),IncomeInvoiceVO.class);
            return ActResult.initialize(incomeInvoiceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 进项发票
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