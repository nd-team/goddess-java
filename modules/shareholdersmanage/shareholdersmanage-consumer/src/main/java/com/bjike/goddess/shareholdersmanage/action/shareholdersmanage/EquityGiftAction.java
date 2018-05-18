package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityGiftAPI;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityGiftBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityGiftDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityGiftTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.vo.EquityGiftVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 股权赠与
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equitygift")
public class EquityGiftAction {
    @Autowired
    private EquityGiftAPI equityGiftAPI;
    @Autowired
    private EquityTransactRecordAPI equityTransactRecordAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, javax.servlet.http.HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = equityGiftAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param equityGiftDTO 股权赠与dto
     * @des 获取所有股权赠与总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EquityGiftDTO equityGiftDTO) throws ActException {
        try {
            Long count = equityGiftAPI.countGift(equityGiftDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股权赠与
     *
     * @param id 股权赠与id
     * @return class EquityGiftVO
     * @des 根据id获取股权赠与
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            EquityGiftVO equityGiftVO = BeanTransform.copyProperties(
                    equityGiftAPI.getOne(id), EquityGiftVO.class);
            return ActResult.initialize(equityGiftVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股权赠与列表
     *
     * @param equityGiftDTO 股权赠与dto
     * @return class EquityGiftVO
     * @des 获取所有股权赠与
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(EquityGiftDTO equityGiftDTO, HttpServletRequest request) throws ActException {
        try {
            List<EquityGiftVO> equityGiftVOS = BeanTransform.copyProperties(
                    equityGiftAPI.findList(equityGiftDTO), EquityGiftVO.class, request);
            return ActResult.initialize(equityGiftVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股权赠与
     *
     * @param equityGiftTO 股权赠与to
     * @return class EquityGiftVO
     * @des 添加股权赠与
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addInher(@Validated({ADD.class}) EquityGiftTO equityGiftTO, BindingResult result) throws ActException {
        try {
            EquityGiftBO equityGiftBO = equityGiftAPI.save(equityGiftTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityGiftBO, EquityGiftVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股权赠与
     *
     * @param equityGiftTO 股权赠与数据bo
     * @return class EquityGiftVO
     * @des 编辑股权赠与
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) EquityGiftTO equityGiftTO, BindingResult result) throws ActException {
        try {
            EquityGiftBO equityGiftBO = equityGiftAPI.edit(equityGiftTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityGiftBO, EquityGiftVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股权赠与
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            equityGiftAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据赠与人链接数据
     *
     * @param shareholderName 被继承人
     * @des 根据赠与人链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/percentage")
    public Result getLinkDate(@RequestParam String shareholderName) throws ActException {
        try {
            EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordAPI.getByName(shareholderName);
            String equityType = equityTransactRecordBO.getEquityType();
            return ActResult.initialize(equityType);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}