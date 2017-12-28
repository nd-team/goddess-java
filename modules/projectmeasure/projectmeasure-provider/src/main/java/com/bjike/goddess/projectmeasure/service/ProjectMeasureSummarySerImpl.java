package com.bjike.goddess.projectmeasure.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.projectmeasure.bo.ProjectEvaluateResultBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.*;
import com.bjike.goddess.projectmeasure.entity.*;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;
import com.bjike.goddess.projectmeasure.type.CycleType;
import com.bjike.goddess.projectmeasure.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private MessageAPI messageAPI;

    @Autowired
    private ProjectOtherDemandSer projectOtherDemandSer;

    @Autowired
    private ProjectMeasureSummarySer projectMeasureSummarySer;
    @Autowired
    private SingleProjectMultipleUIBSer singleProjectMultipleUIBSer;
    @Autowired
    private MultipleProjectMultipleUIBSer multipleProjectMultipleUIBSer;

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
        if (!"admin".equals(userName.toLowerCase())) {
            //商务模块权限
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
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 分页查询项目测算汇总邮件发送
     *
     * @param dto 项目测算汇总dto
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureSummaryBO> list(ProjectMeasureSummaryDTO dto) throws SerException {
        checkPermission();
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
    @Transactional(rollbackFor = SerException.class)
    public ProjectMeasureSummaryBO save(ProjectMeasureSummaryTO to) throws SerException {
        checkPermission();
        String curUsername = userAPI.currentUser().getUsername();
        String sb = getProjectGroup(to);
        ProjectMeasureSummary entity = BeanTransform.copyProperties(to, ProjectMeasureSummary.class, true, "areas", "emails");
        entity.setStatus(Status.THAW);
        entity.setCreateUser(curUsername);
        entity.setProjectGroups(sb);
        String areas = StringUtils.join(to.getAreas(), ",");
        String emails = StringUtils.join(to.getEmails(), ",");
        entity.setAreas(areas);
        entity.setEmails(emails);
        entity.setLastTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity = super.save(entity);
        ProjectMeasureSummaryBO bo = BeanTransform.copyProperties(entity, ProjectMeasureSummaryBO.class);
        return bo;
    }

    private String getProjectGroup(ProjectMeasureSummaryTO to) {
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
     * 更新项目测算汇总邮件记录
     *
     * @param to 项目测算汇总邮件记录to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ProjectMeasureSummaryTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
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
     * 更新项目测算汇总邮件记录
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateProjectMeasureSummary(ProjectMeasureSummaryTO to, ProjectMeasureSummary model) throws SerException {
        String sb = getProjectGroup(to);
        String areas = StringUtils.join(to.getAreas(), ",");
//       BeanTransform.copyProperties(to, model, true);
        String emails = StringUtils.join(to.getEmails(), ",");

        model.setDetailInterval(to.getDetailInterval());
        model.setDetailCycle(to.getDetailCycle());
        model.setSendInterval(to.getSendInterval());
        model.setCycle(to.getCycle());
        model.setModifyTime(LocalDateTime.now());
        model.setProjectGroups(sb);
        model.setUpdateTime(LocalDateTime.now());
        model.setAreas(areas);
        model.setEmails(emails);
        super.update(model);
    }

    /**
     * 冻结项目测算汇总
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void thaw(String id) throws SerException {
        checkPermission();
        ProjectMeasureSummary model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setStatus(Status.THAW);
        super.update(model);
    }

    /**
     * 解冻项目测算汇总邮件记录
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void congeal(String id) throws SerException {
        checkPermission();
        ProjectMeasureSummary model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setStatus(Status.CONGEAL);//将状态修改为解冻状态
        super.update(model);
    }

    /**
     * 项目测算管理汇总
     *
     * @param areas 汇总地区
     * @return class ProjectMeasureBO
     * @throws SerException
     */
    @Override
    public List<ProjectMeasureBO> summarize(String[] areas) throws SerException {
        checkPermission();
        List<ProjectMeasureBO> projectMeasureBOList = new ArrayList<>(0);
        for (String area : areas) {
            Integer projectNo = countProjectNo(area);//某一地区的项目数量
            Double projectProfit = countProjectProfit(area);//计算项目利润
            Integer mmProjectNo = countmMProjectNo(area);//计算多项目多界面项目数量
            Integer msProjectNo = countmSProjectNo(area);//计算多项目单界面项目数量
            Integer smProjectNo = countsMProjectNo(area);//计算单项目多界面项目数量
            Integer ssProjectNo = countsSProjectNo(area);//计算多项目多界面项目数量
            Integer longTermProjectNo = countLongTermProjectNo(area);//计算长期合作项目数量
            Integer matterProjectNo = countMatterProjectNo(area);//计算事项合作项目
            Integer intermediaryProjectNo = countIntermediaryProjectNo(area);//计算中介合作项目
            Integer winProNo = countWinProNo(area, 1);//计算盈利项目的数量
            Integer deficitNo = countWinProNo(area, 2);//计算亏损项目的数量


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
            bo.setTestProfitProjectCount(winProNo);
            bo.setTestDeficitProjectCount(deficitNo);

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
        Integer winProNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getTestProfitProjectCount()).sum();//计算盈利项目合计项
        Integer deficitNoSum = projectMeasureBOList.stream().mapToInt(c -> c.getTestDeficitProjectCount()).sum();//计算亏损项目合计项


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
        boSummary.setTestProfitProjectCount(winProNoSum);
        boSummary.setTestDeficitProjectCount(deficitNoSum);
        projectMeasureBOList.add(boSummary);


        return projectMeasureBOList;
    }

    /**
     * 计算长期合作项目的数量
     *
     * @param area
     * @return
     */
    private Integer countLongTermProjectNo(String area) throws SerException {
        ProjectBasicInfoDTO dto = new ProjectBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("cooperationType", 0));
        List<ProjectBasicInfo> projectBasicInfoList = projectBasicInfoSer.findByCis(dto);
        return projectBasicInfoList.size();
    }

    /**
     * 计算事项合作项目数量
     *
     * @param area
     * @return
     */
    private Integer countMatterProjectNo(String area) throws SerException {
        ProjectBasicInfoDTO dto = new ProjectBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("cooperationType", 1));
        List<ProjectBasicInfo> projectBasicInfoList = projectBasicInfoSer.findByCis(dto);
        return projectBasicInfoList.size();
    }

    /**
     * 计算中介合作项目数量
     *
     * @param area
     * @return
     */
    private Integer countIntermediaryProjectNo(String area) throws SerException {
        ProjectBasicInfoDTO dto = new ProjectBasicInfoDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("cooperationType", 2));
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
        MultipleProjectMultipleUIBDTO dto = new MultipleProjectMultipleUIBDTO();

        for (String projectName : projectNameSet) {
            for (MultipleProjectMultipleUI mmUI : mMList) {
                dto.getConditions().add(Restrict.eq("multipleProjectMultipleUI.id",mmUI.getId()));
                List<MultipleProjectMultipleUIB> mMBList = multipleProjectMultipleUIBSer.findByCis(dto);
                for (MultipleProjectMultipleUIB mmBUI : mMBList) {
                    if (projectName.equals(mmUI.getProjectName())) {
                        Double mmProjectProfit = mmBUI.getProfit();//获取多项目多界面的利润
                        mMProjectProfitTotal += mmProjectProfit;
                    }
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
        SingleProjectMultipleUIBDTO dto = new SingleProjectMultipleUIBDTO();
        for (String projectName : projectNameSet) {
            for (SingleProjectMultipleUI smUI : smList) {
                dto.getConditions().add(Restrict.eq("singleProjectMultipleUI.id",smUI.getId()));
                List<SingleProjectMultipleUIB> smBList = singleProjectMultipleUIBSer.findByCis(dto);
                for (SingleProjectMultipleUIB smUIB : smBList) {
                    if (projectName.equals(smUI.getProjectName())) {
                        Double smProjectProfit = smUIB.getProfit();//获取单项目多界面的利润
                        smProjectProfitTotal += smProjectProfit;
                    }
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

    /**
     * 计算盈利项目数量
     *
     * @return 盈利项目数量
     */
    private Integer countWinProNo(String area, Integer value) throws SerException {
        ProjectOtherDemandDTO projectOtherDemandDTO = new ProjectOtherDemandDTO();
        projectOtherDemandDTO.setArea(area);
        List<ProjectEvaluateResultBO> projectEvaluateResultBOList = projectOtherDemandSer.findEvaluateResult(projectOtherDemandDTO);
        Integer countWin = 0;
        Integer countDeficit = 0;
        if (projectEvaluateResultBOList != null && projectEvaluateResultBOList.size() > 0) {
            for (ProjectEvaluateResultBO str : projectEvaluateResultBOList) {
                Double profit = str.getProfit() == null ? 0d : str.getProfit();
                if (profit > 0) {
                    countWin++;
                } else {
                    countDeficit++;
                }
            }
        }
        return value == 1 ? countWin : countDeficit;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guildPermission();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
            case SEE:
                flag = guildPermission();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public ProjectMeasureSummaryBO getOne(String id) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空哦");
        }
        ProjectMeasureSummary projectMeasureSummary = super.findById(id);
        return BeanTransform.copyProperties(projectMeasureSummary, ProjectMeasureSummaryBO.class);
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
        List<ProjectMeasureSummary> summaryEmails = new ArrayList<>();
        List<ProjectMeasureSummary> allEmails = new ArrayList<>();
        //检测有哪些需要发送的
        //上次发送时间
        //现在时间
        //发送间隔
        //发送单位
        //发送类型
        //发送对象
        ProjectMeasureSummaryDTO dto = new ProjectMeasureSummaryDTO();
        dto.getConditions().add(Restrict.eq("status", Status.THAW));
        List<ProjectMeasureSummary> list = super.findByCis(dto);
        LocalDateTime nowTime = LocalDateTime.now();
        for (ProjectMeasureSummary str : list) {
            //上次发送时间
            LocalDateTime lastTime = str.getLastTime();
            //发送间隔
            Double sendNum = str.getSendInterval();
            //发送单位
            CycleType cycle = str.getCycle();
            //发送对象;隔开
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
                        str.setLastTime(lastTime.plusMinutes(sendNum.longValue()));
                    }
                    break;
                case HOUR:
                    temp_sendNum = sendNum * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusHours(sendNum.longValue()));
                    }
                    break;
                case DAY:
                    temp_sendNum = sendNum * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusDays(sendNum.longValue()));
                    }
                    break;
                case WEEK:
                    temp_sendNum = sendNum * 7 * 24 * 60 * 60 * 1000;
                    if (temp_sendNum <= mis.doubleValue()) {
                        flag = true;
                        str.setLastTime(lastTime.plusWeeks(sendNum.longValue()));
                    }
                    break;
                case MONTH:
                    if (nowTime.minusMonths(sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusMonths(sendNum.longValue()));
                    }
                    break;
                case QUARTER:
                    if (nowTime.minusMonths(3 * sendNum.longValue()).isEqual(lastTime) || nowTime.minusMonths(3 * sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusMonths(3 * sendNum.longValue()));
                    }
                    break;
                case YEAR:
                    if (nowTime.minusYears(sendNum.longValue()).isEqual(lastTime) || nowTime.minusYears(sendNum.longValue()).isAfter(lastTime)) {
                        flag = true;
                        str.setLastTime(lastTime.plusYears(sendNum.longValue()));
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

    private String htmlSummary(List<ProjectMeasureBO> summaryBOList) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (summaryBOList != null && summaryBOList.size() > 0) {
            sb = new StringBuffer("<h4>项目测算汇总:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
            ProjectMeasureBO title = summaryBOList.get(summaryBOList.size() - 1);
            sb.append("<tr>");
            sb.append("<td>地区</td>");
            sb.append("<td>项目数量</td>");
            sb.append("<td>项目利润</td>");
            sb.append("<td>多项目多界面项目数量</td>");
            sb.append("<td>多项目单界面项目数量</td>");
            sb.append("<td>单项目多界面项目数量</td>");
            sb.append("<td>多项目多界面项目数量</td>");
            sb.append("<td>长期合作项目数量</td>");
            sb.append("<td>事项合作项目</td>");
            sb.append("<td>中介合作项目</td>");
            sb.append("<td>盈利项目的数量</td>");
            sb.append("<td>亏损项目的数量</td>");

            sb.append("<tr>");

            //拼body部分
            for (ProjectMeasureBO bo : summaryBOList) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getArea() + "</td>");
                sb.append("<td>" + bo.getProjectCount() + "</td>");
                sb.append("<td>" + bo.getProjectProfit() + "</td>");
                sb.append("<td>" + bo.getMmProjectCount() + "</td>");
                sb.append("<td>" + bo.getMsProjectCount() + "</td>");
                sb.append("<td>" + bo.getSmProjectCount() + "</td>");
                sb.append("<td>" + bo.getSsProjectCount() + "</td>");
                sb.append("<td>" + bo.getLongTermProjectCount() + "</td>");
                sb.append("<td>" + bo.getMatterProjectCount() + "</td>");
                sb.append("<td>" + bo.getAgencyProjectCount() + "</td>");
                sb.append("<td>" + bo.getTestProfitProjectCount() + "</td>");
                sb.append("<td>" + bo.getTestDeficitProjectCount() + "</td>");

                sb.append("<tr>");
            }

            //结束
            sb.append("</table>");
        }
        return sb.toString();
    }


    private List<ProjectMeasureSummary> sendObject(List<ProjectMeasureSummary> summaryEmails) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        List<ProjectMeasureSummary> allEmails = new ArrayList<>();
        //项目测算汇总

        if (summaryEmails != null && summaryEmails.size() > 0) {
            for (ProjectMeasureSummary sign : summaryEmails) {

                String area = sign.getAreas();
                String[] condis = area.split(",");
                List<ProjectMeasureBO> measureBOList = projectMeasureSummarySer.summarize(condis);
                //拼表格
                String content = htmlSummary(measureBOList);

                MessageTO messageTO = new MessageTO();
                messageTO.setContent(content);
                messageTO.setTitle("项目测算汇总");
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
