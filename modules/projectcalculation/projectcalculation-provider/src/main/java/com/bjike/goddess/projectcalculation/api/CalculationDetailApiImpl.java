package com.bjike.goddess.projectcalculation.api;

import com.bjike.goddess.projectcalculation.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.entity.MarketInfoPreAnalysis;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import com.bjike.goddess.projectcalculation.bo.CalculationDetailBO;
import com.bjike.goddess.projectcalculation.dto.CalculationDetailDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;
import com.bjike.goddess.projectcalculation.excel.SonPermissionObject;
import com.bjike.goddess.projectcalculation.service.CalculationDetailSer;
import com.bjike.goddess.projectcalculation.to.CalculationDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目测算明细业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-05 02:48 ]
 * @Description: [ 项目测算明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("calculationDetailApiImpl")
public class CalculationDetailApiImpl implements CalculationDetailAPI {

    @Autowired
    private CalculationDetailSer calculationDetailSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return calculationDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return calculationDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<CalculationDetailBO> getList() throws SerException {
        return calculationDetailSer.getList();
    }

    @Override
    public void add(CalculationDetailTO calculationDetailTO) throws SerException {
        calculationDetailSer.add(calculationDetailTO);
    }

    @Override
    public void editor(CalculationDetailTO calculationDetailTO) throws SerException {
        calculationDetailSer.editor(calculationDetailTO);
    }

    @Override
    public byte[] exportExcel(CalculationDetailDTO dto) throws SerException {
        return calculationDetailSer.exportExcel(dto);
    }

    @Override
    public void importExcel(List<CalculationDetailTO> tos) throws SerException {
        calculationDetailSer.importExcel(tos);
    }

    @Override
    public List<CalculationDetailBO> getSearchList(String marketInfoNum) throws SerException {
        return calculationDetailSer.getSearchList(marketInfoNum);
    }

    @Override
    public void saveMarket(MarketInfoPreAnalysisTO to) throws SerException {
        calculationDetailSer.saveMarket(to);
    }

    @Override
    public CalculationDetailBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(calculationDetailSer.findById(id), CalculationDetailBO.class);
    }

    @Override
    public void upDate(CalculationDetailTO calculationDetailTO) throws SerException {
        CalculationDetail calculationDetail = BeanTransform.copyProperties(calculationDetailTO, CalculationDetail.class, true);
        calculationDetailSer.update(calculationDetail);
    }


}