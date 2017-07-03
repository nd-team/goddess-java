package com.bjike.goddess.customer.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.regex.Validator;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.entity.CusEmail;
import com.bjike.goddess.customer.enums.CustomerSendUnit;
import com.bjike.goddess.customer.enums.CustomerStatus;
import com.bjike.goddess.customer.enums.CustomerType;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.CusEmailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CusEmailSer cusEmailSer;
    @Autowired
    private MessageAPI messageAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId ) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId ) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity( String flagId ) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity( String flagId ) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId );
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1" );
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity( "5"  );
        if( flagSee || flagAdd ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity( "1" );
                break;
            case ADD:
                flag = guideAddIdentity( "5" );
                break;
            case EDIT:
                flag = guideAddIdentity( "5" );
                break;
            case DELETE:
                flag = guideAddIdentity( "5" );
                break;
            case CONGEL:
                flag = guideAddIdentity( "5" );
                break;
            case THAW:
                flag = guideAddIdentity( "5");
                break;
            case COLLECT:
                flag = guideAddIdentity( "5" );
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        return super.count(cusEmailDTO);
    }

    @Cacheable
    @Override
    public List<CusEmailBO> listCusEmail(CusEmailDTO cusEmailDTO) throws SerException {
        checkSeeIdentity("1");

        cusEmailDTO.getSorts().add("createTime=desc");
        List<CusEmail> list = super.findByCis(cusEmailDTO, true);
        return BeanTransform.copyProperties(list, CusEmailBO.class);
    }

    @Override
    public CusEmailBO getCusEmailById(String id) throws SerException {
        CusEmail cusEmail = super.findById(id);
        return BeanTransform.copyProperties(cusEmail, CusEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CusEmailBO addCusEmail(CusEmailTO cusEmailTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块添加权限
        checkAddIdentity("5");
        RpcTransmit.transmitUserToken( userToken );

        if (cusEmailTO.getWorks() == null || cusEmailTO.getWorks().length <= 0) {
            throw new SerException("行业数组必须填");
        }
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
                if(!Validator.isEmail( emailStr)){
                    throw new SerException("邮箱书写不正确");
                }
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
        String userToken = RpcTransmit.getUserToken();
        //商务模块编辑权限
        checkAddIdentity("5");
        RpcTransmit.transmitUserToken( userToken );


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
                if(!Validator.isEmail( emailStr)){
                    throw new SerException("邮箱书写不正确");
                }
                emails.append(emailStr + ";");
            }
        }

        //先查出来
        CusEmail getEmail = super.findById(cusEmailTO.getId());
        CusEmail cusEmail = BeanTransform.copyProperties(cusEmailTO, CusEmail.class, true);
        BeanUtils.copyProperties(cusEmail, getEmail, "sendNumAndUnit",
                "sendObject", "work", "createTime", "id", "status", "lastSendTime");
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
        //商务模块删除权限
        checkAddIdentity("5");

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCusEmail(String id) throws SerException {
        //商务模块冻结权限
        checkAddIdentity("5");
        CusEmail cusEmail = super.findById(id);
        cusEmail.setStatus(Status.CONGEAL);
        super.update(cusEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCusEmail(String id) throws SerException {
        //商务模块解冻权限
        checkAddIdentity("5");

        CusEmail cusEmail = super.findById(id);
        cusEmail.setStatus(Status.THAW);
        super.update(cusEmail);
    }


    @Override
    public List<CusEmailBO> collectCusEmail(String[] works) throws SerException {
        if (works == null || works.length <= 0) {
            throw new SerException("汇总失败，请选择行业");
        }
        List<CusEmailBO> cusEmailBOList = new ArrayList<>();

        //先查有几个地区
        List<String> areaList = customerBaseInfoAPI.getCustomerBaseInfoArea();
        StringBuffer tempAreas = new StringBuffer("");
        for (String str : areaList) {
            tempAreas.append("'" + str + "',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));

        //再获取星级
        List<String> levelList = customerLevelAPI.getAllLevel();
        StringBuffer tempLevels = new StringBuffer("");
        for (String level : levelList) {
            tempLevels.append("'" + level + "',");
        }
        String levels = String.valueOf(tempLevels).substring(0, String.valueOf(tempLevels).lastIndexOf(","));

        //客户类别
        List<Integer> cusTypeList = Arrays.asList(CustomerType.VIP.getCode(), CustomerType.OLD.getCode()
                , CustomerType.COOPERATOR.getCode(), CustomerType.ORDINARY.getCode());
        StringBuffer tempCusTypes = new StringBuffer("");
        for (Integer str : cusTypeList) {
            tempCusTypes.append(str + ",");
        }
        String cusType = String.valueOf(tempCusTypes).substring(0, String.valueOf(tempCusTypes).lastIndexOf(","));

        //客户状态
        List<Integer> cusStatusList = Arrays.asList(CustomerStatus.COMPLETEPROJECT.getCode(),
                CustomerStatus.PROJECTING.getCode(), CustomerStatus.POTENTIAL.getCode());
        StringBuffer tempCusStatus = new StringBuffer("");
        for (Integer str : cusStatusList) {
            tempCusStatus.append(str + ",");
        }
        String cusStatus = String.valueOf(tempCusStatus).substring(0, String.valueOf(tempCusStatus).lastIndexOf(","));

        StringBuffer worksBuffer = new StringBuffer("");
        for (String work : works) {
            worksBuffer.append("'"+work+"',");
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
            cusTypeMapList = sqlQueryInt("CustomerType", cusTypeList, cusEmailBOS, cusTypeMapList);

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
        cusEmailBOList = calcuteCount(areaList, levelList, cusTypeList, cusStatusList, cusEmailBOList, worksBuffer);

        return cusEmailBOList;
    }


    /**
     * 合计计算总数
     *
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
                                         List<CusEmailBO> cusEmailBOList , StringBuffer worksBuffer) throws SerException {
        String works = StringUtils.substringBeforeLast(worksBuffer.toString(),",");
        String[] fields = new String[]{"counts", "remark"};

        /**
         * 地区
         */
        StringBuffer tempAreas = new StringBuffer("");
        for (String str : areaList) {
            tempAreas.append("'" + str + "',");
        }
        String areas = String.valueOf(tempAreas).substring(0, String.valueOf(tempAreas).lastIndexOf(","));
        String sql = "select count(*) as counts , area as remark  from  customer_customerbaseinfo " +
                "where area in (" + areas + ") and workprofession in("+works+") group by area  order by area asc  ";
        List<Map<String, String>> areaMapList = new ArrayList<>();
        List<CusEmailBO> cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        areaMapList = sqlQueryString(areaList, cusEmailBOS, areaMapList);

        //处理客户级别汇总customerLevel_id
        StringBuffer tempLevels = new StringBuffer("");
        for (String str : levelList) {
            tempLevels.append("'" + str + "',");
        }
        String levels = String.valueOf(tempLevels).substring(0, String.valueOf(tempLevels).lastIndexOf(","));
        sql = " select count(*) AS counts,  csLevel.name AS remark FROM customer_customerbaseinfo AS base" +
                " INNER JOIN customer_customerlevel AS csLevel ON base.customerLevel_id = csLevel.id " +
                " WHERE csLevel.name IN (" + levels + ") and workprofession in("+works+") GROUP BY  csLevel.name" +
                " ORDER BY csLevel.name ASC";
        List<Map<String, String>> levelMapList = new ArrayList<>();
        cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        levelMapList = sqlQueryString(levelList, cusEmailBOS, levelMapList);


        fields = new String[]{"counts", "enumConvert"};
        /**
         * 客户类别
         */
        StringBuffer tempCusTypes = new StringBuffer("");
        for (Integer str : cusTypeList) {
            tempCusTypes.append(str + ",");
        }
        String cusType = String.valueOf(tempCusTypes).substring(0, String.valueOf(tempCusTypes).lastIndexOf(","));
        sql = "select count(*) as counts , customertype as enumConvert  from  customer_customerbaseinfo " +
                "where customertype in (" + cusType + ")  and workprofession in("+works+") group by customertype  order by customertype asc  ";
        List<Map<String, String>> cusTypeMapList = new ArrayList<>();
        cusEmailBOS = customerBaseInfoAPI.findBySql(sql, CusEmailBO.class, fields);
        cusTypeMapList = sqlQueryInt("CustomerType", cusTypeList, cusEmailBOS, cusTypeMapList);

        //处理客户状态汇总
        StringBuffer tempCusStatus = new StringBuffer("");
        for (Integer str : cusStatusList) {
            tempCusStatus.append(str + ",");
        }
        String cusStatus = String.valueOf(tempCusStatus).substring(0, String.valueOf(tempCusStatus).lastIndexOf(","));
        sql = "select count(*) as counts , customerstatus as enumConvert  from  customer_customerbaseinfo " +
                "where customerstatus in (" + cusStatus + ") and workprofession in("+works+") group by  customerstatus order by customerstatus asc  ";
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
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (cusEmailBOS.size() < obj.size()) {
//                obj:广州，湖南,北京    cus:湖南
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
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
                for (String dif : diffrent) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", dif);
                    areaMap.put("count", String.valueOf(0));
                    mapList.add(areaMap);
                }


            }
        } else {
            for (String dif : obj) {
                Map<String, String> areaMap = new HashMap<>();
                areaMap.put("remark", dif);
                areaMap.put("count", String.valueOf(0));
                mapList.add(areaMap);
            }
        }

        Collections.sort(mapList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                //进行判断
                return ((String) o1.get("remark")).compareTo((String) o2.get("remark"));
            }
        });
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
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
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
                for (CusEmailBO cbo : cusEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("CustomerStatus")) {
                        areaMap.put("remark", CustomerStatus.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("CustomerType")) {
                        areaMap.put("remark", CustomerType.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
                for (Integer dif : diffrent) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("CustomerStatus")) {
                        areaMap.put("remark", CustomerStatus.getStrConvert(dif));
                    } else if (enumStr.equals("CustomerType")) {
                        areaMap.put("remark", CustomerType.getStrConvert(dif));
                    }
                    areaMap.put("count", 0 + "");
                    mapList.add(areaMap);
                }


            }
        } else {
            for (Integer dif : obj) {
                Map<String, String> areaMap = new HashMap<>();
                if (enumStr.equals("CustomerStatus")) {
                    areaMap.put("remark", CustomerStatus.getStrConvert(dif));
                } else if (enumStr.equals("CustomerType")) {
                    areaMap.put("remark", CustomerType.getStrConvert(dif));
                }
                areaMap.put("count", 0 + "");
                mapList.add(areaMap);
            }
        }

        Collections.sort(mapList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                //进行判断
                return ((String) o1.get("remark")).compareTo((String) o2.get("remark"));
            }
        });
        return mapList;
    }



    @Override
    public void checkSendEmail() throws SerException {
        List<CusEmail> allEmails = new ArrayList<>();
        List<CusEmail> baseEmails = new ArrayList<>();

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        CusEmailDTO dto = new CusEmailDTO();
        dto.getConditions().add(Restrict.eq("status",Status.THAW));
        List<CusEmail> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (CusEmail str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendNum();
            //发送单位
            CustomerSendUnit collectSendUnit = str.getCustomerSendUnit();
            //发送对象;隔开
            String sendObject = str.getSendObject();

            Long mis = nowTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - lastTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Double temp_sendNum = 0d;
            Boolean flag = false;
            switch (collectSendUnit) {
                case MINUTE:
                    //毫秒数
                    temp_sendNum = sendNum * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case HOURS:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3*sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3*sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
            }

                baseEmails.add(str);
                allEmails.add(str);



        }

        //调用发邮件
        allEmails = sendObject(baseEmails);

        //修改上次发送时间
        super.update(allEmails);

    }

    private List<CusEmail> sendObject(List<CusEmail> baseEmails ) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<CusEmail> allEmails = new ArrayList<>();
        //客户基本信息汇总
        if (baseEmails != null && baseEmails.size() > 0) {
            for (CusEmail sign : baseEmails) {
                String[] condis = sign.getWork().split(";");
                List<CusEmailBO> signBOList = cusEmailSer.collectCusEmail(condis);
                //拼表格
                String content = htmlSign(signBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送客户基本信息汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);
                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(sign.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                sign.setLastSendTime(LocalDateTime.now());
                sign.setModifyTime(LocalDateTime.now());
                allEmails.add(sign);
            }
        }

        return allEmails;

    }


    private String htmlSign(List<CusEmailBO> signBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (signBOList != null && signBOList.size() > 0) {
            sb = new StringBuffer("<h4>客户基本信息汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CusEmailBO title = signBOList.get(signBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>行业</td>");
            for (Map<String, String> map : title.getAreaMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getCusStatusMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getCusTypeMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getLevelMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            sb.append("<tr>");

            //拼body部分
            for (CusEmailBO bo : signBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getWork() + "</td>");
                for (Map<String, String> map : bo.getAreaMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getCusStatusMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getCusTypeMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getLevelMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


}