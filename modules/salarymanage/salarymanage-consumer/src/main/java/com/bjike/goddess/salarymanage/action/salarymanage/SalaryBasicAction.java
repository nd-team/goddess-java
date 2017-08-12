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
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.vo.*;
import com.bjike.goddess.salarymanage.api.SalaryBasicAPI;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.entity.SalaryBasic;
import com.bjike.goddess.salarymanage.excel.SalaryBasicSetExcel;
import com.bjike.goddess.salarymanage.excel.SonPermissionObject;
import com.bjike.goddess.salarymanage.to.ExportSalaryBasicTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import com.bjike.goddess.salarymanage.vo.SalaryBasicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
* 薪资管理基本信息设置
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理基本信息设置 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarybasic")
public class SalaryBasicAction extends BaseFileAction {
    @Autowired
    private SalaryBasicAPI salaryBasicAPI;

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

            List<SonPermissionObject> hasPermissionList = salaryBasicAPI.sonPermission();
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

            Boolean isHasPermission = salaryBasicAPI.guidePermission(guidePermissionTO);
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
     * 查询所有地区
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/findArea")
    public Result findArea() throws ActException{
        try{
            List<AreaBO> list = salaryBasicAPI.findArea();
            List<AreaVO> areaVOS = BeanTransform.copyProperties(list,AreaVO.class);
            return ActResult.initialize(areaVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有未冻结的部门
     * @return class OpinionVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/thawOpinion/find")
    public Result findThawOpinion() throws ActException{
        try{
            List<OpinionBO> list = salaryBasicAPI.findThawOpinion();
            List<OpinionVO> operateVOS = BeanTransform.copyProperties(list,OpinionVO.class);
            return ActResult.initialize(operateVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有正常状态的体系
     * @return class HierarchyVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/status")
    public Result findStatus() throws ActException{
        try {
           List<HierarchyBO> list =  salaryBasicAPI.findStatus();
           List<HierarchyVO> hierarchyVOS = BeanTransform.copyProperties(list,HierarchyVO.class);
           return ActResult.initialize(hierarchyVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询所有正常状态的岗位详细
     * @return class PositionDetailVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/position")
    public Result findPosition() throws ActException{
        try {
            List<PositionDetailBO> boList = salaryBasicAPI.findPosition();
            List<PositionDetailVO> voList = BeanTransform.copyProperties(boList,PositionDetailVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     * @param dto
     * @return class SalaryBasicVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/page/list")
    public Result pageList(SalaryBasicDTO dto) throws ActException{
        try {
            List<SalaryBasicBO> boList = salaryBasicAPI.pageList(dto);
            List<SalaryBasicVO> voList = BeanTransform.copyProperties(boList,SalaryBasicVO.class,true);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有薪资基本资料
     * @return class SalaryBasicVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/salaryBasic")
    public Result findSalaryBasic() throws ActException{
        try {
            List<SalaryBasicBO> boList = salaryBasicAPI.findSalaryBasic();
            List<SalaryBasicVO> voList = BeanTransform.copyProperties(boList,SalaryBasicVO.class,true);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有薪资基本资料
     * @param dto
     * @return class SalaryBasicVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/salary")
    public Result findSalary(SalaryBasicDTO dto) throws ActException{
        try{
            List<SalaryBasicBO> list = salaryBasicAPI.findSalaryBasic();
            List<SalaryBasicVO> voList = BeanTransform.copyProperties(list,SalaryBasicVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     * @param to
     * @return class SalaryBasicVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryBasicTO to) throws ActException{
        try {
            SalaryBasicBO bo = salaryBasicAPI.add(to);
            SalaryBasicVO vo = BeanTransform.copyProperties(bo,SalaryBasicVO.class);
            return ActResult.initialize(vo);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     * @param to
     * @return class SalaryBasicVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SalaryBasicTO to) throws ActException{
        try {
            SalaryBasicBO bo = salaryBasicAPI.edit(to);
            SalaryBasicVO vo = BeanTransform.copyProperties(bo,SalaryBasicVO.class);
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
            salaryBasicAPI.delete(id);
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
            List<SalaryBasicSetExcel> tos = ExcelUtil.excelToClazz(is, SalaryBasicSetExcel.class, excel);
            List<SalaryBasicTO> toList = BeanTransform.copyProperties(tos,SalaryBasicTO.class);
            salaryBasicAPI.leadExcel(toList);
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
    public Result exportExcel(ExportSalaryBasicTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资管理基本信息.xlsx";
            super.writeOutFile(response, salaryBasicAPI.exportExcel(to), fileName);
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
            String fileName = "薪资管理基本信息模板.xlsx";
            super.writeOutFile(response, salaryBasicAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 列表总条数
     * @param dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryBasicDTO dto) throws ActException{
        try{
            Long count = salaryBasicAPI.count(dto);
            return ActResult.initialize(count);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


 }