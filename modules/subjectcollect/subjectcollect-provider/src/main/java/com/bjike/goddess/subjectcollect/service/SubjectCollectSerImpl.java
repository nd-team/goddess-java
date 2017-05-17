package com.bjike.goddess.subjectcollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.subjectcollect.bo.SubjectCollectBO;
import com.bjike.goddess.subjectcollect.dto.SubjectCollectDTO;
import com.bjike.goddess.subjectcollect.entity.SubjectCollect;
import com.bjike.goddess.subjectcollect.to.SubjectCollectTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 科目汇总表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-07 04:02 ]
 * @Description: [ 科目汇总表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "subjectcollectSerCache")
@Service
public class SubjectCollectSerImpl extends ServiceImpl<SubjectCollect, SubjectCollectDTO> implements SubjectCollectSer {

    /*@Autowired
    private SubjectCollectSer subjectCollectSer;*/
    @Transactional(rollbackFor = SerException.class)
    @Override
    public String exportExcel() throws SerException {
        //todo 未做导出
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeSubjectCollect(String id) throws SerException {
        super.remove(id);
    }

   /* @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO collectSubjectCollect(String[] firstSubject) throws SerException {
        if (firstSubject == null || firstSubject.length <= 0) {
            throw new SerException("汇总失败，请选择科目");
        }
        List<SubjectCollectBO> subjectCollectBOList = new ArrayList<>();

        //先查有几个科目
        List<String> firstSubjectList = subjectCollectSer.getSubjectCollectFirstSubject();
        StringBuffer tempFirstSubjects = new StringBuffer("");
        for (String str : firstSubjectList) {
            tempFirstSubjects.append("'" + str + "',");
        }
        String firstSubjects = String.valueOf(tempFirstSubjects).substring(0, String.valueOf(tempFirstSubjects).lastIndexOf(","));

        for (String firstSubjectStr : firstSubject) {
            //处理科目汇总
            String[] fields = new String[]{"counts", "firstSubject", "remark"};
            String sql = "select count(*) as counts ,firstSubject as firstSubject  from  subjectcollect_subjectcollect " +
                    "where firstSubject in (" + firstSubjectStr + ") firstSubject order by firstSubject asc  ";
            List<Map<String, String>> firstSubjectsMapList = new ArrayList<>();
            List<SubjectCollectBO> subjectCollectBOS = subjectCollectSer.findBySql(sql, SubjectCollectBO.class, fields);
            firstSubjectsMapList = sqlQueryString(firstSubjectList, subjectCollectBOS, firstSubjectsMapList);
        }
        SubjectCollectDTO dto = new SubjectCollectDTO();
        List<SubjectCollect> list = super.findByCis(dto);
        Double beginningDebitAmount = list.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();//期初借方余额
        Double beginningCreditAmount = list.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();//期初贷方余额
        Double issueDebitAmount = list.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();//本期借方发生额
        Double issueCreditAmount = list.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();//本期贷方发生额
        Double endDebitAmount = list.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();//期末借方余额
        Double endCreditAmount = list.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();//期末贷方余额
        SubjectCollect subjectCollect = new SubjectCollect("合计", beginningDebitAmount, beginningCreditAmount, issueDebitAmount, issueCreditAmount,endDebitAmount,endCreditAmount);
        list.add(subjectCollect);
        return BeanTransform.copyProperties(subjectCollect, SubjectCollectBO.class);
    }

    @Override
    public List<String> getSubjectCollectFirstSubject() throws SerException {
        String[] fields = new String[]{"firstSubject"};
        List<SubjectCollectBO> subjectCollectBOS = super.findBySql("select firstSubject from subjectcollect_subjectcollect order by firstSubject asc ", SubjectCollectBO.class, fields);

        List<String> firstSubjectList = subjectCollectBOS.stream().map(SubjectCollectBO::getFirstSubject)
                .filter(firstSubject -> (firstSubject != null || !"".equals(firstSubject.trim()))).distinct().collect(Collectors.toList());


        return firstSubjectList;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO collectArea(String[] area) throws SerException {
        if (area == null || area.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        List<SubjectCollectBO> subjectCollectBOList = new ArrayList<>();

        //先查有几个地区
        List<String> areaList = subjectCollectSer.getSubjectCollectArea();
        StringBuffer tempAreas = new StringBuffer("");
        for (String str : areaList) {
            tempAreas.append("'" + str + "',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));

        for (String areaStr : area) {
            //处理地区汇总
            String[] fields = new String[]{"counts", "area", "remark"};
            String sql = "select count(*) as counts ,area as area  from  subjectcollect_subjectcollect " +
                    "where area in (" + areaStr + ") area order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            List<SubjectCollectBO> subjectCollectBOS = subjectCollectSer.findBySql(sql, SubjectCollectBO.class, fields);
            areaMapList = sqlQueryString(areaList, subjectCollectBOS, areaMapList);
        }
        SubjectCollectDTO dto = new SubjectCollectDTO();
        List<SubjectCollect> list = super.findByCis(dto);
        Double beginningDebitAmount = list.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();//期初借方余额
        Double beginningCreditAmount = list.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();//期初贷方余额
        Double issueDebitAmount = list.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();//本期借方发生额
        Double issueCreditAmount = list.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();//本期贷方发生额
        Double endDebitAmount = list.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();//期末借方余额
        Double endCreditAmount = list.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();//期末贷方余额
        SubjectCollect subjectCollect = new SubjectCollect("合计",null,null, beginningDebitAmount, beginningCreditAmount, issueDebitAmount, issueCreditAmount,endDebitAmount,endCreditAmount);
        list.add(subjectCollect);
        return BeanTransform.copyProperties(subjectCollect, SubjectCollectBO.class);
    }

    @Override
    public List<String> getSubjectCollectArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<SubjectCollectBO> subjectCollectBOS = super.findBySql("select area from subjectcollect_subjectcollect order by area asc ", SubjectCollectBO.class, fields);

        List<String> areaList = subjectCollectBOS.stream().map(SubjectCollectBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO collectProjectName(String[] projectName) throws SerException {
        if (projectName == null || projectName.length <= 0) {
            throw new SerException("汇总失败，请选择项目名称");
        }
        List<SubjectCollectBO> subjectCollectBOList = new ArrayList<>();

        //先查有几个项目名称
        List<String> projectNameList = subjectCollectSer.getSubjectCollectProjectName();
        StringBuffer tempProjectNames = new StringBuffer("");
        for (String str : projectNameList) {
            tempProjectNames.append("'" + str + "',");
        }
        String projectNames = String.valueOf(tempProjectNames).substring(0, String.valueOf(tempProjectNames).lastIndexOf(","));

        for (String projectNameStr : projectName) {
            //处理项目名称汇总
            String[] fields = new String[]{"counts", "projectName", "remark"};
            String sql = "select count(*) as counts ,projectName as projectName  from  subjectcollect_subjectcollect " +
                    "where projectName in (" + projectNameStr + ") projectName order by projectName asc  ";
            List<Map<String, String>> projectNameMapList = new ArrayList<>();
            List<SubjectCollectBO> subjectCollectBOS = subjectCollectSer.findBySql(sql, SubjectCollectBO.class, fields);
            projectNameMapList = sqlQueryString(projectNameList, subjectCollectBOS, projectNameMapList);
        }
        SubjectCollectDTO dto = new SubjectCollectDTO();
        List<SubjectCollect> list = super.findByCis(dto);
        Double beginningDebitAmount = list.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();//期初借方余额
        Double beginningCreditAmount = list.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();//期初贷方余额
        Double issueDebitAmount = list.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();//本期借方发生额
        Double issueCreditAmount = list.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();//本期贷方发生额
        Double endDebitAmount = list.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();//期末借方余额
        Double endCreditAmount = list.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();//期末贷方余额
        SubjectCollect subjectCollect = new SubjectCollect("合计",null,null, beginningDebitAmount, beginningCreditAmount, issueDebitAmount, issueCreditAmount,endDebitAmount,endCreditAmount);
        list.add(subjectCollect);
        return BeanTransform.copyProperties(subjectCollect, SubjectCollectBO.class);
    }

    @Override
    public List<String> getSubjectCollectProjectName() throws SerException {
        String[] fields = new String[]{"projectName"};
        List<SubjectCollectBO> subjectCollectBOS = super.findBySql("select projectName from subjectcollect_subjectcollect order by projectName asc ", SubjectCollectBO.class, fields);

        List<String> proNameList = subjectCollectBOS.stream().map(SubjectCollectBO::getProjectName)
                .filter(projectName -> (projectName != null || !"".equals(projectName.trim()))).distinct().collect(Collectors.toList());


        return proNameList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SubjectCollectBO collectProjectGroup(String[] projectGroup) throws SerException {
        if (projectGroup == null || projectGroup.length <= 0) {
            throw new SerException("汇总失败，请选择项目组");
        }
        List<SubjectCollectBO> subjectCollectBOList = new ArrayList<>();

        //先查有几个项目组
        List<String> projectGroupList = subjectCollectSer.getSubjectCollectProjectGroup();
        StringBuffer tempProjectGroups = new StringBuffer("");
        for (String str : projectGroupList) {
            tempProjectGroups.append("'" + str + "',");
        }
        String projectGroups = String.valueOf(tempProjectGroups).substring(0, String.valueOf(tempProjectGroups).lastIndexOf(","));

        for (String projectGroupStr : projectGroup) {
            //处理项目组汇总
            String[] fields = new String[]{"counts", "projectGroup", "remark"};
            String sql = "select count(*) as counts ,projectGroup as projectGroup  from  subjectcollect_subjectcollect " +
                    "where projectGroup in (" + projectGroupStr + ") projectGroup order by projectGroup asc  ";
            List<Map<String, String>> projectGroupMapList = new ArrayList<>();
            List<SubjectCollectBO> subjectCollectBOS = subjectCollectSer.findBySql(sql, SubjectCollectBO.class, fields);
            projectGroupMapList = sqlQueryString(projectGroupList, subjectCollectBOS, projectGroupMapList);
        }
        SubjectCollectDTO dto = new SubjectCollectDTO();
        List<SubjectCollect> list = super.findByCis(dto);
        Double beginningDebitAmount = list.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();//期初借方余额
        Double beginningCreditAmount = list.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();//期初贷方余额
        Double issueDebitAmount = list.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();//本期借方发生额
        Double issueCreditAmount = list.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();//本期贷方发生额
        Double endDebitAmount = list.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();//期末借方余额
        Double endCreditAmount = list.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();//期末贷方余额
        SubjectCollect subjectCollect = new SubjectCollect("合计",null,null, beginningDebitAmount, beginningCreditAmount, issueDebitAmount, issueCreditAmount,endDebitAmount,endCreditAmount);
        list.add(subjectCollect);
        return BeanTransform.copyProperties(subjectCollect, SubjectCollectBO.class);
    }

    @Override
    public List<String> getSubjectCollectProjectGroup() throws SerException {
        String[] fields = new String[]{"projectGroup"};
        List<SubjectCollectBO> subjectCollectBOS = super.findBySql("select projectGroup from subjectcollect_subjectcollect order by projectGroup asc ", SubjectCollectBO.class, fields);

        List<String> proGroupList = subjectCollectBOS.stream().map(SubjectCollectBO::getProjectGroup)
                .filter(projectGroup -> (projectGroup != null || !"".equals(projectGroup.trim()))).distinct().collect(Collectors.toList());


        return proGroupList;
    }
    *//**
     * 数据库查询返回，然后添加map数组
     *//*
    public List<Map<String, String>> sqlQueryString(List<String> obj, List<SubjectCollectBO> subjectCollectBOS, List<Map<String, String>> mapList) throws SerException {

        if (subjectCollectBOS != null && subjectCollectBOS.size() > 0) {
            if (obj.size() == subjectCollectBOS.size()) {
                for (SubjectCollectBO cbo : subjectCollectBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (subjectCollectBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (SubjectCollectBO cb : subjectCollectBOS) {
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
                    for (SubjectCollectBO cbo : subjectCollectBOS) {
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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<SubjectCollectBO> collectCompare(SubjectCollectTO subjectCollectTO) throws SerException {
        try {
            if (StringUtils.isBlank(subjectCollectTO.getStartTime()) && StringUtils.isBlank(subjectCollectTO.getEndTime())) {
                throw new SerException("对比汇总日期不能为空");
            }
            SubjectCollectDTO dto = new SubjectCollectDTO();
            List<SubjectCollect> list = super.findByCis(dto);

            List<SubjectCollectBO> pageList = new ArrayList<>();
            if(subjectCollectTO.equals("area")) {
                List<String> subjectCollectString = subjectCollectSer.getAreas();
                for (String rs : subjectCollectString) {
                    List<SubjectCollect> subjectCollects = new ArrayList<SubjectCollect>();
                    for (SubjectCollect all : list) {
                        if (all.getArea().equals(rs)) {
                            subjectCollects.add(all);
                        }
                    }
                    Double beginningDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();
                    Double beginningCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();
                    Double issueCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();
                    Double endCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    Double beginMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    SubjectCollectBO subjectCollectBO = new SubjectCollectBO(rs,beginningDebitAmount,beginningCreditAmount,beginMinusMoney,issueDebitAmount,issueCreditAmount,issueMinusMoney,endDebitAmount,endCreditAmount,endMinusMoney);
                    pageList.add(subjectCollectBO);
                }

            }else if (subjectCollectTO.equals("projectName")){
                List<String> subjectCollectString = subjectCollectSer.getProjectName();
                for (String rs : subjectCollectString) {
                    List<SubjectCollect> subjectCollects = new ArrayList<SubjectCollect>();
                    for (SubjectCollect all : list) {
                        if (all.getArea().equals(rs)) {
                            subjectCollects.add(all);
                        }
                    }
                    Double beginningDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();
                    Double beginningCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();
                    Double issueCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();
                    Double endCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    Double beginMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    SubjectCollectBO subjectCollectBO = new SubjectCollectBO(rs,beginningDebitAmount,beginningCreditAmount,beginMinusMoney,issueDebitAmount,issueCreditAmount,issueMinusMoney,endDebitAmount,endCreditAmount,endMinusMoney);
                    pageList.add(subjectCollectBO);
                }
            }else if(subjectCollectTO.equals("projectGroup")){
                List<String> subjectCollectString = subjectCollectSer.getProjectGroup();
                for (String rs : subjectCollectString) {
                    List<SubjectCollect> subjectCollects = new ArrayList<SubjectCollect>();
                    for (SubjectCollect all : list) {
                        if (all.getArea().equals(rs)) {
                            subjectCollects.add(all);
                        }
                    }
                    Double beginningDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum();
                    Double beginningCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum();
                    Double issueCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endDebitAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum();
                    Double endCreditAmount = subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    Double beginMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getBeginningCreditAmount).sum();
                    Double issueMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getIssueDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getIssueCreditAmount).sum();
                    Double endMinusMoney = subjectCollects.stream().mapToDouble(SubjectCollect::getEndDebitAmount).sum()-
                            subjectCollects.stream().mapToDouble(SubjectCollect::getEndCreditAmount).sum();
                    SubjectCollectBO subjectCollectBO = new SubjectCollectBO(rs,beginningDebitAmount,beginningCreditAmount,beginMinusMoney,issueDebitAmount,issueCreditAmount,issueMinusMoney,endDebitAmount,endCreditAmount,endMinusMoney);
                    pageList.add(subjectCollectBO);
                }
            }
            //合计
            Double beginningDebitAmount = pageList.stream().mapToDouble(SubjectCollectBO::getBeginningDebitAmount).sum();
            Double beginningCreditAmount = pageList.stream().mapToDouble(SubjectCollectBO::getBeginningCreditAmount).sum();
            Double issueDebitAmount = pageList.stream().mapToDouble(SubjectCollectBO::getIssueDebitAmount).sum();
            Double issueCreditAmount = pageList.stream().mapToDouble(SubjectCollectBO::getIssueCreditAmount).sum();
            Double endDebitAmount = pageList.stream().mapToDouble(SubjectCollectBO::getEndDebitAmount).sum();
            Double endCreditAmount = pageList.stream().mapToDouble(SubjectCollectBO::getEndCreditAmount).sum();
            Double beginMinusMoney = pageList.stream().mapToDouble(SubjectCollectBO::getBeginMinusMoney).sum();
            Double issueMinusMoney = pageList.stream().mapToDouble(SubjectCollectBO::getIssueMinusMoney).sum();
            Double endMinusMoney = pageList.stream().mapToDouble(SubjectCollectBO::getEndMinusMoney).sum();
            SubjectCollectBO subjectCollectBO = new SubjectCollectBO("合计",beginningDebitAmount,beginningCreditAmount,beginMinusMoney,issueDebitAmount,issueCreditAmount,issueMinusMoney,endDebitAmount,endCreditAmount,endMinusMoney);
            pageList.add(subjectCollectBO);
            return pageList;
        }catch (SerException e){
            throw new SerException(e.getMessage());
        }
    }*/
}