package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.CasualtyPurchasingListBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingListDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingList;
import com.bjike.goddess.businsurance.service.CasualtyPurchasingListSer;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingListTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团体意外险购买名单业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:24 ]
 * @Description: [ 团体意外险购买名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("casualtyPurchasingListApiImpl")
public class CasualtyPurchasingListApiImpl implements CasualtyPurchasingListAPI {
    @Autowired
    private CasualtyPurchasingListSer casualtyPurchasingListSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return casualtyPurchasingListSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return casualtyPurchasingListSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        return casualtyPurchasingListSer.countCasualty(casualtyPurchasingListDTO);
    }

    @Override
    public CasualtyPurchasingListBO getOneCasualty(String id) throws SerException {
        return casualtyPurchasingListSer.getOneCasualty(id);
    }

    @Override
    public List<CasualtyPurchasingListBO> listCasualty(CasualtyPurchasingListDTO casualtyPurchasingListDTO) throws SerException {
        return casualtyPurchasingListSer.listCasualty(casualtyPurchasingListDTO);
    }

    @Override
    public CasualtyPurchasingListBO addCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        return casualtyPurchasingListSer.addCasualty(casualtyPurchasingListTO);
    }

    @Override
    public CasualtyPurchasingListBO editCasualty(CasualtyPurchasingListTO casualtyPurchasingListTO) throws SerException {
        return casualtyPurchasingListSer.editCasualty(casualtyPurchasingListTO);
    }

    @Override
    public void deleteCasualty(String id) throws SerException {
        casualtyPurchasingListSer.deleteCasualty(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return casualtyPurchasingListSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return casualtyPurchasingListSer.templateExport();
    }

    @Override
    public void importExcel(List<CasualtyPurchasingListTO> casualtyPurchasingListTOS) throws SerException {
        casualtyPurchasingListSer.importExcel(casualtyPurchasingListTOS);
    }
}