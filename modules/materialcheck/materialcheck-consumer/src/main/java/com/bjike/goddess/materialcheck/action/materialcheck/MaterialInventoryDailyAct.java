package com.bjike.goddess.materialcheck.action.materialcheck;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
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
 * 物资分析-日盘
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-08 04:00 ]
 * @Description: [ 物资分析-日盘 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialinventory_daily")
public class MaterialInventoryDailyAct {

    @Autowired
    private MaterialInventoryAPI materialInventoryAPI;

    /**
     * 根据id查询物资分析-日盘
     *
     * @param id 物资分析-日盘唯一标识
     * @return class MaterialInventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/devicerepair/{id}")
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
     * @param dto 物资分析-日盘dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated MaterialInventoryDTO dto, BindingResult result) throws ActException {
        try {
            Long count = materialInventoryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 物资分析-日盘dto
     * @return class MaterialInventoryVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialInventoryDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("inventoryType", InventoryType.DAILY_INVENTORY));
            List<MaterialInventoryBO> boList = materialInventoryAPI.list(dto);
            List<MaterialInventoryVO> voList = BeanTransform.copyProperties(boList, MaterialInventoryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资分析-日盘
     *
     * @param to 物资分析-日盘to信息
     * @return class MaterialInventoryVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MaterialInventoryTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            to.setInventoryType(InventoryType.DAILY_INVENTORY);
            MaterialInventoryBO bo = materialInventoryAPI.save(to);
            MaterialInventoryVO vo = BeanTransform.copyProperties(bo, MaterialInventoryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资分析-日盘
     *
     * @param id 物资分析-日盘唯一标识
     * @throws ActException
     * @version v1
     */
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
     * 编辑物资分析-日盘
     *
     * @param to 物资分析-日盘to信息
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MaterialInventoryTO to, BindingResult result) throws ActException {
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
     * @param id             物资分析-日盘唯一标识
     * @param operatorStatus 经办人确认情况
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/operatorStatus/{id}")
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
     * @param id            物资分析-日盘唯一标识
     * @param accountStatus 账务模块核实情况
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/accountModuleConfirm/{id}")
    public Result accountModuleConfirm(@PathVariable final String id, @RequestParam(value = "accountStatus") String accountStatus) throws ActException {
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
     * @param id        物资分析-日盘唯一标识
     * @param zjbStatus 总经办审核意见
     * @throws ActException
     * @version v1
     */
    @PatchMapping
    public Result zjbConfirm(@PathVariable String id, @RequestParam(value = "zjbStatus") String zjbStatus) throws ActException {
        try {
            materialInventoryAPI.zjbConfirm(id, zjbStatus);
            return new ActResult("zjbConfirm success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}