package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.StaffRecordsAPI;
import com.bjike.goddess.archive.bo.OptionBO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.archive.bo.StaffRecordsCollectBO;
import com.bjike.goddess.archive.bo.StaffRecordsDataBO;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords1Excel;
import com.bjike.goddess.archive.entity.StaffRecordsExcel;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.StaffRecords1ExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsExcelTO;
import com.bjike.goddess.archive.to.StaffRecordsTO;
import com.bjike.goddess.archive.vo.*;
import com.bjike.goddess.common.api.constant.RpcCommon;
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
import com.bjike.goddess.qualifications.api.QualificationsCollectAPI;
import com.bjike.goddess.secure.api.EmployeeSecureAPI;
import com.bjike.goddess.secure.vo.SecureVO;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.vo.EntryRegisterVO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 员工档案
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 10:32 ]
 * @Description: [ 员工档案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffrecords")
public class StaffRecordsAct extends BaseFileAction {

    @Autowired
    private StaffRecordsAPI staffRecordsAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private QualificationsCollectAPI qualificationsCollectAPI;
    @Autowired
    private EmployeeSecureAPI employeeSecureAPI;

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

            Boolean isHasPermission = staffRecordsAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StaffRecordsTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            staffRecordsAPI.add(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @version v1
     */
    @PutMapping("v1/edit/{id}")
    public Result edit(@Validated(EDIT.class) StaffRecordsTO to, BindingResult bindingResult) throws ActException {
        try {
            staffRecordsAPI.edit(to);
            return ActResult.initialize("EDIT SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对象
     *
     * @version v1
     */
    @GetMapping("v1/findEntity/{id}")
    public Result findEntity(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            StaffRecordsBO bo = staffRecordsAPI.findEntity(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffRecordsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            staffRecordsAPI.delete(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 员工档案
     * @des 导出员工档案
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(StaffRecordsDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "员工档案.xlsx";
            super.writeOutFile(response, staffRecordsAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<StaffRecordsExcel> tos = ExcelUtil.excelToClazz(is, StaffRecordsExcel.class, excel);
            List<StaffRecordsExcelTO> toList = BeanTransform.copyProperties(tos, StaffRecordsExcelTO.class);
            staffRecordsAPI.upload(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 上传附件
     *
     * @param id 员工档案数据id
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadEnclosure/{id}")
    public Result uploadEnclosure(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/staffrecords/" + id;
            List<InputStream> inputStreams = getInputStreams(request, path);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 员工档案数据id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/archive/staffrecords/" + id;
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
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param paths 多文件信息路径
     * @version v1
     */
    @PostMapping("v1/deleteFile")
    public Result delFile(@RequestParam String[] paths, HttpServletRequest request) throws ActException {
        try {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), paths);
            return new ActResult("delFile success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 员工档案数据传输对象
     * @return class StaffRecordsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(StaffRecordsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.maps(dto), StaffRecordsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return class StaffRecordsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.getById(id), StaffRecordsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取在职人员总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(StaffRecordsDTO dto) throws ActException {
        try {
            return ActResult.initialize(staffRecordsAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取员工档案的员工姓名
     *
     * @return class StaffNameVO
     * @version v1
     */
    @GetMapping("v1/getName")
    public Result getName() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.getName(), StaffNameVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 在职员工基本信息
     *
     * @return class EntryRegisterVO
     * @version v1
     */
    @GetMapping("v1/employee/list")
    public Result listEmployee() throws ActException {
        try {
//            staffRecordsAPI.listEmployee(dto);
            List<EntryRegisterBO> entryBasicInfoBOList = entryRegisterAPI.list();
            return ActResult.initialize(BeanTransform.copyProperties(entryBasicInfoBOList, EntryRegisterVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出导入的excel模板
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/templateExcel")
    public Result templateExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "excel模板下载.xlsx";
            super.writeOutFile(response, staffRecordsAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 离职人员信息导入
     *
     * @version v1
     */
    @PostMapping("v1/dimission/upload")
    public Result dimissionUpload(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<StaffRecords1Excel> tos = ExcelUtil.excelToClazz(is, StaffRecords1Excel.class, excel);
            List<StaffRecords1ExcelTO> toList = BeanTransform.copyProperties(tos, StaffRecords1ExcelTO.class);
            staffRecordsAPI.dimissionUpload(toList);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 离职人员信息列表
     *
     * @param dto 员工档案数据传输对象
     * @return class StaffRecords1VO
     * @version v1
     */
    @GetMapping("v1/dimission/maps")
    public Result dimissionMaps(StaffRecordsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(staffRecordsAPI.dimissionMaps(dto), StaffRecords1VO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取离职人员总条数
     *
     * @version v1
     */
    @GetMapping("v1/dimission/count")
    public Result count(StaffRecordsDTO dto) throws ActException {
        try {
            return ActResult.initialize(staffRecordsAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出导入的excel离职模板
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dimission/templateExcel")
    public Result templateDimissionExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "excel离职模板下载.xlsx";
            super.writeOutFile(response, staffRecordsAPI.templateDimissionExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @version v1
     */
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            staffRecordsAPI.freeze(id);
            return ActResult.initialize("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            staffRecordsAPI.thaw(id);
            return ActResult.initialize("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取资质认证证书
     *
     * @version v1
     */
    @GetMapping("v1/find/qualifications")
    public Result findAllQualifications() throws ActException {
        try {
            return ActResult.initialize(qualificationsCollectAPI.findAllQualifications());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据姓名获取是否购买社保社保所属类型社保所属公司
     *
     * @return class SecureVO
     * @version v1
     */
    @GetMapping("v1/findSecureBO")
    public Result findSecureBO(@RequestParam String name) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(employeeSecureAPI.findSecureBO(name), SecureVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工信息管理进度日汇总
     *
     * @return class StaffRecordsCollectVO
     * @version v1
     */
    @GetMapping("v1/day/collect")
    public Result dayCollect(@RequestParam String day, HttpServletRequest request) throws ActException {
        try {
            List<StaffRecordsCollectBO> bos = staffRecordsAPI.dayCollect(day);
            return ActResult.initialize(BeanTransform.copyProperties(bos, StaffRecordsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周员工信息管理汇总
     *
     * @return class StaffRecordsCollectVO
     * @version v1
     */
    @GetMapping("v1/week/collect")
    public Result weekCollect(Integer year, Integer month, Integer week) throws ActException {
        try {
            List<StaffRecordsCollectBO> bos = staffRecordsAPI.weekCollect(year, month, week);
            return ActResult.initialize(BeanTransform.copyProperties(bos, StaffRecordsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月员工信息管理汇总
     *
     * @return class StaffRecordsCollectVO
     * @version v1
     */
    @GetMapping("v1/month/collect")
    public Result monthCollect(String month) throws ActException {
        try {
            List<StaffRecordsCollectBO> bos = staffRecordsAPI.monthCollect(month);
            return ActResult.initialize(BeanTransform.copyProperties(bos, StaffRecordsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 累计员工信息管理汇总
     *
     * @return class StaffRecordsCollectVO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect() throws ActException {
        try {
            List<StaffRecordsCollectBO> bos = staffRecordsAPI.totalCollect();
            return ActResult.initialize(BeanTransform.copyProperties(bos, StaffRecordsCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工信息管理日汇总柱状图
     *
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/day")
    public Result figureShowDay(String day, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = staffRecordsAPI.figureShowDay(day);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工信息管理周汇总柱状图
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
            OptionBO optionBO = staffRecordsAPI.figureShowWeek(year, month, week);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工信息管理月汇总图形化
     *
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/month")
    public Result figureShowMonth(String month, HttpServletRequest request) throws ActException {
        try {
            OptionBO optionBO = staffRecordsAPI.figureShowMonth(month);
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 员工信息管理累计汇总柱状图
     *
     * @return class OptionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/figureShow/all")
    public Result figureShowAll() throws ActException {
        try {
            OptionBO optionBO = staffRecordsAPI.figureShowAll();
            OptionVO optionVO = BeanTransform.copyProperties(optionBO, OptionVO.class);
            return ActResult.initialize(optionVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据名字获取数据
     *
     * @return class StaffRecordsDataVO
     * @version v1
     */
    @GetMapping("v1/findDataByName")
    public Result findDataByName(@RequestParam String name) throws ActException {
        try {
            StaffRecordsDataBO bo = staffRecordsAPI.findDataByName(name);
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffRecordsDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}