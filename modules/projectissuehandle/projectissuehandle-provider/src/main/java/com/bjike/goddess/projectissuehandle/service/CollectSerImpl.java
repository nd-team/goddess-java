package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectissuehandle.bo.CollectBO;
import com.bjike.goddess.projectissuehandle.dto.CollectDTO;
import com.bjike.goddess.projectissuehandle.entity.Collect;
import com.bjike.goddess.projectissuehandle.enums.ProblemEmergencyDegree;
import com.bjike.goddess.projectissuehandle.enums.ProblemObject;
import com.bjike.goddess.projectissuehandle.enums.ProblemProcessingResult;
import com.bjike.goddess.projectissuehandle.enums.ProblemTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 汇总项目执行中的问题受理及处理结果业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-27 10:49 ]
 * @Description: [ 汇总项目执行中的问题受理及处理结果业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectissuehandleSerCache")
@Service
public class CollectSerImpl extends ServiceImpl<Collect, CollectDTO> implements CollectSer {
    @Autowired
    private ProblemAcceptSer problemAcceptAPI;
    @Autowired
    private ProblemHandlingResultSer problemHandlingResultAPI;

    /**
     * 汇总
     *
     * @param area area
     * @return class CollectBO
     */
    public List<CollectBO> collect(String[] area) throws SerException {
        List<CollectBO> collectList = new ArrayList<>();
        List<String> areas = Arrays.asList(area);
       /* //先查有几个地区
        List<String> areas = problemAcceptAPI.getProblemAcceptArea();*/
        //合同外部项目名称
        List<String> externalContractProjectName = problemAcceptAPI.getProblemAcceptExternalContractProjectName();

        //内部项目名称
        List<String> internalProjectName = problemAcceptAPI.getProblemAcceptInternalProjectName();
        //问题类型
        List<Integer> problemTypes = Arrays.asList(ProblemTypes.PERSONNERCLASS.getCode(), ProblemTypes.PROGRESSCLASS.getCode()
                , ProblemTypes.DELIVERCLASS.getCode(), ProblemTypes.DEVICECLASS.getCode());
        //问题紧急程度
        List<Integer> problemEmergencyDegree = Arrays.asList(ProblemEmergencyDegree.PRIMARY.getCode(),
                ProblemEmergencyDegree.INTERMEDIATE.getCode(), ProblemEmergencyDegree.EMERGENCY.getCode());
        //问题对象
        List<Integer> problemObject = Arrays.asList(ProblemObject.OPERATOR.getCode(),
                ProblemObject.VENDER.getCode(), ProblemObject.INTEGRATOR.getCode(),
                ProblemObject.GOVERNMENT.getCode(), ProblemObject.INNERSTAFF.getCode());
        //问题处理结果
        List<Integer> problemProcessingResult = Arrays.asList(ProblemProcessingResult.COMPLETE.getCode(),
                ProblemProcessingResult.UNFINISHED.getCode());

        for (String areaStr : area) {
            String[] fields = new String[]{"count", "area", "remark"};
            //获取问题类型
            String sql = "select count(*) as count , area as area ,problemTypes as enumConvert  from  projectissuehandle_problemaccept " +
                    "where problemTypes in (" + problemTypes + ") and area = " + areaStr + " group by area , problemTypes order by problemTypes asc  ";
            List<Map<String, String>> proTypeMapList = new ArrayList<>();
            List<CollectBO> collectBOS = problemAcceptAPI.findBySql(sql, CollectBO.class, fields);
            proTypeMapList = sqlQueryInt("ProblemTypes", problemTypes, collectBOS, proTypeMapList);
            //获取问题紧急程度
            sql = "select count(*) as count , area as area , problemEmergencyDegree as enumConvert  from projectissuehandle_problemaccept " +
                    "where problemEmergencyDegree in (" + problemEmergencyDegree + ") and area = " + areaStr + " group by area , problemEmergencyDegree order by problemEmergencyDegree asc ";
            List<Map<String, String>> proEmerDegreeMapList = new ArrayList<>();
            collectBOS = problemAcceptAPI.findBySql(sql, CollectBO.class, fields);
            proEmerDegreeMapList = sqlQueryInt("ProblemEmergencyDegree", problemEmergencyDegree, collectBOS, proEmerDegreeMapList);
            //获取问题对象
            sql = "select count(*) as count , area as area , problemObject as enumConvert from projectissuehandle_problemhandlingresult " +
                    "where problemObject in (" + problemObject + ") and area = " + areaStr + " group by area , problemObject order by problemObject asc ";
            List<Map<String, String>> proObjectMapList = new ArrayList<>();
            collectBOS = problemHandlingResultAPI.findBySql(sql, CollectBO.class, fields);
            proObjectMapList = sqlQueryInt("ProblemObject", problemObject, collectBOS, proObjectMapList);
            //获取问题处理结果
            sql = "select count(*) as count , area as area , problemProcessingResult as enumConvert from  projectissuehandle_problemhandlingresult " +
                    "where problemProcessingResult in (" + problemProcessingResult + ") and area = " + areaStr + " group by area , problemProcessingResult order by problemProcessingResult asc ";
            List<Map<String, String>> proProcResultMapList = new ArrayList<>();
            collectBOS = problemHandlingResultAPI.findBySql(sql, CollectBO.class, fields);
            proProcResultMapList = sqlQueryInt("ProblemProcessingResult", problemProcessingResult, collectBOS, proProcResultMapList);
            //合同外部项目名称
            sql = "select count(*) as count ,area as area , externalContractProjectName as externalContractProjectName " +
                    "where area = " + areaStr;
            List<Map<String, String>> exterProNameMapList = new ArrayList<>();
            exterProNameMapList = sqlQueryString(externalContractProjectName, fields, sql, exterProNameMapList);

            //内部项目名称
            sql = "select count(*) as count ,area as area , internalProjectName as internalProjectName " +
                    "where area = " + areaStr;
            List<Map<String, String>> interProNameMapList = new ArrayList<>();
            interProNameMapList = sqlQueryString(internalProjectName, fields, sql, interProNameMapList);
            CollectBO collectBO = new CollectBO();
            collectBO.setArea(areaStr);
            collectBO.setProblemTypes(proTypeMapList);
            collectBO.setProblemEmergencyDegree(proEmerDegreeMapList);
            collectBO.setProblemObject(proObjectMapList);
            collectBO.setProblemProcessingResult(proProcResultMapList);
            collectBO.setExternalContractProjectName(String.valueOf(externalContractProjectName));
            collectBO.setInternalProjectName(String.valueOf(internalProjectName));
            collectList.add(collectBO);
        }
        return collectList;
    }


    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<CollectBO> collectBOS = problemAcceptAPI.findBySql(sql, CollectBO.class, fields);
        if (collectBOS != null && collectBOS.size() > 0) {
            if (obj.size() == collectBOS.size()) {
                for (CollectBO cbo : collectBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CollectBO cb : collectBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的  如：地区
                List<String> diffrent = new ArrayList<>();
                for (String o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (String o : obj) {
                    for (CollectBO cbo : collectBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            areaMap.put("remark", cbo.getRemark());
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            areaMap.put("remark", o);
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }


    /**
     * 将数据库返回的枚举int值转换，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, List<CollectBO> collectBOS, List<Map<String, String>> mapList) throws SerException {

        if (collectBOS != null && collectBOS.size() > 0) {
            if (obj.size() == collectBOS.size()) {
                for (CollectBO cbo : collectBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("ProblemTypes")) {
                        areaMap.put("remark", ProblemTypes.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ProblemEmergencyDegree")) {
                        areaMap.put("remark", ProblemEmergencyDegree.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ProblemObject")) {
                        areaMap.put("remark", ProblemObject.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ProblemProcessingResult")) {
                        areaMap.put("remark", ProblemProcessingResult.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CollectBO cb : collectBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的int值  如：枚举
                List<Integer> diffrent = new ArrayList<>();
                for (Integer o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (Integer o : obj) {
                    for (CollectBO cbo : collectBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            if (enumStr.equals("ProblemTypes")) {
                                areaMap.put("remark", ProblemTypes.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemEmergencyDegree")) {
                                areaMap.put("remark", ProblemEmergencyDegree.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemObject")) {
                                areaMap.put("remark", ProblemObject.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemProcessingResult")) {
                                areaMap.put("remark", ProblemProcessingResult.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            if (enumStr.equals("ProblemTypes")) {
                                areaMap.put("remark", ProblemTypes.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemEmergencyDegree")) {
                                areaMap.put("remark", ProblemEmergencyDegree.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemObject")) {
                                areaMap.put("remark", ProblemObject.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ProblemProcessingResult")) {
                                areaMap.put("remark", ProblemProcessingResult.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }


}