package com.bjike.goddess.fundrecords.action.fundrecords;

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
import com.bjike.goddess.fundrecords.api.FundRecordAPI;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.entity.FundRecord;
import com.bjike.goddess.fundrecords.excel.FundRecordExcel;
import com.bjike.goddess.fundrecords.excel.SonPermissionObject;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.CompetitorDeleteFileTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.to.GuidePermissionTO;
import com.bjike.goddess.fundrecords.vo.*;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.OpinionVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Area;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金流水
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fundrecord")
public class FundRecordAct extends BaseFileAction {

    @Autowired
    private FundRecordAPI fundRecordAPI;
    @Autowired
    private DepartmentDetailAPI departmentDetailAPI;
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

            List<SonPermissionObject> hasPermissionList = fundRecordAPI.sonPermission();
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

            Boolean isHasPermission = fundRecordAPI.guidePermission(guidePermissionTO);
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
     * 地区列表查询
     *
     * @return class AreaVO
     * @version v1
     */
    @GetMapping("v1/areas")
    public Result allAreas(HttpServletRequest request) throws ActException {
        try {
            List<AreaVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findArea(), AreaVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组或部门列表查询
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/groups")
    public Result allGroups(HttpServletRequest request) throws ActException {
        try {
            List<OpinionVO> voList = BeanTransform.copyProperties(departmentDetailAPI.findThawOpinion(), OpinionVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目列表查询
     *
     * @return class OpinionVO
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result allProjects(HttpServletRequest request){
        //todo 需求尚未确定项目数据来源，因此项目下拉列表可编辑
        return null;
    }

    /**
     * 新增资金流水
     *
     * @param to 资金流水信息
     * @return class FundRecordVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) FundRecordTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO voList = BeanTransform.copyProperties(fundRecordAPI.add(to), FundRecordVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金流水
     *
     * @param to 资金流水信息
     * @return class FundRecordVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) FundRecordTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO vo = BeanTransform.copyProperties(fundRecordAPI.edit(to), FundRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param id      id
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/upload/{id}")
    public Result upload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String path = "/fundrecord/" + id;
            List<InputStream> inputStreams = super.getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/files/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/fundrecord/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/download")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = org.apache.commons.lang3.StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("下载成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/delfile")
    public Result delFile(@Validated(CompetitorDeleteFileTO.TestDEL.class) CompetitorDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/lead")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<FundRecordExcel> tos = ExcelUtil.excelToClazz(is, FundRecordExcel.class, excel);
            List<FundRecordTO> toList = BeanTransform.copyProperties(tos, FundRecordTO.class);
            fundRecordAPI.leadExcel(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出Excel
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportExcel(String startDate, String endDate, HttpServletResponse response) throws ActException {
        try {
            String fileName = "资金流水.xlsx";
            super.writeOutFile(response, fundRecordAPI.exportExcel(startDate, endDate), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 导出Excel模板
     *
     * @version v1
     */
    @GetMapping("v1/module")
    public Result exportModule(HttpServletResponse response) throws ActException {
        try {
            String fileName = "资金流水模板.xlsx";
            super.writeOutFile(response, fundRecordAPI.exportExcelModule(), fileName);
            return new ActResult(0, "导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 删除资金流水
     *
     * @param id 资金流水ID
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fundRecordAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class FundRecordVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(FundRecordDTO dto) throws ActException {
        try {
            List<FundRecordVO> voList = BeanTransform.copyProperties(fundRecordAPI.findList(dto), FundRecordVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FundRecordDTO dto) throws ActException {
        try {
            Long count = fundRecordAPI.findCount(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询资金流水
     *
     * @param id 资金流水id
     * @return class FundRecordVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FundRecordVO vo = BeanTransform.copyProperties(fundRecordAPI.findById(id), FundRecordVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class MonthCollectVO
     * @version v1
     */
    @GetMapping("v1/month")
    public Result month(@RequestParam Integer year, @RequestParam Integer month) throws ActException {
        try {
            MonthCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.month(year, month), MonthCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 条件汇总
     *
     * @param to 汇总条件
     * @return class ConditionCollectVO
     * @version v1
     */
    @GetMapping("v1/condition")
    public Result condition(@Validated({CollectTO.Collect.class}) CollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<ConditionCollectVO> vo = BeanTransform.copyProperties(fundRecordAPI.condition(to), ConditionCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区分析
     *
     * @param year  年份
     * @param month 月份
     * @param area  地区
     * @return class AreaAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/area")
    public Result areaAnalyze(@RequestParam Integer year, @RequestParam Integer month, String area, HttpServletRequest request) throws ActException {
        try {
            List<AreaAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.areaAnalyze(year, month, area), AreaAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组分析
     *
     * @param year  年份
     * @param month 月份
     * @param group 地区
     * @return class GroupAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/group")
    public Result groupAnalyze(@RequestParam Integer year, @RequestParam Integer month, String group, HttpServletRequest request) throws ActException {
        try {
            List<GroupAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.groupAnalyze(year, month, group), GroupAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目分析
     *
     * @param year    年份
     * @param month   月份
     * @param project 地区
     * @return class ProjectAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/project")
    public Result projectAnalyze(@RequestParam Integer year, @RequestParam Integer month, String project, HttpServletRequest request) throws ActException {
        try {
            List<ProjectAnalyzeVO> voList = BeanTransform.copyProperties(fundRecordAPI.projectAnalyze(year, month, project), ProjectAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 账户来源下拉值
     *
     * @version v1
     */
    @GetMapping("v1/sourceAcount")
    public Result sourceAcount( HttpServletRequest request) throws ActException {
        try {
            List<String> sourceAcount = fundRecordAPI.sourceAccountValue();
            return ActResult.initialize(sourceAcount);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 地区下拉值
     *
     * @version v1
     */
    @GetMapping("v1/areaValue")
    public Result areaValue( HttpServletRequest request) throws ActException {
        try {
            List<String> areas = fundRecordAPI.findAllArea();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目组下拉值
     *
     * @version v1
     */
    @GetMapping("v1/projectGroup/value")
    public Result projectGroupVa( HttpServletRequest request) throws ActException {
        try {
            List<String> projectGroups = fundRecordAPI.findAllProjectGroup();
            return ActResult.initialize(projectGroups);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目名称下拉值
     *
     * @version v1
     */
    @GetMapping("v1/projectName/value")
    public Result projectNameVa( HttpServletRequest request) throws ActException {
        try {
            List<String> projectNames = fundRecordAPI.findAllProjectName();
            return ActResult.initialize(projectNames);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总lijuntao
     *
     * @param year  年份
     * @param month 月份
     * @return class MonthCollectVO
     * @version v1
     */
    @GetMapping("v1/monthColl")
    public Result monthColl(@RequestParam Integer year, @RequestParam Integer month) throws ActException {
        try {
            MonthCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.monthSumma(year, month), MonthCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总lijuntao
     *
     * @param year 年份
     * @param month 月份
     * @param area 地区
     * @return class ConditionCollectVO
     * @version v1
     */
    @GetMapping("v1/areaSumma")
    public Result areaSumma(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String area) throws ActException {
        try {
            ConditionCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.areaSumma(year,month,area), ConditionCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总lijuntao
     *
     * @param year 年份
     * @param month 月份
     * @param projectGroup 项目组
     * @return class ConditionCollectVO
     * @version v1
     */
    @GetMapping("v1/projectSumma")
    public Result projectSumma(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String projectGroup) throws ActException {
        try {
            ConditionCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.projectSumma(year,month,projectGroup), ConditionCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目名称汇总lijuntao
     *
     * @param year 年份
     * @param month 月份
     * @param projectName 项目名称
     * @return class ConditionCollectVO
     * @version v1
     */
    @GetMapping("v1/projectNameSumma")
    public Result projectNameSumma(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String projectName) throws ActException {
        try {
            ConditionCollectVO vo = BeanTransform.copyProperties(fundRecordAPI.projectNameSumma(year,month,projectName), ConditionCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 地区分析lijuntao
     *
     * @param year  年份
     * @param month 月份
     * @param area  地区
     * @return class AreaAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/areaAnalysis")
    public Result areaAnalysis(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String area, HttpServletRequest request) throws ActException {
        try {
            AreaAnalyzeVO voList = BeanTransform.copyProperties(fundRecordAPI.areaAnalysis(year, month, area), AreaAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目组分析lijuntao
     *
     * @param year  年份
     * @param month 月份
     * @param group 项目组
     * @return class GroupAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/projectAnalysis")
    public Result projectAnalysis(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String group, HttpServletRequest request) throws ActException {
        try {
            GroupAnalyzeVO vo = BeanTransform.copyProperties(fundRecordAPI.projectAnalysis(year, month, group), GroupAnalyzeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目名称分析lijuntao
     *
     * @param year    年份
     * @param month   月份
     * @param project 项目名称
     * @return class ProjectAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/projectNameAnalysis")
    public Result projectNameAnalysis(@RequestParam Integer year, @RequestParam Integer month, @RequestParam String project, HttpServletRequest request) throws ActException {
        try {
            ProjectAnalyzeVO vo = BeanTransform.copyProperties(fundRecordAPI.projectNameAnalysis(year, month, project), ProjectAnalyzeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入资金流水lijuntao
     *
     * @version v1
     */
    @GetMapping("v1/exportFund")
    public Result exportFund(HttpServletRequest request) throws ActException {
        try {
             fundRecordAPI.exportFund();
            return new ActResult("export success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据账户来源导出Excel
     *
     * @param dataSource 账户来源
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export/ljt")
    public Result exportExcel(@RequestParam String dataSource, HttpServletResponse response) throws ActException {
        try {
            String fileName = "资金流水.xlsx";
            super.writeOutFile(response, fundRecordAPI.exportExcelLJT(dataSource), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
    /**
     * 获取所有的账户来源
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/allDataSource")
    public Result allDataSource( HttpServletRequest request) throws ActException {
        try {
            List<String> dataSources = fundRecordAPI.findAllDataSource();
            return  ActResult.initialize(dataSources);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}