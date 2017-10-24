package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.WithUnitAPI;
import com.bjike.goddess.financeinit.bo.WithUnitBO;
import com.bjike.goddess.financeinit.dto.WithUnitDTO;
import com.bjike.goddess.financeinit.entity.WithUnit;
import com.bjike.goddess.financeinit.excel.SonPermissionObject;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.WithUnitTO;
import com.bjike.goddess.financeinit.vo.WithUnitVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 往来单位
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:28 ]
 * @Description: [ 往来单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("withunit")
public class WithUnitAction extends BaseFileAction{
    @Autowired
    private WithUnitAPI withUnitAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;


    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = withUnitAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = withUnitAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param withUnitDTO 往来单位dto
     * @des 获取所有往来单位总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WithUnitDTO withUnitDTO) throws ActException {
        try {
            Long count = withUnitAPI.countWith(withUnitDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个往来单位
     *
     * @param id 往来单位id
     * @return class WithUnitVO
     * @des 根据id获取往来单位
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            WithUnitVO withUnitVO = BeanTransform.copyProperties(
                    withUnitAPI.getOneById(id), WithUnitVO.class);
            return ActResult.initialize(withUnitVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 往来单位列表
     *
     * @param withUnitDTO 往来单位dto
     * @return class WithUnitVO
     * @des 获取所有往来单位
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(WithUnitDTO withUnitDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<WithUnitVO> companyBasicInfoVOS = BeanTransform.copyProperties(
                    withUnitAPI.listWith(withUnitDTO), WithUnitVO.class, request);
            return ActResult.initialize(companyBasicInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加往来单位
     *
     * @param withUnitTO 往来单位数据to
     * @return class WithUnitVO
     * @des 添加往来单位
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) WithUnitTO withUnitTO, BindingResult bindingResult) throws ActException {
        try {
            WithUnitBO withUnitBO = withUnitAPI.addWith(withUnitTO);
            return ActResult.initialize(BeanTransform.copyProperties(withUnitBO, WithUnitVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑往来单位
     *
     * @param withUnitTO 往来单位数据bo
     * @return class WithUnitVO
     * @des 编辑往来单位
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAccount(@Validated(value = EDIT.class) WithUnitTO withUnitTO, BindingResult bindingResult) throws ActException {
        try {
            WithUnitBO withUnitBO = withUnitAPI.editWith(withUnitTO);
            return ActResult.initialize(BeanTransform.copyProperties(withUnitBO, WithUnitVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除往来单位记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            withUnitAPI.deleteWith(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出往来单位
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "往来单位.xlsx";
            super.writeOutFile(response, withUnitAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}