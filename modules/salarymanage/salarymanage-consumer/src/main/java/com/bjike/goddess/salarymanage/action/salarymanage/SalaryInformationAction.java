package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.salarymanage.api.SalaryInformationAPI;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.salarymanage.vo.SalaryInformationVO;
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
     * 添加
     * @param to
     * @return class SalaryInformationVO
     * @throws ActException
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(SalaryInformationTO.TestAdd.class)SalaryInformationTO to) throws ActException{
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
    public Result edit(@Validated(SalaryInformationTO.TestAdd.class)SalaryInformationTO to) throws ActException{
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
     * @return class SalaryBasicTO
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


 }