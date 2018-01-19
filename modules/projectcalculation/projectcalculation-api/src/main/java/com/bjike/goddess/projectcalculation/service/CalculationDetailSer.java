package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import com.bjike.goddess.projectcalculation.bo.CalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;
import com.bjike.goddess.projectcalculation.excel.SonPermissionObject;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;

import java.util.List;

/**
 * 项目测算明细业务接口
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CalculationDetailSer extends Ser<CalculationDetail, CalculationDetailDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    List<CalculationDetailBO> getList() throws SerException;

    void add(CalculationDetailTO calculationDetailTO) throws SerException;

    void editor(CalculationDetailTO calculationDetailTO) throws SerException;

    byte[] exportExcel(CalculationDetailDTO calculationDetailDTO) throws SerException;

    void importExcel(List<CalculationDetailTO> tos) throws SerException;

    List<CalculationDetailBO> getSearchList(String marketInfoNum) throws SerException;

    void saveMarket(MarketInfoPreAnalysisTO to) throws SerException;


}