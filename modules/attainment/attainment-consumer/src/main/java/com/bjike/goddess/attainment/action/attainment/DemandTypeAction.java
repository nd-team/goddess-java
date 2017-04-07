package com.bjike.goddess.attainment.action.attainment;

import com.bjike.goddess.attainment.api.DemandTypeAPI;
import com.bjike.goddess.attainment.to.DemandTypeTO;
import com.bjike.goddess.attainment.vo.DemandTypeVO;
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

/**
 * 调研需求类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:46 ]
 * @Description: [ 调研需求类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("attainment/demandtype")
public class DemandTypeAction {

    @Autowired
    private DemandTypeAPI demandTypeAPI;

    /**
     * 保存
     *
     * @param to 调研需求类型传输对象
     * @return class DemandTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) DemandTypeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.save(to), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 调研需求类型传输对象
     * @return class DemandTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) DemandTypeTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.update(to), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 调研需求类型数据id
     * @return class DemandTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.delete(id), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 调研需求类型数据id
     * @return class DemandTypeVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.congeal(id), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 调研需求类型数据id
     * @return class DemandTypeVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.thaw(id), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结数据
     *
     * @return class DemandTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(demandTypeAPI.findThaw(), DemandTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}