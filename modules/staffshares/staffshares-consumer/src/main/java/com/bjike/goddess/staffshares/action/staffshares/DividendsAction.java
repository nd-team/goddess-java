package com.bjike.goddess.staffshares.action.staffshares;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffshares.api.DividendsAPI;
import com.bjike.goddess.staffshares.bo.CompanySchemeBO;
import com.bjike.goddess.staffshares.dto.DividendsDTO;
import com.bjike.goddess.staffshares.to.DividendsTO;
import com.bjike.goddess.staffshares.vo.CompanySchemeVO;
import com.bjike.goddess.staffshares.vo.DividendsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}