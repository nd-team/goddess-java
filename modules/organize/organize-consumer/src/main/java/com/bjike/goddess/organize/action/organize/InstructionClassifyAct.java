package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.InstructionClassifyAPI;
import com.bjike.goddess.organize.dto.InstructionClassifyDTO;
import com.bjike.goddess.organize.to.InstructionClassifyTO;
import com.bjike.goddess.organize.vo.InstructionClassifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
     * @return class InstructionClassifyVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) InstructionClassifyTO to, BindingResult result) throws ActException {
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
     * @return class InstructionClassifyVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) InstructionClassifyTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.update(to), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取正常状态的岗位说明书分类信息
     *
     * @return class InstructionClassifyVO
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

    /**
     * 关闭
     *
     * @param id 岗位说明书数据id
     * @return class InstructionClassifyVO
     * @version v1
     */
    @PatchMapping("v1/close/{id}")
    public Result close(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.close(id), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 开启
     *
     * @param id 岗位说明书数据id
     * @return class InstructionClassifyVO
     * @version v1
     */
    @PatchMapping("v1/open/{id}")
    public Result open(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.open(id), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位说明书数据id
     * @return class InstructionClassifyVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.delete(id), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 岗位说明书数据传输
     * @return class InstructionClassifyVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(InstructionClassifyDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.maps(dto), InstructionClassifyVO.class));
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
            return ActResult.initialize(instructionClassifyAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询岗位说明书
     *
     * @param id 岗位说明书数据id
     * @return class InstructionClassifyVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(instructionClassifyAPI.findById(id), InstructionClassifyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
