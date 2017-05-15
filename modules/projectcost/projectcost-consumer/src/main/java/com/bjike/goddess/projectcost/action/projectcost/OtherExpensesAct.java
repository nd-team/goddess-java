package com.bjike.goddess.projectcost.action.projectcost;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.api.OtherExpensesAPI;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.to.OtherExpensesTO;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.vo.OtherExpensesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 其他费用
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:02 ]
 * @Description: [ 其他费用 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("otherexpenses")
public class OtherExpensesAct {

    @Autowired
    private OtherExpensesAPI otherExpensesAPI;

    /**
     * 保存
     *
     * @param to 其他费用传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OtherExpensesTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.save(to), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 其他费用传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) OtherExpensesTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.update(to), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 其他费用数据id
     * @return class OtherExpensesVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.delete(id), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 其他费用数据传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(OtherExpensesDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.maps(dto), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id 其他费用数据id
     * @return class OtherExpensesVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.getById(id), OtherExpensesVO.class, request));
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
            return ActResult.initialize(otherExpensesAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据查询条件查询数据
     *
     * @param to 查询条件传输对象
     * @return class OtherExpensesVO
     * @version v1
     */
    @GetMapping("v1/findByTo")
    public Result findByTO(FindTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherExpensesAPI.findByTO(to), OtherExpensesVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}