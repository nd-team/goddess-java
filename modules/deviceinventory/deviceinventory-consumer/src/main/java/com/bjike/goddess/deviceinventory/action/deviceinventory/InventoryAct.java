package com.bjike.goddess.deviceinventory.action.deviceinventory;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.deviceinventory.api.InventoryAPI;
import com.bjike.goddess.deviceinventory.bo.InventoryBO;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.to.InventoryTO;
import com.bjike.goddess.deviceinventory.vo.InventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 盘点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 08:54 ]
 * @Description: [ 盘点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("inventory")
public class InventoryAct {
    @Autowired
    private InventoryAPI inventoryAPI;

    /**
     * 盘点列表
     *
     * @param dto 盘点dto
     * @return class InventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InventoryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<InventoryBO> list = inventoryAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找盘点信息
     *
     * @param id 盘点id
     * @return class InventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/inventoryOne/{id}")
    public Result inventoryOne(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InventoryBO bo = inventoryAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, InventoryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 盘点
     *
     * @param to 盘点to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/inventory")
    public Result inventory(@Validated({EDIT.class}) InventoryTO to, BindingResult result) throws ActException {
        try {
            inventoryAPI.inventory(to);
            return ActResult.initialize("盘点成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出(根据时间区间，时间具体到日)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class InventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/export/{startTime}/{endTime}")
    public Result export(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<InventoryBO> list = inventoryAPI.export(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 盘点dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InventoryDTO dto) throws ActException {
        try {
            return ActResult.initialize(inventoryAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}