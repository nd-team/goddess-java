package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.bo.ServeSummaryBO;
import com.bjike.goddess.marketactivitymanage.dto.CustomerInfoDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeApplyDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeSummaryDTO;
import com.bjike.goddess.marketactivitymanage.entity.CustomerInfo;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeApply;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeSummary;
import com.bjike.goddess.marketactivitymanage.excel.SonPermissionObject;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import com.bjike.goddess.marketactivitymanage.type.CycleType;
import com.bjike.goddess.marketactivitymanage.type.GuideAddrStatus;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 市场招待汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-21 02:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketactivitymanageSerCache")
@Service
public class MarketServeSummarySerImpl extends ServiceImpl<MarketServeSummary, MarketServeSummaryDTO> implements MarketServeSummarySer {

    @Autowired
    private MarketServeApplySer marketServeApplySer;
    @Autowired
    private MarketServeSummarySer marketServeSummarySer;

    @Autowired
    private MarketServeRecordSer marketServeRecordSer;

    @Autowired
    private CustomerInfoSer customerInfoSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private MessageAPI messageAPI;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //商务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对审核权限（模块级别）
     */
    private Boolean guideAuditMIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对审核权限（层次级别）
     */
    private Boolean guideAuditAIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航检查权限
     *
     * @throws SerException
     */
    private Boolean guildPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag =  cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSummSign = guildPermission();
        RpcTransmit.transmitUserToken(userToken);

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("marketservesummary");
        obj.setDescribesion("市场活动招待记录汇总及发送邮件");
        if (flagSummSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAppMulM = marketServeApplySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("marketserveapply");
        obj.setDescribesion("市场招待申请记录");
        if (flagAppMulM) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagRecMulM = marketServeRecordSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("marketserverecord");
        obj.setDescribesion("市场招待记录");
        if (flagRecMulM) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guildPermission();
                break;
            case ADD:
                flag = guildPermission();
                break;
            case EDIT:
                flag = guildPermission();
                break;
            case DELETE:
                flag = guildPermission();
                break;
            case CONGEL:
                flag = guildPermission();
                break;
            case THAW:
                flag = guildPermission();
                break;
            case COLLECT:
                flag = guildPermission();
                break;
            case UPLOAD:
                flag = guildPermission();
                break;
            case DOWNLOAD:
                flag = guildPermission();
                break;
            case IMPORT:
                flag = guildPermission();
                break;
            case EXPORT:
                flag = guildPermission();
                break;
            case SEE:
                flag = guildPermission();
                break;
            case SEEFILE:
                flag = guildPermission();
                break;
            case MONEYAUDIT:
                flag = guideAuditMIdentity();
                break;
            case DECISIONAUDIT:
                flag = guideAuditAIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询市场招待汇总
     *
     * @param dto 市场招待汇总dto
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    public List<MarketServeSummaryBO> list(MarketServeSummaryDTO dto) throws SerException {
        checkPermission();
        List<MarketServeSummary> list = super.findByPage(dto);
        List<MarketServeSummaryBO> listBO = BeanTransform.copyProperties(list, MarketServeSummaryBO.class);
        return listBO;
    }

    /**
     * 保存市场招待汇总
     *
     * @param to 市场招待汇总to
     * @return class MarketServeSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public MarketServeSummaryBO save(MarketServeSummaryTO to) throws SerException {
        checkPermission();
        String sb = getProjectGroup(to);
        String curUsername = userAPI.currentUser().getUsername();
        MarketServeSummary marketServeSummary = BeanTransform.copyProperties(to, MarketServeSummary.class, true, "emails");
        marketServeSummary.setStatus(Status.THAW);
        marketServeSummary.setEmails(StringUtils.join(to.getEmails(), ","));
        marketServeSummary.setCreateUser(curUsername);
        marketServeSummary.setProjectGroups(sb);
        marketServeSummary.setUpdateTime(LocalDateTime.now());
        marketServeSummary.setLastTime(LocalDateTime.now());
        marketServeSummary = super.save(marketServeSummary);
        MarketServeSummaryBO bo = BeanTransform.copyProperties(marketServeSummary, MarketServeSummaryBO.class);
        return bo;
    }

    private String getProjectGroup(MarketServeSummaryTO to) {
        String[] projectGroups = to.getProjects();
        boolean projectGroupNotEmpty = (projectGroups != null) && (projectGroups.length > 0);
        StringBuilder sb = new StringBuilder();
        if (projectGroupNotEmpty) {

            for (int i = 0; i < projectGroups.length; i++) {
                if (i < projectGroups.length - 1) {
                    sb.append(projectGroups[i]).append(",");
                } else {
                    sb.append(projectGroups[i]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 编辑市场招待汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(MarketServeSummaryTO to) throws SerException {
         checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            MarketServeSummary model = super.findById(to.getId());
            if (model != null) {
                updateMarketServeSummary(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新市场招待汇总
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateMarketServeSummary(MarketServeSummaryTO to, MarketServeSummary model) throws SerException {
        String sb = getProjectGroup(to);
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        model.setProjectGroups(sb);
        model.setUpdateTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 解冻市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void thaw(String id) throws SerException {
         checkPermission();
        MarketServeSummary model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setStatus(Status.THAW);
        super.update(model);
    }

    /**
     * 冻结市场招待汇总邮件发送
     *
     * @param id 市场招待汇总邮件发送唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void congeal(String id) throws SerException {
         checkPermission();
        MarketServeSummary model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setStatus(Status.CONGEAL);
        super.update(model);
    }

    /**
     * 市场招待汇总
     *
     * @param type            汇总类型
     * @param projectGroups   部门/项目组
     * @param startTimeString 起始时间
     * @param endTimeString   结束时间
     * @return class MarketServeSummaryVO
     * @throws SerException
     */
    @Override
    public List<ServeSummaryBO> summarize(Boolean type, String[] projectGroups, String startTimeString, String endTimeString) throws SerException {
        //checkPermission();
        if(type==null){
            throw new SerException("汇总类型不能为空");
        }else {
            if (type) {
                return summarizePlan(projectGroups, startTimeString, endTimeString);
            } else {
                return summarizeActual(projectGroups, startTimeString, endTimeString);
            }
        }
    }

    /**
     * 市场招待记录汇总
     *
     * @param projectGroups
     * @param startTimeString
     * @param endTimeString
     * @return
     * @throws SerException
     */
    private List<ServeSummaryBO> summarizeActual(String[] projectGroups, String startTimeString, String endTimeString) throws SerException {
        LocalDateTime[] actualActivityTiming = null;
        if (StringUtils.isNotBlank(startTimeString) && StringUtils.isNotBlank(endTimeString)) {
            LocalDateTime startTime = DateUtil.parseDateTime(startTimeString);//起始时间
            LocalDateTime endTime = DateUtil.parseDateTime(endTimeString);//结束时间
            actualActivityTiming = new LocalDateTime[]{startTime, endTime};
        }else if(StringUtils.isNotBlank(startTimeString) && StringUtils.isBlank(endTimeString)){
            throw new SerException("参数检验不通过");
        }else if(StringUtils.isBlank(startTimeString) && StringUtils.isNotBlank(endTimeString)){
            throw new SerException("参数检验不通过");
        }
        if(projectGroups==null || projectGroups.length<=0){
            throw new SerException("项目组不能为空");
        }
        List<ServeSummaryBO> serveSummaryBOList = new ArrayList<>(0);

            //按照项目组查询
            for (String projectGroup : projectGroups) {
                MarketServeRecordDTO dto = new MarketServeRecordDTO();
                dto.getConditions().add(Restrict.eq("projectName", projectGroup));
                if (actualActivityTiming != null) {
                    dto.getConditions().add(Restrict.between("actualActivityTiming", actualActivityTiming));
                }
                dto.getSorts().add("projectName=asc");
                dto.getSorts().add("actualActivityTiming=asc");
                List<MarketServeRecord> marketServeRecordList = marketServeRecordSer.findByCis(dto);

                if (marketServeRecordList != null && marketServeRecordList.size() > 0) {

                    //遍历集合查询
                    for (MarketServeRecord obj : marketServeRecordList) {
                        String clientName = getClientName(obj);
                        String actualActivityTimingStr = obj.getActualActivityTiming().toString().replace("T", " ").substring(0, 19);
                        String whetherTemporaryServe = getWhetherTemporaryServe(obj);

                        ServeSummaryBO serveSummaryBO = new ServeSummaryBO();
                        serveSummaryBO.setProjectName(obj.getProjectName());//项目名称
                        serveSummaryBO.setArea(obj.getArea());//设置地区
                        serveSummaryBO.setServePrincipal(obj.getServePrincipal());//设置招待负责人
                        serveSummaryBO.setClientName(clientName);//设置客户姓名
                        serveSummaryBO.setActivityType(obj.getActualActivityType());//设置实际活动类型
                        serveSummaryBO.setActivityTiming(actualActivityTimingStr);//设置实际活动时间点
                        serveSummaryBO.setWhetherTemporaryServe(whetherTemporaryServe);//设置是否临时招待
                        serveSummaryBO.setAttendPeopleNo(obj.getPredictAttendNo());//设置参加人数
                        serveSummaryBO.setCharge(obj.getPredictCharge());//设置费用

                        serveSummaryBOList.add(serveSummaryBO);//添加单个项目
                    }

                    String areaCount = countActualAreaNo(actualActivityTiming, projectGroup);//地区计数
                    String servePrincipalCount = countActualServePrincipalNo(actualActivityTiming, projectGroup);//招待负责人计数
                    String clientCount = countActualClientNo(actualActivityTiming, projectGroup);//客户姓名计数
                    String planActivityCount = countActualActivity(actualActivityTiming, projectGroup);//计划活动类型计数
                    String actualActivityTimingCount = countActualTiming(actualActivityTiming, projectGroup);//计算计划活动时间点
                    String whetherTemporaryServeCount = countActualWhetherTemporaryServe(actualActivityTiming, projectGroup);//计算是否临时招待计数
                    Integer predictAttendNoCount = countActualPredictAttendNo(actualActivityTiming, projectGroup);//计算预计参加人数
                    Double predictChargeCount = countActualCharge(actualActivityTiming, projectGroup);//计算预计费用
                    ServeSummaryBO summaryBO = new ServeSummaryBO();
                    summaryBO.setProjectName("合计");
                    summaryBO.setArea(areaCount);//设置地区
                    summaryBO.setServePrincipal(servePrincipalCount);//设置招待负责人
                    summaryBO.setClientName(clientCount);//设置客户姓名
                    summaryBO.setActivityType(planActivityCount);//设置实际活动类型
                    summaryBO.setActivityTiming(actualActivityTimingCount);//设置实际活动时间点
                    summaryBO.setWhetherTemporaryServe(whetherTemporaryServeCount);//设置是否临时招待
                    summaryBO.setAttendPeopleNo(predictAttendNoCount);//设置参加人数
                    summaryBO.setCharge(predictChargeCount);//设置费用

                    serveSummaryBOList.add(summaryBO);//添加合计项
                }
            }


        return serveSummaryBOList;
    }

    /**
     * 计算实际费用的总数
     *
     * @param actualActivityTiming 实际时间段
     * @param projectGroup         项目组
     * @return
     */
    private Double countActualCharge(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Double actualCharge = 0d;
        for (MarketServeRecord record : list) {
            if (record.getPredictCharge() != null) {
                actualCharge += record.getPredictCharge();
            }
        }

        return actualCharge;
    }

    /**
     * 计算预计参加人数
     *
     * @param actualActivityTiming 实际时间段
     * @param projectGroup         项目组
     * @return 预计参加人数
     */
    private Integer countActualPredictAttendNo(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Integer predictAttendTotalNo = 0;
        for (MarketServeRecord record : list) {
            if (record.getPredictAttendNo() != null) {
                predictAttendTotalNo += record.getPredictAttendNo();
            }
        }

        return predictAttendTotalNo;
    }

    /**
     * 计算是否临时招待数目
     *
     * @param actualActivityTiming 实际时间段
     * @param projectGroup         项目组
     * @return 是否临时招待数目
     */
    private String countActualWhetherTemporaryServe(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<Boolean> whetherTemporaryServeSet = new HashSet<>(0);
        for (MarketServeRecord record : list) {
            if (record.getWhetherTemporaryServe() != null) {
                whetherTemporaryServeSet.add(record.getWhetherTemporaryServe());
            }
        }

        return String.valueOf(whetherTemporaryServeSet.size());
    }

    /**
     * 计算实际活动时间点的数目
     *
     * @param actualActivityTiming 时间段
     * @param projectGroup         项目组
     * @return　实际活动时间点的数目
     */
    private String countActualTiming(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<LocalDateTime> actualActivityTimingSet = new HashSet<>(0);
        for (MarketServeRecord record : list) {
            if (record.getPlanActivityTiming() != null) {
                actualActivityTimingSet.add(record.getPlanActivityTiming());
            }
        }
        return String.valueOf(actualActivityTimingSet.size());
    }

    /**
     * 计算实际活动数量
     *
     * @param actualActivityTiming 时间段
     * @param projectGroup         项目组
     * @return 实际活动数量
     */
    private String countActualActivity(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<String> actualActivitySet = new HashSet<>(0);
        for (MarketServeRecord record : list) {
            if (StringUtils.isNotBlank(record.getPlanActivityType())) {
                actualActivitySet.add(record.getPlanActivityType());
            }
        }
        return String.valueOf(actualActivitySet.size());
    }

    /**
     * 计算实际客户数量
     *
     * @param actualActivityTiming 时间段
     * @param projectGroup         项目组
     * @return
     */
    private String countActualClientNo(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<String> clientSet = new HashSet<>(0);
        for (MarketServeRecord apply : list) {
            String marketServeRecordId = apply.getId();
            CustomerInfoDTO dto = new CustomerInfoDTO();
            dto.getConditions().add(Restrict.eq("marketServeId", marketServeRecordId));
            List<CustomerInfo> customerInfoList = customerInfoSer.findByCis(dto);
            StringBuffer clientName = new StringBuffer();
            for (CustomerInfo customerInfo : customerInfoList) {
                if (StringUtils.isNotBlank(customerInfo.getClientName())) {
                    clientName.append(customerInfo.getClientName());
                    clientName.append(",");
                }
            }
            String clientNameString = clientName.toString();
            if(clientNameString.length()>0 && StringUtils.isNotBlank(clientNameString) ){

                clientNameString = clientNameString.substring(0, clientNameString.length() - 1);
            }
            clientSet.add(clientNameString);
        }
        return String.valueOf(clientSet.size());
    }

    /**
     * 计算实际招待负责人的数量
     *
     * @param actualActivityTiming 实际活动时间段
     * @param projectGroup         项目组
     * @return
     */
    private String countActualServePrincipalNo(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<String> servePrincipalSet = new HashSet<>(0);
        for (MarketServeRecord entity : list) {
            if (StringUtils.isNoneBlank(entity.getServePrincipal())) {
                servePrincipalSet.add(entity.getServePrincipal());
            }
        }
        return String.valueOf(servePrincipalSet.size());
    }

    /**
     * 计算实际招待地区数量
     *
     * @param actualActivityTiming
     * @param projectGroup
     * @return
     * @throws SerException
     */
    private String countActualAreaNo(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        List<MarketServeRecord> list = getMarketServeRecords(actualActivityTiming, projectGroup);
        Set<String> areaSet = new HashSet<>(0);
        for (MarketServeRecord entity : list) {
            if (StringUtils.isNotBlank(entity.getArea())) {
                areaSet.add(entity.getArea());
            }
        }
        return String.valueOf(areaSet.size());
    }

    /**
     * 根据项目组获取某一时间段的市场招待记录数量
     *
     * @param actualActivityTiming 实际招待时间段
     * @param projectGroup         项目组
     * @return 市场招待记录
     * @throws SerException
     */
    private List<MarketServeRecord> getMarketServeRecords(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        MarketServeRecordDTO dto = new MarketServeRecordDTO();
        dto.getConditions().add(Restrict.eq("projectName", projectGroup));
        if (actualActivityTiming != null) {
            dto.getConditions().add(Restrict.between("actualActivityTiming", actualActivityTiming));
        }
        dto.getSorts().add("actualActivityTiming=desc");
        return marketServeRecordSer.findByCis(dto);
    }

    /**
     * 判断是否临时招待
     *
     * @param obj
     * @return
     */
    private String getWhetherTemporaryServe(MarketServeRecord obj) {
        Boolean whetherTemporaryServe = obj.getWhetherTemporaryServe();
        if (whetherTemporaryServe != null) {
            if (whetherTemporaryServe == Boolean.TRUE) {
                return "是";
            } else {
                return "否";
            }
        }

        return "否";
    }

    /**
     * 获取对应记录的客户姓名
     *
     * @param obj
     * @return
     */
    private String getClientName(MarketServeRecord obj) throws SerException {
        StringBuffer clientNames = new StringBuffer();
        String marketServeId = obj.getId();
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", marketServeId));
        List<CustomerInfo> boList = customerInfoSer.findByCis(dto);
        for (CustomerInfo info : boList) {
            clientNames.append(info.getClientName()).append(",");
        }
        String clientNameString = clientNames.toString();
        if(clientNameString.length()>0 && StringUtils.isNotBlank(clientNameString )){
            clientNameString = clientNameString.substring(0, clientNameString.length() - 1);
        }

        return clientNameString;
    }

    /**
     * 市场招待记录申请汇总
     *
     * @param projectGroups
     * @param startTimeString
     * @param endTimeString
     * @return
     * @throws SerException
     */
    private List<ServeSummaryBO> summarizePlan(String[] projectGroups, String startTimeString, String endTimeString) throws SerException {
        LocalDateTime[] planActivityTiming = null;
        if (StringUtils.isNotBlank(startTimeString) && StringUtils.isNotBlank(endTimeString)) {
            LocalDateTime startTime = DateUtil.parseDateTime(startTimeString);//起始时间
            LocalDateTime endTime = DateUtil.parseDateTime(endTimeString);//结束时间
            planActivityTiming = new LocalDateTime[]{startTime, endTime};
        }else if(StringUtils.isNotBlank(startTimeString) && StringUtils.isBlank(endTimeString)){
            throw new SerException("参数检验不通过");
        }else if(StringUtils.isBlank(startTimeString) && StringUtils.isNotBlank(endTimeString)){
            throw new SerException("参数检验不通过");
        }
        if(projectGroups==null || projectGroups.length<=0){
            throw new SerException("项目组不能为空");
        }
        List<ServeSummaryBO> serveSummaryBOList = new ArrayList<>(0);

        //按照项目组查询
        for (String projectGroup : projectGroups) {
            MarketServeApplyDTO dto = new MarketServeApplyDTO();
            dto.getConditions().add(Restrict.eq("projectName", projectGroup));
            if (planActivityTiming != null) {
                dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming)); //时间范围查询
            }
            dto.getSorts().add("projectName=asc");
            dto.getSorts().add("planActivityTiming=asc");
            List<MarketServeApply> marketServeApplyList = marketServeApplySer.findByCis(dto, true);

            if (marketServeApplyList != null && marketServeApplyList.size() > 0) {


                //遍历查询集合
                for (MarketServeApply obj : marketServeApplyList) {
                    String clientName = getClientName(obj);
                    String planActivityTimingStr = obj.getPlanActivityTiming().toString().replace("T", " ").substring(0, 19);
                    String whetherTemporaryServe = getWhetherTemporaryServe(obj);

                    ServeSummaryBO serveSummaryBO = new ServeSummaryBO();
                    serveSummaryBO.setProjectName(obj.getProjectName());//项目名称
                    serveSummaryBO.setArea(obj.getArea());//设置地区
                    serveSummaryBO.setServePrincipal(obj.getServePrincipal());//设置招待负责人
                    serveSummaryBO.setClientName(clientName);//设置客户姓名
                    serveSummaryBO.setActivityType(obj.getPlanActivityType());//设置计划活动类型
                    serveSummaryBO.setActivityTiming(planActivityTimingStr);//设置计划活动时间点
                    serveSummaryBO.setWhetherTemporaryServe(whetherTemporaryServe);//设置是否临时招待
                    serveSummaryBO.setAttendPeopleNo(obj.getPredictAttendNo());//设置参加人数
                    serveSummaryBO.setCharge(obj.getPredictCharge());//设置费用

                    serveSummaryBOList.add(serveSummaryBO);//添加单个项目
                }

                String areaCount = countApplyAreaNo(planActivityTiming, projectGroup);//地区计数
                String servePrincipalCount = countApplyServePrincipalNo(planActivityTiming, projectGroup);//招待负责人计数
                String clientCount = countApplyClientNo(planActivityTiming, projectGroup);//客户姓名计数
                String planActivityCount = countPlanActivity(planActivityTiming, projectGroup);//计划活动类型计数
                String planActivityTimingCount = countPlanActivityTiming(planActivityTiming, projectGroup);//计算计划活动时间点
                String whetherTemporaryServeCount = countApplyWhetherTemporaryServe(planActivityTiming, projectGroup);//计算是否临时招待计数
                Integer predictAttendNoCount = countApplyPredictAttendNo(planActivityTiming, projectGroup);//计算预计参加人数
                Double predictChargeCount = countApplyPredictCharge(planActivityTiming, projectGroup);//计算预计费用
                ServeSummaryBO summaryBO = new ServeSummaryBO();
                summaryBO.setProjectName("合计");
                summaryBO.setArea(areaCount);//设置地区
                summaryBO.setServePrincipal(servePrincipalCount);//设置招待负责人
                summaryBO.setClientName(clientCount);//设置客户姓名
                summaryBO.setActivityType(planActivityCount);//设置计划活动类型
                summaryBO.setActivityTiming(planActivityTimingCount);//设置计划活动时间点
                summaryBO.setWhetherTemporaryServe(whetherTemporaryServeCount);//设置是否临时招待
                summaryBO.setAttendPeopleNo(predictAttendNoCount);//设置参加人数
                summaryBO.setCharge(predictChargeCount);//设置费用

                serveSummaryBOList.add(summaryBO);//添加单个项目
            }
        }

        return serveSummaryBOList;
    }

    /**
     * 判断是否临时招待
     *
     * @param obj
     * @return
     */
    private String getWhetherTemporaryServe(MarketServeApply obj) {
        Boolean whetherTemporaryServe = obj.getWhetherTemporaryServe();
        if (whetherTemporaryServe != null) {
            if (whetherTemporaryServe == Boolean.TRUE) {
                return "是";
            } else {
                return "否";
            }
        }

        return "否";
    }

    /**
     * 计算预计费用的总数
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 预计费用的总数
     */
    private Double countApplyPredictCharge(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Double predictCharge = 0d;
        for (MarketServeApply apply : list) {
            if (apply.getPredictCharge() != null) {
                predictCharge += apply.getPredictCharge();
            }
        }

        return predictCharge;
    }

    /**
     * 计算预计参加人数总数
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 预计参加人数总数
     */
    private Integer countApplyPredictAttendNo(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Integer predictAttendTotalNo = 0;
        for (MarketServeApply apply : list) {
            if (apply.getPredictAttendNo() != null) {
                predictAttendTotalNo += apply.getPredictAttendNo();
            }
        }

        return predictAttendTotalNo;
    }

    /**
     * 计算是否临时招待数目
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 是否临时招待数目
     */
    private String countApplyWhetherTemporaryServe(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<Boolean> whetherTemporaryServeSet = new HashSet<>(0);
        for (MarketServeApply apply : list) {
            if (apply.getWhetherTemporaryServe() != null) {
                whetherTemporaryServeSet.add(apply.getWhetherTemporaryServe());
            }
        }

        return String.valueOf(whetherTemporaryServeSet.size());
    }

    /**
     * 计算计划活动时间点的数目
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 计划活动时间点的数目
     */
    private String countPlanActivityTiming(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<LocalDateTime> planActivityTimingSet = new HashSet<>(0);
        for (MarketServeApply apply : list) {
            if (apply.getPlanActivityTiming() != null) {
                planActivityTimingSet.add(apply.getPlanActivityTiming());
            }
        }
        return String.valueOf(planActivityTimingSet.size());
    }

    /**
     * 计算计划活动数量
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 计划活动数量
     */
    private String countPlanActivity(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<String> planActivitySet = new HashSet<>(0);
        for (MarketServeApply apply : list) {
            if (StringUtils.isNotBlank(apply.getPlanActivityType())) {
                planActivitySet.add(apply.getPlanActivityType());
            }
        }
        return String.valueOf(planActivitySet.size());
    }

    /**
     * 计算客户的数量
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 客户的数量
     */
    private String countApplyClientNo(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<String> clientSet = new HashSet<>(0);
        for (MarketServeApply apply : list) {
            String marketServeApplyId = apply.getId();
            CustomerInfoDTO dto = new CustomerInfoDTO();
            dto.getConditions().add(Restrict.eq("marketServeId", marketServeApplyId));
            List<CustomerInfo> customerInfoList = customerInfoSer.findByCis(dto);
            if (CollectionUtils.isEmpty(customerInfoList)) {
                return null;
            }
            StringBuffer clientName = new StringBuffer();
            for (CustomerInfo customerInfo : customerInfoList) {
                if (StringUtils.isNoneBlank(customerInfo.getClientName())) {
                    clientName.append(customerInfo.getClientName());
                    clientName.append(",");
                }
            }
            String clientNameString = clientName.toString();
            clientNameString = clientNameString.substring(0, clientNameString.length() - 1);
            clientSet.add(clientNameString);
        }
        return String.valueOf(clientSet.size());
    }

    /**
     * 计算招待负责人数量
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 负责人的数量
     */
    private String countApplyServePrincipalNo(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<String> servePrincipalSet = new HashSet<>(0);
        for (MarketServeApply entity : list) {
            if (StringUtils.isNoneBlank(entity.getServePrincipal())) {
                servePrincipalSet.add(entity.getServePrincipal());
            }
        }
        return String.valueOf(servePrincipalSet.size());
    }

    /**
     * 计算某一项目组某一时间段地区的数量
     *
     * @param planActivityTiming 时间段
     * @param projectGroup       项目组
     * @return 地区的数量
     */
    private String countApplyAreaNo(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        List<MarketServeApply> list = getMarketServeApplies(planActivityTiming, projectGroup);
        Set<String> areaSet = new HashSet<>(0);
        for (MarketServeApply entity : list) {
            if (StringUtils.isNotBlank(entity.getArea())) {
                areaSet.add(entity.getArea());
            }
        }
        return String.valueOf(areaSet.size());
    }

    /**
     * 根据项目组获取某一时间段的市场招待申请数量
     *
     * @param planActivityTiming
     * @param projectGroup
     * @return
     * @throws SerException
     */
    private List<MarketServeApply> getMarketServeApplies(LocalDateTime[] planActivityTiming, String projectGroup) throws SerException {
        MarketServeApplyDTO dto = new MarketServeApplyDTO();
        dto.getConditions().add(Restrict.eq("projectName", projectGroup));
        if (planActivityTiming != null) {
            dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming));
        }
        dto.getSorts().add("planActivityTiming=desc");
        return marketServeApplySer.findByCis(dto);
    }

    /**
     * 获取对应记录的客户姓名
     *
     * @param obj
     * @return
     */
    private String getClientName(MarketServeApply obj) throws SerException {
        StringBuffer clientNames = new StringBuffer();
        String marketServeId = obj.getId();
        CustomerInfoDTO dto = new CustomerInfoDTO();
        dto.getConditions().add(Restrict.eq("marketServeId", marketServeId));
        List<CustomerInfo> boList = customerInfoSer.findByCis(dto);
        if (CollectionUtils.isEmpty(boList)) {
            return null;
        }
        for (CustomerInfo info : boList) {
            clientNames.append(info.getClientName()).append(",");
        }
        String clientNameString = clientNames.toString();

        if(clientNameString.length()>0 && StringUtils.isNotBlank(clientNameString)){
            clientNameString.substring(0, clientNameString.length() - 1);
        }
        return clientNameString;
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "年";
                break;
            case 1:
                unit = "月";
                break;
            case 2:
                unit = "周";
                break;
            case 3:
                unit = "日";
                break;
            case 4:
                unit = "小时";
                break;
            case 5:
                unit = "分";
                break;
            case 6:
                unit = "季";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    @Override
    public void checkSendEmail() throws SerException {
        List<MarketServeSummary> summaryEmails = new ArrayList<>();
        List<MarketServeSummary> allEmails = new ArrayList<>();
        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        MarketServeSummaryDTO dto = new MarketServeSummaryDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<MarketServeSummary> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (MarketServeSummary str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastTime();
            //发送间隔
            Double sendNum = str.getSendInterval();
            //发送单位
            CycleType cycle = str.getCycle();
            //发送对象 ,隔开
            String sendObject = str.getEmails();

            Long mis = nowTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - lastTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Double temp_sendNum = 0d;
            Boolean flag = false;
            switch (cycle) {
                case MINUTE:
                    //毫秒数
                    temp_sendNum = sendNum * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusMinutes( sendNum.longValue() ));
                    }
                    break;
                case HOUR:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusHours( sendNum.longValue() ));
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusDays( sendNum.longValue() ));
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusWeeks( sendNum.longValue() ));
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusMonths( sendNum.longValue() ));
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3*sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3*sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusMonths( 3* sendNum.longValue() ));
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusYears( sendNum.longValue() ));
                    }
                    break;
            }

            if (flag) {
                summaryEmails.add(str);
            }
            //调用发邮件
            allEmails = sendObject(summaryEmails);

            //修改上次发送时间
            super.update(allEmails);

        }
    }

    private String htmlSummary(List<ServeSummaryBO> summaryBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (summaryBOList != null && summaryBOList.size() > 0) {
            sb = new StringBuffer("<h4>市场活动汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            ServeSummaryBO title = summaryBOList.get(summaryBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>项目名称</td>");
            sb.append("<td>地区</td>");
            sb.append("<td>招待负责人</td>");
            sb.append("<td>客户姓名</td>");
            sb.append("<td>计划/实际活动类型</td>");
            sb.append("<td>计划/实际活动时间点</td>");
            sb.append("<td>否临时招待</td>");
            sb.append("<td>参加人数</td>");
            sb.append("<td>费用</td>");

            sb.append("<tr>");

            //拼body部分
            for (ServeSummaryBO bo : summaryBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getProjectName() + "</td>");
                sb.append("<td>" + bo.getArea() + "</td>");
                sb.append("<td>" + bo.getServePrincipal() + "</td>");
                sb.append("<td>" + (StringUtils.isBlank(bo.getClientName()) ? " " : bo.getClientName()) + "</td>");
                sb.append("<td>" + bo.getActivityType() + "</td>");
                sb.append("<td>" + bo.getActivityTiming() + "</td>");
                sb.append("<td>" + bo.getWhetherTemporaryServe() + "</td>");
                sb.append("<td>" + bo.getAttendPeopleNo() + "</td>");
                sb.append("<td>" + bo.getCharge() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<MarketServeSummary> sendObject(List<MarketServeSummary> summaryEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<MarketServeSummary> allEmails = new ArrayList<>();
        //市场活动汇总

        if (summaryEmails != null && summaryEmails.size() > 0) {
            for (MarketServeSummary sign : summaryEmails) {

                Boolean type = sign.getType();
                String projectName = sign.getProjectGroups();
                String[] condis = projectName.split(",");
                //处理汇总间隔
                /*Integer collectTime = sign.getDetailInterval();//汇总间隔数
                CyclePerType cyclePerType = sign.getDetailCycle();//汇总间隔单位　年
                switch (cyclePerType){
                    case DAY:
                }
                int year  = LocalDate.now().getYear();
                DateTimeFormatter fotmatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                startTime = String.valueOf(LocalDate.parse( year+"-01-01", fotmatter));
                endTime = String.valueOf(LocalDate.now());*/
                String startTime ="";
                String endTime ="";
                if(sign.getStartTime()!=null && sign.getEndTime()!=null){
                    startTime = String.valueOf(sign.getStartTime()).replace("T", " ").substring(0, 19);
                    endTime = String.valueOf(sign.getEndTime()).replace("T", " ").substring(0, 19);
                }

                List<ServeSummaryBO> measureBOList = marketServeSummarySer.summarize(type, condis, startTime, endTime);
                //拼表格
                String content = htmlSummary(measureBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("市场活动汇总");
                messageTO.setMsgType(MsgType.SYS);
                messageTO.setSendType(SendType.EMAIL);
                messageTO.setRangeType(RangeType.SPECIFIED);

                //定时发送必须写
                messageTO.setSenderId("SYSTEM");
                messageTO.setSenderName("SYSTEM");

                messageTO.setReceivers(sign.getEmails().split(","));
                messageAPI.send(messageTO);


                sign.setModifyTime(LocalDateTime.now());
                allEmails.add(sign);
            }
        }

        return allEmails;

    }
}