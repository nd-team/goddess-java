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
import com.bjike.goddess.materialcheck.api.MaterialInventoryAPI;
import com.bjike.goddess.materialcheck.bo.MaterialInventoryBO;
import com.bjike.goddess.materialcheck.dto.MaterialInventoryDTO;
import com.bjike.goddess.materialcheck.to.MaterialInventoryTO;
import com.bjike.goddess.materialcheck.type.InventoryType;
import com.bjike.goddess.materialcheck.vo.MaterialInventoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资盘点周盘
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialinventoryWeekly")
public class MaterialInventoryWeeklyAct {

    @Autowired
    private MaterialInventoryAPI materialInventoryAPI;

    /**
     * 根据id查询物资盘点周盘
     *
     * @param id 物资盘点周盘唯一标识
     * @return class MaterialInventoryVO
     * @version v1
     */
    @GetMapping("v1/materialinventoryWeekly/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialInventoryBO bo = materialInventoryAPI.findById(id);
            MaterialInventoryVO vo = BeanTransform.copyProperties(bo, MaterialInventoryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 物资盘点周盘dto
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialInventoryDTO dto, BindingResult result) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("inventoryType", 1));
            Long count = materialInventoryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 物资盘点周盘dto
     * @return class MaterialInventoryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialInventoryDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("inventoryType", 1));
            List<MaterialInventoryBO> boList = materialInventoryAPI.list(dto);
            List<MaterialInventoryVO> voList = BeanTransform.copyProperties(boList, MaterialInventoryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资盘点周盘
     *
     * @param to 物资盘点周盘to信息
     * @return class MaterialInventoryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) MaterialInventoryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialInventoryBO bo = materialInventoryAPI.save(to);
            MaterialInventoryVO vo = BeanTransform.copyProperties(bo, MaterialInventoryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资盘点周盘
     *
     * @param id 物资盘点周盘唯一标识
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialInventoryAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资盘点周盘
     *
     * @param to 物资盘点周盘to信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) MaterialInventoryTO to, BindingResult result) throws ActException {
        try {
            materialInventoryAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 经办人确认情况
     *
     * @param id             物资盘点周盘唯一标识
     * @param operatorStatus 经办人确认情况
     * @version v1
     */
    @PutMapping("v1/operatorStatus/{id}")
    public Result operatorConfirm(@PathVariable String id, @RequestParam(value = "operatorStatus") String operatorStatus) throws ActException {
        try {
            materialInventoryAPI.operatorConfirm(id, operatorStatus);
            return new ActResult("operatorStatus success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 账务模块核实
     *
     * @param id            物资盘点周盘唯一标识
     * @param accountStatus 账务模块核实情况
     * @version v1
     */
    @PutMapping("v1/accountModuleConfirm/{id}")
    public Result accountModuleConfirm(@PathVariable String id, @RequestParam(value = "accountStatus") String accountStatus) throws ActException {
        try {
            materialInventoryAPI.accountModuleConfirm(id, accountStatus);
            return new ActResult("accountModuleConfirm success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 总经办审核
     *
     * @param id        物资盘点周盘唯一标识
     * @param zjbStatus 总经办审核意见
     * @version v1
     */
    @PutMapping("v1/zjbConfirm/{id}")
    public Result zjbConfirm(@PathVariable String id, @RequestParam(value = "zjbStatus") String zjbStatus) throws ActException {
        try {
            materialInventoryAPI.zjbConfirm(id, zjbStatus);
            return new ActResult("zjbConfirm success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}