package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.DetailsAPI;
import com.bjike.goddess.staffshares.api.PurchaseAPI;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.api.SellscheduleAPI;
import com.bjike.goddess.staffshares.bo.DetailsBO;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;
import com.bjike.goddess.staffshares.vo.DetailsVO;
import com.bjike.goddess.staffshares.vo.SchemeIssueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 交易详情
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("details")
public class DetailsAction {

    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private PurchaseAPI purchaseAPI;
    @Autowired
    private DetailsAPI detailsAPI;
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

            Boolean isHasPermission = detailsAPI.guidePermission(guidePermissionTO);
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
     * 交易中心列表
     *
     * @param dto 员工持股管理数据传输对象
     * @return class SchemeIssueVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SchemeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SchemeIssueBO> list = schemeAPI.list(dto);
            if (null != list && list.size() > 0) {
                return ActResult.initialize(BeanTransform.copyProperties(list, SchemeIssueVO.class, request));
            } else {
                return ActResult.initialize(list);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得一条交易中心数据
     *
     * @param id id
     * @return class SchemeIssueVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(schemeAPI.getOne(id), SchemeIssueVO.class));
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
    public Result getTotal(SchemeDTO schemeDTO) throws ActException {
        try {
            return ActResult.initialize(schemeAPI.count(schemeDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请购买
     *
     * @param to 申请购买
     * @version v1
     */
    @PutMapping("v1/buy/{id}")
    public Result buy(@Validated(EDIT.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.buy(to);
            return ActResult.initialize("已申请购买");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 交易详情列表
     *
     * @param dto 员工持股管理数据传输对象
     * @return class DetailsVO
     * @version v1
     */
    @GetMapping("v1/detail/list/{id}")
    public Result listDetail(DetailsDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DetailsBO> list = detailsAPI.listDetail(dto);
            if (null != list && list.size() > 0) {
                return ActResult.initialize(BeanTransform.copyProperties(list, DetailsVO.class, request));
            } else {
                return ActResult.initialize(list);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获得一条交易详情数据
     *
     * @param id id
     * @return class DetailsVO
     * @version v1
     */
    @GetMapping("v1/details/findById/{id}")
    public Result getDetailById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(detailsAPI.getDetailById(id), DetailsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取交易详情总条数
     *
     * @version v1
     */
    @GetMapping("v1/details/getTotal")
    public Result getTotal(DetailsDTO detailsDTO) throws ActException {
        try {
            return ActResult.initialize(detailsAPI.getTotal(detailsDTO));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 购买
     *
     * @version v1
     */
    @PutMapping("v1/detail/buy/{id}")
    public Result detailBuy(@Validated(EDIT.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            detailsAPI.buy(to);
            return ActResult.initialize("已申请购买");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 回收
     *
     * @version v1
     */
    @PutMapping("v1/recovery/{id}")
    public Result recovery(@PathVariable String id) throws ActException {
        try {
            detailsAPI.recovery(id);
            return ActResult.initialize("回收成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 出售
     *
     * @version v1
     */
    @PutMapping("v1/sell/{id}")
    public Result sell(@Validated(EDIT.class) SellscheduleTO to,BindingResult bindingResult) throws ActException {
        try {
            detailsAPI.sell(to);
            return ActResult.initialize("出售成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}