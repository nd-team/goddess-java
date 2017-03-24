package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.AngleAPI;
import com.bjike.goddess.organize.bo.AngleBO;
import com.bjike.goddess.organize.to.AngleTO;
import com.bjike.goddess.organize.vo.AngleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角度操作
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("angle")
public class AngleAct {

    @Autowired
    private AngleAPI angleAPI;

    /**
     * 保存角度信息
     *
     * @param to 角度传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(AngleTO to) throws ActException {
        try {
            return ActResult.initialize(angleAPI.saveAsTo(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改角度信息
     *
     * @param id 角度ID
     * @param to 角度传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@PathVariable String id, AngleTO to) throws ActException {
        try {
            AngleBO bo = BeanTransform.copyProperties(to, AngleBO.class, true);
            to.setId(id);
            angleAPI.updateAsTo(to);
            return ActResult.initialize(to);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取未冻结角度数据
     *
     * @return class AngleVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            List<AngleBO> bos = angleAPI.findStatus();
            List<AngleVO> vos = BeanTransform.copyProperties(bos, AngleVO.class);
            return ActResult.initialize(vos);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}
