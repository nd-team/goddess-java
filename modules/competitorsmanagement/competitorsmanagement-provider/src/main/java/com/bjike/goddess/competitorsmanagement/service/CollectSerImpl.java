package com.bjike.goddess.competitorsmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.competitorsmanagement.bo.CollectBO;
import com.bjike.goddess.competitorsmanagement.dto.CollectDTO;
import com.bjike.goddess.competitorsmanagement.entity.Collect;
import com.bjike.goddess.competitorsmanagement.entity.Competitor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        String[] fields = {"businessType","MAcompetitor", "COnumber", "CIcompetitior", "UNCIcompetitior", "AFareaNum", "AFbusinessNum"};
        List<CollectBO> list = super.findBySql(sql(), CollectBO.class, fields);
        System.out.println(list);
        return list;
    }

    private String sql() {
        String sql = "SELECT\n" +
                "  businessType,\n" +
                "  count(if(marketInfoNum != '', TRUE, NULL))            AS MAcompetitor,\n" +
                "  count(id)                                             AS COnumber,\n" +
                "  count(if(is_competitorInformation = 1, TRUE, NULL))   AS CIcompetitior,\n" +
                "  count((if(is_competitorInformation = 0, TRUE, NULL))) AS UNCIcompetitior,\n" +
                "  sum(affectedAreaNum)                                  AS AFareaNum,\n" +
                "  sum(impactOnBusinessNum)                              AS AFbusinessNum\n" +
                "FROM competitorsmanagement_competitor\n" +
                "WHERE createTime BETWEEN '2017-12-14' AND '2017-12-17'\n" +
                "GROUP BY businessType;";
        return sql;
    }

}