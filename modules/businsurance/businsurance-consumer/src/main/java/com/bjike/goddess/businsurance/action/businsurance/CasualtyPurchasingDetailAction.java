package com.bjike.goddess.businsurance.action.businsurance;

import com.bjike.goddess.businsurance.api.CasualtyPurchasingDetailAPI;
import com.bjike.goddess.businsurance.bo.CasualtyPurchasingDetailBO;
import com.bjike.goddess.businsurance.bo.SummaryBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.excel.CasualtyPurchasingDetailExcel;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingDetailTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.businsurance.vo.CasualtyPurchasingDetailVO;
import com.bjike.goddess.businsurance.vo.SummaryVO;
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
 * 团体意外险购买详情
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("casualtypurchasingdetail")
public class CasualtyPurchasingDetailAction extends BaseFileAction {
    @Autowired
    private CasualtyPurchasingDetailAPI casualtyPurchasingDetailAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = casualtyPurchasingDetailAPI.guidePermission(guidePermissionTO);
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
     * 总条数
     *
     * @param casualtyPurchasingDetailDTO 团体意外险购买详情dto
     * @des 获取所有团体意外险购买详情总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws ActException {
        try {
            Long count = casualtyPurchasingDetailAPI.countDetail(casualtyPurchasingDetailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个团体意外险购买详情
     *
     * @param id id
     * @return class CasualtyPurchasingDetailVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/detail/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            CasualtyPurchasingDetailBO bo = casualtyPurchasingDetailAPI.getOneDetail(id);
            CasualtyPurchasingDetailVO vo = BeanTransform.copyProperties(bo, CasualtyPurchasingDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 团体意外险购买详情列表
     *
     * @param casualtyPurchasingDetailDTO 团体意外险购买名单dto
     * @return class CasualtyPurchasingDetailVO
     * @des 获取所有团体意外险购买详情
     * @version v1
     */
    @GetMapping("v1/listDetail")
    public Result findList(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO, BindingResult bindingResult) throws ActException {
        try {
            List<CasualtyPurchasingDetailVO> casualtyPurchasingListVOS = BeanTransform.copyProperties(
                    casualtyPurchasingDetailAPI.listDetail(casualtyPurchasingDetailDTO), CasualtyPurchasingDetailVO.class, true);
            return ActResult.initialize(casualtyPurchasingListVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param casualtyPurchasingDetailTO 团体意外险购买详情数据to
     * @return class CasualtyPurchasingDetailVO
     * @des 添加 团体意外险购买详情
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CasualtyPurchasingDetailTO casualtyPurchasingDetailTO, BindingResult bindingResult) throws ActException {
        try {
            CasualtyPurchasingDetailBO casualtyPurchasingDetailBO = casualtyPurchasingDetailAPI.addDetail(casualtyPurchasingDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(casualtyPurchasingDetailBO, CasualtyPurchasingDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param casualtyPurchasingDetailTO 团体意外险购买详情数据bo
     * @return class CasualtyPurchasingDetailVO
     * @des 编辑团体意外险购买详情
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws ActException {
        try {
            CasualtyPurchasingDetailBO casualtyPurchasingDetailBO = casualtyPurchasingDetailAPI.editDetail(casualtyPurchasingDetailTO);
            return ActResult.initialize(BeanTransform.copyProperties(casualtyPurchasingDetailBO, CasualtyPurchasingDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
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
            List<CasualtyPurchasingDetailExcel> tos = ExcelUtil.excelToClazz(is, CasualtyPurchasingDetailExcel.class, excel);
            List<CasualtyPurchasingDetailTO> tocs = new ArrayList<>();
            for (CasualtyPurchasingDetailExcel str : tos) {
                CasualtyPurchasingDetailTO casualtyPurchasingDetailTO = BeanTransform.copyProperties(str, CasualtyPurchasingDetailTO.class, "entryDate", "stopBuyTime");
                casualtyPurchasingDetailTO.setEntryDate(String.valueOf(str.getEntryDate()));
                casualtyPurchasingDetailTO.setStopBuyTime(String.valueOf(str.getStopBuyTime()));
                tocs.add(casualtyPurchasingDetailTO);
            }
            //注意序列化
            casualtyPurchasingDetailAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出团体意外险购买详情
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "导出团体意外险购买详情.xlsx";
            super.writeOutFile(response, casualtyPurchasingDetailAPI.exportExcel(), fileName);
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
     * @des 下载模板电脑补助
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "导出团体意外险购买详情模板.xlsx";
            super.writeOutFile(response, casualtyPurchasingDetailAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 获取所有的员工编号
     *
     * @des 获取所有的员工编号
     * @version v1
     */
    @GetMapping("v1/findEmpNo")
    public Result findEmpNo() throws ActException {
        try {
            List<String> empNos = casualtyPurchasingDetailAPI.findEmpNo();
            return ActResult.initialize(empNos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据员工编号获取信息
     *
     * @return class CasualtyPurchasingDetailBO
     * @des 根据员工编号获取信息
     * @version v1
     */
    @GetMapping("v1/findByEmpNo")
    public Result findByEmpNo(@RequestParam String empNo) throws ActException {
        try {
            CasualtyPurchasingDetailBO casualtyPurchasingDetailBO = casualtyPurchasingDetailAPI.findByEmpNo(empNo);
            return ActResult.initialize(casualtyPurchasingDetailBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险日汇总
     *
     * @param date 日期
     * @return class SummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            List<SummaryBO> boList = casualtyPurchasingDetailAPI.summaDay(date);
            List<SummaryVO> voList = BeanTransform.copyProperties(boList, SummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeDay(Integer year, Integer month, Integer week, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            List<SummaryBO> boList = casualtyPurchasingDetailAPI.summaWeek(year, month, week);
            List<SummaryVO> voList = BeanTransform.copyProperties(boList, SummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            List<SummaryBO> boList = casualtyPurchasingDetailAPI.summaMonth(year, month);
            List<SummaryVO> voList = BeanTransform.copyProperties(boList, SummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业保险累计汇总
     *
     * @param date 截止日期
     * @return class SummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeMonth(String date, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            List<SummaryBO> boList = casualtyPurchasingDetailAPI.summaTotal(date);
            List<SummaryVO> voList = BeanTransform.copyProperties(boList, SummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}