package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.projectroyalty.bo.CollectBO;
import com.bjike.goddess.projectroyalty.bo.WeightalTypeBO;
import com.bjike.goddess.projectroyalty.bo.WeightalsBO;
import com.bjike.goddess.projectroyalty.dto.*;
import com.bjike.goddess.projectroyalty.entity.*;
import com.bjike.goddess.projectroyalty.enums.GuideAddrStatus;
import com.bjike.goddess.projectroyalty.enums.Type;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalTypeTO;
import com.bjike.goddess.projectroyalty.to.WeightalsTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目提成权重分配表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class WeightalsSerImpl extends ServiceImpl<Weightals, WeightalsDTO> implements WeightalsSer {

    @Autowired
    private WeightalTypeSer weightalTypeSer;
    @Autowired
    private ProjectFactorsSer projectFactorsSer;
    @Autowired
    private ContractAmountSer contractAmountSer;
    @Autowired
    private CollectionPeriodSer collectionPeriodSer;
    @Autowired
    private FacilitySer facilitySer;
    @Autowired
    private CompletionTimeSer completionTimeSer;
    @Autowired
    private RatioSer ratioSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
//    @Autowired
//    private SiginManageAPI siginManageAPI;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public List<CollectBO> dayCollect(String time) throws SerException {
        String startTime = "";
        String endTime = "";
        if (StringUtils.isNotBlank(time)) {
            startTime = time;
            endTime = getSpecifiedDayAfter(time);
        }
        return returnMethod(startTime, endTime);
    }

    @Override
    public List<CollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        String[] date = getTimes(year, month, week);
        String startTime = date[0];
        String endTime = date[1];
        return returnMethod(startTime, endTime);
    }

    @Override
    public List<CollectBO> monthCollect(String month) throws SerException {
        if (StringUtils.isBlank(month)) {
            month = LocalDate.now().toString().substring(0, LocalDate.now().toString().lastIndexOf("-"));
        }
        month = month + "-01";
        String startTime = findFirstDayOfMonth(month);
        String endTime = findEndDayOfMonth(month);
        return returnMethod(startTime, endTime);
    }

    @Override
    public List<CollectBO> totalCollect() throws SerException {
        String startTime = "";
        String endTime = "";
        return returnMethod(startTime, endTime);
    }

    @Transactional
    @Override
    public void save(WeightalsTO to) throws SerException {
        Weightals entity = BeanTransform.copyProperties(to, Weightals.class, true, "time", "weightalTypeTOs");
        entity.setTime(LocalDateTime.now());
        entity = super.save(entity);
        for (WeightalTypeTO weightalTypeTO : to.getWeightalTypeTOs()) {
            WeightalType weightalType = BeanTransform.copyProperties(weightalTypeTO, WeightalType.class, true, "totalRatio", "compreRatio", "adjCompreRatio", "amountProfit", "businessRatio", "busCompreRatio", "adjBusCompRatio", "business", "menageRatio", "meCompreRatio", "adjMeCompreRatio", "menage", "capitalRatio", "caCompreRatio", "adjCaCompreRatio", "capital", "allRatio");
            weightalType = transForm(weightalType);
            weightalType.setWeightalsId(entity.getId());
            weightalTypeSer.save(weightalType);
        }
        //生成差异类型
        WeightalType weightalType = differenceType(entity);
        if (null != weightalType) {
            weightalTypeSer.save(weightalType);
        }
    }

    @Transactional
    @Override
    public void update(WeightalsTO to) throws SerException {
        Weightals entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        BeanTransform.copyProperties(to, entity, true, "time", "weightalTypeTOs");
        for (WeightalTypeTO weightalTypeTO : to.getWeightalTypeTOs()) {
            WeightalType weightalType = weightalTypeSer.findById(weightalTypeTO.getId());
            if (null == weightalType) {
                throw new SerException("目标数据不能为空");
            }
            BeanTransform.copyProperties(weightalTypeTO, weightalType, true, "totalRatio", "compreRatio", "adjCompreRatio", "amountProfit", "businessRatio", "busCompreRatio", "adjBusCompRatio", "business", "menageRatio", "meCompreRatio", "adjMeCompreRatio", "menage", "capitalRatio", "caCompreRatio", "adjCaCompreRatio", "capital", "allRatio");
            weightalType = transForm(weightalType);
            weightalType.setModifyTime(LocalDateTime.now());
            weightalTypeSer.update(weightalType);
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        //生成差异类型
        WeightalType weightalType = differenceType(entity);
        if (null != weightalType) {
            weightalTypeSer.save(weightalType);
        }
    }

    @Transactional
    @Override
    public void delete(String id) throws SerException {
        Weightals entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
        weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", id));
        List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);
        if (null != weightalTypes && weightalTypes.size() > 0) {
            weightalTypeSer.remove(weightalTypes);
        }
        super.remove(entity);
    }

    @Override
    public WeightalsBO getById(String id) throws SerException {
        Weightals entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据不能为空");
        }
        WeightalsBO weightalsBO = BeanTransform.copyProperties(entity, WeightalsBO.class, false, "weightalTypeBOs");
        WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
        weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", id));
        List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);
        if (null != weightalTypes && weightalTypes.size() > 0) {
            weightalTypes = weightalTypes.stream().filter(obj -> !Type.DIFFERENCE.equals(obj.getType())).collect(Collectors.toList());
            List<WeightalTypeBO> weightalTypeBOs = BeanTransform.copyProperties(weightalTypes, WeightalTypeBO.class);
            weightalsBO.setWeightalTypeBOs(weightalTypeBOs);
        }
        return weightalsBO;
    }

    @Override
    public List<WeightalsBO> maps(WeightalsDTO dto) throws SerException {
//        WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
//        if(null != dto.getType()){
//            weightalTypeDTO.getConditions().add(Restrict.eq("type", dto.getType()));
//        }
//        if(null != dto.getMonth()){
//            weightalTypeDTO.getConditions().add(Restrict.eq("month", dto.getMonth()));
//        }
        List<Weightals> weightalses = super.findByPage(dto);
        if (null != weightalses && weightalses.size() > 0) {
            List<WeightalsBO> weightalsBOs = BeanTransform.copyProperties(weightalses, WeightalsBO.class, false, "weightalTypeBOs");
            for (WeightalsBO weightalsBO : weightalsBOs) {
                WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
                weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", weightalsBO.getId()));
                List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);
                List<WeightalTypeBO> weightalTypeBOs = BeanTransform.copyProperties(weightalTypes, WeightalTypeBO.class);
                weightalsBO.setWeightalTypeBOs(weightalTypeBOs);
            }
            return weightalsBOs;
        }
        return null;
    }

    @Override
    public Long getTotal(WeightalsDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void adjust(WeightalAdjustTO to) throws SerException {
        Weightals entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
        weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", to.getId()));
        weightalTypeDTO.getConditions().add(Restrict.eq("type", to.getType()));
        List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);
        if (null != weightalTypes && weightalTypes.size() > 0) {
            WeightalType weightalType = weightalTypes.get(0);

            weightalType.setAdjCompreRatio(to.getAdjCompreRatio());
            weightalType.setAdjBusCompRatio(to.getAdjBusCompRatio());
            weightalType.setAdjMeCompreRatio(to.getAdjMeCompreRatio());
            weightalType.setAdjCaCompreRatio(to.getAdjCaCompreRatio());

            weightalType.setAmountProfit(weightalType.getProfit() * weightalType.getAdjCompreRatio());
            weightalType.setBusiness(weightalType.getProfit() * weightalType.getAdjBusCompRatio());
            weightalType.setMenage(weightalType.getProfit() * weightalType.getAdjMeCompreRatio());
            weightalType.setCapital(weightalType.getProfit() * weightalType.getAdjCaCompreRatio());

            weightalType.setAllRatio(weightalType.getAdjCompreRatio() + weightalType.getAdjBusCompRatio() + weightalType.getAdjMeCompreRatio() + weightalType.getAdjCaCompreRatio());

            weightalType.setModifyTime(LocalDateTime.now());
            weightalTypeSer.update(weightalType);
        }
//        super.update(entity);
        //生成差异类型
        WeightalType weightalType = differenceType(entity);
        if (null != weightalType) {
            weightalTypeSer.save(weightalType);
        }
    }

    @Override
    public List<String> findProgram() throws SerException {
        List<ProjectFactors> projectFactorses = projectFactorsSer.findAll();
        List<String> list = projectFactorses.stream().map(ProjectFactors::getCode).distinct().collect(Collectors.toList());
        return list;
    }

    private WeightalType transForm(WeightalType weightalType) throws SerException {
        ProjectFactorsDTO projectFactorsDTO = new ProjectFactorsDTO();
        projectFactorsDTO.getConditions().add(Restrict.eq("code", weightalType.getProgram()));
        ProjectFactors projectFactors = projectFactorsSer.findOne(projectFactorsDTO);
        if (null == projectFactors) {
            throw new SerException("所选方案无数据");
        }
//        weightalType.setTime(LocalDateTime.now());
        Double contract = weightalType.getContract();
        Integer collection = weightalType.getCollection();
        Double importance = weightalType.getImportance();
        Double facility = weightalType.getFacility();
        Double ratio = weightalType.getRatio();
        Double totalRate = 0d;

        ContractAmountDTO contractAmountDTO = new ContractAmountDTO();
        contractAmountDTO.getConditions().add(Restrict.eq("contract", contract));
        List<ContractAmount> contractAmounts = contractAmountSer.findByCis(contractAmountDTO);
        if (null != contractAmounts && contractAmounts.size() > 0) {
            totalRate += contractAmounts.get(0).getRate();
        }

        CollectionPeriodDTO collectionPeriodDTO = new CollectionPeriodDTO();
        collectionPeriodDTO.getConditions().add(Restrict.eq("collection", collection));
        List<CollectionPeriod> collectionPeriods = collectionPeriodSer.findByCis(collectionPeriodDTO);
        if (null != collectionPeriods && collectionPeriods.size() > 0) {
            totalRate += collectionPeriods.get(0).getRate();
        }

        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.getConditions().add(Restrict.eq("facility", facility));
        List<Facility> facilities = facilitySer.findByCis(facilityDTO);
        if (null != facilities && facilities.size() > 0) {
            totalRate += facilities.get(0).getRate();
        }

        CompletionTimeDTO completionTimeDTO = new CompletionTimeDTO();
        completionTimeDTO.getConditions().add(Restrict.eq("importance", importance));
        List<CompletionTime> completionTimes = completionTimeSer.findByCis(completionTimeDTO);
        if (null != completionTimes && completionTimes.size() > 0) {
            totalRate += completionTimes.get(0).getRate();
        }

        RatioDTO ratioDTO = new RatioDTO();
        ratioDTO.getConditions().add(Restrict.eq("ratio", ratio));
        List<Ratio> ratios = ratioSer.findByCis(ratioDTO);
        if (null != ratios && ratios.size() > 0) {
            totalRate += ratios.get(0).getRate();
        }

        Double businessFactors = weightalType.getBusinessFactors();
        Double menageFactors = weightalType.getMenageFactors();
        Double capitalFactors = weightalType.getCapitalFactors();

        weightalType.setAdjCompreRatio(0d);
        weightalType.setAdjBusCompRatio(0d);
        weightalType.setAdjMeCompreRatio(0d);
        weightalType.setAdjCaCompreRatio(0d);

        weightalType.setTotalRatio(projectFactors.getProfitsProportion());
        weightalType.setBusinessRatio(projectFactors.getBusiness());
        weightalType.setMenageRatio(projectFactors.getManage());
        weightalType.setCapitalRatio(projectFactors.getCapital());

        weightalType.setAmountProfit(weightalType.getProfit() * weightalType.getAdjCompreRatio());
        weightalType.setBusiness(weightalType.getProfit() * weightalType.getAdjBusCompRatio());
        weightalType.setMenage(weightalType.getProfit() * weightalType.getAdjMeCompreRatio());
        weightalType.setCapital(weightalType.getProfit() * weightalType.getAdjCaCompreRatio());

        weightalType.setBusCompreRatio(totalRate / businessFactors);
        weightalType.setMeCompreRatio(totalRate / menageFactors);
        weightalType.setCaCompreRatio(totalRate / capitalFactors);
        weightalType.setCompreRatio(100 - weightalType.getBusCompreRatio() - weightalType.getMeCompreRatio() - weightalType.getCaCompreRatio());
        weightalType.setAllRatio(weightalType.getAdjCompreRatio() + weightalType.getAdjBusCompRatio() + weightalType.getAdjMeCompreRatio() + weightalType.getAdjCaCompreRatio());
        return weightalType;
    }

    //差异类型
    private WeightalType differenceType(Weightals entity) throws SerException {
        WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
        weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", entity.getId()));
        List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);
        List<Type> types = new ArrayList<>();
        types.add(Type.ACTUAL);
        types.add(Type.PLAN);
        if (null != weightalTypes && weightalTypes.size() > 0) {
            List<Type> typeList = weightalTypes.stream().map(WeightalType::getType).distinct().collect(Collectors.toList());
            if (typeList.containsAll(types)) {
                //得到类型为实际的数据
                List<WeightalType> weightalTypeActual = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).collect(Collectors.toList());
                //得到类型为计划的数据
                List<WeightalType> weightalTypePlan = weightalTypes.stream().filter(obj -> Type.PLAN.equals(obj.getType())).collect(Collectors.toList());
                //判断是否有类型为差异的数据,没有->生成, 有->更新
                List<WeightalType> weightalTypeDifference = weightalTypes.stream().filter(obj -> Type.DIFFERENCE.equals(obj.getType())).collect(Collectors.toList());
                if (null != weightalTypeDifference && weightalTypeDifference.size() > 0) {
                    weightalTypeSer.remove(weightalTypeDifference);
                }
                WeightalType weightalType = new WeightalType();
                weightalType.setWeightalsId(entity.getId());
                weightalType.setType(Type.DIFFERENCE);
                weightalType.setMonth(weightalTypeActual.get(0).getMonth() - weightalTypePlan.get(0).getMonth());
                weightalType.setContract(weightalTypeActual.get(0).getContract() - weightalTypePlan.get(0).getContract());
                weightalType.setCollection(weightalTypeActual.get(0).getCollection() - weightalTypePlan.get(0).getCollection());
                weightalType.setImportance(weightalTypeActual.get(0).getImportance() - weightalTypePlan.get(0).getImportance());
                weightalType.setFacility(weightalTypeActual.get(0).getFacility() - weightalTypePlan.get(0).getFacility());
                weightalType.setRatio(weightalTypeActual.get(0).getRatio() - weightalTypePlan.get(0).getRatio());
                weightalType.setProfit(weightalTypeActual.get(0).getProfit() - weightalTypePlan.get(0).getProfit());
                weightalType.setProgram(weightalTypeActual.get(0).getProgram());
                weightalType.setTotalRatio(weightalTypeActual.get(0).getTotalRatio() - weightalTypePlan.get(0).getTotalRatio());
                weightalType.setProfitFactors(weightalTypeActual.get(0).getProfitFactors() - weightalTypePlan.get(0).getProfitFactors());
                weightalType.setCompreRatio(weightalTypeActual.get(0).getCompreRatio() - weightalTypePlan.get(0).getCompreRatio());
                weightalType.setAdjCompreRatio(weightalTypeActual.get(0).getAdjCompreRatio() - weightalTypePlan.get(0).getAdjCompreRatio());
                weightalType.setAmountProfit(weightalTypeActual.get(0).getAmountProfit() - weightalTypePlan.get(0).getAmountProfit());
                weightalType.setBusinessRatio(weightalTypeActual.get(0).getBusinessRatio() - weightalTypePlan.get(0).getBusinessRatio());
                weightalType.setBusinessFactors(weightalTypeActual.get(0).getBusinessFactors() - weightalTypePlan.get(0).getBusinessFactors());
                weightalType.setBusCompreRatio(weightalTypeActual.get(0).getBusCompreRatio() - weightalTypePlan.get(0).getBusCompreRatio());
                weightalType.setAdjBusCompRatio(weightalTypeActual.get(0).getAdjBusCompRatio() - weightalTypePlan.get(0).getAdjBusCompRatio());
                weightalType.setBusiness(weightalTypeActual.get(0).getBusiness() - weightalTypePlan.get(0).getBusiness());
                weightalType.setMenageRatio(weightalTypeActual.get(0).getMenageRatio() - weightalTypePlan.get(0).getMenageRatio());
                weightalType.setMenageFactors(weightalTypeActual.get(0).getMenageFactors() - weightalTypePlan.get(0).getMenageFactors());
                weightalType.setMeCompreRatio(weightalTypeActual.get(0).getMeCompreRatio() - weightalTypePlan.get(0).getMeCompreRatio());
                weightalType.setAdjMeCompreRatio(weightalTypeActual.get(0).getAdjMeCompreRatio() - weightalTypePlan.get(0).getAdjMeCompreRatio());
                weightalType.setMenage(weightalTypeActual.get(0).getMenage() - weightalTypePlan.get(0).getMenage());
                weightalType.setCapitalRatio(weightalTypeActual.get(0).getCapitalRatio() - weightalTypePlan.get(0).getCapitalRatio());
                weightalType.setCapitalFactors(weightalTypeActual.get(0).getCapitalFactors() - weightalTypePlan.get(0).getCapitalFactors());
                weightalType.setCaCompreRatio(weightalTypeActual.get(0).getCaCompreRatio() - weightalTypePlan.get(0).getCaCompreRatio());
                weightalType.setAdjCaCompreRatio(weightalTypeActual.get(0).getAdjCaCompreRatio() - weightalTypePlan.get(0).getAdjCaCompreRatio());
                weightalType.setCapital(weightalTypeActual.get(0).getCapital() - weightalTypePlan.get(0).getCapital());
                weightalType.setAllRatio(weightalTypeActual.get(0).getAllRatio() - weightalTypePlan.get(0).getAllRatio());
                weightalType.setRemark("");
                return weightalType;
            }
        }
        return null;
    }

    private void searchCondition(WeightalsDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getTime())) {
            dto.getConditions().add(Restrict.eq("time", dto.getTime()));
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
        }
        if (StringUtils.isNotBlank(dto.getDepartment())) {
            dto.getConditions().add(Restrict.eq("department", dto.getDepartment()));
        }
        if (StringUtils.isNotBlank(dto.getProject())) {
            dto.getConditions().add(Restrict.eq("project", dto.getProject()));
        }
        if (null != dto.getBuild()) {
            dto.getConditions().add(Restrict.eq("build", dto.getBuild()));
        }
        if (null != dto.getBuildTime()) {
            dto.getConditions().add(Restrict.eq("buildTime", dto.getBuildTime()));
        }
        if (null != dto.getComplete()) {
            dto.getConditions().add(Restrict.eq("complete", dto.getComplete()));
        }


//        if (null != dto.getType()) {
//            dto.getConditions().add(Restrict.eq("type", dto.getType()));
//        }
//        if (null != dto.getMonth()) {
//            dto.getConditions().add(Restrict.eq("month", dto.getMonth()));
//        }
    }

    //获取总和
    private CollectBO findTotal(String[] projectName) throws SerException {
        Double aimProfit = 0d;
        Double planProfit = 0d;
        Double actualProfit = 0d;
        Double amountProfit = 0d;
        Double business = 0d;
        Double menage = 0d;
        Double capital = 0d;
        WeightalsDTO weightalsDTO = new WeightalsDTO();
        weightalsDTO.getConditions().add(Restrict.in("project", projectName));
        List<Weightals> weightalses = super.findByCis(weightalsDTO);
        if (null != weightalses && weightalses.size() > 0) {
            for (Weightals weightals : weightalses) {
                WeightalTypeDTO weightalTypeDTO = new WeightalTypeDTO();
                weightalTypeDTO.getConditions().add(Restrict.eq("weightalsId", weightals.getId()));
                List<WeightalType> weightalTypes = weightalTypeSer.findByCis(weightalTypeDTO);

                List<Double> aimProfits = weightalTypes.stream().filter(obj -> Type.AIM.equals(obj.getType())).map(WeightalType::getProfit).collect(Collectors.toList());
                if (null != aimProfits && aimProfits.size() > 0) {
                    aimProfit += aimProfits.get(0);
                }

                List<Double> planProfits = weightalTypes.stream().filter(obj -> Type.PLAN.equals(obj.getType())).map(WeightalType::getProfit).collect(Collectors.toList());
                if (null != planProfits && planProfits.size() > 0) {
                    planProfit += planProfits.get(0);
                }

                List<Double> actualProfits = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).map(WeightalType::getProfit).collect(Collectors.toList());
                if (null != actualProfits && actualProfits.size() > 0) {
                    actualProfit += actualProfits.get(0);
                }

                List<Double> amountProfits = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).map(WeightalType::getAmountProfit).collect(Collectors.toList());
                if (null != amountProfits && amountProfits.size() > 0) {
                    amountProfit += amountProfits.get(0);
                }

                List<Double> businesses = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).map(WeightalType::getBusiness).collect(Collectors.toList());
                if (null != businesses && businesses.size() > 0) {
                    business += businesses.get(0);
                }

                List<Double> menages = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).map(WeightalType::getMenage).collect(Collectors.toList());
                if (null != menages && menages.size() > 0) {
                    menage += menages.get(0);
                }

                List<Double> capitals = weightalTypes.stream().filter(obj -> Type.ACTUAL.equals(obj.getType())).map(WeightalType::getCapital).collect(Collectors.toList());
                if (null != capitals && capitals.size() > 0) {
                    capital += capitals.get(0);
                }
            }
        }
        CollectBO collectBO = new CollectBO();
        collectBO.setAimProfit(aimProfit);
        collectBO.setPlanProfit(planProfit);
        collectBO.setActualProfit(actualProfit);
        collectBO.setAmountProfit(amountProfit);
        collectBO.setBusiness(business);
        collectBO.setMenage(menage);
        collectBO.setCapital(capital);
        return collectBO;
    }

    //获取指定日期的后一天
    public String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    //得到符合条件的相同部门的项目名
    private List<String[]> getProjectName(String startTime, String endTime) throws SerException {
        String[] fildes = new String[]{"area", "department", "is_isBuild", "project"};
        StringBuilder sql = new StringBuilder(" select area, department, is_isBuild, project from projectroyalty_weightals ");
        sql.append(" where is_isBuild = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" and buildTime between '" + startTime + "' ");
            sql.append(" and '" + endTime + "' ");
        }
        List<CollectBO> collectBOs = super.findBySql(sql.toString(), CollectBO.class, fildes);
        List<String[]> list = new ArrayList<>();
        if (null != collectBOs && collectBOs.size() > 0) {
            List<String> departments = collectBOs.stream().map(CollectBO::getDepartment).distinct().collect(Collectors.toList());
            for (String department : departments) {
                List<String> projects = collectBOs.stream().filter(obj -> department.equals(obj.getDepartment())).map(CollectBO::getProject).distinct().collect(Collectors.toList());
                String[] departmentStr = (String[]) projects.toArray(new String[projects.size()]);
                list.add(departmentStr);
            }
        }
        return list;
    }

    private List<CollectBO> listCollectBO(String startTime, String endTime) throws SerException {
        String[] fildes = new String[]{"area", "department", "num"};
        StringBuilder sql = new StringBuilder("select area, department, count(project) as num from projectroyalty_weightals ");
        sql.append(" where is_isBuild = 1 ");
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            sql.append(" and buildTime between '" + startTime + "'");
            sql.append(" and '" + endTime + "'");
        }
        sql.append(" group by area,department;");
        List<CollectBO> collectBOs = super.findBySql(sql.toString(), CollectBO.class, fildes);
        return collectBOs;
    }

    //转换周期
    private String[] getTimes(int year, int month, int week) throws SerException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
        calendar.set(Calendar.WEEK_OF_MONTH, week);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String start = dateFormat.format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String end = dateFormat.format(calendar.getTime());
        LocalDate e = DateUtil.parseDate(end);
        if (week == 1) {
            if (String.valueOf(month).length() == 1) {
                start = year + "-0" + month + "-01";
            } else {
                start = year + "-" + month + "-01";
            }
        }
        if (week == weekNum) {
            if (month != e.getMonthValue()) {
                e = DateUtil.parseDate(end);
                e = e.minusDays(e.getDayOfMonth());
            }
        }
        String endTime = e.toString();
        String[] time = new String[]{start, endTime};
        return time;
    }

    private List<CollectBO> returnMethod(String startTime, String endTime) throws SerException {
        List<CollectBO> returnCollectBOList = new ArrayList<>(0);
        List<CollectBO> collectBOs = listCollectBO(startTime, endTime);
        if (null != collectBOs && collectBOs.size() > 0) {
            for (String[] projectName : getProjectName(startTime, endTime)) {
                for (CollectBO collectBO : collectBOs) {
                    CollectBO collectBO1 = findTotal(projectName);
                    WeightalsDTO weightalsDTO = new WeightalsDTO();
                    weightalsDTO.getConditions().add(Restrict.eq("project", projectName[0]));
                    List<Weightals> weightalses = super.findByCis(weightalsDTO);
                    String department = weightalses.stream().map(Weightals::getDepartment).distinct().collect(Collectors.toList()).get(0);
                    if (department.equals(collectBO.getDepartment())) {
                        BeanTransform.copyProperties(collectBO, collectBO1);
                        returnCollectBOList.add(collectBO1);
                        break;
                    }
                }
            }
        }
        return returnCollectBOList;
    }

    //获取某一个月的第一天
    private String findFirstDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(firstDayOfMonth);
        return startTime;
    }

    //获取某一个月的最后一天
    private String findEndDayOfMonth(String month) throws SerException {
        Date date = null;
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = fmt.parse(month);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfMonth = calendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endTime = sdf.format(lastDayOfMonth);
        return endTime;
    }
}