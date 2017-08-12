package com.bjike.goddess.version.action.version;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import com.bjike.goddess.version.api.VersionAPI;
import com.bjike.goddess.version.bo.VersionBO;
import com.bjike.goddess.version.dto.VersionDTO;
import com.bjike.goddess.version.to.AttachmentTO;
import com.bjike.goddess.version.to.HelpTO;
import com.bjike.goddess.version.to.VersionTO;
import com.bjike.goddess.version.vo.VersionVO;
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
 * 版本信息
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-04 03:03 ]
 * @Description: [ 版本信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("version")
public class VersionAction extends BaseFileAction {
    @Autowired
    private VersionAPI versionAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 上传附件
     *
     * @param attachmentTO 附件to
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(@Validated(AttachmentTO.UPLOAD.class) AttachmentTO attachmentTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String id = attachmentTO.getId();
            String module = attachmentTO.getModule();
            String folder = attachmentTO.getFolder();
            String path = "/" + module + "/" + folder + "/" + id;
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
     * @param attachmentTO 附件to
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile")
    public Result list(@Validated(AttachmentTO.UPLOAD.class) AttachmentTO attachmentTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String id = attachmentTO.getId();
            String module = attachmentTO.getModule();
            String folder = attachmentTO.getFolder();
            String path = "/" + module + "/" + folder + "/" + id;
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
     * 删除文件
     *
     * @param deleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(AttachmentTO.TestDEL.class) AttachmentTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 列表
     *
     * @param dto 版本信息数据传输
     * @return class VersionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated(VersionDTO.LIST.class) VersionDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<VersionBO> list = versionAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, VersionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 版本信息传输对象
     * @return class VersionVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) VersionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            VersionBO bo = versionAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, VersionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 版本信息id
     * @return class VersionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/version/{id}")
    public Result version(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            VersionBO bo = versionAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, VersionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 版本信息传输对象
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) VersionTO to, BindingResult result) throws ActException {
        try {
            versionAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 版本信息id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            versionAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 版本信息数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(VersionDTO.LIST.class) VersionDTO dto, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(versionAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我要发问
     *
     * @param id     id
     * @param helpTO helpTO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/ask/{id}")
    public Result ask(@PathVariable String id, @Validated(HelpTO.ASK.class) HelpTO helpTO, BindingResult result) throws ActException {
        try {
            versionAPI.ask(id, helpTO);
            return new ActResult("发问成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(versionAPI.findDetail(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 附件文件夹
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/folder")
    public Result folder() throws ActException {
        try {
            List<String> list = new ArrayList<>();
            list.add("思维导图（PDF/jpg)");
            list.add("流程图（PDF）");
            list.add("制度（excel，word，PDF）");
            list.add("操作说明（word，PDF）");
            list.add("问题解答（word，pdf）");
            list.add("需求文档（word、PDF）");
            return ActResult.initialize(list);
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}