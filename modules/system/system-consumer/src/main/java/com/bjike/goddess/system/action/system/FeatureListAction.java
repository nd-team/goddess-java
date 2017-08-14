package com.bjike.goddess.system.action.system;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.system.api.FeatureListAPI;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.entity.Question;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.to.QuestionTO;
import com.bjike.goddess.system.to.SystemTO;
import com.bjike.goddess.system.vo.FeatureListVO;
import com.bjike.goddess.system.vo.FieldDockVO;
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
 * 功能列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("featurelist")
public class FeatureListAction extends BaseFileAction{
    @Autowired
    private FeatureListAPI featureListAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
    @Autowired
    private ModuleTypeAPI moduleTypeAPI;
    @Autowired
    private FileAPI fileAPI;
    /**
     * 功能列表总条数
     *
     * @param dto 功能列表记录dto
     * @des 获取所有功能列表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FeatureListDTO dto) throws ActException {
        try {
            Long count = featureListAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个功能列表
     *
     * @param id
     * @return class FeatureListVO
     * @des 获取一个功能列表
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO, FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 功能列表
     *
     * @param dto 功能列表记录dto
     * @return class FeatureListVO
     * @des 获取所有功能列表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FeatureListDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FeatureListVO> featureListVOS = BeanTransform.copyProperties(
                    featureListAPI.list(dto), FeatureListVO.class, request);
            return ActResult.initialize(featureListVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加功能列表
     *
     * @param to 功能列表to
     * @return class FeatureListVO
     * @des 添加功能列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(FeatureListTO to, BindingResult bindingResult) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO,FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑功能列表
     *
     * @param to 功能列表数据to
     * @return class FeatureListVO
     * @des 编辑功能列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(FeatureListTO to, BindingResult bindingResult) throws ActException {
        try {
            FeatureListBO featureListBO = featureListAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(featureListBO,FeatureListVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除功能列表
     *
     * @param id 用户id
     * @des 根据用户id删除功能列表
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            featureListAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 我要发问
     *
     * @param id     id
     * @param questionTO questionTO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/ask/{id}")
    public Result ask(@PathVariable String id, QuestionTO questionTO, BindingResult result) throws ActException {
        try {
            featureListAPI.ask(id, questionTO);
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
            return ActResult.initialize(featureListAPI.findDetail(id));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询未冻结部门项目组详细信息
     *
     * @return class DepartmentDetailVO
     * @version v1
     */
    @GetMapping("v1/department")
    public Result department(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(departmentDetailAPI.findStatus(), DepartmentDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询正常状态的模块类型数据
     *
     * @return class ModuleTypeVO
     * @version v1
     */
    @GetMapping("v1/module")
    public Result module(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(moduleTypeAPI.findByStatus(Status.THAW), ModuleTypeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 上传附件
     *
     * @param systemTO 附件to
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile")
    public Result uploadFile(@Validated(SystemTO.UPLOAD.class) SystemTO systemTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            String id = systemTO.getId();
            String folder = systemTO.getFolder();
            String path =  "/" + folder + "/" + id;
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
     * @param systemTO 附件to
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile")
    public Result list(@Validated(SystemTO.UPLOAD.class) SystemTO systemTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String id = systemTO.getId();
            String folder = systemTO.getFolder();
            String path =  "/" + folder + "/" + id;
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
    public Result delFile(@Validated(SystemTO.TestDEL.class) SystemTO deleteFileTO, HttpServletRequest request) throws SerException {
        if (null != deleteFileTO.getPaths() && deleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), deleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
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



}