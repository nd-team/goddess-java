package com.bjike.goddess.common.consumer.file;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 上传文件的action继承该类
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-18 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class BaseFileAction {
    /**
     * 上传文件调用该方法获得文件流
     * 需要单独处理path
     *
     * @param request
     * @param path
     * @return
     * @throws SerException
     */
    public List<InputStream> getInputStreams(HttpServletRequest request, String path) throws SerException {
        return initInputStreams(request, path);
    }

    /**
     * 上传文件调用该方法获得文件流
     *
     * @param request
     * @return
     * @throws SerException
     */
    public List<InputStream> getInputStreams(HttpServletRequest request) throws SerException {
        String path = request.getParameter("path");
        return initInputStreams(request, path);
    }

    private List<InputStream> initInputStreams(HttpServletRequest request, String path) throws SerException {
        List<MultipartFile> multipartFiles = getMultipartFile(request);
        List<InputStream> inputStreams = null;
        String token = request.getParameter("storageToken");
        if (null != multipartFiles) {
            inputStreams = new ArrayList<>(multipartFiles.size() * 2);
            try {
                for (MultipartFile mf : multipartFiles) {
                    String fileInfo = "{\"fileName\":\"%s\",\"path\":\"%s\",\"storageToken\":\"%s\"}";
                    fileInfo = String.format(fileInfo, mf.getOriginalFilename(), path, token);
                    InputStream is_fileInfo = new ByteArrayInputStream(JSON.toJSONBytes(fileInfo));
                    InputStream file = mf.getInputStream();
                    inputStreams.add(is_fileInfo);
                    inputStreams.add(file);
                }
            } catch (IOException e) {
                throw new SerException(e.getMessage());
            }

        }
        return inputStreams;
    }

    /**
     * 通过request获取上传文件
     *
     * @param request
     * @return
     * @throws SerException
     */
    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {

        if (null != request && !isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");

    }

    /**
     * 上传是否合理
     *
     * @param request
     * @return
     */
    private boolean isMultipartContent(HttpServletRequest request) {
        if (!"post".equals(request.getMethod().toLowerCase())) {
            return false;
        }

        String contentType = request.getContentType();  //获取Content-Type
        if ((contentType != null) && (contentType.toLowerCase().startsWith("multipart/"))) {
            return true;
        } else {
            return false;
        }
    }


}
