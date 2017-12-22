package com.bjike.goddess.competitorsmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.competitorsmanagement.bo.CompetitorBO;
import com.bjike.goddess.competitorsmanagement.bo.OrganizationSDBO;
import com.bjike.goddess.competitorsmanagement.dto.CompetitorDTO;
import com.bjike.goddess.competitorsmanagement.entity.OrganizationSD;
import com.bjike.goddess.competitorsmanagement.service.CompetitorSer;
import com.bjike.goddess.competitorsmanagement.to.CompetitorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 竞争对手管理业务接口实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("competitorApiImpl")
public class CompetitorApiImpl implements CompetitorAPI {

    @Autowired
    private CompetitorSer competitorSer;
    @Override
    public List<CompetitorBO> getList() throws SerException {
        return competitorSer.getList();
    }

    @Override
    public void add(CompetitorTO to) throws SerException {
        competitorSer.add(to);
    }

    @Override
    public CompetitorBO editor(String id) throws SerException {
        return competitorSer.editor(id);
    }

    @Override
    public void upDate(CompetitorTO to) throws SerException {
        competitorSer.upDate(to);
    }

    @Override
    public byte[] exportExcel(CompetitorDTO dto) throws SerException {
        return competitorSer.exportExcel(dto);
    }

    @Override
    public void importExcel(List<CompetitorTO> tos) throws SerException {
        competitorSer.importExcel(tos);
    }

    @Override
    public List<CompetitorBO> search(String condition) throws SerException {
        return competitorSer.search(condition);
    }

    @Override
    public OrganizationSD organizationSD(String id) throws SerException {
        return competitorSer.organizationSD(id);
    }
}