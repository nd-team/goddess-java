package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.customer.api.CustomerBaseInfoAPI;
import com.bjike.goddess.customer.bo.CustomerInfoBO;
import com.bjike.goddess.marketdevelopment.api.PlanDayAPI;
import com.bjike.goddess.marketdevelopment.bo.MarkProblemAcceBO;
import com.bjike.goddess.marketdevelopment.dto.PlanDayDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanDayImportExcel;
import com.bjike.goddess.marketdevelopment.to.PlanDayTO;
import com.bjike.goddess.marketdevelopment.vo.MarkProblemAcceVO;
import com.bjike.goddess.marketdevelopment.vo.PlanDayVO;
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
 * 日计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 03:55 ]
 * @Description: [ 日计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("planday")
public class PlanDayAction extends BaseFileAction {
    @Autowired
    private PlanDayAPI planDayAPI;
    @Autowired
    private CustomerBaseInfoAPI customerBaseInfoAPI;

//    /**
//     * 功能导航权限
//     *
//     * @param guidePermissionTO 导航类型数据
//     * @throws ActException
//     * @version v1
//     */
//    @GetMapping("v1/guidePermission")
//    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            Boolean isHasPermission = planDayAPI.guidePermission(guidePermissionTO);
//            if (!isHasPermission) {
//                //int code, String msg
//                return new ActResult(0, "没有权限", false);
//            } else {
//                return new ActResult(0, "有权限", true);
//            }
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    /**
     * 列表
     *
     * @param dto 客户日计划数据传输对象
     * @return class PlanDayVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PlanDayDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planDayAPI.maps(dto), PlanDayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 客户日计划传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PlanDayTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planDayAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改客户日计划数据
     *
     * @param to 客户日计划传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PlanDayTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planDayAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结客户日计划数据
     *
     * @param to 客户日计划传输对象
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(PlanDayTO to, HttpServletRequest request) throws ActException {
        try {
            planDayAPI.congeal(to);
            return ActResult.initialize("congeal success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻客户日计划数据
     *
     * @param to 客户日计划传输对象
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(PlanDayTO to, HttpServletRequest request) throws ActException {
        try {
            planDayAPI.thaw(to);
            return ActResult.initialize("thaw success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除客户日计划数据
     *
     * @param to 客户日计划传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(PlanDayTO to, HttpServletRequest request) throws ActException {
        try {
            planDayAPI.delete(to);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取客户日计划
     *
     * @param id 业务方向数据id
     * @return class PlanDayVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planDayAPI.getById(id), PlanDayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(PlanDayDTO dto) throws ActException {
        try {
            return ActResult.initialize(planDayAPI.getTotal(dto));
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
            super.writeOutFile(response, planDayAPI.templateExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 客户日计划
     * @des 导出客户日计划
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(PlanDayDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "客户日计划.xlsx";
            super.writeOutFile(response, planDayAPI.exportExcel(dto), fileName);
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
            List<PlanDayImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, PlanDayImportExcel.class, excel);
            planDayAPI.upload(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据客户编号获取公司名称职务姓名电话
     *
     * @version v1
     */
    @GetMapping("v1/findByNum")
    public Result findByNum(@RequestParam String customerNum) throws ActException {
        try {
            CustomerInfoBO bo = customerBaseInfoAPI.findByNum(customerNum);
            return ActResult.initialize(bo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取业务方向类型
     *
     * @version v1
     */
    @GetMapping("v1/findBusinessType")
    public Result findBusinessType() throws ActException {
        try {
            List<String> list = planDayAPI.findBusinessType();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取业务方向科目
     *
     * @version v1
     */
    @GetMapping("v1/findBusinessSub")
    public Result findBusinessSub() throws ActException {
        try {
            List<String> list = planDayAPI.findBusinessSub();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取问题受理编号（对内）
     *
     * @version v1
     */
    @GetMapping("v1/findInterCode")
    public Result findInterCode() throws ActException {
        try {
            List<String> list = planDayAPI.findInterCode();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据问题受理编号获取地区项目组问题归类
     *
     * @return class MarkProblemAcceVO
     * @version v1
     */
    @GetMapping("v1/findProblemAcce")
    public Result findProblemAcce(@RequestParam String interCode) throws ActException {
        try {
            MarkProblemAcceBO bo = planDayAPI.findProblemAcce(interCode);
            return ActResult.initialize(BeanTransform.copyProperties(bo, MarkProblemAcceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取市场信息编号
     *
     * @version v1
     */
    @GetMapping("v1/findMarkCode")
    public Result findMarkCode() throws ActException {
        try {
            List<String> list = planDayAPI.findMarkCode();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}