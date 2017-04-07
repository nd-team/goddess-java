package com.bjike.goddess.festival.action.festival;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.api.AreaRelationerAPI;
import com.bjike.goddess.festival.dto.AreaRelationerDTO;
import com.bjike.goddess.festival.entity.AreaRelationer;
import com.bjike.goddess.festival.vo.AreaRelationerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 各地区紧急联系人
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:13 ]
 * @Description: [ 各地区紧急联系人 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("festival/arearelationer")
public class AreaRelationerAction {

    @Autowired
    private AreaRelationerAPI areaRelationerAPI;

    /**
     * 查看各地区紧急联系人
     *
     * @param areaRelationerDTO 各地区紧急联系人dto
     * @des 查看各地区紧急联系人
     * @return  class AreaRelationerVO
     * @version v1
     */
    @GetMapping("v1/getAreaRelationerDetail")
    public Result getAreaRelationerDetail (@Validated(AreaRelationerDTO.TESTFindDetail.class) AreaRelationerDTO areaRelationerDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AreaRelationerVO> areaRelationerVOS = BeanTransform.copyProperties(
                    areaRelationerAPI.getAreaRelationer(areaRelationerDTO.getHolidayProgrammeId()), AreaRelationerVO.class, true);
            return ActResult.initialize(areaRelationerVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}