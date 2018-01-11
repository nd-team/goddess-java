package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.DemandAPI;
import com.bjike.goddess.businessinteraction.bo.DemandBO;
import com.bjike.goddess.businessinteraction.bo.OptionBO;
import com.bjike.goddess.businessinteraction.bo.SummationBO;
import com.bjike.goddess.businessinteraction.dto.DemandDTO;
import com.bjike.goddess.businessinteraction.excel.DemandExcel;
import com.bjike.goddess.businessinteraction.to.DemandTO;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.vo.DemandVO;
import com.bjike.goddess.businessinteraction.vo.SummationVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.businessinteraction.vo.OptionVO;
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
 * 需求信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 11:18 ]
 * @Description: [ 需求信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("demand")
public class DemandAction extends BaseFileAction {
    @Autowired
    private DemandAPI demandAPI;
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

            Boolean isHasPermission = demandAPI.guidePermission(guidePermissionTO);
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
     * @param demandDTO 需求信息
     * @des 获取所有需求信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DemandDTO demandDTO) throws ActException {
        try {
            Long count = demandAPI.countInter(demandDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个需求信息
     *
     * @param id 需求信息id
     * @return class DemandVO
     * @des 根据id获取需求信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            DemandVO demandVO = BeanTransform.copyProperties(
                    demandAPI.getOneById(id), DemandVO.class);
            return ActResult.initialize(demandVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 需求信息列表
     *
     * @param demandDTO 需求信息dto
     * @return class DemandVO
     * @des 获取所有需求信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListMoneyPerpare(DemandDTO demandDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<DemandVO> demandVOList = BeanTransform.copyProperties(
                    demandAPI.listIntera(demandDTO), DemandVO.class, request);
            return ActResult.initialize(demandVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加需求信息
     *
     * @param demandTO 需求信息to
     * @return class DemandVO
     * @des 添加需求信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({ADD.class}) DemandTO demandTO, BindingResult result) throws ActException {
        try {
            DemandBO demandBO = demandAPI.addIntera(demandTO);
            return ActResult.initialize(BeanTransform.copyProperties(demandBO, DemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑需求信息
     *
     * @param demandTO 需求信息数据bo
     * @return class DemandVO
     * @des 编辑需求信息
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({EDIT.class}) DemandTO demandTO, BindingResult result) throws ActException {
        try {
            DemandBO demandBO = demandAPI.editIntera(demandTO);
            return ActResult.initialize(BeanTransform.copyProperties(demandBO, DemandVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除需求信息
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            demandAPI.deleteIntera(id);
            return new ActResult("delete success!");
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
            List<DemandExcel> tos = ExcelUtil.excelToClazz(is, DemandExcel.class, excel);
            List<DemandTO> tocs = new ArrayList<>();
            for (DemandExcel str : tos) {
                DemandTO demandTO = BeanTransform.copyProperties(str, DemandTO.class, "feedbackDemand",
                        "feedbackDate", "initialAnalysisDate", "initialAnalysis", "projectEstimatesDate", "projectEstimates",
                        "projectEstimatesThrought", "discussDate", "discuss", "reachedCoop", "introduceContra");
                demandTO.setFeedbackDemand(stringToBool(str.getFeedbackDemand(), "是否反馈需求者"));
                demandTO.setFeedbackDate(DateUtil.dateToString(str.getFeedbackDate()));
                demandTO.setInitialAnalysisDate(DateUtil.dateToString(str.getInitialAnalysisDate()));
                demandTO.setInitialAnalysis(stringToBool(str.getInitialAnalysis(), "是否初步分析"));
                demandTO.setProjectEstimatesDate(DateUtil.dateToString(str.getProjectEstimatesDate()));
                demandTO.setProjectEstimates(stringToBool(str.getProjectEstimates(), "是否进行测算"));
                demandTO.setProjectEstimatesThrought(stringToBool(str.getProjectEstimatesThrought(), "测算是否通过"));
                demandTO.setDiscussDate(DateUtil.dateToString(str.getDiscussDate()));
                demandTO.setDiscuss(stringToBool(str.getDiscuss(), "是否进行洽谈"));
                demandTO.setReachedCoop(stringToBool(str.getReachedCoop(), "我司是否达成合作"));
                demandTO.setIntroduceContra(stringToBool(str.getIntroduceContra(), "是否介绍给别的承包商"));
                tocs.add(demandTO);
            }
            //注意序列化
            demandAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private Boolean stringToBool(String type, String name) throws ActException {
        Boolean bool = null;
        if (type != null) {
            switch (type) {
                case "是":
                    bool = true;
                    break;
                case "否":
                    bool = false;
                    break;
                default:
                    throw new ActException(name + ":类型不正确,正确类型为(是/否)");
            }
        }
        return bool;
    }

    /**
     * 导出excel
     *
     * @des 导出需求信息
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "需求信息.xlsx";
            super.writeOutFile(response, demandAPI.exportExcel(), fileName);
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
     * @des 下载模板需求信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "需求信息模板.xlsx";
            super.writeOutFile(response, demandAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 商业互动信息管理日汇总
     *
     * @param date 日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaDay(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商业互动信息管理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaWeek(year, month, week);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业互动信息管理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaMonth(year, month);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业互动信息管理季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/quarter")
    public Result summarizeQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaQuarter(year, quarter);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商业互动信息管理年度汇总
     *
     * @param year  年份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/year")
    public Result summarizeYear(Integer year,  HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaYear(year);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业互动信息管理累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = demandAPI.summaTotal(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商业互动信息管理图形展示日汇总
     *
     * @param date 日期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowDay(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);

            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业互动信息管理图形展示周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/week")
    public Result figureShowWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 商业互动信息管理图形展示月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowMonth(year, month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商业互动信息管理图形展示季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/quarter")
    public Result figureShowQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowQuarter(year, quarter);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 商业互动信息管理图形展示年度汇总
     *
     * @param year  年份
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/year")
    public Result figureShowQuarter(Integer year, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowYear(year);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 商业互动信息管理图形展示累计汇总
     * @return class OptionVO
     * @param date 截止日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = demandAPI.figureShowTotal(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}