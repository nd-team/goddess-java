package com.bjike.goddess.contacts.action.contacts;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.contacts.bo.ContactsCollectBO;
import com.bjike.goddess.contacts.to.CollectTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 通讯录管理汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-16 05:08 ]
 * @Description: [ 通讯录管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contactscollect")
public class ContactsCollectAct {

    @Autowired
    private InternalContactsAPI internalContactsAPI;

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

            Boolean isHasPermission = internalContactsAPI.guidePermission(guidePermissionTO);
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

    /**
     * 通讯录信息管理日汇总
     *
     * @param to to
     * @return class ContactsCollectBO
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(CollectTO to) throws ActException {
        try {
            List<ContactsCollectBO> boList = internalContactsAPI.dayCollect(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通讯录信息管理周汇总
     *
     * @param to to
     * @return class ContactsCollectBO
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(CollectTO to) throws ActException {
        try {
            List<ContactsCollectBO> boList = internalContactsAPI.weekCollect(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通讯录信息管理月汇总
     *
     * @param to to
     * @return class ContactsCollectBO
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(CollectTO to) throws ActException {
        try {
            List<ContactsCollectBO> boList = internalContactsAPI.monthCollect(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通讯录信息管理累计汇总
     *
     * @param to to
     * @return class ContactsCollectBO
     * @version v1
     */
    @GetMapping("v1/totalCollect")
    public Result totalCollect(CollectTO to) throws ActException {
        try {
            List<ContactsCollectBO> boList = internalContactsAPI.totalCollect(to);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}