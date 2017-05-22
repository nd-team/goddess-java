package com.bjike.goddess.deviceinventory.action.deviceinventory;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.deviceinventory.api.InventoryRecordAPI;
import com.bjike.goddess.deviceinventory.bo.InventoryRecordBO;
import com.bjike.goddess.deviceinventory.dto.InventoryRecordDTO;
import com.bjike.goddess.deviceinventory.vo.InventoryRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 盘点记录
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-19 09:33 ]
 * @Description: [ 盘点记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("inventoryrecord")
public class InventoryRecordAct {
    @Autowired
    private InventoryRecordAPI inventoryRecordAPI;

    /**
     * 盘点记录列表
     *
     * @param dto 盘点dto
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InventoryRecordDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/areaCount")
    public Result areaCount(HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.areaCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 部门汇总
     *
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projectGroupCount")
    public Result projectGroupCount(HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.projectGroupCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 物资名称汇总
     *
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/materialNameCount")
    public Result materialNameCount(HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.materialNameCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 状态汇总
     *
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/stateCount")
    public Result stateCount(HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.stateCount();
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出(根据时间区间，时间具体到日)
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class InventoryRecordVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/export/{startTime}/{endTime}")
    public Result export(@PathVariable String startTime, @PathVariable String endTime, HttpServletRequest request) throws ActException {
        try {
            List<InventoryRecordBO> list = inventoryRecordAPI.export(startTime, endTime);
            return ActResult.initialize(BeanTransform.copyProperties(list, InventoryRecordVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 盘点记录dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InventoryRecordDTO dto) throws ActException {
        try {
            return ActResult.initialize(inventoryRecordAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}