package com.bjike.goddess.businessevaluate.service;

import com.bjike.goddess.businessevaluate.bo.EvaluateProjectInfoBO;
import com.bjike.goddess.businessevaluate.bo.ProjectProfitRateBO;
import com.bjike.goddess.businessevaluate.dto.*;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.BusinessPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.FrontlineEvaluateDTO;
import com.bjike.goddess.businessevaluate.dto.interiorevaluate.ProjectPrincipalEvaluateDTO;
import com.bjike.goddess.businessevaluate.entity.*;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.BusinessPrincipalEvaluate;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.FrontlineEvaluate;
import com.bjike.goddess.businessevaluate.entity.interiorevaluate.ProjectPrincipalEvaluate;
import com.bjike.goddess.businessevaluate.enums.GuideAddrStatus;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.BusinessPrincipalEvaluateSer;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.FrontlineEvaluateSer;
import com.bjike.goddess.businessevaluate.service.interiorevaluate.ProjectPrincipalEvaluateSer;
import com.bjike.goddess.businessevaluate.to.EvaluateProjectInfoTO;
import com.bjike.goddess.businessevaluate.to.GuidePermissionTO;
import com.bjike.goddess.businessevaluate.vo.SonPermissionObject;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 项目基本信息业务实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-27 03:59 ]
 * @Description: [ 项目基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessevaluateSerCache")
@Service
public class EvaluateProjectInfoSerImpl extends ServiceImpl<EvaluateProjectInfo, EvaluateProjectInfoDTO> implements EvaluateProjectInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    @Autowired
    private DemandCostSer demandCostSer;
    @Autowired
    private LabourCostSer labourCostSer;
    @Autowired
    private AnotherCostSer anotherCostSer;
    @Autowired
    private ProjectCostSer projectCostSer;
    @Autowired
    private AbilityGrowUpSer abilityGrowUpSer;
    @Autowired
    private MarketSesponseSer marketSesponseSer;
    @Autowired
    private ProblemDisposeSer problemDisposeSer;
    @Autowired
    private ProjectAmountSer projectAmountSer;
    @Autowired
    private FrontlineEvaluateSer frontlineEvaluateSer;
    @Autowired
    private BusinessPrincipalEvaluateSer businessPrincipalSer;
    @Autowired
    private ProjectPrincipalEvaluateSer projectPrincipalSer;
    @Autowired
    private BusinessEvaluateCollectSer evaluateCollectSer;


    @Override
    @Transactional(rollbackFor = SerException.class)
    public EvaluateProjectInfoBO addModel(EvaluateProjectInfoTO to) throws SerException {


        EvaluateProjectInfo model = BeanTransform.copyProperties(to, EvaluateProjectInfo.class, true);
        if (to.getYears() != null && to.getMonths() != null && to.getDays() != null) {
            StringBuilder experienceTime = new StringBuilder();
            experienceTime.append(to.getYears());
            experienceTime.append("年");
            experienceTime.append(to.getMonths());
            experienceTime.append("月");
            experienceTime.append(to.getDays());
            experienceTime.append("日");
            model.setExperienceTime(experienceTime.toString());
        } else {
            throw new SerException("工期经历时间不能为空!");
        }
        super.save(model);
        to.setId(model.getId());
        return BeanTransform.copyProperties(to, EvaluateProjectInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public EvaluateProjectInfoBO editModel(EvaluateProjectInfoTO to) throws SerException {

        if (!StringUtils.isEmpty(to.getId())) {
            EvaluateProjectInfo model = super.findById(to.getId());
            if (model != null) {
                if (to.getYears() != null && to.getMonths() != null && to.getDays() != null) {
                    StringBuilder experienceTime = new StringBuilder();
                    experienceTime.append(to.getYears());
                    experienceTime.append("年");
                    experienceTime.append(to.getMonths());
                    experienceTime.append("月");
                    experienceTime.append(to.getDays());
                    experienceTime.append("日");
                    to.setExperienceTime(experienceTime.toString());
                } else {
                    throw new SerException("工期经历时间不能为空!");
                }

                BeanTransform.copyProperties(to, model, true);
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, EvaluateProjectInfoBO.class);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EvaluateProjectInfoBO> pageList(EvaluateProjectInfoDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        List<EvaluateProjectInfo> list = super.findByPage(dto);
        List<EvaluateProjectInfoBO> boList = BeanTransform.copyProperties(list, EvaluateProjectInfoBO.class);
        if (!CollectionUtils.isEmpty(list)) {
            for (EvaluateProjectInfoBO bo : boList) {

                bo.setCost(getCost(bo.getId()));
            }
        }
        return boList;
    }

    @Override
    public Double getCost(String infoId) throws SerException {
        DemandCostDTO demandCostDTO = new DemandCostDTO();
        AnotherCostDTO anotherCostDTO = new AnotherCostDTO();
        LabourCostDTO labourCostDTO = new LabourCostDTO();
        demandCostDTO.getConditions().add(Restrict.eq("projectInfoId", infoId));
        anotherCostDTO.getConditions().add(Restrict.eq("projectInfoId", infoId));
        labourCostDTO.getConditions().add(Restrict.eq("projectInfoId", infoId));

        List<DemandCost> demandCostList = demandCostSer.findByCis(demandCostDTO);
        List<AnotherCost> anotherCostList = anotherCostSer.findByCis(anotherCostDTO);
        List<LabourCost> labourCosts = labourCostSer.findByCis(labourCostDTO);
        Double totalCost = 0.0;
        if (!CollectionUtils.isEmpty(demandCostList)) {
            Double totalEq = demandCostList.stream().filter(p -> p.getEquipmentSalary() != null).mapToDouble(p -> p.getEquipmentSalary()).sum();
            Double totalCf = demandCostList.stream().filter(p -> p.getConfigSalary() != null).mapToDouble(p -> p.getConfigSalary()).sum();
            Double totalCs = demandCostList.stream().filter(p -> p.getCarSalary() != null).mapToDouble(p -> p.getCarSalary()).sum();
            Double totalAn = demandCostList.stream().filter(p -> p.getAnother() != null).mapToDouble(p -> p.getAnother()).sum();
            totalCost = totalCost + totalEq;
            totalCost = totalCost + totalCf;
            totalCost = totalCost + totalCs;
            totalCost = totalCost + totalAn;
        }
        if (!CollectionUtils.isEmpty(anotherCostList)) {
            Double totalAnother = anotherCostList.stream().filter(p -> p.getSalary() != null).mapToDouble(p -> p.getSalary()).sum();
            totalCost = totalCost + totalAnother;
        }
        if (!CollectionUtils.isEmpty(labourCosts)) {
            Double totalStaff = labourCosts.stream().filter(p -> p.getStaffLease() != null).mapToDouble(p -> p.getStaffLease()).sum();
            Double totalNs = labourCosts.stream().filter(p -> p.getNormalSalary() != null).mapToDouble(p -> p.getNormalSalary()).sum();
            Double totalOs = labourCosts.stream().filter(p -> p.getOvertimeSalary() != null).mapToDouble(p -> p.getOvertimeSalary()).sum();
            Double totalAnother = labourCosts.stream().filter(p -> p.getAnother() != null).mapToDouble(p -> p.getAnother()).sum();
            totalCost = totalCost + totalStaff;
            totalCost = totalCost + totalNs;
            totalCost = totalCost + totalOs;
            totalCost = totalCost + totalAnother;
        }
        return totalCost;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectProfitRateBO> profitPageList(EvaluateProjectInfoDTO dto) throws SerException {

        dto.getSorts().add("createTime=desc");
        List<EvaluateProjectInfo> list = super.findByPage(dto);
        List<ProjectProfitRateBO> boList = new ArrayList<ProjectProfitRateBO>();
        for (EvaluateProjectInfo model : list) {
            //查询费用
            ProjectCostDTO costDTO = new ProjectCostDTO();
            costDTO.getConditions().add(Restrict.eq("projectInfoId", model.getId()));
            ProjectCost projectCost = projectCostSer.findOne(costDTO);

            Double fee = 0.0;
            if (projectCost == null) {
                fee = 0.0;
            } else {
                fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission() + projectCost.getAnother();
            }

            Double cost = getCost(model.getId());

            ProjectProfitRateBO bo = new ProjectProfitRateBO();
            //利润 = 项目总金额 - 总成本(劳动成本 + 需求成本 + 其他成本 ) -管理费 -税金 - 费用
            Double profit = model.getTotalAmount() - cost - model.getManageCost() - model.getTaxes() - fee;
            //利润率 = 利润 / 项目总金额

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            Double profitRate = profit / model.getTotalAmount() * 100;
            Double costRate = null;
            Double feeRate = null;
            String costRateStr = null;
            String feeRateStr = null;
            if (cost.doubleValue() == 0) {

            } else {
                costRate = profit / cost * 100;
                costRateStr = decimalFormat.format(costRate) + "%";
            }
            if (fee.doubleValue() == 0) {

            } else {
                feeRate = profit / fee * 100;
                feeRateStr = decimalFormat.format(feeRate) + "%";
            }

            //转换利率百分比
            String profitRateStr = decimalFormat.format(profitRate) + "%";

            bo.setArea(model.getArea());
            bo.setProject(model.getProject());
            bo.setStartTime(model.getStartTime().toString());
            bo.setEndTime(model.getEndTime().toString());
            bo.setProfit(profit);
            bo.setProjectProfitRate(profitRateStr);
            bo.setCostProfitRate(costRateStr);
            bo.setFeeProfitRate(feeRateStr);
            boList.add(bo);
        }
        return boList;
    }

    /**
     * 查询利润率最高及最低项目
     *
     * @return 利润率最高及最低项目
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ProjectProfitRateBO> profitScope() throws SerException {

        List<EvaluateProjectInfo> list = super.findAll();
        List<ProjectProfitRateBO> boList = new ArrayList<ProjectProfitRateBO>();
        for (EvaluateProjectInfo model : list) {
            //查询费用
            ProjectCostDTO costDTO = new ProjectCostDTO();
            costDTO.getConditions().add(Restrict.eq("projectInfoId", model.getId()));
            ProjectCost projectCost = projectCostSer.findOne(costDTO);

            Double fee = 0.0;
            if (projectCost == null) {
                fee = 0.0;
            } else {
                fee = projectCost.getServiceCost() + projectCost.getEntertainCost() + projectCost.getCommission() + projectCost.getAnother();
            }

            Double cost = getCost(model.getId());

            ProjectProfitRateBO bo = new ProjectProfitRateBO();
            //利润 = 项目总金额 - 成本 -管理费 -税金 - 费用
            Double profit = model.getTotalAmount() - cost - model.getManageCost() - model.getTaxes() - fee;
            //利润率 = 利润 / 项目总金额

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            Double profitRate = profit / model.getTotalAmount() * 100;
            String profitRateStr = decimalFormat.format(profitRate) + "%";

            bo.setArea(model.getArea());
            bo.setProject(model.getProject());
            bo.setStartTime(model.getStartTime().toString());
            bo.setEndTime(model.getEndTime().toString());
            bo.setProjectProfitRateNum(profitRate);
            bo.setProjectProfitRate(profitRateStr);
            boList.add(bo);
        }
        return swapSize(boList);
    }

    @Override
    public List<EvaluateProjectInfoBO> findAllArea() throws SerException {

        String sql = "select distinct area from businessevaluate_evaluateprojectinfo where 0 = 0 ";
        List<EvaluateProjectInfo> list = super.findBySql(sql, EvaluateProjectInfo.class, new String[]{"area"});
        return BeanTransform.copyProperties(list, EvaluateProjectInfoBO.class);
    }

    @Override
    public List<EvaluateProjectInfoBO> findAllProject() throws SerException {

        String sql = "select distinct project ,1 from businessevaluate_evaluateprojectinfo where 0 = 0 ";
        List<EvaluateProjectInfo> list = super.findBySql(sql, EvaluateProjectInfo.class, new String[]{"project"});
        return BeanTransform.copyProperties(list, EvaluateProjectInfoBO.class);
    }

    @Override
    public void delete(String id) throws SerException {

        checkDemand(id);

        checkLabour(id);

        checkAnother(id);

        checkProjectCost(id);

        checkAbility(id);

        checkMarket(id);

        checkProblem(id);

        checkProjectAmount(id);

        checkFront(id);

        checkBusinessPrincipal(id);

        checkProjectPrincipal(id);

        checkCollect(id);

        super.remove(id);
    }


    //查询需求成本
    public void checkDemand(String id) throws SerException {
        DemandCostDTO demandCostDTO = new DemandCostDTO();
        demandCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        demandCostDTO.setLimit(1);
        List<DemandCost> demandCost = demandCostSer.findByPage(demandCostDTO);
        if (!CollectionUtils.isEmpty(demandCost)) {
            throw new SerException("需求成本已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询劳动成本
    public void checkLabour(String id) throws SerException {
        LabourCostDTO labourCostDTO = new LabourCostDTO();
        labourCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        labourCostDTO.setLimit(1);
        List<LabourCost> labourCost = labourCostSer.findByPage(labourCostDTO);
        if (!CollectionUtils.isEmpty(labourCost)) {
            throw new SerException("劳动成本已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询其他成本
    public void checkAnother(String id) throws SerException {
        AnotherCostDTO anotherCostDTO = new AnotherCostDTO();
        anotherCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        anotherCostDTO.setLimit(1);
        List<AnotherCost> anotherCost = anotherCostSer.findByPage(anotherCostDTO);
        if (!CollectionUtils.isEmpty(anotherCost)) {
            throw new SerException("其他成本已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询项目费用
    public void checkProjectCost(String id) throws SerException {
        ProjectCostDTO projectCostDTO = new ProjectCostDTO();
        projectCostDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        projectCostDTO.setLimit(1);
        List<ProjectCost> projectCost = projectCostSer.findByPage(projectCostDTO);
        if (!CollectionUtils.isEmpty(projectCost)) {
            throw new SerException("项目费用已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询能力成长已关联
    public void checkAbility(String id) throws SerException {
        AbilityGrowUpDTO abilityGrowUpDTO = new AbilityGrowUpDTO();
        abilityGrowUpDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        abilityGrowUpDTO.setLimit(1);
        List<AbilityGrowUp> abilityGrowUp = abilityGrowUpSer.findByPage(abilityGrowUpDTO);
        if (!CollectionUtils.isEmpty(abilityGrowUp)) {
            throw new SerException("能力成长已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询市场反应和创新能力基本信息
    public void checkMarket(String id) throws SerException {
        MarketSesponseDTO marketSesponseDTO = new MarketSesponseDTO();
        marketSesponseDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        marketSesponseDTO.setLimit(1);
        List<MarketSesponse> marketSesponses = marketSesponseSer.findByPage(marketSesponseDTO);
        if (!CollectionUtils.isEmpty(marketSesponses)) {
            throw new SerException("市场反应和创新能力基本信息已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询项目问题受理和处理
    public void checkProblem(String id) throws SerException {
        ProblemDisposeDTO problemDisposeDTO = new ProblemDisposeDTO();
        problemDisposeDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        problemDisposeDTO.setLimit(1);
        List<ProblemDispose> problemDisposes = problemDisposeSer.findByPage(problemDisposeDTO);
        if (!CollectionUtils.isEmpty(problemDisposes)) {
            throw new SerException("项目问题受理和处理已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询项目金额
    public void checkProjectAmount(String id) throws SerException {
        ProjectAmountDTO projectAmountDTO = new ProjectAmountDTO();
        projectAmountDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        projectAmountDTO.setLimit(1);
        List<ProjectAmount> projectAmounts = projectAmountSer.findByPage(projectAmountDTO);
        if (!CollectionUtils.isEmpty(projectAmounts)) {
            throw new SerException("项目金额已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询一线体系评价
    public void checkFront(String id) throws SerException {
        FrontlineEvaluateDTO frontlineDTO = new FrontlineEvaluateDTO();
        frontlineDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        frontlineDTO.setLimit(1);
        List<FrontlineEvaluate> frontlineEvaluates = frontlineEvaluateSer.findByPage(frontlineDTO);
        if (!CollectionUtils.isEmpty(frontlineEvaluates)) {
            throw new SerException("一线体系评价已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询商务负责人评价
    public void checkBusinessPrincipal(String id) throws SerException {
        BusinessPrincipalEvaluateDTO businessPrincipalDTO = new BusinessPrincipalEvaluateDTO();
        businessPrincipalDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        businessPrincipalDTO.setLimit(1);
        List<BusinessPrincipalEvaluate> businessPrincipalEvaluates = businessPrincipalSer.findByPage(businessPrincipalDTO);
        if (!CollectionUtils.isEmpty(businessPrincipalEvaluates)) {
            throw new SerException("商务负责人评价已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询項目负责人评价
    public void checkProjectPrincipal(String id) throws SerException {
        ProjectPrincipalEvaluateDTO projectPrincipalDTO = new ProjectPrincipalEvaluateDTO();
        projectPrincipalDTO.getConditions().add(Restrict.eq("projectInfoId", id));
        projectPrincipalDTO.setLimit(1);
        List<ProjectPrincipalEvaluate> projectPrincipalEvaluates = projectPrincipalSer.findByPage(projectPrincipalDTO);
        if (!CollectionUtils.isEmpty(projectPrincipalEvaluates)) {
            throw new SerException("項目负责人评价已关联该项目，请确保该项目无数据关联!");
        }
    }

    //查询商务评估结果汇总
    public void checkCollect(String id) throws SerException {
        BusinessEvaluateCollectDTO collectDTO = new BusinessEvaluateCollectDTO();
        collectDTO.getConditions().add(Restrict.eq("projectId", id));
        collectDTO.setLimit(1);
        List<BusinessEvaluateCollect> evaluateCollects = evaluateCollectSer.findByPage(collectDTO);
        if (!CollectionUtils.isEmpty(evaluateCollects)) {
            throw new SerException("商务评估结果汇总已关联该项目，请确保该项目无数据关联!");
        }
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();

        String userToken = RpcTransmit.getUserToken();
        Boolean flagAddSign = guideSeeIdentity();
        SonPermissionObject obj = new SonPermissionObject();
        obj = new SonPermissionObject();
        obj.setName("baseinfo");
        obj.setDescribesion("商务评估-项目基本信息");
        if (flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagDemandDis = demandCostSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("demandcost");
        obj.setDescribesion("项目成本-需求成本");
        if (flagDemandDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagLabourDis = labourCostSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("labourcost");
        obj.setDescribesion("项目成本-劳动成本");
        if (flagLabourDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAnotherDis = anotherCostSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("anothercost");
        obj.setDescribesion("项目成本-其他成本");
        if (flagAnotherDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagFeeDis = projectCostSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectcost");
        obj.setDescribesion("项目费用-项目费用");
        if (flagFeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagProfitDis = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectprofitAct");
        obj.setDescribesion("项目利润率-项目利润率");
        if (flagProfitDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAbilityDis = abilityGrowUpSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("abilitygrowup");
        obj.setDescribesion("项目成长能力-项目成长能力");
        if (flagAbilityDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagResponseDis = marketSesponseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("marketsesponse");
        obj.setDescribesion("市场反应和创新能力-基本信息");
        if (flagResponseDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagDisposeDis = problemDisposeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("problemdispose");
        obj.setDescribesion("市场反应和创新能力-项目问题受理和处理");
        if (flagDisposeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAmountDis = projectAmountSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("projectamount");
        obj.setDescribesion("输出评估结果-项目金额");
        if (flagAmountDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutAbilityDis = abilityGrowUpSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outabilitygrowup");
        obj.setDescribesion("输出评估结果-项目成长能力");
        if (flagOutAbilityDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutProfitDis = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outprojectprofitAct");
        obj.setDescribesion("输出评估结果-项目利润率");
        if (flagOutProfitDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutDisposeDis = problemDisposeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outproblemdispose");
        obj.setDescribesion("输出评估结果-市场反应和创新能力");
        if (flagOutDisposeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagOutResponseDis = marketSesponseSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("outmarketsesponse");
        obj.setDescribesion("输出评估结果-客户关系指标");
        if (flagOutResponseDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagCollectDis = evaluateCollectSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("collect");
        obj.setDescribesion("商务评估结果汇总-商务评估结果汇总");
        if (flagCollectDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = to.getGuideAddrStatus();
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（部门级别）
     */
    private Boolean guideAddIdentity() throws SerException {
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

    //排序利润率最高最低项目
    public List<ProjectProfitRateBO> swapSize(List<ProjectProfitRateBO> boList) throws SerException {
        //设置最快最慢月份
        Collections.sort(boList, new Comparator<ProjectProfitRateBO>() {
            @Override
            public int compare(ProjectProfitRateBO bo1, ProjectProfitRateBO bo2) {
                return bo1.getProjectProfitRateNum().compareTo(bo2.getProjectProfitRateNum());
            }
        });
        List<ProjectProfitRateBO> returnBoList = new ArrayList<ProjectProfitRateBO>();
        if (boList != null && !boList.isEmpty()) {
            ProjectProfitRateBO bo1 = boList.get(0);
            returnBoList.add(new ProjectProfitRateBO("最低", bo1.getArea(), bo1.getProject(), bo1.getStartTime(), bo1.getEndTime(), bo1.getProjectProfitRate()));
            ProjectProfitRateBO bo2 = boList.get(boList.size() - 1);
            returnBoList.add(new ProjectProfitRateBO("最高", bo2.getArea(), bo2.getProject(), bo2.getStartTime(), bo2.getEndTime(), bo2.getProjectProfitRate()));
        }
        return returnBoList;
    }


}