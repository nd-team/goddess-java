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
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.vo.MaterialInStockVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.workjoin.api.DeviceJoinAPI;
import com.bjike.goddess.workjoin.bo.DeviceJoinBO;
import com.bjike.goddess.workjoin.dto.DeviceJoinDTO;
import com.bjike.goddess.workjoin.to.DeviceJoinTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.WorkJoinDeleteFileTO;
import com.bjike.goddess.workjoin.vo.DeviceJoinVO;
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
 * 设备交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:57 ]
 * @Description: [ 设备交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("devicejoin")
public class DeviceJoinAction extends BaseFileAction{
    @Autowired
    private DeviceJoinAPI deviceJoinAPI;
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

            Boolean isHasPermission = deviceJoinAPI.guidePermission(guidePermissionTO);
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
     * 设备交接列表总条数
     *
     * @param deviceJoinDTO 设备交接dto
     * @des 获取所有设备交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DeviceJoinDTO deviceJoinDTO) throws ActException {
        try {
            Long count = deviceJoinAPI.countDeviceJoin(deviceJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个设备交接
     *
     * @param id 设备交接id
     * @return class DeviceJoinVO
     * @des 获取一个设备交接
     * @version v1
     */
    @GetMapping("v1/device/{id}")
    public Result device(@PathVariable String id) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(deviceJoinBO, DeviceJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 设备交接列表
     *
     * @param deviceJoinDTO 设备交接dto
     * @return class DeviceJoinVO
     * @des 获取所有设备交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DeviceJoinDTO deviceJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<DeviceJoinVO> deviceJoinVOS = BeanTransform.copyProperties
                    (deviceJoinAPI.findListDeviceJoin(deviceJoinDTO), DeviceJoinVO.class, request);
            return ActResult.initialize(deviceJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinVO
     * @des 添加设备交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) DeviceJoinTO deviceJoinTO, BindingResult bindingResult) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.insertDeviceJoin(deviceJoinTO);
            return ActResult.initialize(deviceJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑设备交接
     *
     * @param deviceJoinTO 设备交接数据to
     * @return class DeviceJoinVO
     * @des 编辑设备交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DeviceJoinTO deviceJoinTO, BindingResult bindingResult) throws ActException {
        try {
            DeviceJoinBO deviceJoinBO = deviceJoinAPI.editDeviceJoin(deviceJoinTO);
            return ActResult.initialize(deviceJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除设备交接
     *
     * @param id 用户id
     * @des 根据用户id删除设备交接记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            deviceJoinAPI.removeDeviceJoin(id);
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
     * 获取设备编号和设备名称
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/material")
    public Result findMaterial() throws ActException{
        try {
            List<MaterialInStockBO> boList = deviceJoinAPI.findMaterial();
            List<MaterialInStockVO> voList = BeanTransform.copyProperties(boList,MaterialInStockVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


}