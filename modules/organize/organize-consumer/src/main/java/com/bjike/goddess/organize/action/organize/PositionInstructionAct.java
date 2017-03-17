package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionInstructionAPI;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.organize.to.PositionInstructionTO;
import com.bjike.goddess.organize.vo.PositionInstructionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 岗位说明书操作
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@RestController
@RequestMapping("positionInstruction")
public class PositionInstructionAct {

    @Autowired
    private PositionInstructionAPI positionInstructionAPI;

    /**
     * 根据职位查询说明书
     *
     * @param id 职位详细ID
     * @version v1
     */
    @GetMapping("v1/findByPosition")
    public Result findByPosition(String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.findByPosition(id), PositionInstructionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页数据
     *
     * @param dto 岗位说明书数据传输
     * @version v1
     */
    @GetMapping("v1/findPage")
    public Result findPage(PositionInstructionDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.findPage(dto), PositionInstructionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存说明书信息
     *
     * @param to 说明书传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(PositionInstructionTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.save(to), PositionInstructionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());

        }
    }

    /**
     * 修改说明书信息
     *
     * @param to 说明书传输对象
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(PositionInstructionTO to) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.update(to), PositionInstructionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
