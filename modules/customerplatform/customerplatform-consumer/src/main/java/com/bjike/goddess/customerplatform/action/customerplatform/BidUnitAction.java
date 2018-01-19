package com.bjike.goddess.customerplatform.action.customerplatform;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customerplatform.api.BidUnitAPI;
import com.bjike.goddess.customerplatform.dto.BidUnitDTO;
import com.bjike.goddess.customerplatform.entity.SonPermissionObject;
import com.bjike.goddess.customerplatform.to.BidUnitTO;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.vo.BidUnitVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 中标单位
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:20 ]
 * @Description: [ 中标单位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("bidunit")
public class BidUnitAction {
    @Autowired
    private BidUnitAPI bidUnitAPI;
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

            List<SonPermissionObject> hasPermissionList = bidUnitAPI.sonPermission();
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

            Boolean isHasPermission = bidUnitAPI.guidePermission(guidePermissionTO);
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
     * 保存
     *
     * @param to 中标单位传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) BidUnitTO to, BindingResult result) throws ActException {
        try {
            bidUnitAPI.save(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 中标单位传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) BidUnitTO to, BindingResult result) throws ActException {
        try {
            bidUnitAPI.update(to);
            return ActResult.initialize("EDIT SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 中标单位传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            bidUnitAPI.delete(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 中标单位数据传输对象
     * @return class BidUnitVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(BidUnitDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(bidUnitAPI.maps(dto), BidUnitVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取中标单位数据
     *
     * @param id 中标单位数据id
     * @return class BidUnitVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(bidUnitAPI.getById(id), BidUnitVO.class));
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
    public Result getTotal(BidUnitDTO dto) throws ActException {
        try {
            return ActResult.initialize(bidUnitAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有省份
     *
     * @version v1
     */
    @GetMapping("v1/provinces")
    public Result getProvinces() throws ActException {
        try {
            return ActResult.initialize(bidUnitAPI.getProvinces());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据省份获取市
     *
     * @version v1
     */
    @GetMapping("v1/city")
    public Result getCity(String provinces) throws ActException {
        try {
            return ActResult.initialize(bidUnitAPI.getCity(provinces));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据省份市获取区
     *
     * @version v1
     */
    @GetMapping("v1/area")
    public Result getArea(String provinces,String city) throws ActException {
        try {
            return ActResult.initialize(bidUnitAPI.getArea(provinces,city));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}