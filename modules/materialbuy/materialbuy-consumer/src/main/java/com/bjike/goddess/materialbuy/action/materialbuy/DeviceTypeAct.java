package com.bjike.goddess.materialbuy.action.materialbuy;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialbuy.api.DeviceTypeAPI;
import com.bjike.goddess.materialbuy.bo.DeviceTypeBO;
import com.bjike.goddess.materialbuy.dto.DeviceTypeDTO;
import com.bjike.goddess.materialbuy.to.DeviceTypeTO;
import com.bjike.goddess.materialbuy.vo.DeviceTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备类型
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-19 03:39 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("devicetype")
public class DeviceTypeAct {

    @Autowired
    private DeviceTypeAPI deviceTypeAPI;

    /**
     * 根据id查询设备类型
     *
     * @param id 设备类型唯一标识
     * @return class DeviceTypeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findbyid/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            DeviceTypeBO bo = deviceTypeAPI.findById(id);
            DeviceTypeVO vo = BeanTransform.copyProperties(bo, DeviceTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询设备类型
     *
     * @param dto 设备类型dto
     * @param bindingResult
     * @return class DeviceTypeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated DeviceTypeDTO dto, BindingResult bindingResult) throws ActException {
        try {
            List<DeviceTypeBO> boList = deviceTypeAPI.list(dto);
            List<DeviceTypeVO> voList = BeanTransform.copyProperties(boList, DeviceTypeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加设备类型
     *
     * @param to 设备类型to
     * @param result
     * @return class DeviceTypeVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) DeviceTypeTO to, BindingResult result) throws ActException {
        try {
            DeviceTypeBO bo = deviceTypeAPI.save(to);
            DeviceTypeVO vo = BeanTransform.copyProperties(bo, DeviceTypeVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除设备类型
     *
     * @param id 设备类型唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            deviceTypeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑设备类型
     *
     * @param to 设备类型to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated DeviceTypeTO to, BindingResult result) throws ActException {
        try {
            deviceTypeAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}