package com.bjike.goddess.competitormanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.competitormanage.bo.CompetitorBO;
import com.bjike.goddess.competitormanage.bo.OrganizationBO;
import com.bjike.goddess.competitormanage.dto.CompetitorDTO;
import com.bjike.goddess.competitormanage.excel.SonPermissionObject;
import com.bjike.goddess.competitormanage.service.CompetitorSer;
import com.bjike.goddess.competitormanage.to.CompetitorOrganizaeTO;
import com.bjike.goddess.competitormanage.to.CompetitorTO;
import com.bjike.goddess.competitormanage.to.GuidePermissionTO;
import com.bjike.goddess.market.bo.MarketInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竞争对手信息业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("competitorApiImpl")
public class CompetitorApiImpl implements CompetitorAPI {

    @Autowired
    private CompetitorSer competitorSer;

    @Override
    public CompetitorBO saveCompetitor(CompetitorTO to) throws SerException {
        return competitorSer.saveCompetitor(to);
    }

    @Override
    public CompetitorBO editCompetitor(CompetitorTO to) throws SerException {
        return competitorSer.editCompetitor(to);
    }

    @Override
    public void delete(String id) throws SerException {
        competitorSer.delete(id);
    }

    @Override
    public CompetitorBO editOrganization(CompetitorOrganizaeTO to) throws SerException {
        return competitorSer.editOrganization(to);
    }

    @Override
    public List<CompetitorBO> pageList(CompetitorDTO dto) throws SerException {
        return competitorSer.pageList(dto);
    }

    @Override
    public Long count(CompetitorDTO dto) throws SerException {
        return competitorSer.count(dto);
    }

    @Override
    public CompetitorBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(competitorSer.findById(id),CompetitorBO.class);
    }

    @Override
    public List<CompetitorBO> findByBusinessType(String businessType) throws SerException {
        return competitorSer.findByBusinessType(businessType);
    }

    @Override
    public List<CompetitorBO> findByOrganization(String organization) throws SerException {
        return competitorSer.findByOrganization(organization);
    }

    public void leadExcel(List<CompetitorTO> toList) throws SerException {
        competitorSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(String startDate, String endDate) throws SerException {
        return competitorSer.exportExcel(startDate,endDate);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return competitorSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return competitorSer.guidePermission(to);
    }

    @Override
    public byte[] exportExcelModule() throws SerException {
        return competitorSer.exportExcelModule();
    }

    public List<CompetitorBO> areas() throws SerException {
        return competitorSer.areas();
    }

    @Override
    public OrganizationBO organizeList(String id) throws SerException {
        return competitorSer.organizeList(id);
    }

    @Override
    public List<String> findCompeName() throws SerException {
        return competitorSer.findCompeName();
    }

    @Override
    public List<MarketInfoBO> findProject() throws SerException {
        return competitorSer.findProject();
    }
}