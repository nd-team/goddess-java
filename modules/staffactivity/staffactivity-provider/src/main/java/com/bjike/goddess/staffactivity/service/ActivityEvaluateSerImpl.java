package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateBO;
import com.bjike.goddess.staffactivity.bo.ActivityEvaluateSummaryBO;
import com.bjike.goddess.staffactivity.bo.EvaluateScoreSummaryBO;
import com.bjike.goddess.staffactivity.dto.ActivityEvaluateDTO;
import com.bjike.goddess.staffactivity.entity.ActivityEvaluate;
import com.bjike.goddess.staffactivity.enums.GuideAddrStatus;
import com.bjike.goddess.staffactivity.to.ActivityEvaluateTO;
import com.bjike.goddess.staffactivity.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 活动评价业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 09:23 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivityEvaluateSerImpl extends ServiceImpl<ActivityEvaluate, ActivityEvaluateDTO> implements ActivityEvaluateSer {
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
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 分页查询活动评价
     *
     * @param dto 活动评价dto
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    @Override
    public List<ActivityEvaluateBO> list(ActivityEvaluateDTO dto) throws SerException {
        checkSeeIdentity();
        List<ActivityEvaluate> list = super.findByPage(dto);
        List<ActivityEvaluateBO> listBO = BeanTransform.copyProperties(list, ActivityEvaluateBO.class);
        return listBO;
    }

    /**
     * 保存活动评价
     *
     * @param to 活动评价to
     * @return class ActivityEvaluateBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ActivityEvaluateBO save(ActivityEvaluateTO to) throws SerException {
        checkAddIdentity();
        ActivityEvaluate entity = BeanTransform.copyProperties(to, ActivityEvaluate.class, true);
        entity = super.save(entity);
        ActivityEvaluateBO bo = BeanTransform.copyProperties(entity, ActivityEvaluateBO.class);
        return bo;
    }

    /**
     * 根据id删除活动评价
     *
     * @param id 活动评价唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    /**
     * 更新活动评价
     *
     * @param to 活动评价to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ActivityEvaluateTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityEvaluate model = super.findById(to.getId());
            if (model != null) {
                updateActivityEvaluate(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动评价
     *
     * @param to    活动评价to
     * @param model 活动评价
     * @throws SerException
     */
    private void updateActivityEvaluate(ActivityEvaluateTO to, ActivityEvaluate model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 活动评价得分汇总
     *
     * @param dto dto
     * @return class EvaluateScoreSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<EvaluateScoreSummaryBO> evaluateScoreSummary(ActivityEvaluateDTO dto) throws SerException {
        checkSeeIdentity();
        String[] schemes = dto.getSchemes();
        List<EvaluateScoreSummaryBO> boList = new ArrayList<>();
        for (String scheme : schemes) {
            List<ActivityEvaluate> list = getActivityEvaluateByScheme(scheme);
            countScoreSummary(boList, scheme, list);//汇总评价分数
        }
        return boList;
    }

    /**
     * 获得评价分数汇总
     *
     * @param boList
     * @param scheme
     * @param list
     */
    private void countScoreSummary(List<EvaluateScoreSummaryBO> boList, String scheme, List<ActivityEvaluate> list) {
        Boolean isListNotEmpty = (list != null) && (list.size() > 0);
        if (isListNotEmpty) {
            Integer totalScore = list.stream().mapToInt(c -> c.getActivityScore()).sum();
            String ratio = countOrganizerRatio(list);
            EvaluateScoreSummaryBO bo = new EvaluateScoreSummaryBO();
            bo.setScheme(scheme);
            bo.setTotalScores(totalScore);
            bo.setOrganizerRatio(ratio);
            boList.add(bo);
        }
    }

    /**
     * 根据方案名称查询活动评价
     *
     * @param scheme 活动方案名称
     * @return 活动评价集合
     * @throws SerException
     */
    private List<ActivityEvaluate> getActivityEvaluateByScheme(String scheme) throws SerException {
        ActivityEvaluateDTO dto = new ActivityEvaluateDTO();
        dto.getConditions().add(Restrict.in("scheme", scheme));
        return super.findByCis(dto);
    }

    /**
     * 计算组织者评分的比例
     *
     * @param list 活动评价list
     * @return
     */
    private String countOrganizerRatio(List<ActivityEvaluate> list) {
        Integer totalScore = list.stream().mapToInt(c -> c.getActivityScore()).sum();
        String ratio = "0";
        for (ActivityEvaluate model : list) {
            if ("组织者".equals(model.getAttendRole())) {
                Double organizarScore = new Double(model.getActivityScore());
                ratio = new DecimalFormat("#.00").format(organizarScore);
                break;
            }
        }
        return ratio + "%";
    }

    /**
     * 各活动评估汇总
     *
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @return class ActivityEvaluateSummaryBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ActivityEvaluateSummaryBO> evaluateSummary(String startDate, String endDate) throws SerException {
        checkSeeIdentity();
        LocalDate beginDate = DateUtil.parseDate(startDate);//起始日期
        LocalDate finishDate = DateUtil.parseDate(endDate);//结束日期
        LocalDate[] evaluateDate = new LocalDate[]{beginDate, finishDate};
        ActivityEvaluateDTO dto = new ActivityEvaluateDTO();
        dto.getConditions().add(Restrict.between("evaluateDate", evaluateDate));
        // TODO: 17-4-11
        return null;
    }

    @Override
    public Set<String> allActivityScheme() throws SerException {
        List<ActivityEvaluate> list = super.findAll();
        Set<String> set = new HashSet<>();
        for (ActivityEvaluate a : list) {
            set.add(a.getScheme());
        }
        return set;
    }
}