package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessBO;
import com.bjike.goddess.marketdevelopment.bo.BusinessReturnBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
import com.bjike.goddess.marketdevelopment.excel.BusinessImportExcel;
import com.bjike.goddess.marketdevelopment.service.BusinessSer;
import com.bjike.goddess.marketdevelopment.to.BusinessTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 业务对象业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-28 02:54 ]
* @Description:	[ 业务对象业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("businessApiImpl")
public class BusinessApiImpl implements BusinessAPI  {

    @Autowired
    private BusinessSer businessSer;

    @Override
    public List<BusinessBO> maps(BusinessDTO dto) throws SerException {
        return businessSer.maps(dto);
    }

    @Override
    public void save(BusinessTO to) throws SerException {
        businessSer.save(to);
    }

    @Override
    public void update(BusinessTO to) throws SerException {
        businessSer.update(to);
    }

    @Override
    public void congeal(BusinessTO to) throws SerException {
        businessSer.congeal(to);
    }

    @Override
    public void thaw(BusinessTO to) throws SerException {
        businessSer.thaw(to);
    }

    @Override
    public void delete(BusinessTO to) throws SerException {
        businessSer.delete(to);
    }

    @Override
    public BusinessReturnBO getById(String id) throws SerException {
        return businessSer.getById(id);
    }

    @Override
    public Long getTotal(BusinessDTO dto) throws SerException {
        return businessSer.getTotal(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return businessSer.templateExcel();
    }

    @Override
    public void upload(List<BusinessImportExcel> tos) throws SerException {
        businessSer.upload(tos);
    }

    @Override
    public byte[] exportExcel(BusinessDTO dto) throws SerException {
        return businessSer.exportExcel(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return businessSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return businessSer.guidePermission(guidePermissionTO);
    }
}