package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ReflectAPI;
import com.bjike.goddess.organize.entity.Reflect;
import com.bjike.goddess.organize.to.OperateTO;
import com.bjike.goddess.organize.to.ReflectTO;
import com.bjike.goddess.organize.vo.ReflectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 体现类型操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("reflect")
public class ReflectAct {
    
    @Autowired
    private ReflectAPI reflectAPI;

    /**
     * 保存体现类型信息
     *
     * @param to 体现类型传输对象
     * @return class ReflectVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) ReflectTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.save(to), ReflectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改体现类型信息
     *
     * @param to 体现类型传输对象
     * @return class ReflectVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) ReflectTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.update(to), ReflectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的体现类型信息
     *
     * @return class ReflectVO
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(reflectAPI.findStatus(), ReflectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
