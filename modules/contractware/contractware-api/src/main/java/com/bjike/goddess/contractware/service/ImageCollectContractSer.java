package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractware.bo.OptionContractBO;
import com.bjike.goddess.contractware.entity.ImageCollectContract;
import com.bjike.goddess.contractware.dto.ImageCollectContractDTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;

/**
* 合同管理图形化业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ImageCollectContractSer extends Ser<ImageCollectContract, ImageCollectContractDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 合同管理饼状图
     * @throws SerException
     */
    OptionContractBO figureShow() throws SerException;
 }