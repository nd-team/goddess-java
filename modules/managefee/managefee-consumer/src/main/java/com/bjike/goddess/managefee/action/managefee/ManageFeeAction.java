package com.bjike.goddess.managefee.action.managefee;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managefee.api.ManageFeeAPI;
import com.bjike.goddess.managefee.bo.ManageFeeBO;
import com.bjike.goddess.managefee.dto.ManageFeeDTO;
import com.bjike.goddess.managefee.excel.ManageFeeAreaExportDetail;
import com.bjike.goddess.managefee.excel.ManageFeeImport;
import com.bjike.goddess.managefee.to.*;
import com.bjike.goddess.managefee.vo.ManageFeeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-27 09:38 ]
 * @Description: [ 管理费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("managefee")
public class ManageFeeAction extends BaseFileAction{

    @Autowired
    private ManageFeeAPI manageFeeAPI;

    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = manageFeeAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param manageFeeDTO 管理费信息dto
     * @des 获取所有管理费信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ManageFeeDTO manageFeeDTO) throws ActException {
        try {
            Long count = manageFeeAPI.countManageFee(manageFeeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个管理费
     *
     * @param id 管理费信息id
     * @return class ManageFeeVO
     * @des 根据id获取所有管理费信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ManageFeeVO manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.getOneById(id), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 管理费列表
     *
     * @param manageFeeDTO 管理费信息dto
     * @param request      前端过滤参数
     * @return class ManageFeeVO
     * @des 获取所有管理费信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListManageFee(ManageFeeDTO manageFeeDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.listManageFee(manageFeeDTO), ManageFeeVO.class, request);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加管理费
     *
     * @param manageFeeTO 管理费基本信息数据to
     * @return class ManageFeeVO
     * @des 添加管理费
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addManageFee(@Validated(ManageFeeTO.TestAdd.class) ManageFeeTO manageFeeTO, BindingResult bindingResult) throws ActException {
        try {
            ManageFeeBO manageFeeBO1 = manageFeeAPI.addManageFee(manageFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(manageFeeBO1, ManageFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑管理费
     *
     * @param manageFeeTO 管理费基本信息数据bo
     * @return class ManageFeeVO
     * @des 编辑管理费
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editManageFee(@Validated(ManageFeeTO.TestAdd.class) ManageFeeTO manageFeeTO , BindingResult bindingResult ) throws ActException {
        try {
            ManageFeeBO manageFeeBO1 = manageFeeAPI.editManageFee(manageFeeTO);
            return ActResult.initialize(BeanTransform.copyProperties(manageFeeBO1, ManageFeeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除管理费信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteManageFee(@PathVariable String id) throws ActException {
        try {
            manageFeeAPI.deleteManageFee(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 根据地区汇总合计
     *
     * @param collectAreaTO 地区汇总to
     * @return class ManageFeeVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/ctArea")
    public Result collectCom(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectArea(collectAreaTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目组汇总合计
     *
     * @param collectGroupTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据项目组汇总
     * @version v1
     */
    @GetMapping("v1/ctGroup")
    public Result ctGroup(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectGroupTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectGroup(collectGroupTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总合计
     *
     * @param collectProjectTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据项目汇总
     * @version v1
     */
    @GetMapping("v1/ctProject")
    public Result collectPro(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectProject(collectProjectTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据类别汇总合计
     *
     * @param collectCategoryTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据类别汇总
     * @version v1
     */
    @GetMapping("v1/ctType")
    public Result ctType(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectType(collectCategoryTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据地区汇总详情
     *
     * @param collectAreaTO 地区汇总to
     * @return class ManageFeeVO
     * @des 根据地区汇总
     * @version v1
     */
    @GetMapping("v1/collectArea/detail")
    public Result collectAreaDetial(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectAreaDetial(collectAreaTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目组汇总详情
     *
     * @param collectGroupTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据项目组汇总
     * @version v1
     */
    @GetMapping("v1/collectGroup/detail")
    public Result ctGroupDetial(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectGroupTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectGroupDetail(collectGroupTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目汇总详情
     *
     * @param collectProjectTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据项目汇总
     * @version v1
     */
    @GetMapping("v1/collectProject/detail")
    public Result collectProDetial(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectProjectDetail(collectProjectTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据类别汇总详情
     *
     * @param collectCategoryTO 管理费信息to
     * @return class ManageFeeVO
     * @des 根据类别汇总
     * @version v1
     */
    @GetMapping("v1/collectType/detail")
    public Result ctTypeDetial(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , BindingResult bindingResult) throws ActException {
        try {
            List<ManageFeeVO> manageFeeVOList = BeanTransform.copyProperties(
                    manageFeeAPI.collectTypeDetail(collectCategoryTO), ManageFeeVO.class);
            return ActResult.initialize(manageFeeVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有年份
     *
     * @version v1
     */
    @GetMapping("v1/listYear")
    public Result yearList() throws ActException {
        try {
            List<String> list = manageFeeAPI.yearList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result areaList() throws ActException {
        try {
            List<String> list = manageFeeAPI.areaList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目组
     *
     * @version v1
     */
    @GetMapping("v1/listGroup")
    public Result groupList() throws ActException {
        try {
            List<String> list = manageFeeAPI.groupList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总类别
     *
     * @version v1
     */
    @GetMapping("v1/listType")
    public Result typeList() throws ActException {
        try {
            List<String> list = manageFeeAPI.typeList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有汇总项目
     *
     * @version v1
     */
    @GetMapping("v1/listProject")
    public Result projectList() throws ActException {
        try {
            List<String> list = manageFeeAPI.projectList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出地区excel
     *
     * @param collectAreaTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/area/export")
    public Result areaExportReport(@Validated(CollectAreaTO.TestAdd.class) CollectAreaTO collectAreaTO, HttpServletResponse response) throws ActException {
        try {
            String fileName = "地区汇总.xlsx";
            super.writeOutFile(response, manageFeeAPI.areaExportReport(collectAreaTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出项目名称excel
     *
     * @param collectProjectTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/project/export")
    public Result projectExportReport(@Validated(CollectProjectTO.TestAdd.class) CollectProjectTO collectProjectTO ,  HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目名称汇总.xlsx";
            super.writeOutFile(response, manageFeeAPI.projectExportReport(collectProjectTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出项目组excel
     *
     * @param collectProjectTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/group/export")
    public Result groupExportReport(@Validated(CollectGroupTO.TestAdd.class) CollectGroupTO collectProjectTO , HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目组汇总.xlsx";
            super.writeOutFile(response, manageFeeAPI.groupExportReport(collectProjectTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出类别excel
     *
     * @param collectCategoryTO 管理费用
     * @des 管理费用
     * @version v1
     */
    @GetMapping("v1/type/export")
    public Result typeExportReport(@Validated(CollectCategoryTO.TestAdd.class) CollectCategoryTO collectCategoryTO , HttpServletResponse response) throws ActException {
        try {
            String fileName = "类别汇总.xlsx";
            super.writeOutFile(response, manageFeeAPI.typeExportReport(collectCategoryTO), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }



    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<ManageFeeImport> tos = ExcelUtil.excelToClazz(is, ManageFeeAreaExportDetail.class, excel);
            List<ManageFeeTO> tocs = new ArrayList<>();
            for (ManageFeeImport str : tos) {
                ManageFeeTO manageFeeTO = BeanTransform.copyProperties(str, ManageFeeTO.class);
                if(StringUtils.isNotBlank(str.getMonth()) ){
                    if(  Integer.parseInt( str.getMonth().trim())>12  ||  Integer.parseInt( str.getMonth().trim())<=0 ){
                        throw new ActException("导入失败，月份必须在1-12月份");
                    }
                    str.setMonth( String.valueOf(Integer.parseInt( str.getMonth().trim())) );
                }
                tocs.add(manageFeeTO);
            }
            //注意序列化
            manageFeeAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




    /**
     * excel导入模板下载
     *
     * @des 下载模板管理费用
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "管理费用导入模板.xlsx";
            super.writeOutFile(response, manageFeeAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}