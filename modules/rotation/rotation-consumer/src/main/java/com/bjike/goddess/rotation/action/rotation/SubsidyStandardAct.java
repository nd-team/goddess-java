package com.bjike.goddess.rotation.action.rotation;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rotation.api.SubsidyStandardAPI;
import com.bjike.goddess.rotation.dto.SubsidyStandardDTO;
import com.bjike.goddess.rotation.to.SubsidyStandardTO;
import com.bjike.goddess.rotation.vo.SubsidyStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位补贴标准
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:06 ]
 * @Description: [ 岗位补贴标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("subsidystandard")
public class SubsidyStandardAct {

    @Autowired
    private SubsidyStandardAPI subsidyStandardAPI;

    /**
     * 保存
     *
     * @param to 岗位补贴标准传输对象
     * @return class SubsidyStandardVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SubsidyStandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.save(to), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 岗位补贴标准传输对象
     * @return class SubsidyStandardVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SubsidyStandardTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.update(to), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位补贴标准数据id
     * @return class SubsidyStandardVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.delete(id), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 岗位补贴标准数据id
     * @return class SubsidyStandardVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.congeal(id), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 岗位补贴标准数据id
     * @return class SubsidyStandardVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.thaw(id), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询岗位补贴标准数据
     *
     * @param id 岗位补贴标准数据id
     * @return class SubsidyStandardVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.getById(id), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 岗位补贴标准数据传输对象
     * @return class SubsidyStandardVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SubsidyStandardDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.maps(dto), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    public Result getTotal() throws ActException {
        return null;
    }

    /**
     * 根据岗位层级查询岗位补贴标准数据
     *
     * @param arrangement 岗位层级
     * @return class SubsidyStandardVO
     * @version v1
     */
    @GetMapping("v1/findByArrangement")
    public Result findByArrangement(String arrangement, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.findByArrangement(arrangement), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结层级
     *
     * @return class SubsidyStandardVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(subsidyStandardAPI.findThaw(), SubsidyStandardVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}