package com.bjike.goddess.storage.utils;

import com.bjike.goddess.common.api.exception.SerException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-25 16:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileUtils {

    public static List<MultipartFile> getMFiles(HttpServletRequest request) throws SerException {
        if (null != request && !ServletFileUpload.isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("file");
    }
}
