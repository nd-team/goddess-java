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
import com.bjike.goddess.shareholdersmanage.api.ShareChangeAPI;
import com.bjike.goddess.shareholdersmanage.bo.ShareChangeBO;
import com.bjike.goddess.shareholdersmanage.dto.ShareChangeDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareChange;
import com.bjike.goddess.shareholdersmanage.to.ShareChangeTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenDeleteFileTO;
import com.bjike.goddess.shareholdersmanage.vo.ShareChangeVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 股东变更
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:37 ]
 * @Description: [ 股东变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("sharechange")
public class ShareChangeAction extends BaseFileAction {
    @Autowired
    private ShareChangeAPI shareChangeAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 列表总条数
     *
     * @param shareChangeDTO 股东变更dto
     * @des 获取所有股东变更总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ShareChangeDTO shareChangeDTO) throws ActException {
        try {
            Long count = shareChangeAPI.countShareChange(shareChangeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股东变更
     *
     * @param id 股东变更id
     * @return class ShareChangeVO
     * @des 根据id获取股东变更
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ShareChangeVO shareChangeVO = BeanTransform.copyProperties(
                    shareChangeAPI.getOne(id), ShareChangeVO.class);
            return ActResult.initialize(shareChangeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股东变更列表
     *
     * @param shareChangeDTO 股东变更dto
     * @return class ShareChangeVO
     * @des 获取所有股东变更
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findShareChange(ShareChangeDTO shareChangeDTO, HttpServletRequest request) throws ActException {
        try {
            List<ShareChangeVO> shareChangeVOList = BeanTransform.copyProperties(
                    shareChangeAPI.findList(shareChangeDTO), ShareChangeVO.class, request);
            return ActResult.initialize(shareChangeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股东变更
     *
     * @param shareChangeTO 股东变更to
     * @return class ShareChangeVO
     * @des 添加股东变更
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addShareOpen(@Validated({ADD.class}) ShareChangeTO shareChangeTO, BindingResult result) throws ActException {
        try {
            ShareChangeBO shareChangeBO = shareChangeAPI.save(shareChangeTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareChangeBO, ShareChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股东变更
     *
     * @param shareChangeTO 股东变更数据bo
     * @return class ShareChangeVO
     * @des 编辑股东变更
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) ShareChangeTO shareChangeTO, BindingResult result) throws ActException {
        try {
            ShareChangeBO shareChangeBO = shareChangeAPI.edit(shareChangeTO);
            return ActResult.initialize(BeanTransform.copyProperties(shareChangeBO, ShareChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股东变更
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            shareChangeAPI.delete(id);
            return new ActResult("delete success!");
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
    public Result delFile(@Validated(ShareOpenDeleteFileTO.TestDEL.class) ShareOpenDeleteFileTO siginManageDeleteFileTO, javax.servlet.http.HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
}