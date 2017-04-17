package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.to.ArrangementTO;
import com.bjike.goddess.organize.vo.ArrangementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位层级操作
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("arrangement")
public class ArrangementAct {

    @Autowired
    private ArrangementAPI arrangementAPI;

    /**
     * 获取未冻结岗位层级数据
     *
     * @return class ArrangementVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            List<ArrangementBO> bos = arrangementAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(bos, ArrangementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存岗位层级数据
     *
     * @param to 岗位层级传输对象
     * @return class ArrangementVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(ArrangementTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(arrangementAPI.save(to), ArrangementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改岗位层级信息
     *
     * @param to 岗位层级传输对象
     * @return class ArrangementVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(ArrangementTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(arrangementAPI.update(to), ArrangementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
