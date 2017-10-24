package com.bjike.goddess.managementpromotion.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.event.api.EventAPI;
import com.bjike.goddess.managementpromotion.api.LevelDesignAPI;
import com.bjike.goddess.managementpromotion.api.LevelShowAPI;
import com.bjike.goddess.managementpromotion.bo.LevelShowBO;
import com.bjike.goddess.managementpromotion.bo.PromotionApplyBO;
import com.bjike.goddess.managementpromotion.dto.LevelShowDTO;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.entity.PromotionApply;
import com.bjike.goddess.managementpromotion.enums.GuideAddrStatus;
import com.bjike.goddess.managementpromotion.enums.ManagerOpinion;
import com.bjike.goddess.managementpromotion.to.GuidePermissionTO;
import com.bjike.goddess.managementpromotion.to.LevelShowTO;
import com.bjike.goddess.managementpromotion.to.PromotionApplyTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.organize.api.PositionDetailAPI;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.regularization.api.RegularizationAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;

//import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;

/**
 * 管理等级晋升申请业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-22 02:04 ]
 * @Description: [ 管理等级晋升申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managementpromotionSerCache")
@Service
public class PromotionApplySerImpl extends ServiceImpl<PromotionApply, PromotionApplyDTO> implements PromotionApplySer {
    @Autowired
    private LevelDesignAPI levelDesignAPI;
    @Autowired
    private LevelShowAPI levelShowAPI;
    @Autowired
    private MessageAPI messageAPI;
    //    @Autowired
//    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private RegularizationAPI regularizationAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private PositionDetailAPI positionDetailAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;
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
     * 规划模块
     *
     * @throws SerException
     */
    private void checkGuiHuaIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是规划模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 素养模块
     *
     * @throws SerException
     */
    private void checkSuYangIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是素养模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 项目经理审核
     *
     * @throws SerException
     */
    private void checkXMJIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是项目经理，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 运营商务部预算模块
     *
     * @throws SerException
     */
    private void checkBuinessIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("6");
            if (!flag) {
                throw new SerException("您不是运营商务部预算模块的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 模块负责人
     *
     * @throws SerException
     */
    private void checkModuleFZIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("7");
            if (!flag) {
                throw new SerException("您不是模块负责人，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 总经理
     *
     * @throws SerException
     */
    private void checkManIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("8");
            if (!flag) {
                throw new SerException("您不是总经理，不可以操作");
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
     * 规划模块
     *
     * @return
     * @throws SerException
     */
    private Boolean guideGuiHuaIdentity() throws SerException {
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
     * 素养模块
     *
     * @return
     * @throws SerException
     */
    private Boolean guideSuYangIdentity() throws SerException {
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
     * 项目经理审核
     *
     * @return
     * @throws SerException
     */
    private Boolean guideXMJIdentity() throws SerException {
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
     * 运营商务部预算模块
     *
     * @return
     * @throws SerException
     */
    private Boolean guideBuinessIdentity() throws SerException {
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
     * 模块负责人
     *
     * @return
     * @throws SerException
     */
    private Boolean guideModuleFZIdentity() throws SerException {
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

    /**
     * 总经理
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
            flag = cusPermissionSer.busCusPermission("8");
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
        Boolean flagGH = guideGuiHuaIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSY = guideSuYangIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagXMJ = guideXMJIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagBuiness = guideBuinessIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagModuleFZ = guideModuleFZIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMan = guideManIdentity();
        if (flagSee || flagAdd || flagGH || flagSY || flagXMJ || flagBuiness || flagModuleFZ || flagMan) {
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
            case GUIHUA:
                flag = guideGuiHuaIdentity();
                break;
            case SUYANG:
                flag = guideSuYangIdentity();
                break;
            case MANAGER:
                flag = guideXMJIdentity();
                break;
            case BUINESS:
                flag = guideBuinessIdentity();
                break;
            case MODULE:
                flag = guideModuleFZIdentity();
                break;
            case BOSS:
                flag = guideManIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    private Set<String> events(String name) throws SerException {
        Set<String> set = new HashSet<>();
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list = positionDetailUserAPI.getPositionDetail(name);
            RpcTransmit.transmitUserToken(token);
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
                        RpcTransmit.transmitUserToken(token);
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

//    @Override
//    @Transactional(rollbackFor = {SerException.class})
//    public PromotionApplyBO save(PromotionApplyTO to) throws SerException {
//        checkAddIdentity();
//        PromotionApply entity = BeanTransform.copyProperties(to, PromotionApply.class, true);
//        String token = RpcTransmit.getUserToken();
//        if (moduleAPI.isCheck("staffentry")) {
//            RpcTransmit.transmitUserToken(token);
//            List<EntryBasicInfoBO> list = entryBasicInfoAPI.getByEmpNumber(to.getEmployeeId());
//            if ((list != null) && (list.size() != 0)) {
//                LocalDate time = DateUtil.parseDate(list.get(0).getEntryTime());
//                int entryYear = time.getYear();
//                int entryMonth = time.getMonthValue();
//                int year = LocalDate.now().getYear();
//                int month = LocalDate.now().getMonthValue();
//                if (month - entryMonth >= 0) {
//                    if (year - entryYear > 0) {
//                        double d = (year - entryYear) * 12 + (month - entryMonth);
//                        entity.setWorkAge(d);
//                    } else {
//                        entity.setWorkAge((double) (month - entryMonth));
//                    }
//                } else {
//                    double d = (year - entryYear - 1) * 12 + (12 - entryMonth + month);
//                    entity.setWorkAge(d);
//                }
//            }
//        }
//        if (moduleAPI.isCheck("regularization")) {
//            RpcTransmit.transmitUserToken(token);
//            String time = regularizationAPI.time(to.getEmployeeId());
//            if (null != time) {
//                LocalDate date = DateUtil.parseDate(time);
//                entity.setPositiveDate(date);
//            }
//        }
//        super.save(entity);
//        if (moduleAPI.isCheck("event")) {
//            for (String s : events(entity.getName())) {
//                EventTO eventTO = new EventTO();
//                eventTO.setProject("管理等级晋升");
//                eventTO.setContent("管理等级晋升申请审核");
//                eventTO.setRequestTime(DateUtil.dateToString(LocalDateTime.now()));    //todo:要求处理时间不确定
//                eventTO.setName(s);
//                eventTO.setPermissions(Permissions.ADUIT);
//                eventTO.setEventId(entity.getId());
//                RpcTransmit.transmitUserToken(token);
//                eventAPI.save(eventTO);
//            }
//        }
//        return BeanTransform.copyProperties(entity, PromotionApplyBO.class);
//    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        PromotionApply p = super.findById(id);
        if (p == null) {
            throw new SerException("对象不存在!");
        }
        boolean b1 = p.getIsConform() == null;
        boolean b2 = p.getProjectManagerOpinion() == null;
        boolean b3 = p.getResourceDepartmentOpinion() == null;
        boolean b4 = p.getCommerceDepartmentOpinion() == null;
        boolean b5 = p.getModulerOpinion() == null;
        boolean b6 = p.getManagerOpinion() == null;
        if (b1 && b2 && b3 && b4 && b5 && b6) {
            super.remove(id);
        } else {
            throw new SerException("已审核，不能删除");
        }
    }

//    @Override
//    @Transactional(rollbackFor = {SerException.class})
//    public void edit(PromotionApplyTO to) throws SerException {
//        checkAddIdentity();
//        PromotionApply entity = super.findById(to.getId());
//        if (entity == null) {
//            throw new SerException("对象不存在!");
//        }
//        LocalDateTime a = entity.getCreateTime();
//        boolean b1 = entity.getIsConform() == null;
//        boolean b2 = entity.getProjectManagerOpinion() == null;
//        boolean b3 = entity.getResourceDepartmentOpinion() == null;
//        boolean b4 = entity.getCommerceDepartmentOpinion() == null;
//        boolean b5 = entity.getModulerOpinion() == null;
//        boolean b6 = entity.getManagerOpinion() == null;
//        if (b1 && b2 && b3 && b4 && b5 && b6) {
//            entity = BeanTransform.copyProperties(to, PromotionApply.class, true);
//            String token = RpcTransmit.getUserToken();
//            if (moduleAPI.isCheck("staffentry")) {
//                RpcTransmit.transmitUserToken(token);
//                List<EntryBasicInfoBO> list = entryBasicInfoAPI.getByEmpNumber(to.getEmployeeId());
//                if ((list != null) && (list.size() != 0)) {
//                    LocalDate time = DateUtil.parseDate(list.get(0).getEntryTime());
//                    int entryYear = time.getYear();
//                    int entryMonth = time.getMonthValue();
//                    int year = LocalDate.now().getYear();
//                    int month = LocalDate.now().getMonthValue();
//                    if (month - entryMonth >= 0) {
//                        if (year - entryYear > 0) {
//                            double d = (year - entryYear) * 12 + (month - entryMonth);
//                            entity.setWorkAge(d);
//                        } else {
//                            entity.setWorkAge((double) (month - entryMonth));
//                        }
//                    } else {
//                        double d = (year - entryYear - 1) * 12 + (12 - entryMonth + month);
//                        entity.setWorkAge(d);
//                    }
//                }
//            }
//            if (moduleAPI.isCheck("regularization")) {
//                RpcTransmit.transmitUserToken(token);
//                String time = regularizationAPI.time(to.getEmployeeId());
//                if (null != time) {
//                    LocalDate date = DateUtil.parseDate(time);
//                    entity.setPositiveDate(date);
//                }
//            }
//            entity.setCreateTime(a);
//            entity.setModifyTime(LocalDateTime.now());
//            super.update(entity);
//        } else {
//            throw new SerException("已审核，不能编辑");
//        }
//    }

    @Override
    public List<PromotionApplyBO> find(PromotionApplyDTO dto) throws SerException {
        UserBO userBO = userAPI.currentUser();
        if (("admin".equals(userBO.getUsername().toLowerCase()))) {
            List<PromotionApply> list = super.findByCis(dto, true);
            return BeanTransform.copyProperties(list, PromotionApplyBO.class);
        }
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailUserAPI.findPositionByUser(userBO.getId());
            for (PositionDetailBO p1 : list1) {
                String depart = p1.getDepartmentName();
                String module = p1.getModuleName();
                List<UserBO> users = modules(userBO.getUsername());
                Set<String> names = new HashSet<>();
                for (UserBO u : users) {
                    names.add(u.getUsername());
                }
                if (("综合资源部".equals(depart) && "规划模块".equals(module)) || ("运营商务部".equals(depart) && "预算模块".equals(module)) || names.contains(userBO.getUsername())) {
                    List<PromotionApply> list = super.findByCis(dto, true);
                    return BeanTransform.copyProperties(list, PromotionApplyBO.class);
                } else {
                    List<PromotionApplyBO> boList = new ArrayList<>();
                    dto.getConditions().add(Restrict.eq("name", userBO.getUsername()));
                    List<PromotionApply> list = super.findByCis(dto, true);
                    for (PromotionApply p : list) {
                        p.setProjectManagerOpinion(null);
                        p.setResourceDepartmentOpinion(null);
                        p.setCommerceDepartmentOpinion(null);
                        p.setModulerOpinion(null);
                        p.setManagerOpinion(null);
                        PromotionApplyBO bo = BeanTransform.copyProperties(p, PromotionApplyBO.class);
                        boList.add(bo);
                    }
                    return boList;
                }
            }
        }
        List<PromotionApplyBO> boList = new ArrayList<>();
        dto.getConditions().add(Restrict.eq("name", userBO.getUsername()));
        List<PromotionApply> list = super.findByCis(dto, true);
        for (PromotionApply p : list) {
            p.setProjectManagerOpinion(null);
            p.setResourceDepartmentOpinion(null);
            p.setCommerceDepartmentOpinion(null);
            p.setModulerOpinion(null);
            p.setManagerOpinion(null);
            PromotionApplyBO bo = BeanTransform.copyProperties(p, PromotionApplyBO.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public PromotionApplyBO findByID(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), PromotionApplyBO.class);
    }

    private List<PromotionApplyBO> find1(PromotionApplyDTO dto) throws SerException {
        String token = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        if (("admin".equals(userBO.getUsername().toLowerCase()))) {
            List<PromotionApply> list = super.findByCis(dto);
            return BeanTransform.copyProperties(list, PromotionApplyBO.class);
        }
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list1 = positionDetailUserAPI.findPositionByUser(userBO.getId());
            for (PositionDetailBO p1 : list1) {
                String depart = p1.getDepartmentName();
                String module = p1.getModuleName();
                List<UserBO> users = modules(userBO.getUsername());
                Set<String> names = new HashSet<>();
                for (UserBO u : users) {
                    names.add(u.getUsername());
                }
                if (("综合资源部".equals(depart) && "规划模块".equals(module)) || ("运营商务部".equals(depart) && "预算模块".equals(module)) || names.contains(userBO.getUsername())) {
                    List<PromotionApply> list = super.findByCis(dto);
                    return BeanTransform.copyProperties(list, PromotionApplyBO.class);
                } else {
                    List<PromotionApplyBO> boList = new ArrayList<>();
                    dto.getConditions().add(Restrict.eq("name", userBO.getUsername()));
                    List<PromotionApply> list = super.findByCis(dto);
                    for (PromotionApply p : list) {
                        p.setProjectManagerOpinion(null);
                        p.setResourceDepartmentOpinion(null);
                        p.setCommerceDepartmentOpinion(null);
                        p.setModulerOpinion(null);
                        p.setManagerOpinion(null);
                        PromotionApplyBO bo = BeanTransform.copyProperties(p, PromotionApplyBO.class);
                        boList.add(bo);
                    }
                    return boList;
                }
            }
        }
        List<PromotionApplyBO> boList = new ArrayList<>();
        dto.getConditions().add(Restrict.eq("name", userBO.getUsername()));
        List<PromotionApply> list = super.findByCis(dto);
        for (PromotionApply p : list) {
            p.setProjectManagerOpinion(null);
            p.setResourceDepartmentOpinion(null);
            p.setCommerceDepartmentOpinion(null);
            p.setModulerOpinion(null);
            p.setManagerOpinion(null);
            PromotionApplyBO bo = BeanTransform.copyProperties(p, PromotionApplyBO.class);
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public Long count(PromotionApplyDTO dto) throws SerException {
        String token=RpcTransmit.getUserToken();
        RpcTransmit.transmitUserToken(token);
        List<PromotionApplyBO> list = find1(dto);
        long sum = 0l;
        if (list != null) {
            for (PromotionApplyBO p : list) {
                sum++;
            }
        }
        return sum;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //规划填写是否符合晋升条件
    public void conform(PromotionApplyTO to) throws SerException {
        checkGuiHuaIdentity();
        String token = RpcTransmit.getUserToken();
        PromotionApply entity = super.findById(to.getId());
        entity.setIsConform(to.getIsConform());
        super.update(entity);
        if ((entity.getIsConform() != null) && (entity.getPromotionCriteria() != null)) {
            List<UserBO> users = modules(entity.getName());
            for (UserBO user : users) {
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle("有管理等级晋升申请需审核");
                messageTO.setContent("您有一个管理等级晋升申请需您去审核，请登陆issp系统完成审核");
                if (moduleAPI.isCheck("contacts")) {
                    RpcTransmit.transmitUserToken(token);
                    if (null != internalContactsAPI.getEmail(user.getUsername())) {
                        RpcTransmit.transmitUserToken(token);
                        messageTO.setReceivers(new String[]{internalContactsAPI.getEmail(user.getUsername())});
                        messageAPI.send(messageTO);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //综合素养模块填写晋升标准达标数
    public void writePromotionCriteria(PromotionApplyTO to) throws SerException {
        checkSuYangIdentity();
        String token = RpcTransmit.getUserToken();
        PromotionApply entity = super.findById(to.getId());
        entity.setPromotionCriteria(to.getPromotionCriteria());
        super.update(entity);
        if ((entity.getIsConform() != null) && (entity.getPromotionCriteria() != null)) {
            RpcTransmit.transmitUserToken(token);
            List<UserBO> users = modules(entity.getName());
            for (UserBO user : users) {
                MessageTO messageTO = new MessageTO();
                messageTO.setTitle("有管理等级晋升申请需审核");
                messageTO.setContent("您有一个管理等级晋升申请需您去审核，请登陆issp系统完成审核");
                if (moduleAPI.isCheck("contacts")) {
                    RpcTransmit.transmitUserToken(token);
                    if (null != internalContactsAPI.getEmail(user.getUsername())) {
                        RpcTransmit.transmitUserToken(token);
                        messageTO.setReceivers(new String[]{internalContactsAPI.getEmail(user.getUsername())});
                        messageAPI.send(messageTO);
                    }
                }
            }
        }
    }

    private List<UserBO> modules(String name) throws SerException {
        String token = RpcTransmit.getUserToken();
        List<UserBO> boList = new ArrayList<>();
        UserBO userBO = userAPI.findByUsername(name);
        if (null != userBO) {
            if (moduleAPI.isCheck("organize")) {
                RpcTransmit.transmitUserToken(token);
                List<PositionDetailBO> list = positionDetailUserAPI.findPositionByUser(userBO.getId());
                RpcTransmit.transmitUserToken(token);
                List<PositionDetailBO> list1 = positionDetailAPI.findStatus();
                for (PositionDetailBO p : list) {
                    String module = p.getModuleName();
                    for (PositionDetailBO p1 : list1) {
                        if ((module.equals(p1.getModuleName()) && ("管理层".equals(p1.getArrangementName()) || "决策层".equals(p1.getArrangementName()))) || "总经理".equals(p1.getPosition())) {
                            RpcTransmit.transmitUserToken(token);
                            List<UserBO> users = positionDetailUserAPI.findByPosition(p1.getId());
                            boList.addAll(users);
                        }
                    }
                }
            }
        }
        return boList;
    }

    /**
     * 获取总经理邮箱
     *
     * @return
     * @throws SerException
     */
    private Set<String> mangerEmail() throws SerException {
        Set<String> set = new HashSet<>();
        String token = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("organize")) {
            RpcTransmit.transmitUserToken(token);
            List<PositionDetailBO> list = positionDetailAPI.findStatus();
            for (PositionDetailBO p : list) {
                if ("总经理".equals(p.getPosition())) {
                    RpcTransmit.transmitUserToken(token);
                    List<UserBO> users = positionDetailUserAPI.findByPosition(p.getId());
                    if (moduleAPI.isCheck("contacts")) {
                        for (UserBO u : users) {
                            RpcTransmit.transmitUserToken(token);
                            if (null != internalContactsAPI.getEmail(u.getUsername())) {
                                RpcTransmit.transmitUserToken(token);
                                set.add(internalContactsAPI.getEmail(u.getUsername()));
                            }
                        }
                    }
                }
            }
        }
        return set;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //项目经理审核
    public void writeProjectManager(PromotionApplyTO to) throws SerException {
        checkXMJIdentity();
        PromotionApply entity = super.findById(to.getId());
        entity.setProjectManagerOpinion(to.getProjectManagerOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //综合资源部规划模块审核
    public void writeResourceDepartment(PromotionApplyTO to) throws SerException {
        checkGuiHuaIdentity();
        PromotionApply entity = super.findById(to.getId());
        entity.setResourceDepartmentOpinion(to.getResourceDepartmentOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //运营商务部审核
    public void writeCommerceDepartment(PromotionApplyTO to) throws SerException {
        checkBuinessIdentity();
        PromotionApply entity = super.findById(to.getId());
        entity.setCommerceDepartmentOpinion(to.getCommerceDepartmentOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //模块负责人审核
    public void writeModuler(PromotionApplyTO to) throws SerException {
        checkModuleFZIdentity();
        PromotionApply entity = super.findById(to.getId());
        entity.setModulerOpinion(to.getModulerOpinion());
        super.update(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    //总经办审核和填写本次晋升等级获得时间
    public void writeManager(PromotionApplyTO to) throws SerException {
        checkManIdentity();
        PromotionApply entity = super.findById(to.getId());
        entity.setManagerOpinion(to.getManagerOpinion());
        LocalDate time = null;
        if (to.getPromotionTakeTime() != null) {
            try {
                time = DateUtil.parseDate(to.getPromotionTakeTime());
            } catch (Exception e) {
                throw new SerException("日期格式错误");
            }
            entity.setPromotionTakeTime(time);
        }
        super.update(entity);
        if (ManagerOpinion.PASS.equals(entity.getManagerOpinion())) {
            List<LevelShowBO> list = levelShowAPI.findAll(new LevelShowDTO());
            if ((list != null) && (list.size() != 0)) {
                boolean b = true;
                for (LevelShowBO l : list) {
                    if (entity.getEmployeeId().equals(l.getEmployeeId())) {
                        LevelShowTO levelShowTO = new LevelShowTO();
                        BeanUtils.copyProperties(entity, levelShowTO);
                        levelShowTO.setPositiveDate(DateUtil.dateToString(entity.getPositiveDate()));
                        if (null != levelShowAPI.findByEmployeeId(l.getEmployeeId())) {
                            levelShowTO.setId(levelShowAPI.findByEmployeeId(l.getEmployeeId()).getId());
                            levelShowTO.setPromotionNum(entity.getPromotionNum() + 1);
                            levelShowAPI.update(levelShowTO);
                            b = false;
                        }
                    }
                }
                if (b) {
                    LevelShowTO levelShowTO = new LevelShowTO();
                    BeanUtils.copyProperties(entity, levelShowTO);
                    levelShowTO.setPositiveDate(DateUtil.dateToString(entity.getPositiveDate()));
                    levelShowTO.setPromotionNum(entity.getPromotionNum() + 1);
                    levelShowAPI.save(levelShowTO);
                }
            } else {
                LevelShowTO levelShowTO = new LevelShowTO();
                BeanUtils.copyProperties(entity, levelShowTO);
                levelShowTO.setPositiveDate(DateUtil.dateToString(entity.getPositiveDate()));
                levelShowTO.setPromotionNum(entity.getPromotionNum() + 1);
                levelShowAPI.save(levelShowTO);
            }
        }
    }

    @Override
    public List<PromotionApplyBO> rank() throws SerException {
        LocalDate now = LocalDate.now();
        String s = DateUtil.dateToString(now);
        s = s.substring(0, s.length() - 2);
        LocalDate start = DateUtil.parseDate(s + "21");
        LocalDate end = DateUtil.parseDate(s + "20");
        LocalDate[] time = null;
        PromotionApplyDTO dto = new PromotionApplyDTO();
        if (now.getDayOfMonth() < 21) {     //排名的日子是21号前，则排前2个月的21号到上个月20号区间
            start = start.minusMonths(2);
            end = end.minusMonths(1);
            time = new LocalDate[]{start, end};
            dto.getConditions().add(Restrict.between("applyDate", time));
            dto.getSorts().add("direction=desc");
            dto.getSorts().add("promotionCriteria=desc");
            List<PromotionApply> list = super.findByCis(dto);
            List<PromotionApplyBO> boList = new ArrayList<PromotionApplyBO>();
            for (String classification : allClassifications()) {
                for (String direction : allDirections()) {
                    int rank = 0;
                    for (PromotionApply p : list) {
                        if ((p.getPromotionCriteria() != null) && classification.equals(p.getClassification()) && direction.equals(p.getDirection())) {
                            rank++;
                            PromotionApplyBO bo = new PromotionApplyBO();
                            bo.setDirection(direction);
                            bo.setSkillLevel(p.getSkillLevel());
                            String grade = levelDesignAPI.findGrade(classification, direction, p.getSkillLevel());
                            if (grade == null) {
                                throw new SerException("获取等级失败");
                            }
                            bo.setGrade(grade);
                            bo.setName(p.getName());
                            bo.setPromotionCriteria(p.getPromotionCriteria());
                            bo.setRank(rank);
                            boList.add(bo);
                        }
                    }
                }
            }
            return boList;
        } else {  //21号排序的是上月21号至本月20号的数据
            start = start.minusMonths(1);
            time = new LocalDate[]{start, end};
            dto.getConditions().add(Restrict.between("applyDate", time));
            dto.getSorts().add("direction=desc");
            dto.getSorts().add("promotionCriteria=desc");
            List<PromotionApply> list = super.findByCis(dto);
            List<PromotionApplyBO> boList = new ArrayList<PromotionApplyBO>();
            for (String classification : allClassifications()) {
                for (String direction : allDirections()) {
                    int rank = 0;
                    for (PromotionApply p : list) {
                        if ((p.getPromotionCriteria() != null) && classification.equals(p.getClassification()) && direction.equals(p.getDirection())) {
                            rank++;
                            PromotionApplyBO bo = new PromotionApplyBO();
                            bo.setDirection(direction);
                            bo.setSkillLevel(p.getSkillLevel());
                            String grade = levelDesignAPI.findGrade(classification, direction, p.getSkillLevel());
                            if (grade == null) {
                                throw new SerException("获取等级失败");
                            }
                            bo.setGrade(grade);
                            bo.setName(p.getName());
                            bo.setPromotionCriteria(p.getPromotionCriteria());
                            bo.setRank(rank);
                            boList.add(bo);
                        }
                    }
                }
            }
            return boList;
        }
    }

    @Override
    //每月21号定时发邮件给总经办  //todo:
    public void send() throws SerException {
        String token=RpcTransmit.getUserToken();
        List<PromotionApplyBO> list = rank();
        RpcTransmit.transmitUserToken(token);
        Set<String> set = mangerEmail();
        for (String s : set) {
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("晋升申请排名");
            messageTO.setContent(html(list));
            messageTO.setReceivers(new String[]{s});
            messageAPI.send(messageTO);
        }
    }

    private String html(List<PromotionApplyBO> collectEmailBOs) throws SerException {
        StringBuffer sb = new StringBuffer("");
        if (collectEmailBOs != null && collectEmailBOs.size() > 0) {
            sb = new StringBuffer("<h4>晋升申请排名:</h4>");
            sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"   > ");
            //拼表头
//            CollectEmailBO title = dispatchEmails.get(dispatchEmails.size() - 1);
            sb.append("<tr>");
            sb.append("<td>管理方向</td>");
            sb.append("<td>技能等级</td>");
            sb.append("<td>级别</td>");
            sb.append("<td>姓名</td>");
            sb.append("<td>晋升标准达标数</td>");
            sb.append("<td>排名</td>");
            sb.append("</tr>");

            //拼body部分
            for (PromotionApplyBO bo : collectEmailBOs) {
                sb.append("<tr>");
                sb.append("<td>" + bo.getDirection() + "</td>");
                sb.append("<td>" + bo.getSkillLevel() + "</td>");
                sb.append("<td>" + bo.getGrade() + "</td>");
                sb.append("<td>" + bo.getName() + "</td>");
                sb.append("<td>" + bo.getPromotionCriteria() + "</td>");
                sb.append("<td>" + bo.getRank() + "</td>");
            }
        }
        sb.append("</tr>");
        //结束
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * 查找所有分类
     *
     * @throws SerException
     */
    private Set<String> allClassifications() throws SerException {
        Set<String> set = new HashSet<String>();
        List<PromotionApply> list = super.findAll();
        for (PromotionApply p : list) {
            set.add(p.getClassification());
        }
        return set;
    }

    /**
     * 查找所有管理方向
     *
     * @throws SerException
     */
    private Set<String> allDirections() throws SerException {
        Set<String> set = new HashSet<String>();
        List<PromotionApply> list = super.findAll();
        for (PromotionApply p : list) {
            set.add(p.getDirection());
        }
        return set;
    }
}

