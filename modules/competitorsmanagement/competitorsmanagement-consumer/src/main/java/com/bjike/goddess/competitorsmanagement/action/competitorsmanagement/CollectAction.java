package com.bjike.goddess.competitorsmanagement.action.competitorsmanagement;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitorsmanagement.api.CollectAPI;
import com.bjike.goddess.competitorsmanagement.vo.CollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 竞争对手管理汇总
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 04:55 ]
 * @Description: [ 竞争对手管理汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collect")
public class CollectAction {
    @Autowired
    private CollectAPI collectAPI;

    //竞争对手汇总 未完成
    @GetMapping("v1/getcollet")
    public Result getCollet() throws SerException {
        List<CollectVO> vos = BeanTransform.copyProperties(collectAPI.getCollet(), CollectVO.class);
        return new ActResult("success", vos);
    }
}