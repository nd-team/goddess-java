package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.ImageCollectContractAPI;
import com.bjike.goddess.contractware.bo.OptionContractBO;
import com.bjike.goddess.contractware.vo.OptionContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* 合同管理图形化
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("imagecollectcontract")
public class ImageCollectContractAction {
    @Autowired
    private ImageCollectContractAPI imageCollectContractAPI;


    /**
     * 合同管理饼状图
     * @return class OptionContractVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/figureShow")
    public Result figureShow() throws ActException{
        try {
            OptionContractBO optionContractBO = imageCollectContractAPI.figureShow();
            OptionContractVO optionContractVO = BeanTransform.copyProperties(optionContractBO,OptionContractVO.class);
            return ActResult.initialize(optionContractVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }
 }