package com.bjike.goddess.dimission.action.dimission;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dimission.api.WorkHandoverAPI;
import com.bjike.goddess.dimission.dto.WorkHandoverDTO;
import com.bjike.goddess.dimission.to.DeleteFileTO;
import com.bjike.goddess.dimission.to.GuidePermissionTO;
import com.bjike.goddess.dimission.to.HandoverSuccessTO;
import com.bjike.goddess.dimission.to.WorkHandoverTO;
import com.bjike.goddess.dimission.vo.WorkHandoverVO;
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
 * 工作交接
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-17 02:24 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workhandover")
public class WorkHandoverAct extends BaseFileAction {

    @Autowired
    private WorkHandoverAPI workHandoverAPI;
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

            Boolean isHasPermission = workHandoverAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 工作交接传输对象
     * @return class WorkHandoverVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) WorkHandoverTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.save(to), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 工作交接传输对象
     * @return class WorkHandoverVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) WorkHandoverTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.update(to), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 工作交接数据id
     * @return class WorkHandoverVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.delete(id), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交接人确认
     *
     * @param to 工作交接确认信息传输对象
     * @return class WorkHandoverVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/success/{id}")
    public Result success(@Validated(EDIT.class) HandoverSuccessTO to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.FALSE);
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.success(to), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 福利模块负责人确认
     *
     * @param to 工作交接确认信息传输对象
     * @return class WorkHandoverVO
     * @version v1
     */
    @PutMapping("v1/authority/{id}")
    public Result authority(@Validated(EDIT.class) HandoverSuccessTO to, BindingResult result) throws ActException {
        try {
            to.setAuthority(Boolean.TRUE);
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.success(to), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 工作交接数据传输对象
     * @return class WorkHandoverVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(WorkHandoverDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.maps(dto), WorkHandoverVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取工作交接数据
     *
     * @param id 工作交接数据id
     * @return class WorkHandoverVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(workHandoverAPI.getById(id), WorkHandoverVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(workHandoverAPI.getTotal());
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DeleteFileTO.TestDEL.class) DeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}