package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.receivable.bo.BackProgressBO;
import com.bjike.goddess.receivable.bo.BackProgressCollectBO;
import com.bjike.goddess.receivable.bo.CollectBO;
import com.bjike.goddess.receivable.bo.CollectDetailBO;
import com.bjike.goddess.receivable.dto.BackProgressDTO;
import com.bjike.goddess.receivable.entity.BackProgress;
import com.bjike.goddess.receivable.enums.GuideAddrStatus;
import com.bjike.goddess.receivable.excel.BackProgressTemplateExcel;
import com.bjike.goddess.receivable.to.BackProgressCollectTO;
import com.bjike.goddess.receivable.to.BackProgressTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.YearCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 回款进度业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "receivableSerCache")
@Service
public class BackProgressSerImpl extends ServiceImpl<BackProgress, BackProgressDTO> implements BackProgressSer {
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
    public Long count(BackProgressDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public BackProgressBO getOne(String id) throws SerException {
        BackProgress backProgress = super.findById(id);
        BackProgressBO backProgressBO = BeanTransform.copyProperties(backProgress, BackProgressBO.class);
        return backProgressBO;
    }

    @Override
    public List<BackProgressBO> list(BackProgressDTO dto) throws SerException {
        List<BackProgress> backProgresses = super.findByCis(dto);
        List<BackProgressBO> backProgressBOS = BeanTransform.copyProperties(backProgresses, BackProgressBO.class);
        return backProgressBOS;
    }

    @Override
    public BackProgressBO importExcel(List<BackProgressTO> backProgressTOS) throws SerException {
        List<BackProgress> backProgressList = new ArrayList<>(backProgressTOS.size());
        for (BackProgressTO to : backProgressTOS) {
            BackProgress backProgress = BeanTransform.copyProperties(to, BackProgress.class, true);
            backProgressList.add(backProgress);
        }
        super.save(backProgressList);
        BackProgressBO backProgressBO = BeanTransform.copyProperties(new BackProgress(), BackProgressBO.class);
        return backProgressBO;
    }

    @Override
    public byte[] templateExport() throws SerException {
        List<BackProgressTemplateExcel> templateExcels = new ArrayList<>();
        BackProgressTemplateExcel templateExcel = new BackProgressTemplateExcel();
        templateExcel.setOperatorName("运营商名称");
        templateExcel.setArea("地区");
        templateExcel.setContractor("外包单位");
        templateExcel.setType("类型");
        templateExcel.setMajor("专业");
        templateExcel.setTaskName("派工名称");
        templateExcel.setFactory("厂家");
        templateExcel.setSalesContractNum("销售合同号");
        templateExcel.setOutsourceContractNum("外包合同号");
        templateExcel.setTaskCase("派工情况");
        templateExcel.setTaskMoney(0.0);
        templateExcel.setCompleteStatus("实际完工状态");
        templateExcel.setCompleteTime(LocalDate.now());
        templateExcel.setClearingMoney(0.0);
        templateExcel.setAccountMoney(0.0);
        templateExcel.setClearingData(LocalDate.now());
        templateExcel.setSoftCoreReport(LocalDate.now());
        templateExcel.setSignature(LocalDate.now());
        templateExcel.setProgressB("结算进度:B");
        templateExcel.setDataUploadSystem(LocalDate.now());
        templateExcel.setManagerAudit(LocalDate.now());
        templateExcel.setAcceptApply(LocalDate.now());
        templateExcel.setOutsourceAudit(LocalDate.now());
        templateExcel.setOfficeAudit(LocalDate.now());
        templateExcel.setProgressC("结算进度:C");
        templateExcel.setSystemInvoice(LocalDate.now());
        templateExcel.setElectronicInvoice(LocalDate.now());
        templateExcel.setPhysicalInvoice(LocalDate.now());
        templateExcel.setFillInvoice(LocalDate.now());
        templateExcel.setPhysicalInvoiceComplete(LocalDate.now());
        templateExcel.setPayTime(LocalDate.now());
        templateExcel.setInvoice(LocalDate.now());
        templateExcel.setAdvanceAccountTime(LocalDate.now());
        templateExcel.setProgress(LocalDate.now());
        templateExcel.setArrivalTime(LocalDate.now());
        templateExcel.setKpi("kpi");
        templateExcel.setActualSituation("现场实际情况");
        templateExcel.setUnitType("设备型号");
        templateExcel.setTaskCaseRemark("大概描述项目派工的情况备注");
        templateExcel.setScaleNum(0.0);
        templateExcel.setApplyClearing("是");
        templateExcel.setInfluenceClearing("是");
        templateExcel.setClearingPlan("结算计划");
        templateExcel.setPerformProject("正在执行项目");
        templateExcel.setAscription("归属");
        templateExcel.setRemark("备注");

        templateExcels.add(templateExcel);

        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(templateExcels, excel);
        return bytes;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BackProgressBO edit(BackProgressTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            BackProgress backProgress = super.findById(to.getId());
            LocalDateTime createTime = backProgress.getCreateTime();
            backProgress = BeanTransform.copyProperties(to, BackProgress.class, true);
            backProgress.setCreateTime(createTime);
            backProgress.setModifyTime(LocalDateTime.now());
            super.update(backProgress);
            BackProgressBO backProgressBO = BeanTransform.copyProperties(backProgress, BackProgressBO.class);
            return backProgressBO;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<BackProgressCollectBO> backProgressCollect(BackProgressCollectTO to) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT operatorName AS operatorName,area AS area,contractor as contractor, ");
        sb.append(" type AS type,major AS major,taskName AS taskName,factory AS factory, ");
        sb.append(" salesContractNum AS salesContractNum,outsourceContractNum AS outsourceContractNum, ");
        sb.append(" taskCase AS taskCase,sum(taskMoney) AS taskMoney,completeStatus AS completeStatus, ");
        sb.append(" sum(clearingMoney) AS clearingMoney,sum(accountMoney) AS accountMoney, ");
        sb.append(" sum(scaleNum) AS scaleNum,is_applyClearing as applyClearing, ");
        sb.append(" is_influenceClearing AS influenceClearing,clearingPlan AS clearingPlan, ");
        sb.append(" performProject AS performProject,ascription AS ascription ");
        sb.append(" FROM receivable_backprogress where 1=1 ");
        //地区
        if (StringUtils.isNotBlank(to.getArea())) {
            sb.append(" and area = '" + to.getArea() + "' ");
        }
        //外包单位
        if (StringUtils.isNotBlank(to.getContractor())) {
            sb.append(" and contractor = '" + to.getContractor() + "' ");
        }
        //类型
        if (StringUtils.isNotBlank(to.getType())) {
            sb.append(" and type = '" + to.getType() + "' ");
        }
        //专业
        if (StringUtils.isNotBlank(to.getMajor())) {
            sb.append(" and major = '" + to.getMajor() + "' ");
        }
        //开发票
        if (StringUtils.isNotBlank(to.getInvoiceStartTime()) && StringUtils.isNotBlank(to.getInvoiceEndTime())) {
            sb.append(" and invoice between '" + to.getInvoiceStartTime() + "' and '" + to.getInvoiceEndTime() + "'");
        }
        //预收账款时间
        if (StringUtils.isNotBlank(to.getAdvanceAccountStartTime()) && StringUtils.isNotBlank(to.getAdvanceAccountEndTime())) {
            sb.append(" and advanceAccountTime between '" + to.getAdvanceAccountStartTime() + "' and '" + to.getAdvanceAccountEndTime() + "'");
        }
        //运营商名称
        if (StringUtils.isNotBlank(to.getOperatorName())) {
            sb.append(" and operatorName = '" + to.getOperatorName() + "'");
        }
        //派工情况
        if (StringUtils.isNotBlank(to.getTaskCase())) {
            sb.append(" and taskCase = '" + to.getTaskCase() + "' ");
        }
        //实际完成状态
        if (StringUtils.isNotBlank(to.getCompleteStatus())) {
            sb.append(" and completeStatus = '" + to.getCompleteStatus() + "'");
        }
        sb.append(" GROUP BY operatorName,area,contractor,type, ");
        sb.append(" major,taskName,factory,salesContractNum,outsourceContractNum,taskCase, ");
        sb.append(" completeStatus,is_applyClearing,is_influenceClearing,clearingPlan, ");
        sb.append(" performProject,ascription ");
        String[] fields = new String[]{"operatorName", "area", "contractor", "type", "major", "taskName",
                "factory", "salesContractNum", "outsourceContractNum", "taskCase", "taskMoney",
                "completeStatus", "clearingMoney", "accountMoney", "scaleNum", "applyClearing", "influenceClearing",
                "clearingPlan", "performProject", "ascription"};
        String sql = sb.toString();
        List<BackProgressCollectBO> backProgressCollectBOS = super.findBySql(sql, BackProgressCollectBO.class, fields);
        BackProgressCollectBO bo = new BackProgressCollectBO();
        // 派工金额
        Double taskMoney = backProgressCollectBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getTaskMoney()).sum();
        // 可结算金额
        Double clearingMoney = backProgressCollectBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getClearingMoney()).sum();
        // 已到账金额
        Double accountMoney = backProgressCollectBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getAccountMoney()).sum();
        // 总规模数
        Double scaleNum = backProgressCollectBOS.stream().filter(p -> p != null).mapToDouble(p -> p.getScaleNum()).sum();
        bo.setArea("合计");
        bo.setTaskMoney(taskMoney);
        bo.setClearingMoney(clearingMoney);
        bo.setAccountMoney(accountMoney);
        bo.setScaleNum(scaleNum);
        backProgressCollectBOS.add(bo);
        return backProgressCollectBOS;
    }

    @Override
    public Set<String> areas() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getArea());
        }
        return set;
    }

    @Override
    public Set<String> contractors() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getContractor());
        }
        return set;
    }

    @Override
    public Set<String> types() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getType());
        }
        return set;
    }

    @Override
    public Set<String> majors() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getMajor());
        }
        return set;
    }

    @Override
    public Set<String> operatorNames() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getOperatorName());
        }
        return set;
    }

    @Override
    public Set<String> taskCases() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getTaskCase());
        }
        return set;
    }

    @Override
    public Set<String> completeStatus() throws SerException {
        Set<String> set = new HashSet<>();
        List<BackProgress> list = super.findAll();
        for (BackProgress backProgress : list) {
            set.add(backProgress.getCompleteStatus());
        }
        return set;
    }

    @Override
    public List<CollectDetailBO> yearCollect(YearCollectTO to) throws SerException {
        List<CollectDetailBO> collectDetailBOS = new ArrayList<>();
        String start = null;
        String end = null;
        if (to.getYear() != null) {
            start = to.getYear() + "-01-01";
            end = to.getYear() + "-12-31";
        } else {
            start = LocalDate.now().getYear() + "-01-01";
            end = LocalDate.now().getYear() + "-12-31";
        }
        String[] field = new String[]{"subject", "total", "time"};
        //主营业务收入合计
        StringBuilder mainSql = new StringBuilder();
        mainSql.append("SELECT '主营业务收入' AS subject, sum(A.money) AS total, A.time AS time FROM" +
                "  (SELECT  money,substring(time,6,2)as time" +
                " FROM receivable_mainincome where time BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.time ");
        List<CollectBO> collectBOS = super.findBySql(mainSql.toString(), CollectBO.class, field);
        if (collectBOS != null && collectBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            collectBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);

        }
        //预收帐款时间合计
        StringBuilder accountSql = new StringBuilder();
        accountSql.append(" SELECT '预收帐款时间' AS subject, sum(A.taskCase) AS total, A.advanceAccountTime AS time FROM" +
                "  (SELECT  taskCase,substring(advanceAccountTime,6,2)as advanceAccountTime" +
                "   FROM receivable_backprogress where advanceAccountTime BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.advanceAccountTime ");
        List<CollectBO> accountBOS = super.findBySql(accountSql.toString(), CollectBO.class, field);
        if (accountBOS != null && accountBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            accountBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //资料上传ERP系统合计
        StringBuilder dataSql = new StringBuilder();
        dataSql.append(" SELECT '资料上传ERP系统' AS subject,sum(A.taskCase) AS total,A.dataUploadSystem AS time FROM" +
                "  (SELECT  taskCase,substring(dataUploadSystem,6,2)as dataUploadSystem" +
                "   FROM receivable_backprogress where dataUploadSystem BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.dataUploadSystem; ");
        List<CollectBO> dataCollectBOS = super.findBySql(dataSql.toString(), CollectBO.class, field);
        if (dataCollectBOS != null && dataCollectBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            dataCollectBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //群主（项目经理）审核合计
        StringBuilder managerSql = new StringBuilder();
        managerSql.append(" SELECT '群主（项目经理）审核' AS subject,sum(A.taskCase) AS total,managerAudit AS time FROM" +
                "  (SELECT  taskCase,substring(managerAudit,6,2)as managerAudit" +
                "   FROM receivable_backprogress where managerAudit BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.managerAudit ");
        List<CollectBO> managerBOS = super.findBySql(managerSql.toString(), CollectBO.class, field);
        if (managerBOS != null && managerBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            managerBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //预接收申请合计
        StringBuilder applySql = new StringBuilder();
        applySql.append(" SELECT '预接收申请' AS subject,sum(A.taskCase) AS total,A.acceptApply AS time FROM" +
                "  (SELECT  taskCase,substring(acceptApply,6,2)as acceptApply" +
                "   FROM receivable_backprogress where acceptApply BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.acceptApply ");
        List<CollectBO> applyBOS = super.findBySql(applySql.toString(), CollectBO.class, field);
        if (applyBOS != null && applyBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            applyBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //外包经理审核合计
        StringBuilder outSql = new StringBuilder();
        outSql.append(" SELECT '外包经理审核' AS subject,sum(A.taskCase) AS total,A.outsourceAudit AS time FROM" +
                "  (SELECT  taskCase,substring(outsourceAudit,6,2)as outsourceAudit" +
                "   FROM receivable_backprogress where outsourceAudit BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.outsourceAudit ");
        List<CollectBO> outBOS = super.findBySql(outSql.toString(), CollectBO.class, field);
        if (outBOS != null && outBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            outBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //办事处副总审批合计
        StringBuilder officeSql = new StringBuilder();
        officeSql.append(" SELECT '办事处副总审批' AS subject,sum(A.taskCase) AS total,A.officeAudit AS time FROM" +
                "  (SELECT  taskCase,substring(officeAudit,6,2)as officeAudit" +
                "  FROM receivable_backprogress where officeAudit BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.officeAudit");
        List<CollectBO> officeBOS = super.findBySql(officeSql.toString(), CollectBO.class, field);
        if (officeBOS != null && officeBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            officeBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //制作结算资料合计
        StringBuilder clearingSql = new StringBuilder();
        clearingSql.append(" SELECT '制作结算资料' AS subject,sum(A.taskCase) AS total,A.clearingData AS time FROM" +
                "  (SELECT  taskCase,substring(clearingData,6,2)as clearingData" +
                "   FROM receivable_backprogress where clearingData BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.clearingData ");
        List<CollectBO> clearingBOS = super.findBySql(clearingSql.toString(), CollectBO.class, field);
        if (clearingBOS != null && clearingBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            clearingBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //软调报告合计
        StringBuilder softSql = new StringBuilder();
        softSql.append(" SELECT '软调报告' AS subject,sum(A.taskCase) AS total,A.softCoreReport AS time FROM" +
                "  (SELECT  taskCase,substring(softCoreReport,6,2)as softCoreReport" +
                "   FROM receivable_backprogress where softCoreReport BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.softCoreReport ");
        List<CollectBO> softBOS = super.findBySql(softSql.toString(), CollectBO.class, field);
        if (softBOS != null && softBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            softBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //签字合计
        StringBuilder signatureSql = new StringBuilder();
        signatureSql.append(" SELECT '签字' AS subject,sum(A.taskCase) AS total,A.signature AS time FROM" +
                "  (SELECT  taskCase,substring(signature,6,2)as signature" +
                "   FROM receivable_backprogress where signature BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.signature");
        List<CollectBO> signatureBOS = super.findBySql(signatureSql.toString(), CollectBO.class, field);
        if (signatureBOS != null && signatureBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            signatureBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
        }
        //完工时间合计
        StringBuilder completeSql = new StringBuilder();
        completeSql.append(" SELECT '完工时间' AS subject,sum(A.taskCase) AS total,A.completeTime AS time FROM" +
                "  (SELECT  taskCase,substring(completeTime,6,2)as completeTime" +
                "   FROM receivable_backprogress where completeTime BETWEEN  '" + start + "' AND  '" + end + "')A GROUP BY A.completeTime ");
        List<CollectBO> completeBOS = super.findBySql(completeSql.toString(), CollectBO.class, field);
        if (completeBOS != null && completeBOS.size() > 0) {
            CollectDetailBO bo = new CollectDetailBO();
            completeBOS.stream().forEach(str -> {
                Double money = str.getTotal();
                switch (str.getTime()) {
                    case "01":
                        bo.setJanuary(money);
                        break;
                    case "02":
                        bo.setFebruary(money);
                        break;
                    case "03":
                        bo.setMarch(money);
                        break;
                    case "04":
                        bo.setApril(money);
                        break;
                    case "05":
                        bo.setMay(money);
                        break;
                    case "06":
                        bo.setJune(money);
                        break;
                    case "07":
                        bo.setJuly(money);
                        break;
                    case "08":
                        bo.setAugust(money);
                        break;
                    case "09":
                        bo.setSeptember(money);
                        break;
                    case "10":
                        bo.setOctober(money);
                        break;
                    case "11":
                        bo.setNovember(money);
                        break;
                    case "12":
                        bo.setDecember(money);
                        break;
                    default:
                        break;
                }
                bo.setSubject(str.getSubject());
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getFebruary() == null) {
                    bo.setFebruary(0d);
                }
                if (bo.getMarch() == null) {
                    bo.setMarch(0d);
                }
                if (bo.getApril() == null) {
                    bo.setApril(0d);
                }
                if (bo.getMay() == null) {
                    bo.setMay(0d);
                }
                if (bo.getJanuary() == null) {
                    bo.setJanuary(0d);
                }
                if (bo.getJune() == null) {
                    bo.setJune(0d);
                }
                if (bo.getJuly() == null) {
                    bo.setJuly(0d);
                }
                if (bo.getAugust() == null) {
                    bo.setAugust(0d);
                }
                if (bo.getSeptember() == null) {
                    bo.setSeptember(0d);
                }
                if (bo.getOctober() == null) {
                    bo.setOctober(0d);
                }
                if (bo.getNovember() == null) {
                    bo.setNovember(0d);
                }
                if (bo.getDecember() == null) {
                    bo.setDecember(0d);
                }

            });
            Double total = bo.getJanuary() + bo.getFebruary() + bo.getMarch() + bo.getApril() +
                    bo.getMay() + bo.getJune() + bo.getJuly() + bo.getAugust() + bo.getSeptember() +
                    bo.getOctober() + bo.getNovember() + bo.getDecember();
            bo.setTotal(total);
            collectDetailBOS.add(bo);
            try {
                subtotal(collectDetailBOS);
                aduitMoney(collectDetailBOS);
                dataMoney(collectDetailBOS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return collectDetailBOS;
    }

    //小计
    private void subtotal(List<CollectDetailBO> collectDetailBOS) throws Exception {
        CollectDetailBO detailBO = new CollectDetailBO();
        detailBO.setSubject("小计");
        Double total = 0.0;
        String[] collectFields = new String[]{"january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december"};
        for (String f : collectFields) {
            for (CollectDetailBO bo : collectDetailBOS) {
                if (bo.getSubject().equals("主营业务收入") || bo.getSubject().equals("预收帐款时间")) {
                    List<Field> fields = ClazzUtils.getFields(bo.getClass());
                    for (Field field : fields) {
                        if (f.equals(field.getName())) {
                            field.setAccessible(true);
                            Object val = field.get(bo);
                            setVal(detailBO, f, val);
                            break;
                        }
                    }
                }

            }
        }
        total = detailBO.getJanuary() + detailBO.getFebruary() + detailBO.getMarch() + detailBO.getApril() +
                detailBO.getMay() + detailBO.getJune() + detailBO.getJuly() + detailBO.getAugust() + detailBO.getSeptember() +
                detailBO.getOctober() + detailBO.getNovember() + detailBO.getDecember();

        detailBO.setTotal(total);
        collectDetailBOS.add(detailBO);
    }

    //审批完成金额
    private void aduitMoney(List<CollectDetailBO> collectDetailBOS) throws Exception {
        CollectDetailBO detailBO = new CollectDetailBO();
        detailBO.setSubject("审批完成金额");
        Double total = 0.0;
        String[] collectFields = new String[]{"january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december"};
        for (String f : collectFields) {
            for (CollectDetailBO bo : collectDetailBOS) {
                if (bo.getSubject().equals("资料上传ERP系统") || bo.getSubject().equals("群主（项目经理）审核")
                        || bo.getSubject().equals("预接收申请") || bo.getSubject().equals("外包经理审核")
                        || bo.getSubject().equals("办事处副总审批")) {
                    List<Field> fields = ClazzUtils.getFields(bo.getClass());
                    for (Field field : fields) {
                        if (f.equals(field.getName())) {
                            field.setAccessible(true);
                            Object val = field.get(bo);
                            setVal(detailBO, f, val);
                            break;
                        }
                    }
                }
            }
        }
        total = detailBO.getJanuary() + detailBO.getFebruary() + detailBO.getMarch() + detailBO.getApril() +
                detailBO.getMay() + detailBO.getJune() + detailBO.getJuly() + detailBO.getAugust() + detailBO.getSeptember() +
                detailBO.getOctober() + detailBO.getNovember() + detailBO.getDecember();
        detailBO.setTotal(total);
        collectDetailBOS.add(detailBO);
    }

    //结算资料完成金额
    private void dataMoney(List<CollectDetailBO> collectDetailBOS) throws Exception {
        CollectDetailBO detailBO = new CollectDetailBO();
        detailBO.setSubject("结算资料完成金额");
        Double total = 0.0;
        String[] collectFields = new String[]{"january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december"};
        for (String f : collectFields) {
            for (CollectDetailBO bo : collectDetailBOS) {
                if (bo.getSubject().equals("制作结算资料") || bo.getSubject().equals("软调报告")
                        || bo.getSubject().equals("签字")) {
                    List<Field> fields = ClazzUtils.getFields(bo.getClass());
                    for (Field field : fields) {
                        if (f.equals(field.getName())) {
                            field.setAccessible(true);
                            Object val = field.get(bo);
                            setVal(detailBO, f, val);
                            break;
                        }
                    }
                }
            }
        }
        total = detailBO.getJanuary() + detailBO.getFebruary() + detailBO.getMarch() + detailBO.getApril() +
                detailBO.getMay() + detailBO.getJune() + detailBO.getJuly() + detailBO.getAugust() + detailBO.getSeptember() +
                detailBO.getOctober() + detailBO.getNovember() + detailBO.getDecember();
        detailBO.setTotal(total);
        collectDetailBOS.add(detailBO);
    }

    private void setVal(CollectDetailBO detailBO, String fieldName, Object val) throws Exception {
        List<Field> fields = ClazzUtils.getFields(detailBO.getClass());
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) {
                field.setAccessible(true);
                double v = null != val ? Double.parseDouble(String.valueOf(val)) : 0;
                double o_v = field.get(detailBO) != null ? Double.parseDouble(String.valueOf(field.get(detailBO))) : 0;
                field.set(detailBO, v + o_v);
                break;
            }
        }
    }
}