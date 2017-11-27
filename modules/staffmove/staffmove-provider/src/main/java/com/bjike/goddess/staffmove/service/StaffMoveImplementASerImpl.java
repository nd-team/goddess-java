package com.bjike.goddess.staffmove.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffmove.bo.*;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementADTO;
import com.bjike.goddess.staffmove.dto.StaffMoveImplementBDTO;
import com.bjike.goddess.staffmove.entity.StaffMoveImplementA;
import com.bjike.goddess.staffmove.entity.StaffMoveImplementB;
import com.bjike.goddess.staffmove.enums.GuideAddrStatus;
import com.bjike.goddess.staffmove.to.GuidePermissionTO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementATO;
import com.bjike.goddess.staffmove.to.StaffMoveImplementBTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 人员调动实施业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffmoveSerCache")
@Service
public class StaffMoveImplementASerImpl extends ServiceImpl<StaffMoveImplementA, StaffMoveImplementADTO> implements StaffMoveImplementASer {
    @Autowired
    private StaffMoveImplementBSer staffMoveImplementBSer;

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
     * 核对原决策层审核权限（层级级别）
     */
    private void checkOraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应原决策层的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对调往决策层审核权限（层级级别）
     */
    private void checkTraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
            if (!flag) {
                throw new SerException("您不是相应调往决策层的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private void checkGenAduditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应总经办的人员，不可以操作");
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
     * 核对原决策层审核权限（层级级别）
     */
    private Boolean guideOraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对调往决策层审核权限（层级级别）
     */
    private Boolean guideTraAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对总经办审核权限（部门级别）
     */
    private Boolean guideGenAduditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("5");
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
        Boolean flagOraAudit = guideOraAuditIdentity();
        Boolean flagTraAudit = guideTraAuditIdentity();
        Boolean flagGenAdudit = guideGenAduditIdentity();
        if (flagSee || flagAdd || flagOraAudit || flagTraAudit || flagGenAdudit) {
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
            case DELETE:
                flag = guideAddIdentity();
                break;
            case COLLECT:
                flag = guideAddIdentity();
                break;
            case ORANAUDIT:
                flag = guideOraAuditIdentity();
                break;
            case TRAAUDIT:
                flag = guideTraAuditIdentity();
                break;
            case GENAUDIT:
                flag = guideGenAduditIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    @Override
    public Long count(StaffMoveImplementADTO adto) throws SerException {
        Long count = super.count(adto);
        return count;
    }

    @Override
    public StaffMoveImplementBBO getOneB(String id) throws SerException {
        StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(id);
        StaffMoveImplementBBO staffMoveImplementBBO = BeanTransform.copyProperties(staffMoveImplementB, StaffMoveImplementBBO.class);
        return staffMoveImplementBBO;
    }

    @Override
    public StaffMoveImplementABO getOneA(String id) throws SerException {
        StaffMoveImplementA implementA = super.findById(id);
        StaffMoveImplementABO implementABO = BeanTransform.copyProperties(implementA, StaffMoveImplementABO.class);
        StaffMoveImplementBDTO bdto = new StaffMoveImplementBDTO();
        bdto.getConditions().add(Restrict.eq("staffMoveImplementA.id", implementABO.getId()));
        List<StaffMoveImplementB> staffMoveImplementBS = staffMoveImplementBSer.findByCis(bdto);
        List<StaffMoveImplementBBO> staffMoveImplementBBOS = BeanTransform.copyProperties(staffMoveImplementBS, StaffMoveImplementBBO.class);
        implementABO.setStaffMoveImplementBBOS(staffMoveImplementBBOS);
        return implementABO;
    }

    @Override
    public List<StaffMoveImplementABO> list(StaffMoveImplementADTO dto) throws SerException {
        checkSeeIdentity();
        List<StaffMoveImplementA> staffMoveImplementAS = super.findByCis(dto);
        List<StaffMoveImplementABO> staffMoveImplementABOS = BeanTransform.copyProperties(staffMoveImplementAS, StaffMoveImplementABO.class);
        if (staffMoveImplementABOS != null) {
            for (StaffMoveImplementABO abo : staffMoveImplementABOS) {
                StaffMoveImplementBDTO bdto = new StaffMoveImplementBDTO();
                bdto.getConditions().add(Restrict.eq("staffMoveImplementA.id", abo.getId()));

                List<StaffMoveImplementB> staffMoveImplementBS = staffMoveImplementBSer.findByCis(bdto);
                List<StaffMoveImplementBBO> staffMoveImplementBBOS = BeanTransform.copyProperties(staffMoveImplementBS, StaffMoveImplementBBO.class);
                if (staffMoveImplementBBOS != null) {
                    for (StaffMoveImplementBBO bbo : staffMoveImplementBBOS) {
                        String area = bbo.getMobilizedArea();
                        String department = bbo.getMobilizedProjectGroup();
                        String sql = " SELECT count(*) FROM staffmove_staffmoveimplementb where mobilizedArea='" + area + "' and mobilizedProjectGroup = '" + department + "'";
                        List<Object> objects = staffMoveImplementBSer.findBySql(sql);
                        if (objects != null && objects.size() > 0) {
                            bbo.setTransferNum(Integer.valueOf(String.valueOf(objects.get(0))));
                        }
                    }
                }
                abo.setStaffMoveImplementBBOS(staffMoveImplementBBOS);
            }
        }
        return staffMoveImplementABOS;
    }

    @Override
    public StaffMoveImplementABO insert(StaffMoveImplementATO to) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void edit(StaffMoveImplementATO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            StaffMoveImplementA staffMoveImplementA = super.findById(to.getId());
//            LocalDateTime createTime = staffMoveImplementA.getCreateTime();
            BeanTransform.copyProperties(to, staffMoveImplementA, true);
//            staffMoveImplementA.setCreateTime(createTime);
            staffMoveImplementA.setModifyTime(LocalDateTime.now());
            super.update(staffMoveImplementA);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void staffMove(StaffMoveImplementATO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            //修改B表
            StaffMoveImplementA staffMoveImplementA = super.findById(to.getId());
            List<StaffMoveImplementBTO> staffMoveImplementBTOS = to.getStaffMoveImplementBTOS();
            if (null != staffMoveImplementBTOS) {
                for (StaffMoveImplementBTO bto : staffMoveImplementBTOS) {
                    StaffMoveImplementB staffMoveImplementB = BeanTransform.copyProperties(bto, StaffMoveImplementB.class, true);
                    staffMoveImplementB.setStaffMoveImplementA(staffMoveImplementA);
                    staffMoveImplementBSer.update(staffMoveImplementB);
                }
            }
        }
    }

    private List<String> areas() throws SerException {
        Set<String> set = new HashSet<>();
        List<StaffMoveImplementB> list = staffMoveImplementBSer.findAll();
        for (StaffMoveImplementB staffMoveImplementB : list) {
            set.add(staffMoveImplementB.getMobilizedArea());
        }
        return new ArrayList<>(set);
    }

    private List<String> departments() throws SerException {
        Set<String> set = new HashSet<>();
        List<StaffMoveImplementB> list = staffMoveImplementBSer.findAll();
        for (StaffMoveImplementB staffMoveImplementB : list) {
            set.add(staffMoveImplementB.getMobilizedProjectGroup());
        }
        return new ArrayList<>(set);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void originalAudit(StaffMoveImplementBTO to) throws SerException {
        checkOraAuditIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(to.getId());
            BeanTransform.copyProperties(to, staffMoveImplementB, true);
            staffMoveImplementB.setModifyTime(LocalDateTime.now());
            staffMoveImplementBSer.update(staffMoveImplementB);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void transferAudit(StaffMoveImplementBTO to) throws SerException {
        checkTraAuditIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(to.getId());
            BeanTransform.copyProperties(to, staffMoveImplementB, true);
            staffMoveImplementB.setModifyTime(LocalDateTime.now());
            staffMoveImplementBSer.update(staffMoveImplementB);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void generalAudit(StaffMoveImplementBTO to) throws SerException {
        checkGenAduditIdentity();
        StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(to.getId());
        BeanTransform.copyProperties(to, staffMoveImplementB, true);
        if (StringUtils.isBlank(staffMoveImplementB.getOriginalIdea()) || StringUtils.isBlank(staffMoveImplementB.getTransferIdea())) {
            throw new SerException("原决策层或调往决策层还未给出意见！");
        } else {
            staffMoveImplementB.setModifyTime(LocalDateTime.now());
            staffMoveImplementBSer.update(staffMoveImplementB);
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void solve(StaffMoveImplementBTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(to.getId());
            BeanTransform.copyProperties(to, staffMoveImplementB, true);
            staffMoveImplementB.setModifyTime(LocalDateTime.now());
            staffMoveImplementBSer.update(staffMoveImplementB);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            StaffMoveImplementB staffMoveImplementB = staffMoveImplementBSer.findById(id);
            staffMoveImplementBSer.remove(id);
            if (staffMoveImplementB == null) {
                throw new SerException("该对象不能为空");
            }
            List<StaffMoveImplementA> aList = super.findAll();
            Set<String> aids = new HashSet<>();
            for (StaffMoveImplementB b : staffMoveImplementBSer.findAll()) {
                aids.add(b.getStaffMoveImplementA().getId());
            }
            for (StaffMoveImplementA a : aList) {
                if (!aids.contains(a.getId())) {
                    super.remove(a.getId());
                }
            }
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<StaffMoveCollectBO> weekStaff(Integer year, Integer month, Integer week) throws SerException {
        if (null == year && null == month && null == week) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        return staffCollect(startDate, endDate);
    }

    @Override
    public List<StaffMoveCollectBO> monthStaff(Integer year, Integer month) throws SerException {
        if (null == year && null == month) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return staffCollect(startDate, endDate);
    }

    @Override
    public List<StaffMoveCollectBO> totalStaff(String time) throws SerException {
        String startDate = "";
        String endDate = time;
        String sql = "select realityWorkTime as realityWorkTime from " + getTableName(StaffMoveImplementB.class) + " where realityWorkTime <= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        return staffCollect(startDate, endDate);
    }

    private List<StaffMoveCollectBO> staffCollect(String startTime, String endTime) throws SerException {
        List<StaffMoveCollectBO> boList = new ArrayList<>();
        List<StaffMoveCollectBO> collectBOS = new ArrayList<>();
        //地区 项目组 被调动人数
        String[] mobilizedFields = new String[]{"area", "projectGroup", "mobilizedNum"};
        String mobilizedSql = " SELECT a.area as area,a.projectGroup AS projectGroup,count(*) AS mobilizedNum " +
                " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b " +
                " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND " +
                " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 " +
                " GROUP BY a.area,a.projectGroup ";
        List<StaffMoveCollectBO> staffMoveCollectBOS = super.findBySql(mobilizedSql, StaffMoveCollectBO.class, mobilizedFields);
        for (StaffMoveCollectBO bo : staffMoveCollectBOS) {
            //公司发展需求调动次数
            String[] companyFields = new String[]{"companyMobilizedNum"};
            String companySql = " SELECT ifnull(count(a.demandType),0) AS companyMobilizedNum" +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b" +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND" +
                    " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='公司发展需求' " +
                    " and a.projectGroup = '" + bo.getProjectGroup() + "' and a.area = '" + bo.getArea() + "'";
            collectBOS = super.findBySql(companySql, StaffMoveCollectBO.class, companyFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setCompanyMobilizedNum(bo1.getCompanyMobilizedNum());
            }
            //人员需求调动次数
            String[] staffFields = new String[]{"staffMobilizedNum"};
            String staffSql = " SELECT ifnull(count(a.demandType),0) AS staffMobilizedNum" +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b" +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND" +
                    " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='个人需求'" +
                    " and a.projectGroup = '" + bo.getProjectGroup() + "' and a.area = '" + bo.getArea() + "'";
            collectBOS = super.findBySql(staffSql, StaffMoveCollectBO.class, staffFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setStaffMobilizedNum(bo1.getStaffMobilizedNum());
            }
            // 项目需求调动次数
            String[] projectFields = new String[]{"projectMobilizedNum"};
            String projectSql = "SELECT ifnull(count(a.demandType),0) AS projectMobilizedNum " +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b " +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND " +
                    " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='项目需求' " +
                    " and a.projectGroup = '" + bo.getProjectGroup() + "' and a.area = '" + bo.getArea() + "'";
            collectBOS = super.findBySql(projectSql, StaffMoveCollectBO.class, projectFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setProjectMobilizedNum(bo1.getProjectMobilizedNum());
            }
            //调动需求人数
            String[] transFields = new String[]{"transferNum"};
            String transSql = " SELECT ifnull(sum(demandNum),0) AS count" +
                    " FROM staffmove_staffmovedemand a" +
                    " WHERE demandTime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                    " and projectGroup = '" + bo.getProjectGroup() + "' and area = '" + bo.getArea() + "' ";
            collectBOS = super.findBySql(transSql, StaffMoveCollectBO.class, transFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setTransferNum(bo1.getTransferNum());
            }
            //服从全国范围内调动人数
            String[] obeyFields = new String[]{"obeyPlanNum"};
            String obeySql = " SELECT ifnull(count(*),0) AS obeyPlanNum" +
                    " FROM staffmove_staffmoveintendcase WHERE is_obeyPlan=1 AND entryTime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                    " and projectGroup = '" + bo.getProjectGroup() + "' and area = '" + bo.getArea() + "' ";
            collectBOS = super.findBySql(obeySql, StaffMoveCollectBO.class, obeyFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setObeyPlanNum(bo1.getObeyPlanNum());
            }
            //已解决调动需求人数
            String[] solveFields = new String[]{"solveTransferNum"};
            String solveSql = " SELECT ifnull(count(*),0) AS solveTransferNum " +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b " +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND " +
                    " a.id=b.staffMoveImplementA_id AND b.is_solveDemand=1 " +
                    " and a.projectGroup = '" + bo.getProjectGroup() + "' and a.area = '" + bo.getArea() + "' ";
            collectBOS = super.findBySql(solveSql, StaffMoveCollectBO.class, solveFields);
            for (StaffMoveCollectBO bo1 : collectBOS) {
                bo.setSolveTransferNum(bo1.getSolveTransferNum());
            }
            // 人员编制状态(人员编制-管理编制中实际编制状态)
            // 目前在职人数(人员编制-管理编制 中 实际在编人数)
            // 超编人数(编制汇总-实际编制汇总为负数的)
            // 缺编人数(编制汇总-实际编制汇总为正数的)
            //todo 上面那些字段都要从人员编制中获取，之前的人员编制中没有，暂时拿不了
            // 调动需求频率(调动需求人数/部门总人数)
            // 被调动频率(被调动人数/部门总人数)
            //todo 部门总人数就相当于目前在职人数
            boList.add(bo);
        }
        return boList;
    }

    @Override
    public OptionBO weekStaffFigure(Integer year, Integer month, Integer week) throws SerException {
        if (null == year && null == month && null == week) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        String text_1 = "人员调动管理周汇总" + startDate + "-" + endDate;
        return staffFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO monthStaffFigure(Integer year, Integer month) throws SerException {
        if (null == year && null == month) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        String text_1 = "人员调动管理月汇总" + startDate + "-" + endDate;
        return staffFigureCollect(startDate, endDate, text_1);
    }

    @Override
    public OptionBO totalStaffFigure(String time) throws SerException {
        String startDate = "";
        String endDate = time;
        String sql = "select realityWorkTime as realityWorkTime from " + getTableName(StaffMoveImplementB.class) + " where realityWorkTime <= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        String text_1 = "人员调动管理累计汇总" + startDate + "-" + endDate;
        return staffFigureCollect(startDate, endDate, text_1);
    }

    private OptionBO staffFigureCollect(String startTime, String endTime, String text_1) throws SerException {
        List<StaffMoveFigureBO> boList = new ArrayList<>();
        List<StaffMoveFigureBO> figureBOS = new ArrayList<>();
        //被调动人数
        String[] mobilizedFields = new String[]{"projectGroup", "mobilizedNum"};
        String mobilizedSql = " SELECT a.projectGroup AS projectGroup,count(*) AS mobilizedNum " +
                " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b " +
                " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND " +
                " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 group by a.projectGroup ";
        List<StaffMoveFigureBO> staffMoveFigureBOS = super.findBySql(mobilizedSql, StaffMoveFigureBO.class, mobilizedFields);
//        Long count = staffMoveFigureBOS.stream().filter(staffMoveFigureBO -> staffMoveFigureBO.getProjectGroup()!= null).count();
        for (StaffMoveFigureBO bo : staffMoveFigureBOS) {
            //公司发展需求调动次数
            String[] companyFields = new String[]{"companyMobilizedNum"};
            String companySql = " SELECT ifnull(count(a.demandType),0) AS companyMobilizedNum" +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b" +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND" +
                    " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='公司发展需求' and a.projectGroup = '" + bo.getProjectGroup() + "'";
            figureBOS = super.findBySql(companySql, StaffMoveFigureBO.class, companyFields);
            for (StaffMoveFigureBO bo1 : figureBOS) {
                bo.setCompanyMobilizedNum(bo1.getCompanyMobilizedNum());
            }
            //人员需求调动次数
            String[] staffFields = new String[]{"staffMobilizedNum"};
            String staffSql = " SELECT ifnull(count(a.demandType),0) AS staffMobilizedNum" +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b" +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' AND" +
                    " a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='个人需求' and a.projectGroup = '" + bo.getProjectGroup() + "'";
            figureBOS = super.findBySql(staffSql, StaffMoveFigureBO.class, staffFields);
            for (StaffMoveFigureBO bo1 : figureBOS) {
                bo.setStaffMobilizedNum(bo1.getStaffMobilizedNum());
            }
            //项目组 项目需求调动次数
            String[] projectFields = new String[]{"projectMobilizedNum"};
            String projectSql = " SELECT ifnull(count(a.demandType),0) AS projectMobilizedNum " +
                    " FROM staffmove_staffmoveimplementa a,staffmove_staffmoveimplementb b " +
                    " WHERE b.realityWorkTime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                    " AND a.id=b.staffMoveImplementA_id AND b.is_obeyStaff=1 AND a.demandType='项目需求' and a.projectGroup = '" + bo.getProjectGroup() + "'";
            figureBOS = super.findBySql(projectSql, StaffMoveFigureBO.class, projectFields);
            for (StaffMoveFigureBO bo1 : figureBOS) {
                bo.setProjectMobilizedNum(bo1.getProjectMobilizedNum());
            }
            boList.add(bo);
        }
        //标题
        TitleBO titleBO = new TitleBO();
        titleBO.setText(text_1);
        //横坐标描述
        LegendBO legendBO = new LegendBO();
        String[] text_2 = new String[]{"被调动人数", "项目需求调动次数", "公司发展需求调动次数", "个人需求调动次数"};
        //纵坐标
        YAxisBO yAxisBO = new YAxisBO();
        //悬停提示
        TooltipBO tooltipBO = new TooltipBO();
        //横坐标描述
        XAxisBO xAxisBO = new XAxisBO();
        List<String> text_list_3 = new ArrayList<>();
        AxisLabelBO axisLabelBO = new AxisLabelBO();
        axisLabelBO.setInterval(0);
        xAxisBO.setAxisLabel(axisLabelBO);
        List<SeriesBO> seriesBOS = new ArrayList<>();
        if (figureBOS != null && figureBOS.size() > 0) {
            List<Integer> mobilizedNum = new ArrayList<>();
            List<Integer> projectNum = new ArrayList<>();
            List<Integer> companyNum = new ArrayList<>();
            List<Integer> staffNum = new ArrayList<>();
            for (StaffMoveFigureBO figureBO : boList) {
                text_list_3.add(figureBO.getProjectGroup());

                //柱状图数据
                mobilizedNum.add(figureBO.getMobilizedNum());
                projectNum.add(figureBO.getProjectMobilizedNum());
                companyNum.add(figureBO.getCompanyMobilizedNum());
                staffNum.add(figureBO.getStaffMobilizedNum());
            }
            List<List<Integer>> nums = new ArrayList<>();
            nums.add(mobilizedNum);
            nums.add(projectNum);
            nums.add(companyNum);
            nums.add(staffNum);
            String[] ziduan = new String[]{"被调动人数", "项目需求调动次数", "公司发展需求调动次数", "个人需求调动次数"};
//            List<SeriesBO> seriesBOList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                SeriesBO seriesBO = new SeriesBO();
                seriesBO.setName(ziduan[i]);
                seriesBO.setType("bar");
                Integer[] text_int_4 = new Integer[nums.get(i).size()];
                text_int_4 = nums.get(i).toArray(text_int_4);
                seriesBO.setData(text_int_4);
                seriesBOS.add(seriesBO);
            }
        }

        String[] text_3 = new String[text_list_3.size()];
        text_3 = text_list_3.toArray(text_3);
        xAxisBO.setData(text_3);

        SeriesBO[] text_4 = new SeriesBO[seriesBOS.size()];
        text_4 = seriesBOS.toArray(text_4);
        legendBO.setData(text_2);
        OptionBO optionBO = new OptionBO();
        optionBO.setTitle(titleBO);
        optionBO.setLegend(legendBO);
        optionBO.setTooltip(tooltipBO);
        optionBO.setxAxis(xAxisBO);
        optionBO.setyAxis(yAxisBO);

        optionBO.setSeries(text_4);
        return optionBO;
    }

}