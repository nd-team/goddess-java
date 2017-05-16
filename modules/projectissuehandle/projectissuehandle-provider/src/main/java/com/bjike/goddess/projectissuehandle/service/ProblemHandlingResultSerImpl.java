package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectissuehandle.bo.CollectBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemHandlingResultDTO;
import com.bjike.goddess.projectissuehandle.entity.ProblemHandlingResult;
import com.bjike.goddess.projectissuehandle.to.ProblemHandlingResultTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 确认问题处理结果业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 04:30 ]
 * @Description: [ 确认问题处理结果业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class ProblemHandlingResultSerImpl extends ServiceImpl<ProblemHandlingResult, ProblemHandlingResultDTO> implements ProblemHandlingResultSer {

    @Override
    public Long countProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        problemHandlingResultDTO.getSorts().add("createTime=desc");
        Long counts = super.count(problemHandlingResultDTO);
        return counts;
    }

    @Override
    public ProblemHandlingResultBO getOne(String id) throws SerException {
        ProblemHandlingResult problemHandlingResult = super.findById(id);
        return BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class, true);
    }

    @Override
    public List<ProblemHandlingResultBO> findListProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        problemHandlingResultDTO.getSorts().add("createTime=desc");
        List<ProblemHandlingResult> problemHandlingResults = super.findByCis(problemHandlingResultDTO, true);
        List<ProblemHandlingResultBO> problemHandlingResultBOS = BeanTransform.copyProperties(problemHandlingResults, ProblemHandlingResultBO.class);
        return problemHandlingResultBOS;
    }

    @Override
    public ProblemHandlingResultBO insertProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        ProblemHandlingResult problemHandlingResult = BeanTransform.copyProperties(problemHandlingResultTO, ProblemHandlingResult.class, true);
        problemHandlingResult.setCreateTime(LocalDateTime.now());
        super.save(problemHandlingResult);
        return BeanTransform.copyProperties(problemHandlingResult, ProblemHandlingResultBO.class);
    }

    @Override
    public ProblemHandlingResultBO editProblemHandlingResult(ProblemHandlingResultTO problemHandlingResultTO) throws SerException {
        ProblemHandlingResult problemHandlingResult = super.findById(problemHandlingResultTO.getId());
        BeanTransform.copyProperties(problemHandlingResultTO, problemHandlingResult, true);
        problemHandlingResult.setModifyTime(LocalDateTime.now());
        super.update(problemHandlingResult);
        return BeanTransform.copyProperties(problemHandlingResultTO, ProblemHandlingResultBO.class);
    }

    @Override
    public void removeProblemHandlingResult(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Override
    public String exportExcel(String internalProjectName, String projectType) throws SerException {
        //TODO: xiazhili 2017-03-25 未做导出
        return null;
    }

    @Override
    public List<ProblemHandlingResultBO> searchProblemHandlingResult(ProblemHandlingResultDTO problemHandlingResultDTO) throws SerException {
        /**
         * 内部项目名称
         */
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getInternalProjectName())) {
            problemHandlingResultDTO.getConditions().add(Restrict.eq("internalProjectName", problemHandlingResultDTO.getInternalProjectName()));
        }
        /**
         * 工程类型
         */
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProjectType())) {
            problemHandlingResultDTO.getConditions().add(Restrict.eq("projectType", problemHandlingResultDTO.getProjectType()));
        }
        /**
         * 问题对象
         */
        /*if(problemHandlingResultDTO.getProblemObject()!=null){
            problemHandlingResultDTO.getConditions().add(Restrict.eq("problemObject", problemHandlingResultDTO.getProblemObject()));
        }*/
        if (StringUtils.isNotBlank(problemHandlingResultDTO.getProblemObject())) {
            problemHandlingResultDTO.getConditions().add(Restrict.eq("projectObject", problemHandlingResultDTO.getProblemObject()));
        }

        List<ProblemHandlingResult> problemHandlingResultList = super.findByCis(problemHandlingResultDTO);

        List<ProblemHandlingResultBO> problemHandlingResultBOList = BeanTransform.copyProperties(problemHandlingResultList, ProblemHandlingResultBO.class);
        return problemHandlingResultBOList;
    }

    @Override
    public List<CollectBO> collect(String[] areas) throws SerException {
        if (areas == null || areas.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for(int i = 0;i<areas.length;i++){
            areasTemp[i] = "'"+areas[i]+"'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * from(select A.*,B.person ,B.progress,B.deliver,B.device,C.elementary,C.emergency,C.intermediate,D.operator ");
        sb.append(" ,D.vender,D.intergrator,D.goverment,D.innerstaff,E.complete,E.uncomplete ");
        sb.append(" from ( ");
        sb.append(" select area as area , externalContractProjectName as externalContractProjectName ,internalProjectName as internalProjectName ");
        sb.append(" from projectissuehandle_problemaccept ");
        sb.append(" a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,area ORDER BY area ");
        sb.append(" )A,( ");
        sb.append(" SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemTypes=0 THEN problemTypeCounts END ) AS person, ");
        sb.append(" MAX( CASE WHEN problemTypes=1 THEN problemTypeCounts END ) AS progress, ");
        sb.append(" MAX( CASE WHEN problemTypes=2 THEN problemTypeCounts END ) AS deliver, ");
        sb.append(" MAX( CASE WHEN problemTypes=3 THEN problemTypeCounts END ) AS device FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemTypeCounts , problemTypes as problemTypes ,area as area,externalContractProjectName,internalProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,problemTypes,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,externalContractProjectName,internalProjectName)B, ");
        sb.append(" (SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=0 THEN problemEmergencyDegreeCounts END ) AS elementary, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=1 THEN problemEmergencyDegreeCounts END ) AS intermediate, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=2 THEN problemEmergencyDegreeCounts END ) AS emergency FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemEmergencyDegreeCounts , problemEmergencyDegree as problemEmergencyDegree ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY problemEmergencyDegree,area,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,internalProjectName,externalContractProjectName)C, ");
        sb.append(" (SELECT  area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemObject=0 THEN problemObjectCounts END ) AS operator, ");
        sb.append(" MAX( CASE WHEN problemObject=1 THEN problemObjectCounts END ) AS vender, ");
        sb.append(" MAX( CASE WHEN problemObject=2 THEN problemObjectCounts END ) AS intergrator, ");
        sb.append(" MAX( CASE WHEN problemObject=3 THEN problemObjectCounts END ) AS goverment , ");
        sb.append(" MAX( CASE WHEN problemObject=4 THEN problemObjectCounts END ) AS innerstaff FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemObjectCounts , problemObject as problemObject ,area as area ,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemObject,area ,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,problemObject,internalProjectName,externalContractProjectName)D, ");
        sb.append(" (SELECT area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=0 THEN problemProcessingResultCounts END ) AS complete, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=1 THEN problemProcessingResultCounts END ) AS uncomplete FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemProcessingResultCounts , problemProcessingResult as problemProcessingResult ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemProcessingResult,internalProjectName,externalContractProjectName,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,internalProjectName,externalContractProjectName,problemProcessingResult)E ");
        sb.append(" where A.area=B.area and A.externalContractProjectName= B.externalContractProjectName and A.externalContractProjectName=C.externalContractProjectName ");
        sb.append(" and A.internalProjectName=B.internalProjectName and C.internalProjectName=A.internalProjectName and  A.area=C.area and ");
        sb.append(" A.area=D.area and A.externalContractProjectName=D.externalContractProjectName ");
        sb.append(" and A.internalProjectName=D.internalProjectName and A.area=E.area and A.externalContractProjectName=E.externalContractProjectName ");
        sb.append(" and A.internalProjectName=E.internalProjectName ");
        sb.append(" order by area)F ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,NULL as externalContractProjectName,NULL as internalProjectName, ");
        sb.append(" sum(person) AS person,sum(progress)AS progress,sum(deliver)AS deliver, ");
        sb.append(" sum(device)AS device,sum(elementary)as elementary,sum(emergency)as emergency, ");
        sb.append(" sum(intermediate)as intermediate,sum(operator)as operator,sum(vender)as vender, ");
        sb.append(" sum(intergrator)as intergrator,sum(goverment)as goverment,sum(innerstaff)as innerstaff, ");
        sb.append(" sum(complete)as complete,sum(uncomplete)AS uncomplete ");
        sb.append(" FROM (select A.*,B.person ,B.progress,B.deliver,B.device,C.elementary,C.emergency,C.intermediate,D.operator ");
        sb.append(" ,D.vender,D.intergrator,D.goverment,D.innerstaff,E.complete,E.uncomplete ");
        sb.append(" from ( ");
        sb.append(" select area as area , externalContractProjectName as externalContractProjectName ,internalProjectName as internalProjectName ");
        sb.append(" from projectissuehandle_problemaccept ");
        sb.append(" a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,area ORDER BY area ");
        sb.append(" )A,( ");
        sb.append(" SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemTypes=0 THEN problemTypeCounts END ) AS person, ");
        sb.append(" MAX( CASE WHEN problemTypes=1 THEN problemTypeCounts END ) AS progress, ");
        sb.append(" MAX( CASE WHEN problemTypes=2 THEN problemTypeCounts END ) AS deliver, ");
        sb.append(" MAX( CASE WHEN problemTypes=3 THEN problemTypeCounts END ) AS device FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemTypeCounts , problemTypes as problemTypes ,area as area,externalContractProjectName,internalProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY externalContractProjectName,internalProjectName ,problemTypes,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,externalContractProjectName,internalProjectName)B, ");
        sb.append(" (SELECT area ,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=0 THEN problemEmergencyDegreeCounts END ) AS elementary, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=1 THEN problemEmergencyDegreeCounts END ) AS intermediate, ");
        sb.append(" MAX( CASE WHEN problemEmergencyDegree=2 THEN problemEmergencyDegreeCounts END ) AS emergency FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemEmergencyDegreeCounts , problemEmergencyDegree as problemEmergencyDegree ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemaccept a WHERE area in(%s) ");
        sb.append(" GROUP BY problemEmergencyDegree,area,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,internalProjectName,externalContractProjectName)C, ");
        sb.append(" (SELECT  area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemObject=0 THEN problemObjectCounts END ) AS operator, ");
        sb.append(" MAX( CASE WHEN problemObject=1 THEN problemObjectCounts END ) AS vender, ");
        sb.append(" MAX( CASE WHEN problemObject=2 THEN problemObjectCounts END ) AS intergrator, ");
        sb.append(" MAX( CASE WHEN problemObject=3 THEN problemObjectCounts END ) AS goverment , ");
        sb.append(" MAX( CASE WHEN problemObject=4 THEN problemObjectCounts END ) AS innerstaff FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemObjectCounts , problemObject as problemObject ,area as area ,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemObject,area ,internalProjectName,externalContractProjectName ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area ,problemObject,internalProjectName,externalContractProjectName)D, ");
        sb.append(" (SELECT area,internalProjectName,externalContractProjectName, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=0 THEN problemProcessingResultCounts END ) AS complete, ");
        sb.append(" MAX( CASE WHEN problemProcessingResult=1 THEN problemProcessingResultCounts END ) AS uncomplete FROM ");
        sb.append(" ( ");
        sb.append(" select count(*) as problemProcessingResultCounts , problemProcessingResult as problemProcessingResult ,area as area,internalProjectName,externalContractProjectName ");
        sb.append(" from  projectissuehandle_problemhandlingresult a WHERE area in(%s) ");
        sb.append(" GROUP BY problemProcessingResult,internalProjectName,externalContractProjectName,area ORDER BY area ");
        sb.append(" )a ");
        sb.append(" GROUP BY area,internalProjectName,externalContractProjectName,problemProcessingResult)E ");
        sb.append(" where A.area=B.area and A.externalContractProjectName= B.externalContractProjectName and A.externalContractProjectName=C.externalContractProjectName ");
        sb.append(" and A.internalProjectName=B.internalProjectName and C.internalProjectName=A.internalProjectName and  A.area=C.area and ");
        sb.append(" A.area=D.area and A.externalContractProjectName=D.externalContractProjectName ");
        sb.append(" and A.internalProjectName=D.internalProjectName and A.area=E.area and A.externalContractProjectName=E.externalContractProjectName ");
        sb.append(" and A.internalProjectName=E.internalProjectName ");
        sb.append(" order by area)F ");

        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr, areaStr);
        String[] fields = new String[]{"area","externalContractProjectName","internalProjectName",
                "person","progress","deliver","device","elementary","emergency","intermediate",
                "operator","vender","intergrator","goverment","innerstaff" ,"complete","uncomplete"};
        List<CollectBO> collectBOS = super.findBySql(sql, CollectBO.class, fields);
        return collectBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String [] fields = new String[]{"area"};
        List<ProblemHandlingResultBO> problemHandlingResultBOS = super.findBySql("select distinct area from projectissuehandle_problemhandlingresult group by area order by area asc ",ProblemHandlingResultBO.class,fields);

        List<String> collectList = problemHandlingResultBOS.stream().map(ProblemHandlingResultBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return collectList;
    }

}

