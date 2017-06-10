package com.bjike.goddess.coststandard.action.coststandard;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.coststandard.api.CostStandardContrastAPI;
import com.bjike.goddess.coststandard.dto.CostStandardContrastDTO;
import com.bjike.goddess.coststandard.to.CostStandardContrastTO;
import com.bjike.goddess.coststandard.vo.CostStandardContrastVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 费用标准对比
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:15 ]
 * @Description: [ 费用标准对比 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("coststandardcontrast")
public class CostStandardContrastAction {

    @Autowired
    private CostStandardContrastAPI costStandardContrastAPI;

    /**
     * 保存
     *
     * @param to 费用标准对比传输对象
     * @return class CostStandardContrastVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CostStandardContrastTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costStandardContrastAPI.save(to), CostStandardContrastVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 费用标准对比传输对象
     * @return class CostStandardContrastVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CostStandardContrastTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costStandardContrastAPI.update(to), CostStandardContrastVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 费用标准对比数据id
     * @return class CostStandardContrastVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costStandardContrastAPI.delete(id), CostStandardContrastVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取费用标准对比数据
     *
     * @param id 费用标准对比数据id
     * @return class CostStandardContrastVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costStandardContrastAPI.getById(id), CostStandardContrastVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 费用标准对比数据传输对象
     * @return class CostStandardContrastVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CostStandardContrastDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(costStandardContrastAPI.maps(dto), CostStandardContrastVO.class, request));
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
            return ActResult.initialize(costStandardContrastAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}