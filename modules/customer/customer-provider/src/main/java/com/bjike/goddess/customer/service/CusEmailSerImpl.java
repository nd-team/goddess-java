package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.entity.CusEmail;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.to.CusEmailTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 客户邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 客户邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class CusEmailSerImpl extends ServiceImpl<CusEmail, CusEmailDTO> implements CusEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CustomerBaseInfoSer customerBaseInfoAPI;
    @Autowired
    private CustomerLevelSer customerLevelAPI;

    @Override
    public Long countCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return super.count(cusEmailDTO);
    }

    @Cacheable
    @Override
    public List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        List<CusEmail> list = super.findByCis(cusEmailDTO, true);
        return BeanTransform.copyProperties(list, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {
        List<String> works = Arrays.asList(cusEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }


        List<String> sendObjectList = cusEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CusEmail cusEmail = BeanTransform.copyProperties(cusEmailTO, CusEmail.class, true);
        cusEmail.setCreateTime(LocalDateTime.now());
        cusEmail.setStatus(Status.THAW);
        cusEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(cusEmail.getCustomerSendUnit().getCode());
        cusEmail.setSendNumAndUnit(cusEmail.getSendNum() + unit);

        //设置发送对象
        cusEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        cusEmail.setLastSendTime(LocalDateTime.now());
        //设置行业
        cusEmail.setWork(String.valueOf(workBuf));

        super.save(cusEmail);

        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO editCusEmail(CusEmailTO cusEmailTO) throws SerException {
        List<String> works = Arrays.asList(cusEmailTO.getWorks());
        //设置行业
        StringBuffer workBuf = new StringBuffer("");
        if (works != null && works.size() > 0) {
            for (String work : works) {
                workBuf.append(work.trim() + ";");
            }
        }

        List<String> sendObjectList = cusEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }

        //先查出来
        CusEmail getEmail = super.findById(cusEmailTO.getId());
        CusEmail cusEmail = BeanTransform.copyProperties(cusEmailTO, CusEmail.class, true);
        BeanUtils.copyProperties(cusEmail,getEmail,"sendNumAndUnit",
                "sendObject","work","createTime","id","status","lastSendTime");
        getEmail.setModifyTime(LocalDateTime.now());
        getEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(cusEmail.getCustomerSendUnit().getCode());
        getEmail.setSendNumAndUnit(cusEmail.getSendNum() + unit);

        //设置发送对象
        getEmail.setSendObject(String.valueOf(emails));
        //设置行业
        getEmail.setWork(String.valueOf(workBuf));

        super.update(getEmail);
        return BeanTransform.copyProperties(getEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCusEmail(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCusEmail(String id) throws SerException {
        CusEmail cusEmail = super.findById(id);
        cusEmail.setStatus(Status.CONGEAL);
        super.update(cusEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCusEmail(String id) throws SerException {
        CusEmail cusEmail = super.findById(id);
        cusEmail.setStatus(Status.THAW);
        super.update(cusEmail);
    }


    
    @Override
    public List<CusEmailBO> collectCusEmail(String[] works) throws SerException {
        if(works == null || works.length<=0 ){
            throw new SerException("汇总失败，请选择行业");
        }
        List<CusEmailBO> cusEmailBOList = new ArrayList<>();

        //先查有几个地区
        List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoArea();
        StringBuffer tempAreas = new StringBuffer("");
        for(String str : areaList){
            tempAreas.append("'"+str+"',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));

        //再获取星级
        List<String> levelList = customerLevelAPI.getAllLevel();
        StringBuffer tempLevels = new StringBuffer("");
        for(String level : levelList){
            tempLevels.append("'"+level+"',");
        }
        String levels = String.valueOf(tempLevels).substring(0, String.valueOf(tempLevels).lastIndexOf(","));

        //客户类别
        List<Integer> cusTypeList = Arrays.asList(CustomerType.VIP.getCode(), CustomerType.OLD.getCode()
                , CustomerType.COOPERATOR.getCode(), CustomerType.ORDINARY.getCode());
        StringBuffer tempCusTypes = new StringBuffer("");
        for(Integer str : cusTypeList){
            tempCusTypes.append(str+",");
        }
        String cusType = String.valueOf(tempCusTypes).substring(0, String.valueOf(tempCusTypes).lastIndexOf(","));

        //客户状态
        List<Integer> cusStatusList = Arrays.asList(CustomerStatus.COMPLETEPROJECT.getCode(),
                CustomerStatus.PROJECTING.getCode(), CustomerStatus.POTENTIAL.getCode());
        StringBuffer tempCusStatus = new StringBuffer("");
        for(Integer str : cusStatusList){
            tempCusStatus.append(str+",");
        }
        String cusStatus = String.valueOf(tempCusStatus).substring(0, String.valueOf(tempCusStatus).lastIndexOf(","));

        for (String work : works) {
            //处理地区汇总
            String[] fields = new String[]{"counts", "work", "remark"};
            String sql = "select count(*) as counts , workprofession as work ,area as remark  from  customer_customerbaseinfo " +
                    "where area in (" + areas + ") and workprofession = '" + work + "' group by workprofession , area order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            List<CusEmailBO> cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
            areaMapList = sqlQueryString(areaList, cusEmailBOS, areaMapList);

            //处理客户级别汇总customerLevel_id
            sql = " select count(*) AS counts, base.workprofession AS work, csLevel.name AS remark FROM customer_customerbaseinfo AS base" +
                    " INNER JOIN customer_customerlevel AS csLevel ON base.customerLevel_id = csLevel.id " +
                    " WHERE csLevel.name IN (" + levels + ") AND base.workprofession = '" + work + "' GROUP BY base.workprofession, csLevel.name" +
                    " ORDER BY csLevel.name ASC";
            List<Map<String, String>> levelMapList = new ArrayList<>();
            cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
            levelMapList = sqlQueryString(levelList, cusEmailBOS, levelMapList);

            fields = new String[]{"counts", "work", "enumConvert"};
            //处理客户类别汇总
            sql = "select count(*) as counts , workprofession as work ,customerType as enumConvert  from  customer_customerbaseinfo " +
                    "where customertype in (" + cusType + ") and workprofession = '" + work + "' group by workprofession , customertype order by customertype asc  ";
            List<Map<String, String>> cusTypeMapList = new ArrayList<>();
            cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
            cusTypeMapList = sqlQueryInt("CustomerType", cusTypeList,  cusEmailBOS, cusTypeMapList);

            //处理客户状态汇总
            sql = "select count(*) as counts , workprofession as work ,customerstatus as enumConvert  from  customer_customerbaseinfo " +
                    "where customerstatus in (" + cusStatus + ") and workprofession = '" + work + "' group by workprofession , customerstatus order by customerstatus asc  ";
            List<Map<String, String>> cusStatusMapList = new ArrayList<>();
            cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
            cusStatusMapList = sqlQueryInt("CustomerStatus", cusStatusList, cusEmailBOS, cusStatusMapList);



            CusEmailBO cusEmailBO = new CusEmailBO();
            cusEmailBO.setWork(work);
            cusEmailBO.setAreaMap(areaMapList);
            cusEmailBO.setCusTypeMap(cusTypeMapList);
            cusEmailBO.setCusStatusMap(cusStatusMapList);
            cusEmailBO.setLevelMap(levelMapList);
            cusEmailBOList.add(cusEmailBO);
        }

        //合计计算总数
        cusEmailBOList = calcuteCount(areaList, levelList, cusTypeList, cusStatusList, cusEmailBOList);

        return cusEmailBOList;
    }

    /**
     * 合计计算总数
     * @param areaList
     * @param levelList
     * @param cusTypeList
     * @param cusStatusList
     * @param cusEmailBOList
     * @return
     * @throws SerException
     */
    public List<CusEmailBO> calcuteCount(List<String> areaList, List<String> levelList,
                                                  List<Integer> cusTypeList, List<Integer> cusStatusList,
                                                  List<CusEmailBO> cusEmailBOList) throws SerException {
        String[] fields = new String[]{"counts", "remark"};

        /**
         * 地区
         */
        StringBuffer tempAreas = new StringBuffer("");
        for(String str : areaList){
            tempAreas.append("'"+str+"',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));
        String sql = "select count(*) as counts , area as remark  from  customer_customerbaseinfo " +
                "where area in (" + areas + ")  group by area  order by area asc  ";
        List<Map<String, String>> areaMapList = new ArrayList<>();
        List<CusEmailBO> cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        areaMapList = sqlQueryString(areaList, cusEmailBOS, areaMapList);

        //处理客户级别汇总customerLevel_id
        StringBuffer tempLevels = new StringBuffer("");
        for(String str : levelList){
            tempLevels.append("'"+str+"',");
        }
        String levels = String.valueOf(tempLevels).substring(0, String.valueOf(tempLevels).lastIndexOf(","));
        sql = " select count(*) AS counts,  csLevel.name AS remark FROM customer_customerbaseinfo AS base" +
                " INNER JOIN customer_customerlevel AS csLevel ON base.customerLevel_id = csLevel.id " +
                " WHERE csLevel.name IN (" + levels + ") GROUP BY  csLevel.name" +
                " ORDER BY csLevel.name ASC";
        List<Map<String, String>> levelMapList = new ArrayList<>();
        cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        levelMapList = sqlQueryString(levelList, cusEmailBOS, levelMapList);


        fields = new String[]{"counts", "enumConvert"};
        /**
         * 客户类别
         */
        StringBuffer tempCusTypes = new StringBuffer("");
        for(Integer str : cusTypeList){
            tempCusTypes.append(str+",");
        }
        String cusType = String.valueOf(tempCusTypes).substring(0, String.valueOf(tempCusTypes).lastIndexOf(","));
        sql = "select count(*) as counts , customertype as enumConvert  from  customer_customerbaseinfo " +
                "where customertype in (" + cusType + ")  group by customertype  order by customertype asc  ";
        List<Map<String, String>> cusTypeMapList = new ArrayList<>();
        cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        cusTypeMapList = sqlQueryInt("CustomerType", cusTypeList, cusEmailBOS, cusTypeMapList);

        //处理客户状态汇总
        StringBuffer tempCusStatus = new StringBuffer("");
        for(Integer str : cusStatusList){
            tempCusStatus.append(str+",");
        }
        String cusStatus = String.valueOf(tempCusStatus).substring(0, String.valueOf(tempCusStatus).lastIndexOf(","));
        sql = "select count(*) as counts , customerstatus as enumConvert  from  customer_customerbaseinfo " +
                "where customerstatus in (" + cusStatus + ")  group by  customerstatus order by customerstatus asc  ";
        List<Map<String, String>> cusStatusMapList = new ArrayList<>();
        cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        cusStatusMapList = sqlQueryInt("CustomerStatus", cusStatusList, cusEmailBOS, cusStatusMapList);




        CusEmailBO cusEBO = new CusEmailBO();
        cusEBO.setWork("合计");
        cusEBO.setAreaMap(areaMapList);
        cusEBO.setCusTypeMap(cusTypeMapList);
        cusEBO.setCusStatusMap(cusStatusMapList);
        cusEBO.setLevelMap(levelMapList);
        cusEmailBOList.add(cusEBO);

        return cusEmailBOList;
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, List<CusEmailBO> cusEmailBOS, List<Map<String, String>> mapList) throws SerException {

        if (cusEmailBOS != null && cusEmailBOS.size() > 0) {
            if (obj.size() == cusEmailBOS.size()) {
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()==null ? 0:cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (cusEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CusEmailBO cb : cusEmailBOS) {
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
                    for (CusEmailBO cbo : cusEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            areaMap.put("remark", cbo.getRemark());
                            areaMap.put("count", String.valueOf(cbo.getCounts()==null ? 0:cbo.getCounts()));
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
    public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, List<CusEmailBO> cusEmailBOS, List<Map<String, String>> mapList) throws SerException {
        if (cusEmailBOS != null && cusEmailBOS.size() > 0) {
            if (obj.size() == cusEmailBOS.size()) {
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("CustomerStatus")) {
                        areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("CustomerType")) {
                        areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts()==null ? 0:cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (cusEmailBOS.size() < obj.size()) {
                List<Integer> cbStr = new ArrayList<>();
                for (CusEmailBO cb : cusEmailBOS) {
                    cbStr.add(cb.getEnumConvert());
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
                    for (CusEmailBO cbo : cusEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getEnumConvert()==o ) {
                            if (enumStr.equals("CustomerStatus")) {
                                areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("CustomerType")) {
                                areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", String.valueOf(cbo.getCounts()==null ? 0:cbo.getCounts()));
                        } else {
                            if (enumStr.equals("CustomerStatus")) {
                                areaMap.put("remark", CustomerStatus.getStrConvert(o));
                            } else if (enumStr.equals("CustomerType")) {
                                areaMap.put("remark", CustomerType.getStrConvert(o));
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