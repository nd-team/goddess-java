package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.SenioritySubsidiesAPI;
import com.bjike.goddess.assistance.dto.SenioritySubsidiesDTO;
import com.bjike.goddess.assistance.excel.SenioritySubsidiesExcel;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.to.SenioritySubsidiesTO;
import com.bjike.goddess.assistance.vo.SenioritySubsidiesVO;
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
 * 工龄补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 11:34 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("senioritysubsidies")
public class SenioritySubsidiesAction extends BaseFileAction {
    @Autowired
    private SenioritySubsidiesAPI senioritySubsidiesAPI;

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

            Boolean isHasPermission = senioritySubsidiesAPI.guidePermission(guidePermissionTO);
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
     * @param senioritySubsidiesDTO 工龄补助dto
     * @des 获取所有公司补助方案总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SenioritySubsidiesDTO senioritySubsidiesDTO) throws ActException {
        try {
            Long count = senioritySubsidiesAPI.countSenSub(senioritySubsidiesDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 一个工龄补助
     *
     * @param id 工龄补助id
     * @return class SenioritySubsidiesVO
     * @des 一个工龄补助
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            SenioritySubsidiesVO senioritySubsidiesVO = BeanTransform.copyProperties(
                    senioritySubsidiesAPI.getOneById(id), SenioritySubsidiesVO.class);
            return ActResult.initialize(senioritySubsidiesVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param senioritySubsidiesDTO 工龄补助信息dto
     * @return class SenioritySubsidiesVO
     * @des 获取所有工龄补助
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListSubsidy(SenioritySubsidiesDTO senioritySubsidiesDTO, BindingResult bindingResult) throws ActException {
        try {
            List<SenioritySubsidiesVO> senioritySubsidiesVOS = BeanTransform.copyProperties(
                    senioritySubsidiesAPI.listSenSub(senioritySubsidiesDTO), SenioritySubsidiesVO.class, true);
            return ActResult.initialize(senioritySubsidiesVOS);
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
            List<SenioritySubsidiesExcel> tos = ExcelUtil.excelToClazz(is, SenioritySubsidiesExcel.class, excel);
            List<SenioritySubsidiesTO> tocs = new ArrayList<>();
            for (SenioritySubsidiesExcel str : tos) {
                SenioritySubsidiesTO senioritySubsidiesTO = BeanTransform.copyProperties(str, SenioritySubsidiesTO.class, "entryDate", "startIssueDate");
                senioritySubsidiesTO.setEntryDate(String.valueOf(str.getEntryDate()));
                senioritySubsidiesTO.setStartIssueDate(String.valueOf(str.getStartIssueDate()));
                tocs.add(senioritySubsidiesTO);
            }
            //注意序列化
            senioritySubsidiesAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    private SubsidiesStatus convertSubsidStatus(String subsidiesStatus) throws ActException {
//        SubsidiesStatus status = null;
//        if (null == subsidiesStatus) {
//            throw new ActException("补助状态填写不正确,导入失败,正确填写方式（在补助/未补助）");
//        }
//        switch (subsidiesStatus) {
//            case "在补助":
//                status = SubsidiesStatus.INSUBSIDIES;
//                break;
//            case "未补助":
//                status = SubsidiesStatus.NOSUBSIDIES;
//                break;
//            default:
//                throw new ActException("补助状态填写不正确,导入失败,正确填写方式（在补助/未补助）");
//        }
//        return status;
//    }
//    private StaffStatus convertStaffStatus(String staffStatus) throws ActException {
//        StaffStatus status = null;
//        if (null == status) {
//            throw new ActException("员工状态填写不正确,导入失败,正确填写方式（在职/已离职/待离职/请假）");
//        }
//        switch (staffStatus) {
//            case "在职":
//                status = StaffStatus.WORKING;
//                break;
//            case "已离职":
//                status = StaffStatus.HAVELEAVE;
//                break;
//            case "待离职":
//                status = StaffStatus.LEAVING;
//                break;
//            case "请假":
//                status = StaffStatus.REST;
//                break;
//            default:
//                throw new ActException("员工状态填写不正确,导入失败,正确填写方式（在职/已离职/待离职/请假）");
//        }
//        return status;
//    }


    /**
     * 导出excel
     *
     * @des 导出工龄补助
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "工龄补助.xlsx";
            super.writeOutFile(response, senioritySubsidiesAPI.exportExcel(), fileName);
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
     * @des 下载模板工龄补助
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "工龄补助模板.xlsx";
            super.writeOutFile(response, senioritySubsidiesAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}