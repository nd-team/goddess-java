package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.LandTaxReportBO;
import com.bjike.goddess.foreigntax.dto.LandTaxReportDTO;
import com.bjike.goddess.foreigntax.entity.LandTaxReport;
import com.bjike.goddess.foreigntax.service.LandTaxReportSer;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.LandTaxReportTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 国地税报表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:13 ]
 * @Description: [ 国地税报表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("landTaxReportApiImpl")
public class LandTaxReportApiImpl implements LandTaxReportAPI {
@Autowired
    private LandTaxReportSer landTaxReportSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return landTaxReportSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return landTaxReportSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(LandTaxReportDTO dto) throws SerException {
        return landTaxReportSer.count(dto);
    }

    @Override
    public LandTaxReportBO getOne(String id) throws SerException {
        return landTaxReportSer.getOne(id);
    }

    @Override
    public List<LandTaxReportBO> list(LandTaxReportDTO dto) throws SerException {
        return landTaxReportSer.list(dto);
    }

    @Override
    public LandTaxReportBO insert(LandTaxReportTO to) throws SerException {
        return landTaxReportSer.insert(to);
    }

    @Override
    public LandTaxReportBO edit(LandTaxReportTO to) throws SerException {
        return landTaxReportSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        landTaxReportSer.remove(id);
    }
}