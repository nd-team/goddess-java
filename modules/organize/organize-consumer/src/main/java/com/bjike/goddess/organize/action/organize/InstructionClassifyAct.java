package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.InstructionClassifyAPI;
import com.bjike.goddess.organize.to.InstructionClassifyTO;
import com.bjike.goddess.organize.vo.InstructionClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位说明书分类操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("instructionClassify")
public class InstructionClassifyAct {

    @Autowired
    private InstructionClassifyAPI instructionClassifyAPI;

    /**
     * 保存岗位说明书分类信息
     *
     * @param to 岗位说明书传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(InstructionClassifyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.save(to), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改岗位说明书分类信息
     *
     * @param to 岗位说明书分类传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(InstructionClassifyTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.update(to), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的岗位说明书分类信息
     *
     * @version v1
     */
    @GetMapping("v1/findStatus")
    public Result findStatus() throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.findStatus(), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
