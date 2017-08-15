package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.SellscheduleAPI;
import com.bjike.goddess.staffshares.bo.SellscheduleCollectBO;
import com.bjike.goddess.staffshares.bo.TransactionBO;
import com.bjike.goddess.staffshares.dto.SellscheduleDTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.vo.SellscheduleCollectVO;
import com.bjike.goddess.staffshares.vo.SellscheduleVO;
import com.bjike.goddess.staffshares.vo.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 出售记录表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:15 ]
 * @Description: [ 出售记录表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("sellschedule")
public class SellscheduleAction {
    @Autowired
    private SellscheduleAPI sellscheduleAPI;

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

            Boolean isHasPermission = sellscheduleAPI.guidePermission(guidePermissionTO);
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
     * 出售记录表列表
     *
     * @param dto 出售记录表数据传输对象
     * @return class SellscheduleVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(SellscheduleDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(sellscheduleAPI.maps(dto), SellscheduleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取出售记录表数据
     *
     * @param id 出售记录表数据id
     * @return class SellscheduleVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(sellscheduleAPI.getById(id), SellscheduleVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(SellscheduleDTO sellscheduleDTO) throws ActException {
        try {
            return ActResult.initialize(sellscheduleAPI.getTotal(sellscheduleDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect() throws ActException {
        try {
            List<SellscheduleCollectBO> buyscheduleBOList = sellscheduleAPI.collect();
            return ActResult.initialize(BeanTransform.copyProperties(buyscheduleBOList, SellscheduleCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交易汇总表
     *
     * @version v1
     */
    @GetMapping("v1/transaction/collect")
    public Result transaction() throws ActException {
        try {
            List<TransactionBO> transactionBOs = sellscheduleAPI.transaction();
            return ActResult.initialize(BeanTransform.copyProperties(transactionBOs, TransactionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}