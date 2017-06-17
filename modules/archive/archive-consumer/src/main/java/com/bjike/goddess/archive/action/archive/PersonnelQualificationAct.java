package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.PersonnelQualificationAPI;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.archive.vo.PersonnelQualificationVO;
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
 * 人员资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("personnelqualification")
public class PersonnelQualificationAct extends BaseFileAction {

    @Autowired
    private PersonnelQualificationAPI personnelQualificationAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 保存
     *
     * @param to 人员资质传输对象
     * @return class PersonnelQualificationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PersonnelQualificationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelQualificationAPI.save(to), PersonnelQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 人员资质传输对象
     * @return class PersonnelQualificationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PersonnelQualificationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelQualificationAPI.update(to), PersonnelQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 人员资质数据id
     * @return class PersonnelQualificationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelQualificationAPI.delete(id), PersonnelQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 人员资质数据传输对象
     * @return class PersonnelQualificationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PersonnelQualificationDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelQualificationAPI.maps(dto), PersonnelQualificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @param id 人员资质数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/personnelqualification/" + id;
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
     * @param id 人员资质数据id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/personnelqualification/" + id;
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
     * @param paths 多文件信息路径
     * @version v1
     */
    @PostMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), paths);
            return new ActResult("delFile success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取人员资质数据
     *
     * @param id 人员资质数据id
     * @return class PersonnelQualificationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(personnelQualificationAPI.getById(id), PersonnelQualificationVO.class, request));
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
            return ActResult.initialize(personnelQualificationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}