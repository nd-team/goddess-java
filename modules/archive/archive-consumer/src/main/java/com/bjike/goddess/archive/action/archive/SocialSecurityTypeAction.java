package com.bjike.goddess.archive.action.archive;

import com.bjike.goddess.archive.api.SocialSecurityTypeAPI;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.to.SocialSecurityTypeTO;
import com.bjike.goddess.archive.vo.SocialSecurityTypeVO;
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

/**
 * 公司社保购买类型
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 公司社保购买类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("socialsecuritytype")
public class SocialSecurityTypeAction {

    @Autowired
    private SocialSecurityTypeAPI socialSecurityTypeAPI;

    /**
     * 保存
     *
     * @param to 公司社保购买类型传输对象
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) SocialSecurityTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.save(to), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 公司社保购买类型传输对象
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) SocialSecurityTypeTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.update(to), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 公司社保购买类型数据id
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.delete(id), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 公司社保购买类型数据id
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.congeal(id), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 公司社保购买类型数据id
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.thaw(id), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据状态查询公司社保购买类型数据
     *
     * @param status 状态
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @GetMapping("v1/findByStatus")
    public Result findByStatus(Status status) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.findByStatus(status), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询正常状态的公司社保购买类型数据
     *
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.findByStatus(Status.THAW), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 公司社保购买类型数据传输对象
     * @return class SocialSecurityTypeVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SocialSecurityTypeDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(socialSecurityTypeAPI.maps(dto), SocialSecurityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}