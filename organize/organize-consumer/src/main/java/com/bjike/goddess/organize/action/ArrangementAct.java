package com.bjike.goddess.organize.action;

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
@RequestMapping("{version}/arrangement")
public class ArrangementAct {

    @Autowired
    private ArrangementAPI arrangementAPI;

    @GetMapping("findStatus")
    public Result findStatus() throws ActException {
        try {
            List<ArrangementBO> bos = arrangementAPI.findStatus();
            return ActResult.initialize(BeanTransform.copyProperties(bos, ArrangementVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
//
//    @DeleteMapping("delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            arrangementAPI.remove(id);
//            return ActResult.initialize(id);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

    @PostMapping("save")
    public Result save(ArrangementTO to) throws ActException {
        try {
            return ActResult.initialize(arrangementAPI.save(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @PutMapping("update/{id}")
    public Result update(ArrangementTO to) throws ActException {
        try {
            return ActResult.initialize(arrangementAPI.update(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
