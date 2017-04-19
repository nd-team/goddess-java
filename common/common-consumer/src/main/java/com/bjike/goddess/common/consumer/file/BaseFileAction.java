package com.bjike.goddess.common.consumer.file;

import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
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
     * 通过request获取上传文件
     *
     * @param request
     * @return
     * @throws SerException
     */
    public List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {

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
    public boolean isMultipartContent(HttpServletRequest request) {
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
