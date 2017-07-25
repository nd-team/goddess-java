package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualInfoAPI;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.vo.AnnualInfoVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 年假信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 03:30 ]
 * @Description: [ 年假信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("annualinfo")
public class AnnualInfoAct {

    @Autowired
    private AnnualInfoAPI annualInfoAPI;

    /**
     * 获取指定用户的年假信息(我的年假记录)
     *
     * @param username 用户名
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findByUsername")
    public Result findByUsername(String username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.findByUsername(username), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户名查询年假信息
     *
     * @param username 用户名
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findByUsers")
    public Result findByUsers(String[] username) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.findByUsers(username), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 查询列表
     *
     * @param dto 年假信息数据传输对象
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(AnnualInfoDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.maps(dto), AnnualInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id获取年假信息数据
     *
     * @param id 年假信息数据id
     * @return class AnnualInfoVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result getById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(annualInfoAPI.getById(id), AnnualInfoVO.class));
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
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(annualInfoAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}