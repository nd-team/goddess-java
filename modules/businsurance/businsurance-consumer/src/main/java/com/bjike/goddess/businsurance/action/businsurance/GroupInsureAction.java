package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.GroupInsureAPI;
import com.bjike.goddess.businsurance.bo.GroupInsureBO;
import com.bjike.goddess.businsurance.dto.GroupInsureDTO;
import com.bjike.goddess.businsurance.to.GroupInsureTO;
import com.bjike.goddess.businsurance.to.SiginManageDeleteFileTO;
import com.bjike.goddess.businsurance.vo.GroupInsureVO;
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
import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;

/**
 * 团体意外险信息管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 10:02 ]
 * @Description: [ 团体意外险信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("groupinsure")
public class GroupInsureAction extends BaseFileAction{

    @Autowired
    private GroupInsureAPI groupInsureAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     *  团体意外险列表总条数
     *
     * @param groupInsureDTO  团体意外险信息dto
     * @des 获取所有团体意外险信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(GroupInsureDTO groupInsureDTO) throws ActException {
        try {
            Long count = groupInsureAPI.countGroupInsure(groupInsureDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 团体意外险列表
     *
     * @param groupInsureDTO 团体意外险信息dto
     * @des 获取所有团体意外险信息
     * @return  class GroupInsureVO
     * @version v1
     */
    @GetMapping("v1/listGroupInsure")
    public Result findList(GroupInsureDTO groupInsureDTO, BindingResult bindingResult) throws ActException {
        try {
            List<GroupInsureVO> groupInsureVOList = BeanTransform.copyProperties(
                    groupInsureAPI.listGroupInsure(groupInsureDTO), GroupInsureVO.class, true);
            return ActResult.initialize(groupInsureVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加团体意外险
     *
     * @param groupInsureTO 团体意外险基本信息数据to
     * @des 添加团体意外险
     * @return  class GroupInsureVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(GroupInsureTO.TestAdd.class) GroupInsureTO groupInsureTO, BindingResult bindingResult) throws ActException {
        try {
            GroupInsureBO groupInsureBO1 = groupInsureAPI.addGroupInsure(groupInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupInsureBO1,GroupInsureVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑团体意外险
     *
     * @param groupInsureTO 团体意外险基本信息数据bo
     * @des 编辑团体意外险
     * @return  class GroupInsureVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(GroupInsureTO.TestAdd.class)  GroupInsureTO groupInsureTO) throws ActException {
        try {
            GroupInsureBO groupInsureBO1 = groupInsureAPI.editGroupInsure(groupInsureTO);
            return ActResult.initialize(BeanTransform.copyProperties(groupInsureBO1,GroupInsureVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除团体意外险信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            groupInsureAPI.deleteGroupInsure(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 一个团体意外险
     *
     * @param id id
     * @des 根据id查看详细
     * @return  class GroupInsureVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id ) throws ActException {
        try {
            GroupInsureBO busInsuranceBO1 = groupInsureAPI.getGroupInsure(id);
            return ActResult.initialize(BeanTransform.copyProperties(busInsuranceBO1,GroupInsureVO.class,true));
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
            String path = "/businsurance/groupInsure/" + id;
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
            String path = "/businsurance/groupInsure/" + id;
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
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class ) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

}