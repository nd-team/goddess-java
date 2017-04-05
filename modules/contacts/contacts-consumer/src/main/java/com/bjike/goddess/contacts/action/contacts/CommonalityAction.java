package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.dto.CommonalityDTO;
import com.bjike.goddess.contacts.to.CommonalityTO;
import com.bjike.goddess.contacts.vo.CommonalityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 公共邮箱管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:45 ]
 * @Description: [ 公共邮箱管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/commonality")
public class CommonalityAction {

    @Autowired
    private CommonalityAPI commonalityAPI;

    /**
     * 保存
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.save(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.update(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.delete(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PatchMapping("v1/congeal/{id}")
    public Result congeal(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.congeal(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param to 公共邮箱管理传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @PatchMapping("v1/thaw/{id}")
    public Result thaw(CommonalityTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.thaw(to), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/findThaw")
    public Result findThaw() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findThaw(), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表查询
     *
     * @param dto 公共邮箱管理数据传输对象
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(CommonalityDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.maps(dto), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门ID查询公共邮箱
     *
     * @param department 部门ID
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/findByDepartment")
    public Result findByDepartment(String department) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findByDepartment(department), CommonalityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}