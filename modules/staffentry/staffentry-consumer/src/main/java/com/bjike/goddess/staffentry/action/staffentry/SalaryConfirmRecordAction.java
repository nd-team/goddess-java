package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.SalaryConfirmRecordAPI;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;
import com.bjike.goddess.staffentry.to.SiginManageDeleteFileTO;
import com.bjike.goddess.staffentry.vo.SalaryConfirmRecordVO;
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
 * 薪资确认
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-11 10:31]
 * @Description: [薪资确认]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("salaryconfirmrecord")
public class SalaryConfirmRecordAction extends BaseFileAction{

    @Autowired
    private SalaryConfirmRecordAPI salaryConfirmRecordAPI;
    @Autowired
    private FileAPI fileAPI;


    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = salaryConfirmRecordAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 薪资确认列表总条数
     *
     * @param salaryConfirmRecordDTO 注册用户信息dto
     * @des 获取所有薪资确认信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws ActException {
        try {
            Long count = salaryConfirmRecordAPI.countSalaryConfirmRecord(salaryConfirmRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个薪资确认
     *
     * @param id 薪资确认信息id
     * @return class SalaryConfirmRecordVO
     * @des 根据id查询薪资确认
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            SalaryConfirmRecordVO salaryConfirmRecordVOList = BeanTransform.copyProperties(
                    salaryConfirmRecordAPI.getOne(id), SalaryConfirmRecordVO.class);
            return ActResult.initialize(salaryConfirmRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 薪资确认列表
     *
     * @param salaryConfirmRecordDTO 薪资确认dto
     * @des 获取所有薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws ActException {
        try {
            List<SalaryConfirmRecordVO> salaryConfirmRecordVOList = BeanTransform.copyProperties(
                    salaryConfirmRecordAPI.listSalaryConfirmRecord(salaryConfirmRecordDTO), SalaryConfirmRecordVO.class, true);
            return ActResult.initialize(salaryConfirmRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param salaryConfirmRecordTO 员工薪资确认数据to
     * @des 添加薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSalaryConfirmRecord(@Validated(SalaryConfirmRecordTO.TestAdd.class) SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.insertSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(salaryConfirmRecordBO1,SalaryConfirmRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param salaryConfirmRecordTO 员工薪资确认数据to
     * @des 编辑薪资确认
     * @return class SalaryConfirmRecordVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSalaryConfirmRecord(@Validated(SalaryConfirmRecordTO.TestAdd.class) SalaryConfirmRecordTO salaryConfirmRecordTO) throws ActException {
        try {
            SalaryConfirmRecordBO salaryConfirmRecordBO1 = salaryConfirmRecordAPI.editSalaryConfirmRecord(salaryConfirmRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(salaryConfirmRecordBO1,SalaryConfirmRecordVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除薪资确认
     *
     * @param id 列表id
     * @des 根据列表id删除员工薪资确认记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSalaryConfirmRecord(@PathVariable String id) throws ActException {
        try {
            salaryConfirmRecordAPI.removeSalaryConfirmRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @des 上传附件
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/staffentry/entrybasicinfo/" + id;
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
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/staffentry/entrybasicinfo/" + id;
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if(null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length>=0 ){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }



}
