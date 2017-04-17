package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.DesignNumberInfoAPI;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;
import com.bjike.goddess.organize.vo.DesignNumberInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编号设计信息操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:26]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("designNumberInfo")
public class DesignNumberInfoAct {

    @Autowired
    private DesignNumberInfoAPI designNumberInfoAPI;

    /**
     * 保存编号设计信息
     *
     * @param to 编号设计信息传输对象
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(DesignNumberInfoTO to) throws ActException {
        try {
            DesignNumberInfoBO bo = designNumberInfoAPI.save(to);
            return new ActResult(BeanTransform.copyProperties(bo, DesignNumberInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改编号设计信息
     *
     * @param to 编号设计信息传输对象
     * @return class DesignNumberInfoVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(DesignNumberInfoTO to) throws ActException {
        try {
            DesignNumberInfoBO bo = designNumberInfoAPI.update(to);
            return new ActResult(BeanTransform.copyProperties(bo, DesignNumberInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
