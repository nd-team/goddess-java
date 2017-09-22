package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.salarymanage.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.to.SalaryConfirmRecordDeleteFileTO;
import com.bjike.goddess.salarymanage.to.SalaryConfirmRecordTO;
import com.bjike.goddess.salarymanage.vo.SalaryConfirmRecordVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.List;

/**
* 招聘面谈薪资确认记录
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-15 02:20 ]
* @Description:	[ 招聘面谈薪资确认记录 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salaryconfirmrecord")
public class SalaryConfirmRecordAction extends BaseFileAction{
    @Autowired
    private SalaryConfirmRecordAPI salaryConfirmRecordAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 添加
     * @param to 招聘面谈薪资确认记录
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryConfirmRecordTO to) throws ActException{
        try {
            salaryConfirmRecordAPI.add(to);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id 面谈薪资确认记录id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            salaryConfirmRecordAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 修改
     * @param to 面谈薪资确认记录
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SalaryConfirmRecordTO to) throws ActException{
        try {
            salaryConfirmRecordAPI.edit(to);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     * @param dto 查询条件
     * @return class SalaryConfirmRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(SalaryConfirmRecordDTO dto) throws ActException{
        try {
            List<SalaryConfirmRecordBO> boList = salaryConfirmRecordAPI.pageList(dto);
            List<SalaryConfirmRecordVO> voList = BeanTransform.copyProperties(boList,SalaryConfirmRecordVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 薪资确认
     * @param id 招聘面谈薪资确认记录id
     * @param ifConfirm 是否确认
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/confirm")
    public Result confirm(@RequestParam String id ,@RequestParam Boolean ifConfirm) throws ActException{
        try {
            salaryConfirmRecordAPI.confirm(id,ifConfirm);
            return new ActResult("确认成功");
        }catch (SerException e){
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
            String path = "/contract/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/contract/" + id;
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
     * @param salaryConfirmRecordDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(SalaryConfirmRecordDeleteFileTO.TestDEL.class) SalaryConfirmRecordDeleteFileTO salaryConfirmRecordDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != salaryConfirmRecordDeleteFileTO.getPaths() && salaryConfirmRecordDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), salaryConfirmRecordDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }


    /**
     * 列表总条数
     * @param dto 查询条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryConfirmRecordDTO dto) throws ActException{
        try {
            Long count = salaryConfirmRecordAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
 }