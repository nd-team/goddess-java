package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.event.enums.Permissions;
import com.bjike.goddess.event.to.EventTO;
import com.bjike.goddess.managepromotion.bo.SkillLevelCollectBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.bo.SkillPromotionDetailCollectABO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillLevelCollectTO;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 技能晋升申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillPromotionApplySerImpl extends ServiceImpl<SkillPromotionApply, SkillPromotionApplyDTO> implements SkillPromotionApplySer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private EventAPI eventAPI;

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
     * 核对模块负责人审核权限（模块级别）
     */
    private void checkHeadIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应模块负责人的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对预算模块审核权限（模块级别）
     */
    private void checkBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是相应预算模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对项目经理审核权限（岗位级别）
     */
    private void checkManagerIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是项目经理人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对规划模块审核权限（模块级别）
     */
    private void checkPlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是规划模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private void checkGeneralIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
            if (!flag) {
                throw new SerException("您不是总经办的人员，不可以操作");
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
        if (!"admin".equalsIgnoreCase(userName)) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对模块负责人审核权限（模块级别）
     */
    private Boolean guideHeadAuditIdentity() throws SerException {
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

    /**
     * 核对预算模块审核权限（模块级别）
     */
    private Boolean guideBudgetAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对项目经理审核权限（岗位级别）
     */
    private Boolean guideManagerAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对规划模块审核权限（模块级别）
     */
    private Boolean guidePlanAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private Boolean guideGeneralAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
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
        Boolean flagHead = guideHeadAuditIdentity();
        Boolean flagBudget = guideBudgetAuditIdentity();
        Boolean flagManager = guideManagerAuditIdentity();
        Boolean flagPlan = guidePlanAuditIdentity();
        Boolean flagGeneral = guideGeneralAuditIdentity();
        if (flagSee || flagAdd || flagHead || flagBudget || flagManager || flagPlan || flagGeneral) {
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
            case HEADAUDIT:
                flag = guideHeadAuditIdentity();
                break;
            case BUDGETAUDIT:
                flag = guideBudgetAuditIdentity();
                break;
            case MANAGERAUDIT:
                flag = guideManagerAuditIdentity();
                break;
            case PLANAUDIT:
                flag = guidePlanAuditIdentity();
                break;
            case GENERALAUDIT:
                flag = guideGeneralAuditIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;

            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long countSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        Long count = super.count(skillPromotionApplyDTO);
        return count;
    }

    @Override
    public SkillPromotionApplyBO getOne(String id) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(id);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public List<SkillPromotionApplyBO> findListSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        checkSeeIdentity();
        skillPromotionApplyDTO.getSorts().add("createTime=desc");
        List<SkillPromotionApply> skillPromotionApplies = super.findByPage(skillPromotionApplyDTO);
        List<SkillPromotionApplyBO> skillPromotionApplyBOS = BeanTransform.copyProperties(skillPromotionApplies, SkillPromotionApplyBO.class);
        return skillPromotionApplyBOS;
    }

    private Set<String> events(String name) throws SerException {
        Set<String> set = new HashSet<>();
        if (moduleAPI.isCheck("organize")) {
            List<PositionDetailBO> list = positionDetailUserAPI.getPositionDetail(name);
            List<PositionDetailBO> all = positionDetailAPI.findStatus();
            for (PositionDetailBO p : list) {
                String depart = p.getDepartmentName();
                for (PositionDetailBO p1 : all) {
                    boolean b = depart.equals(p1.getDepartmentName()) ? true : false;
                    boolean b1 = "决策层".equals(p1.getArrangementName()) && p1.getPosition().contains("项目经理") && Status.THAW.equals(p1.getStatus());
                    boolean b2 = "综合资源部".equals(p1.getDepartmentName()) && "规划模块".equals(p1.getModuleName()) && "规划模块负责人".equals(p1.getPosition()) && Status.THAW.equals(p1.getStatus());
                    boolean b3 = "财务运营部".equals(p1.getDepartmentName()) && "预算模块".equals(p1.getModuleName()) && "预算模块负责人".equals(p1.getPosition()) && Status.THAW.equals(p1.getStatus());
                    boolean b4 = "管理层".equals(p1.getArrangementName());
                    boolean b5 = "总经理".equals(p1.getPosition()) && Status.THAW.equals(p1.getStatus());
                    if ((b && b1) || (b && b4) || b2 || b3 || b5) {
                        List<UserBO> userBOs = positionDetailUserAPI.findByPosition(p1.getId());
                        for (UserBO userBO : userBOs) {
                            set.add(userBO.getUsername());
                        }
                    }
                }
            }
        }
        return set;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO insertSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkAddIdentity();
        SkillPromotionApply skillPromotionApply = BeanTransform.copyProperties(skillPromotionApplyTO, SkillPromotionApply.class, true);
        skillPromotionApply.setCreateTime(LocalDateTime.now());
        super.save(skillPromotionApply);
//        if (moduleAPI.isCheck("event")) {
//            for (String s : events(skillPromotionApply.getName())) {
//                EventTO eventTO = new EventTO();
//                eventTO.setProjectChineseName("管理等级晋升");
//                eventTO.setContent("管理等级晋升申请审核");
//                eventTO.setRequestTime(DateUtil.dateToString(LocalDateTime.now()));    //todo:要求处理时间不确定
//                eventTO.setName(s);
//                eventTO.setPermissions(Permissions.ADUIT);
//                eventTO.setEventId(skillPromotionApply.getId());
//                eventAPI.save(eventTO);
//            }
//        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO editSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkAddIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        BeanTransform.copyProperties(skillPromotionApplyTO, skillPromotionApply, true);
        skillPromotionApply.setModifyTime(LocalDateTime.now());
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillPromotionApplyBO headAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkHeadIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());

        skillPromotionApply.setHeadOpinion(skillPromotionApplyTO.getHeadOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillPromotionApplyBO budgetAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkBudgetIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        skillPromotionApply.setBudgetOpinion(skillPromotionApplyTO.getBudgetOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillPromotionApplyBO projectManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkManagerIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getHeadOpinion() == null;
        boolean b2 = skillPromotionApply.getBudgetOpinion() == null;
        if (b1 && b2) {
            throw new SerException("负责人或预算模块还未审核");
        } else {
            skillPromotionApply.setProjectManagerOpinion(skillPromotionApplyTO.getProjectManagerOpinion());
            skillPromotionApply.setPhase(2);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillPromotionApplyBO planAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkPlanIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getProjectManagerOpinion() == null;
        if (b1) {
            throw new SerException("项目经理还未审核");
        } else {
            skillPromotionApply.setPlanOpinion(skillPromotionApplyTO.getPlanOpinion());
            skillPromotionApply.setPhase(3);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillPromotionApplyBO generalManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        checkGeneralIdentity();
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getPlanOpinion() == null;
        if (b1) {
            throw new SerException("规划模块还未审核");
        } else {
            skillPromotionApply.setManagerOpinion(skillPromotionApplyTO.getManagerOpinion());
            skillPromotionApply.setPhase(4);
            skillPromotionApply.setPromotionTime(LocalDate.parse(skillPromotionApplyTO.getPromotionTime()));
            skillPromotionApply.setPass(skillPromotionApplyTO.getPass());
            skillPromotionApply.setAuditStatus(skillPromotionApplyTO.getAuditStatus());
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public List<SkillLevelCollectBO> dayLevelCollect(SkillLevelCollectTO to) throws SerException {
        LocalDate time = null;
        if (to.getTime() != null) {
            time = DateUtil.parseDate(to.getTime());
        } else {
            time = LocalDate.now();
        }
        List<SkillLevelCollectBO> boList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ( ");
        sb.append(" SELECT A.*,B.pass,B.unpass,C.result,C.unresult FROM ");
        sb.append(" (SELECT area,department, ");
        sb.append(" max(CASE WHEN dealStatus=0 THEN dealStatusCount END )AS receiving, ");
        sb.append(" max(CASE WHEN dealStatus=1 THEN dealStatusCount END )AS untreated, ");
        sb.append(" max(CASE WHEN dealStatus=2 THEN dealStatusCount END )AS completedProcessing FROM ");
        sb.append(" (SELECT count(*) as dealStatusCount, dealStatus as dealStatus, area as area,department AS department ");
        sb.append(" FROM managepromotion_skillpromotionapply a WHERE acquisitionTime='" + time + "' ");
        sb.append(" GROUP BY area,department,dealStatus)a ");
        sb.append(" GROUP BY area,department)A, ");
        sb.append(" (SELECT area,department, ");
        sb.append(" max(CASE WHEN pass=0 THEN passCount END )AS unpass, ");
        sb.append(" max(CASE WHEN pass=1 THEN passCount END )AS pass FROM ");
        sb.append(" (SELECT count(*) as passCount, is_pass as pass, area as area,department AS department ");
        sb.append(" FROM managepromotion_skillpromotionapply a WHERE acquisitionTime='" + time + "' ");
        sb.append(" GROUP BY area,department,is_pass)a ");
        sb.append(" GROUP BY area,department)B, ");
        sb.append(" (SELECT area,department, ");
        sb.append(" max(CASE WHEN result=0 THEN resultCount END )AS unresult, ");
        sb.append(" max(CASE WHEN result=1 THEN resultCount END )AS result FROM ");
        sb.append(" (SELECT count(*) as resultCount, is_result as result , area as area,department AS department ");
        sb.append(" FROM managepromotion_skillpromotionapply a WHERE acquisitionTime='" + time + "' ");
        sb.append(" GROUP BY area,department,is_result)a ");
        sb.append(" GROUP BY area,department)C ");
        sb.append(" WHERE A.area = B.area AND A.area = C.area AND A.department = B.department AND A.department = C.department)D ");
        String sql = sb.toString();
        String[] fields = new String[]{"area", "department", "receving", "untreated", "completedProcessing",
                "pass", "unpass", "result", "unresult"};
        List<SkillLevelCollectBO> skillLevelCollectBOS = super.findBySql(sql, SkillLevelCollectBO.class, fields);
        //todo 以下表头需求方只说了大的模块，没有具体到哪个模块哪张表，要等到时候再问需求方
        //todo 公司发展需求人数 （人员编制）
        //todo 月收入（元） (财务运营部预算模块)
        //TODO 月人工成本 (财务运营部预算模块)
        //todo 差异（月收入（元）-月人工成本）
        return skillLevelCollectBOS;
    }

    @Override
    public List<SkillLevelCollectBO> weekLevelCollect(SkillLevelCollectTO to) throws SerException {
        List<SkillPromotionDetailCollectABO> boList = new ArrayList<>();
        LocalDate[] time = null;
        Integer year = to.getYear();
        Integer month = to.getMonth();
        Integer week = to.getWeek();
        if (year != null && month != null && week != null) {
            time = DateUtil.getWeekTimes(year, month, week);
        } else {
            String dateString = DateUtil.dateToString(LocalDate.now());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
            time = DateUtil.getWeekTimes(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), weekOfMonth);
        }
        LocalDate start = time[0];
        LocalDate end = time[1];
        return null;
    }

    @Override
    public List<SkillLevelCollectBO> monthLevelCollect(SkillLevelCollectTO to) throws SerException {
        Integer year = 0;
        Integer month = 0;
        if (to.getYear() != null && to.getMonth() != null) {
            year = to.getYear();
            month = to.getMonth();
        } else {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        return null;
    }

    @Override
    public List<SkillLevelCollectBO> totalLevelCollect(SkillLevelCollectTO to) throws SerException {
        LocalDate end = null;
        if (to.getTime() != null) {
            end = DateUtil.parseDate(to.getTime());
        } else {
            end = LocalDate.now();
        }
        return null;
    }

    //获取所有地区
    private List<String> levelAreas() throws SerException {
        String[] fields = new String[]{"area"};
        String sql = " SELECT area AS  area FROM managepromotion_skillpromotionapply GROUP BY area ";
        List<SkillPromotionApply> skillPromotionApplies = super.findBySql(sql, SkillPromotionApply.class, fields);
        List<String> areas = skillPromotionApplies.stream().map(SkillPromotionApply::getArea).collect(Collectors.toList());
        return areas;
    }

    //获取所有项目组/部门
    private List<String> levelDepartments() throws SerException {
        String[] fields = new String[]{"department"};
        String sql = " SELECT department AS  department FROM managepromotion_skillpromotionapply GROUP BY department ";
        List<SkillPromotionApply> skillPromotionApplies = super.findBySql(sql, SkillPromotionApply.class, fields);
        List<String> departments = skillPromotionApplies.stream().map(SkillPromotionApply::getDepartment).collect(Collectors.toList());
        return departments;
    }

    @Override
    public Integer monthPromotedNum(SkillLevelCollectTO to) throws SerException {
        Integer year = to.getYear();
        Integer month = to.getMonth();
        if (null == year && null == month) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return promotedNum(startDate, endDate);
    }

    @Override
    public Integer quartPromotedNum(SkillLevelCollectTO to) throws SerException {
        Integer year = to.getYear();
        Integer quart = to.getQuart();
        if (year == null && quart == null) {
            year = LocalDate.now().getYear();
            quart = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quart);
        String startDate = date[0];
        String endDate = date[1];
        return promotedNum(startDate, endDate);
    }

    @Override
    public Integer yearPromotedNum(SkillLevelCollectTO to) throws SerException {
        Integer year = to.getYear();
        if (null == year) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return promotedNum(startDate, endDate);
    }

    private Integer promotedNum(String startDate, String endDate) throws SerException {
        UserBO userBO = userAPI.currentUser();
        String sql = " SELECT ifnull(sum(promotedNumber),0) AS promotedNumber FROM managepromotion_skillpromotionapply WHERE name='" + userBO.getUsername() + "' AND promotionTime BETWEEN '" + startDate + "' AND '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        Integer nums = 0;
        if (objects != null && objects.size() > 0) {
            nums = Integer.valueOf(String.valueOf(objects.get(0)));
        }
        return nums;
    }

    //季度
    private String[] quarter(Integer year, Integer quarter) throws SerException {
        String startDate = null;
        String endDate = null;
        switch (quarter) {
            case 1:
                startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 3, DateUtil.getDayByDate(year, 3)));
                break;
            case 2:
                startDate = DateUtil.dateToString(LocalDate.of(year, 4, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 6, DateUtil.getDayByDate(year, 6)));
                break;
            case 3:
                startDate = DateUtil.dateToString(LocalDate.of(year, 7, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 9, DateUtil.getDayByDate(year, 9)));
                break;
            case 4:
                startDate = DateUtil.dateToString(LocalDate.of(year, 10, 1));
                endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
                break;
            default:
                startDate = DateUtil.dateToString(LocalDate.now());
                endDate = DateUtil.dateToString(LocalDate.now());
                break;
        }

        return new String[]{startDate, endDate};
    }


}