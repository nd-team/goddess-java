package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.AttainmentWayAPI;
import com.bjike.goddess.attainment.dto.AttainmentWayDTO;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.attainment.vo.AttainmentWayVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 调研方式
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attainmentway")
public class AttainmentWayAct {

    @Autowired
    private AttainmentWayAPI attainmentWayAPI;

    /**
     * 保存
     *
     * @param to 调研方式传输对象
     * @return class AttainmentWayVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) AttainmentWayTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.save(to), AttainmentWayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研方式传输对象
     * @return class AttainmentWayVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) AttainmentWayTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.update(to), AttainmentWayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研方式数据id
     * @return class AttainmentWayVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.delete(id), AttainmentWayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 调研方式数据id
     * @return class AttainmentWayVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.congeal(id), AttainmentWayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 调研方式数据id
     * @return class AttainmentWayVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.thaw(id), AttainmentWayVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结数据
     *
     * @return class AttainmentWayVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.findThaw(), AttainmentWayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 调研方式数据传输对象
     * @return class AttainmentWayVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AttainmentWayDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.maps(dto), AttainmentWayVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取调研方式数据
     *
     * @param id 调研方式数据id
     * @return class AttainmentWayVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(attainmentWayAPI.getById(id), AttainmentWayVO.class));
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
            return ActResult.initialize(attainmentWayAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}