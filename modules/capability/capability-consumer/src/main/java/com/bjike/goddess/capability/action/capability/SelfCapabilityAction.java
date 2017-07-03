package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.SelfCapabilityAPI;
import com.bjike.goddess.capability.bo.SelfCapabilityBO;
import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.to.CapabilityDeleteFileTO;
import com.bjike.goddess.capability.to.SelfCapabilityTO;
import com.bjike.goddess.capability.vo.SelfCapabilityVO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 个人能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:22 ]
 * @Description: [ 个人能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("selfcapability")
public class SelfCapabilityAction extends BaseFileAction{
    @Autowired
    private SelfCapabilityAPI selfCapabilityAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 个人能力总条数
     *
     * @param selfCapabilityDTO
     * @des 获取所有个人能力总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SelfCapabilityDTO selfCapabilityDTO) throws ActException {
        try {
            Long count = selfCapabilityAPI.counts(selfCapabilityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个个人能力
     *
     * @param id 列表id
     * @param request  前端过滤参数
     * @des 获取一个个人能力
     * @return class SelfCapabilityVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id,HttpServletRequest request) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO = selfCapabilityAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO ,SelfCapabilityVO.class , request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人能力列表
     *
     * @param selfCapabilityDTO 个人能力信息dto
     * @param request  前端过滤参数
     * @return class SelfCapabilityVO
     * @des 获取所有个人能力信息
     * @version v1
     */
    @GetMapping("v1/listSelf")
    public Result findList(SelfCapabilityDTO selfCapabilityDTO,HttpServletRequest request) throws ActException {
        try {
            List<SelfCapabilityVO> selfCapabilityVOList = BeanTransform.copyProperties(
                    selfCapabilityAPI.listSelfCapability(selfCapabilityDTO), SelfCapabilityVO.class, request);
            return ActResult.initialize(selfCapabilityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人能力
     *
     * @param selfCapabilityTO 个人能力基本信息数据to
     * @return class SelfCapabilityVO
     * @des 添加个人能力, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSelfCapability(@Validated(SelfCapabilityTO.TestAdd.class) SelfCapabilityTO selfCapabilityTO) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO1 = selfCapabilityAPI.addSelfCapability(selfCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO1, SelfCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 编辑个人能力
     *
     * @param selfCapabilityTO 个人能力基本信息数据bo
     * @return class SelfCapabilityVO
     * @des 添加个人能力, 公司名称不能为空
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSelfCapability(@Validated(SelfCapabilityTO.TestAdd.class)  SelfCapabilityTO selfCapabilityTO) throws ActException {
        try {
            SelfCapabilityBO selfCapabilityBO1 = selfCapabilityAPI.editSelfCapability(selfCapabilityTO);
            return ActResult.initialize(BeanTransform.copyProperties(selfCapabilityBO1, SelfCapabilityVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除个人能力信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSelfCapability(@PathVariable String id) throws ActException {
        try {
            selfCapabilityAPI.deleteSelfCapability(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @param selfCapabilityTO 个人能力基本信息数据to
     * @return class SelfCapabilityVO
     * @des 导入个人能力, 名称不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcelCCapability(@Validated SelfCapabilityTO selfCapabilityTO) throws ActException {
        return null;
    }


    /**
     * 导出
     *
     * @param name 名称
     * @return class SelfCapabilityVO
     * @des 导出个人能力, 名称可以为空
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcelCCapability(String name,HttpServletResponse response) throws ActException {
        try {
            String fileName = "个人能力展示.xlsx";
            super.writeOutFile(response, selfCapabilityAPI.exportExcel(name), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有姓名
     *
     * @des 获取所有姓名
     * @version v1
     */
    @GetMapping("v1/listAllSelfName")
    public Result listAllSelfName(  ) throws ActException {
        try {
            List<String> list = selfCapabilityAPI.listAllSelfName();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 上传附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/selfcapability/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     * @param id id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/selfcapability/" + id;
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
     * 附件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("下载成功");
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
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(CapabilityDeleteFileTO.TestDEL.class) CapabilityDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }


}