package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.CollectEmailBO;
import com.bjike.goddess.businessproject.dto.CollectEmailDTO;
import com.bjike.goddess.businessproject.entity.CollectEmail;
import com.bjike.goddess.businessproject.enums.*;
import com.bjike.goddess.businessproject.to.CollectEmailTO;
import com.bjike.goddess.businessproject.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.entity.Message;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 商务项目合同邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 商务项目合同邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SiginManageSer siginManageAPI;
    @Autowired
    private BaseInfoManageSer baseInfoManageAPI;
    @Autowired
    private DispatchSheetSer dispatchSheetAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private CollectEmailSer collectEmailSer;

    @Autowired
    private MessageAPI messageAPI;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if (flagSee || flagAdd) {
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
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case AUDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case CONGEL:
                flag = guideAddIdentity();
                break;
            case THAW:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case IMPORT:
                flag = guideAddIdentity();
                break;
            case EXPORT:
                flag = guideAddIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }


    @Override
    public Long counts(CollectEmailDTO collectEmailDTO) throws SerException {
        Long count = super.count(collectEmailDTO);
        return count;
    }

    @Override
    public CollectEmailBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        CollectEmail selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability, CollectEmailBO.class);

    }

    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        checkSeeIdentity();

        collectEmailDTO.getSorts().add("createTime=desc");
        List<CollectEmail> list = super.findByPage(collectEmailDTO);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        if (null == collectEmail.getCollectSendUnit()) {
            throw new SerException("发送单位不能为空");
        }
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            collectEmail.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());


        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        String useToken = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(useToken);

        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail temp = super.findById(collectEmailTO.getId());
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);

        BeanUtils.copyProperties(collectEmail, temp, "id", "createTime", "createPersion", "lastSendTime", "status");
        temp.setModifyTime(LocalDateTime.now());
        temp.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        temp.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置汇总条件
        StringBuffer condiSb = new StringBuffer("");
        String[] condis = collectEmailTO.getCondis();
        if (condis != null && condis.length >= 0) {
            for (String condiStr : condis) {
                condiSb.append(condiStr + ";");
            }
            temp.setCondi(StringUtils.substringBeforeLast(condiSb.toString(), ";"));
        } else {
            throw new SerException("发送条件不能为空");
        }


        //设置发送对象
        temp.setSendObject(String.valueOf(emails));

        super.update(temp);
        return BeanTransform.copyProperties(temp, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        checkAddIdentity();

        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        checkAddIdentity();

        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    @Override
    public List<CollectEmailBO> collectCollectEmail(String[] area) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<String> areas = Arrays.asList(area);

//        if (areas == null || areas.size() == 0) {
//            areas = siginManageAPI.listArea();
//        }
        //先查有几个地区
//        List<String> areas = siginManageAPI.listArea();

        //再获取业务类型
        List<Integer> busType = Arrays.asList(BusinessType.MOBILECOMMUNICATION.getCode(), BusinessType.SOFTDEVELOP.getCode()
                , BusinessType.INTELLIGENCESYSTEM.getCode(), BusinessType.ADVERT.getCode());
        StringBuffer busTypeStr = new StringBuffer("");
        for (Integer type : busType) {
            busTypeStr.append(type + ",");
        }
        //再获取合作方式
        List<Integer> cooperStatus = Arrays.asList(BusinessCooperate.RENTCONTRACT.getCode(),
                BusinessCooperate.CHARCONTRACT.getCode(), BusinessCooperate.DISTRIBUTECONTRACT.getCode(), BusinessCooperate.SALECONTRACT.getCode());
        StringBuffer cooperStr = new StringBuffer("");
        for (Integer type : cooperStatus) {
            cooperStr.append(type + ",");
        }
        //再获合同属性
        List<Integer> distribute = Arrays.asList(ContractProperty.FRAMECONTRACT.getCode(), ContractProperty.SINGLECONTRACT.getCode());
        StringBuffer distributeStr = new StringBuffer("");
        for (Integer type : distribute) {
            distributeStr.append(type + ",");
        }
        //立项情况
        List<String> makeProjects = Arrays.asList("已立项", "未立项");
        StringBuffer makeProjectsStr = new StringBuffer("");
        for (String type : makeProjects) {
            makeProjectsStr.append("'" + type + "'" + ",");
        }
        //签订合同情况
        List<String> signConditions = Arrays.asList("已签订", "未签订");
        StringBuffer signStr = new StringBuffer("");
        for (String type : signConditions) {
            signStr.append("'" + type + "'" + ",");
        }

        for (String areaStr : areas) {

            String[] fields = new String[]{"counts", "type", "enumConvert"};

            /**
             * 再获取业务类型
             */
            String sql = "select count(*) as counts , area as type ,businessType as enumConvert  from  businessproject_siginmanage " +
                    "where businessType in (" + StringUtils.substringBeforeLast(busTypeStr + "", ",") + ") and area = '" + areaStr + "' group by area , businessType order by businessType asc  ";
            List<Map<String, String>> busTypeMapList = new ArrayList<>();
            List<CollectEmailBO> collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

            /**
             * 再获取合作方式
             */
            sql = "select count(*) as counts , area as type ,businessCooperate as enumConvert  from  businessproject_siginmanage " +
                    "where businessCooperate in (" + StringUtils.substringBeforeLast(cooperStr + "", ",") + ") and area = '" + areaStr + "' group by area , businessCooperate order by businessCooperate asc  ";
            List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);

            /**
             * 再获合同属性
             */
            sql = "select count(*) as counts , area as type ,contractProperty as enumConvert  from  businessproject_siginmanage " +
                    "where contractProperty in (" + StringUtils.substringBeforeLast(distributeStr + "", ",") + ") and area = '" + areaStr + "' group by area , contractProperty order by contractProperty asc  ";
            List<Map<String, String>> propertyMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

            fields = new String[]{"counts", "type", "remark"};
            /**
             * 立项情况
             */
            sql = "select count(*) as counts , area as type ,makeProject as remark  from  businessproject_siginmanage " +
                    "where makeProject in (" + StringUtils.substringBeforeLast(makeProjectsStr + "", ",") + ") and area = '" + areaStr + "' group by makeProject , area order by makeProject asc  ";
            List<Map<String, String>> makeProjectMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            makeProjectMapList = sqlQueryString(makeProjects, collectEmailBOS, makeProjectMapList);
            /**
             * 签订合同情况
             */
            sql = "select count(*) as counts , area as type ,siginStatus as remark  from  businessproject_siginmanage " +
                    "where siginStatus in (" + StringUtils.substringBeforeLast(signStr + "", ",") + ") and area = '" + areaStr + "' group by siginStatus , area order by siginStatus asc  ";
            List<Map<String, String>> signMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


            CollectEmailBO collectEmailBO = new CollectEmailBO();
            collectEmailBO.setType(areaStr);
            collectEmailBO.setBusTypeMap(busTypeMapList);
            collectEmailBO.setCooperWaysMap(cooperStatusMapList);
            collectEmailBO.setContractPropertyMap(propertyMapList);
            collectEmailBO.setMakeProjectMap(makeProjectMapList);
            collectEmailBO.setSignMap(signMapList);
            collectEmailBOList.add(collectEmailBO);
        }

        //合计计算合同签订与立项每个业务类型等总数
        collectEmailBOList = calcuteSiginCount(areas, busType, cooperStatus, distribute, makeProjects, signConditions, collectEmailBOList);

        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectBaseInfoEmail(String[] firstCompany) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<String> firstCompanys = Arrays.asList(firstCompany);

        //再获取业务类型
        List<Integer> busType = Arrays.asList(BusinessType.MOBILECOMMUNICATION.getCode(), BusinessType.SOFTDEVELOP.getCode()
                , BusinessType.INTELLIGENCESYSTEM.getCode(), BusinessType.ADVERT.getCode());
        StringBuffer busTypeStr = new StringBuffer("");
        for (Integer type : busType) {
            busTypeStr.append(type + ",");
        }
        //再获取合作方式
        List<Integer> cooperStatus = Arrays.asList(BusinessCooperate.RENTCONTRACT.getCode(),
                BusinessCooperate.CHARCONTRACT.getCode(), BusinessCooperate.DISTRIBUTECONTRACT.getCode(), BusinessCooperate.SALECONTRACT.getCode());
        StringBuffer cooperStr = new StringBuffer("");
        for (Integer type : cooperStatus) {
            cooperStr.append(type + ",");
        }
        //再获合同属性
        List<Integer> distribute = Arrays.asList(ContractProperty.FRAMECONTRACT.getCode(), ContractProperty.SINGLECONTRACT.getCode());
        StringBuffer distributeStr = new StringBuffer("");
        for (Integer type : distribute) {
            distributeStr.append(type + ",");
        }

        //合同归档情况
        List<String> signConditions = Arrays.asList("已归档", "未归档");
        StringBuffer signStr = new StringBuffer("");
        for (String type : signConditions) {
            signStr.append("'" + type + "',");
        }

        for (String firstCompanyStr : firstCompanys) {

            String[] fields = new String[]{"counts", "type", "enumConvert"};

            /**
             * 再获取业务类型
             */
            String sql = "select count(*) as counts , firstCompany as type ,businessType as enumConvert  from  businessproject_baseinfomanage " +
                    "where businessType in (" + StringUtils.substringBeforeLast(busTypeStr + "", ",") + ") and firstCompany = '" + firstCompanyStr + "' group by firstCompany , businessType order by businessType asc  ";
            List<Map<String, String>> busTypeMapList = new ArrayList<>();
            List<CollectEmailBO> collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

            /**
             * 再获取合作方式
             */
            sql = "select count(*) as counts , firstCompany as type ,businessCooperate as enumConvert  from  businessproject_baseinfomanage " +
                    "where businessCooperate in (" + StringUtils.substringBeforeLast(cooperStr + "", ",") + ") and firstCompany = '" + firstCompanyStr + "' group by firstCompany , businessCooperate order by businessCooperate asc  ";
            List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);

            /**
             * 再获合同属性
             */
            sql = "select count(*) as counts , firstCompany as type ,contractProperty as enumConvert  from  businessproject_baseinfomanage " +
                    "where contractProperty in (" + StringUtils.substringBeforeLast(distributeStr + "", ",") + ") and firstCompany = '" + firstCompanyStr + "' group by firstCompany , contractProperty order by contractProperty asc  ";
            List<Map<String, String>> propertyMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

            fields = new String[]{"counts", "type", "remark"};
            /**
             * 合同归档情况
             */
            sql = "select count(*) as counts , firstCompany as type ,fileCondition as remark  from  businessproject_baseinfomanage " +
                    "where fileCondition in (" + StringUtils.substringBeforeLast(signStr + "", ",") + ") and firstCompany = '" + firstCompanyStr + "' group by fileCondition , firstCompany order by fileCondition asc  ";
            List<Map<String, String>> signMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);

            /**
             * 汇总金额
             */
            fields = new String[]{"money"};
            sql = "select sum(money) as money  from  businessproject_baseinfomanage " +
                    "where  firstCompany = '" + firstCompanyStr + "' group by  firstCompany   ";
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            Double money = collectEmailBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(CollectEmailBO::getMoney).sum();


            CollectEmailBO collectEmailBO = new CollectEmailBO();
            collectEmailBO.setType(firstCompanyStr);
            collectEmailBO.setBusTypeMap(busTypeMapList);
            collectEmailBO.setCooperWaysMap(cooperStatusMapList);
            collectEmailBO.setContractPropertyMap(propertyMapList);
            collectEmailBO.setSignMap(signMapList);
            collectEmailBO.setMoney(money);
            collectEmailBOList.add(collectEmailBO);
        }

        //合计计算合同基本信息每个业务类型等总数
        collectEmailBOList = calcuteBaseInfoCount(firstCompanys, busType, cooperStatus, distribute, signConditions, collectEmailBOList);

        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectDispatchEmail(String[] area) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();

        String[] fields = new String[]{"counts", "type", "remark"};
        for (String areaStr : area) {
            String sql = "select count(*) as counts  , area as type , 1 as remark from  businessproject_dispatchsheet " +
                    "where  area = '" + areaStr + "' group by type   ";

            List<CollectEmailBO> collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countDispatchName = 0;
            if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
                countDispatchName = collectEmailBOS.get(0).getCounts();
            }

            //已完工
            sql = "select count(*) as counts , area as type , completeProject as remark from  businessproject_dispatchsheet " +
                    "where  area = '" + areaStr + "' and completeProject ='已完工'   ";
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countComplete = 0;
            if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
                countComplete = collectEmailBOS.get(0).getCounts();
            }

            //未完工
            sql = "select count(*) as counts , area as type , completeProject as remark from  businessproject_dispatchsheet " +
                    "where  area = '" + areaStr + "' and completeProject ='未完工'   ";
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countNotComplete = 0;
            if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
                countNotComplete = collectEmailBOS.get(0).getCounts();
            }

            String[] fieldMoney = new String[]{"money", "type"};
            //金额
            sql = "select sum(money) as money , area as type  from  businessproject_dispatchsheet " +
                    " where  area = '" + areaStr + "'";
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fieldMoney);
            Double money = 0d;
            if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
                money = collectEmailBOS.get(0).getMoney();
            }

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setType(areaStr);
            cbo.setDispatchProjectCount(countDispatchName);
            cbo.setComplete(countComplete);
            cbo.setNotComplete(countNotComplete);
            cbo.setMoney(null == money ? 0d : money);

            collectEmailBOList.add(cbo);
        }

        //计算合计
        int sumProjectAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getDispatchProjectCount).sum();
        int sumCompleteAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getComplete).sum();
        int sumNotCompleteAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getNotComplete).sum();
        Double sumMoneyAll = collectEmailBOList.stream().mapToDouble(CollectEmailBO::getMoney).sum();

        CollectEmailBO cbo = new CollectEmailBO();
        cbo.setType("合计");
        cbo.setDispatchProjectCount(sumProjectAll);
        cbo.setComplete(sumCompleteAll);
        cbo.setNotComplete(sumNotCompleteAll);
        cbo.setMoney(sumMoneyAll);

        collectEmailBOList.add(cbo);

        return collectEmailBOList;
    }

    /**
     * 合计计算合同基本信息每个业务类型等总数
     *
     * @param busType
     * @param cooperStatus
     * @param distribute
     * @param signConditions
     * @param collectEmailBOList
     * @return
     * @throws SerException
     */
    public List<CollectEmailBO> calcuteBaseInfoCount(List<String> firstCompanys, List<Integer> busType, List<Integer> cooperStatus,
                                                     List<Integer> distribute, List<String> signConditions,
                                                     List<CollectEmailBO> collectEmailBOList) throws SerException {
        StringBuffer companyStr = new StringBuffer("");
        for (String type : firstCompanys) {
            companyStr.append("'" + type + "',");
        }
        StringBuffer busTypeStr = new StringBuffer("");
        for (Integer type : busType) {
            busTypeStr.append(type + ",");
        }
        StringBuffer cooperStr = new StringBuffer("");
        for (Integer type : cooperStatus) {
            cooperStr.append(type + ",");
        }
        StringBuffer distributeStr = new StringBuffer("");
        for (Integer type : distribute) {
            distributeStr.append(type + ",");
        }
        StringBuffer signStr = new StringBuffer("");
        for (String type : signConditions) {
            signStr.append("'" + type + "',");
        }

        String[] fields = new String[]{"counts", "enumConvert"};

        /**
         * 再获取业务类型
         */
        String sql = "select count(*) as counts , businessType as enumConvert  from  businessproject_baseinfomanage " +
                "where businessType in (" + StringUtils.substringBeforeLast(busTypeStr + "", ",") + ") and firstCompany in(" + StringUtils.substringBeforeLast(companyStr + "", ",") + ") group by businessType order by businessType asc  ";
        List<Map<String, String>> busTypeMapList = new ArrayList<>();
        List<CollectEmailBO> collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

        /**
         * 再获取合作方式
         */
        sql = "select count(*) as counts , businessCooperate as enumConvert  from  businessproject_baseinfomanage " +
                "where businessCooperate in (" + StringUtils.substringBeforeLast(cooperStr + "", ",") + ") and firstCompany in(" + StringUtils.substringBeforeLast(companyStr + "", ",") + ") group by  businessCooperate order by businessCooperate asc  ";
        List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);


        /**
         * 再获合同属性
         */
        sql = "select count(*) as counts  ,contractProperty as enumConvert  from  businessproject_baseinfomanage " +
                "where contractProperty in (" + StringUtils.substringBeforeLast(distributeStr + "", ",") + ") and firstCompany in(" + StringUtils.substringBeforeLast(companyStr + "", ",") + ") group by  contractProperty order by contractProperty asc  ";
        List<Map<String, String>> propertyMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

        fields = new String[]{"counts", "remark"};
        /**
         * 合同归档情况
         */
        sql = "select count(*) as counts , fileCondition as remark  from  businessproject_baseinfomanage " +
                "where fileCondition in (" + StringUtils.substringBeforeLast(signStr + "", ",") + ") and firstCompany in(" + StringUtils.substringBeforeLast(companyStr + "", ",") + ") group by fileCondition  order by fileCondition asc  ";
        List<Map<String, String>> signMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);

        /**
         * 汇总金额
         */
        fields = new String[]{"money"};
        sql = "select sum(money) as money  from  businessproject_baseinfomanage " +
                "where  firstCompany in(" + StringUtils.substringBeforeLast(companyStr + "", ",") + ")     ";
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        Double money = collectEmailBOS.stream().filter(str -> null != str.getMoney()).mapToDouble(CollectEmailBO::getMoney).sum();


        CollectEmailBO collectEmailBO = new CollectEmailBO();
        collectEmailBO.setType("合计");
        collectEmailBO.setBusTypeMap(busTypeMapList);
        collectEmailBO.setCooperWaysMap(cooperStatusMapList);
        collectEmailBO.setContractPropertyMap(propertyMapList);
        collectEmailBO.setSignMap(signMapList);
        collectEmailBO.setMoney(money);
        collectEmailBOList.add(collectEmailBO);

        return collectEmailBOList;
    }


    /**
     * 合计计算合同签订与立项每个业务类型等总数
     *
     * @param busType
     * @param cooperStatus
     * @param distribute
     * @param makeProjects
     * @param signConditions
     * @param collectEmailBOList
     * @return
     * @throws SerException
     */
    public List<CollectEmailBO> calcuteSiginCount(List<String> areas, List<Integer> busType, List<Integer> cooperStatus,
                                                  List<Integer> distribute, List<String> makeProjects,
                                                  List<String> signConditions,
                                                  List<CollectEmailBO> collectEmailBOList) throws SerException {
        StringBuffer areaStr = new StringBuffer("");
        for (String type : areas) {
            areaStr.append("'" + type + "',");
        }
        StringBuffer busTypeStr = new StringBuffer("");
        for (Integer type : busType) {
            busTypeStr.append(type + ",");
        }
        StringBuffer cooperStr = new StringBuffer("");
        for (Integer type : cooperStatus) {
            cooperStr.append(type + ",");
        }
        StringBuffer distributeStr = new StringBuffer("");
        for (Integer type : distribute) {
            distributeStr.append(type + ",");
        }
        StringBuffer makeProjectsStr = new StringBuffer("");
        for (String type : makeProjects) {
            makeProjectsStr.append("'" + type + "',");
        }
        StringBuffer signStr = new StringBuffer("");
        for (String type : signConditions) {
            signStr.append("'" + type + "',");
        }
        String[] fields = new String[]{"counts", "enumConvert"};

        /**
         * 再获取业务类型
         */
        String sql = "select count(*) as counts , businessType as enumConvert  from  businessproject_siginmanage " +
                "where businessType in (" + StringUtils.substringBeforeLast(busTypeStr + "", ",") + ") and area in(" + StringUtils.substringBeforeLast(areaStr + "", ",") + ") group by  businessType order by businessType asc  ";
        List<Map<String, String>> busTypeMapList = new ArrayList<>();
        List<CollectEmailBO> collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

        /**
         * 再获取合作方式
         */
        sql = "select count(*) as counts , businessCooperate as enumConvert  from  businessproject_siginmanage " +
                "where businessCooperate in (" + StringUtils.substringBeforeLast(cooperStr + "", ",") + ") and area in(" + StringUtils.substringBeforeLast(areaStr + "", ",") + ") group by  businessCooperate order by businessCooperate asc  ";
        List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);


        /**
         * 再获合同属性
         */
        sql = "select count(*) as counts  ,contractProperty as enumConvert  from  businessproject_siginmanage " +
                "where contractProperty in (" + StringUtils.substringBeforeLast(distributeStr + "", ",") + ") and area in(" + StringUtils.substringBeforeLast(areaStr + "", ",") + ") group by  contractProperty order by contractProperty asc  ";
        List<Map<String, String>> propertyMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

        fields = new String[]{"counts", "remark"};
        /**
         * 立项情况
         */
        sql = "select count(*) as counts , makeProject as remark  from  businessproject_siginmanage " +
                "where makeProject in (" + StringUtils.substringBeforeLast(makeProjectsStr + "", ",") + ") and area in(" + StringUtils.substringBeforeLast(areaStr + "", ",") + ") group by makeProject  order by makeProject asc  ";
        List<Map<String, String>> makeProjectMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        makeProjectMapList = sqlQueryString(makeProjects, collectEmailBOS, makeProjectMapList);
        /**
         * 签订合同情况
         */
        sql = "select count(*) as counts , siginStatus as remark  from  businessproject_siginmanage " +
                "where siginStatus in (" + StringUtils.substringBeforeLast(signStr + "", ",") + ") and area in(" + StringUtils.substringBeforeLast(areaStr + "", ",") + ") group by siginStatus  order by siginStatus asc  ";
        List<Map<String, String>> signMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


        CollectEmailBO collectEmailBO = new CollectEmailBO();
        collectEmailBO.setType("合计");
        collectEmailBO.setBusTypeMap(busTypeMapList);
        collectEmailBO.setCooperWaysMap(cooperStatusMapList);
        collectEmailBO.setContractPropertyMap(propertyMapList);
        collectEmailBO.setMakeProjectMap(makeProjectMapList);
        collectEmailBO.setSignMap(signMapList);
        collectEmailBOList.add(collectEmailBO);

        return collectEmailBOList;
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
    public List<Map<String, String>> sqlQueryString(List<String> obj, List<CollectEmailBO> collectEmailBOS, List<Map<String, String>> mapList) throws SerException {
        if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
            if (obj.size() == collectEmailBOS.size()) {
                for (CollectEmailBO cbo : collectEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CollectEmailBO cb : collectEmailBOS) {
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
                for (CollectEmailBO cbo : collectEmailBOS) {
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
    public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, List<CollectEmailBO> collectEmailBOS, List<Map<String, String>> mapList) throws SerException {

        if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
            if (obj.size() == collectEmailBOS.size()) {
                for (CollectEmailBO cbo : collectEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("BusinessType")) {
                        areaMap.put("remark", BusinessType.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("BusinessCooperate")) {
                        areaMap.put("remark", BusinessCooperate.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ContractProperty")) {
                        areaMap.put("remark", ContractProperty.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectEmailBOS.size() < obj.size()) {
                List<Integer> cbStr = new ArrayList<>();
                for (CollectEmailBO cb : collectEmailBOS) {
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
                for (CollectEmailBO cbo : collectEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("BusinessType")) {
                        areaMap.put("remark", BusinessType.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("BusinessCooperate")) {
                        areaMap.put("remark", BusinessCooperate.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ContractProperty")) {
                        areaMap.put("remark", ContractProperty.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts() == null ? 0 : cbo.getCounts()));
                    mapList.add(areaMap);
                }
                for (Integer dif : diffrent) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("BusinessType")) {
                        areaMap.put("remark", BusinessType.getStrConvert(dif));
                    } else if (enumStr.equals("BusinessCooperate")) {
                        areaMap.put("remark", BusinessCooperate.getStrConvert(dif));
                    } else if (enumStr.equals("ContractProperty")) {
                        areaMap.put("remark", ContractProperty.getStrConvert(dif));
                    }
                    areaMap.put("count", 0 + "");
                    mapList.add(areaMap);
                }

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
        List<CollectEmail> allEmails = new ArrayList<>();
        List<CollectEmail> signEmails = new ArrayList<>();
        List<CollectEmail> dispatchEmails = new ArrayList<>();
        List<CollectEmail> baseInfoEmails = new ArrayList<>();

        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        CollectEmailDTO dto = new CollectEmailDTO();
        List<CollectEmail> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (CollectEmail str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastSendTime();
            //发送间隔
            Double sendNum = str.getSendNum();
            //发送单位
            CollectSendUnit collectSendUnit = str.getCollectSendUnit();
            //发送类型
            String type = str.getType();
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
                    if (nowTime.minusMonths(1).isEqual(lastTime) || nowTime.minusMonths(1).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3).isEqual(lastTime) || nowTime.minusMonths(3).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(1).isEqual(lastTime) || nowTime.minusYears(1).isAfter(lastTime)) {
                        flag = true;
                    }
                    break;
            }

            if (flag && type.equals("合同签订与立项汇总")) {
                signEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals("派工单信息汇总")) {
                dispatchEmails.add(str);
                allEmails.add(str);
            } else if (flag && type.equals("项目合同基本信息汇总")) {
                baseInfoEmails.add(str);
                allEmails.add(str);
            }


        }

        //调用发邮件
        allEmails = sendObject(signEmails, dispatchEmails, baseInfoEmails);

        //修改上次发送时间
        super.update(allEmails);

    }

    private String htmlSign(List<CollectEmailBO> signBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (signBOList != null && signBOList.size() > 0) {
            sb = new StringBuffer("<h4>商务合同项目签订与立项汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = signBOList.get(signBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>地区</td>");
            for (Map<String, String> map : title.getBusTypeMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getCooperWaysMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getContractPropertyMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getMakeProjectMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            for (Map<String, String> map : title.getSignMap()) {
                sb.append("<td>" + map.get("remark") + "</td>");
            }
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : signBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                for (Map<String, String> map : bo.getBusTypeMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getCooperWaysMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getContractPropertyMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getMakeProjectMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                for (Map<String, String> map : bo.getSignMap()) {
                    sb.append("<td>" + map.get("count") + "</td>");
                }
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private String htmlBaseInfo(List<CollectEmailBO> baseinfoBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (baseinfoBOList != null && baseinfoBOList.size() > 0) {
            sb = new StringBuffer("<h4>商务合同基本信息汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = baseinfoBOList.get(baseinfoBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>甲方公司名称</td>");
            if (title.getBusTypeMap() != null && title.getBusTypeMap().size() > 0) {
                for (Map<String, String> map : title.getBusTypeMap()) {
                    sb.append("<td>" + map.get("remark") + "</td>");
                }
            }
            if (title.getCooperWaysMap() != null && title.getCooperWaysMap().size() > 0) {
                for (Map<String, String> map : title.getCooperWaysMap()) {
                    sb.append("<td>" + map.get("remark") + "</td>");
                }
            }
            if (title.getContractPropertyMap() != null && title.getContractPropertyMap().size() > 0) {
                for (Map<String, String> map : title.getContractPropertyMap()) {
                    sb.append("<td>" + map.get("remark") + "</td>");
                }
            }
            if (title.getSignMap() != null && title.getSignMap().size() > 0) {
                for (Map<String, String> map : title.getSignMap()) {
                    sb.append("<td>" + map.get("remark") + "</td>");
                }
            }
            sb.append( title.getMoney() );
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : baseinfoBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                if (bo.getBusTypeMap() != null && bo.getBusTypeMap().size() > 0) {
                    for (Map<String, String> map : bo.getBusTypeMap()) {
                        sb.append("<td>" + map.get("count") + "</td>");
                    }
                }
                if (bo.getCooperWaysMap() != null && bo.getCooperWaysMap().size() > 0) {
                    for (Map<String, String> map : bo.getCooperWaysMap()) {
                        sb.append("<td>" + map.get("count") + "</td>");
                    }
                }
                if (bo.getContractPropertyMap() != null && bo.getContractPropertyMap().size() > 0) {
                    for (Map<String, String> map : bo.getContractPropertyMap()) {
                        sb.append("<td>" + map.get("count") + "</td>");
                    }
                }
                if (bo.getSignMap() != null && bo.getSignMap().size() > 0) {
                    for (Map<String, String> map : bo.getSignMap()) {
                        sb.append("<td>" + map.get("count") + "</td>");
                    }
                }
                sb.append( bo.getMoney() );
                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private String htmlDispatch(List<CollectEmailBO> dispatchEmails) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (dispatchEmails != null && dispatchEmails.size() > 0) {
            sb = new StringBuffer("<h4>商务合同派工单信息合同汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
            sb.append("<tr>");
            sb.append("<td>地区</td>");
            sb.append("<td>派工单名称</td>");
            sb.append("<td>派工单金额(元)</td>");
            sb.append("<td>已完工</td>");
            sb.append("<td>未完工</td>");
            sb.append("<tr>");

            //拼body部分
            for (CollectEmailBO bo : dispatchEmails) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getType() + "</td>");
                sb.append("<td>" + bo.getDispatchProjectCount() + "</td>");
                sb.append("<td>" + bo.getMoney() + "</td>");
                sb.append("<td>" + bo.getComplete() + "</td>");
                sb.append("<td>" + bo.getNotComplete() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<CollectEmail> sendObject(List<CollectEmail> signEmails, List<CollectEmail> dispatchEmails, List<CollectEmail> baseInfoEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<CollectEmail> allEmails = new ArrayList<>();
        //签订与立项汇总
        //基本信息汇总
        //派工单汇总
        if (signEmails != null && signEmails.size() > 0) {
            for (CollectEmail sign : signEmails) {
                String[] condis = sign.getCondi().split(";");
                List<CollectEmailBO> signBOList = collectEmailSer.collectCollectEmail(condis);
                //拼表格
                String content = htmlSign(signBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送商务合同签订与立项汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);

                messageTO.setReceivers(sign.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                sign.setLastSendTime(LocalDateTime.now());
                sign.setModifyTime(LocalDateTime.now());
                allEmails.add(sign);
            }
        }

        RpcTransmit.transmitUserToken( userToken );
        if (baseInfoEmails != null && baseInfoEmails.size() > 0) {
            for (CollectEmail baseinfo : baseInfoEmails) {
                String[] condis = baseinfo.getCondi().split(";");
                List<CollectEmailBO> baseinfoBOList = collectEmailSer.collectBaseInfoEmail(condis);
                //拼表格
                String content = htmlBaseInfo(baseinfoBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送商务合同基本信息汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);

                messageTO.setReceivers(baseinfo.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                baseinfo.setLastSendTime(LocalDateTime.now());
                baseinfo.setModifyTime(LocalDateTime.now());
                allEmails.add(baseinfo);
            }
        }
        RpcTransmit.transmitUserToken( userToken );
        if (dispatchEmails != null && dispatchEmails.size() > 0) {
            for (CollectEmail dispa : dispatchEmails) {
                String[] condis = dispa.getCondi().split(";");
                List<CollectEmailBO> dispatchBOList = collectEmailSer.collectDispatchEmail(condis);
                //拼表格
                String content = htmlDispatch(dispatchBOList);
                MessageTO messageTO = new MessageTO();
                messageTO.setContent( content );
                messageTO.setTitle("定时发送商务合同派工单信息汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType( SendType.EMAIL);
                messageTO.setRangeType( RangeType.SPECIFIED);

                messageTO.setReceivers(dispa.getSendObject().split(";") );
                messageAPI.send(  messageTO );

                dispa.setLastSendTime(LocalDateTime.now());
                dispa.setModifyTime(LocalDateTime.now());
                allEmails.add(dispa);
            }
        }
        return allEmails;

    }

}