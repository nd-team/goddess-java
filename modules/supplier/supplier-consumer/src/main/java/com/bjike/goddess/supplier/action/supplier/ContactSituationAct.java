package com.bjike.goddess.supplier.action.supplier;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.ContactSituationAPI;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.vo.ContactSituationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("contactsituation")
public class ContactSituationAct {

    @Autowired
    private ContactSituationAPI contactSituationAPI;

    /**
     * 根据供应商基本信息ID查询联系情况
     *
     * @param id 供应商基本信息ID
     * @return class ContactSituationVO
     * @version v1
     */
    @GetMapping("v1/findByInformation/{id}")
    public Result findByInformation(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(
                    BeanTransform.copyProperties(
                            contactSituationAPI.findByInformation(id)
                            , ContactSituationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return class ContactSituationVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ContactSituationTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.save(to), ContactSituationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return class ContactSituationVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ContactSituationTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.update(to), ContactSituationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除供应商联系情况数据
     *
     * @param id 供应商联系情况id
     * @return class ContactSituationVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.delete(id), ContactSituationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取供应商联系情况数据
     *
     * @param id 供应商联系情况数据id
     * @return class ContactSituationVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(contactSituationAPI.getById(id), ContactSituationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = contactSituationAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}