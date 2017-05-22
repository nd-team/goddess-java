package com.bjike.goddess.regionalprogresscollect.action.regionalprogresscollect;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regionalprogresscollect.api.MonthTargetAPI;
import com.bjike.goddess.regionalprogresscollect.dto.MonthTargetDTO;
import com.bjike.goddess.regionalprogresscollect.to.MonthTargetTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;
import com.bjike.goddess.regionalprogresscollect.vo.MonthTargetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 月指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 月指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("monthtarget")
public class MonthTargetAction {

    @Autowired
    private MonthTargetAPI monthTargetAPI;

    /**
     * 保存
     *
     * @param to 月指标传输对象
     * @return class MonthTargetVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) MonthTargetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.save(to), MonthTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 月指标传输对象
     * @return class MonthTargetVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) MonthTargetTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.update(to), MonthTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 月指标数据id
     * @return class MonthTargetVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.delete(id), MonthTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询月指标数据
     *
     * @param id 月指标数据id
     * @return class MonthTargetVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.getById(id), MonthTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑标准
     *
     * @param to 标准修改传输对象
     * @return class MonthTargetVO
     * @version v1
     */
    @PutMapping("v1/standard/{id}")
    public Result updateStandard(@Validated(ADD.class) StandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.updateStandard(to), MonthTargetVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 月指标数据传输对象
     * @return class MonthTargetVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(MonthTargetDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthTargetAPI.maps(dto), MonthTargetVO.class, request));
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
            return ActResult.initialize(monthTargetAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}