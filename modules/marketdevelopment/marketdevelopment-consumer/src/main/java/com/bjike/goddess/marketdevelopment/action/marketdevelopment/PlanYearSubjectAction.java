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
import com.bjike.goddess.marketdevelopment.api.PlanYearSubjectAPI;
import com.bjike.goddess.marketdevelopment.dto.PlanYearSubjectDTO;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearmapsImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearSubjectUpdateTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearmapsTO;
import com.bjike.goddess.marketdevelopment.vo.PlanYearSubjectUpdateVO;
import com.bjike.goddess.marketdevelopment.vo.PlanYearmapsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 年计划(科目方向)
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-08 03:34 ]
 * @Description: [ 年计划(科目方向) ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("planyearsubject")
public class PlanYearSubjectAction extends BaseFileAction {

    @Autowired
    private PlanYearSubjectAPI planYearSubjectAPI;

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

            Boolean isHasPermission = planYearSubjectAPI.guidePermission(guidePermissionTO);
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
     * @param dto 年计划数据传输对象
     * @return class PlanYearmapsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PlanYearSubjectDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planYearSubjectAPI.maps(dto), PlanYearmapsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 年计划传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PlanYearmapsTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planYearSubjectAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改年计划数据
     *
     * @param to 年计划传输对象(id为返回的科目id)
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PlanYearSubjectUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planYearSubjectAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年计划数据
     *
     * @param id id为返回的业务方向分类id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            planYearSubjectAPI.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取年计划数据
     *
     * @param id 类型id
     * @return class PlanYearSubjectUpdateVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planYearSubjectAPI.getById(id), PlanYearSubjectUpdateVO.class, request));
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
    public Result getTotal(PlanYearTypeDTO dto) throws ActException {
        try {
            return ActResult.initialize(planYearSubjectAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 年份id
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            planYearSubjectAPI.congeal(id);
            return ActResult.initialize("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 年份id
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            planYearSubjectAPI.thaw(id);
            return ActResult.initialize("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导入
     *
     * @version v1
     */
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream inputStream = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<PlanYearmapsImportExcel> tos = ExcelUtil.mergeExcelToClazz(inputStream, PlanYearmapsImportExcel.class, excel);
            planYearSubjectAPI.importExcel(tos);
            return ActResult.initialize("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板导出
     *
     * @version v1
     */
    @GetMapping("v1/exportTempExcel")
    public Result exportTempExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "年计划(业务方向)模板.xlsx";
            super.writeOutFile(response, planYearSubjectAPI.exportTempExcel(), fileName);
            return ActResult.initialize("导出成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     *
     * @version v1
     */
    @GetMapping("v1/export")
    public Result export(HttpServletResponse response) throws ActException {
        try {
            String fileName = "年计划(业务方向).xlsx";
            super.writeOutFile(response, planYearSubjectAPI.export(), fileName);
            return ActResult.initialize("导出成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年份获取商务合同的派工金额
     *
     * @param year 传年份 如2017
     * @version v1
     */
    @GetMapping("v1/findMoney")
    public Result findMoney(@RequestParam String year) throws ActException {
        try {
            return ActResult.initialize(planYearSubjectAPI.findMoney(year));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据业务类型和年份获取各业务类型实际金额
     *
     * @version v1
     */
    @GetMapping("v1/moneyByType")
    public Result moneyByType(@RequestParam String businessType, @RequestParam String year) throws ActException {
        try {
            return ActResult.initialize(planYearSubjectAPI.moneyByType(businessType, year));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算各业务科目年度占比
     *
     * @param proportion 各业务科目同一业务类型中占比
     * @param workWeight 工作量权重
     * @version v1
     */
    @GetMapping("v1/countYearProportion")
    public Result countYearProportion(Double workWeight, Double proportion) throws ActException {
        try {
            return ActResult.initialize(planYearSubjectAPI.countYearProportion(workWeight, proportion));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 现有业务可发展对象
     *
     * @param course 业务方向科目
     * @version v1
     */
    @GetMapping("v1/findDeveBusiness")
    public Result findDeveBusiness(@RequestParam String course) throws ActException {
        try {
            return ActResult.initialize(planYearSubjectAPI.findDeveBusiness(course));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}