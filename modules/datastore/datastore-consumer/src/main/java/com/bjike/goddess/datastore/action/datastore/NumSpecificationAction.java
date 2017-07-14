package com.bjike.goddess.datastore.action.datastore;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.api.NumSpecificationAPI;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.to.DataStoreDeleteFileTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import com.bjike.goddess.datastore.vo.NumSpecificationVO;
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
 * 数据存储编号规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储编号规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("numspecification")
public class NumSpecificationAction extends BaseFileAction{
    @Autowired
    private NumSpecificationAPI numSpecificationAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = numSpecificationAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 数据存储编号规范列表总条数
     *
     * @param numSpecificationDTO 数据存储编号规范dto
     * @des 获取所有数据存储编号规范总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(NumSpecificationDTO numSpecificationDTO) throws ActException {
        try {
            Long count = numSpecificationAPI.countNumSpecification(numSpecificationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个数据存储编号规范
     *
     * @param id
     * @return class NumSpecificationVO
     * @des 获取一个数据存储编号规范
     * @version v1
     */
    @GetMapping("v1/num/{id}")
    public Result num(@PathVariable String id) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(numSpecificationBO, NumSpecificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 数据存储编号规范列表
     *
     * @param numSpecificationDTO 数据存储编号规范dto
     * @return class NumSpecificationVO
     * @des 获取所有数据存储编号规范
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(NumSpecificationDTO numSpecificationDTO, HttpServletRequest request) throws ActException {
        try {
            List<NumSpecificationVO> numSpecificationVOS = BeanTransform.copyProperties
                    (numSpecificationAPI.findListNumSpecification(numSpecificationDTO), NumSpecificationVO.class, request);
            return ActResult.initialize(numSpecificationVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationVO
     * @des 添加数据存储编号规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(NumSpecificationTO numSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.insertNumSpecification(numSpecificationTO);
            return ActResult.initialize(numSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationVO
     * @des 编辑数据存储编号规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(NumSpecificationTO numSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            NumSpecificationBO numSpecificationBO = numSpecificationAPI.editNumSpecification(numSpecificationTO);
            return ActResult.initialize(numSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除数据存储编号规范
     *
     * @param id 用户id
     * @des 根据用户id删除数据存储编号规范记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            numSpecificationAPI.removeNumSpecification(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @des 数据存储编号规范
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
     * @param id 数据存储编号规范id
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
     * @param dataStoreDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DataStoreDeleteFileTO.TestDEL.class) DataStoreDeleteFileTO dataStoreDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != dataStoreDeleteFileTO.getPaths() && dataStoreDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),dataStoreDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}