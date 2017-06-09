package com.bjike.goddess.storage.action.file;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.StorageAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.constant.StorageCommon;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 文件存储
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@StorageAuth
@RestController
@RequestMapping("file")
public class FileAct extends BaseFileAction {
    @Autowired
    private FileAPI fileAPI;


    /**
     * 预览文件
     *
     * @param fileInfo
     * @param result
     * @return
     * @throws ActException
     */
    @GetMapping("v1/preview")
    public Result preview(@Validated({FileInfo.COMMON.class}) FileInfo fileInfo, HttpServletResponse response, BindingResult result,HttpServletRequest request) throws ActException {
        String url = null;
        try {
            handlerToken(request,fileInfo);
            byte[] bytes = fileAPI.download(fileInfo);
            url = previewUrl(bytes, fileInfo.getFileName());
            return ActResult.initialize(url);
        } catch (
                SerException e)

        {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 文件列表
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件上传
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result,HttpServletRequest request ) throws ActException {
        try {
            handlerToken(request,fileInfo);
            List<InputStream> inputStreams = getInputStreams(request);
            List<FileBO> fileBOS = fileAPI.upload(inputStreams);
            return new ActResult("upload success",BeanTransform.copyProperties(fileBOS,FileVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 文件是否存在
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @GetMapping("v1/exists")
    public Result exists(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            if (fileAPI.existsFile(fileInfo)) {
                return new ActResult(filename + " is exists!");
            }

        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("not exists!");

    }


    /**
     * 文件夹创建
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PostMapping("v1/mkdir")
    public Result mkdir(@Validated({FileInfo.COMMON.class, FileInfo.MKDIR.class}) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws SerException {
        handlerToken(request,fileInfo);
        fileAPI.mkDir(fileInfo);
        return new ActResult("mkDir success");
    }

    /**
     * 删除文件、文件夹
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delFile(@Validated({FileInfo.COMMON.class}) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws SerException {
        handlerToken(request,fileInfo);
        fileAPI.delFile(fileInfo);
        return new ActResult("delFile success");
    }


    /**
     * 重命名文件、文件夹
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PutMapping("v1/rename")
    public Result rename(@Validated({FileInfo.COMMON.class, FileInfo.RENAME.class}) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws SerException {
        handlerToken(request,fileInfo);
        fileAPI.rename(fileInfo);
        return new ActResult("rename success");
    }

    /**
     * 文件下载
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @GetMapping("v1/download")
    public Result download(@Validated({FileInfo.COMMON.class}) FileInfo fileInfo, HttpServletResponse response, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 文件、文件夹移动
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PutMapping("v1/move")
    public Result move(@Validated({FileInfo.MOVE.class, FileInfo.COMMON.class}) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            Boolean rs = fileAPI.move(fileInfo);
            return ActResult.initialize(rs);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 文件、文件夹回收
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PutMapping("v1/recycle")
    public Result recycle(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            fileAPI.recycle(fileInfo);
            return new ActResult("recycle success!");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件、文件夹还原
     *
     * @param fileInfo 文件信息
     * @version v1
     */
    @PutMapping("v1/restore")
    public Result restore(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            fileAPI.restore(fileInfo);
            return new ActResult("restore success!");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 回收站列表
     *
     * @param fileInfo 文件夹路径
     * @version v1
     */
    @GetMapping("v1/recycle-list")
    public Result recycleList(@Validated(FileInfo.COMMON.class) FileInfo fileInfo, BindingResult result,HttpServletRequest request) throws ActException {
        try {
            handlerToken(request,fileInfo);
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.recycleList(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private void handlerToken(HttpServletRequest request,FileInfo fileInfo){
        fileInfo.setStorageToken(request.getAttribute("storageToken").toString());

    }

}