package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.costdetail.bo.CollectBO;
import com.bjike.goddess.costdetail.dto.CollectDTO;
import com.bjike.goddess.costdetail.entity.Collect;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 成本明细汇总业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-03 04:10 ]
 * @Description: [ 成本明细汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "costdetailSerCache")
@Service
public class CollectSerImpl extends ServiceImpl<Collect, CollectDTO> implements CollectSer {

    @Override
    public List<CollectBO> getCollect(String date) throws SerException {
        String[] fields = {"type", "January", "Febtype", "March", "April", "May", "June", "July", "Aguest", "September", "October", "November", "December"};
        return super.findBySql(sql(date), CollectBO.class, fields);
    }

    private String sql(String date) {
        String sql = "SELECT\n" +
                "  type,\n" +
                "  sum(any_value(total)) AS January,\n" +
                "  any_value(February) AS Febtype,\n" +
                "  any_value(March) AS March,\n" +
                "  any_value(April) AS April,\n" +
                "  any_value(May) AS May,\n" +
                "  any_value(June) AS June,\n" +
                "  any_value(July) AS July,\n" +
                "  any_value(Aguest) AS Aguest,\n" +
                "  any_value(September) AS September,\n" +
                "  any_value(October) AS October,\n" +
                "  any_value(November) AS November,\n" +
                "  any_value(December) AS December\n" +
                "FROM costdetail_costdetail\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Febtype,\n" +
                "               any_value(sum(total)) AS February\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-02'\n" +
                "             GROUP BY type) AS Feb ON type = Febtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Martype,\n" +
                "               any_value(sum(total)) AS March\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-03'\n" +
                "             GROUP BY type) AS Mar ON type = Martype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Aprtype,\n" +
                "               any_value(sum(total)) AS April\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-04'\n" +
                "             GROUP BY type) AS Apr ON type = Aprtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Maytype,\n" +
                "               any_value(sum(total)) AS May\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-05'\n" +
                "             GROUP BY type) AS May ON type = Maytype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Junetype,\n" +
                "               any_value(sum(total)) AS June\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-06'\n" +
                "             GROUP BY type) AS June ON type = Junetype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Julytype,\n" +
                "               any_value(sum(total)) AS July\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-07'\n" +
                "             GROUP BY type) AS July ON type = Julytype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Augtype,\n" +
                "               any_value(sum(total)) AS Aguest\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-08'\n" +
                "             GROUP BY type) AS Aug ON type = Augtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Septtype,\n" +
                "               any_value(sum(total)) AS September\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-09'\n" +
                "             GROUP BY type) AS Sept ON type = Septtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Octtype,\n" +
                "               any_value(sum(total)) AS October\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-10'\n" +
                "             GROUP BY type) AS Oct ON type = Octtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Novtype,\n" +
                "               any_value(sum(total)) AS November\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-11'\n" +
                "             GROUP BY type) AS Nov ON type = Novtype\n" +
                "  LEFT JOIN (SELECT\n" +
                "               type                     AS Decetype,\n" +
                "               any_value(sum(total)) AS December\n" +
                "             FROM costdetail_costdetail\n" +
                "             WHERE date = '" + date + "-12'\n" +
                "             GROUP BY type) AS Dece ON type = Decetype\n" +
                "WHERE\n" +
                "  date = '" + date + "-01'\n" +
                "GROUP BY type;";

        
        return sql;
    }
}