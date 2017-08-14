package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.DividendsAPI;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.bo.DividendsConditionsBO;
import com.bjike.goddess.staffshares.bo.DividendsDetailBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.to.DividendsTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.vo.CompanySchemeVO;
import com.bjike.goddess.staffshares.vo.DividendsConditionsVO;
import com.bjike.goddess.staffshares.vo.DividendsDetailVO;
import com.bjike.goddess.staffshares.vo.DividendsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 干股分红表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 11:14 ]
 * @Description: [ 干股分红表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dividends")
public class DividendsAction {
    @Autowired
    private DividendsAPI dividendsAPI;

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

            Boolean isHasPermission = dividendsAPI.guidePermission(guidePermissionTO);
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
     * 公司干股交易情况
     *
     * @return class CompanySchemeVO
     * @version v1
     */
    @GetMapping("v1/detail")
    public Result detail() throws ActException {
        try {
            List<CompanySchemeBO> companySchemeBOs = dividendsAPI.detail();
            return ActResult.initialize(BeanTransform.copyProperties(companySchemeBOs, CompanySchemeVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分红
     *
     * @param to 公司干股分红
     * @version v1
     */
    @GetMapping("v1/dividends/{id}")
    public Result dividends(@Validated(ADD.class) DividendsTO to) throws ActException {
        try {
            dividendsAPI.dividends(to);
            return ActResult.initialize("分红成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 干股分红列表
     *
     * @param dto 干股分红数据传输对象
     * @return class DividendsVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(DividendsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dividendsAPI.maps(dto), DividendsVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取干股分红
     *
     * @param id 干股分红id
     * @return class DividendsVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dividendsAPI.getById(id), DividendsVO.class));
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
    public Result getTotal(DividendsDTO dto) throws ActException {
        try {
            return ActResult.initialize(dividendsAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认分红
     *
     * @version v1
     */
    @PostMapping("v1/confirm/{id}")
    public Result confirm(@Validated(EDIT.class) DividendsTO to) throws ActException {
        try {
            dividendsAPI.confirm(to);
            return ActResult.initialize("确认成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交易持股明细列表
     *
     * @param dto 交易持股明细数据传输对象
     * @return class DividendsDetailVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DividendsDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dividendsAPI.list(dto), DividendsDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取交易持股明细
     *
     * @param id 交易持股明细id
     * @return class DividendsDetailVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dividendsAPI.find(id), DividendsDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取交易持股明细总条数
     *
     * @version v1
     */
    @GetMapping("v1/count")
    public Result getCount(DividendsDTO dto) throws ActException {
        try {
            return ActResult.initialize(dividendsAPI.getCount(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交易持股明细汇总
     *
     * @return class DividendsDetailVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect() throws ActException {
        try {
            List<DividendsDetailBO> dividendsDetailBOs = dividendsAPI.collect();
            return ActResult.initialize(BeanTransform.copyProperties(dividendsDetailBOs, DividendsDetailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司干股情况汇总
     *
     * @return class DividendsConditionsVO
     * @version v1
     */
    @GetMapping("v1/detail/list")
    public Result detailList() throws ActException {
        try {
            List<DividendsConditionsBO> dividendsConditionsBOs = dividendsAPI.detailList();
            return ActResult.initialize(BeanTransform.copyProperties(dividendsConditionsBOs, DividendsConditionsVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}