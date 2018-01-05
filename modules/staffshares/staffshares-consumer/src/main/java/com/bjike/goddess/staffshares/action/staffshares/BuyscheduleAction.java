package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.BuyscheduleAPI;
import com.bjike.goddess.staffshares.api.SellscheduleAPI;
import com.bjike.goddess.staffshares.bo.BuyscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import com.bjike.goddess.staffshares.vo.BuyscheduleCollectVO;
import com.bjike.goddess.staffshares.vo.BuyscheduleVO;
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
 * 买入记录表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("buyschedule")
public class BuyscheduleAction {
    @Autowired
    private BuyscheduleAPI buyscheduleAPI;
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

            Boolean isHasPermission = buyscheduleAPI.guidePermission(guidePermissionTO);
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
     * 买入记录表列表
     *
     * @param dto 买入记录表数据传输对象
     * @return class BuyscheduleVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(BuyscheduleDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(buyscheduleAPI.maps(dto), BuyscheduleVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取买入记录表数据
     *
     * @param id 买入记录表数据id
     * @return class BuyscheduleVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(buyscheduleAPI.getById(id), BuyscheduleVO.class));
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
    public Result getTotal(BuyscheduleDTO buyscheduleDTO) throws ActException {
        try {
            return ActResult.initialize(buyscheduleAPI.getTotal(buyscheduleDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 出售
     *
     * @version v1
     */
    @GetMapping("v1/sell/{id}")
    public Result sell(@Validated(EDIT.class) SellscheduleTO to) throws ActException {
        try {
            sellscheduleAPI.sell(to);
            return ActResult.initialize("出售成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总
     *
     * @return class BuyscheduleCollectVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect() throws ActException {
        try {
            List<BuyscheduleCollectBO> buyscheduleBOList = buyscheduleAPI.collect();
            return ActResult.initialize(BeanTransform.copyProperties(buyscheduleBOList, BuyscheduleCollectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}