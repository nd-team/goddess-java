package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DimensionAPI;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.to.DimensionTO;
import com.bjike.goddess.organize.vo.DimensionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 维度操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:24]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("dimension")
public class DimensionAct {

    @Autowired
    private DimensionAPI dimensionAPI;

    /**
     * 保存维度信息
     *
     * @param to 维度传输对象
     * @return class DimensionVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DimensionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DimensionBO bo = dimensionAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改维度信息
     *
     * @param to 维度传输对象
     * @return class DimensionVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DimensionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DimensionBO bo = dimensionAPI.update(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取未冻结维度信息
     *
     * @return class DimensionVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.findStatus(), DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭
     *
     * @param id 维度数据id
     * @return class DimensionVO
     * @version v1
     */
    @PutMapping("v1/close/{id}")
    public Result close(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.close(id), DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启
     *
     * @param id 维度数据id
     * @return class DimensionVO
     * @version v1
     */
    @PutMapping("v1/open/{id}")
    public Result open(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.open(id), DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 维度数据id
     * @return class DimensionVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.delete(id), DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 维度数据传输
     * @return class DimensionVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DimensionDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.maps(dto), DimensionVO.class, request));
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
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(dimensionAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询维度
     *
     * @param id 维度数据id
     * @return class DimensionVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.findById(id), DimensionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
