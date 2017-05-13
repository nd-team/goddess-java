package com.bjike.goddess.projectcost.action.projectcost;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.api.ArtificialCostAPI;
import com.bjike.goddess.projectcost.dto.ArtificialCostDTO;
import com.bjike.goddess.projectcost.to.ArtificialCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.vo.ArtificialCostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 人工费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:19 ]
 * @Description: [ 人工费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("artificialcost")
public class ArtificialCostAct {

    @Autowired
    private ArtificialCostAPI artificialCostAPI;

    /**
     * 保存
     *
     * @param to 人工费用传输对象
     * @return class ArtificialCostVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ArtificialCostTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.save(to), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 人工费用传输对象
     * @return class ArtificialCostVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ArtificialCostTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.update(to), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 人工费用数据id
     * @return class ArtificialCostVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.delete(id), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 人工费用数据传输对象
     * @return class ArtificialCostVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ArtificialCostDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.maps(dto), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改实际人工工时
     *
     * @param to 人工费用传输对象
     * @return class ArtificialCostVO
     * @version v1
     */
    @PutMapping("v1/updateActual/{id}")
    public Result updateActual(ArtificialCostTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.updateActual(to), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id 人工费用数据id
     * @return class ArtificialCostVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.getById(id), ArtificialCostVO.class, request));
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
            return ActResult.initialize(artificialCostAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据查询条件查询数据
     *
     * @param to 查询条件传输对象
     * @return class ArtificialCostVO
     * @version v1
     */
    @GetMapping("v1/findByTo")
    public Result findByTO(FindTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(artificialCostAPI.findByTO(to), ArtificialCostVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}