package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.ComputerSubsidiesAPI;
import com.bjike.goddess.assistance.dto.ComputerSubsidiesDTO;
import com.bjike.goddess.assistance.entity.ComputerSubsidies;
import com.bjike.goddess.assistance.excel.ComputerSubsidiesExcel;
import com.bjike.goddess.assistance.to.ComputerSubsidiesExcelTO;
import com.bjike.goddess.assistance.to.ComputerSubsidiesTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.vo.ComputerSubsidiesVO;
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
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 电脑补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("computersubsidies")
public class ComputerSubsidiesAction extends BaseFileAction{
    @Autowired
    private ComputerSubsidiesAPI computerSubsidiesAPI;

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

            Boolean isHasPermission = computerSubsidiesAPI.guidePermission(guidePermissionTO);
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
     * @param computerSubsidiesDTO 电脑补助dto
     * @des 获取所有电脑补助总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ComputerSubsidiesDTO computerSubsidiesDTO) throws ActException {
        try {
            Long count = computerSubsidiesAPI.countComputer(computerSubsidiesDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个电脑补助
     *
     * @param id 电脑补助id
     * @return class ComputerSubsidiesVO
     * @des 一个电脑补助
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ComputerSubsidiesVO computerSubsidiesVO = BeanTransform.copyProperties(
                    computerSubsidiesAPI.getOneById(id), ComputerSubsidiesVO.class);
            return ActResult.initialize(computerSubsidiesVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param computerSubsidiesDTO 电脑补助信息dto
     * @return class ComputerSubsidiesVO
     * @des 获取所有电脑补助
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSubsidy(ComputerSubsidiesDTO computerSubsidiesDTO, BindingResult bindingResult) throws ActException {
        try {
            List<ComputerSubsidiesVO> temperatureSubsidiesVOS = BeanTransform.copyProperties(
                    computerSubsidiesAPI.listComputer(computerSubsidiesDTO), ComputerSubsidiesVO.class);
            return ActResult.initialize(temperatureSubsidiesVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 添加
//     *
//     * @param computerSubsidiesTO 电脑补助数据to
//     * @des 添加电脑补助
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result addSubsidy(@Validated({ADD.class}) ComputerSubsidiesTO computerSubsidiesTO, BindingResult bindingResult) throws ActException {
//        try {
//            computerSubsidiesAPI.saveComputer(computerSubsidiesTO);
//            return new ActResult("save succese");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }


    /**
     * 编辑
     *
     * @param computerSubsidiesTO 电脑补助数据bo
     * @des 添加电脑补助
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editSubsidy(@Validated({EDIT.class}) ComputerSubsidiesTO computerSubsidiesTO) throws ActException {
        try {
            computerSubsidiesAPI.editComputer(computerSubsidiesTO);
            return new ActResult("edit succese");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除电脑补助
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteSubsidy(@PathVariable String id) throws ActException {
        try {
            computerSubsidiesAPI.deleteTemp(id);
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
            List<ComputerSubsidiesExcel> tos = ExcelUtil.excelToClazz(is, ComputerSubsidiesExcel.class, excel);
            List<ComputerSubsidiesExcelTO> tocs = new ArrayList<>();
            for (ComputerSubsidiesExcel str : tos) {
                ComputerSubsidiesExcelTO computerSubsidiesExcelTO = BeanTransform.copyProperties(str, ComputerSubsidiesExcelTO.class, "entryDate", "confirmDate");
                computerSubsidiesExcelTO.setEntryDate(String.valueOf(str.getEntryDate()));
                computerSubsidiesExcelTO.setConfirmDate(String.valueOf(str.getConfirmDate()));
                tocs.add(computerSubsidiesExcelTO);
            }
            //注意序列化
            computerSubsidiesAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @des 导出电脑补助
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "电脑补助.xlsx";
            super.writeOutFile(response, computerSubsidiesAPI.exportExcel(), fileName);
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
            String fileName = "电脑补助模板.xlsx";
            super.writeOutFile(response, computerSubsidiesAPI.templateExport(), fileName);
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
     * @param id 电脑补助id
     * @des 提醒确认
     * @version v1
     */
    @GetMapping("v1/remindingConfirm")
    public Result remindingConfirm(@RequestParam String id) throws ActException {
        try {
            computerSubsidiesAPI.remindingConfirm(id);
            return new ActResult("提醒成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认
     *
     * @param id 电脑补助id
     * @des 确认
     * @version v1
     */
    @GetMapping("v1/confirm")
    public Result confirm(@RequestParam String id, @RequestParam Boolean confirm, HttpServletResponse response) throws ActException {
        try {
            computerSubsidiesAPI.confirm(id, confirm);
            return new ActResult("确认成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}