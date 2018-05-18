package com.bjike.goddess.projectcalculation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.market.enums.SourceInfo;
import com.bjike.goddess.projectcalculation.bo.CalculationCollectBO;
import com.bjike.goddess.projectcalculation.dto.CalculationCollectDTO;
import com.bjike.goddess.projectcalculation.entity.CalculationCollect;
import com.bjike.goddess.projectcalculation.entity.CalculationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目测算管理汇总业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-12 10:27 ]
 * @Description: [ 项目测算管理汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcalculationSerCache")
@Service
public class CalculationCollectSerImpl extends ServiceImpl<CalculationCollect, CalculationCollectDTO> implements CalculationCollectSer {

    @Autowired
    private CalculationDetailSer calculationDetailSer;

    @Override
    public List<CalculationCollectBO> save() throws SerException {

        String str = "SELECT businessType FROM projectcalculation_calculationdetail GROUP BY businessType";
        String[] fields = {"businessType"};
        List<CalculationCollect> calculationCollects = new ArrayList<CalculationCollect>();
        List<CalculationDetail> list = calculationDetailSer.findBySql(str, CalculationDetail.class,fields);
        for (int i = 0;i<list.size();i++) {
            String sql = getSQL(list.get(i).getBusinessType());
            System.out.println(list.get(i).getBusinessType());
            String[] field = {"businessType","pendingNum","finisNum","incomplete","calculationPass","contractAmount","scale","estimatedAmount","forecastProfit","estimateTheTotalCost","artisanAllocateNum","administratorAllocateNum","equipmentAllocateNum"+
            "vehicleAllocateNum"+"finishProgressNum"+"epibolyNum"+"notEpibolyNum"};
            List<CalculationCollect> l = super.findBySql(sql,CalculationCollect.class,field);
            calculationCollects.add(l.get(0));
            System.out.println(calculationCollects.get(0).getBusinessType());
//            findBySql();
        }

        return BeanTransform.copyProperties(calculationCollects, CalculationCollectBO.class);
        /*String[] fields = {"pendingNum","businessType",   "contractAmount"};
        List<CalculationCollect> list = super.findBySql(sql,CalculationCollect.class,fields);
        System.out.println(new Date());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
        System.out.println(LocalDate.now().getMonth());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println(LocalDate.now().getYear());
        System.out.println(list.get(0).getFinisNum());
        System.out.println(list.get(0).getPendingNum());
        System.out.println(list.get(0).getBusinessType());
        System.out.println(list.get(0).getContractAmount());*/
    }

    public String newSql() {
        String sql = "SELECT\n" +
                "  calculationdetail.businessType,\n" +
                "  count(if(calculationProgress = 'no', TRUE, NULL))     AS pendingNum,\n" +
                "  count(if(calculationProgress = 'finish', TRUE, NULL)) AS finisNum,\n" +
                "  count(if(calculationProgress = 'wait', TRUE, NULL))   AS incomplete,\n" +
                "  count(if(is_calculationPass = 1, TRUE, NULL))         AS calculationPass,\n" +
                "  sum(contractAmount)                                   AS contractAmount,\n" +
                "  sum(scale)                                            AS scale,\n" +
                "  sum(estimateArtificialCost)                           AS estimatedAmount,\n" +
                "  sum(forecastProfit)                                   AS forecastProfit,\n" +
                "  sum(estimateTheTotalCost)                             AS estimateTheTotalCost,\n" +
                "  count(if(is_artisanAllocate = 1, TRUE, NULL))         AS artisanAllocateNum,\n" +
                "  count(if(is_administratorAllocate = 1, TRUE, NULL))   AS administratorAllocateNum,\n" +
                "  count(if(is_equipmentAllocate = 1, TRUE, NULL))       AS equipmentAllocateNum,\n" +
                "  count(if(is_vehicleAllocate = 1, TRUE, NULL))         AS vehicleAllocateNum,\n" +
                "  any_value(finishProgressNum)                          AS finishProgressNum,\n" +
                "  any_value(epibolyNum)                                 AS epibolyNum,\n" +
                "  any_value(notEpibolyNum)                              AS notEpibolyNum\n" +
                "FROM projectcalculation_calculationdetail AS calculationdetail LEFT JOIN (SELECT\n" +
                "                                                                            businessType,\n" +
                "                                                                            count(\n" +
                "                                                                                if(calculationProgress = 'finish', TRUE,\n" +
                "                                                                                   NULL))   AS finishProgressNum,\n" +
                "                                                                            count(if(is_epiboly = 1, TRUE,\n" +
                "                                                                                     NULL)) AS epibolyNum,\n" +
                "                                                                            count(if(is_epiboly = 0, TRUE,\n" +
                "                                                                                     NULL)) AS notEpibolyNum\n" +
                "                                                                          FROM\n" +
                "                                                                            projectcalculation_interfacecalculationdetail\n" +
                "                                                                          GROUP BY businessType) AS interface\n" +
                "    ON calculationdetail.businessType = interface.businessType\n" +
                "GROUP BY calculationdetail.businessType;";
        return sql;
    }

    public String getSQL(String businessType) throws SerException {

        String sql = "SELECT\n" +
                "  (SELECT businessType\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   GROUP BY businessType\n" +
                "   HAVING businessType = '"+businessType+"')                                                                AS businessType,\n" +
                "  (SELECT count(calculationProgress)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND calculationProgress =\n" +
                "                                     'wait' AND createTime BETWEEN '2017-12-03' AND '2017-12-12') AS pendingNum,\n" +
                "  (SELECT count(calculationProgress)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND calculationProgress = 'finish' AND\n" +
                "         createTime BETWEEN '2017-12-07' AND '2017-12-08')                                        AS finisNum,\n" +
                "  (SELECT count(calculationProgress)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND calculationProgress =\n" +
                "                                     'no' AND createTime BETWEEN '2017-12-03' AND '2017-12-12')   AS incomplete,\n" +
                "  (SELECT count(is_calculationPass)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_calculationPass =\n" +
                "                                     1 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS calculationPass,\n" +
                "  (SELECT sum(contractAmount)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND\n" +
                "         createTime BETWEEN '2017-12-07' AND '2017-12-09')                                        AS contractAmount,\n" +
                "  (SELECT sum(scale)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND createTime BETWEEN '2017-12-06' AND '2017-12-07')            AS scale,\n" +
                "  (SELECT sum(estimatedAmount)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType =\n" +
                "         '"+businessType+"' AND createTime BETWEEN '2017-12-03' AND '2017-12-12')                           AS estimatedAmount,\n" +
                "  (SELECT sum(forecastProfit)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType =\n" +
                "         '"+businessType+"' AND\n" +
                "         createTime BETWEEN '2017-12-03' AND '2017-12-12')                                        AS forecastProfit,\n" +
                "  (SELECT sum(estimateTheTotalCost)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND\n" +
                "         createTime BETWEEN '2017-12-03' AND '2017-12-05')                                        AS estimateTheTotalCost,\n" +
                "  (SELECT count(is_artisanAllocate)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_artisanAllocate =\n" +
                "                                     1 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS artisanAllocateNum,\n" +
                "  (SELECT count(is_administratorAllocate)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_administratorAllocate =\n" +
                "                                     1 AND\n" +
                "         createTime BETWEEN '2017-12-03' AND '2017-12-12')                                        AS administratorAllocateNum,\n" +
                "  (SELECT count(is_equipmentAllocate)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_administratorAllocate =\n" +
                "                                     1 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS equipmentAllocateNum,\n" +
                "  (SELECT count(is_vehicleAllocate)\n" +
                "   FROM projectcalculation_calculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_vehicleAllocate =\n" +
                "                                     1 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS vehicleAllocateNum,\n" +
                "  (SELECT count(calculationProgress)\n" +
                "   FROM projectcalculation_interfacecalculationdetail\n" +
                "   WHERE projectcalculation_calculationdetail.businessType = '"+businessType+"' AND\n" +
                "         projectcalculation_interfacecalculationdetail.calculationProgress =\n" +
                "         'finish' AND\n" +
                "         createTime BETWEEN '2017-12-03' AND '2017-12-12')                                        AS finishProgressNum,\n" +
                "  (SELECT count(is_epiboly)\n" +
                "   FROM projectcalculation_interfacecalculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_epiboly =\n" +
                "                                     1 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS epibolyNum,\n" +
                "  (SELECT count(is_epiboly)\n" +
                "   FROM projectcalculation_interfacecalculationdetail\n" +
                "   WHERE businessType = '"+businessType+"' AND is_epiboly =\n" +
                "                                     0 AND createTime BETWEEN '2017-12-03' AND '2017-12-12')      AS notEpibolyNum\n" +
                "FROM projectcalculation_calculationdetail\n" +
                "GROUP BY businessType\n" +
                "HAVING businessType = '"+businessType+"'";
        return sql;
    }
}