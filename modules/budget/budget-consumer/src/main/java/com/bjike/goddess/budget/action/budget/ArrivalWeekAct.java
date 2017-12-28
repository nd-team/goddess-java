package com.bjike.goddess.budget.action.budget;

import com.bjike.goddess.budget.api.ArrivalWeekAPI;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.bo.ArrivalWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.vo.ArrivalWeekCountVO;
import com.bjike.goddess.budget.vo.ArrivalWeekVO;
import com.bjike.goddess.budget.vo.SonPermissionObject;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 地区收入周
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("arrivalweek")
public class ArrivalWeekAct extends BaseFileAction{
    @Autowired
    private ArrivalWeekAPI arrivalWeekAPI;
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
    public Result i() throws ActException {
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

            List<SonPermissionObject> hasPermissionList = arrivalWeekAPI.sonPermission();
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

            Boolean isHasPermission = arrivalWeekAPI.guidePermission(guidePermissionTO);
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
     * 添加
     *
     * @param to      地区收入周信息
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) ArrivalWeekTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            ArrivalWeekBO bo = arrivalWeekAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 地区收入周信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ArrivalWeekTO to, BindingResult result) throws ActException {
        try {
            arrivalWeekAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 地区收入周id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            arrivalWeekAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     地区收入周分页信息
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ArrivalWeekDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekBO> list = arrivalWeekAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      地区收入周id
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/arrivalweek/{id}")
    public Result arrivalweek(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            ArrivalWeekBO bo = arrivalWeekAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @param request 请求对象
     * @return class ArrivalWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekCountBO> list = arrivalWeekAPI.count();
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分地区汇总
     *
     * @param dto 地区收入周dto
     * @return class ArrivalWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/conditionsCount")
    public Result conditionsCount(ArrivalWeekDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekCountBO> list = arrivalWeekAPI.conditionsCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(ArrivalWeekDTO dto) throws ActException {
        try {
            Long num = arrivalWeekAPI.countNum(dto);
            return ActResult.initialize(num);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有地区
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findAllArrivals")
    public Result findAllArrivals() throws ActException {
        try {
            List<String> list = arrivalWeekAPI.findAllArrivals();
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * excel模板下载
     *
     * @des 下载模板地区收入周
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "地区收入周模板.xlsx";
            super.writeOutFile(response, arrivalWeekAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 按条件汇总
     *
     * @param dto 地区收入周dto
     * @return class ArrivalWeekCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(ArrivalWeekDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekCountBO> list = arrivalWeekAPI.collect(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}