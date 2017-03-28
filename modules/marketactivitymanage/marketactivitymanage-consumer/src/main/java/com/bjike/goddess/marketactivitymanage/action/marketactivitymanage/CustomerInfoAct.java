package com.bjike.goddess.marketactivitymanage.action.marketactivitymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketactivitymanage.api.CustomerInfoAPI;
import com.bjike.goddess.marketactivitymanage.bo.CustomerInfoBO;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeApplyBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeApplyTO;
import com.bjike.goddess.marketactivitymanage.vo.CustomerInfoVO;
import com.bjike.goddess.marketactivitymanage.vo.MarketServeApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户信息
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 07:03 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("marketactivitymanage/customerinfo")
public class CustomerInfoAct {

    @Autowired
    private CustomerInfoAPI customerInfoAPI;

    /**
     * 获取列表
     *
     * @param dto 客户信息dto
     * @return class CustomerInfoVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CustomerInfoDTO dto) throws ActException {
        try {
            List<CustomerInfoBO> boList = customerInfoAPI.list(dto);
            List<CustomerInfoVO> voList = BeanTransform.copyProperties(boList, CustomerInfoVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @return class CustomerInfoVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) CustomerInfoTO to) throws ActException {
        try {
            CustomerInfoBO bo = customerInfoAPI.save(to);
            CustomerInfoVO vo = BeanTransform.copyProperties(bo, CustomerInfoVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除客户信息
     *
     * @param id 客户信息唯一标识
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            customerInfoAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(CustomerInfoTO to) throws ActException {
        try {
            customerInfoAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}