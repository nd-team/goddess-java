package com.bjike.goddess.businesscommission.api;

import com.bjike.goddess.businesscommission.bo.ProportionBO;
import com.bjike.goddess.businesscommission.dto.ProportionDTO;
import com.bjike.goddess.businesscommission.excel.SonPermissionObject;
import com.bjike.goddess.businesscommission.service.ProportionSer;
import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
import com.bjike.goddess.businesscommission.to.ProportionExcelTO;
import com.bjike.goddess.businesscommission.to.ProportionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务提成分配比例表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-23 11:29 ]
 * @Description: [ 业务提成分配比例表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("proportionApiImpl")
public class ProportionApiImpl implements ProportionAPI {
    @Autowired
    private ProportionSer proportionSer;

    @Override
    public Long getTotal(ProportionDTO proportionDTO) throws SerException {
        return proportionSer.getTotal(proportionDTO);
    }

    @Override
    public ProportionBO getOneById(String id) throws SerException {
        return proportionSer.getOneById(id);
    }

    @Override
    public List<ProportionBO> listProportion(ProportionDTO proportionDTO) throws SerException {
        return proportionSer.listProportion(proportionDTO);
    }

    @Override
    public void addProportion(ProportionTO to) throws SerException {
        proportionSer.addProportion(to);
    }

    @Override
    public void editProportion(ProportionTO to) throws SerException {
        proportionSer.editProportion(to);
    }

    @Override
    public void deleteProportion(String id) throws SerException {
        proportionSer.deleteProportion(id);
    }

    @Override
    public void confirm(Boolean tar) throws SerException {
        proportionSer.confirm(tar);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return proportionSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return proportionSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void importExcel(List<ProportionExcelTO> tos) throws SerException {
        proportionSer.importExcel(tos);
    }

    @Override
    public byte[] exportExcel(ProportionDTO dto) throws SerException {
        return proportionSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return proportionSer.templateExport();
    }

}