package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.CustomerBO;
import com.bjike.goddess.marketdevelopment.dto.CustomerDTO;
import com.bjike.goddess.marketdevelopment.excel.CustomerImportExcel;
import com.bjike.goddess.marketdevelopment.service.CustomerSer;
import com.bjike.goddess.marketdevelopment.to.CustomerTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 客户接触阶段业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-29 09:37 ]
* @Description:	[ 客户接触阶段业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("customerApiImpl")
public class CustomerApiImpl implements CustomerAPI  {

    @Autowired
    private CustomerSer customerSer;

//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return customerSer.guidePermission(guidePermissionTO);
//    }

    @Override
    public void save(CustomerTO to) throws SerException {
        customerSer.save(to);
    }

    @Override
    public void update(CustomerTO to) throws SerException {
        customerSer.update(to);
    }

    @Override
    public void congeal(CustomerTO to) throws SerException {
        customerSer.congeal(to);
    }

    @Override
    public void thaw(CustomerTO to) throws SerException {
        customerSer.thaw(to);
    }

    @Override
    public void delete(CustomerTO to) throws SerException {
        customerSer.delete(to);
    }

    @Override
    public CustomerBO getById(String id) throws SerException {
        return customerSer.getById(id);
    }

    @Override
    public List<CustomerBO> maps(CustomerDTO dto) throws SerException {
        return customerSer.maps(dto);
    }

    @Override
    public Long getTotal(CustomerDTO dto) throws SerException {
        return customerSer.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return customerSer.templateExcel();
    }

    @Override
    public byte[] exportExcel(CustomerDTO dto) throws SerException {
        return customerSer.exportExcel(dto);
    }

    @Override
    public void upload(List<CustomerImportExcel> tos) throws SerException {
        customerSer.upload(tos);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return customerSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<String> findStage() throws SerException {
        return customerSer.findStage();
    }
}