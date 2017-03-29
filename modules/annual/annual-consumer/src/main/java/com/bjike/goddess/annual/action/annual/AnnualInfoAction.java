package com.bjike.goddess.annual.action.annual;

import com.bjike.goddess.annual.api.AnnualInfoAPI;
import com.bjike.goddess.annual.vo.AnnualInfoVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("annual/annualinfo")
public class AnnualInfoAction {

    @Autowired
    private AnnualInfoAPI annualInfoAPI;

    /**
     * 获取指定用户的年假信息
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
}