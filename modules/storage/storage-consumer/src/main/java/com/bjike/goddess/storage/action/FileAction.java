package com.bjike.goddess.storage.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
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
@RestController
@RequestMapping("storage/file")
public class FileAction {
    @Autowired
    private FileAPI fileAPI;

    /**
     * 文件列表
     *
     * @param path
     * @vsersion v1
     */
    @GetMapping("v1/list")
    public Result list(String path) throws SerException {
        List<File> files = fileAPI.list(path);
        return ActResult.initialize(files);
    }

    /**
     * 文件上传
     *
     * @param path 上传路径
     * @vsersion v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request, String path) throws ActException {
        try {
            List<MultipartFile> mfiles = FileUtils.getMFiles(request); // 取得request中的所有文件
            MultipartFile file = mfiles.get(0);
            java.io.File _file = null;
            if (null != file && StringUtils.isNoneBlank(path)) {
                String myFileName = file.getOriginalFilename(); // 取得当前上传文件的文件名称
                if (StringUtils.isNotBlank(myFileName)) {
                    java.io.File localFile = new java.io.File(path);
                    if (!localFile.exists()) {// 文件夹不存在则创建
                        localFile.mkdirs();
                    }

                    localFile = new java.io.File(path+"/" + myFileName);
                    try {
                        file.transferTo(localFile);
                        _file = localFile;
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            fileAPI.upload(Arrays.asList(_file), null);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 文件夹创建
     *
     * @param path
     * @vsersion v1
     */
    @PostMapping("v1/mkDir")
    public Result mkDir(String path) throws SerException {
        fileAPI.mkDir(path);
        return new ActResult("mkDir success");
    }

    /**
     * 删除文件
     *
     * @param path
     * @vsersion v1
     */
    @DeleteMapping("v1/delFile")
    public Result delFile(String path) throws SerException {
        fileAPI.delFile(path);
        return new ActResult("delFile success");
    }

    /**
     * 删除文件夹
     *
     * @param path
     * @vsersion v1
     */
    @DeleteMapping("v1/delFolder")
    public Result delFolder(String path) throws SerException {
        fileAPI.delFolder(path);
        return new ActResult("delFolder success");
    }

    /**
     * 重命名
     *
     * @param path
     * @param newName
     * @vsersion v1
     */
    @PutMapping("v1/rename")
    public Result rename(String path, String newName) throws SerException {
        fileAPI.rename(path, newName);
        return new ActResult("rename success");
    }

    /**
     * 下载
     *
     * @param path
     * @vsersion v1
     */
    @GetMapping("v1/download")
    public Result download(String path) throws SerException {
        fileAPI.download(path);
        return new ActResult("download success");
    }

}