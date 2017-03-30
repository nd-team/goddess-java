package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.OtherContactsAPI;
import com.bjike.goddess.contacts.dto.OtherContactsDTO;
import com.bjike.goddess.contacts.to.OtherContactsTO;
import com.bjike.goddess.contacts.vo.OtherContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 其他通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:44 ]
 * @Description: [ 其他通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/othercontacts")
public class OtherContactsAction {

    @Autowired
    private OtherContactsAPI otherContactsAPI;

    /**
     * 保存
     *
     * @param to 其他通讯录传输对象
     * @return class OtherContactsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) OtherContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherContactsAPI.save(to), OtherContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 其他通讯录传输对象
     * @return class OtherContactsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) OtherContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherContactsAPI.update(to), OtherContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 其他通讯录传输对象
     * @return class OtherContactsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(OtherContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherContactsAPI.delete(to), OtherContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto 其他通讯录数据传输对象
     * @return class OtherContactsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(OtherContactsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(otherContactsAPI.maps(dto), OtherContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}