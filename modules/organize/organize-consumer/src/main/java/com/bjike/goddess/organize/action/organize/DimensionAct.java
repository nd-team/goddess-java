package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DimensionAPI;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.to.DimensionTO;
import com.bjike.goddess.organize.vo.DimensionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 维度操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:24]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("dimension")
public class DimensionAct {

    @Autowired
    private DimensionAPI dimensionAPI;

    /**
     * 保存维度信息
     *
     * @param to 维度传输对象
     * @return class DimensionVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(DimensionTO to) throws ActException {
        try {
            DimensionBO bo = dimensionAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DimensionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改维度信息
     *
     * @param to 维度传输对象
     * @return class DimensionVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(DimensionTO to) throws ActException {
        try {
            DimensionBO bo = dimensionAPI.update(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, DimensionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取未冻结维度信息
     *
     * @return class DimensionVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(dimensionAPI.findStatus(), DimensionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
