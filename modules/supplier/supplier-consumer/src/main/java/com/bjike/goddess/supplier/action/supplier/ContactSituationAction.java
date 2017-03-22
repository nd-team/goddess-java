package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.ContactSituationAPI;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import com.bjike.goddess.supplier.vo.ContactSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 联系情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.705 ]
 * @Description: [ 联系情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("supplier/contactsituation")
public class ContactSituationAction {

    @Autowired
    private ContactSituationAPI contactSituationAPI;

    /**
     * 根据供应商基本信息ID查询联系情况
     *
     * @param info_id 供应商基本信息ID
     * @return class ContactSituationVO
     */
    @GetMapping("findByInformation/{info_id}")
    public Result findByInformation(@PathVariable String info_id) throws ActException {
        try {
            return ActResult.initialize(
                    BeanTransform.copyProperties(
                            contactSituationAPI.findByInformation(info_id)
                            , ContactSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return class ContactSituationVO
     */
    @PostMapping("save")
    public Result save(@Validated ContactSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.save(to), ContactSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return class ContactSituationVO
     */
    @PutMapping("update/{id}")
    public Result update(@Validated ContactSituationTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.update(to), ContactSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商联系情况数据
     *
     * @param id 供应商联系情况id
     * @return class ContactSituationVO
     */
    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.delete(id), ContactSituationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}