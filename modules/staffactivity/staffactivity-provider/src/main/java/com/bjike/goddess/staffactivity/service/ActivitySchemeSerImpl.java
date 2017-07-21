package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;
import com.bjike.goddess.staffactivity.enums.GuideAddrStatus;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 活动方案业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivitySchemeSerImpl extends ServiceImpl<ActivityScheme, ActivitySchemeDTO> implements ActivitySchemeSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
     * 总经办
     */
    private void checkManIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是总经办，不可以操作");
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

    /**
     * 总经办
     *
     * @return
     * @throws SerException
     */
    private Boolean guideManIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
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
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMan = guideManIdentity();
        if (flagSee || flagAdd || flagMan) {
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
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideAddIdentity();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity();
                break;
            case SEEFILE:
                flag = guideSeeIdentity();
                break;
            case JOIN:
                flag = guideAddIdentity();
                break;
            case EXIT:
                flag = guideAddIdentity();
                break;
            case BUSINESSADVICE:
                flag = guideAddIdentity();
                break;
            case CARRY:
                flag = guideAddIdentity();
                break;
            case FUNDEVALUATE:
                flag = guideAddIdentity();
                break;
            case OVERSEERVALUATE:
                flag = guideAddIdentity();
                break;
            case MANAGERADVICE:
                flag = guideManIdentity();
                break;
            case MANAGERVALUATE:
                flag = guideManIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询活动方案
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    public List<ActivitySchemeBO> list(ActivitySchemeDTO dto) throws SerException {
        checkSeeIdentity();
        List<ActivityScheme> list = super.findByPage(dto);
        List<ActivitySchemeBO> listBO = BeanTransform.copyProperties(list, ActivitySchemeBO.class);
        return listBO;
    }

    /**
     * 保存活动方案
     *
     * @param to 活动方案to
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ActivitySchemeBO save(ActivitySchemeTO to) throws SerException {
        checkAddIdentity();
        ActivityScheme entity = BeanTransform.copyProperties(to, ActivityScheme.class, true);
        entity = super.save(entity);
        ActivitySchemeBO bo = BeanTransform.copyProperties(entity, ActivitySchemeBO.class);
        return bo;
    }

    /**
     * 根据id删除活动方案
     *
     * @param id 活动方案唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    /**
     * 更新活动方案
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ActivitySchemeTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityScheme model = super.findById(to.getId());
            if (model != null) {
                updateActivityScheme(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动方案
     *
     * @param to    活动方案to
     * @param model 活动方案
     * @throws SerException
     */
    private void updateActivityScheme(ActivitySchemeTO to, ActivityScheme model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 运营商务部意见
     *
     * @param id        活动方案id
     * @param yYOpinion 运营商务部意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void yYOpinion(String id, String yYOpinion) throws SerException {
        checkAddIdentity();
        ActivityScheme model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setYYOpinion(yYOpinion);
        super.update(model);
    }

    /**
     * 总经办意见
     *
     * @param id           活动方案唯一标识
     * @param ifSchemePass 方案是否通过
     * @param zjbOpinion   总经办意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void zjbOpinion(String id, Boolean ifSchemePass, String zjbOpinion) throws SerException {
        checkManIdentity();
        ActivityScheme model = super.findById(id);
        model.setIfSchemePass(ifSchemePass);
        model.setZjbOpinion(zjbOpinion);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws SerException
     */
    /**
     * 是否持续开展
     *
     * @param id               活动方案唯一标识
     * @param ifNeedContinue   是否有必要持续开展
     * @param reasonAndOpinion 原因及意见
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void ifContinueLaunch(String id, Boolean ifNeedContinue, String reasonAndOpinion) throws SerException {
        checkAddIdentity();
        ActivityScheme model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setIfNeedContinue(ifNeedContinue);
        model.setReasonAndOpinion(reasonAndOpinion);
        super.update(model);
    }

    /**
     * 运营资金评价
     *
     * @param id                    活动方案唯一标识
     * @param ifTotalOutlayRational 活动总支出是否合理
     * @param fundProposal          经费建议
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void yYFundEvaluate(String id, Boolean ifTotalOutlayRational, String fundProposal) throws SerException {
        checkAddIdentity();
        ActivityScheme model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setIfTotalOutlayRational(ifTotalOutlayRational);
        model.setFundProposal(fundProposal);
        super.update(model);
    }

    /**
     * 监督者评价
     *
     * @param id           活动方案id
     * @param ifFlowDefect 活动流程是否存在缺陷
     * @param flowProposal 活动流程建议
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void supervisorEvaluate(String id, Boolean ifFlowDefect, String flowProposal) throws SerException {
        checkAddIdentity();
        ActivityScheme model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setIfFlowDefect(ifFlowDefect);
        model.setFlowProposal(flowProposal);
        super.update(model);
    }

    /**
     * 总经办评价
     *
     * @param id             活动方案唯一标识
     * @param activityEffect 活动效应
     * @param zjbEvaluate    总经办评价及建议
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void zjbEvaluate(String id, String activityEffect, String zjbEvaluate) throws SerException {
        checkManIdentity();
        ActivityScheme model = super.findById(id);
        model.setModifyTime(LocalDateTime.now());
        model.setActivityEffect(activityEffect);
        model.setZjbEvaluate(zjbEvaluate);
        super.update(model);
    }

    /**
     * 公司各地区活动经费汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ActivityFundSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ActivityFundSummaryBO> activityFundSummary(String startTime, String endTime) throws SerException {
        checkSeeIdentity();
        LocalDateTime beginTime = DateUtil.parseDateTime(startTime);
        LocalDateTime finishTime = DateUtil.parseDateTime(endTime);
        LocalDateTime[] activityTime = new LocalDateTime[]{beginTime, finishTime};
        String timeSlot = getTimeSlot(beginTime, finishTime);
        List<String> areas = findAllAreas();
        if (CollectionUtils.isEmpty(areas)) {
            return Collections.emptyList();
        }
        List<ActivityFundSummaryBO> boList = new ArrayList<>(0);
        for (String area : areas) {
            ActivitySchemeDTO dto = new ActivitySchemeDTO();
            dto.getConditions().add(Restrict.between("activityTime", activityTime));
            dto.getConditions().add(Restrict.eq("area", area));
            List<ActivityScheme> schemeList = super.findByCis(dto);
            Double activityFund = schemeList.stream().filter(c -> c.getTotalActivityFund() != null).mapToDouble(c -> c.getTotalActivityFund()).sum();
            ActivityFundSummaryBO bo = new ActivityFundSummaryBO();
            bo.setActivityTime(timeSlot);
            bo.setArea(area);
            bo.setActivityFund(activityFund);
            boList.add(bo);
        }

        //计算合计项
        countCombinedItem(timeSlot, boList);
        return boList;
    }

    private void countCombinedItem(String timeSlot, List<ActivityFundSummaryBO> boList) {
        Double totalFund = boList.stream().filter(c -> c.getActivityFund() != null).mapToDouble(c -> c.getActivityFund()).sum();
        ActivityFundSummaryBO bo = new ActivityFundSummaryBO();
        bo.setActivityTime(timeSlot);
        bo.setArea("合计");
        bo.setActivityFund(totalFund);
        boList.add(bo);
    }

    /**
     * 获取时间区间
     *
     * @param beginTime  起始时间
     * @param finishTime 结束时间
     * @return
     */
    private String getTimeSlot(LocalDateTime beginTime, LocalDateTime finishTime) {
        String beginTimeString = beginTime.toString().replace('T', ' ');
        String finishTimeString = finishTime.toString().replace('T', ' ');
        return new StringBuilder(beginTimeString).append("~").append(finishTimeString).toString();
    }


    /**
     * 获取活动方案中的所有地区
     *
     * @return 所有地区
     * @throws SerException
     */
    private List<String> findAllAreas() throws SerException {
        Set<String> areaSet = new HashSet<String>();
        List<ActivityScheme> schemeList = super.findAll();
        for (ActivityScheme scheme : schemeList) {
            if (StringUtils.isNotBlank(scheme.getArea())) {
                areaSet.add(scheme.getArea());
            }
        }
        List<String> areaList = new ArrayList<>(areaSet);
        return areaList;
    }

    @Override
    public Set<String> allTheme() throws SerException {
        List<ActivityScheme> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (ActivityScheme a : list) {
            set.add(a.getTheme());
        }
        return set;
    }
}