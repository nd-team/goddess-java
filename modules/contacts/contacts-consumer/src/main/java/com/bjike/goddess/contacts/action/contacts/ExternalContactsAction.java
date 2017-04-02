package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.ExternalContactsAPI;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.contacts.vo.ExternalContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 外部通讯录
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/externalcontacts")
public class ExternalContactsAction {

    @Autowired
    private ExternalContactsAPI externalContactsAPI;

    /**
     * 保存
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ExternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.save(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ExternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.update(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to 外部通讯录传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(ExternalContactsTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.delete(to), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据地区查询
     *
     * @param area 地区
     * @return class ExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/findByArea")
    public Result findByArea(String area) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.findByArea(area), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表查询
     *
     * @param dto 外部通讯录数据传输对象
     * @return class ExternalContactsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(ExternalContactsDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(externalContactsAPI.maps(dto), ExternalContactsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}