package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcalculation.bo.InterfaceCalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.dto.InterfaceCalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.InterfaceCalculationDetail;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.projectcalculation.to.InterfaceCalculationDetailTO;

import java.util.List;

/**
 * 测算决策业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 测算决策业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InterfaceCalculationDetailSer extends Ser<InterfaceCalculationDetail, InterfaceCalculationDetailDTO> {

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    List<InterfaceCalculationDetailBO> getList() throws SerException;

    void add(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException;

    InterfaceCalculationDetailBO editor(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException;

    void upDate(InterfaceCalculationDetailTO interfaceCalculationDetailTO) throws SerException;

    byte[] exportExcel(InterfaceCalculationDetailDTO interfaceCalculationDetailDTO) throws SerException;

    void importExcel(List<InterfaceCalculationDetailTO> tos) throws SerException;

    List<InterfaceCalculationDetailBO> searchByArea(String area) throws SerException;

    List<InterfaceCalculationDetailBO> searchByInternalProjectNum(String internalProjectNum) throws SerException;

    void submit(InterfaceCalculationDetailTO to) throws SerException;

    List<InterfaceCalculationDetailBO> search(String condition) throws SerException;

}