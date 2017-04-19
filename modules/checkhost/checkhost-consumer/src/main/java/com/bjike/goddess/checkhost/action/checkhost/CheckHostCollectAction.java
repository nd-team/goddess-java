package com.bjike.goddess.checkhost.action.checkhost;

import com.bjike.goddess.checkhost.api.CheckHostCollectAPI;
import com.bjike.goddess.checkhost.bo.CheckHostCollectBO;
import com.bjike.goddess.checkhost.vo.CheckHostCollectVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 汇总表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:13 ]
 * @Description: [ 汇总表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("checkhostcollect")
public class CheckHostCollectAction {
    @Autowired
    private CheckHostCollectAPI checkHostCollectAPI;
    /**
     * 汇总表
     *
     * @param name name
     * @return class CheckHostCollectVO
     * @des 根据名字汇总表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result listCheckHostCollect(String name) throws ActException {
        try {
            CheckHostCollectBO checkHostCollectBO = checkHostCollectAPI.listCheckHostCollect(name);
            CheckHostCollectVO checkHostCollectVO = BeanTransform.copyProperties(checkHostCollectBO, CheckHostCollectVO.class);
            return ActResult.initialize(checkHostCollectVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}