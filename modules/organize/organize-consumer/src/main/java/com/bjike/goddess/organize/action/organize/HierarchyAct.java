package com.bjike.goddess.organize.action.organize;

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
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("hierarchy")
public class HierarchyAct {

    @Autowired
    private HierarchyAPI hierarchyAPI;

    /**
     * 保存体系信息
     *
     * @param to 体系传输对象
     * @return class HierarchyVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated HierarchyTO to) throws ActException {
        try {
            HierarchyVO vo = BeanTransform.copyProperties(hierarchyAPI.save(to), HierarchyVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改体系信息
     *
     * @param to 体系传输对象
     * @return class HierarchyVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(HierarchyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.update(to), HierarchyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体系信息
     *
     * @return class HierarchyVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(hierarchyAPI.findStatus(), HierarchyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
