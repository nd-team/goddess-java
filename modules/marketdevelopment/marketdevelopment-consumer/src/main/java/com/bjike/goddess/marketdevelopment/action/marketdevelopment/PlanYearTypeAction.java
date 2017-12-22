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
import com.bjike.goddess.marketdevelopment.api.PlanYearTypeAPI;
import com.bjike.goddess.marketdevelopment.dto.PlanYearTypeDTO;
import com.bjike.goddess.marketdevelopment.excel.PlanYearImportExcel;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearTO;
import com.bjike.goddess.marketdevelopment.to.PlanYearUpdateTO;
import com.bjike.goddess.marketdevelopment.vo.PlanYearUpdateVO;
import com.bjike.goddess.marketdevelopment.vo.PlanYearVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 年计划
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 05:32 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("planyeartype")
public class PlanYearTypeAction extends BaseFileAction {
    @Autowired
    private PlanYearTypeAPI planYearTypeAPI;


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

            Boolean isHasPermission = planYearTypeAPI.guidePermission(guidePermissionTO);
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
     * @return class WeekMonthMoneyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PlanYearTypeDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planYearTypeAPI.maps(dto), PlanYearVO.class, request));
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
    public Result save(@Validated(ADD.class) PlanYearTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planYearTypeAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改年计划数据
     *
     * @param to 年计划传输对象(id为返回的金额类型表中的id)
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PlanYearUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            planYearTypeAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除年计划数据
     *
     * @param id id为返回的金额类型表中的id
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            planYearTypeAPI.delete(id);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取年计划数据
     *
     * @param id 类型id
     * @return class PlanYearUpdateVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(planYearTypeAPI.getById(id), PlanYearUpdateVO.class, request));
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
            return ActResult.initialize(planYearTypeAPI.getTotal(dto));
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
            planYearTypeAPI.congeal(id);
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
            planYearTypeAPI.thaw(id);
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
            Excel excel = new Excel(0, 2);
            List<PlanYearImportExcel> tos = ExcelUtil.mergeExcelToClazz(inputStream, PlanYearImportExcel.class, excel);
            planYearTypeAPI.importExcel(tos);
            return ActResult.initialize("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel模板
     *
     * @version v1
     */
    @PostMapping("v1/exportTempExcel")
    public Result exportTempExcel(HttpServletResponse response) throws ActException {
        try {
            String fileName = "年计划导入模板.xlsx";
            super.writeOutFile(response, planYearTypeAPI.exportTempExcel(), fileName);
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
            String fileName = "年计划.xlsx";
            super.writeOutFile(response, planYearTypeAPI.export(), fileName);
            return ActResult.initialize("导出成功");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     *
     *
     */


}