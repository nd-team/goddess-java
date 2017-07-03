package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.ForeignStaffingSetAPI;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
import com.bjike.goddess.archive.vo.ForeignStaffingSetVO;
import com.bjike.goddess.archive.vo.ForeignStaffingSetVO;
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
 * 对外人员基本信息设置
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("foreignstaffingset")
public class ForeignStaffingSetAct {

    @Autowired
    private ForeignStaffingSetAPI foreignStaffingSetAPI;

    /**
     * 保存
     *
     * @param to 对外人员基本信息设置传输对象
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ForeignStaffingSetTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.save(to), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 对外人员基本信息设置传输对象
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ForeignStaffingSetTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.update(to), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 对外人员基本信息设置数据id
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.delete(id), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 对外人员基本信息设置数据id
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.congeal(id), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 对外人员基本信息设置数据id
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.thaw(id), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询对外人员基本信息设置数据
     *
     * @param status 状态
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Status status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.findByStatus(status), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的对外人员基本信息设置数据
     *
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.findByStatus(Status.THAW), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 对外人员基本信息设置数据传输对象
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ForeignStaffingSetDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.maps(dto), ForeignStaffingSetVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对外人员基本信息数据
     *
     * @param id 对外人员基本信息数据id
     * @return class ForeignStaffingSetVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(foreignStaffingSetAPI.getById(id), ForeignStaffingSetVO.class, request));
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
            return ActResult.initialize(foreignStaffingSetAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}