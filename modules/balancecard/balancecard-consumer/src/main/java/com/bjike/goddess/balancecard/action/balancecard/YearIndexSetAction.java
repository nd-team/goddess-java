package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.YearIndexSetAPI;
import com.bjike.goddess.balancecard.bo.YearIndexSetBO;
import com.bjike.goddess.balancecard.dto.YearIndexSetDTO;
import com.bjike.goddess.balancecard.excel.SonPermissionObject;
import com.bjike.goddess.balancecard.excel.YearIndexSetExcel;
import com.bjike.goddess.balancecard.to.DendrogramYearSetTO;
import com.bjike.goddess.balancecard.to.ExportExcelYearTO;
import com.bjike.goddess.balancecard.to.GuidePermissionTO;
import com.bjike.goddess.balancecard.to.YearIndexSetTO;
import com.bjike.goddess.balancecard.vo.YearIndexSetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
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
 * 年度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("yearindexset")
public class YearIndexSetAction extends BaseFileAction  {

    @Autowired
    private YearIndexSetAPI yearIndexSetAPI;
    @Autowired
    private FileAPI fileAPI;
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

            List<SonPermissionObject> hasPermissionList = yearIndexSetAPI.sonPermission();
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

            Boolean isHasPermission = yearIndexSetAPI.guidePermission(guidePermissionTO);
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
     * @param yearIndexSetDTO  年度指标信息dto
     * @des 获取所有年度指标信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(YearIndexSetDTO yearIndexSetDTO) throws ActException {
        try {
            Long count = yearIndexSetAPI.countYearIndexSet(yearIndexSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年度指标列表
     *
     * @param yearIndexSetDTO 年度指标信息dto
     * @param request 前端过滤参数
     * @des 获取所有年度指标信息
     * @return  class YearIndexSetVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListYearIndexSet(@Validated(YearIndexSetTO.TestAdd.class) YearIndexSetDTO yearIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<YearIndexSetVO> yearIndexSetVOList = BeanTransform.copyProperties(
                    yearIndexSetAPI.listYearIndexSet(yearIndexSetDTO), YearIndexSetVO.class, request);
            return ActResult.initialize(yearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个年度指标
     *
     * @param id 年度指标信息id
     * @des 获取所有年度指标信息
     * @return  class YearIndexSetVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            YearIndexSetVO yearIndexSetVOList = BeanTransform.copyProperties(
                    yearIndexSetAPI.getOneById( id), YearIndexSetVO.class);
            return ActResult.initialize(yearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加年度指标
     *
     * @param yearIndexSetTO 年度指标基本信息数据to
     * @des 添加年度指标
     * @return  class YearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addYearIndexSet(@Validated(YearIndexSetTO.TestAdd.class) YearIndexSetTO yearIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            YearIndexSetBO yearIndexSetBO1 = yearIndexSetAPI.addYearIndexSet(yearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(yearIndexSetBO1,YearIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑年度指标
     *
     * @param yearIndexSetTO 年度指标基本信息数据bo
     * @des 编辑年度指标
     * @return  class YearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editYearIndexSet(@Validated(YearIndexSetTO.TestAdd.class) YearIndexSetTO yearIndexSetTO) throws ActException {
        try {
            YearIndexSetBO yearIndexSetBO1 = yearIndexSetAPI.editYearIndexSet(yearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(yearIndexSetBO1,YearIndexSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除年度指标信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteYearIndexSet(@PathVariable String id) throws ActException {
        try {
            yearIndexSetAPI.deleteYearIndexSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 部门项目组指标分解
     *
     * @param yearIndexSetTO 年指标分解部门年指标数据to
     * @des 添加年度指标
     * @return  class YearIndexSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/seperateYear")
    public Result seperateDepartYear(@Validated(YearIndexSetTO.TestSer.class) YearIndexSetTO yearIndexSetTO, BindingResult bindingResult) throws ActException {
        try {
            YearIndexSetBO yearIndexSetBO1 = yearIndexSetAPI.seperateDepartYear(yearIndexSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(yearIndexSetBO1,YearIndexSetVO.class));
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
            List<String> list = yearIndexSetAPI.yearList();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //TODO 导入导出
    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<YearIndexSetExcel> tos = ExcelUtil.excelToClazz(is, YearIndexSetExcel.class, excel);
            List<YearIndexSetTO> toList = BeanTransform.copyProperties(tos,YearIndexSetTO.class);
            yearIndexSetAPI.leadExcel(toList);
            return new ActResult("上传成功");
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
    public Result exportExcel(ExportExcelYearTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "年度指标.xlsx";
            super.writeOutFile(response, yearIndexSetAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1){
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 指标库树状图
     *
     * @param yearIndexSetDTO 年度指标信息dto
     * @version v1
     * @return class YearIndexSetVO
     */
    @PostMapping("v1/dendrogram")
    public Result dendrogram( YearIndexSetDTO yearIndexSetDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException{
        try {
            List<YearIndexSetVO> yearIndexSetVOList = BeanTransform.copyProperties(
                    yearIndexSetAPI.dendrogram(yearIndexSetDTO), YearIndexSetVO.class, request);
            return ActResult.initialize(yearIndexSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}