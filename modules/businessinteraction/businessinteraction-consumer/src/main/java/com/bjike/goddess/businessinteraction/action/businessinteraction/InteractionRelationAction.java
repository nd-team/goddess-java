package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.businessinteraction.api.InteractionRelationAPI;
import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.businessinteraction.vo.InteractionRelationVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商业能力互动联系
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessinteraction/interactionrelation")
public class InteractionRelationAction {

    @Autowired
    private InteractionRelationAPI interactionRelationAPI;

    /**
     * 商业能力互动联系列表
     *
     * @param interactionRelationDTO 商业能力互动联系信息dto
     * @des 获取所有商业能力互动联系信息
     * @return  class InteractionRelationVO
     * @version v1
     */
    @GetMapping("v1/listInteractionRelation")
    public Result findListInteractionRelation(InteractionRelationDTO interactionRelationDTO, BindingResult bindingResult) throws ActException {
        try {
            List<InteractionRelationVO> interactionRelationVOList = BeanTransform.copyProperties(
                    interactionRelationAPI.listInteractionRelation(interactionRelationDTO), InteractionRelationVO.class, true);
            return ActResult.initialize(interactionRelationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加商业能力互动联系
     *
     * @param interactionRelationTO 商业能力互动联系基本信息数据to
     * @des 添加商业能力互动联系
     * @return  class InteractionRelationVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addInteractionRelation(@Validated InteractionRelationTO interactionRelationTO, BindingResult bindingResult) throws ActException {
        try {
            InteractionRelationBO interactionRelationBO1 = interactionRelationAPI.addInteractionRelation(interactionRelationTO);
            return ActResult.initialize(BeanTransform.copyProperties(interactionRelationBO1,InteractionRelationVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商业能力互动联系
     *
     * @param interactionRelationTO 商业能力互动联系基本信息数据bo
     * @des 添加商业能力互动联系
     * @return  class InteractionRelationVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editInteractionRelation(@Validated InteractionRelationTO interactionRelationTO) throws ActException {
        try {
            InteractionRelationBO interactionRelationBO1 = interactionRelationAPI.editInteractionRelation(interactionRelationTO);
            return ActResult.initialize(BeanTransform.copyProperties(interactionRelationBO1,InteractionRelationVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除商业能力互动联系信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteInteractionRelation(@PathVariable String id) throws ActException {
        try {
            interactionRelationAPI.deleteInteractionRelation(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 搜索
     *
     * @param interactionRelationDTO 商业能力互动联系信息dto
     * @des 获取所有商业能力互动联系信息
     * @return  class InteractionRelationVO
     * @version v1
     */
    @GetMapping("v1/searchInteractionRelation")
    public Result searchInteractionRelation(@Validated({InteractionRelationDTO.TESTInteractionRelationDto.class}) InteractionRelationDTO interactionRelationDTO, BindingResult bindingResult) throws ActException {
        try {
            List<InteractionRelationVO> interactionRelationVOList = BeanTransform.copyProperties(
                    interactionRelationAPI.searchInteractionRelation(interactionRelationDTO), InteractionRelationVO.class, true);
            return ActResult.initialize(interactionRelationVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}