package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDetailDTO;
import com.bjike.goddess.projectcalculation.service.InterfaceCalculationDetailSer;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测算决策业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 测算决策业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("interfaceCalculationDetailApiImpl")
public class InterfaceCalculationDetailApiImpl implements InterfaceCalculationDetailAPI {
    @Autowired
    private InterfaceCalculationDetailSer interfaceCalculationDetailSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return interfaceCalculationDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<InterfaceCalculationDetailBO> getList() throws SerException {
        return interfaceCalculationDetailSer.getList();
    }

    @Override
    public void add(InterfaceCalculationDetailTO interfaceCalculationDetailTO) {

    }

    @Override
    public InterfaceCalculationDetailBO editor(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        return interfaceCalculationDetailSer.editor(interfaceCalculationDetailTO);
    }

    @Override
    public void upDate(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException {
        interfaceCalculationDetailSer.upDate(interfaceCalculationDetailTO);
    }

    @Override
    public byte[] exportExcel(InterfaceCalculationDetailDTO interfaceCalculationDetailDTO) throws SerException {
        return interfaceCalculationDetailSer.exportExcel(interfaceCalculationDetailDTO);
    }

    @Override
    public void importExcel(List<InterfaceCalculationDetailTO> tos) throws SerException {
        interfaceCalculationDetailSer.importExcel(tos);
    }

    @Override
    public List<InterfaceCalculationDetailBO> searchByArea(String area) throws SerException {
        return interfaceCalculationDetailSer.searchByArea(area);
    }

    @Override
    public List<InterfaceCalculationDetailBO> searchByInternalProjectNum(String internalProjectNum) throws SerException {
        return interfaceCalculationDetailSer.searchByInternalProjectNum(internalProjectNum);
    }

    @Override
    public void submit(InterfaceCalculationDetailTO to) throws SerException {
        interfaceCalculationDetailSer.submit(to);
    }
}