package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.TemperatureSubsidiesAPI;
import com.bjike.goddess.assistance.dto.TemperatureSubsidiesDTO;
import com.bjike.goddess.assistance.excel.TemperatureSubsidiesExcel;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.TemperatureSubsidiesTO;
import com.bjike.goddess.assistance.vo.SonPermissionObject;
import com.bjike.goddess.assistance.vo.TemperatureSubsidiesVO;
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
 * 高温补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:38 ]
 * @Description: [ 高温补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("temperaturesubsidies")
public class TemperatureSubsidiesAction extends BaseFileAction {
    @Autowired
    private TemperatureSubsidiesAPI temperatureSubsidiesAPI;
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

            List<SonPermissionObject> hasPermissionList = temperatureSubsidiesAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总条数
     *
     * @param temperatureSubsidiesDTO 高温补助dto
     * @des 获取所有高温补助总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TemperatureSubsidiesDTO temperatureSubsidiesDTO) throws ActException {
        try {
            Long count = temperatureSubsidiesAPI.countTempera(temperatureSubsidiesDTO);
            return ActResult.initialize(count);
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
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = temperatureSubsidiesAPI.guidePermission(guidePermissionTO);
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
     * 一个高温补助
     *
     * @param id 高温补助id
     * @return class TemperatureSubsidiesVO
     * @des 一个高温补助
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            TemperatureSubsidiesVO temperatureSubsidiesVO = BeanTransform.copyProperties(
                    temperatureSubsidiesAPI.getOneById(id), TemperatureSubsidiesVO.class);
            return ActResult.initialize(temperatureSubsidiesVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param temperatureSubsidiesDTO 高温补助信息dto
     * @return class TemperatureSubsidiesVO
     * @des 获取所有高温补助
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSubsidy(TemperatureSubsidiesDTO temperatureSubsidiesDTO, BindingResult bindingResult) throws ActException {
        try {
            List<TemperatureSubsidiesVO> temperatureSubsidiesVOS = BeanTransform.copyProperties(
                    temperatureSubsidiesAPI.listTempera(temperatureSubsidiesDTO), TemperatureSubsidiesVO.class);
            return ActResult.initialize(temperatureSubsidiesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param temperatureSubsidiesTO 高温补助数据to
     * @des 添加高温补助
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addSubsidy(@Validated({ADD.class}) TemperatureSubsidiesTO temperatureSubsidiesTO, BindingResult bindingResult) throws ActException {
        try {
            temperatureSubsidiesAPI.saveTempera(temperatureSubsidiesTO);
            return new ActResult("save succese");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param temperatureSubsidiesTO 高温补助数据bo
     * @des 添加高温补助
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSubsidy(@Validated({EDIT.class}) TemperatureSubsidiesTO temperatureSubsidiesTO) throws ActException {
        try {
            temperatureSubsidiesAPI.editTempera(temperatureSubsidiesTO);
            return new ActResult("edit succese");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除高温补助
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSubsidy(@PathVariable String id) throws ActException {
        try {
            temperatureSubsidiesAPI.deleteTemp(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
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
            List<TemperatureSubsidiesExcel> tos = ExcelUtil.excelToClazz(is, TemperatureSubsidiesExcel.class, excel);
            List<TemperatureSubsidiesExcelTO> tocs = new ArrayList<>();
            for (TemperatureSubsidiesExcel str : tos) {
                TemperatureSubsidiesExcelTO temperatureSubsidiesExcelTO = BeanTransform.copyProperties(str, TemperatureSubsidiesExcelTO.class, "entryDate", "salaryStartDate", "salaryEndDate", "outdoorWorkDate", "confirmDate");
                temperatureSubsidiesExcelTO.setEntryDate(String.valueOf(str.getEntryDate()));
                temperatureSubsidiesExcelTO.setSalaryStartDate(String.valueOf(str.getSalaryStartDate()));
                temperatureSubsidiesExcelTO.setSalaryEndDate(String.valueOf(str.getSalaryEndDate()));
                temperatureSubsidiesExcelTO.setOutdoorWorkDate(String.valueOf(str.getOutdoorWorkDate()));
                temperatureSubsidiesExcelTO.setConfirmDate(String.valueOf(str.getConfirmDate()));
                tocs.add(temperatureSubsidiesExcelTO);
            }
            //注意序列化
            temperatureSubsidiesAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出高温补助
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "高温补助.xlsx";
            super.writeOutFile(response, temperatureSubsidiesAPI.exportExcel(), fileName);
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
     * @des 下载模板高温补助
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "高温补助模板.xlsx";
            super.writeOutFile(response, temperatureSubsidiesAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 提醒确认
     *
     * @param id 高温补助id
     * @des 提醒确认
     * @version v1
     */
    @GetMapping("v1/remindingConfirm")
    public Result remindingConfirm(@RequestParam String id) throws ActException {
        try {
            temperatureSubsidiesAPI.remindingConfirm(id);
            return new ActResult("提醒成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认
     *
     * @param id 高温补助id
     * @des 确认
     * @version v1
     */
    @GetMapping("v1/confirm")
    public Result confirm(@RequestParam String id, @RequestParam Boolean confirm, HttpServletResponse response) throws ActException {
        try {
            temperatureSubsidiesAPI.confirm(id, confirm);
            return new ActResult("确认成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}