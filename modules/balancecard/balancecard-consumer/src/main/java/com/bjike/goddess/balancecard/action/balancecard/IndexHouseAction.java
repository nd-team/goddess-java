package com.bjike.goddess.balancecard.action.balancecard;

import com.bjike.goddess.balancecard.api.DepartMonIndexSetAPI;
import com.bjike.goddess.balancecard.api.DepartYearIndexSetAPI;
import com.bjike.goddess.balancecard.api.PositionIndexSetAPI;
import com.bjike.goddess.balancecard.api.YearIndexSetAPI;
import com.bjike.goddess.balancecard.bo.DepartYearIndexSetBO;
import com.bjike.goddess.balancecard.dto.DepartYearIndexSetDTO;
import com.bjike.goddess.balancecard.entity.PositionIndexSet;
import com.bjike.goddess.balancecard.to.*;
import com.bjike.goddess.balancecard.vo.DepartYearIndexSetVO;
import com.bjike.goddess.balancecard.vo.DepartMonIndexSetVO;
import com.bjike.goddess.balancecard.vo.PositionIndexSetVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.bjike.goddess.balancecard.vo.YearIndexSetVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 指标库
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:28 ]
 * @Description: [ 指标库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("indexhouse")
public class IndexHouseAction extends BaseFileAction {

    @Autowired
    private DepartYearIndexSetAPI departYearIndexSetAPI;
    @Autowired
    private DepartMonIndexSetAPI departMonIndexSetAPI;
    @Autowired
    private PositionIndexSetAPI positionIndexSetAPI;
    @Autowired
    private YearIndexSetAPI yearIndexSetAPI;


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

            Boolean isHasPermission = departMonIndexSetAPI.guidePermission(guidePermissionTO);
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


    //TODO: 列表是树形的，看流程图，还没做

    /**
     * 年度报告
     *
     * @param to 年度指标信息
     * @return class YearIndexSetVO
     * @des 获取所有年度指标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/yearReport")
    public Result yearReport(@Validated(ADD.class) ExportExcelYearTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "年度报告.xlsx";
            super.writeOutFile(response, yearIndexSetAPI.exportYearExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 部门年度报告
     *
     * @param to 部门年度指标信息
     * @return class DepartYearIndexSetVO
     * @des 获取所有部门年度指标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/departYearReport")
    public Result departYearReport(@Validated(ADD.class) ExportExcelDepartTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "部门年度报告.xlsx";
            super.writeOutFile(response, departYearIndexSetAPI.departYearReport(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 部门月度报告
     *
     * @param to 部门年度指标信息
     * @return class DepartMonIndexSetVO
     * @des 获取所有部门月度指标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/departMonReport")
    public Result departMonReport(@Validated(ADD.class) ExportExcelDepartTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "部门月度报告.xlsx";
            super.writeOutFile(response, departMonIndexSetAPI.exportExcel(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 岗位报告
     *
     * @param to 岗位指标信息
     * @return class PositionIndexSetVO
     * @des 获取所有岗位指标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/positionReport")
    public Result positionReport(@Validated(ADD.class) ExportExcelPositTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位报告.xlsx";
            super.writeOutFile(response, positionIndexSetAPI.positionReport(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 个人报告
     *
     * @param to 岗位指标信息
     * @return class PositionIndexSetVO
     * @des 获取所有个人指标信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/personReport")
    public Result personReport(@Validated(ADD.class) ExportExcelPositTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "岗位报告.xlsx";
            super.writeOutFile(response, positionIndexSetAPI.personReport(to), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 维度报告
     *
     * @param to 维度报告 信息
     * @des 获取所有维度报告信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deminsionReport")
    public Result personReport(@Validated(ADD.class) ExportExcelDeTO to, HttpServletResponse response) throws ActException {
        try {
            //年度报告/部门年度报告/部门月度报告/岗位报告/个人
            String flag = to.getFlag();
            String fileName = "";
            switch (flag) {
                case "年度报告":
                    fileName = "年度维度报告.xlsx";
                    ExportExcelYearTO yearto = new ExportExcelYearTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        yearto.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        yearto.setStartTime(to.getStartTime());
                        yearto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearto), fileName);
                    break;
                case "部门年度报告":
                    fileName = "部门年度维度报告.xlsx";
                    ExportExcelDepartTO dyearto = new ExportExcelDepartTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        dyearto.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        dyearto.setStartTime(to.getStartTime());
                        dyearto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, departYearIndexSetAPI.departYearReport(dyearto), fileName);
                    break;
                case "部门月度报告":
                    fileName = "部门月度维度报告.xlsx";
                    ExportExcelDepartTO dMonto = new ExportExcelDepartTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        dMonto.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        dMonto.setStartTime(to.getStartTime());
                        dMonto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, departMonIndexSetAPI.exportExcel(dMonto), fileName);
                    break;
                case "岗位报告":
                    fileName = "岗位维度报告.xlsx";
                    ExportExcelPositTO postto = new ExportExcelPositTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        postto.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        postto.setStartTime(to.getStartTime());
                        postto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, positionIndexSetAPI.positionReport(postto), fileName);
                    break;
                case "个人报告":
                    fileName = "个人维度报告.xlsx";
                    ExportExcelPositTO perto = new ExportExcelPositTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        perto.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        perto.setStartTime(to.getStartTime());
                        perto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, positionIndexSetAPI.personReport(perto), fileName);
                    break;
                default:
                    fileName = "年度维度报告.xlsx";
                    ExportExcelYearTO yearTo = new ExportExcelYearTO();
                    if(StringUtils.isNotBlank(to.getDimension())) {
                        yearTo.setDimension(to.getDimension());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        yearTo.setStartTime(to.getStartTime());
                        yearTo.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearTo), fileName);
                    break;
            }

            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 指标类型报告
     *
     * @param to 维度报告 信息
     * @des 获取所有维度报告信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/indexTypeReport")
    public Result indexTypeReport(@Validated(ADD.class) ExportExcelTypeTO to, HttpServletResponse response) throws ActException {
        try {
            //年度报告/部门年度报告/部门月度报告/岗位报告/个人
            String flag = to.getFlag();
            String fileName = "";
            switch (flag) {
                case "年度报告":
                    fileName = "年度标类型报告.xlsx";
                    ExportExcelYearTO yearto = new ExportExcelYearTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        yearto.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        yearto.setStartTime(to.getStartTime());
                        yearto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearto), fileName);
                    break;
                case "部门年度报告":
                    fileName = "部门年度标类型报告.xlsx";
                    ExportExcelDepartTO dyearto = new ExportExcelDepartTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        dyearto.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        dyearto.setStartTime(to.getStartTime());
                        dyearto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, departYearIndexSetAPI.departYearReport(dyearto), fileName);
                    break;
                case "部门月度报告":
                    fileName = "部门月度标类型报告.xlsx";
                    ExportExcelDepartTO dMonto = new ExportExcelDepartTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        dMonto.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        dMonto.setStartTime(to.getStartTime());
                        dMonto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, departMonIndexSetAPI.exportExcel(dMonto), fileName);
                    break;
                case "岗位报告":
                    fileName = "岗位标类型报告.xlsx";
                    ExportExcelPositTO postto = new ExportExcelPositTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        postto.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        postto.setStartTime(to.getStartTime());
                        postto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, positionIndexSetAPI.positionReport(postto), fileName);
                    break;
                case "个人报告":
                    fileName = "个人标类型报告.xlsx";
                    ExportExcelPositTO perto = new ExportExcelPositTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        perto.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        perto.setStartTime(to.getStartTime());
                        perto.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, positionIndexSetAPI.personReport(perto), fileName);
                    break;
                default:
                    fileName = "年度指标类型报告.xlsx";
                    ExportExcelYearTO yearTo = new ExportExcelYearTO();
                    if(StringUtils.isNotBlank(to.getIndexType())) {
                        yearTo.setIndexType(to.getIndexType());
                    }
                    if(StringUtils.isNotBlank(to.getStartTime()) && StringUtils.isNotBlank(to.getEndTime())) {
                        yearTo.setStartTime(to.getStartTime());
                        yearTo.setEndTime(to.getEndTime());
                    }
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearTo), fileName);
                    break;
            }

            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}