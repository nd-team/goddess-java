package com.bjike.goddess.staffmove.action.staffmove;

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
import com.bjike.goddess.staffmove.api.StaffMoveIntendCaseAPI;
import com.bjike.goddess.staffmove.bo.StaffMoveIntendCaseBO;
import com.bjike.goddess.staffmove.dto.StaffMoveIntendCaseDTO;
import com.bjike.goddess.staffmove.excel.StaffMoveIntendCaseExcel;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveIntendCaseTO;
import com.bjike.goddess.staffmove.vo.StaffMoveIntendCaseVO;
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
 * 人员调动意愿情况
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:36 ]
 * @Description: [ 人员调动意愿情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("staffmoveintendcase")
public class StaffMoveIntendCaseAction extends BaseFileAction {
    @Autowired
    private StaffMoveIntendCaseAPI staffMoveIntendCaseAPI;

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

            Boolean isHasPermission = staffMoveIntendCaseAPI.guidePermission(guidePermissionTO);
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
     * 人员调动意愿情况列表总条数
     *
     * @param dto 人员调动意愿情况dto
     * @des 获取所有人员调动意愿情况总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(StaffMoveIntendCaseDTO dto) throws ActException {
        try {
            Long count = staffMoveIntendCaseAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个人员调动意愿情况
     *
     * @param id
     * @return class StaffMoveIntendCaseVO
     * @des 获取一个人员调动意愿情况
     * @version v1
     */
    @GetMapping("v1/demand/{id}")
    public Result demand(@PathVariable String id) throws ActException {
        try {
            StaffMoveIntendCaseBO bo = staffMoveIntendCaseAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffMoveIntendCaseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 人员调动意愿情况列表
     *
     * @param dto 人员调动意愿情况dto
     * @return class StaffMoveIntendCaseVO
     * @des 获取所有人员调动意愿情况
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(StaffMoveIntendCaseDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<StaffMoveIntendCaseVO> caseVOS = BeanTransform.copyProperties
                    (staffMoveIntendCaseAPI.list(dto), StaffMoveIntendCaseVO.class, request);
            return ActResult.initialize(caseVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加人员调动意愿情况
     *
     * @param to 人员调动意愿情况数据to
     * @return class StaffMoveIntendCaseVO
     * @des 添加人员调动意愿情况
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StaffMoveIntendCaseTO to, BindingResult bindingResult) throws ActException {
        try {
            StaffMoveIntendCaseBO bo = staffMoveIntendCaseAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffMoveIntendCaseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑人员调动意愿情况
     *
     * @param to 人员调动意愿情况数据to
     * @return class StaffMoveIntendCaseVO
     * @des 编辑人员调动意愿情况
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) StaffMoveIntendCaseTO to, BindingResult bindingResult) throws ActException {
        try {
            StaffMoveIntendCaseBO bo = staffMoveIntendCaseAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, StaffMoveIntendCaseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除人员调动意愿情况
     *
     * @param id 用户id
     * @des 根据用户id删除人员调动意愿情况记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            staffMoveIntendCaseAPI.remove(id);
            return new ActResult("delete success");
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
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<StaffMoveIntendCaseExcel> tos = ExcelUtil.excelToClazz(is, StaffMoveIntendCaseExcel.class, excel);
            List<StaffMoveIntendCaseTO> tocs = new ArrayList<>();
            for (StaffMoveIntendCaseExcel str : tos) {
                StaffMoveIntendCaseTO caseTO = BeanTransform.copyProperties(str, StaffMoveIntendCaseTO.class, "askWorkArea", "obeyPlan");
                //是否对工作地区有要求
                if (str.getAskWorkArea().equals("否")) {
                    caseTO.setAskWorkArea(false);
                } else {
                    str.setAskWorkArea(true);
                }
                //是否服从全国范围内调动安排
                if (caseTO.getObeyPlan().equals("否")) {
                    str.setObeyPlan(false);
                } else {
                    str.setObeyPlan(true);
                }
                tocs.add(caseTO);
            }
            //注意序列化
            staffMoveIntendCaseAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 人员调动意愿情况
     * @des 导出人员调动意愿情况
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(StaffMoveIntendCaseDTO dto, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            String fileName = "人员调动意愿情况.xlsx";
            super.writeOutFile(response, staffMoveIntendCaseAPI.exportExcel(dto), fileName);
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
     * @des 下载模板商务项目合同
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "人员调动意愿情况导入模板.xlsx";
            super.writeOutFile(response, staffMoveIntendCaseAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

}