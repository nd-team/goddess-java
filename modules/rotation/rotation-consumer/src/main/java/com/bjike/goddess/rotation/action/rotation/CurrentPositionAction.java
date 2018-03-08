package com.bjike.goddess.rotation.action.rotation;

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
import com.bjike.goddess.organize.api.DepartmentDetailAPI;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.rotation.api.CurrentPositionAPI;
import com.bjike.goddess.rotation.bo.CurrentPositionBO;
import com.bjike.goddess.rotation.dto.CurrentPositionDTO;
import com.bjike.goddess.rotation.excel.CurrentPositionImport;
import com.bjike.goddess.rotation.to.CurrentPositionTO;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.vo.CurrentPositionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 目前岗位情况
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:30 ]
 * @Description: [ 目前岗位情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("currentposition")
public class CurrentPositionAction extends BaseFileAction{

    @Autowired
    CurrentPositionAPI currentPositionAPI;
    @Autowired
    DepartmentDetailAPI departmentDetailAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = currentPositionAPI.guidePermission(guidePermissionTO);
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
     * 获取当前岗位列表(分页)
     *
     * @param dto dto
     * @return class CurrentPositionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(CurrentPositionDTO dto) throws ActException{
        try {
            List<CurrentPositionBO> bos = currentPositionAPI.list(dto);
            List<CurrentPositionVO> vos = BeanTransform.copyProperties(bos, CurrentPositionVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @param dto dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(CurrentPositionDTO dto) throws ActException{
        try {

            Long count = currentPositionAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加当前岗位
     *
     * @param to to
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(CurrentPositionTO to) throws ActException{
        try {
            currentPositionAPI.add(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 更新当前岗位
     *
     * @param to to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result modify(@Validated(EDIT.class) CurrentPositionTO to, BindingResult bindingResult) throws ActException{
        try {
            currentPositionAPI.update(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除当前岗位
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result list(@PathVariable String id) throws ActException{
        try {
            currentPositionAPI.delete(id);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取一个当前岗位列
     *
     * @param id id
     * @return class CurrentPositionVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException{
        try {
            CurrentPositionBO bo = currentPositionAPI.getOne(id);
            CurrentPositionVO vo = BeanTransform.copyProperties(bo, CurrentPositionVO.class);
            return ActResult.initialize(vo);
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
    @PostMapping("v1/import")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<CurrentPositionImport> tos = ExcelUtil.excelToClazz(is, CurrentPositionImport.class, excel);
            List<CurrentPositionTO> tocs = new ArrayList<>();
            for (CurrentPositionImport str : tos) {
                CurrentPositionTO to = BeanTransform.copyProperties(str, CurrentPositionTO.class,
                        "rotation", "delay", "delayRotation");

                //是否应该轮岗
                if (StringUtils.isNotBlank(str.getRotation())) {
                    to.setRotation(str.getRotation().equals("是"));
                }
                //是否延后
                if (StringUtils.isNotBlank(str.getDelay())) {
                    to.setDelay(str.getDelay().equals("是"));
                }
                //（延后）是否应该轮岗
                if (StringUtils.isNotBlank(str.getDelayRotation())) {
                    to.setDelayRotation(str.getDelayRotation().equals("是"));
                }

                tocs.add(to);
            }
            //注意序列化
            currentPositionAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出
     *
     * @param dto dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result export(CurrentPositionDTO dto, HttpServletResponse response) throws ActException{
        try {
            String fileName = "岗位轮换.xlsx";
            super.writeOutFile(response, currentPositionAPI.exportExcel(dto), fileName);
            return new ActResult("导出成功");
        } catch (SerException | IOException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出模板
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportTemplate")
    public Result exportTemplate(HttpServletResponse response) throws ActException{
        try {
            String fileName = "岗位轮换模板.xlsx";
            super.writeOutFile(response, currentPositionAPI.exportTemplate(), fileName);
            return new ActResult("导出成功");
        } catch (SerException | IOException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取部门、项目组
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/project")
    public Result project() throws ActException{
        try {
            List<String> list = departmentDetailAPI.findAllDepartment();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区
     *
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/area")
    public Result area() throws ActException{
        try {
            List<AreaBO> bos = departmentDetailAPI.findArea();
            Set<String> list = new HashSet<>();
            for (AreaBO areaBO : bos) {
                list.add(areaBO.getArea());
            }
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}