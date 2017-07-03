package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.EnclosureTypeAPI;
import com.bjike.goddess.archive.dto.EnclosureTypeDTO;
import com.bjike.goddess.archive.to.EnclosureTypeTO;
import com.bjike.goddess.archive.vo.EnclosureTypeVO;
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
 * 附件类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 附件类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("enclosuretype")
public class EnclosureTypeAct {

    @Autowired
    private EnclosureTypeAPI enclosureTypeAPI;

    /**
     * 保存
     *
     * @param to 附件类型传输对象
     * @return class EnclosureTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) EnclosureTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.save(to), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 附件类型传输对象
     * @return class EnclosureTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) EnclosureTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.update(to), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 附件类型数据id
     * @return class EnclosureTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.delete(id), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 附件类型数据id
     * @return class EnclosureTypeVO
     * @version v1
     */
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.congeal(id), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 附件类型数据id
     * @return class EnclosureTypeVO
     * @version v1
     */
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.thaw(id), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询附件类型数据
     *
     * @param status 状态
     * @return class EnclosureTypeVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Status status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.findByStatus(status), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的附件类型数据
     *
     * @return class EnclosureTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.findByStatus(Status.THAW), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 附件类型数据传输对象
     * @return class EnclosureTypeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(EnclosureTypeDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.maps(dto), EnclosureTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取附件类型数据
     *
     * @param id 附件类型数据id
     * @return class EnclosureTypeVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(enclosureTypeAPI.getById(id), EnclosureTypeVO.class, request));
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
            return ActResult.initialize(enclosureTypeAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}