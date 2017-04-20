package com.bjike.goddess.archive.action.archive;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.to.StaffRecordsUploadTO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.storage.api.FileAPI;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工档案
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffrecords")
public class StaffRecordsAct {

    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 上传数据
     *
     * @return class Result
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            staffRecordsAPI.upload();
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 上传请求
     * @param to      员工档案附件上传
     * @return class Result
     * @version v1
     */
    @PostMapping("v1/uploadEnclosure")
    public Result uploadEnclosure(HttpServletRequest request, @Validated(ADD.class) StaffRecordsUploadTO to, BindingResult result) throws ActException {
        try {
            Object o = RpcContext.getContext().getAttachment("storageToken");

            String path = "/" + to.getUsername() + "/" + to.getEnclosure();
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> multipartFiles = multiRequest.getFiles("file");
            Map<String, byte[]> map = new HashMap<>(multipartFiles.size());

            for (MultipartFile multipartFile : multipartFiles) {
                byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
                map.put(multipartFile.getOriginalFilename(), bytes);
            }
            fileAPI.upload(map, path);
            return new ActResult("上传成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

}