package com.bjike.goddess.rentutilitiespay.action.rentutilitiespay;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.api.StencilAPI;
import com.bjike.goddess.rentutilitiespay.bo.StencilBO;
import com.bjike.goddess.rentutilitiespay.dto.StencilDTO;
import com.bjike.goddess.rentutilitiespay.vo.StencilVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 模板
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-24 10:50 ]
* @Description:	[ 模板 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("stencil")
public class StencilAction {
    @Autowired
    private StencilAPI stencilAPI;

    @GetMapping("v1/pageList")
    public Result pageList(StencilDTO stencilDTO) throws ActException{
        try {
            List<StencilBO> boList = stencilAPI.pageList(stencilDTO);
            List<StencilVO> voList = BeanTransform.copyProperties(boList,StencilVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
 }