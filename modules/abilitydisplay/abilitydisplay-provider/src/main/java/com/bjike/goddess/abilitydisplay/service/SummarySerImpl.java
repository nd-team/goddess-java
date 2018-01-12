package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.abilitydisplay.bo.SummaryBO;
import com.bjike.goddess.abilitydisplay.dto.SummaryDTO;
import com.bjike.goddess.abilitydisplay.entity.Summary;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 能力展示汇总业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-08 02:04 ]
 * @Description: [ 能力展示汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "abilitydisplaySerCache")
@Service
public class SummarySerImpl extends ServiceImpl<Summary, SummaryDTO> implements SummarySer {

    @Override
    public List<SummaryBO> getSum() throws SerException {
        String[] fields = {"name","qualifiNum","adminNum","majorNum","zteNum","huaweiNum","finishProNum","impProNum"};
        return super.findBySql(sql(), SummaryBO.class, fields);
    }

    private String sql() {
        String sql = "SELECT\n" +
                "  name,\n" +
                "  any_value(ss)        AS qualifiNum,\n" +
                "  any_value(cs)        AS adminNum,\n" +
                "  any_value(cc)        AS majorNum,\n" +
                "  any_value(zteNum)    AS zteNum,\n" +
                "  any_value(huaweiNum) AS huaweiNum,\n" +
                "  any_value(count(if(progress='s',TRUE ,NULL ))) AS finishProNum,\n" +
                "  any_value(count(if(progress='s',TRUE ,NULL ))) AS impProNum\n" +
                "FROM abilitydisplay_company\n" +
                "  LEFT JOIN (SELECT\n" +
                "               id,\n" +
                "               companyId,\n" +
                "               zteNum,\n" +
                "               huaweiNum,\n" +
                "               c.ss AS ss,\n" +
                "               s.ss AS cs,\n" +
                "               b.ss AS cc\n" +
                "             FROM abilitydisplay_companyauth\n" +
                "               LEFT JOIN (SELECT\n" +
                "                            count(type)              AS ss,\n" +
                "                            any_value(companyAuthid) AS companyAuthid\n" +
                "                          FROM abilitydisplay_comcertificate\n" +
                "                          WHERE type = '公司资质数量') AS c ON companyAuthid = id\n" +
                "               LEFT JOIN (SELECT\n" +
                "                            count(type)              AS ss,\n" +
                "                            any_value(companyAuthid) AS companyAuthid1\n" +
                "                          FROM abilitydisplay_comcertificate\n" +
                "                          WHERE type = '管理资质认证数量') AS s ON companyAuthid1 = id\n" +
                "               LEFT JOIN (SELECT\n" +
                "                            count(type)              AS ss,\n" +
                "                            any_value(companyAuthid) AS companyAuthid2\n" +
                "                          FROM abilitydisplay_comcertificate\n" +
                "                          WHERE type = '专业资质认证数量') AS b ON companyAuthid2 = id) AS companyauth\n" +
                "    ON companyId = abilitydisplay_company.id\n" +
                "  LEFT JOIN (SELECT\n" +
                "               project_id,\n" +
                "               companyId AS comId,\n" +
                "               progress\n" +
                "             FROM abilitydisplay_companybn\n" +
                "               LEFT JOIN (SELECT progress ,id AS proId\n" +
                "                          FROM abilitydisplay_comproject) AS comproject ON project_id = proId) AS companybn\n" +
                "    ON comId = abilitydisplay_company.id\n" +
                "GROUP BY name;";

        return sql;
    }

}