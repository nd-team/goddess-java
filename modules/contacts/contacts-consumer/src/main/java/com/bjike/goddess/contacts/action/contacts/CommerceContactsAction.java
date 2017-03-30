package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommerceContactsAPI;
import com.bjike.goddess.contacts.to.CommerceContactsTO;
import com.bjike.goddess.contacts.vo.CommerceContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商务通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 06:04 ]
 * @Description: [ 商务通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/commercecontacts")
public class CommerceContactsAction {

    @Autowired
    private CommerceContactsAPI commerceContactsAPI;

    /**
     * 保存
     *
     * @param to 商务通讯录传输对象
     * @return class CommerceContactsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) CommerceContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceContactsAPI.save(to), CommerceContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 商务通讯录传输对象
     * @return class CommerceContactsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) CommerceContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commerceContactsAPI.update(to), CommerceContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 商务通讯录传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(CommerceContactsTO to) throws ActException {
        try {
            commerceContactsAPI.delete(to);
            return new ActResult();
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    ;

}