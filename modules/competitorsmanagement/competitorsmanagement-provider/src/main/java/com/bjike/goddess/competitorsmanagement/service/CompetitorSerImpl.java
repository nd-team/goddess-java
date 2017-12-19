package com.bjike.goddess.competitorsmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.competitorsmanagement.bo.CompetitorBO;
import com.bjike.goddess.competitorsmanagement.bo.OrganizationSDBO;
import com.bjike.goddess.competitorsmanagement.dto.CompetitorDTO;
import com.bjike.goddess.competitorsmanagement.entity.Competitor;
import com.bjike.goddess.competitorsmanagement.entity.OrganizationSD;
import com.bjike.goddess.competitorsmanagement.excel.CompetitorExport;
import com.bjike.goddess.competitorsmanagement.to.CompetitorTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 竞争对手管理业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitorsmanagementSerCache")
@Service
public class CompetitorSerImpl extends ServiceImpl<Competitor, CompetitorDTO> implements CompetitorSer {

//    @Autowired
//    private OrganizationSDSer organizationSDSer;

    @Override
    public List<CompetitorBO> getList() throws SerException {
        List<Competitor> list = super.findAll();
        System.out.println(list);
        List<CompetitorBO> bOlist = BeanTransform.copyProperties(list,CompetitorBO.class);
        System.out.println(bOlist);
        return BeanTransform.copyProperties(super.findAll(), CompetitorBO.class);
    }

    @Override
    public void add(CompetitorTO to) throws SerException {
        Competitor competitor = BeanTransform.copyProperties(to, Competitor.class, true);
        super.save(competitor);
    }

    @Override
    public CompetitorBO editor(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), CompetitorBO.class);
    }

    @Override
    public void upDate(CompetitorTO to) throws SerException {
        Competitor competitor = BeanTransform.copyProperties(to, Competitor.class, true);
        super.update(competitor);
    }

    @Override
    public byte[] exportExcel(CompetitorDTO dto) throws SerException {
        List<Competitor> list = super.findByCis(dto);
        List<CompetitorExport> siginManageExports = new ArrayList<>();
        list.stream().forEach(str -> {
            CompetitorExport excel = BeanTransform.copyProperties(str, CompetitorExport.class);
            siginManageExports.add(excel);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(siginManageExports, excel);
        return bytes;
    }

    @Override
    public void importExcel(List<CompetitorTO> tos) throws SerException {
        List<Competitor> calculationDetails = BeanTransform.copyProperties(tos, Competitor.class, true);
        calculationDetails.stream().forEach(str -> {
            str.setCreateTime(LocalDateTime.now());
            str.setModifyTime(LocalDateTime.now());
        });
        super.save(calculationDetails);
    }

    @Override
    public List<CompetitorBO> search(String condition) throws SerException {
        String sql = "SELECT *\n" +
                "FROM competitorsmanagement_competitor\n" +
                "WHERE area LIKE '%" + condition + "%' OR businessType LIKE '%" + condition + "%' OR rivalCompany LIKE '%" + condition + "%' OR projectName LIKE '%" + condition + "%'";
        String[] fields = {"id", "createTime", "modifyTime", "address", "affectedArea", "affectedAreaNum", "area", "businessType", "competitorCollectTime", "competitorDynamics", "competitor" +
                "Information", "competitorSource", "customerNum", "impactOnBusiness", "impactOn" +
                "BusinessNum", "informationCollector", "marketInfoNum", "note", "organizationStructure", "phone", "projectName", "relatedContact", "rivalCompany", "workRight"};
        return super.findBySql(sql,CompetitorBO.class,fields);
    }

    @Override
    public OrganizationSD organizationSD(String id) throws SerException {
        Competitor competitor = findById(id);
        OrganizationSD sd = new OrganizationSD();
        competitor.setOrganizationSD(sd);
        super.update(competitor);
        return null;
        /*if (to.getOrganizationSD() == null) {
            OrganizationSD sd = new OrganizationSD();
            Competitor competitor = BeanTransform.copyProperties(to, Competitor.class, true);
            competitor.setOrganizationSD(sd);
            super.update(competitor);
            return sd;
        } else {
            return BeanTransform.copyProperties(to.getOrganizationSD(), OrganizationSD.class);
        }*/
    }
}