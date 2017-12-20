package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.InformationBO;
import com.bjike.goddess.marketdevelopment.bo.OutBillBO;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.excel.OutBillImportExcel;
import com.bjike.goddess.marketdevelopment.service.OutBillSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.OutBillTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 外出单业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 11:12 ]
* @Description:	[ 外出单业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("outBillApiImpl")
public class OutBillApiImpl implements OutBillAPI  {

    @Autowired
    private OutBillSer outBillSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return outBillSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<OutBillBO> maps(OutBillDTO dto) throws SerException {
        return outBillSer.maps(dto);
    }

    @Override
    public void save(OutBillTO to) throws SerException {
        outBillSer.save(to);
    }

    @Override
    public void update(OutBillTO to) throws SerException {
        outBillSer.update(to);
    }

    @Override
    public void congeal(OutBillTO to) throws SerException {
        outBillSer.congeal(to);
    }

    @Override
    public void thaw(OutBillTO to) throws SerException {
        outBillSer.thaw(to);
    }

    @Override
    public void delete(OutBillTO to) throws SerException {
        outBillSer.delete(to);
    }

    @Override
    public OutBillBO getById(String id) throws SerException {
        return outBillSer.getById(id);
    }

    @Override
    public Long getTotal(OutBillDTO dto) throws SerException {
        return outBillSer.count(dto);
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return outBillSer.templateExcel();
    }

    @Override
    public byte[] exportExcel(OutBillDTO dto) throws SerException {
        return outBillSer.exportExcel(dto);
    }

    @Override
    public void upload(List<OutBillImportExcel> tos) throws SerException {
        outBillSer.upload(tos);
    }

    @Override
    public List<String> findName() throws SerException {
        return outBillSer.findName();
    }

    @Override
    public InformationBO findDataByName(String name) throws SerException {
        return outBillSer.findDataByName(name);
    }
}