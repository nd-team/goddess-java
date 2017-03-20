package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.OperateAPI;
import com.bjike.goddess.organize.to.OperateTO;
import com.bjike.goddess.organize.vo.OperateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 操作类型操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("operate")
public class OperateAct {

    @Autowired
    private OperateAPI operateAPI;

    /**
     * 保存操作类型信息
     *
     * @param to 操作类型传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(OperateTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(operateAPI.save(to), OperateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改操作类型信息
     *
     * @param to 操作类型传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(OperateTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(operateAPI.update(to), OperateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的操作类型信息
     *
     * @return class OperateVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(operateAPI.findStatus(), OperateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
