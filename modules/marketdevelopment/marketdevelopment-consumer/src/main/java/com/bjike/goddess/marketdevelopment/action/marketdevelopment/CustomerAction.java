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
import com.bjike.goddess.marketdevelopment.api.CustomerAPI;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.excel.CustomerImportExcel;
import com.bjike.goddess.marketdevelopment.to.CustomerTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.vo.CustomerVO;
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
 * 客户接触阶段
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer")
public class CustomerAction extends BaseFileAction {
    @Autowired
    private CustomerAPI customerAPI;

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
            Boolean isHasPermission = customerAPI.guidePermission(guidePermissionTO);
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
     * @param dto 客户接触阶段数据传输对象
     * @return class CustomerVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CustomerDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(customerAPI.maps(dto), CustomerVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 客户接触阶段传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CustomerTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            customerAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改客户接触阶段数据
     *
     * @param to 客户接触阶段传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CustomerTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            customerAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结客户接触阶段数据
     *
     * @param to 客户接触阶段传输对象
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(CustomerTO to, HttpServletRequest request) throws ActException {
        try {
            customerAPI.congeal(to);
            return ActResult.initialize("congeal success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻客户接触阶段数据
     *
     * @param to 客户接触阶段传输对象
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(CustomerTO to, HttpServletRequest request) throws ActException {
        try {
            customerAPI.thaw(to);
            return ActResult.initialize("thaw success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除客户接触阶段数据
     *
     * @param to 客户接触阶段传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(CustomerTO to, HttpServletRequest request) throws ActException {
        try {
            customerAPI.delete(to);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取客户接触阶段
     *
     * @param id 业务方向数据id
     * @return class CustomerVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(customerAPI.getById(id), CustomerVO.class, request));
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
    public Result getTotal(CustomerDTO dto) throws ActException {
        try {
            return ActResult.initialize(customerAPI.getTotal(dto));
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
            super.writeOutFile(response, customerAPI.templateExcel(), fileName);
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
     * @param dto 客户接触阶段
     * @des 导出客户接触阶段
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(CustomerDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "客户接触阶段.xlsx";
            super.writeOutFile(response, customerAPI.exportExcel(dto), fileName);
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
            List<CustomerImportExcel> tos = ExcelUtil.mergeExcelToClazz(is, CustomerImportExcel.class, excel);
            customerAPI.upload(tos);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取阶段
     *
     * @version v1
     */
    @GetMapping("v1/findStage")
    public Result findStage() throws ActException {
        try {
            List<String> list = customerAPI.findStage();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}