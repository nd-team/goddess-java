package com.bjike.goddess.organize.action;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.to.HierarchyTO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 体系操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:30]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("{version}/hierarchy")
public class HierarchyAct {

    @Autowired
    private HierarchyAPI hierarchyAPI;

    @PostMapping("v1/save")
    public Result save(@Validated HierarchyTO to) throws ActException {
        try {
            HierarchyVO vo = BeanTransform.copyProperties(hierarchyAPI.save(to), HierarchyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
