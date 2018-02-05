package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.vo.DriverInfoVO;
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
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.bo.AuditDetailBO;
import com.bjike.goddess.dispatchcar.bo.DispatchCarInfoBO;
import com.bjike.goddess.dispatchcar.bo.OilCardBasicCarBO;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchcarExportDTO;
import com.bjike.goddess.dispatchcar.excel.DispatchCarInfoSetExcel;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.to.*;
import com.bjike.goddess.dispatchcar.vo.AuditDetailVO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import com.bjike.goddess.dispatchcar.vo.OilCardBasicCarVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
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
 * 出车记录
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dispatchcarinfo")
public class DispatchCarInfoAct extends BaseFileAction {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;
    @Autowired
    private FileAPI fileAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;


    /**
     * 模块设置导航权限
     *
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
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = dispatchCarInfoAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = dispatchCarInfoAPI.guidePermission(guidePermissionTO);
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
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(DispatchCarInfoDTO dto) throws ActException {
        try {
            Long count = dispatchCarInfoAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id出车记录
     *
     * @param id 出车记录id
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findById(id), DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增出车记录
     *
     * @param to 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DispatchCarInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoBO bo = dispatchCarInfoAPI.addModel(to);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(bo, DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 完善出车记录
     *
     * @param to 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/complete")
    public Result complete(@Validated({DispatchCarInfoTO.COMPLETE.class}) DispatchCarInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        if (StringUtils.isBlank(to.getId())) {
            throw new ActException("出车记录不能为空");
        }
        try {
            DispatchCarInfoBO model = dispatchCarInfoAPI.editModel(to);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(model, DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑出车记录
     *
     * @param to 出车记录
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) DispatchCarInfoTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            DispatchCarInfoBO model = dispatchCarInfoAPI.editModel(to);
            DispatchCarInfoVO vo = BeanTransform.copyProperties(model, DispatchCarInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻出车记录
     *
     * @param id 出车记录id
     * @version v1
     */
    @PatchMapping("v1/unfreeze/{id}")
    public Result breakFreeze(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.breakFreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @param request 附件内容
     * @param id      出车id
     * @version v1
     */
    @PostMapping("v1/upload/{id}")
    public Result fileUpload(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            String path = "/dispatchcar/" + id;
            fileAPI.upload(this.getInputStreams(request, path.toString()));
            return new ActResult("上传成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id id 列表id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            String path = "/dispatchcar/" + id;
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
     * @param path 文件路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {
            //该文件的路径
            FileInfo fileInfo = new FileInfo();
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            fileInfo.setPath(path);
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
     * @param dispatchcarDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(DispatchcarDeleteFileTO.TestDEL.class) DispatchcarDeleteFileTO dispatchcarDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != dispatchcarDeleteFileTO.getPaths() && dispatchcarDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), dispatchcarDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @return class AuditDetailVO
     * @version v1
     */
    @GetMapping("v1/audit/{id}")
    public Result findAudit(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AuditDetailBO bo = dispatchCarInfoAPI.findAudit(id);
            AuditDetailVO vo = BeanTransform.copyProperties(bo, AuditDetailVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DispatchCarInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DispatchCarInfoBO> dispatchCarInfoBOS = dispatchCarInfoAPI.pageList(dto);
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoBOS, DispatchCarInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有司机信息和车牌号码
     *
     * @return class DriverInfoVO
     * @version v1
     */
    @GetMapping("v1/find/driver")
    public Result findDriver() throws ActException {
        try {
            List<DriverInfoBO> boList = dispatchCarInfoAPI.findDriver();
            List<DriverInfoVO> voList = BeanTransform.copyProperties(boList, DriverInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有用车陪同人员和用车人员和任务下达人员和所属项目组
     *
     * @return class UserVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/find/entry")
    public Result findAllEntry() throws ActException {
        try {
            List<UserBO> boList = dispatchCarInfoAPI.findAllEntry();
            List<UserVO> voList = BeanTransform.copyProperties(boList, UserVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有所属地区
     *
     * @return class AreaBO
     * @version v1
     */
    @GetMapping("v1/find/area")
    public Result findAllArea() throws ActException {
        try {
            List<AreaBO> areas = dispatchCarInfoAPI.findArea();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有项目组
     *
     * @version v1
     */
    @GetMapping("v1/find/department")
    public Result findAllDepartment() throws ActException {
        try {
            List<String> departments = dispatchCarInfoAPI.getAllDepartment();
            return ActResult.initialize(departments);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有油卡信息
     *
     * @return class OilCardBasicCarVO
     * @version v1
     */
    @GetMapping("v1/find/oil")
    public Result findAllOil() throws ActException {
        try {
            List<OilCardBasicCarBO> boList = dispatchCarInfoAPI.findAllOil();
            List<OilCardBasicCarVO> voList = BeanTransform.copyProperties(boList, OilCardBasicCarVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查找所有项目名称
     *
     * @version v1
     */
    @GetMapping("v1/find/allProject")
    public Result findAllProject() throws ActException {
        try {
            List<String> allProject = dispatchCarInfoAPI.findAllProject();
            return ActResult.initialize(allProject);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 寄件
     *
     * @param to 寄件内容
     * @version v1
     */
    @PostMapping("v1/mail")
    public Result mail(@Validated(EDIT.class) MailTO to, BindingResult bindingResult) throws ActException {
        try {
            dispatchCarInfoAPI.mail(to);
            return new ActResult("寄件编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导入
     *
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/leadExcel")
    public Result leadExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<DispatchCarInfoSetExcel> tos = ExcelUtil.excelToClazz(is, DispatchCarInfoSetExcel.class, excel);
            dispatchCarInfoAPI.leadExcel(tos);
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
    public Result exportExcel(ExportDispatchCarInfoTO to, HttpServletResponse response) throws ActException {
        try {
            DispatchcarExportDTO dto = new DispatchcarExportDTO();
            dto.setArea(to.getArea());
            dto.setStartTime(to.getStartTime());
            dto.setEndTime(to.getEndTime());
            dto.setFindType(null);
            String fileName = "出车记录.xlsx";
            super.writeOutFile(response, dispatchCarInfoAPI.exportExcel(dto), fileName);
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
            String fileName = "出车记录模板.xlsx";
            super.writeOutFile(response, dispatchCarInfoAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


//    /**
//     * 添加数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/copy/serve")
//    public Result copyServe() throws ActException{
//        try {
//            dispatchCarInfoAPI.copyServer();
//            return new ActResult("添加成功");
//        }catch (SerException e){
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 出车记录删除
     *
     * @param id id
     * @version v1
     */
    @DeleteMapping("v1/delete")
    public Result delete(@RequestParam String id) throws ActException {
        try {
            dispatchCarInfoAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据司机姓名获取出车油耗
     *
     * @param driver driver
     * @version v1
     */
    @GetMapping("v1/find/oilwear")
    public Result findOilWear(String driver) throws ActException {
        try {
            Double oilWear = dispatchCarInfoAPI.findOilWear(driver);
            return ActResult.initialize(oilWear);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据油卡编号查询油卡余额
     *
     * @param oilCardNumber oilCardNumber
     * @version v1
     */
    @GetMapping("v1/find/balance")
    public Result findBalance(@RequestParam String oilCardNumber) throws ActException {
        try {
            Double balance = dispatchCarInfoAPI.findBalance(oilCardNumber);
            return ActResult.initialize(balance);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据项目名称获取立项信息
     *
     * @param project project
     * @version v1
     */
    @GetMapping("v1/find/projectapproval")
    public Result findProjectApproval(String project) throws ActException {
        try {
            Boolean projectAproval = dispatchCarInfoAPI.findProjectAproval(project);
            return ActResult.initialize(projectAproval);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取出车单号
     *
     * @version v1
     */
    @GetMapping("v1/dispatchNumber")
    public Result getDispatchNumber() throws ActException {
        try {
            String number = dispatchCarInfoAPI.getDispathNumber();
            return ActResult.initialize(number);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}