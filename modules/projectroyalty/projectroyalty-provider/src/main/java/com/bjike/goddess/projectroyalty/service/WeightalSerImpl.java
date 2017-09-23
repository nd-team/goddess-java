package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectroyalty.bo.WeightalBO;
import com.bjike.goddess.projectroyalty.bo.WeightalListBO;
import com.bjike.goddess.projectroyalty.dto.*;
import com.bjike.goddess.projectroyalty.entity.*;
import com.bjike.goddess.projectroyalty.enums.GuideAddrStatus;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 项目提成权重分配表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectroyaltySerCache")
@Service
public class WeightalSerImpl extends ServiceImpl<Weightal, WeightalDTO> implements WeightalSer {
    @Autowired
    private ProjectFactorsSer projectFactorsSer;
    @Autowired
    private ContractAmountSer contractAmountSer;
    @Autowired
    private CollectionPeriodSer collectionPeriodSer;
    @Autowired
    private FacilitySer facilitySer;
    @Autowired
    private RatioSer ratioSer;
    @Autowired
    private CompletionTimeSer completionTimeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public void save(WeightalTO to) throws SerException {
        Weightal entity = BeanTransform.copyProperties(to, Weightal.class, "time", "totalRatio", "compreRatio", "adjCompreRatio", "amountProfit", "businessRatio", "busCompreRatio", "adjBusCompRatio", "business", "menageRatio", "meCompreRatio", "adjMeCompreRatio", "menage", "capitalRatio", "caCompreRatio", "adjCaCompreRatio", "capital", "allRatio");
        entity = transForm(entity);
        super.save(entity);
    }

    @Override
    public void update(WeightalTO to) throws SerException {
        Weightal entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        BeanTransform.copyProperties(to, entity, "time", "totalRatio", "compreRatio", "adjCompreRatio", "amountProfit", "businessRatio", "busCompreRatio", "adjBusCompRatio", "business", "menageRatio", "meCompreRatio", "adjMeCompreRatio", "menage", "capitalRatio", "caCompreRatio", "adjCaCompreRatio", "capital", "allRatio");
        entity = transForm(entity);
        entity.setModifyTime(LocalDateTime.now());
        entity.setRemark(to.getRemark());
        super.update(entity);
    }

    @Override
    public void delete(String id) throws SerException {
        Weightal entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        super.remove(entity);
    }

    @Override
    public WeightalBO getById(String id) throws SerException {
        Weightal entity = super.findById(id);
        if (null == entity) {
            throw new SerException("目标数据对象为空");
        }
        WeightalBO weightalBO = BeanTransform.copyProperties(entity, WeightalBO.class, false);
        return weightalBO;
    }

    @Override
    public List<WeightalBO> maps(WeightalDTO dto) throws SerException {
        searchCondition(dto);
        List<Weightal> weightals = super.findByPage(dto);
        if (null != weightals && weightals.size() > 0) {
            List<WeightalBO> weightalBOs = BeanTransform.copyProperties(weightals, WeightalBO.class, false);
            return weightalBOs;
        }
        return null;
    }

    @Override
    public Long getTotal(WeightalDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void adjust(WeightalAdjustTO to) throws SerException {
        Weightal entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("目标数据对象不能为空");
        }
        entity.setAdjCompreRatio(to.getAdjCompreRatio());
        entity.setAdjBusCompRatio(to.getAdjBusCompRatio());
        entity.setAdjMeCompreRatio(to.getAdjMeCompreRatio());
        entity.setAdjCaCompreRatio(to.getAdjCaCompreRatio());

        entity.setAmountProfit(entity.getProfit() * entity.getAdjCompreRatio());
        entity.setBusiness(entity.getProfit() * entity.getAdjBusCompRatio());
        entity.setMenage(entity.getProfit() * entity.getAdjMeCompreRatio());
        entity.setCapital(entity.getProfit() * entity.getAdjCaCompreRatio());

        entity.setAllRatio(entity.getAdjCompreRatio() + entity.getAdjBusCompRatio() + entity.getAdjMeCompreRatio() + entity.getAdjCaCompreRatio());

        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<String> findProgram() throws SerException {
        List<ProjectFactors> projectFactorses = projectFactorsSer.findAll();
        List<String> list = projectFactorses.stream().map(ProjectFactors::getCode).distinct().collect(Collectors.toList());
        return list;
    }

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
    public List<WeightalListBO> list(WeightalDTO dto) throws SerException {
        return null;
    }

    private Weightal transForm(Weightal entity) throws SerException {
        ProjectFactorsDTO projectFactorsDTO = new ProjectFactorsDTO();
        projectFactorsDTO.getConditions().add(Restrict.eq("code", entity.getProgram()));
        ProjectFactors projectFactors = projectFactorsSer.findOne(projectFactorsDTO);
        if (null == projectFactors) {
            throw new SerException("所选方案无数据");
        }
        entity.setTime(LocalDateTime.now());
        Double contract = entity.getContract();
        Integer collection = entity.getCollection();
        Double importance = entity.getImportance();
        Double facility = entity.getFacility();
        Double ratio = entity.getRatio();
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

        Double businessFactors = entity.getBusinessFactors();
        Double menageFactors = entity.getMenageFactors();
        Double capitalFactors = entity.getCapitalFactors();

        entity.setAdjCompreRatio(0d);
        entity.setAdjBusCompRatio(0d);
        entity.setAdjMeCompreRatio(0d);
        entity.setAdjCaCompreRatio(0d);

        entity.setTotalRatio(projectFactors.getProfitsProportion());
        entity.setBusinessRatio(projectFactors.getBusiness());
        entity.setMenageRatio(projectFactors.getManage());
        entity.setCapitalRatio(projectFactors.getCapital());

        entity.setAmountProfit(entity.getProfit() * entity.getAdjCompreRatio());
        entity.setBusiness(entity.getProfit() * entity.getAdjBusCompRatio());
        entity.setMenage(entity.getProfit() * entity.getAdjMeCompreRatio());
        entity.setCapital(entity.getProfit() * entity.getAdjCaCompreRatio());

        entity.setBusCompreRatio(totalRate / businessFactors);
        entity.setMeCompreRatio(totalRate / menageFactors);
        entity.setCaCompreRatio(totalRate / capitalFactors);
        entity.setCompreRatio(100 - entity.getBusCompreRatio() - entity.getMeCompreRatio() - entity.getCaCompreRatio());
        entity.setAllRatio(entity.getAdjCompreRatio() + entity.getAdjBusCompRatio() + entity.getAdjMeCompreRatio() + entity.getAdjCaCompreRatio());
        return entity;
    }

    private void searchCondition(WeightalDTO dto) throws SerException {
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
        if (null != dto.getType()) {
            dto.getConditions().add(Restrict.eq("type", dto.getType()));
        }
        if (null != dto.getMonth()) {
            dto.getConditions().add(Restrict.eq("month", dto.getMonth()));
        }
    }
}