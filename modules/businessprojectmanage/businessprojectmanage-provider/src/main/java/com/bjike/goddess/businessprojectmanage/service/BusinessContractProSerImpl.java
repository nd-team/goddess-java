package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.api.BusinessContractProAPI;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractCollectBO;
import com.bjike.goddess.businessprojectmanage.bo.BusinessContractProBO;
import com.bjike.goddess.businessprojectmanage.dto.BusinessContractProDTO;
import com.bjike.goddess.businessprojectmanage.entity.BusinessContractPro;
import com.bjike.goddess.businessprojectmanage.enums.GuideAddrStatus;
import com.bjike.goddess.businessprojectmanage.enums.MakeContract;
import com.bjike.goddess.businessprojectmanage.enums.ProgressStatus;
import com.bjike.goddess.businessprojectmanage.excel.BusinessContractProExport;
import com.bjike.goddess.businessprojectmanage.to.BusinessContractProTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 项目合同基本信息业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-11 10:03 ]
 * @Description: [ 项目合同基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectmanageSerCache")
@Service
public class BusinessContractProSerImpl extends ServiceImpl<BusinessContractPro, BusinessContractProDTO> implements BusinessContractProSer {

    @Autowired
    BusinessContractProAPI businessContractProAPI;
    @Autowired
    UserAPI userAPI;
    @Autowired
    CusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getCusPermission("1", null);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（部门级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
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
            flag = cusPermissionSer.getCusPermission("1", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideManageIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guidePlanIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("4", null);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideBudgetIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.modCusPermission("5", null);
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
            case MANAGER:
                flag = guideManageIdentity();
                break;
            case PLAN:
                flag = guidePlanIdentity();
                break;
            case BUDGET:
                flag = guideBudgetIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public List<BusinessContractProBO> listBusinessContactProgress(BusinessContractProDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getProjectGroup())) {
            dto.getConditions().add(Restrict.eq("projectGroup", dto.getProjectGroup()));
        }
        if (StringUtils.isNotBlank(dto.getArea())) {
            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
        }

        List<BusinessContractPro> bos = super.findByCis(dto);
        for (BusinessContractPro businessContractPro : bos) {
            for (ProgressStatus progressStatus : ProgressStatus.values()) {
                if (progressStatus.getCode() == Integer.parseInt(businessContractPro.getProgressStatus())) {
                    businessContractPro.setProgressStatus(progressStatus.getRemark());
                    break;
                }
            }

        }
        List<BusinessContractProBO> businessContractProBOS = BeanTransform.copyProperties(bos, BusinessContractProBO.class, false);
        return businessContractProBOS;
    }

    @Override
    public Long countBusinessContactProgress(BusinessContractProDTO dto) throws SerException {

        return super.count(dto);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void addBusinessContactProgress(BusinessContractProTO to) throws SerException {

        BusinessContractPro entity = BeanTransform.copyProperties(to, BusinessContractPro.class, true);
        super.save(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateBusinessContactProgress(BusinessContractProTO to) throws SerException {
        BusinessContractPro entity = BeanTransform.copyProperties(to, BusinessContractPro.class, true);
        super.update(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void importExcel(List<BusinessContractProTO> tos) throws SerException {
        List<BusinessContractPro> businessContracts = new ArrayList<>(tos.size());
        for (BusinessContractProTO to : tos) {
            BusinessContractPro contract = BeanTransform.copyProperties(to, BusinessContractPro.class, true);
            businessContracts.add(contract);
        }
        super.save(businessContracts);
//        BusinessContractProBO bo = BeanTransform.copyProperties(new BusinessContractPro(), BusinessContractProBO.class);
    }

    @Override
    public byte[] exportExcel(BusinessContractProDTO dto) throws SerException {
        if (null != dto.getArea()) {
            dto.getConditions().add(Restrict.eq("area", dto.getArea()));
        }
        List<BusinessContractPro> list = super.findByCis(dto);
        List<BusinessContractProExport> exports = new ArrayList<>();
        list.stream().forEach(str -> {
            BusinessContractProExport export = BeanTransform.copyProperties(str, BusinessContractProExport.class,
                    "measurePass", "notification", "commonSubcontractor", "taskFinish", "taskContract",
                    "makeContract", "scaleBalance", "solutionBalance", "implement", "partial",
                    "persist", "settlementProcess", "account", "closeSingle", "archive", "summarizeFinish");
            //测算是否通过
            if (null != str.getMeasurePass()) {
                if (str.getMeasurePass().equals(true)) {
                    export.setMeasurePass("是");
                } else {
                    export.setMeasurePass("否");
                }

            }
            //是否通报
            if (null != str.getNotification()) {
                if (str.getNotification().equals(true)) {
                    export.setNotification("是");
                } else {
                    export.setNotification("否");
                }
            }
            //是否有共同分包单位
            if (null != str.getCommonSubcontractor()) {
                if (str.getCommonSubcontractor().equals(true)) {
                    export.setCommonSubcontractor("是");
                } else {
                    export.setCommonSubcontractor("否");
                }

            }
            //派工归属清理是否完成
            if (null != str.getTaskFinish()) {

                if (str.getTaskFinish().equals(true)) {
                    export.setTaskFinish("是");
                } else {
                    export.setTaskFinish("否");
                }
            }
            //是否有合同派工合同
            if (null != str.getTaskContract()) {
                if (str.getTaskContract().equals(true)) {
                    export.setTaskContract("是");
                } else {
                    export.setTaskContract("否");
                }

            }

            //合同规模数是否有差异
            if (null != str.getScaleBalance()) {
                if (str.getScaleBalance().equals(true)) {
                    export.setScaleBalance("是");
                } else {
                    export.setScaleBalance("否");
                }
            }
            //是否解决差异问题
            if (null != str.getSolutionBalance()) {

                if (str.getSolutionBalance().equals(true)) {
                    export.setSolutionBalance("是");
                } else {
                    export.setSolutionBalance("否");
                }
            }
            //预估项目是否确认实施
            if (null != str.getImplement()) {

                if (str.getImplement().equals(true)) {
                    export.setImplement("是");
                } else {
                    export.setImplement("否");
                }
            }
            //是否分批结算
            if (null != str.getPartial()) {
                if (str.getPartial().equals(true)) {
                    export.setPartial("是");
                } else {
                    export.setPartial("否");
                }

            }
            //是否为持续
            if (null != str.getPersist()) {
                if (str.getPersist().equals(true)) {
                    export.setPersist("是");
                } else {
                    export.setPersist("否");
                }

            }
            //是否正在走结算流程
            if (null != str.getSettlementProcess()) {
                if (str.getSettlementProcess().equals(true)) {
                    export.setSettlementProcess("是");
                } else {
                    export.setSettlementProcess("否");
                }
            }
            //是否到账
            if (null != str.getAccount()) {
                if (str.getAccount().equals(true)) {
                    export.setAccount("是");
                } else {
                    export.setAccount("否");
                }
            }
            //是否闭单
            if (null != str.getCloseSingle()) {
                if (str.getCloseSingle().equals(true)) {
                    export.setCloseSingle("是");
                } else {
                    export.setCloseSingle("否");
                }
            }
            //合同是否已归档
            if (null != str.getArchive()) {
                if (str.getArchive().equals(true)) {
                    export.setArchive("是");
                } else {
                    export.setArchive("否");
                }
            }
            //是否有合同立项
            export.setMakeContract(MakeContract.getStrConvert(str.getMakeContract()));

            //是否总结完成
            if (null != str.getSummarizeFinish()) {
                if (str.getSummarizeFinish().equals(true)) {
                    export.setSummarizeFinish("是");
                } else {
                    export.setSummarizeFinish("否");
                }
            }
            //实施状态
            if (null != str.getProgressStatus()) {

                export.setProgressStatus(ProgressStatus.getStrConvert(Integer.parseInt(str.getProgressStatus())));
            }
            exports.add(export);
        });
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(exports, excel);
        return bytes;
    }

    @Override
    public List<BusinessContractCollectBO> dayCollect(String time) throws SerException {
        if (StringUtils.isBlank(time)) {
            time = DateUtil.dateToString(LocalDate.now());
        }
        String startTime = time;
        String endTime = time;
        return progressCollect(startTime, endTime);
    }

    @Override
    public List<BusinessContractCollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        if (year == null || month == null || week == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
            Calendar c = Calendar.getInstance();
            week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        }
        LocalDate[] date = DateUtil.getWeekTimes(year, month, week);
        String startDate = String.valueOf(date[0]);
        String endDate = String.valueOf(date[1]);
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        if (year == null && month == null) {
            year = LocalDate.now().getYear();
            month = LocalDate.now().getMonthValue();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, month, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, month, DateUtil.getDayByDate(year, month)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractCollectBO> quarterCollect(Integer year, Integer quarter) throws SerException {
        if (year == null && quarter == null) {
            year = LocalDate.now().getYear();
            quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        }
        String[] date = quarter(year, quarter);
        String startDate = date[0];
        String endDate = date[1];
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractCollectBO> yearCollect(Integer year) throws SerException {
        if (year == null) {
            year = LocalDate.now().getYear();
        }
        String startDate = DateUtil.dateToString(LocalDate.of(year, 1, 1));
        String endDate = DateUtil.dateToString(LocalDate.of(year, 12, DateUtil.getDayByDate(year, 12)));
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractCollectBO> totalCollect(String time) throws SerException {
        String startDate = " ";
        String endDate = time;
        String sql = "select signedTime as signedTime from  businessprojectmanage_businesscontractpro where signedTime<= '" + endDate + "'";
        List<Object> objects = super.findBySql(sql);
        if (null != objects && objects.size() > 0) {
            startDate = String.valueOf(objects.get(0));
            endDate = StringUtils.isNotBlank(endDate) ? endDate : LocalDate.now().toString();
        }
        return progressCollect(startDate, endDate);
    }

    @Override
    public List<BusinessContractCollectBO> collectCollectEmail(String condition) throws SerException{
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonth().getValue();
        int quarter = (LocalDate.now().getMonthValue() + 2) / 3;
        String now = LocalDate.now().toString();
        switch (condition) {
            case "项目实施进度管理日汇总":
                String day = LocalDate.now().toString();
                return dayCollect(day);
            case "项目实施进度管理周汇总":
                return weekCollect(year, month, week);
            case "项目实施进度管理月汇总":
                return monthCollect(year, month);
            case "项目实施进度管理季度汇总":
                return quarterCollect(year, quarter);
            case "项目实施进度管理年汇总":
                return yearCollect(new Integer(year));
            case "项目实施进度管理累计汇总":
                return totalCollect(now);
            default:
                return null;
        }
    }


    private List<BusinessContractCollectBO> progressCollect(String startTime, String endTime) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT projectGroup, sum(scaleContract) AS scaleContract, count(1) as contractAmount, sum(makeMoney) as contractMoney, sum(makeMoney) as makeMoney, sum(forecastMoney) as forecastMoney from businessprojectmanage_businesscontractpro");
        sb.append(" WHERE signedTime between '" + startTime + "' and '" + endTime + "' GROUP BY projectGroup");
        String[] fields = new String[]{"projectGroup", "scaleContract", "contractAmount", "contractMoney", "makeMoney", "forecastMoney"};
        List<BusinessContractCollectBO> progressBOS = super.findBySql(sb.toString(), BusinessContractCollectBO.class, fields);
        List<BusinessContractCollectBO> boList = new ArrayList<>();
        List<BusinessContractCollectBO> contractProgressBOS = new ArrayList<>();
        Double forecastUnit = 0.0;
        Double makeUnit = 0.0;
        Double totalMoney = 0.0;
        if (progressBOS != null) {
            for (BusinessContractCollectBO bo : progressBOS) {
                if (bo.getMakeMoney() != null && bo.getForecastMoney() != null) {
                    //总金额(立项总金额+预估总金额)
                    totalMoney = bo.getMakeMoney() + bo.getForecastMoney();
                }
                bo.setTotalMoney(totalMoney);
                //合同立项规模
                String[] contractNumField = new String[]{"makeContractAmount"};
                String contractNumSql = " SELECT ifnull(count(makeContract),0) AS makeContractAmount FROM businessprojectmanage_businesscontractpro " +
                        " WHERE makeContract=1 AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(contractNumSql, BusinessContractCollectBO.class, contractNumField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setMakeContractAmout(bo1.getMakeContractAmout());
                }
                //合同立项金额
                String[] contractMoneyField = new String[]{"makeMoney"};
                String contractMoneySql = " SELECT ifnull(sum(makeMoney),0) AS makeMoney FROM businessprojectmanage_businesscontractpro WHERE makeContract=1 AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(contractMoneySql, BusinessContractCollectBO.class, contractMoneyField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setMakeMoney(bo1.getMakeMoney());
                }

                //合同预立项规模
                String[] contractNumField２ = new String[]{"forecastContractAmount"};
                String contractNumSql２ = " SELECT ifnull(count(makeContract),0) AS forecastContractAmount FROM businessprojectmanage_businesscontractpro " +
                        " WHERE makeContract=0 AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(contractNumSql２, BusinessContractCollectBO.class, contractNumField２);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setForecastContractAmount(bo1.getForecastContractAmount());
                }
                //合同预立项金额
                String[] contractMoneyField２ = new String[]{"forecastMoney"};
                String contractMoneySql2 = " SELECT ifnull(sum(forecastMoney),0) AS forecastMoney FROM businessprojectmanage_businesscontractpro " +
                        " WHERE makeContract=0 AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(contractMoneySql2, BusinessContractCollectBO.class, contractMoneyField２);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setForecastMoney(bo1.getForecastMoney());
                }

                //已完工单数
                String[] completeField = new String[]{"complete"};
                String completeSql = " SELECT ifnull(count(complete),0) AS complete FROM businessprojectmanage_businesscontractpro " +
                        " WHERE complete='是' AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(completeSql, BusinessContractCollectBO.class, completeField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setComplete(bo1.getComplete());
                }
                //完工金额
                String[] completeMoneyField = new String[]{"completeMoney"};
                String completeMoneySql = " SELECT ifnull(sum(taskMoney),0) AS completeMoney FROM businessprojectmanage_businesscontractpro " +
                        " WHERE complete='是' AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(completeMoneySql, BusinessContractCollectBO.class, completeMoneyField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setCompleteMoney(bo1.getCompleteMoney());
                }
                //未完工规模
                String[] notcompleteField = new String[]{"notCompleteContract"};
                String notcompleteSql = " SELECT ifnull(count(complete),0) AS notCompleteContract FROM businessprojectmanage_businesscontractpro " +
                        " WHERE complete='否' AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(notcompleteSql, BusinessContractCollectBO.class, notcompleteField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setNotCompleteContract(bo1.getNotCompleteContract());
                }
                //未完工金额
                String[] notcompleteMoneyField = new String[]{"notCompleteMoney"};
                String notcompleteMoneySql = " SELECT ifnull(sum(taskMoney),0) AS notCompleteMoney FROM businessprojectmanage_businesscontractpro " +
                        " WHERE complete='否' AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(notcompleteMoneySql, BusinessContractCollectBO.class, notcompleteMoneyField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setNotCompleteMoney(bo1.getNotCompleteMoney());
                }

                //需项目总结数量
                String[] needSummarizeAmoumtField = new String[]{"needSummarizeAmount"};
                String needSummarizeAmoumtSql = " SELECT ifnull(count(1),0) AS needSummarizeAmount FROM businessprojectmanage_businesscontractpro " +
                        " WHERE projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(needSummarizeAmoumtSql, BusinessContractCollectBO.class, needSummarizeAmoumtField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setNeedSummarizeAmount(bo1.getNeedSummarizeAmount());
                }
                //已完成项目总结
                String[] summarizeAmoumtField = new String[]{"summarizeAmount"};
                String summarizeAmoumtSql = " SELECT ifnull(count(1),0) AS summarizeAmount FROM businessprojectmanage_businesscontractpro " +
                        " WHERE progressStatus = 3 AND projectGroup='"+ bo.getProjectGroup() +"'";
                contractProgressBOS = super.findBySql(summarizeAmoumtSql, BusinessContractCollectBO.class, summarizeAmoumtField);
                for (BusinessContractCollectBO bo1 : contractProgressBOS) {
                    bo.setSummarizeAmount(bo1.getSummarizeAmount());
                }
                //未完结项目总结
                if (bo.getNeedSummarizeAmount() != null && bo.getSummarizeAmount() != null) {
                    bo.setNotSummarizeAmount(bo.getNeedSummarizeAmount() - bo.getSummarizeAmount());
                }

                boList.add(bo);
            }
        }
        return boList;
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