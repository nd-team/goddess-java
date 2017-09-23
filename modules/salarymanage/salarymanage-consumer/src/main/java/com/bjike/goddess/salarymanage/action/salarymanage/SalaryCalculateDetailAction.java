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
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.vo.InterviewInforVO;
import com.bjike.goddess.salarymanage.api.SalaryCalculateDetailAPI;
import com.bjike.goddess.salarymanage.bo.SalaryCalculateDetailBO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.excel.SalaryCalculateDetailSetExcel;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.excel.SonPermissionObject;
import com.bjike.goddess.salarymanage.to.ExportSalaryCalculateTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateDetailTO;
import com.bjike.goddess.salarymanage.vo.SalaryCalculateDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* 薪资测算明细表
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-16 10:45 ]
* @Description:	[ 薪资测算明细表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarycalculatedetail")
public class SalaryCalculateDetailAction extends BaseFileAction{
    @Autowired
    private SalaryCalculateDetailAPI salaryCalculateDetailAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;



    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = salaryCalculateDetailAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = salaryCalculateDetailAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     * @param to 薪资测算明细表明细
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryCalculateDetailTO to) throws ActException{
        try {
            salaryCalculateDetailAPI.add(to);
            return new ActResult("添加成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id 薪资测试明细表id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException{
        try {
            salaryCalculateDetailAPI.delete(id);
            return new ActResult("删除成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 修改
     * @param to 薪资测试明细修改内容
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/modify")
    public Result modify(@Validated(EDIT.class) SalaryCalculateDetailTO to) throws ActException{
        try {
            salaryCalculateDetailAPI.modify(to);
            return new ActResult("修改成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     * @param detailDTO 查询条件
     * @return class SalaryCalculateDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(SalaryCalculateDetailDTO detailDTO) throws ActException{
        try {
            List<SalaryCalculateDetailBO> boList = salaryCalculateDetailAPI.findList(detailDTO);
            List<SalaryCalculateDetailVO> voList = BeanTransform.copyProperties(boList,SalaryCalculateDetailVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 从招聘管理获取岗位和姓名和地区项目组和部门等信息
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/interview")
    public Result findInterview() throws ActException{
        try {
            List<InterviewInforBO> boList = salaryCalculateDetailAPI.findInteview();
            List<InterviewInforVO> voList = BeanTransform.copyProperties(boList,InterviewInforVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表总条数
     * @param detailDTO 查询条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryCalculateDetailDTO detailDTO) throws ActException{
        try {
            Long count = salaryCalculateDetailAPI.count(detailDTO);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查找单条数据
     * @param id 薪资测试明细表
     * @return class SalaryCalculateDetailVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            SalaryCalculateDetailBO bo =  salaryCalculateDetailAPI.findOne(id);
            SalaryCalculateDetailVO vo = BeanTransform.copyProperties(bo,SalaryCalculateDetailVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SalaryCalculateDetailSetExcel> tos = ExcelUtil.excelToClazz(is, SalaryCalculateDetailSetExcel.class, excel);
            List<SalaryCalculateDetailTO> toList = BeanTransform.copyProperties(tos, SalaryCalculateDetailTO.class);
            salaryCalculateDetailAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
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
    public Result exportExcel(ExportSalaryCalculateTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资测算明细表.xlsx";
            super.writeOutFile(response, salaryCalculateDetailAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板项目签订与立项
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资测算明细表模板.xlsx";
            super.writeOutFile(response, salaryCalculateDetailAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
 }