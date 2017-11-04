package com.bjike.goddess.contractware.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractware.bo.OptionContractBO;
import com.bjike.goddess.contractware.service.ImageCollectContractSer;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 合同管理图形化业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-03 02:20 ]
* @Description:	[ 合同管理图形化业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("imageCollectContractApiImpl")
public class ImageCollectContractApiImpl implements ImageCollectContractAPI  {
    @Autowired
    private ImageCollectContractSer imageCollectContractSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return imageCollectContractSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return imageCollectContractSer.guidePermission(guidePermissionTO);
    }

    @Override
    public OptionContractBO figureShow() throws SerException {
        return imageCollectContractSer.figureShow();
    }
}