//package com.bjike.goddess.marketdevelopment.service;
//
//import com.bjike.goddess.common.api.dto.Restrict;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.jpa.service.ServiceImpl;
//import com.bjike.goddess.common.provider.utils.RpcTransmit;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.common.utils.excel.Excel;
//import com.bjike.goddess.common.utils.excel.ExcelUtil;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanChoiceBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanCollectBO;
//import com.bjike.goddess.marketdevelopment.bo.YearPlanExcelBO;
//import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
//import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
//import com.bjike.goddess.marketdevelopment.entity.YearPlan;
//import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
//import com.bjike.goddess.marketdevelopment.to.CollectTO;
//import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
//import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
//import com.bjike.goddess.user.api.UserAPI;
//import com.bjike.goddess.user.bo.UserBO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 年计划业务实现
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 05:57 ]
// * @Description: [ 年计划业务实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@CacheConfig(cacheNames = "marketdevelopmentSerCache")
//@Service
//public class YearPlanSerImpl extends ServiceImpl<YearPlan, YearPlanDTO> implements YearPlanSer {
//
//    @Autowired
//    private MarPermissionSer marPermissionSer;
//
//    @Autowired
//    private MonthPlanSer monthPlanSer;
//
//    @Autowired
//    private WeekPlanSer weekPlanSer;
//
//    @Autowired
//    private DayPlanSer dayPlanSer;
//
//    @Autowired
//    private BusinessCourseSer businessCourseSer;
//
//    @Autowired
//    private BusinessTypeSer businessTypeSer;
//
//    @Autowired
//    private DemandAnalysisSer demandAnalysisSer;
//
//    @Autowired
//    private MarketChannelSer marketChannelSer;
//
//    @Autowired
//    private MarketMeasureSer marketMeasureSer;
//
//    @Autowired
//    private MarketResearchSer marketResearchSer;
//
//    @Autowired
//    private TargetInformationSer targetInformationSer;
//    @Autowired
//    private UserAPI userAPI;
//
//    private static final String marketCheck = "market-check";
//
//    private static final String marketManage = "market-manage";
//
//    private static final String planManage = "plan-manage";
//
//    private static final String planCheck = "plan-check";
//
//    @Transactional(rollbackFor = SerException.class)
//    @Override
//    public YearPlanBO save(YearPlanTO to) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        YearPlan entity = BeanTransform.copyProperties(to, YearPlan.class);
//        super.save(entity);
//        return BeanTransform.copyProperties(entity, YearPlanBO.class);
//    }
//
//    @Transactional(rollbackFor = SerException.class)
//    @Override
//    public YearPlanBO update(YearPlanTO to) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        if (StringUtils.isNotBlank(to.getId())) {
//            try {
//                YearPlan entity = super.findById(to.getId());
//                BeanTransform.copyProperties(to, entity, true);
//                entity.setModifyTime(LocalDateTime.now());
//                super.update(entity);
//                return BeanTransform.copyProperties(entity, YearPlanBO.class);
//            } catch (Exception e) {
//                throw new SerException("数据对象不能为空");
//            }
//        } else
//            throw new SerException("数据ID不能为空");
//    }
//
//    @Transactional(rollbackFor = SerException.class)
//    @Override
//    public YearPlanBO delete(YearPlanTO to) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        YearPlan entity = super.findById(to.getId());
//        if (entity == null)
//            throw new SerException("数据对象不能为空");
//        if (monthPlanSer.findByYearID(entity.getId()).size() != 0)
//            throw new SerException("存在依赖关系无法删除");
//        super.remove(entity);
//        return BeanTransform.copyProperties(entity, YearPlanBO.class);
//    }
//
//    @Override
//    public List<YearPlanBO> findThisYear() throws SerException {
//        return this.findByYear(LocalDate.now().getYear());
//    }
//
//    @Override
//    public List<YearPlanBO> findByYear(Integer year) throws SerException {
//        YearPlanDTO dto = new YearPlanDTO();
//        dto.getConditions().add(Restrict.eq("year", year));
//        List<YearPlan> list = super.findByCis(dto);
//        return BeanTransform.copyProperties(list, YearPlanBO.class);
//    }
//
//    @Override
//    public List<YearPlanChoiceBO> getChoice() throws SerException {
//        YearPlanDTO dto = new YearPlanDTO();
//        dto.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
//        List<YearPlanChoiceBO> list = new ArrayList<>(0);
//        String format = "%d年 业务类型:%s 科目:%s";
//        for (YearPlan entity : super.findByCis(dto)) {
//            YearPlanChoiceBO choiceBO = new YearPlanChoiceBO();
//            choiceBO.setId(entity.getId());
//            choiceBO.setShowValue(String.format(format, entity.getYear(), entity.getType(), entity.getCourse()));
//            list.add(choiceBO);
//        }
//        return list;
//    }
//
//    @Override
//    public YearPlanBO getById(String id) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        YearPlan entity = super.findById(id);
//        if (entity == null)
//            throw new SerException("数据对象不能为空");
//        return BeanTransform.copyProperties(entity, YearPlanBO.class);
//    }
//
//    @Override
//    public List<YearPlanBO> maps(YearPlanDTO dto) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        dto.getSorts().add("createTime=desc");
//        return BeanTransform.copyProperties(super.findByPage(dto), YearPlanBO.class);
//    }
//
//    public Boolean isPermission(String planCheck) throws SerException{
//
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = marPermissionSer.getMarPermission(planCheck);
//        } else {
//            flag = true;
//        }
//        return flag;
//
//    }
//
//    @Override
//    public Integer getTotal() throws SerException {
//        return super.findAll().size();
//    }
//
//    @Override
//    public byte[] exportExcel(CollectTO to) throws SerException {
////        if (!isPermission(planCheck))
////            throw new SerException("您的帐号没有权限");
//        YearPlanDTO dto = new YearPlanDTO();
//        if (StringUtils.isNotBlank(to.getType()))
//            dto.getConditions().add(Restrict.eq("type", to.getType()));
//        dto.getSorts().add("year=desc");
//        List<YearPlan> list = super.findByCis(dto);
//        List<YearPlanExcelBO> boList = new ArrayList<>(0);
//        for (YearPlan entity : list) {
//            YearPlanExcelBO bo = new YearPlanExcelBO();
//            BeanTransform.copyProperties(entity, bo, true);
//            boList.add(bo);
//        }
//        Excel excel = new Excel(0, 2);
//        byte[] bytes = ExcelUtil.clazzToExcel(boList, excel);
//        return bytes;
//    }
//
//    @Override
//    public List<YearPlanBO> findByType(String type) throws SerException {
//        YearPlanDTO dto = new YearPlanDTO();
//        if (StringUtils.isNotBlank(type))
//            dto.getConditions().add(Restrict.eq("type", type));
//        dto.getSorts().add("year=desc");
//        List<YearPlan> list = super.findByCis(dto);
//        return BeanTransform.copyProperties(list, YearPlanBO.class);
//    }
//
//    @Override
//    public List<SonPermissionObject> sonPermission() throws SerException {
//        List<SonPermissionObject> list = new ArrayList<>();
//        String userToken = RpcTransmit.getUserToken();
//        Boolean flagSeeSign = marPermissionSer.getMarPermission(planCheck);
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagAddSign = marPermissionSer.getMarPermission(planManage);
//
//        SonPermissionObject obj = new SonPermissionObject();
//
//        obj = new SonPermissionObject();
//        obj.setName("yearplan");
//        obj.setDescribesion("年计划");
//        if (flagSeeSign || flagAddSign) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//
//        RpcTransmit.transmitUserToken(userToken);
//        Boolean flagSee = monthPlanSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("monthplan");
//        obj.setDescribesion("月计划");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        flagSee = weekPlanSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("weekplan");
//        obj.setDescribesion("周计划");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        flagSee = dayPlanSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("dayplan");
//        obj.setDescribesion("天计划");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        flagSee = businessCourseSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("businesscourse");
//        obj.setDescribesion("业务方向科目");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = businessTypeSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("businesstype");
//        obj.setDescribesion("业务类型");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = demandAnalysisSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("demandanalysis");
//        obj.setDescribesion("市场需求分析");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = marketChannelSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("marketchannel");
//        obj.setDescribesion("市场挖掘");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = marketMeasureSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("marketmeasure");
//        obj.setDescribesion("市场测算");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = marketResearchSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("marketresearch");
//        obj.setDescribesion("市场调研");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        RpcTransmit.transmitUserToken(userToken);
//        flagSee = targetInformationSer.sonPermission();
//        RpcTransmit.transmitUserToken(userToken);
//        obj = new SonPermissionObject();
//        obj.setName("targetinformation");
//        obj.setDescribesion("确定目标信息");
//        if (flagSee) {
//            obj.setFlag(true);
//        } else {
//            obj.setFlag(false);
//        }
//        list.add(obj);
//
//        return list;
//    }
//
//    @Override
//    public List<YearPlanCollectBO> collect() throws SerException {
//        YearPlanDTO dto = new YearPlanDTO();
//        dto.getSorts().add("year=desc");
//        dto.getSorts().add("type=desc");
//        List<YearPlan> list = super.findByCis(dto);
//        List<YearPlanCollectBO> collectBOs = new ArrayList<>(0);
//        int year = 0;
//        String type = "";
//        for (YearPlan entity : list) {
//            if (year != entity.getYear() || !type.equals(entity.getType())) {
//                year = entity.getYear();
//                type = entity.getType();
//                List<YearPlan> count = list.stream()
//                        .filter(y -> y.getYear() == entity.getYear() && y.getType().equals(entity.getType()))
//                        .collect(Collectors.toList());
//                double size = count.size();
//                YearPlanCollectBO bo = new YearPlanCollectBO();
//                bo.setYear(year);
//                bo.setType(type);
//                bo.setDevelopment(count.stream().mapToInt(YearPlan::getDevelopment).sum() + 0d);
//                bo.setWorkloadWeight(this.decimal(count.stream().mapToDouble(YearPlan::getWorkloadWeight).sum() / size) + "%");
//                collectBOs.add(bo);
//            }
//        }
//        YearPlanCollectBO bo = new YearPlanCollectBO();
//        bo.setType("总计");
//        bo.setDevelopment(collectBOs.stream().mapToDouble(YearPlanCollectBO::getDevelopment).sum());
//        bo.setWorkloadWeight(this.decimal(list.stream().mapToDouble(YearPlan::getWorkloadWeight).sum() / list.size()) + "%");
//        collectBOs.add(bo);
//        return collectBOs;
//    }
//
//    private Double decimal(double number) {
//        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
//    }
//
//    /**
//     * 核对查看权限（部门级别）
//     */
//    private Boolean guideSeeIdentity() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = marPermissionSer.getMarPermission(planCheck);
//        } else {
//            flag = true;
//        }
//        return flag;
//    }
//
//    /**
//     * 核对添加修改删除审核权限（岗位级别）
//     */
//    private Boolean guideAddIdentity() throws SerException {
//        Boolean flag = false;
//        String userToken = RpcTransmit.getUserToken();
//        UserBO userBO = userAPI.currentUser();
//        RpcTransmit.transmitUserToken(userToken);
//        String userName = userBO.getUsername();
//        if (!"admin".equals(userName.toLowerCase())) {
//            flag = marPermissionSer.getMarPermission(planManage);
//        } else {
//            flag = true;
//        }
//        return flag;
//    }
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        String userToken = RpcTransmit.getUserToken();
//        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
//        Boolean flag = true;
//        switch (guideAddrStatus) {
//            case LIST:
//                flag = guideSeeIdentity();
//                break;
//            case ADD:
//                flag = guideAddIdentity();
//                break;
//            case EDIT:
//                flag = guideAddIdentity();
//                break;
//            case AUDIT:
//                flag = guideAddIdentity();
//                break;
//            case DELETE:
//                flag = guideAddIdentity();
//                break;
//            case CONGEL:
//                flag = guideAddIdentity();
//                break;
//            case THAW:
//                flag = guideAddIdentity();
//                break;
//            case COLLECT:
//                flag = guideAddIdentity();
//                break;
//            case IMPORT:
//                flag = guideAddIdentity();
//                break;
//            case EXPORT:
//                flag = guideAddIdentity();
//                break;
//            case UPLOAD:
//                flag = guideAddIdentity();
//                break;
//            case DOWNLOAD:
//                flag = guideAddIdentity();
//                break;
//            case SEE:
//                flag = guideSeeIdentity();
//                break;
//            case SEEFILE:
//                flag = guideSeeIdentity();
//                break;
//            default:
//                flag = true;
//                break;
//        }
//
//        RpcTransmit.transmitUserToken(userToken);
//        return flag;
//    }
//
//}