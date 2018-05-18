package com.bjike.goddess.contractware.action.contractware;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.api.ImageCollectContractAPI;
import com.bjike.goddess.contractware.bo.OptionContractBO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.vo.OptionContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = imageCollectContractAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


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