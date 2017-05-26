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
import com.bjike.goddess.marketactivitymanage.to.MarketServeSummaryTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

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
    private MarketServeRecordSer marketServeRecordSer;

    @Autowired
    private CustomerInfoSer customerInfoSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        //商务模块权限
        Boolean permissionLevel = cusPermissionSer.busCusPermission("1");
        if (!permissionLevel) {
            throw new SerException("您不是商务模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

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
        MarketServeSummary marketServeSummary = BeanTransform.copyProperties(to, MarketServeSummary.class, true);
        marketServeSummary.setStatus(Status.THAW);
        marketServeSummary.setCreateUser(curUsername);
        marketServeSummary.setProjectGroups(sb);
        marketServeSummary.setUpdateTime(LocalDateTime.now());
        marketServeSummary = super.save(marketServeSummary);
        MarketServeSummaryBO bo = BeanTransform.copyProperties(marketServeSummary, MarketServeSummaryBO.class);
        return bo;
    }

    private String getProjectGroup(MarketServeSummaryTO to) {
        String[] projectGroups = to.getProjectGroups();
        boolean projectGroupNotEmpty = (projectGroups != null) && (projectGroups.length > 0);
        StringBuilder sb = new StringBuilder();
        if (projectGroupNotEmpty) {
            for (int i = 0; i < projectGroups.length; i ++) {
                if (i < projectGroups.length -1){
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
        if (StringUtils.isNotEmpty(to.getId())){
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
     * 冻结市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void thaw(MarketServeSummaryTO to) throws SerException {
        checkPermission();
        to.setStatus(Status.THAW);
        this.update(to);
    }

    /**
     * 解冻市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void congeal(MarketServeSummaryTO to) throws SerException {
        checkPermission();
        to.setStatus(Status.CONGEAL);
        this.update(to);
    }

    /**
     * 市场招待申请汇总
     *
     * @param to 市场招待汇总to
     * @return class ServeSummaryBO
     * @throws SerException
     */
    @Override
    public List<ServeSummaryBO> summarize(MarketServeSummaryTO to) throws SerException {
        checkPermission();
        boolean isApply = to.getType();                 //判断是市场招待申请汇总还是实际汇总
        if (isApply) {
            return summarizePlan(to);
        } else {
            return summarizeActual(to);
        }
    }

    /**
     * 市场招待记录汇总
     *
     * @param to
     * @return
     */
    private List<ServeSummaryBO> summarizeActual(MarketServeSummaryTO to) throws SerException {
        String[] projectGroups = to.getProjectGroups();//获取项目组
        String startTimeString = to.getStartTime();          //开始活动时间点
        String endTimeString = to.getEndTime();              //结束活动时间点
        LocalDateTime startTime = DateUtil.parseDateTime(startTimeString);//起始时间
        LocalDateTime endTime = DateUtil.parseDateTime(endTimeString);//结束时间
        LocalDateTime[] actualActivityTiming = new LocalDateTime[]{startTime, endTime};

        List<ServeSummaryBO> serveSummaryBOList = new ArrayList<>(0);

        //按照项目组查询
        for (String projectGroup : projectGroups) {
            MarketServeRecordDTO dto = new MarketServeRecordDTO();
            dto.getConditions().add(Restrict.eq("projectName", projectGroup));
            dto.getConditions().add(Restrict.between("actualActivityTiming", actualActivityTiming));
            dto.getSorts().add("projectName=asc");
            dto.getSorts().add("actualActivityTiming=asc");
            List<MarketServeRecord> marketServeRecordList = marketServeRecordSer.findByCis(dto);

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
                serveSummaryBO.setActivityType(obj.getPlanActivityType());//设置计划活动类型
                serveSummaryBO.setActivityTiming(actualActivityTimingStr);//设置计划活动时间点
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

        return serveSummaryBOList;
    }

    /**
     * 计算实际费用的总数
     *
     * @param actualActivityTiming 实际时间段
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * 计算实际招待负责人的数量
     *
     * @param actualActivityTiming 实际活动时间段
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
     * @return 市场招待记录
     * @throws SerException
     */
    private List<MarketServeRecord> getMarketServeRecords(LocalDateTime[] actualActivityTiming, String projectGroup) throws SerException {
        MarketServeRecordDTO dto = new MarketServeRecordDTO();
        dto.getConditions().add(Restrict.eq("projectName", projectGroup));
        dto.getConditions().add(Restrict.between("actualActivityTiming", actualActivityTiming));
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
        if (whetherTemporaryServe != null){
            if (whetherTemporaryServe == Boolean.TRUE){
                return "是";
            }else {
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

        return clientNameString.substring(0, clientNameString.length() - 1);
    }

    /**
     * 市场招待记录申请汇总
     *
     * @param to 市场招待汇总to
     * @return
     */
    private List<ServeSummaryBO> summarizePlan(MarketServeSummaryTO to) throws SerException {
        String[] projectGroups = to.getProjectGroups();//获取项目组
        String startTimeString = to.getStartTime();          //开始活动时间点
        String endTimeString = to.getEndTime();              //结束活动时间点
        LocalDateTime startTime = DateUtil.parseDateTime(startTimeString);//起始时间
        LocalDateTime endTime = DateUtil.parseDateTime(endTimeString);//结束时间
        LocalDateTime[] planActivityTiming = new LocalDateTime[]{startTime, endTime};

        List<ServeSummaryBO> serveSummaryBOList = new ArrayList<>(0);

        //按照项目组查询
        for (String projectGroup : projectGroups) {
            MarketServeApplyDTO dto = new MarketServeApplyDTO();
            dto.getConditions().add(Restrict.eq("projectName", projectGroup));
            dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming)); //时间范围查询
            dto.getSorts().add("projectName=asc");
            dto.getSorts().add("planActivityTiming=asc");
            List<MarketServeApply> marketServeApplyList = marketServeApplySer.findByCis(dto, true);

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
        if (whetherTemporaryServe != null){
            if (whetherTemporaryServe == Boolean.TRUE){
                return "是";
            }else {
                return "否";
            }
        }

        return "否";
    }

    /**
     * 计算预计费用的总数
     *
     * @param planActivityTiming 时间段
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
     * @param projectGroup 项目组
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
        dto.getConditions().add(Restrict.between("planActivityTiming", planActivityTiming));
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
        for (CustomerInfo info : boList) {
            clientNames.append(info.getClientName()).append(",");
        }
        String clientNameString = clientNames.toString();

        return clientNameString.substring(0, clientNameString.length() - 1);
    }
}