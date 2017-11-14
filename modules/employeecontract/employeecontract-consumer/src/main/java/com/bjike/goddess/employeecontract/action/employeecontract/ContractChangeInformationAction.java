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
import com.bjike.goddess.employeecontract.api.ContractChangeInformationAPI;
import com.bjike.goddess.employeecontract.bo.ContractChangeInformationBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeInformationDTO;
import com.bjike.goddess.employeecontract.excel.ContractChangeInformationSetExcel;
import com.bjike.goddess.employeecontract.to.ChangeEnsuerTO;
import com.bjike.goddess.employeecontract.to.ContractChangeInformationTO;
import com.bjike.goddess.employeecontract.to.ExportContractChangeInformationTO;
import com.bjike.goddess.employeecontract.vo.ContractChangeInformationVO;
import com.bjike.goddess.storage.api.FileAPI;
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
* 合同变更信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractchangeinformation")
public class ContractChangeInformationAction extends BaseFileAction{
    @Autowired
    private ContractChangeInformationAPI contractChangeInformationAPI;

    @Autowired
    private FileAPI fileAPI;

    /**
     * 添加
     * @param contractChangeInformationTO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ContractChangeInformationTO contractChangeInformationTO) throws ActException{
        try {
            contractChangeInformationAPI.add(contractChangeInformationTO);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param contractChangeInformationTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) ContractChangeInformationTO contractChangeInformationTO) throws ActException{
        try {
            contractChangeInformationAPI.modify(contractChangeInformationTO);
            return new ActResult("修改成功");
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
            contractChangeInformationAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     * @param contractChangeInformationDTO
     * @return class ContractChangeInformationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(ContractChangeInformationDTO contractChangeInformationDTO) throws ActException{
        try {
            List<ContractChangeInformationBO> boList = contractChangeInformationAPI.pageList(contractChangeInformationDTO);
            List<ContractChangeInformationVO> voList = BeanTransform.copyProperties(boList,ContractChangeInformationVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param contractChangeInformationDTO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContractChangeInformationDTO contractChangeInformationDTO) throws ActException{
        try {
            long count = contractChangeInformationAPI.count(contractChangeInformationDTO);
            return ActResult.initialize(contractChangeInformationDTO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询单条数据
     * @param id 数据的id
     * @return class ContractChangeInformationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findOne")
    public Result findOne(String id) throws ActException{
        try {
            ContractChangeInformationBO model = contractChangeInformationAPI.findOne(id);
            ContractChangeInformationVO vo = BeanTransform.copyProperties(model,ContractChangeInformationVO.class);
            return ActResult.initialize(vo);
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
            List<ContractChangeInformationSetExcel> tos = ExcelUtil.excelToClazz(is,ContractChangeInformationSetExcel.class,excel);
            List<ContractChangeInformationTO> toList = BeanTransform.copyProperties(tos,ContractChangeInformationTO.class);
            contractChangeInformationAPI.leadExcel(toList);
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
    public Result exprtExcel(ExportContractChangeInformationTO to, HttpServletResponse response) throws ActException{
        try {
            String fileName = "合同变更信息.xlsx";
            super.writeOutFile(response, contractChangeInformationAPI.exportExcel(to), fileName);
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
            String fileName = "合同变更信息模块.xlsx";
            super.writeOutFile(response,contractChangeInformationAPI.templateExport(),fileName);
            return new ActResult("导出成功");
        } catch (SerException e){
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 变更确认
     * @param changeEnsuerTO
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/change/ensure")
    public Result changeEnsure(@Validated(EDIT.class)ChangeEnsuerTO changeEnsuerTO) throws ActException{
        try {
            contractChangeInformationAPI.changeEnsure(changeEnsuerTO);
            return new ActResult("变更确认成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


 }