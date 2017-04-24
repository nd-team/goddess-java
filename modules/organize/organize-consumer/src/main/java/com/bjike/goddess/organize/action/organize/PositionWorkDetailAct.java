package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionWorkDetailAPI;
import com.bjike.goddess.organize.dto.PositionWorkDetailDTO;
import com.bjike.goddess.organize.to.PositionWorkDetailTO;
import com.bjike.goddess.organize.vo.PositionWorkDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 岗位工作详细操作
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:20]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("positionWorkDetail")
public class PositionWorkDetailAct {

    @Autowired
    private PositionWorkDetailAPI positionWorkDetailAPI;

    /**
     * 根据说明书ID查询工作详细
     *
     * @param id 岗位说明书ID
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @GetMapping("v1/findByInstruction")
    public Result findByInstruction(String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.findByInstruction(id), PositionWorkDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加岗位工作明细信息
     *
     * @param to 岗位工作明细传输对象
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(BindingResult result, @Validated(ADD.class) PositionWorkDetailTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.save(to), PositionWorkDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改岗位工作明细信息
     *
     * @param to 岗位工作明细传输对象
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(BindingResult result, @Validated(EDIT.class) PositionWorkDetailTO to, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.update(to), PositionWorkDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 岗位工作明细数据id
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.delete(id), PositionWorkDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 岗位工作明细数据传输
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PositionWorkDetailDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.maps(dto), PositionWorkDetailVO.class, request));
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
            return ActResult.initialize(positionWorkDetailAPI.getTotal());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据ID查询岗位工作明细
     *
     * @param id
     * @return class PositionWorkDetailVO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionWorkDetailAPI.findById(id), PositionWorkDetailVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
