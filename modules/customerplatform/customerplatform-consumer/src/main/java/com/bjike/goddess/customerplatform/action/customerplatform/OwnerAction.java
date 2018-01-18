package com.bjike.goddess.customerplatform.action.customerplatform;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customerplatform.api.OwnerAPI;
import com.bjike.goddess.customerplatform.dto.OwnerDTO;
import com.bjike.goddess.customerplatform.to.GuidePermissionTO;
import com.bjike.goddess.customerplatform.to.OwnerTO;
import com.bjike.goddess.customerplatform.vo.OwnerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 业主
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-30 11:27 ]
 * @Description: [ 业主 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("owner")
public class OwnerAction {
    @Autowired
    private OwnerAPI ownerAPI;

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

            Boolean isHasPermission = ownerAPI.guidePermission(guidePermissionTO);
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
     * @param to 业主传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OwnerTO to, BindingResult result) throws ActException {
        try {
            ownerAPI.save(to);
            return ActResult.initialize("ADD SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 业主传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) OwnerTO to, BindingResult result) throws ActException {
        try {
            ownerAPI.update(to);
            return ActResult.initialize("EDIT SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 业主传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            ownerAPI.delete(id);
            return ActResult.initialize("DELETE SUCCESS");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 业主数据传输对象
     * @return class OwnerVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(OwnerDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(ownerAPI.maps(dto), OwnerVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取业主数据
     *
     * @param id 业主数据id
     * @return class OwnerVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(ownerAPI.getById(id), OwnerVO.class));
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
    public Result getTotal(OwnerDTO dto) throws ActException {
        try {
            return ActResult.initialize(ownerAPI.getTotal(dto));
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
            return ActResult.initialize(ownerAPI.getProvinces());
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
            return ActResult.initialize(ownerAPI.getCity(provinces));
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
            return ActResult.initialize(ownerAPI.getArea(provinces,city));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}