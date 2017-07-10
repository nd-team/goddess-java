package com.bjike.goddess.reimbursementprepare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.reimbursementprepare.bo.MoneyReadyBO;
import com.bjike.goddess.reimbursementprepare.bo.MoneyReadyCountBO;
import com.bjike.goddess.reimbursementprepare.dto.MoneyReadyDTO;
import com.bjike.goddess.reimbursementprepare.entity.MoneyReady;
import com.bjike.goddess.reimbursementprepare.enums.GuideAddrStatus;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.MoneyReadyTO;
import com.bjike.goddess.reimbursementprepare.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资金准备业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:07 ]
 * @Description: [ 资金准备业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]+-
 */
@CacheConfig(cacheNames = "reimbursementprepareSerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private WaitPaySer waitPaySer;

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("moneyready");
        obj.setDescribesion("资金审核准备");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = waitPaySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("waitpay");
        obj.setDescribesion("等待付款");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            case DIFFERENCES:
                flag = guideSeeIdentity();
                break;
            case SEE:
                flag = guideSeeIdentity();
                break;
            case PAY:
                flag = guideAddIdentity();
                break;
            case EXPORT:
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
    @Transactional(rollbackFor = {SerException.class})
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady m = BeanTransform.copyProperties(to, MoneyReady.class, true);
        super.save(m);
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(MoneyReadyTO to) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(to.getId());
        LocalDateTime a = moneyReady.getCreateTime();
        moneyReady = BeanTransform.copyProperties(to, MoneyReady.class, true);
        moneyReady.setCreateTime(a);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        List<MoneyReady> list = super.findByCis(dto, true);
        for (MoneyReady m : list) {
            m.setReserve(m.getProrate() * m.getTotalReserve());
        }
        return BeanTransform.copyProperties(list, MoneyReadyBO.class);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        MoneyReady m = super.findById(id);
        if (m == null) {
            throw new SerException("该对象不存在");
        }
        m.setReserve(m.getProrate() * m.getTotalReserve());
        return BeanTransform.copyProperties(m, MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyCountBO> countContrast(MoneyReadyDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        LocalDate start = DateUtil.parseDate(startTime);
        LocalDate end = DateUtil.parseDate(endTime);
        int startYear = start.getYear();
        int startMonth = start.getMonthValue();
        LocalDate[] time = new LocalDate[]{start, end};
        dto.getConditions().add(Restrict.between("time", time));
        List<MoneyReady> list = super.findByCis(dto);
        List<MoneyReady> all = super.findAll();
        Set<String> projectGroups = findAllProjectGroups();
        Set<String> subjects = findAllSubjects();
        List<MoneyReadyCountBO> boList = new ArrayList<>();
        List<MoneyReadyCountBO> last = new ArrayList<>();
        double currentMonthReserveSum = 0;
        double lastMonthReserveSum = 0;
        for (String projectGroup : projectGroups) {
            for (String subject : subjects) {
                for (MoneyReady m : list) {
                    if (projectGroup.equals(m.getProjectGroup()) && subject.equals(m.getSubject())) {
                        currentMonthReserveSum += m.getReserve();
                    }
                }
                if (currentMonthReserveSum != 0) {
                    MoneyReadyCountBO bo = new MoneyReadyCountBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setTime(startTime + "~" + endTime);
                    bo.setSubject(subject);
                    bo.setCurrentMonthReserveSum(currentMonthReserveSum);
                    boList.add(bo);
                    currentMonthReserveSum = 0;
                }
            }
        }
        if (startMonth != 1) {
            for (String projectGroup : projectGroups) {
                for (String subject : subjects) {
                    for (MoneyReady m : all) {
                        LocalDate t = m.getTime();
                        if (projectGroup.equals(m.getProjectGroup()) && subject.equals(m.getSubject()) && t.getYear() == startYear && t.getMonthValue() == (startMonth - 1)) {
                            lastMonthReserveSum += m.getReserve();
                        }
                    }
                    if (lastMonthReserveSum != 0) {
                        MoneyReadyCountBO bo = new MoneyReadyCountBO();
                        bo.setProjectGroup(projectGroup);
                        bo.setSubject(subject);
                        bo.setLastMonthReserveSum(lastMonthReserveSum);
                        last.add(bo);
                        lastMonthReserveSum = 0;    //置为0
                    }
                }
            }
        } else {
            for (String projectGroup : projectGroups) {
                for (String subject : subjects) {
                    for (MoneyReady m : all) {
                        LocalDate t = m.getTime();
                        if (projectGroup.equals(m.getProjectGroup()) && subject.equals(m.getSubject()) && t.getYear() == (startYear - 1) && t.getMonthValue() == 12) {
                            lastMonthReserveSum += m.getReserve();
                        }
                    }
                    if (lastMonthReserveSum != 0) {
                        MoneyReadyCountBO bo = new MoneyReadyCountBO();
                        bo.setProjectGroup(projectGroup);
                        bo.setSubject(subject);
                        bo.setLastMonthReserveSum(lastMonthReserveSum);
                        last.add(bo);
                        lastMonthReserveSum = 0;    //置为0
                    }
                }
            }
        }
        return contrast(boList, last);
    }

    private List<MoneyReadyCountBO> contrast(List<MoneyReadyCountBO> currents, List<MoneyReadyCountBO> lasts) throws SerException {
        for (MoneyReadyCountBO current : currents) {
            for (MoneyReadyCountBO last : lasts) {
                if (current.getProjectGroup().equals(last.getProjectGroup()) && current.getSubject().equals(last.getSubject())) {
                    double lastMonthReserveSum = last.getLastMonthReserveSum();
                    double differences = current.getCurrentMonthReserveSum() - lastMonthReserveSum;
                    current.setLastMonthReserveSum(lastMonthReserveSum);
                    current.setDifferences(differences);
                    if (lastMonthReserveSum != 0) {
                        current.setGrowth(String.format("%.2f", (differences / lastMonthReserveSum) * 100) + "%");
                    } else {
                        current.setGrowth("100%");
                    }
                }
            }
        }
        return currents;
    }

    /**
     * 获取所有项目组
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllProjectGroups() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getProjectGroup());
        }
        return set;
    }

    /**
     * 获取所有科目
     *
     * @return class String
     * @throws SerException
     */
    private Set<String> findAllSubjects() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getSubject());
        }
        return set;
    }

    @Override
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        return super.count(dto);
    }
}