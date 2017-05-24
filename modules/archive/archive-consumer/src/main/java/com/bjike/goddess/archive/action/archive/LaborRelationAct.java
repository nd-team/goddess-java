package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.LaborRelationAPI;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.archive.vo.LaborRelationVO;
import com.bjike.goddess.archive.vo.LaborRelationVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 劳动关系类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("laborrelation")
public class LaborRelationAct {

    @Autowired
    private LaborRelationAPI laborRelationAPI;

    /**
     * 保存
     *
     * @param to 劳动关系类型传输对象
     * @return class LaborRelationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) LaborRelationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.save(to), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 劳动关系类型传输对象
     * @return class LaborRelationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) LaborRelationTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.update(to), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 劳动关系类型数据id
     * @return class LaborRelationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.delete(id), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 劳动关系类型数据id
     * @return class LaborRelationVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.congeal(id), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 劳动关系类型数据id
     * @return class LaborRelationVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.thaw(id), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询劳动关系类型数据
     *
     * @param status 状态
     * @return class LaborRelationVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Status status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.findByStatus(status), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的劳动关系类型数据
     *
     * @return class LaborRelationVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.findByStatus(Status.THAW), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 劳动关系类型数据传输对象
     * @return class LaborRelationVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(LaborRelationDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.maps(dto), LaborRelationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取劳动关系类型数据
     *
     * @param id 劳动关系类型数据id
     * @return class LaborRelationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(laborRelationAPI.getById(id), LaborRelationVO.class, request));
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
            return ActResult.initialize(laborRelationAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}