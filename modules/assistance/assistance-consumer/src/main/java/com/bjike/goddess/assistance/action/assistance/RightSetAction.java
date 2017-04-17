package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.RightSetAPI;
import com.bjike.goddess.assistance.bo.RightSetBO;
import com.bjike.goddess.assistance.dto.RightSetDTO;
import com.bjike.goddess.assistance.to.RightSetTO;
import com.bjike.goddess.assistance.vo.RightSetVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:14 ]
 * @Description: [ 权限设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("rightset")
public class RightSetAction {

    @Autowired
    private RightSetAPI rightSetAPI;

    /**
     *  权限设置列表总条数
     *
     * @param rightSetDTO  权限设置信息dto
     * @des 获取所有权限设置信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(RightSetDTO rightSetDTO) throws ActException {
        try {
            Long count = rightSetAPI.countRightSet(rightSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 权限设置列表
     *
     * @param rightSetDTO 权限设置信息dto
     * @des 获取所有权限设置信息
     * @return  class RightSetVO
     * @version v1
     */
    @GetMapping("v1/listRightSet")
    public Result findListRightSet(RightSetDTO rightSetDTO, BindingResult bindingResult) throws ActException {
        try {
            List<RightSetVO> rightSetVOList = BeanTransform.copyProperties(
                    rightSetAPI.listRightSet(rightSetDTO), RightSetVO.class, true);
            return ActResult.initialize(rightSetVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加权限设置
     *
     * @param rightSetTO 权限设置基本信息数据to
     * @des 添加权限设置
     * @return  class RightSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addRightSet(@Validated RightSetTO rightSetTO, BindingResult bindingResult) throws ActException {
        try {
            RightSetBO rightSetBO1 = rightSetAPI.addRightSet(rightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(rightSetBO1,RightSetVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑权限设置
     *
     * @param rightSetTO 权限设置基本信息数据bo
     * @des 添加权限设置
     * @return  class RightSetVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editRightSet(@Validated RightSetTO rightSetTO) throws ActException {
        try {
            RightSetBO rightSetBO1 = rightSetAPI.editRightSet(rightSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(rightSetBO1,RightSetVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除权限设置信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteRightSet(@PathVariable String id) throws ActException {
        try {
            rightSetAPI.deleteRightSet(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }



}