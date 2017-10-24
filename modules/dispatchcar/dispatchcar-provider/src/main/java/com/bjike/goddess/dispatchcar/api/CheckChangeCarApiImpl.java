package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.CheckChangeCarBO;
import com.bjike.goddess.dispatchcar.dto.CheckChangeCarDTO;
import com.bjike.goddess.dispatchcar.service.CheckChangeCarSer;
import com.bjike.goddess.dispatchcar.to.CorrectMistakeTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 出车核对修改记录
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("checkChangeCarApiImpl")
public class CheckChangeCarApiImpl implements CheckChangeCarAPI  {
    @Autowired
    private CheckChangeCarSer checkChangeCarSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return checkChangeCarSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return checkChangeCarSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<CheckChangeCarBO> list(CheckChangeCarDTO dto) throws SerException {
        return checkChangeCarSer.list(dto);
    }

    @Override
    public void modify(CorrectMistakeTO to) throws SerException {
        checkChangeCarSer.modify(to);
    }

    @Override
    public CheckChangeCarBO findOne(String id) throws SerException {
        return checkChangeCarSer.findOne(id);
    }


}