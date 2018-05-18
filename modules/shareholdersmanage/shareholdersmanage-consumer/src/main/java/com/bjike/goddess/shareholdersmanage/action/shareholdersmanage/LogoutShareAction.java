package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.LogoutShareAPI;
import com.bjike.goddess.shareholdersmanage.bo.LogoutShareBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutShareDTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutShareTO;
import com.bjike.goddess.shareholdersmanage.to.ShareOpenDeleteFileTO;
import com.bjike.goddess.shareholdersmanage.vo.LogoutShareLinkDateVO;
import com.bjike.goddess.shareholdersmanage.vo.LogoutShareVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 注销股东
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("logoutshare")
public class LogoutShareAction extends BaseFileAction {
    @Autowired
    private LogoutShareAPI logoutShareAPI;
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
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = logoutShareAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param logoutShareDTO 注销股东dto
     * @des 获取所有注销股东总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LogoutShareDTO logoutShareDTO) throws ActException {
        try {
            Long count = logoutShareAPI.countLogout(logoutShareDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个注销股东
     *
     * @param id 注销股东id
     * @return class LogoutShareVO
     * @des 根据id获取注销股东
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            LogoutShareVO logoutShareVO = BeanTransform.copyProperties(
                    logoutShareAPI.getOne(id), LogoutShareVO.class);
            return ActResult.initialize(logoutShareVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 注销股东列表
     *
     * @param logoutShareDTO 注销股东dto
     * @return class LogoutShareVO
     * @des 获取所有注销股东
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findLogout(LogoutShareDTO logoutShareDTO, HttpServletRequest request) throws ActException {
        try {
            List<LogoutShareVO> logoutShareVOList = BeanTransform.copyProperties(
                    logoutShareAPI.findList(logoutShareDTO), LogoutShareVO.class, request);
            return ActResult.initialize(logoutShareVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加注销股东
     *
     * @param logoutShareTO 注销股东to
     * @return class LogoutShareVO
     * @des 添加注销股东
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addLogoutShare(@Validated({ADD.class}) LogoutShareTO logoutShareTO, BindingResult result) throws ActException {
        try {
            LogoutShareBO logoutShareBO = logoutShareAPI.save(logoutShareTO);
            return ActResult.initialize(BeanTransform.copyProperties(logoutShareBO, LogoutShareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑注销股东
     *
     * @param logoutShareTO 注销股东数据bo
     * @return class LogoutShareVO
     * @des 编辑注销股东
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editLogoutShare(@Validated({EDIT.class}) LogoutShareTO logoutShareTO, BindingResult result) throws ActException {
        try {
            LogoutShareBO logoutShareBO = logoutShareAPI.edit(logoutShareTO);
            return ActResult.initialize(BeanTransform.copyProperties(logoutShareBO, LogoutShareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除注销股东
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteLogoutShare(@PathVariable String id) throws ActException {
        try {
            logoutShareAPI.delete(id);
            return new ActResult("delete success!");
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
    public Result uploadFile(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
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
    public Result list(@PathVariable String id, javax.servlet.http.HttpServletRequest request) throws ActException {
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
    public Result download(@RequestParam String path, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) throws ActException {
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(ShareOpenDeleteFileTO.TestDEL.class) ShareOpenDeleteFileTO siginManageDeleteFileTO, javax.servlet.http.HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 根据股东名链接数据
     *
     * @param logoutShareName 注销股东名称
     * @return class LogoutShareLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam String logoutShareName) throws ActException {
        try {
            LogoutShareLinkDateVO logoutShareLinkDateVO = BeanTransform.copyProperties(
                    logoutShareAPI.linkDateByName(logoutShareName), LogoutShareLinkDateVO.class);
            return ActResult.initialize(logoutShareLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}