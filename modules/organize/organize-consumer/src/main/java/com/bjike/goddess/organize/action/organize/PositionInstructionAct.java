package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @param id 岗位详细数据id
     * @return class PositionInstructionVO
     * @version v1
     */
    @GetMapping("v1/findByPosition")
    public Result findByPosition(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.findByPosition(id), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 岗位说明书数据传输
     * @return class PositionInstructionVO
     * @version v1
     */
    @GetMapping("v1/findPage")
    public Result findPage(PositionInstructionDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.findPage(dto), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 保存说明书信息
     *
     * @param to 说明书传输对象
     * @return class PositionInstructionVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PositionInstructionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.save(to), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());

        }
    }

    /**
     * 修改说明书信息
     *
     * @param to 说明书传输对象
     * @return class PositionInstructionVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PositionInstructionTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.update(to), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位说明书数据id
     * @return class PositionInstructionVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.delete(id), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 岗位说明书数据传输
     * @return class PositionInstructionVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PositionInstructionDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.maps(dto), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal() throws ActException {
        try {
            return ActResult.initialize(positionInstructionAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询岗位说明书
     *
     * @param id 岗位说明书数据id
     * @return class PositionInstructionVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionInstructionAPI.findById(id), PositionInstructionVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
