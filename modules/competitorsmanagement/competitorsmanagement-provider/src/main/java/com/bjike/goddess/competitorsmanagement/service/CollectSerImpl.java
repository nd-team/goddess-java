package com.bjike.goddess.competitorsmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.competitorsmanagement.bo.CollectBO;
import com.bjike.goddess.competitorsmanagement.dto.CollectDTO;
import com.bjike.goddess.competitorsmanagement.entity.Collect;
import com.bjike.goddess.competitorsmanagement.entity.Competitor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 竞争对手管理汇总业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-15 04:55 ]
 * @Description: [ 竞争对手管理汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "competitorsmanagementSerCache")
@Service
public class CollectSerImpl extends ServiceImpl<Collect, CollectDTO> implements CollectSer {

    @Override
    public List<CollectBO> getCollet() throws SerException {
        String sql = "SELECT businessType\n" +
                "FROM competitorsmanagement_competitor\n" +
                "GROUP BY businessType";
        String[] fields = {"MAcompetitor", "COnumber", "CIcompetitior", "UNCIcompetitior", "AFareaNum", "AFbusinessNum"};
        List<Object> competitors = super.findBySql(sql);
        List<CollectBO> collectBOS = new ArrayList<>();
        for (int i = 0; i < competitors.size(); i++) {
            List<CollectBO> list = super.findBySql(sql(), CollectBO.class, fields);
            collectBOS.add(list.get(0));
        }

        System.out.println(collectBOS);
        return collectBOS;
    }

    private String sql() {
        String sql = "SELECT\n" +
                "  (SELECT count(marketInfoNum)\n" +
                "   FROM competitorsmanagement_competitor\n" +
                "   WHERE marketInfoNum NOT IN (NULL) AND createTime BETWEEN '2017-12-15' AND '2017-12-16')  AS MAcompetitor,\n" +
                "  (SELECT count(id)\n" +
                "   FROM competitorsmanagement_competitor)                                                   AS COnumber,\n" +
                "  (SELECT count(id)\n" +
                "   FROM competitorsmanagement_competitor\n" +
                "   WHERE is_competitorInformation = 1 AND createTime BETWEEN '2017-12-14' AND '2017-12-15') AS CIcompetitior,\n" +
                "  (SELECT count(id)\n" +
                "   FROM competitorsmanagement_competitor\n" +
                "   WHERE is_competitorInformation = 0 AND createTime BETWEEN '2017-12-14' AND '2017-12-15') AS UNCIcompetitior,\n" +
                "  (SELECT sum(affectedAreaNum)\n" +
                "   FROM competitorsmanagement_competitor\n" +
                "   WHERE businessType = 'csdc' AND createTime BETWEEN '2017-12-14' AND '2017-12-15')        AS AFareaNum,\n" +
                "  (SELECT sum(impactOnBusinessNum)\n" +
                "   FROM competitorsmanagement_competitor\n" +
                "   WHERE businessType = 'csdc' AND createTime BETWEEN '2017-12-14' AND '2017-12-15')        AS AFbusinessNum\n" +
                "FROM competitorsmanagement_competitor\n" +
                "GROUP BY businessType\n" +
                "HAVING businessType = 'csdc';";
        return sql;
    }
}