package com.bjike.goddess.storage.action.file;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.bo.FileBO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
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
     * @param path 文件夹路径
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@RequestParam String path) throws ActException {
        try {
            List<FileBO> files = fileAPI.list(path);
            fileAPI.list(path);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件上传
     *
     * @param path 上传路径
     * @param request multipart、form-data提交的文件
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request, @RequestParam String path) throws ActException {
        try {
            List<MultipartFile> multipartFiles = this.getMultipartFile(request);
            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
                fileAPI.upload(bytes, multipartFile.getOriginalFilename(), path);
            }

            return new ActResult("upload success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }


    /**
     * 文件是否存在
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/exists")
    public Result exists(@RequestParam String path) throws ActException {
        try {
            String filename = StringUtils.substringAfterLast(path, "/");
            if (fileAPI.existsFile(path)) {
                return new ActResult(filename + "is exists!");
            }

        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
        return new ActResult("not exists!");

    }


    /**
     * 文件夹创建
     *
     * @param path 文件夹路径
     * @param dir  新的目录
     * @version v1
     */
    @PostMapping("v1/mkDir")
    public Result mkDir(@RequestParam String path, @RequestParam String dir) throws SerException {
        fileAPI.mkDir(path, dir);
        return new ActResult("mkDir success");
    }

    /**
     * 删除文件、文件夹
     *
     * @param path 文件、文件夹路径
     * @version v1
     */
    @DeleteMapping("v1/delFile")
    public Result delFile(@RequestParam String path) throws SerException {
        fileAPI.delFile(path);
        return new ActResult("delFile success");
    }


    /**
     * 重命名文件、文件夹
     *
     * @param path    文件路径
     * @param oldName 旧文件名
     * @param newName 新文件名
     * @version v1
     */
    @PutMapping("v1/rename")
    public Result rename(@RequestParam String path, @RequestParam String oldName, @RequestParam String newName) throws SerException {
        fileAPI.rename(path, oldName, newName);
        return new ActResult("rename success");
    }

    /**
     * 文件下载
     *
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletResponse response) throws ActException {
        try {
            String filename = StringUtils.substringAfterLast(path, "/");
            byte[] buffer = fileAPI.download(path);
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            response.addHeader("Content-Length", "" + buffer.length);
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);// 输出文件
            os.flush();
            os.close();
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }


    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {
        if (null != request && !ServletFileUpload.isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");
    }


}