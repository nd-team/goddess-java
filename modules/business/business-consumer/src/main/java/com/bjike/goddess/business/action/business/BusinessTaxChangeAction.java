package com.bjike.goddess.business.action.business;

import com.bjike.goddess.business.api.BusinessTaxChangeAPI;
import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;
import com.bjike.goddess.business.to.BusinessDeleteFileTO;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.business.vo.BusinessTaxChangeVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 工商税务变更
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businesstaxchange")
public class BusinessTaxChangeAction extends BaseFileAction{
    @Autowired
    private BusinessTaxChangeAPI businessTaxChangeAPI;
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

            Boolean isHasPermission = businessTaxChangeAPI.guidePermission(guidePermissionTO);
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
     * 工商税务变更列表总条数
     *
     * @param businessTaxChangeDTO 工商税务变更dto
     * @des 获取所有工商税务变更总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(BusinessTaxChangeDTO businessTaxChangeDTO) throws ActException {
        try {
            Long count = businessTaxChangeAPI.countBusinessTaxChange(businessTaxChangeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工商税务变更
     *
     * @param id
     * @return class BusinessTaxChangeVO
     * @des 获取一个工商税务变更
     * @version v1
     */
    @GetMapping("v1/change/{id}")
    public Result change(@PathVariable String id) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(businessTaxChangeBO, BusinessTaxChangeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工商税务变更列表
     *
     * @param businessTaxChangeDTO 工商税务变更dto
     * @return class BusinessTaxChangeVO
     * @des 获取所有工商税务变更
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(BusinessTaxChangeDTO businessTaxChangeDTO, HttpServletRequest request) throws ActException {
        try {
            List<BusinessTaxChangeVO> businessTaxChangeVOS = BeanTransform.copyProperties
                    (businessTaxChangeAPI.findListBusinessTaxChange(businessTaxChangeDTO), BusinessTaxChangeVO.class, request);
            return ActResult.initialize(businessTaxChangeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeVO
     * @des 添加工商税务变更
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) BusinessTaxChangeTO businessTaxChangeTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.insertBusinessTaxChange(businessTaxChangeTO);
            return ActResult.initialize(businessTaxChangeBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeVO
     * @des 编辑工商税务变更
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) BusinessTaxChangeTO businessTaxChangeTO, BindingResult bindingResult) throws ActException {
        try {
            BusinessTaxChangeBO businessTaxChangeBO = businessTaxChangeAPI.editBusinessTaxChange(businessTaxChangeTO);
            return ActResult.initialize(businessTaxChangeBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工商税务变更
     *
     * @param id 用户id
     * @des 根据用户id删除工商税务变更记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            businessTaxChangeAPI.removeBusinessTaxChange(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 工商税务变更
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
     * @param id 工商税务变更id
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
     * @param businessDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(BusinessDeleteFileTO.TestDEL.class) BusinessDeleteFileTO businessDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != businessDeleteFileTO.getPaths() && businessDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),businessDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}