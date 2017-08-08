package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.PurchaseAPI;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.SchemeDTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.to.SchemeApplyTO;
import com.bjike.goddess.staffshares.vo.SchemeIssueVO;
import com.bjike.goddess.staffshares.vo.SchemeVO;
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
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(schemeAPI.getOne(id), SchemeIssueVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请购买
     *
     * @param to 申请购买
     *
     * @version v1
     */
    @PutMapping("v1/buy/{id}")
    public Result update(@Validated(EDIT.class) PurchaseTO to, BindingResult result) throws ActException {
        try {
            purchaseAPI.buy(to);
            return ActResult.initialize("已申请购买");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}