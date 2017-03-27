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

    @Cacheable
    @Override
    public List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        List<CusEmail> list = super.findByCis(cusEmailDTO, true);
        return BeanTransform.copyProperties(list, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {
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

        super.save(cusEmail);

        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO editCusEmail(CusEmailTO cusEmailTO) throws SerException {
        List<String> sendObjectList = cusEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CusEmail cusEmail = BeanTransform.copyProperties(cusEmailTO, CusEmail.class, true);
        cusEmail.setModifyTime(LocalDateTime.now());
        cusEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(cusEmail.getCustomerSendUnit().getCode());
        cusEmail.setSendNumAndUnit(cusEmail.getSendNum() + unit);

        //设置发送对象
        cusEmail.setSendObject(String.valueOf(emails));

        super.update(cusEmail);
        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
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


    @Cacheable
    @Override
    public CusEmailBO collectCusEmail(String[] works) throws SerException {
        List<CusEmailBO> cusEmailBOList = new ArrayList<>();

        //先查有几个地区
        List<String> areas = customerBaseInfoAPI.getCustomerBaseInfoArea();

        //再获取星级
        List<String> levels = customerLevelAPI.getAllLevel();
        //客户类别
        List<Integer> cusType = Arrays.asList(CustomerType.VIP.getCode(), CustomerType.OLD.getCode()
                , CustomerType.COOPERATOR.getCode(), CustomerType.ORDINARY.getCode());
        //客户状态
        List<Integer> cusStatus = Arrays.asList(CustomerStatus.COMPLETEPROJECT.getCode(),
                CustomerStatus.PROJECTING.getCode(), CustomerStatus.POTENTIAL.getCode());

        for (String work : works) {
            //处理地区汇总
            String[] fields = new String[]{"count", "work", "remark"};
            String sql = "select count(*) as count , workprofession as work ,area as remark  from  customer_customerbaseinfo " +
                    "where area in (" + areas + ") and workprofession = " + work + " group by workprofession , area order by area asc  ";
            List<Map<String, String>> areaMapList = new ArrayList<>();
            areaMapList = sqlQueryString(areas, fields, sql, areaMapList);

            //处理客户类别汇总
            sql = "select count(*) as count , workprofession as work ,customerType as enumConvert  from  customer_customerbaseinfo " +
                    "where customertype in (" + cusType + ") and workprofession = " + work + " group by workprofession , customertype order by customertype asc  ";
            List<Map<String, String>> cusTypeMapList = new ArrayList<>();
            cusTypeMapList = sqlQueryInt("CustomerType", cusType, fields, sql, cusTypeMapList);

            //处理客户状态汇总
            sql = "select count(*) as count , workprofession as work ,customerstatus as enumConvert  from  customer_customerbaseinfo " +
                    "where customerstatus in (" + cusStatus + ") and workprofession = " + work + " group by workprofession , customerstatus order by customerstatus asc  ";
            List<Map<String, String>> cusStatusMapList = new ArrayList<>();
            cusStatusMapList = sqlQueryInt("CustomerStatus", cusStatus, fields, sql, cusStatusMapList);

            //处理客户级别汇总customerLevel_id
            sql = " select count(*) AS count, base.workprofession AS work, csLevel.name AS remark FROM customer_customerbaseinfo AS base" +
                    " INNER JOIN customer_customerlevel AS csLevel ON base.customerLevel = csLevel.id " +
                    " WHERE csLevel.name IN (" + levels + ") AND base.workprofession = " + work + " GROUP BY base.workprofession, csLevel.name" +
                    " ORDER BY csLevel.name ASC";
            List<Map<String, String>> levelMapList = new ArrayList<>();
            levelMapList = sqlQueryString(levels, fields, sql, levelMapList);

            CusEmailBO cusEmailBO = new CusEmailBO();
            cusEmailBO.setWork(work);
            cusEmailBO.setAreaMap(areaMapList);
            cusEmailBO.setCusTypeMap(cusTypeMapList);
            cusEmailBO.setCusStatusMap(cusStatusMapList);
            cusEmailBO.setLevelMap(levelMapList);
            cusEmailBOList.add(cusEmailBO);
        }

        return null;
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
    public List<Map<String, String>> sqlQueryString(List<String> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<CusEmailBO> cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        if (cusEmailBOS != null && cusEmailBOS.size() > 0) {
            if (obj.size() == cusEmailBOS.size()) {
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
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
    public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, String[] fields, String sql, List<Map<String, String>> mapList) throws SerException {
        List<CusEmailBO> cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        if (cusEmailBOS != null && cusEmailBOS.size() > 0) {
            if (obj.size() == cusEmailBOS.size()) {
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("CustomerStatus")) {
                        areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("CustomerType")) {
                        areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (cusEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CusEmailBO cb : cusEmailBOS) {
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
                    for (CusEmailBO cbo : cusEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            if (enumStr.equals("CustomerStatus")) {
                                areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("CustomerType")) {
                                areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
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