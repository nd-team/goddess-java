package com.bjike.goddess.reimbursementprepare.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.reimbursementprepare.bo.DifferencesBO;
import com.bjike.goddess.reimbursementprepare.bo.MoneyReadyCountBO;
import com.bjike.goddess.reimbursementprepare.bo.WaitPayBO;
import com.bjike.goddess.reimbursementprepare.dto.MoneyReadyDTO;
import com.bjike.goddess.reimbursementprepare.dto.WaitPayDTO;
import com.bjike.goddess.reimbursementprepare.entity.WaitPay;
import com.bjike.goddess.reimbursementprepare.enums.GuideAddrStatus;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcel;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.WaitPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reimbursementprepareSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Autowired
    private ApplyLendAPI applyLendAPI;
    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private MoneyReadySer moneyReadySer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
    public List<WaitPayBO> waitPays(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        int page = dto.getPage() + 1;
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        BeanUtils.copyProperties(dto, applyLendDTO);
        applyLendDTO.setPage(page);
        ReimburseRecordDTO reimburseRecordDTO = new ReimburseRecordDTO();
        BeanUtils.copyProperties(dto, reimburseRecordDTO);
        reimburseRecordDTO.setPage(page);
        List<ApplyLendBO> applyLendBOs = applyLendAPI.listWaitPayCJH(applyLendDTO);
        List<ReimburseRecordBO> reimburseRecordBOs = reimburseRecordAPI.listWaitPayCJH(reimburseRecordDTO);
        List<WaitPayBO> boList = new ArrayList<>();
        if (applyLendBOs != null && !applyLendBOs.isEmpty()) {
            for (ApplyLendBO applyLendBO : applyLendBOs) {
                WaitPayBO waitPayBO = BeanTransform.copyProperties(applyLendBO, WaitPayBO.class);
                waitPayBO.setPayStatus(PayStatus.WAITPAY);
                waitPayBO.setType("借款");
                boList.add(waitPayBO);
            }
        }
        if (reimburseRecordBOs != null && !reimburseRecordBOs.isEmpty()) {
            for (ReimburseRecordBO reimburseRecordBO : reimburseRecordBOs) {
                WaitPayBO waitPayBO = BeanTransform.copyProperties(reimburseRecordBO, WaitPayBO.class);
                waitPayBO.setLendDate(reimburseRecordBO.getOccureDate());
                waitPayBO.setLendMoney(reimburseRecordBO.getReimMoney());
                waitPayBO.setPayDate(reimburseRecordBO.getBudgetPayTime());
                waitPayBO.setPayStatus(PayStatus.WAITPAY);
                waitPayBO.setType("报销");
                boList.add(waitPayBO);
            }
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pay(WaitPayTO to) throws SerException {
        checkAddIdentity();
        String userToken = RpcTransmit.getUserToken();
        String name = userAPI.currentUser().getUsername();
        if ("借款".equals(to.getType())) {
            ApplyLendTO applyLendTO = BeanTransform.copyProperties(to, ApplyLendTO.class);
            applyLendTO.setPayDate(DateUtil.dateToString(LocalDate.now()));
            RpcTransmit.transmitUserToken(userToken);
            ApplyLendBO applyLendBO = applyLendAPI.editPayMoneyCJH(applyLendTO);
            WaitPay entity = BeanTransform.copyProperties(applyLendBO, WaitPay.class, true);
            entity.setPayStatus(PayStatus.HAVEPAY);
            super.save(entity);
        } else if ("报销".equals(to.getType())) {
            ReimburseRecordTO reimburseRecordTO = BeanTransform.copyProperties(to, ReimburseRecordTO.class);
            reimburseRecordTO.setPayTime(DateUtil.dateToString(LocalDate.now()));
            ReimburseRecordBO reimburseRecordBO = reimburseRecordAPI.waitPayCJH(reimburseRecordTO);
            WaitPay entity = BeanTransform.copyProperties(reimburseRecordBO, WaitPay.class, true);
            entity.setLendDate(DateUtil.parseDate(reimburseRecordBO.getOccureDate()));
            entity.setLendMoney(reimburseRecordBO.getReimMoney());
            entity.setPayDate(LocalDate.now());
            entity.setPayer(name);
            entity.setPayStatus(PayStatus.HAVEPAY);
            super.save(entity);
        }
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        List<WaitPay> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, WaitPayBO.class);
    }

    @Override
    public byte[] waitPayExport(WaitPayDTO dto) throws SerException {
//        checkSeeIdentity();
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        BeanUtils.copyProperties(dto, applyLendDTO);
        ReimburseRecordDTO reimburseRecordDTO = new ReimburseRecordDTO();
        BeanUtils.copyProperties(dto, reimburseRecordDTO);
        List<ExportExcel> list1 = BeanTransform.copyProperties(applyLendAPI.waitPayExport(applyLendDTO), ExportExcel.class, true);
        List<ExportExcel> list2 = BeanTransform.copyProperties(reimburseRecordAPI.exportExcelCjh(reimburseRecordDTO), ExportExcel.class, true);
        List<ExportExcel> list = new ArrayList<>();
        if (list1 != null) {
            list.addAll(list1);
        }
        if (list2 != null) {
            list.addAll(list2);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(list, excel);
        return bytes;
    }

    @Override
    public byte[] payExport(WaitPayDTO dto) throws SerException {
//        checkSeeIdentity();
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        if (StringUtils.isNotBlank(dto.getStartTime())) {
            start = DateUtil.parseDate(dto.getStartTime());
        }
        if (StringUtils.isNotBlank(dto.getEndTime())) {
            end = DateUtil.parseDate(dto.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};

        if (StringUtils.isNotBlank(dto.getStartTime()) || StringUtils.isNotBlank(dto.getEndTime())) {
            dto.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        List<WaitPay> list = super.findByCis(dto);
        List<ExportExcel> toList = new ArrayList<>();
//        if (list == null || list.isEmpty()) {
//            throw new SerException("该时间段没有数据可导出");
//        }
        for (WaitPay pay : list) {
            ExportExcel exportExcel = BeanTransform.copyProperties(pay, ExportExcel.class, true);
            exportExcel.setPayStatus(PayStatus.HAVEPAY);
            toList.add(exportExcel);
        }
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    public List<WaitPayBO> conditionsCount(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        String[] projectGroups = dto.getProjectGroups();
        String[] areas = dto.getAreas();
        if (projectGroups != null) {
            dto.getConditions().add(Restrict.in("projectGroup", projectGroups));
            List<WaitPay> list = super.findByCis(dto);
            return projectGroupCount(list);
        }
        if (areas != null) {
            dto.getConditions().add(Restrict.in("area", areas));
            List<WaitPay> list = super.findByCis(dto);
            return areaCount(list);
        }
        return null;
    }

    private List<WaitPayBO> projectGroupCount(List<WaitPay> list) throws SerException {
        double sum = 0;
        List<WaitPayBO> boList = new ArrayList<>();
        Set<String> set = allProjectGroups();
        for (WaitPay w : list) {
            for (String s : set) {
                if (s.equals(w.getProjectGroup())) {
                    sum += w.getLendMoney();
                    boList.add(BeanTransform.copyProperties(w, WaitPayBO.class));
                }
            }
        }
        WaitPayBO waitPayBO = new WaitPayBO();
        waitPayBO.setProjectGroup("合计:");
        waitPayBO.setLendMoney(sum);
        boList.add(waitPayBO);
        return boList;
    }

    private List<WaitPayBO> areaCount(List<WaitPay> list) throws SerException {
        double sum = 0;
        List<WaitPayBO> boList = new ArrayList<>();
        Set<String> set = allAreas();
        for (WaitPay w : list) {
            for (String s : set) {
                if (s.equals(w.getArea())) {
                    sum += w.getLendMoney();
                    boList.add(BeanTransform.copyProperties(w, WaitPayBO.class));
                }
            }
        }
        WaitPayBO waitPayBO = new WaitPayBO();
        waitPayBO.setProjectGroup("合计:");
        waitPayBO.setLendMoney(sum);
        boList.add(waitPayBO);
        return boList;
    }

    @Override
    public List<DifferencesBO> difference(WaitPayDTO dto) throws SerException {
        checkSeeIdentity();
        String startTime = DateUtil.dateToString(LocalDate.now());
        String endTime = DateUtil.dateToString(LocalDate.now());
        if (StringUtils.isNotBlank(dto.getStartTime())) {
            startTime = dto.getStartTime();
        }
        if (StringUtils.isNotBlank(dto.getEndTime())) {
            endTime = dto.getEndTime();
        }
        List<DifferencesBO> boList = new ArrayList<>();
        MoneyReadyDTO moneyReadyDTO = new MoneyReadyDTO();
        moneyReadyDTO.setStartTime(startTime);
        moneyReadyDTO.setEndTime(endTime);
        List<MoneyReadyCountBO> moneyReadyCountBOs = moneyReadySer.countContrast(moneyReadyDTO);
        List<WaitPayBO> waitPays = count1(dto);
        for (MoneyReadyCountBO m : moneyReadyCountBOs) {
            for (WaitPayBO w : waitPays) {
                if (m.getSubject().equals(w.getFirstSubject()) && m.getProjectGroup().equals(w.getProjectGroup())) {
                    DifferencesBO bo = BeanTransform.copyProperties(m, DifferencesBO.class);
                    bo.setCurrentReserve(m.getCurrentMonthReserveSum());
                    bo.setPayMoney(w.getLendMoney());
                    bo.setTime(startTime + "~" + endTime);
                    bo.setDifference(bo.getCurrentReserve() - bo.getPayMoney());
                    bo.setGrowth(String.format("%.2f", (bo.getDifference() / bo.getPayMoney()) * 100) + "%");
                    boList.add(bo);
                }
            }
        }
        return boList;
    }

    private List<WaitPayBO> count1(WaitPayDTO dto) throws SerException {
        LocalDate start = null;
        LocalDate end = null;
        if (StringUtils.isNotBlank(dto.getStartTime())) {
            start = DateUtil.parseDate(dto.getStartTime());
        }
        if (StringUtils.isNotBlank(dto.getEndTime())) {
            end = DateUtil.parseDate(dto.getEndTime());
        }
        LocalDate lendDate[] = new LocalDate[]{start, end};
        if (StringUtils.isNotBlank(dto.getStartTime()) || StringUtils.isNotBlank(dto.getEndTime())) {
            dto.getConditions().add(Restrict.between("lendDate", lendDate));//借款时间段查询
        }
        double sum = 0;
        List<WaitPayBO> boList = new ArrayList<>();
        List<WaitPay> list = super.findByCis(dto);
        Set<String> projectGroups = allProjectGroups();
        Set<String> subjects = allSubjects();
        if (list != null && !list.isEmpty()) {
            for (String projectGroup : projectGroups) {
                for (String subject : subjects) {
                    for (WaitPay w : list) {
                        if (projectGroup.equals(w.getProjectGroup()) && subject.equals(w.getFirstSubject())) {
                            sum += w.getLendMoney();
                        }
                    }
                    WaitPayBO bo = new WaitPayBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setFirstSubject(subject);
                    bo.setLendMoney(sum);
                    boList.add(bo);
                    sum = 0;
                }
            }
        }
        return boList;
    }

    @Override
    public Set<String> allProjectGroups() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPay> list = super.findAll();
        for (WaitPay w : list) {
            set.add(w.getProjectGroup());
        }
        return set;
    }

    @Override
    public Set<String> allAreas() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPay> list = super.findAll();
        for (WaitPay w : list) {
            set.add(w.getArea());
        }
        return set;
    }

    private Set<String> allSubjects() throws SerException {
        Set<String> set = new HashSet<String>();
        List<WaitPay> list = super.findAll();
        for (WaitPay w : list) {
            set.add(w.getFirstSubject());
        }
        return set;
    }

    @Override
    public Long payCount(WaitPayDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public Long waitPayCount(WaitPayDTO dto) throws SerException {
        ApplyLendDTO applyLendDTO = new ApplyLendDTO();
        BeanUtils.copyProperties(dto, applyLendDTO);
        ReimburseRecordDTO reimburseRecordDTO = new ReimburseRecordDTO();
        BeanUtils.copyProperties(dto, reimburseRecordDTO);
        return applyLendAPI.countWaitPayCJH(applyLendDTO) + reimburseRecordAPI.countWaitPayCJH(reimburseRecordDTO);
    }

    @Override
    public WaitPayBO findWait(String id) throws SerException {
        ApplyLendBO applyLendBO = applyLendAPI.getApplyApplyError(id);
        if (applyLendBO != null) {
            WaitPayBO waitPayBO = BeanTransform.copyProperties(applyLendBO, WaitPayBO.class);
            waitPayBO.setPayStatus(PayStatus.WAITPAY);
            waitPayBO.setType("借款");
            return waitPayBO;
        } else {
            ReimburseRecordBO reimburseRecordBO = reimburseRecordAPI.getOneById(id);
            WaitPayBO waitPayBO = BeanTransform.copyProperties(reimburseRecordBO, WaitPayBO.class);
            waitPayBO.setLendDate(reimburseRecordBO.getOccureDate());
            waitPayBO.setLendMoney(reimburseRecordBO.getReimMoney());
            waitPayBO.setPayDate(reimburseRecordBO.getBudgetPayTime());
            waitPayBO.setPayStatus(PayStatus.WAITPAY);
            waitPayBO.setType("报销");
            return waitPayBO;
        }
    }
}