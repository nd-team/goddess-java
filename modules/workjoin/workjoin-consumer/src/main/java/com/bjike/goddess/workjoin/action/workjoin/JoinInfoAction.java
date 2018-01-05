package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import com.bjike.goddess.datastore.vo.NumSpecificationVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.workjoin.api.JoinInfoAPI;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import com.bjike.goddess.workjoin.to.WorkJoinDeleteFileTO;
import com.bjike.goddess.workjoin.vo.JoinInfoVO;
import com.fasterxml.jackson.databind.ser.Serializers;
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
 * 交接资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:34 ]
 * @Description: [ 交接资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("joininfo")
public class JoinInfoAction extends BaseFileAction{
    @Autowired
    private JoinInfoAPI joinInfoAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = joinInfoAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 交接资料列表总条数
     *
     * @param joinInfoDTO 交接资料dto
     * @des 获取所有交接资料总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(JoinInfoDTO joinInfoDTO) throws ActException {
        try {
            Long count = joinInfoAPI.countJoinInfo(joinInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交接资料
     *
     * @param id 交接资料id
     * @return class JoinInfoVO
     * @des 获取一个交接资料
     * @version v1
     */
    @GetMapping("v1/info/{id}")
    public Result info(@PathVariable String id) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(joinInfoBO, JoinInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 交接资料列表
     *
     * @param joinInfoDTO 交接资料dto
     * @return class JoinInfoVO
     * @des 获取所有交接资料
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(JoinInfoDTO joinInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<JoinInfoVO> joinInfoVOS = BeanTransform.copyProperties
                    (joinInfoAPI.findListJoinInfo(joinInfoDTO), JoinInfoVO.class, request);
            return ActResult.initialize(joinInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoVO
     * @des 添加交接资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) JoinInfoTO joinInfoTO, BindingResult bindingResult) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.insertJoinInfo(joinInfoTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑交接资料
     *
     * @param joinInfoTO 交接资料数据to
     * @return class JoinInfoVO
     * @des 编辑交接资料
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) JoinInfoTO joinInfoTO, BindingResult bindingResult) throws ActException {
        try {
            JoinInfoBO joinInfoBO = joinInfoAPI.editJoinInfo(joinInfoTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除交接资料
     *
     * @param id 用户id
     * @des 根据用户id删除交接资料记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            joinInfoAPI.removeJoinInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 招标信息
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
     * @param id 招标信息id
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
     * @param workJoinDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(WorkJoinDeleteFileTO.TestDEL.class) WorkJoinDeleteFileTO workJoinDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != workJoinDeleteFileTO.getPaths() && workJoinDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),workJoinDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }


    /**
     * 获取制度文件夹编号和经验总结编号
     * @return class NumSpecificationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/specification")
    public Result findSpecification() throws ActException{
        try {
            List<NumSpecificationBO> boList = joinInfoAPI.findNumSepecification();
            List<NumSpecificationVO> voList = BeanTransform.copyProperties(boList,NumSpecificationVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



}