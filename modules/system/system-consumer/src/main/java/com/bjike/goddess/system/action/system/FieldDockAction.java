package com.bjike.goddess.system.action.system;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.api.ModuleTypeAPI;
import com.bjike.goddess.organize.vo.DepartmentDetailVO;
import com.bjike.goddess.organize.vo.ModuleTypeVO;
import com.bjike.goddess.system.api.FieldDockAPI;
import com.bjike.goddess.system.bo.FieldDockBO;
import com.bjike.goddess.system.bo.PlatformClassifyBO;
import com.bjike.goddess.system.dto.FieldDockDTO;
import com.bjike.goddess.system.dto.PlatformClassifyDTO;
import com.bjike.goddess.system.excel.FieldDockExcel;
import com.bjike.goddess.system.excel.PlatformClassifyExcel;
import com.bjike.goddess.system.to.FieldDockTO;
import com.bjike.goddess.system.to.PlatformClassifyTO;
import com.bjike.goddess.system.vo.FieldDockVO;
import com.bjike.goddess.system.vo.PlatformClassifyVO;
import com.bjike.goddess.user.entity.Department;
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
 * 字段对接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-10 11:43 ]
 * @Description: [ 字段对接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("fielddock")
public class FieldDockAction extends BaseFileAction{
    @Autowired
    private FieldDockAPI fieldDockAPI;
    /**
     * 字段对接列表总条数
     *
     * @param dto 字段对接记录dto
     * @des 获取所有字段对接
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FieldDockDTO dto) throws ActException {
        try {
            Long count = fieldDockAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个字段对接
     *
     * @param id
     * @return class FieldDockVO
     * @des 获取一个字段对接
     * @version v1
     */
    @GetMapping("v1/wait/{id}")
    public Result wait(@PathVariable String id) throws ActException {
        try {
            FieldDockBO fieldDockBO = fieldDockAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(fieldDockBO, FieldDockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 字段对接列表
     *
     * @param dto 字段对接记录dto
     * @return class FieldDockVO
     * @des 获取所有字段对接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(FieldDockDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FieldDockVO> fieldDockVOS = BeanTransform.copyProperties(
                    fieldDockAPI.list(dto), FieldDockVO.class, request);
            return ActResult.initialize(fieldDockVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加字段对接
     *
     * @param to 字段对接to
     * @return class FieldDockVO
     * @des 添加字段对接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(FieldDockTO to, BindingResult bindingResult) throws ActException {
        try {
            FieldDockBO fieldDockBO = fieldDockAPI.insert(to);
            return ActResult.initialize(BeanTransform.copyProperties(fieldDockBO,FieldDockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑字段对接
     *
     * @param to 字段对接数据to
     * @return class FieldDockVO
     * @des 编辑字段对接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(FieldDockTO to, BindingResult bindingResult) throws ActException {
        try {
            FieldDockBO fieldDockBO = fieldDockAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(fieldDockBO,FieldDockVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除字段对接
     *
     * @param id 用户id
     * @des 根据用户id删除字段对接
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            fieldDockAPI.remove(id);
            return new ActResult("delete success!");
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
            List<FieldDockExcel> tos = ExcelUtil.excelToClazz(is, FieldDockExcel.class, excel);
            List<FieldDockTO> tocs = new ArrayList<>();
            for (FieldDockExcel str : tos) {
                FieldDockTO fieldDockTO = BeanTransform.copyProperties(str, FieldDockTO.class,"nodeUpdateTime","newNodeUpdateTime","fieldUpdateTime");
                fieldDockTO.setNodeUpdateTime(String.valueOf(str.getNodeUpdateTime()));
                fieldDockTO.setNewNodeUpdateTime(String.valueOf(str.getNewNodeUpdateTime()));
                fieldDockTO.setFieldUpdateTime(String.valueOf(str.getFieldUpdateTime()));
                tocs.add(fieldDockTO);
            }
            //注意序列化
            fieldDockAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @param dto 字段对接
     * @des 导出字段对接
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(FieldDockDTO dto, HttpServletResponse response) throws ActException {
        try {
            String fileName = "字段对接.xlsx";
            super.writeOutFile(response, fieldDockAPI.exportExcel(dto), fileName);
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
     * @des 下载模板字段对接
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "字段对接导入模板.xlsx";
            super.writeOutFile(response, fieldDockAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }


}