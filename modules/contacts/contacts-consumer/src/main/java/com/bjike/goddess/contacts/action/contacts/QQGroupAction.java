package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.QQGroupAPI;
import com.bjike.goddess.contacts.dto.QQGroupDTO;
import com.bjike.goddess.contacts.to.QQGroupTO;
import com.bjike.goddess.contacts.vo.QQGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * QQ群管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contacts/qqgroup")
public class QQGroupAction {

    @Autowired
    private QQGroupAPI qqGroupAPI;

    /**
     * 保存
     *
     * @param to QQ群管理传输对象
     * @return class QQGroupVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) QQGroupTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qqGroupAPI.save(to), QQGroupVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to QQ群管理传输对象
     * @return class QQGroupVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) QQGroupTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qqGroupAPI.save(to), QQGroupVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param to QQ群管理传输对象
     * @return class QQGroupVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(QQGroupTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qqGroupAPI.update(to), QQGroupVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭QQ群
     *
     * @param to QQ群管理传输对象
     * @return class QQGroupVO
     * @version v1
     */
    @PutMapping("v1/close/{id}")
    public Result close(QQGroupTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qqGroupAPI.delete(to), QQGroupVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表数据
     *
     * @param dto QQ群管理数据传输对象
     * @return class QQGroupVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(QQGroupDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(qqGroupAPI.maps(dto), QQGroupVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}