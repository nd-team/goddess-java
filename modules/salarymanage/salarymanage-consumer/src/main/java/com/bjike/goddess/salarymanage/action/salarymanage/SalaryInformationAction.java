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
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.salarymanage.api.SalaryInformationAPI;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationDeleteFileTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.salarymanage.vo.SalaryInformationVO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.vo.EntryBasicInfoVO;
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
* 薪资管理薪资资料
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理薪资资料]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salaryinformation")
public class SalaryInformationAction extends BaseFileAction{
    @Autowired
    private SalaryInformationAPI salaryInformationAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 列表
     * @param dto
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/page/list")
    public Result pageList(SalaryInformationDTO dto) throws ActException{
        try {
            List<SalaryInformationBO> boList = salaryInformationAPI.pageList(dto);
            List<SalaryInformationVO> voList = BeanTransform.copyProperties(boList,SalaryInformationVO.class,true);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加薪资资料
     * @param to
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryInformationTO to) throws ActException{
        try {
            SalaryInformationBO bo = salaryInformationAPI.addSalaryInformation(to);
            SalaryInformationVO vo = BeanTransform.copyProperties(bo,SalaryInformationVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param to
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SalaryInformationTO to) throws ActException{
        try {
            SalaryInformationBO bo = salaryInformationAPI.editSalaryInformation(to);
            SalaryInformationVO vo = BeanTransform.copyProperties(bo,SalaryInformationVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException{
        try {
            salaryInformationAPI.deleteSalaryInformation(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     * @param request
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException{
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0,2);
            List<SalaryInformationSetExcel> tos = ExcelUtil.excelToClazz(is, SalaryInformationSetExcel.class, excel);
            List<SalaryInformationTO> toList = BeanTransform.copyProperties(tos,SalaryInformationTO.class);
            salaryInformationAPI.leadExcel(toList);
            return new ActResult("导入成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出Excel
     *
     * @param to 导出条件
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exportExcel(ExportSalaryInformationTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资资料.xlsx";
            super.writeOutFile(response, salaryInformationAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板项目签订与立项
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资资料模板.xlsx";
            super.writeOutFile(response, salaryInformationAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(SalaryInformationDeleteFileTO.TestDEL.class) SalaryInformationDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 列表总条数
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryInformationDTO dto) throws ActException{
        try{
            Long count = salaryInformationAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据员工编号来查询管理等级
     * @param id
     * @return class LevelShow
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/level/{id}")
    public Result findByEmployeeId(@PathVariable String id) throws ActException{
        try {
            LevelShow show = salaryInformationAPI.findByEmployeeId(id);
            return ActResult.initialize(show);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工编号来查询入职基本信息
     * @param id
     * @return class EntryBasicInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/entryBasic")
    public Result getByEmpNumber(String id) throws ActException{
        try {
            List<EntryBasicInfoBO> boList = salaryInformationAPI.getByEmpNumber(id);
            List<EntryBasicInfoVO> voList = BeanTransform.copyProperties(boList,EntryBasicInfoVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



 }