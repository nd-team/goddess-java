package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.ChangeEquityTypeAPI;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordAPI;
import com.bjike.goddess.shareholdersmanage.bo.ChangeEquityTypeBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.dto.ChangeEquityTypeDTO;
import com.bjike.goddess.shareholdersmanage.entity.ChangeEquityType;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.to.ChangeEquityTypeTO;
import com.bjike.goddess.shareholdersmanage.vo.ChangeEquityTypeVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 变更股权类型
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:42 ]
 * @Description: [ 变更股权类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("changeequitytype")
public class ChangeEquityTypeAction {
    @Autowired
    private ChangeEquityTypeAPI changeEquityTypeAPI;

    /**
     * 列表总条数
     *
     * @param changeEquityTypeDTO 变更股权类型dto
     * @des 获取所有变更股权类型总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ChangeEquityTypeDTO changeEquityTypeDTO) throws ActException {
        try {
            Long count = changeEquityTypeAPI.countChangeType(changeEquityTypeDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个变更股权类型
     *
     * @param id 变更股权类型id
     * @return class ChangeEquityTypeVO
     * @des 根据id获取变更股权类型
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            ChangeEquityTypeVO changeEquityTypeVO = BeanTransform.copyProperties(
                    changeEquityTypeAPI.getOne(id), ChangeEquityTypeVO.class);
            return ActResult.initialize(changeEquityTypeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 变更股权类型列表
     *
     * @param changeEquityTypeDTO 变更股权类型dto
     * @return class ChangeEquityTypeVO
     * @des 获取所有变更股权类型
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findChangeEquity(ChangeEquityTypeDTO changeEquityTypeDTO, HttpServletRequest request) throws ActException {
        try {
            List<ChangeEquityTypeVO> changeEquityTypeVOS = BeanTransform.copyProperties(
                    changeEquityTypeAPI.findList(changeEquityTypeDTO), ChangeEquityTypeVO.class, request);
            return ActResult.initialize(changeEquityTypeVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加变更股权类型
     *
     * @param changeEquityTypeTO 变更股权类型to
     * @return class ChangeEquityTypeVO
     * @des 添加变更股权类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEqurfer(@Validated({ADD.class}) ChangeEquityTypeTO changeEquityTypeTO, BindingResult result) throws ActException {
        try {
            ChangeEquityTypeBO changeEquityTypeBO = changeEquityTypeAPI.save(changeEquityTypeTO);
            return ActResult.initialize(BeanTransform.copyProperties(changeEquityTypeBO, ChangeEquityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑变更股权类型
     *
     * @param changeEquityTypeTO 变更股权类型bo
     * @return class ChangeEquityTypeVO
     * @des 编辑变更股权类型
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) ChangeEquityTypeTO changeEquityTypeTO, BindingResult result) throws ActException {
        try {
            ChangeEquityTypeBO changeEquityTypeBO = changeEquityTypeAPI.edit(changeEquityTypeTO);
            return ActResult.initialize(BeanTransform.copyProperties(changeEquityTypeBO, ChangeEquityTypeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除变更股权类型
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            changeEquityTypeAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}