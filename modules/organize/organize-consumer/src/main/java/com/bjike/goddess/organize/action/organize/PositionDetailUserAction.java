package com.bjike.goddess.organize.action.organize;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.to.PositionDetailUserTO;
import com.bjike.goddess.organize.vo.PositionDetailUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("positiondetailuser")
public class PositionDetailUserAction {

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    /**
     * 保存
     *
     * @param to 用户职位传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) PositionDetailUserTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.save(to), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param to 用户职位传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @PutMapping("v1/update/{id}")
    public Result update(@Validated(EDIT.class) PositionDetailUserTO to, BindingResult result) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.update(to), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 用户职位数据id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.delete(id), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据用户id查询职位详细数据
     *
     * @param id 用户id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/findPositionByUser/{id}")
    public Result findPositionByUser(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.findPositionByUser(id), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取用户职位数据
     *
     * @param id 用户id
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/findOneByUser/{id}")
    public Result findOneByUser(@PathVariable String id) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.findOneByUser(id), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 用户职位数据传输对象
     * @return class PositionDetailUserVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(PositionDetailUserDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(positionDetailUserAPI.maps(dto), PositionDetailUserVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}