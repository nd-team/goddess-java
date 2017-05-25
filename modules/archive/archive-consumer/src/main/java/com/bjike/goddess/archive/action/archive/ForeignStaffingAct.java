package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ForeignStaffingAPI;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.archive.vo.ForeignStaffingVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 对外人员信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("foreignstaffing")
public class ForeignStaffingAct {

    @Autowired
    private ForeignStaffingAPI foreignStaffingAPI;

    /**
     * 保存
     *
     * @param to 对外人员信息传输对象
     * @return class ForeignStaffingVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ForeignStaffingTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingAPI.save(to), ForeignStaffingVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 对外人员信息传输对象
     * @return class ForeignStaffingVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ForeignStaffingTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingAPI.update(to), ForeignStaffingVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 对外人员信息数据id
     * @return class ForeignStaffingVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingAPI.delete(id), ForeignStaffingVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 对外人员信息数据传输对象
     * @return class ForeignStaffingVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ForeignStaffingDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingAPI.maps(dto), ForeignStaffingVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对外人员信息数据
     *
     * @param id 对外人员信息数据id
     * @return class ForeignStaffingVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingAPI.getById(id), ForeignStaffingVO.class, request));
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
            return ActResult.initialize(foreignStaffingAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}