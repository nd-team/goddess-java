package com.bjike.goddess.materialcheck.action.materialcheck;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialcheck.api.MaterialAnalyzeAPI;
import com.bjike.goddess.materialcheck.bo.MaterialAnalyzeBO;
import com.bjike.goddess.materialcheck.dto.MaterialAnalyzeDTO;
import com.bjike.goddess.materialcheck.to.GuidePermissionTO;
import com.bjike.goddess.materialcheck.to.MaterialAnalyzeTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import com.bjike.goddess.materialcheck.vo.MaterialAnalyzeVO;
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 物资分析年盘
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:18 ]
 * @Description: [ 物资分析年盘 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialanalyzeAnnual")
public class MaterialAnalyzeAnnualAct {


    @Autowired
    private MaterialAnalyzeAPI materialAnalyzeAPI;
@Autowired
private DepartmentDetailAPI departmentDetailAPI;

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

            Boolean isHasPermission = materialAnalyzeAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询物资分析年盘
     *
     * @param id 物资分析年盘唯一标识
     * @return class MaterialAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/materialanalyzeAnnual/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialAnalyzeBO bo = materialAnalyzeAPI.findById(id);
            MaterialAnalyzeVO vo = BeanTransform.copyProperties(bo, MaterialAnalyzeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资分析年盘dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialAnalyzeDTO dto, BindingResult result) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("inventoryType", 2));
            Long count = materialAnalyzeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 物资分析年盘dto
     * @return class MaterialAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialAnalyzeDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("inventoryType", 2));
            List<MaterialAnalyzeBO> boList = materialAnalyzeAPI.list(dto);
            List<MaterialAnalyzeVO> voList = BeanTransform.copyProperties(boList, MaterialAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资分析年盘
     *
     * @param to 物资分析年盘to信息
     * @return class MaterialAnalyzeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MaterialAnalyzeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialAnalyzeBO bo = materialAnalyzeAPI.save(to);
            MaterialAnalyzeVO vo = BeanTransform.copyProperties(bo, MaterialAnalyzeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资分析年盘
     *
     * @param id 物资分析年盘唯一标识
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialAnalyzeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资分析年盘
     *
     * @param to 物资分析年盘to信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MaterialAnalyzeTO to, BindingResult result) throws ActException {
        try {
            materialAnalyzeAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加所有部门下拉值
     *
     * @version v1
     */
    @GetMapping("v1/allOrageDepartment")
    public Result allOrageDepartment() throws ActException {
        try {
            List<String> detail = new ArrayList<>();
            detail = materialAnalyzeAPI.findAddAllDetails();
            return ActResult.initialize(detail);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 添加中所有的地区
     *
     * @version v1
     */
    @GetMapping("v1/allArea")
    public Result allArea() throws ActException {
        try {
            List<AreaBO> area = departmentDetailAPI.findArea();
            return ActResult.initialize(area);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有用户
     *
     * @version v1
     */
    @GetMapping("v1/allGetPerson")
    public Result allGetPerson() throws ActException {
        try {
            List<String> getPerson = new ArrayList<>();
            getPerson = materialAnalyzeAPI.findallMonUser();
            return ActResult.initialize(getPerson);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}