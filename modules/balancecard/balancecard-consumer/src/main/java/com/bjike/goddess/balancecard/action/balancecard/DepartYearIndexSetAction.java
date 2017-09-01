package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.DepartYearIndexSetAPI;
import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.excel.DepartYearIndexSetExcel;
import com.bjike.goddess.balancecard.to.DepartYearIndexSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelDepartTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.vo.DepartYearIndexSetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 部门部门年度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:28 ]
 * @Description: [ 部门部门年度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("departyearindexset")
public class DepartYearIndexSetAction extends BaseFileAction {

    @Autowired
    private DepartYearIndexSetAPI departYearIndexSetAPI;

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

            Boolean isHasPermission = departYearIndexSetAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param departYearIndexSetDTO  部门年度指标信息dto
     * @des 获取所有部门年度指标信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DepartYearIndexSetDTO departYearIndexSetDTO) throws ActException {
        try {
            Long count = departYearIndexSetAPI.countDepartYearIndexSet(departYearIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门年度指标列表
     *
     * @param departYearIndexSetDTO 部门年度指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有部门年度指标信息
     * @return  class DepartYearIndexSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListDepartYearIndexSet(DepartYearIndexSetDTO departYearIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DepartYearIndexSetVO> departYearIndexSetVOList = BeanTransform.copyProperties(
                    departYearIndexSetAPI.listDepartYearIndexSet(departYearIndexSetDTO), DepartYearIndexSetVO.class, request);
            return ActResult.initialize(departYearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个部门年度指标
     *
     * @param id 部门年度指标信息id
     * @des 获取所有部门年度指标信息
     * @return  class DepartYearIndexSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            DepartYearIndexSetVO departYearIndexSetVOList = BeanTransform.copyProperties(
                    departYearIndexSetAPI.getOneById( id), DepartYearIndexSetVO.class);
            return ActResult.initialize(departYearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加部门年度指标
     *
     * @param departYearIndexSetTO 部门年度指标基本信息数据to
     * @des 添加部门年度指标
     * @return  class DepartYearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addDepartYearIndexSet(@Validated(DepartYearIndexSetTO.TestAdd.class) DepartYearIndexSetTO departYearIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            DepartYearIndexSetBO departYearIndexSetBO1 = departYearIndexSetAPI.addDepartYearIndexSet(departYearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departYearIndexSetBO1,DepartYearIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑部门年度指标
     *
     * @param departYearIndexSetTO 部门年度指标基本信息数据bo
     * @des 编辑部门年度指标
     * @return  class DepartYearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editDepartYearIndexSet(@Validated(DepartYearIndexSetTO.TestAdd.class) DepartYearIndexSetTO departYearIndexSetTO) throws ActException {
        try {
            DepartYearIndexSetBO departYearIndexSetBO1 = departYearIndexSetAPI.editDepartYearIndexSet(departYearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departYearIndexSetBO1,DepartYearIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除部门年度指标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteDepartYearIndexSet(@PathVariable String id) throws ActException {
        try {
            departYearIndexSetAPI.deleteDepartYearIndexSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 部门月度指标分解
     *
     * @param departYearIndexSetTO 年指标分解部门年指标数据to
     * @des 添加部门年度指标
     * @return  class DepartYearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/seperateYear")
    public Result seperateDepartYear(@Validated(DepartYearIndexSetTO.TestSer.class) DepartYearIndexSetTO departYearIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            DepartYearIndexSetBO departYearIndexSetBO1 = departYearIndexSetAPI.seperateDepartYear(departYearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(departYearIndexSetBO1,DepartYearIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有地区
     *
     * @des 获取所有地区
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea( ) throws ActException {
        try {
            List<String> arealist = departYearIndexSetAPI.listArea( );
            return ActResult.initialize(arealist);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 获取所有项目组部门
     *
     * @des 获取所有项目组部门
     * @version v1
     */
    @GetMapping("v1/listDepart")
    public Result listDepart( ) throws ActException {
        try {
            List<String> arealist = departYearIndexSetAPI.listDepart( );
            return ActResult.initialize(arealist);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 获取所有员工
     *
     * @des 获取所有员工
     * @version v1
     */
    @GetMapping("v1/listEmp")
    public Result listEmp( ) throws ActException {
        try {
            List<String> arealist = departYearIndexSetAPI.listEmp( );
            return ActResult.initialize(arealist);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 导入 excel
     * @des 导出部门年度指标
     * @version v1
     */
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<DepartYearIndexSetExcel> tos = ExcelUtil.excelToClazz(is, DepartYearIndexSetExcel.class, excel);
            List<DepartYearIndexSetTO> toList = BeanTransform.copyProperties(tos,DepartYearIndexSetTO.class);
            departYearIndexSetAPI.leadExcel(toList);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出 excel
     * @des 导出excel
     * @version v1
     */

    @GetMapping("v1/exportExcel")
    public Result exportExcel(ExportExcelDepartTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "部门年度指标.xlsx";
            super.writeOutFile(response, departYearIndexSetAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }
    //查看本月
    //查看某个月


    /**
     * 根据年指标id查询所分解的部门年度信息来构建树状图
     * @param id 年度指标传来的年度指标id
     * @des 获取所分解的所有部门年度指标信息
     * @return class DepartYearIndexSetVO
     * @version v1
     */
    @GetMapping("v1/dendrogram/{id}")
    public Result dendrogram(@PathVariable String id) throws ActException{
        try {
           List<DepartYearIndexSetVO> departYearIndexSetVOList = BeanTransform.copyProperties(
                    departYearIndexSetAPI.dendrogram( id), DepartYearIndexSetVO.class);
            return ActResult.initialize(departYearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板部门年度指标
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "部门年度指标导入模板.xlsx";
            super.writeOutFile(response, departYearIndexSetAPI.templateExport( ), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}