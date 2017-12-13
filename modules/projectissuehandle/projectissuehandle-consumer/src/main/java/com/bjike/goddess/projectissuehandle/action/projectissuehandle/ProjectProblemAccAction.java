package com.bjike.goddess.projectissuehandle.action.projectissuehandle;

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
import com.bjike.goddess.projectissuehandle.api.CommunicationFormworkAPI;
import com.bjike.goddess.projectissuehandle.api.ProjectProblemAccAPI;
import com.bjike.goddess.projectissuehandle.bo.OptionBO;
import com.bjike.goddess.projectissuehandle.bo.ProjectProblemAccBO;
import com.bjike.goddess.projectissuehandle.bo.ProjectSummaryBO;
import com.bjike.goddess.projectissuehandle.dto.ProjectProblemAccDTO;
import com.bjike.goddess.projectissuehandle.excel.ProjectProblemAccExcel;
import com.bjike.goddess.projectissuehandle.excel.SonPermissionObject;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.ProjectProblemAccTO;
import com.bjike.goddess.projectissuehandle.vo.CommunicationFormworkVO;
import com.bjike.goddess.projectissuehandle.vo.OptionVO;
import com.bjike.goddess.projectissuehandle.vo.ProjectProblemAccVO;
import com.bjike.goddess.projectissuehandle.vo.ProjectSummaryVO;
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
 * 项目中问题受理和处理
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("projectproblemacc")
public class ProjectProblemAccAction extends BaseFileAction{
    @Autowired
    private ProjectProblemAccAPI projectProblemAccAPI;
    @Autowired
    private CommunicationFormworkAPI communicationFormworkAPI;

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
    public Result i() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("propermission");
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

            List<SonPermissionObject> hasPermissionList = projectProblemAccAPI.sonPermission();
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

            Boolean isHasPermission = projectProblemAccAPI.guidePermission(guidePermissionTO);
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
     * 项目中问题受理和处理列表总条数
     *
     * @param projectProblemAccDTO 项目中问题受理和处理dto
     * @des 获取所有项目中问题受理和处理总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ProjectProblemAccDTO projectProblemAccDTO) throws ActException {
        try {
            Long count = projectProblemAccAPI.countProjectProblem(projectProblemAccDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个项目中问题受理和处理
     *
     * @param id
     * @return class ProjectProblemAccVO
     * @des 获取一个项目中问题受理和处理
     * @version v1
     */
    @GetMapping("v1/problem/{id}")
    public Result problem(@PathVariable String id) throws ActException {
        try {
            ProjectProblemAccBO projectProblemAccBO = projectProblemAccAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(projectProblemAccBO, ProjectProblemAccVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理列表
     *
     * @param projectProblemAccDTO 项目中问题受理和处理dto
     * @return class ProjectProblemAccVO
     * @des 获取所有项目中问题受理和处理
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ProjectProblemAccDTO projectProblemAccDTO, HttpServletRequest request) throws ActException {
        try {
            List<ProjectProblemAccVO> problemAcceptVOS = BeanTransform.copyProperties
                    (projectProblemAccAPI.findListProjectProblem(projectProblemAccDTO), ProjectProblemAccVO.class, request);
            return ActResult.initialize(problemAcceptVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目中问题受理和处理
     *
     * @param projectProblemAccTO 项目中问题受理和处理数据to
     * @return class ProjectProblemAccVO
     * @des 添加项目中问题受理和处理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) ProjectProblemAccTO projectProblemAccTO, BindingResult result) throws ActException {
        try {
            ProjectProblemAccBO projectProblemAccBO = projectProblemAccAPI.insertProjectProblem(projectProblemAccTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectProblemAccBO, ProjectProblemAccVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目中问题受理和处理
     *
     * @param projectProblemAccTO 项目中问题受理和处理数据to
     * @return class ProjectProblemAccVO
     * @des 编辑项目执行中的问题受理
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editProblemAccept(@Validated(EDIT.class) ProjectProblemAccTO projectProblemAccTO, BindingResult result) throws ActException {
        try {
            ProjectProblemAccBO projectProblemAccBO = projectProblemAccAPI.editProjectProblem(projectProblemAccTO);
            return ActResult.initialize(BeanTransform.copyProperties(projectProblemAccBO, ProjectProblemAccVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目中问题受理和处理
     *
     * @param id 用户id
     * @des 根据用户id删除项目中问题受理和处理
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteProblemAccept(@PathVariable String id) throws ActException {
        try {
            projectProblemAccAPI.removeProjectProblem(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 解冻项目中问题受理和处理
     *
     * @param id 项目中问题受理和处理唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable(value = "id") String id) throws ActException {
        try {
            projectProblemAccAPI.thaw(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结项目中问题受理和处理
     *
     * @param id 项目中问题受理和处理唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable(value = "id") String id) throws ActException {
        try {
            projectProblemAccAPI.congeal(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导出Excel
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel( HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目问题受理和处理.xlsx";
            super.writeOutFile(response, projectProblemAccAPI.exportExcel(), fileName);
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
     * @des 下载市场活动申请模板
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "项目问题受理和处理模板.xlsx";
            super.writeOutFile(response, projectProblemAccAPI.templateExport(), fileName);
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
            List<ProjectProblemAccExcel> tos = ExcelUtil.excelToClazz(is, ProjectProblemAccExcel.class, excel);
            List<ProjectProblemAccTO> tocs = new ArrayList<>();
            for (ProjectProblemAccExcel str : tos) {
                ProjectProblemAccTO projectProblemAccTO = BeanTransform.copyProperties(str, ProjectProblemAccTO.class
                        , "getTime", "expectedTime", "problemFollowComTime", "planTime","problemActually","closeLoop","needCoordinate","communicated","isOutward");
                projectProblemAccTO.setGetTime(str.getGetTime().toString());
                projectProblemAccTO.setExpectedTime(str.getExpectedTime().toString());
                projectProblemAccTO.setProblemFollowComTime(str.getProblemFollowComTime().toString());
                projectProblemAccTO.setPlanTime(str.getPlanTime().toString());
                projectProblemAccTO.setProblemActually(str.getProblemActually().toString());
                projectProblemAccTO.setCloseLoop(convertBool("是否闭环",str.getCloseLoop()));
                projectProblemAccTO.setNeedCoordinate(convertBool("是否需要协调",str.getNeedCoordinate()));
                projectProblemAccTO.setCommunicated(convertBool("是否通报",str.getCommunicated()));
                projectProblemAccTO.setOutward(convertBool("是否对外",str.getIsOutward()));
                tocs.add(projectProblemAccTO);
            }
            //注意序列化
            projectProblemAccAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    private Boolean convertBool(String name ,String type) throws ActException {
        Boolean bool = null;
        if (null != type) {
            if (type.equals("是")) {
                bool = true;
            } else if (type.equals("否")) {
                bool = false;
            } else {
                throw new ActException(name+"填写不正确,导入失败,正确填写方式（是/否）");
            }
        }
        return bool;
    }
    /**
     * 根据分类获取信息
     *
     * @param classification 分类
     * @return class CommunicationFormworkVO
     * @des 获取所有各类沟通模板
     * @version v1
     */
    @GetMapping("v1/findByClassification")
    public Result findByClassification(String classification, HttpServletRequest request) throws ActException {
        try {
            CommunicationFormworkVO communicationFormworkVO = BeanTransform.copyProperties
                    (communicationFormworkAPI.findByClassification(classification), CommunicationFormworkVO.class, request);
            return ActResult.initialize(communicationFormworkVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有的分类
     *
     * @des 获取所有的分类
     * @version v1
     */
    @GetMapping("v1/findAll/classification")
    public Result findAllClassification() throws ActException {
        try {
            List<String> classification = communicationFormworkAPI.findAllType();
            return ActResult.initialize(classification);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通报
     *
     * @version v1
     */
    @GetMapping("v1/notification")
    public Result notification(@RequestParam String classification,@RequestParam String emailModule) throws ActException {
        try {
            communicationFormworkAPI.notification(classification, emailModule);
            return new ActResult("notification success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理日汇总
     *
     * @param date 日期
     * @return class ProjectSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<ProjectSummaryBO> boList = projectProblemAccAPI.summaDay(date);
            List<ProjectSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目中问题受理和处理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class ProjectSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/week")
    public Result summarizeWeek(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            List<ProjectSummaryBO> boList = projectProblemAccAPI.summaWeek(year, month, week);
            List<ProjectSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class ProjectSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<ProjectSummaryBO> boList = projectProblemAccAPI.summaMonth(year, month);
            List<ProjectSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理季度汇总
     *
     * @param year  年份
     * @param quarter 季度
     * @return class ProjectSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/quarter")
    public Result summarizeQuarter(Integer year, Integer quarter, HttpServletRequest request) throws ActException {
        try {
            List<ProjectSummaryBO> boList = projectProblemAccAPI.summaQuarter(year, quarter);
            List<ProjectSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理累计汇总
     *
     * @param date 截止日期
     * @return class ProjectSummaryVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/summarize/total")
    public Result summarizeTotal(String date, HttpServletRequest request) throws ActException {
        try {
            List<ProjectSummaryBO> boList = projectProblemAccAPI.summaTotal(date);
            List<ProjectSummaryVO> voList = BeanTransform.copyProperties(boList, ProjectSummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目中问题受理和处理图形展示日汇总
     *
     * @param date 日期
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = projectProblemAccAPI.figureShowDay(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);

            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理图形展示周汇总
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
            OptionBO optionBO = projectProblemAccAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目中问题受理和处理图形展示月汇总
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
            OptionBO optionBO = projectProblemAccAPI.figureShowMonth(year, month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目中问题受理和处理图形展示季度汇总
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
            OptionBO optionBO = projectProblemAccAPI.figureShowQuarter(year, quarter);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 项目中问题受理和处理图形展示累计汇总
     * @return class OptionVO
     * @param date 截止日期
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/total")
    public Result figureShowTotal(String date, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = projectProblemAccAPI.figureShowTotal(date);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}