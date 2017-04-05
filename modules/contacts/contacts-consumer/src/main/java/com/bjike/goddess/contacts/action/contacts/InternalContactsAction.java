package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;
import com.bjike.goddess.contacts.vo.InternalContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 内部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/internalcontacts")
public class InternalContactsAction {

    @Autowired
    private InternalContactsAPI internalContactsAPI;

    /**
     * 保存
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) InternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.save(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) InternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.update(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 内部通讯录传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(InternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.delete(to), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询邮箱不为空数据
     *
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findEmail")
    public Result findEmailNotNull() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.findEmailNotNull(), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户ID查询通讯录
     *
     * @param user_id 用户ID
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findByUser")
    public Result findByUser(String user_id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.findByUser(user_id), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 内部通讯录数据传输对象
     * @return class InternalContactsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(InternalContactsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(internalContactsAPI.maps(dto), InternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}