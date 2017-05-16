package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.PayRecordBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.dto.PayRecordDTO;
import com.bjike.goddess.housepay.entity.PayRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 已付款记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:13 ]
 * @Description: [ 已付款记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "housepaySerCache")
@Service
public class PayRecordSerImpl extends ServiceImpl<PayRecord, PayRecordDTO> implements PayRecordSer {

    @Override
    public Long countPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        Long count = super.count(payRecordDTO);
        return count;
    }

    @Override
    public PayRecordBO getOne(String id) throws SerException {
        PayRecord payRecord = super.findById(id);
        return BeanTransform.copyProperties(payRecord,PayRecordBO.class);
    }

    @Override
    public List<PayRecordBO> findListPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        List<PayRecord> payRecords = super.findByPage(payRecordDTO);
        List<PayRecordBO> payRecordBOS = BeanTransform.copyProperties(payRecords,PayRecordBO.class);
        return payRecordBOS;
    }
    @Override
    public void removePayRecord(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public List<AreaCollectBO> collectArea(String[] areas) throws SerException {
        if(areas == null || areas.length <= 0){
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for(int i = 0;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areasStr = StringUtils.join(areasTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT  area,CONCAT(DATE_FORMAT(payTime,'%Y-%m-%d %H:%i:%s'),'') AS payTime,sum(rent) AS rent,sum(water) AS water, ");
        sb.append(" sum(energy) AS energy,sum(fee) AS fee,sum(otherFee) AS otherFee, ");
        sb.append(" sum(rent+water+energy+fee+otherFee) AS total FROM housepay_payrecord a ");
        sb.append(" WHERE area IN (");
        sb.append(areasStr);
        sb.append(")GROUP BY payTime,area ORDER BY area ");
        String sql = sb.toString();
        String [] fields = new String[]{"area","payTime","rent","water","energy","fee","otherFee","total"};
        List<AreaCollectBO> areaCollectBOS = super.findBySql(sql,AreaCollectBO.class,fields);
        return areaCollectBOS;
    }

    @Override
    public List<String> getAreas() throws SerException {
        String [] fields = new String[]{"area"};
        List<PayRecordBO> payRecordBOS = super.findBySql("select distinct area from housepay_payrecord group by area order by area asc ",PayRecordBO.class,fields);

        List<String> areasList = payRecordBOS.stream().map(PayRecordBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }
    @Override
    public List<ProjectCollectBO> collectProject(String[] projects) throws SerException {
        if(projects == null || projects.length <= 0){
            throw new SerException("汇总失败，请选择项目");
        }
        String[] projectsTemp = new String[projects.length];
        for(int i = 0;i<projects.length;i++){
            projectsTemp[i] = "'"+projects[i]+"'";
        }
        String projectsStr = StringUtils.join(projectsTemp,",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT project,CONCAT(DATE_FORMAT(payTime,'%Y-%m-%d %H:%i:%s'),'') AS payTime,sum(rent) AS rent,sum(water) AS water, ");
        sb.append(" sum(energy) AS energy,sum(fee) AS fee,sum(otherFee) AS otherFee, ");
        sb.append(" sum(rent+water+energy+fee+otherFee) AS total FROM housepay_payrecord a ");
        sb.append(" WHERE project IN ( ");
        sb.append(projectsStr);
        sb.append(" )GROUP BY payTime,project ORDER BY project ");
        String sql = sb.toString();
        System.out.println(sql);
        String [] fields = new String[]{"project","payTime","rent","water","energy","fee","otherFee","total"};
        List<ProjectCollectBO> projectCollectBOS = super.findBySql(sql,ProjectCollectBO.class,fields);
        return projectCollectBOS;
    }

    @Override
    public List<String> getProject() throws SerException {
        String[] fields = new String[]{"project"};
        List<PayRecordBO> payRecordBOS = super.findBySql("select distinct project from housepay_payrecord group by project order by project asc ", PayRecordBO.class, fields);

        List<String> projectsList = payRecordBOS.stream().map(PayRecordBO::getProject)
                .filter(project -> (project != null || !"".equals(project.trim()))).distinct().collect(Collectors.toList());


        return projectsList;
    }

}