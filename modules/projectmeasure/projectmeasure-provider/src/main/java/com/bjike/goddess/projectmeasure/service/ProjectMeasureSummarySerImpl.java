package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectBasicInfoDTO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.entity.*;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import com.bjike.goddess.projectmeasure.type.CooperationType;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 项目测算汇总业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmeasureSerCache")
@Service
public class ProjectMeasureSummarySerImpl extends ServiceImpl<ProjectMeasureSummary, ProjectMeasureSummaryDTO> implements ProjectMeasureSummarySer {

    @Autowired
    private ProjectBasicInfoSer projectBasicInfoSer;

    @Autowired
    private SingleProjectSingleUISer singleProjectSingleUISer;

    @Autowired
    private SingleProjectMultipleUISer singleProjectMultipleUISer;

    @Autowired
    private MultipleProjectSingleUISer multipleProjectSingleUISer;

    @Autowired
    private MultipleProjectMultipleUISer multipleProjectMultipleUISer;

    @Autowired
    private UserAPI userAPI;

    /**
     * 分页查询项目测算汇总邮件发送
     *
     * @param dto 项目测算汇总dto
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureSummaryBO> list(ProjectMeasureSummaryDTO dto) throws SerException {
        List<ProjectMeasureSummary> list = super.findByPage(dto);
        List<ProjectMeasureSummaryBO> listBO = BeanTransform.copyProperties(list, ProjectMeasureSummaryBO.class);
        return listBO;
    }

    /**
     * 添加项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public ProjectMeasureSummaryBO save(ProjectMeasureSummaryTO to) throws SerException {
        ProjectMeasureSummary entity = BeanTransform.copyProperties(to, ProjectMeasureSummary.class, true);
        entity = setPatameter(to, entity);
        entity = super.save(entity);
        ProjectMeasureSummaryBO bo = BeanTransform.copyProperties(entity, ProjectMeasureSummaryBO.class);
        return bo;
    }

    /**
     * 设置参数
     *
     * @param to
     * @param entity
     * @return
     * @throws SerException
     */
    private ProjectMeasureSummary setPatameter(ProjectMeasureSummaryTO to, ProjectMeasureSummary entity) throws SerException {
        String empNumberUsername = userAPI.currentUser().getEmployeeNumber() + userAPI.currentUser().getUsername();
        entity.setStatus(Status.THAW);
        entity.setCreateUser(empNumberUsername);
        entity.setUpdateTime(LocalDateTime.now());
        entity.setLastTime(LocalDateTime.now());
        String[] projectGroup = to.getProjectGroup();
        boolean projectGroupNotEmpty = (projectGroup != null) && (projectGroup.length > 0);
        if (projectGroupNotEmpty) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < projectGroup.length; i ++) {
                if (i < projectGroup.length - 1) {
                    sb.append(projectGroup[i]).append(",");
                } else {
                    sb.append(projectGroup[i]);
                }
            }
            entity.setProjectGroups(sb.toString());
        } else {
            throw new SerException("要发送的项目组不能为空");
        }
        return entity;
    }

    /**
     * 更新项目测算汇总邮件记录
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void update(ProjectMeasureSummaryTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())){
            ProjectMeasureSummary model = super.findById(to.getId());
            if (model != null) {
                updateProjectMeasureSummary(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新项目测算汇总
     *
     * @param to
     * @param model
     */
    private void updateProjectMeasureSummary(ProjectMeasureSummaryTO to, ProjectMeasureSummary model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        setPatameter(to, model);//设置参数
        verify(model);//参数校验
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 校验参数
     * @param model
     */
    private void verify(ProjectMeasureSummary model) {
    }

    /**
     * 冻结项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void thaw(String id) throws SerException {
        ProjectMeasureSummary entity = super.findById(id);
        entity.setStatus(Status.THAW);//将状态修改为冻结状态
        super.update(entity);//执行更新操作
    }



    /**
     * 解冻项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void congeal(String id) throws SerException {
        ProjectMeasureSummary entity = super.findById(id);
        entity.setStatus(Status.CONGEAL);//将状态修改为解冻状态
        super.update(entity);//执行更新操作
    }

    /**
     * 项目预算管理汇总
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureBO> summarize(ProjectMeasureSummaryTO to) throws SerException {
        List<ProjectMeasureBO> projectMeasureBOList = new ArrayList<>(0);
        String[] areas = to.getAreas();

        boolean areasNotEmpty = (areas != null) && (areas.length > 0);
        if (!areasNotEmpty) {
            throw new SerException("汇总地区不能为空");
        }

        for (String area : areas) {
            Integer projectNo = countProjectNo(area);//某一地区的项目数量
            Double projectProfit = countProjectProfit(area);//计算项目利润
            Integer mmProjectNo = countmMProjectNo(area);//计算多项目多界面项目数量
            Integer msProjectNo = countmSProjectNo(area);//计算多项目单界面项目数量
            Integer smProjectNo = countsMProjectNo(area);//计算单项目多界面项目数量
            Integer ssProjectNo = countsSProjectNo(area);//计算多项目多界面项目数量
            Integer longTermProjectNo = countProjectNoByType(area, CooperationType.LONG_TEAM_COOPERATION);//计算长期合作项目数量
            Integer matterProjectNo = countProjectNoByType(area, CooperationType.MATTER_COOPERATION);//计算事项合作项目
            Integer intermediaryProjectNo = countProjectNoByType(area, CooperationType.INTERMEDIARY);//计算中介合作项目
            // TODO: 17-3-26
            //这里需要计算盈利项目的数量

            ProjectMeasureBO bo = new ProjectMeasureBO();
            bo.setArea(area);
            bo.setProjectCount(projectNo);
            bo.setProjectProfit(projectProfit);
            bo.setMmProjectCount(mmProjectNo);
            bo.setMsProjectCount(msProjectNo);
            bo.setSmProjectCount(smProjectNo);
            bo.setSsProjectCount(ssProjectNo);
            bo.setLongTermProjectCount(longTermProjectNo);
            bo.setMatterProjectCount(matterProjectNo);
            bo.setAgencyProjectCount(intermediaryProjectNo);

            projectMeasureBOList.add(bo);
        }

        //计算合计项
        Integer projectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getProjectCount()).sum();//计算项目数量合计项
        Double projectProfitSum = projectMeasureBOList.stream().mapToDouble(c -> c.getProjectProfit()).sum();//计算项目利润合计项
        Integer mmProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getMmProjectCount()).sum();//计算多项目多界面项目数量合计项
        Integer msProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getMsProjectCount()).sum();//计算多项目单界面项目数量合计项
        Integer smProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getSmProjectCount()).sum();//计算单项目多界面项目数量合计项
        Integer ssProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getSsProjectCount()).sum();//计算单项目单界面项目数量合计项
        Integer longTermProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getLongTermProjectCount()).sum();//计算长期合作项目数量合计项
        Integer matterProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getMatterProjectCount()).sum();//计算事项合作项目数量合计项
        Integer intermediaryProjectNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getAgencyProjectCount()).sum();//计算中介合作项目合计项

        ProjectMeasureBO boSummary = new ProjectMeasureBO();
        boSummary.setArea("合计");
        boSummary.setProjectCount(projectNoSum);
        boSummary.setProjectProfit(projectProfitSum);
        boSummary.setMmProjectCount(mmProjectNoSum);
        boSummary.setMsProjectCount(msProjectNoSum);
        boSummary.setSmProjectCount(smProjectNoSum);
        boSummary.setSsProjectCount(ssProjectNoSum);
        boSummary.setLongTermProjectCount(longTermProjectNoSum);
        boSummary.setMatterProjectCount(matterProjectNoSum);
        boSummary.setAgencyProjectCount(intermediaryProjectNoSum);

        return projectMeasureBOList;
    }

    /**
     * 计算长期合作项目的数量
     *
     * @param area
     * @return
     */
    private Integer countProjectNoByType(String area, CooperationType cooperationType) throws SerException {
        ProjectBasicInfoDTO dto = new ProjectBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("cooperationType", cooperationType));
        List<ProjectBasicInfo> projectBasicInfoList = projectBasicInfoSer.findByCis(dto);
        return projectBasicInfoList.size();
    }

    /**
     * 根据地区计算单个项目单个界面的数量
     *
     * @param area 地区名称
     * @return 单个项目单个界面的数量
     */
    private Integer countsSProjectNo(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        List<SingleProjectSingleUI> sSList = singleProjectSingleUISer.findAll();
        Integer sSProjectNo = 0;
        for (String projectName : projectNameSet) {
            for (SingleProjectSingleUI ssUI : sSList) {
                if (projectName.equals(ssUI.getProjectName())) {
                    sSProjectNo++;
                }
            }
        }
        return sSProjectNo;
    }

    /**
     * 根据地区计算单个项目多个界面的数量
     *
     * @param area 地区名称
     * @return 单个项目多个界面的数量
     */
    private Integer countsMProjectNo(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        List<SingleProjectMultipleUI> sMList = singleProjectMultipleUISer.findAll();
        Integer smProjectNo = 0;
        for (String projectName : projectNameSet) {
            for (SingleProjectMultipleUI smUI : sMList) {
                if (projectName.equals(smUI.getProjectName())) {
                    smProjectNo++;
                }
            }
        }
        return smProjectNo;
    }

    /**
     * 计算多项目单界面项目数量
     *
     * @param area 地区名称
     * @return 多项目单界面项目数量
     */
    private Integer countmSProjectNo(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        List<MultipleProjectSingleUI> mSList = multipleProjectSingleUISer.findAll();
        Integer msProjectNo = 0;
        for (String projectName : projectNameSet) {
            for (MultipleProjectSingleUI msUI : mSList) {
                if (projectName.equals(msUI.getProjectName())) {
                    msProjectNo++;
                }
            }
        }
        return msProjectNo;
    }

    /**
     * 计算多项目多界面项目数量
     *
     * @param area 地区名称
     * @return 多项目多界面的项目数量
     */
    private Integer countmMProjectNo(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        List<MultipleProjectMultipleUI> mMList = multipleProjectMultipleUISer.findAll();
        Integer mmProjectNo = 0;
        for (String projectName : projectNameSet) {
            for (MultipleProjectMultipleUI mmUI : mMList) {
                if (projectName.equals(mmUI.getProjectName())) {
                    mmProjectNo++;
                }
            }
        }
        return mmProjectNo;
    }

    /**
     * 计算某一地区的项目利润
     *
     * @param area 地区
     * @return 某一地区的项目利润
     */
    private Double countProjectProfit(String area) throws SerException {
        Double ssProjectProfit = countSsProjectProfit(area);//计算单个项目单个界面的项目利润
        Double smProjectProfit = countSmProjectProfit(area);//计算单个项目多个界面的项目利润
        Double msProjectProfit = countmSProjectProfit(area);//计算多个项目单个界面的项目利润
        Double mmProjectProfit = countmMProjectProfit(area);//计算多个项目多个界面的项目利润

        //计算总利润
        Double totalProfit = ssProjectProfit + smProjectProfit + msProjectProfit + mmProjectProfit;

        return totalProfit;
    }

    /**
     * 根据地区计算多个项目多个界面的利润
     *
     * @param area
     * @return
     * @throws SerException
     */
    private Double countmMProjectProfit(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        Double mMProfitTotal = getmMProfitTotal(projectNameSet); //计算多项目多界面的利润
        return mMProfitTotal;
    }

    /**
     * 根据项目名称计算多项目多界面的利润
     *
     * @param projectNameSet 项目名称
     * @return 多项目多界面的的利润
     * @throws SerException
     */
    private Double getmMProfitTotal(Set<String> projectNameSet) throws SerException {
        Double mMProjectProfitTotal = 0d;
        List<MultipleProjectMultipleUI> mMList = multipleProjectMultipleUISer.findAll();
        for (String projectName : projectNameSet) {
            for (MultipleProjectMultipleUI mmUI : mMList) {
                if (projectName.equals(mmUI.getProjectName())) {
                    Double mmProjectProfit = mmUI.getProfit();//获取多项目多界面的利润
                    mMProjectProfitTotal += mmProjectProfit;
                }
            }
        }
        return mMProjectProfitTotal;
    }

    /**
     * 根据地区计算多个项目单个界面的利润
     *
     * @param area
     * @return
     */
    private Double countmSProjectProfit(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        Double mSProfitTotal = getmSProfitTotal(projectNameSet); //计算多项目单界面的利润
        return mSProfitTotal;
    }

    /**
     * 根据项目名称获取多项目单界面的总利润
     *
     * @param projectNameSet 项目名称
     * @return 多项目单界面的总利润
     * @throws SerException
     */
    private Double getmSProfitTotal(Set<String> projectNameSet) throws SerException {
        Double msProjectProfitTotal = 0d;
        List<MultipleProjectSingleUI> msList = multipleProjectSingleUISer.findAll();
        for (String projectName : projectNameSet) {
            for (MultipleProjectSingleUI msUI : msList) {
                if (projectName.equals(msUI.getProjectName())) {
                    Double msProjectProfit = msUI.getProfit();//获取多项目单界面的利润
                    msProjectProfitTotal += msProjectProfit;
                }
            }
        }
        return msProjectProfitTotal;
    }

    /**
     * 根据地区计算单个项目多个界面的利润
     *
     * @param area 地区名称
     * @return 某一地区单个项目多个界面的利润
     */
    private Double countSmProjectProfit(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        Double sMProfitTotal = getSmProfitTotal(projectNameSet);//计算单项目多界面的利润
        return sMProfitTotal;
    }

    /**
     * 根据项目名称计算单个项目多个界面的利润
     *
     * @param projectNameSet 项目名称
     * @return 单个项目多个界面的利润
     */
    private Double getSmProfitTotal(Set<String> projectNameSet) throws SerException {
        Double smProjectProfitTotal = 0d;
        List<SingleProjectMultipleUI> smList = singleProjectMultipleUISer.findAll();
        for (String projectName : projectNameSet) {
            for (SingleProjectMultipleUI smUI : smList) {
                if (projectName.equals(smUI.getProjectName())) {
                    Double smProjectProfit = smUI.getProfit();//获取单项目多界面的利润
                    smProjectProfitTotal += smProjectProfit;
                }
            }
        }
        return smProjectProfitTotal;
    }

    /**
     * 根据地区计算单个项目单个界面的利润
     *
     * @param area 地区名称
     * @return 某一地区单个项目单个界面的利润
     */
    private Double countSsProjectProfit(String area) throws SerException {
        Set<String> projectNameSet = getProjectNamesByArea(area);//根据地区获取项目名称
        Double sSProfitTotal = getSsProfitTotal(projectNameSet);
        return sSProfitTotal;
    }

    /**
     * 根据项目名称查找单项目单个界面的利润
     *
     * @param projectNameSet 项目名称集合
     * @return 单项目单个界面的集合
     * @throws SerException
     */
    private Double getSsProfitTotal(Set<String> projectNameSet) throws SerException {
        //根据项目名称查找单项目单个界面的利润
        Double ssProjectProfitTotal = 0d;
        List<SingleProjectSingleUI> ssUIList = singleProjectSingleUISer.findAll();
        for (String projectName : projectNameSet) {
            for (SingleProjectSingleUI ssUI : ssUIList) {
                if (projectName.equals(ssUI.getProjectName())) {
                    Double ssProjectProfit = ssUI.getProfit();//获取项目利润
                    ssProjectProfitTotal += ssProjectProfit;
                    break;
                }
            }
        }
        return ssProjectProfitTotal;
    }

    /**
     * 根据地区获取项目名称
     *
     * @param area 地区名称
     * @return 某一地区的项目名称
     * @throws SerException
     */
    private Set<String> getProjectNamesByArea(String area) throws SerException {
        ProjectBasicInfoDTO pbiDto = new ProjectBasicInfoDTO();
        pbiDto.getConditions().add(Restrict.eq("area", area));
        List<ProjectBasicInfo> projectBasicInfoList = projectBasicInfoSer.findByCis(pbiDto);
        Set<String> projectNameSet = new HashSet<>(0);
        if ((projectBasicInfoList != null) && (projectBasicInfoList.size() > 0)) {
            for (ProjectBasicInfo projectBasicInfo : projectBasicInfoList) {
                String projectName = projectBasicInfo.getProjectName();//获取项目名称
                if (StringUtils.isNotBlank(projectName)) {
                    projectNameSet.add(projectName);
                }
            }
        }
        return projectNameSet;
    }

    /**
     * 计算某一地区的项目数量
     *
     * @param area 地区名称
     * @return 某一地区的项目数量
     */
    private Integer countProjectNo(String area) throws SerException {
        ProjectBasicInfoDTO dto = new ProjectBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<ProjectBasicInfo> projectBasicInfoList = projectBasicInfoSer.findByCis(dto);
        return projectBasicInfoList.size();
    }
}