package com.bjike.goddess.devicerepair.action.devicerepair;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.devicerepair.api.DeviceRepairAPI;
import com.bjike.goddess.devicerepair.bo.DeviceRepairBO;
import com.bjike.goddess.devicerepair.dto.DeviceRepairDTO;
import com.bjike.goddess.devicerepair.excel.SonPermissionObject;
import com.bjike.goddess.devicerepair.to.*;
import com.bjike.goddess.devicerepair.type.AuditState;
import com.bjike.goddess.devicerepair.vo.DeviceRepairVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 设备维修
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-03 02:59 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("devicerepair")
public class DeviceRepairAct extends BaseFileAction {

    @Autowired
    private DeviceRepairAPI deviceRepairAPI;

    @Autowired
    private FileAPI fileAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = deviceRepairAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = deviceRepairAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询设备维修
     *
     * @param id 设备维修唯一标识
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/devicerepair/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DeviceRepairBO bo = deviceRepairAPI.findById(id);
            DeviceRepairVO vo = BeanTransform.copyProperties(bo, DeviceRepairVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 设备维修dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated DeviceRepairDTO dto, BindingResult result) throws ActException {
        try {
            Long count = deviceRepairAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 设备维修dto
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated DeviceRepairDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<DeviceRepairBO> boList = deviceRepairAPI.list(dto);
            List<DeviceRepairVO> voList = BeanTransform.copyProperties(boList, DeviceRepairVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加设备维修
     *
     * @param to 设备维修to信息
     * @return class DeviceRepairVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) DeviceRepairTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DeviceRepairBO bo = deviceRepairAPI.save(to);
            DeviceRepairVO vo = BeanTransform.copyProperties(bo, DeviceRepairVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除设备维修
     *
     * @param id 设备维修唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            deviceRepairAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑设备维修
     *
     * @param to 设备维修to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) DeviceRepairTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块审核
     *
     * @param to 设备维修to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/welfareAudit")
    public Result welfareAudit(@Validated(WelfareAuditTO.WelfareAudit.class) WelfareAuditTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.welfareAudit(to);
            return new ActResult("welfareAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目经理审核
     *
     * @param id           设备维修唯一标识
     * @param pmAuditState 项目经理审核状态
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/pmAudit/{id}")
    public Result pmAudit(@PathVariable String id, @RequestParam(value = "pmAuditState") AuditState pmAuditState) throws ActException {
        try {
            deviceRepairAPI.pmAudit(id, pmAuditState);
            return new ActResult("pmAudit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设备报废
     *
     * @param id          设备维修唯一标识
     * @param deviceIssue 设备出现的问题
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/deviceScrap/{id}")
    public Result deviceScrap(@PathVariable String id, @RequestParam(value = "deviceIssue") String deviceIssue) throws ActException {
        try {
            deviceRepairAPI.deviceScrap(id, deviceIssue);
            return new ActResult("deviceScrap success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 取回设备
     *
     * @param to 设备维修to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/fetchDevice")
    public Result fetchDevice(@Validated(FetchDeviceTO.FetchDevice.class) FetchDeviceTO to, BindingResult result) throws ActException {
        try {
            deviceRepairAPI.fetchDevice(to);
            return new ActResult("fetchDevice success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * @param devicerepairDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DevicerepairDeleteFileTO.TestDEL.class) DevicerepairDeleteFileTO devicerepairDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != devicerepairDeleteFileTO.getPaths() && devicerepairDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), devicerepairDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }
}