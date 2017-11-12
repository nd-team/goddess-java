package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.employeecontract.api.ContractInformationAPI;
import com.bjike.goddess.employeecontract.bo.ContractInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractInformationDTO;
import com.bjike.goddess.employeecontract.excel.ContractInformationSetExcel;
import com.bjike.goddess.employeecontract.to.*;
import com.bjike.goddess.employeecontract.vo.ContractInformationVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
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
* 员工合同信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-08 10:56 ]
* @Description:	[ 员工合同信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractinformation")
public class ContractInformationAction extends BaseFileAction{
    @Autowired
    private ContractInformationAPI contractInformationAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 添加
     * @param contractInformationTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractInformationTO contractInformationTO) throws ActException{
        try {
            contractInformationAPI.add(contractInformationTO);
            return new ActResult("添加成功");
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
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            contractInformationAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     * @param contractInformationTO
     * @return
     * @throws ActException
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) ContractInformationTO contractInformationTO) throws ActException{
        try {
            contractInformationAPI.modify(contractInformationTO);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     * @param contractInformationDTO
     * @return class ContractInformationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ContractInformationDTO contractInformationDTO) throws ActException{
        try {
            List<ContractInformationBO> boList = contractInformationAPI.pageList(contractInformationDTO);
            List<ContractInformationVO> voList = BeanTransform.copyProperties(boList,ContractInformationVO.class);
            return ActResult.initialize(voList);

        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return class ContractInformationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            ContractInformationBO bo = contractInformationAPI.findOne(id);
            ContractInformationVO vo = BeanTransform.copyProperties(bo,ContractInformationVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param contractInformationDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContractInformationDTO contractInformationDTO) throws ActException{
        try {
            Long number = contractInformationAPI.count(contractInformationDTO);
            return ActResult.initialize(number);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 是否续签确认
     * @param renewEnsureTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/renew/ensure")
    public Result renewEnsure(RenewEnsureTO renewEnsureTO) throws ActException{
        try {
            contractInformationAPI.renewEnsure(renewEnsureTO);
            return new ActResult("确认成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 合同解除
     * @param relieveContractTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/relieve/contract")
    public Result relieve(RelieveContractTO relieveContractTO) throws ActException{
        try {
            contractInformationAPI.relieveContract(relieveContractTO);
            return new ActResult("操作成功");
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
    @PostMapping("v1/lead/excel")
    public Result leadExcel(HttpServletRequest request) throws ActException{
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0,1);
            List<ContractInformationSetExcel> tos = ExcelUtil.excelToClazz(is,ContractInformationSetExcel.class,excel);
            List<ContractInformationTO> toList = BeanTransform.copyProperties(tos,ContractInformationTO.class);
            contractInformationAPI.leadExcel(toList);
            return new ActResult("导入成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     * @param to 导出条件
     * @param response
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/exportExcel")
    public Result exprtExcel(ExportContractInformationTO to,HttpServletResponse response) throws ActException{
        try {
            String fileName = "员工合同信息.xlsx";
            super.writeOutFile(response, contractInformationAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e){
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * excel模板下载
     * @param response
     * @des 下载模板员工信息模板
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException{
        try {
            String fileName = "员工合同信息模块.xlsx";
            super.writeOutFile(response,contractInformationAPI.templateExport(),fileName);
            return new ActResult("导出成功");
        } catch (SerException e){
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 上传附件
     * @param id
     * @param request 注入HttpServletRequest对象
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id,HttpServletRequest request) throws ActException{
        try {
            //跟前端约定好，文件路径是列表id
            String path = "/contractinformation"+id;
            List<InputStream> inputStreams = super.getInputStreams(request,path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 文件附件列表
     * @param id
     * @param request
     * @return class FileVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id,HttpServletRequest request) throws ActException{
        try {
            //跟前端约定好，文件路径是列表id
            String path = "contractinformation"+id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo),FileVO.class);
            return ActResult.initialize(files);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     * @param path
     * @param request
     * @param response
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/download")
    public Result download(@RequestParam String path,HttpServletRequest request,HttpServletResponse response) throws ActException{
        try {
            //该文件路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            byte[] buffer = fileAPI.download(fileInfo);
            String fileName = StringUtils.substringAfterLast(fileInfo.getPath(),"/");
            writeOutFile(response,buffer,fileName);
            return new ActResult("下载成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除附件
     * @param to
     * @param request
     * @throws SerException
     * @version v1
     */
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(ContractInformationDeleteFileTO.TestDEL.class) ContractInformationDeleteFileTO to,HttpServletRequest request) throws SerException{
        if (null != to.getPaths() && to.getPaths().length >= 0){
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(),to.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 根据姓名去转正管理获取转正日期
     * @param userName
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/getRegular/date")
    public Result getRegularDate(@RequestParam String userName) throws ActException{
        try {
            String date = contractInformationAPI.getRegularDate(userName);
            return ActResult.initialize(date);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名去离职管理获取离职日期
     * @param userName
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/getDimission/date")
    public Result getDimissionDate(@RequestParam String userName) throws ActException{
        try {
            String date = contractInformationAPI.getDimissionDate(userName);
            return ActResult.initialize(date);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }