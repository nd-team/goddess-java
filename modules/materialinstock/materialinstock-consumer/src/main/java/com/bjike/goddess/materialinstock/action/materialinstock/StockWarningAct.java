package com.bjike.goddess.materialinstock.action.materialinstock;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.StockWarningAPI;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.excel.SonPermissionObject;
import com.bjike.goddess.materialinstock.to.GuidePermissionTO;
import com.bjike.goddess.materialinstock.to.StockWarningTO;
import com.bjike.goddess.materialinstock.vo.StockWarningVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存预警
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 05:00 ]
 * @Description: [ 库存预警 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("stockwarning")
public class StockWarningAct {

    @Autowired
    private StockWarningAPI stockWarningAPI;
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

            List<SonPermissionObject> hasPermissionList = stockWarningAPI.sonPermission();
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

            Boolean isHasPermission = stockWarningAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询库存预警
     *
     * @param id 库存预警唯一标识
     * @return class StockWarningVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/stockwarning/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            StockWarningBO bo = stockWarningAPI.findById(id);
            StockWarningVO vo = BeanTransform.copyProperties(bo, StockWarningVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 库存预警dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated StockWarningDTO dto, BindingResult result) throws ActException {
        try {
            Long count = stockWarningAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询库存预警
     *
     * @param dto 库存预警dto
     * @return class StockWarningVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated StockWarningDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<StockWarningBO> boList = stockWarningAPI.list(dto);
            List<StockWarningVO> voList = BeanTransform.copyProperties(boList, StockWarningVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加库存预警
     *
     * @param to 库存预警to
     * @return class StockWarningVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) StockWarningTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            StockWarningBO bo = stockWarningAPI.save(to);
            StockWarningVO vo = BeanTransform.copyProperties(bo, StockWarningVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除库存预警
     *
     * @param id 库存预警唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            stockWarningAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑库存预警
     *
     * @param to 库存预警to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) StockWarningTO to, BindingResult result) throws ActException {
        try {
            stockWarningAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}