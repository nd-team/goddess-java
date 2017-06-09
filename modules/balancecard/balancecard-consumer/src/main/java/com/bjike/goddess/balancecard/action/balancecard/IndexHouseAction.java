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
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result yearReport(ExportExcelYearTO to, HttpServletResponse response) throws ActException {
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
    public Result departYearReport(ExportExcelDepartTO to, HttpServletResponse response) throws ActException {
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
    public Result departMonReport(ExportExcelDepartTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "部门月度报告.xlsx";
            super.writeOutFile(response, departMonIndexSetAPI.departMonReport(to), fileName);
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
    public Result positionReport(ExportExcelPositTO to, HttpServletResponse response) throws ActException {
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
    public Result personReport(ExportExcelPositTO to, HttpServletResponse response) throws ActException {
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
    public Result personReport(ExportExcelDeTO to, HttpServletResponse response) throws ActException {
        try {
            //年度报告/部门年度报告/部门月度报告/岗位报告/个人
            String flag = to.getFlag();
            String fileName = "";
            switch (flag) {
                case "年度报告":
                    fileName = "年度维度报告.xlsx";
                    ExportExcelYearTO yearto = new ExportExcelYearTO();
                    yearto.setDimension( to.getDimension());
                    yearto.setStartTime( to.getStartTime() );
                    yearto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearto), fileName);
                    break;
                case "部门年度报告":
                    fileName = "部门年度维度报告.xlsx";
                    ExportExcelDepartTO dyearto = new ExportExcelDepartTO();
                    dyearto.setDimension( to.getDimension());
                    dyearto.setStartTime( to.getStartTime() );
                    dyearto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, departYearIndexSetAPI.departYearReport(dyearto), fileName);
                    break;
                case "部门月度报告":
                    fileName = "部门月度维度报告.xlsx";
                    ExportExcelDepartTO dMonto = new ExportExcelDepartTO();
                    dMonto.setDimension( to.getDimension());
                    dMonto.setStartTime( to.getStartTime() );
                    dMonto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, departMonIndexSetAPI.departMonReport(dMonto), fileName);
                    break;
                case "岗位报告":
                    fileName = "岗位维度报告.xlsx";
                    ExportExcelPositTO postto = new ExportExcelPositTO();
                    postto.setDimension( to.getDimension());
                    postto.setStartTime( to.getStartTime() );
                    postto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, positionIndexSetAPI.positionReport(postto), fileName);
                    break;
                case "个人报告":
                    fileName = "个人维度报告.xlsx";
                    ExportExcelPositTO perto = new ExportExcelPositTO();
                    perto.setDimension( to.getDimension());
                    perto.setStartTime( to.getStartTime() );
                    perto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, positionIndexSetAPI.personReport(perto), fileName);
                    break;
                default:
                    fileName = "年度维度报告.xlsx";
                    ExportExcelYearTO yearTo = new ExportExcelYearTO();
                    yearTo.setDimension( to.getDimension());
                    yearTo.setStartTime( to.getStartTime() );
                    yearTo.setEndTime( to.getEndTime());
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
    public Result indexTypeReport(ExportExcelTypeTO to, HttpServletResponse response) throws ActException {
        try {
            //年度报告/部门年度报告/部门月度报告/岗位报告/个人
            String flag = to.getFlag();
            String fileName = "";
            switch (flag) {
                case "年度报告":
                    fileName = "年度标类型报告.xlsx";
                    ExportExcelYearTO yearto = new ExportExcelYearTO();
                    yearto.setIndexType( to.getIndexType());
                    yearto.setStartTime( to.getStartTime() );
                    yearto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, yearIndexSetAPI.exportYearDeExcel(yearto), fileName);
                    break;
                case "部门年度报告":
                    fileName = "部门年度标类型报告.xlsx";
                    ExportExcelDepartTO dyearto = new ExportExcelDepartTO();
                    dyearto.setIndexType( to.getIndexType());
                    dyearto.setStartTime( to.getStartTime() );
                    dyearto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, departYearIndexSetAPI.departYearReport(dyearto), fileName);
                    break;
                case "部门月度报告":
                    fileName = "部门月度标类型报告.xlsx";
                    ExportExcelDepartTO dMonto = new ExportExcelDepartTO();
                    dMonto.setIndexType( to.getIndexType());
                    dMonto.setStartTime( to.getStartTime() );
                    dMonto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, departMonIndexSetAPI.departMonReport(dMonto), fileName);
                    break;
                case "岗位报告":
                    fileName = "岗位标类型报告.xlsx";
                    ExportExcelPositTO postto = new ExportExcelPositTO();
                    postto.setIndexType( to.getIndexType());
                    postto.setStartTime( to.getStartTime() );
                    postto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, positionIndexSetAPI.positionReport(postto), fileName);
                    break;
                case "个人报告":
                    fileName = "个人标类型报告.xlsx";
                    ExportExcelPositTO perto = new ExportExcelPositTO();
                    perto.setIndexType( to.getIndexType());
                    perto.setStartTime( to.getStartTime() );
                    perto.setEndTime( to.getEndTime());
                    super.writeOutFile(response, positionIndexSetAPI.personReport(perto), fileName);
                    break;
                default:
                    fileName = "年度指标类型报告.xlsx";
                    ExportExcelYearTO yearTo = new ExportExcelYearTO();
                    yearTo.setIndexType( to.getIndexType());
                    yearTo.setStartTime( to.getStartTime() );
                    yearTo.setEndTime( to.getEndTime());
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