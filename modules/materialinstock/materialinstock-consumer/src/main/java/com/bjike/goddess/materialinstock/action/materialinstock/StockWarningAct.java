package com.bjike.goddess.materialinstock.action.materialinstock;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.materialinstock.api.StockWarningAPI;
import com.bjike.goddess.materialinstock.bo.StockWarningBO;
import com.bjike.goddess.materialinstock.dto.StockWarningDTO;
import com.bjike.goddess.materialinstock.to.StockWarningTO;
import com.bjike.goddess.materialinstock.vo.StockWarningVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 根据id查询库存预警
     *
     * @param id      库存预警唯一标识
     * @param request
     * @return class StockWarningVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findbyid/{id}")
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
     * 分页查询库存预警
     *
     * @param dto           库存预警dto
     * @param bindingResult
     * @param request
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
     * @param to      库存预警to
     * @param result
     * @param request
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
     * @param to     库存预警to
     * @param result
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