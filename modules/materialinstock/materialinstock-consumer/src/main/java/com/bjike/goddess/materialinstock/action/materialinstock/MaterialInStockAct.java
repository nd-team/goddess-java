package com.bjike.goddess.materialinstock.action.materialinstock;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.MaterialInStockAPI;
import com.bjike.goddess.materialinstock.bo.MaterialInStockBO;
import com.bjike.goddess.materialinstock.dto.MaterialInStockDTO;
import com.bjike.goddess.materialinstock.to.MaterialInStockTO;
import com.bjike.goddess.materialinstock.vo.MaterialInStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 物资入库
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("materialinstock")
public class MaterialInStockAct {

    @Autowired
    private MaterialInStockAPI materialInStockAPI;

    /**
     * 根据id查询物资入库
     *
     * @param id      物资入库唯一标识
     * @param request
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findbyid/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MaterialInStockBO bo = materialInStockAPI.findById(id);
            MaterialInStockVO vo = BeanTransform.copyProperties(bo, MaterialInStockVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询物资入库
     *
     * @param dto          物资入库dto
     * @param bindingResult
     * @param request
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated MaterialInStockDTO dto, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MaterialInStockBO> boList = materialInStockAPI.list(dto);
            List<MaterialInStockVO> voList = BeanTransform.copyProperties(boList, MaterialInStockVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加物资入库
     *
     * @param to      物资入库to
     * @param result
     * @param request
     * @return class MaterialInStockVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated MaterialInStockTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            MaterialInStockBO bo = materialInStockAPI.save(to);
            MaterialInStockVO vo = BeanTransform.copyProperties(bo, MaterialInStockVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除物资入库
     *
     * @param id 物资入库唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            materialInStockAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑物资入库
     *
     * @param to     物资入库to
     * @param result
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated MaterialInStockTO to, BindingResult result) throws ActException {
        try {
            materialInStockAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}