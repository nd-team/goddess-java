package com.bjike.goddess.foreigntax.action.foreigntax;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.api.LandTaxReportAPI;
import com.bjike.goddess.foreigntax.bo.DiscountRecordBO;
import com.bjike.goddess.foreigntax.bo.LandTaxReportBO;
import com.bjike.goddess.foreigntax.dto.DiscountRecordDTO;
import com.bjike.goddess.foreigntax.dto.LandTaxReportDTO;
import com.bjike.goddess.foreigntax.entity.LandTaxReport;
import com.bjike.goddess.foreigntax.to.DiscountRecordTO;
import com.bjike.goddess.foreigntax.to.ForeignTaxDeleteFileTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.LandTaxReportTO;
import com.bjike.goddess.foreigntax.vo.DiscountRecordVO;
import com.bjike.goddess.foreigntax.vo.LandTaxReportVO;
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
 * 国地税报表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("landtaxreport")
public class LandTaxReportAction extends BaseFileAction{
    @Autowired
    private LandTaxReportAPI landTaxReportAPI;
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

            Boolean isHasPermission = landTaxReportAPI.guidePermission(guidePermissionTO);
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
     * 国地税报表列表总条数
     *
     * @param dto 国地税报表dto
     * @des 获取所有国地税报表总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LandTaxReportDTO dto) throws ActException {
        try {
            Long count = landTaxReportAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个国地税报表
     *
     * @param id
     * @return class LandTaxReportVO
     * @des 获取一个国地税报表
     * @version v1
     */
    @GetMapping("v1/record/{id}")
    public Result record(@PathVariable String id) throws ActException {
        try {
            LandTaxReportBO bo = landTaxReportAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, LandTaxReportVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 国地税报表列表
     *
     * @param dto 国地税报表dto
     * @return class LandTaxReportVO
     * @des 获取所有国地税报表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(LandTaxReportDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<LandTaxReportVO> landTaxReportVOS = BeanTransform.copyProperties
                    (landTaxReportAPI.list(dto), LandTaxReportVO.class, request);
            return ActResult.initialize(landTaxReportVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加国地税报表
     *
     * @param to 国地税报表数据to
     * @return class LandTaxReportVO
     * @des 添加国地税报表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) LandTaxReportTO to, BindingResult bindingResult) throws ActException {
        try {
            LandTaxReportBO bo = landTaxReportAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, LandTaxReportVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑国地税报表
     *
     * @param to 国地税报表数据to
     * @return class LandTaxReportVO
     * @des 编辑国地税报表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) LandTaxReportTO to, BindingResult bindingResult) throws ActException {
        try {
            LandTaxReportBO bo = landTaxReportAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, LandTaxReportVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除国地税报表
     *
     * @param id 用户id
     * @des 根据用户id删除国地税报表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            landTaxReportAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 国地税报表
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
     * @param id 国地税报表id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /foreigntax/id/....
            String path = "/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            String token = RpcContext.getContext().getAttachment("storageToken");
            fileInfo.setStorageToken(token);
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
     * @param foreignTaxDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ForeignTaxDeleteFileTO.TestDEL.class) ForeignTaxDeleteFileTO foreignTaxDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != foreignTaxDeleteFileTO.getPaths() && foreignTaxDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), foreignTaxDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}