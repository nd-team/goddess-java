package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.vo.StaffRecordsVO;
import com.bjike.goddess.assemble.api.ModuleAPI;

import com.bjike.goddess.assistance.bo.ComputerSubsidiesBO;
import com.bjike.goddess.assistance.bo.SenioritySubsidiesBO;
import com.bjike.goddess.assistance.bo.TemperatureSubsidiesBO;
import com.bjike.goddess.assistance.vo.ComputerSubsidiesVO;
import com.bjike.goddess.assistance.vo.SenioritySubsidiesVO;
import com.bjike.goddess.assistance.vo.TemperatureSubsidiesVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managementpromotion.vo.LevelShowVO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.vo.OverviewSkillLevelVO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salaryconfirm.vo.SalaryconfirmVO;
import com.bjike.goddess.salarymanage.api.SalaryInformationAPI;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.excel.SalaryInformationSetExcel;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationDeleteFileTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.salarymanage.vo.SalaryInformationVO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.secure.vo.AttachedVO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.bo.EntryOptionBO;
import com.bjike.goddess.staffentry.vo.EntryOptionVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 薪资管理薪资资料
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-07-31 01:45 ]
 * @Description: [ 薪资管理薪资资料]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("salaryinformation")
public class SalaryInformationAction extends BaseFileAction {
    @Autowired
    private SalaryInformationAPI salaryInformationAPI;

    @Autowired
    private FileAPI fileAPI;

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

            Boolean isHasPermission = salaryInformationAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 查询条件
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/page/list")
    public Result pageList(SalaryInformationDTO dto) throws ActException {
        try {
            List<SalaryInformationBO> boList = salaryInformationAPI.pageList(dto);
            List<SalaryInformationVO> voList = BeanTransform.copyProperties(boList, SalaryInformationVO.class, true);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加薪资资料
     *
     * @param to 添加条件
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) SalaryInformationTO to, BindingResult bindingResult) throws ActException {
        try {
            SalaryInformationBO bo = salaryInformationAPI.addSalaryInformation(to);
            SalaryInformationVO vo = BeanTransform.copyProperties(bo, SalaryInformationVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 修改条件
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) SalaryInformationTO to) throws ActException {
        try {
            SalaryInformationBO bo = salaryInformationAPI.editSalaryInformation(to);
            SalaryInformationVO vo = BeanTransform.copyProperties(bo, SalaryInformationVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 薪资资料id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            salaryInformationAPI.deleteSalaryInformation(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<SalaryInformationSetExcel> tos = ExcelUtil.excelToClazz(is, SalaryInformationSetExcel.class, excel);
            List<SalaryInformationTO> toList = BeanTransform.copyProperties(tos, SalaryInformationTO.class);
            salaryInformationAPI.leadExcel(toList);
            return new ActResult("导入成功");
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
    public Result exportExcel(ExportSalaryInformationTO to, HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资资料.xlsx";
            super.writeOutFile(response, salaryInformationAPI.exportExcel(to), fileName);
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
     * @des 下载模板项目签订与立项
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "薪资资料模板.xlsx";
            super.writeOutFile(response, salaryInformationAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
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
            String path = "/contract/" + id;
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
            String path = "/contract/" + id;
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
    public Result delFile(@Validated(SalaryInformationDeleteFileTO.TestDEL.class) SalaryInformationDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("删除成功");
    }

    /**
     * 列表总条数
     *
     * @param dto 分页查询条件
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SalaryInformationDTO dto) throws ActException {
        try {
            Long count = salaryInformationAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据员工编号来查询管理等级
     *
     * @param id
     * @return class LevelShowVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/level/{id}")
    public Result findByEmployeeId(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            LevelShowBO showBO = salaryInformationAPI.findEmployeeId(id);
            return ActResult.initialize(BeanTransform.copyProperties(showBO, LevelShowVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工编号来查询入职基本信息
     *
     * @param id
     * @return class EntryOptionVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/entryBasic/{id}")
    public Result getByEmpNumber(@PathVariable String id) throws ActException {
        try {
            EntryOptionBO entryOptionBO = salaryInformationAPI.getByEmpNumber(id);
            EntryOptionVO entryOptionVO = BeanTransform.copyProperties(entryOptionBO, EntryOptionVO.class);
            return ActResult.initialize(entryOptionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id来查询单个薪资资料
     *
     * @param id
     * @return class SalaryInformationVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one/{id}")
    public Result findOne(@PathVariable String id) throws ActException {
        try {
            SalaryInformationBO bo = salaryInformationAPI.findOne(id);
            SalaryInformationVO vo = BeanTransform.copyProperties(bo, SalaryInformationVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工编号查询入职时间和离职日期和身份证号码和银行卡号
     *
     * @param employeeNumber
     * @return class StaffRecordsVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/staff")
    public Result findStaff(String employeeNumber) throws ActException {
        try {
            StaffRecordsBO bo = salaryInformationAPI.findStaff(employeeNumber);
            StaffRecordsVO vo = BeanTransform.copyProperties(bo, StaffRecordsVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工编号id查找转正时间
     *
     * @param employeeId 员工编号id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/positiveDate")
    public Result findPositiveDate(String employeeId) throws ActException {
        try {
            String time = salaryInformationAPI.findPositiveDate(employeeId);
            return ActResult.initialize(time);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据员工姓名查找技能等级获取时间
     *
     * @param employeeName
     * @return class OverviewSkillLevelVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/skill")
    public Result findSkill(String employeeName) throws ActException {
        try {
            OverviewSkillLevelBO bo = salaryInformationAPI.findSkill(employeeName);
            OverviewSkillLevelVO vo = BeanTransform.copyProperties(bo, OverviewSkillLevelVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据计薪周期查询电脑补助情况
     *
     * @param dto 查询条件
     * @return class ComputerSubsidiesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/computer")
    public Result findComputer(SalaryInformationDTO dto) throws ActException {
        try {
            ComputerSubsidiesBO bo = salaryInformationAPI.findComputerAssist(dto);
            ComputerSubsidiesVO vo = BeanTransform.copyProperties(bo, ComputerSubsidiesVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据计薪周期查询高温补助情况
     *
     * @param dto 查询条件
     * @return class TemperatureSubsidiesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/hot")
    public Result findHot(SalaryInformationDTO dto) throws ActException {
        try {
            TemperatureSubsidiesBO bo = salaryInformationAPI.findHotAssist(dto);
            TemperatureSubsidiesVO vo = BeanTransform.copyProperties(bo, TemperatureSubsidiesVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 根据计薪周期查询住宿补助情况
//     *
//     * @param dto 查询条件
//     * @return class HouseAssistVO
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/find/house")
//    public Result findHouse(SalaryInformationDTO dto) throws ActException {
//        try {
//            HouseAssistBO bo = salaryInformationAPI.findHouseAssist(dto);
//            HouseAssistVO vo = BeanTransform.copyProperties(bo, HouseAssistVO.class);
//            return ActResult.initialize(vo);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 根据员工姓名工龄补助情况
     *
     * @param dto 查询条件
     * @return class SenioritySubsidiesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/age")
    public Result findAge(SalaryInformationDTO dto) throws ActException {
        try {
            SenioritySubsidiesBO bo = salaryInformationAPI.findAgeAssist(dto);
            SenioritySubsidiesVO vo = BeanTransform.copyProperties(bo, SenioritySubsidiesVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据根据计薪周期和员工姓名获取旷工扣款和事病假扣款
     *
     * @param dto 查询条件
     * @return class SalaryconfirmVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/confirm")
    public Result findSalaryConfirm(SalaryInformationDTO dto) throws ActException {
        try {
            SalaryconfirmBO bo = salaryInformationAPI.findSalaryConfirm(dto);
            SalaryconfirmVO vo = BeanTransform.copyProperties(bo, SalaryconfirmVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据根据员工姓名查看扣社保情况
     *
     * @param dto 查询条件
     * @return class AttachedVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/attached")
    public Result findAttached(SalaryInformationDTO dto) throws ActException {
        try {
            AttachedBO bo = salaryInformationAPI.findAttached(dto);
            AttachedVO vo = BeanTransform.copyProperties(bo, AttachedVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}