package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.EquityInheritanceAPI;
import com.bjike.goddess.shareholdersmanage.api.EquityTransactRecordAPI;
import com.bjike.goddess.shareholdersmanage.bo.EquityInheritanceBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityInheritanceDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityInheritanceTO;
import com.bjike.goddess.shareholdersmanage.vo.EquityInheritanceVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 股权继承
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:06 ]
 * @Description: [ 股权继承 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equityinheritance")
public class EquityInheritanceAction {
    @Autowired
    private EquityInheritanceAPI equityInheritanceAPI;
    @Autowired
    private EquityTransactRecordAPI equityTransactRecordAPI;

    /**
     * 列表总条数
     *
     * @param equityInheritanceDTO 股权继承dto
     * @des 获取所有股权继承总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EquityInheritanceDTO equityInheritanceDTO) throws ActException {
        try {
            Long count = equityInheritanceAPI.countInheritance(equityInheritanceDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股权继承
     *
     * @param id 股权继承id
     * @return class EquityInheritanceVO
     * @des 根据id获取股权继承
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            EquityInheritanceVO equityInheritanceVO = BeanTransform.copyProperties(
                    equityInheritanceAPI.getOne(id), EquityInheritanceVO.class);
            return ActResult.initialize(equityInheritanceVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股权继承列表
     *
     * @param equityInheritanceDTO 股权继承dto
     * @return class EquityInheritanceVO
     * @des 获取所有股权继承
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findEquityTrfer(EquityInheritanceDTO equityInheritanceDTO, HttpServletRequest request) throws ActException {
        try {
            List<EquityInheritanceVO> equityInheritanceVOS = BeanTransform.copyProperties(
                    equityInheritanceAPI.findList(equityInheritanceDTO), EquityInheritanceVO.class, request);
            return ActResult.initialize(equityInheritanceVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加股权继承
     *
     * @param equityInheritanceTO 股权继承to
     * @return class EquityInheritanceBO
     * @des 添加股权继承
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addInher(@Validated({ADD.class}) EquityInheritanceTO equityInheritanceTO, BindingResult result) throws ActException {
        try {
            EquityInheritanceBO equityInheritanceBO = equityInheritanceAPI.save(equityInheritanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityInheritanceBO, EquityInheritanceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑股权继承
     *
     * @param equityInheritanceTO 股权继承数据bo
     * @return class EquityTransferVO
     * @des 股权继承转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) EquityInheritanceTO equityInheritanceTO, BindingResult result) throws ActException {
        try {
            EquityInheritanceBO equityInheritanceBO = equityInheritanceAPI.edit(equityInheritanceTO);
            return ActResult.initialize(BeanTransform.copyProperties(equityInheritanceBO, EquityInheritanceVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除股权继承
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            equityInheritanceAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据被继承人链接数据
     *
     * @param shareholderName 被继承人
     * @des 根据被继承人链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/equityType")
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