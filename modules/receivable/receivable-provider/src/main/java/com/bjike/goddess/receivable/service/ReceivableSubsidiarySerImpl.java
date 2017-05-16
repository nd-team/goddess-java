package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.bo.*;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.enums.AuditStatus;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 回款明细业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "receivableSerCache")
@Service
public class ReceivableSubsidiarySerImpl extends ServiceImpl<ReceivableSubsidiary, ReceivableSubsidiaryDTO> implements ReceivableSubsidiarySer {
    @Autowired
    private ContractorSer contractorSer;


    @Override
    public Long countReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        Long count = super.count(receivableSubsidiaryDTO);
        return count;
    }

    @Override
    public ReceivableSubsidiaryBO getOne(String id) throws SerException {
        ReceivableSubsidiary receivableSubsidiary = super.findById(id);
        return BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
    }

    @Override
    public List<ReceivableSubsidiaryBO> findListReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        List<ReceivableSubsidiary> receivableSubsidiaries = super.findByCis(receivableSubsidiaryDTO, true);
        return BeanTransform.copyProperties(receivableSubsidiaries, ReceivableSubsidiaryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivableSubsidiaryBO insertReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        Contractor contractor = contractorSer.findById(receivableSubsidiaryTO.getContractorId());
        ReceivableSubsidiary receivableSubsidiary = BeanTransform.copyProperties(receivableSubsidiaryTO, ReceivableSubsidiary.class, true);
        receivableSubsidiary.setContractor(contractor);
        receivableSubsidiary.setCreateTime(LocalDateTime.now());
        receivableSubsidiary = count(receivableSubsidiary);
        super.save(receivableSubsidiary);
        ReceivableSubsidiaryBO bo = BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
//        bo.setContractorBO( BeanTransform.copyProperties(receivableSubsidiary.getContractor(),ContractorBO.class));
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivableSubsidiaryBO editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        ReceivableSubsidiary receivableSubsidiary = super.findById(receivableSubsidiaryTO.getId());
        receivableSubsidiary = count(receivableSubsidiary);
        super.update(receivableSubsidiary);
        return BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
    }


    /**
     * 计算方法
     */
    public ReceivableSubsidiary count(ReceivableSubsidiary receivableSubsidiary) throws SerException {
        /*Contractor contractor = receivableSubsidiary.getContractor();
        if (null != contractor) {*/
        Contractor contractor = receivableSubsidiary.getContractor();
        //合同规模金额(派工单价*合同规模数)
        Double pactMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactNum();
        receivableSubsidiary.setPactMoney(pactMoney);
        //中兴派工金额(派工单价*已派工量)
        Double taskMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactSize();
        receivableSubsidiary.setTaskMoney(taskMoney);
        //已完工金额(派工单价*已完工量)
        receivableSubsidiary.setFinishMoney(receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getFinishNum());
        //未完工金额(派工单价*未完工量)
        receivableSubsidiary.setUnfinishMoney(receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getUnfinishNum());
        //管理费(实际数量金额*承包商比例)
        Double managementFee = contractor.getPercent() * receivableSubsidiary.getRealCountMoney();
        receivableSubsidiary.setManagementFee(managementFee);
        //到帐金额(实际数量金额-管理费)
        Double accountMoney = receivableSubsidiary.getRealCountMoney() - managementFee;
        receivableSubsidiary.setAccountMoney(accountMoney);
        //税金(到帐金额*6.79%)
        receivableSubsidiary.setTaxes(accountMoney * 0.0679);
        //税后金额(到帐金额-税金)
        Double afterTax = accountMoney - (accountMoney * 0.0679);
        receivableSubsidiary.setAfterTax(afterTax);
        //剩余结算量(已派工量-实际结算数量)
        Double moreNum = receivableSubsidiary.getPactSize() - receivableSubsidiary.getRealCountNum();
        receivableSubsidiary.setMoreNum(moreNum);
        //剩余结算金额(剩余结算量*派工单价)
        receivableSubsidiary.setMoreMoney(moreNum * receivableSubsidiary.getTaskPrice());
        return receivableSubsidiary;
        /*} else {
            throw new SerException(receivableSubsidiary.getContractor() + "承包商不存在，请在承包商列表添加,谢谢！");
        }*/
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeReceivableSubsidiary(String id) throws SerException {
        try {
            super.remove(id);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    /**
     * 签字审批时间
     *
     * @param auditTime
     */
    public List<String> auditTime(String auditTime) throws SerException {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(auditTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 10);
            String countTime = sdf.format(cal.getTime());//ERP结算审批时间
            list.add(countTime);

            cal.setTime(date);
            cal.add(Calendar.DATE, 20);
            String billTime = sdf.format(cal.getTime());//发票审核时间
            list.add(billTime);

            cal.setTime(date);
            cal.add(Calendar.MONTH, 3);
            String planTime = sdf.format(cal.getTime());//预计支付时间
            list.add(planTime);

            cal.setTime(date);
            cal.add(Calendar.MONTH, 4);
            String accountTime = sdf.format(cal.getTime());//到账时间
            list.add(accountTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * ERP结算审批时间
     *
     * @param countTime
     */
    public List<String> countTime(String countTime) throws SerException {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(countTime);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);
            cal.add(Calendar.DATE, 10);
            String billTime = sdf.format(cal.getTime());//发票审核时间
            list.add(billTime);

            cal.setTime(date);
            cal.add(Calendar.MONTH, 3);
            String planTime = sdf.format(cal.getTime());//预计支付时间
            list.add(planTime);

            cal.setTime(date);
            cal.add(Calendar.MONTH, 4);
            String accountTime = sdf.format(cal.getTime());//到账时间
            list.add(accountTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 发票审核时间
     *
     * @param billTime
     */
    public List<String> billTime(String billTime) throws SerException {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(billTime);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);
            cal.add(Calendar.MONTH, 3);
            String planTime = sdf.format(cal.getTime());//预计支付时间
            list.add(planTime);

            cal.setTime(date);
            cal.add(Calendar.MONTH, 4);
            String accountTime = sdf.format(cal.getTime());//到账时间
            list.add(accountTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 预计支付时间
     *
     * @param planTime
     */
    public String planTime(String planTime) throws SerException {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String accountTime = null;
        try {
            Date date = sdf.parse(planTime);
            Calendar cal = Calendar.getInstance();

            cal.setTime(date);
            cal.add(Calendar.MONTH, 3);
            accountTime = sdf.format(cal.getTime());//到账时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return accountTime;
    }

    @Override
    public void editTime(ReceivableSubsidiary receivableSubsidiary, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws SerException {
        ReceivableSubsidiary subsidiary = super.findById(receivableSubsidiary.getId());
        if (auditStatusStr != null) {
            subsidiary.setAuditStatus(AuditStatus.PASS);
        } else {
            subsidiary.setAuditStatus(AuditStatus.NOT);
        }
        if (countStatusStr != null) {
            subsidiary.setCountStatus(AuditStatus.PASS);
        } else {
            subsidiary.setCountStatus(AuditStatus.NOT);
        }
        if (billStatusStr != null) {
            subsidiary.setBillStatus(AuditStatus.PASS);
        } else {
            subsidiary.setBillStatus(AuditStatus.NOT);
        }
        if (planStatusStr != null) {
            subsidiary.setPlanStatus(AuditStatus.PASS);
        } else {
            subsidiary.setPlanStatus(AuditStatus.NOT);
        }
        subsidiary.setFinishTime(receivableSubsidiary.getFinishTime());
        subsidiary.setCheckTime(receivableSubsidiary.getCheckTime());
        subsidiary.setAuditTime(receivableSubsidiary.getAuditTime());
        subsidiary.setCountTime(receivableSubsidiary.getCountTime());
        subsidiary.setBillTime(receivableSubsidiary.getBillTime());
        subsidiary.setPlanTime(receivableSubsidiary.getPlanTime());
        subsidiary.setAccountTime(receivableSubsidiary.getAccountTime());
        super.update(subsidiary);
    }

    @Override
    public String exportExcel(String area, String start, String end) throws SerException {
        //todo:未做导出
        return null;
    }


    @Override
    public void input() throws SerException {
        //todo:未做导入
        return;

    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<ReceivableSubsidiaryBO> receivableSubsidiaryBOS = super.findBySql("select distinct area from receivable_receivablesubsidiary group by area order by area asc ", ReceivableSubsidiaryBO.class, fields);

        List<String> areasList = receivableSubsidiaryBOS.stream().map(ReceivableSubsidiaryBO::getArea)
                .filter(area -> (StringUtils.isNotBlank(area))).distinct().collect(Collectors.toList());


        return areasList;
    }

    @Override
    public List<String> getInnerName() throws SerException {
        String[] fields = new String[]{"innerName"};
        List<ReceivableSubsidiaryBO> receivableSubsidiaryBOS = super.findBySql("select distinct innerName from receivable_receivablesubsidiary group by innerName order by innerName asc ", ReceivableSubsidiaryBO.class, fields);

        List<String> innerNamesList = receivableSubsidiaryBOS.stream().map(ReceivableSubsidiaryBO::getInnerName)
                .filter(innerName -> (StringUtils.isNotBlank(innerName))).distinct().collect(Collectors.toList());


        return innerNamesList;
    }

    @Override
    public List<String> getContractor() throws SerException {
        String[] fields = new String[]{"name"};
        List<ContractorBO> contractorBOS = super.findBySql("select distinct name as contractor from receivable_contractor group by contractor order by contractor asc ", ContractorBO.class, fields);

        List<String> contractorList = contractorBOS.stream().map(ContractorBO::getName)
                .filter(name -> (StringUtils.isNotBlank(name))).distinct().collect(Collectors.toList());

        return contractorList;
    }

    @Override
    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        if (areas == null || areas.length <= 0) {
            throw new SerException("汇总失败，请选择地区");
        }
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM (SELECT area,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes, ");
        sb.append(" sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE area IN (%s) GROUP BY ");
        sb.append(" area ORDER BY area)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes,sum(afterTax) AS afterTax ");
        sb.append(" FROM (SELECT area,sum(managementFee) AS managementFee,sum(accountMoney) AS accountMoney, ");
        sb.append(" sum(taxes) AS taxes,sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE area IN (%s) GROUP BY ");
        sb.append(" area ORDER BY area)A ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr);
        String[] fields = new String[]{"area", "managementFee", "accountMoney", "taxes", "afterTax"};
        List<CollectAreaBO> collectAreaBOS = super.findBySql(sql, CollectAreaBO.class, fields);
        return collectAreaBOS;
    }

    @Override
    public List<CollectProjectNameBO> collectInnerName(String[] innerNames) throws SerException {
        if (innerNames == null || innerNames.length <= 0) {
            throw new SerException("汇总失败，请选择项目名称");
        }
        String[] innerNamesTemp = new String[innerNames.length];
        for (int i = 0; i < innerNames.length; i++) {
            innerNamesTemp[i] = "'" + innerNames[i] + "'";
        }
        String innerNameStr = StringUtils.join(innerNamesTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM (SELECT innerName,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes, ");
        sb.append(" sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE innerName IN (%s) GROUP BY ");
        sb.append(" innerName ORDER BY innerName)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS innerName,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes,sum(afterTax) AS afterTax ");
        sb.append(" FROM (SELECT innerName,sum(managementFee) AS managementFee,sum(accountMoney) AS accountMoney, ");
        sb.append(" sum(taxes) AS taxes,sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE innerName IN (%s) GROUP BY ");
        sb.append(" innerName ORDER BY innerName)A ");
        String sql = sb.toString();
        sql = String.format(sql, innerNameStr, innerNameStr);
        String[] fields = new String[]{"innerName", "managementFee", "accountMoney", "taxes", "afterTax"};
        List<CollectProjectNameBO> collectProjectNameBOS = super.findBySql(sql, CollectProjectNameBO.class, fields);
        return collectProjectNameBOS;
    }

    @Override
    public List<CollectContractorBO> collectContractor(String[] contractors) throws SerException {
        if (contractors == null || contractors.length <= 0) {
            throw new SerException("汇总失败，请选择总包单位");
        }
        String[] contractorsTemp = new String[contractors.length];
        for (int i = 0; i < contractors.length; i++) {
            contractorsTemp[i] = "'" + contractors[i] + "'";
        }
        String contractorStr = StringUtils.join(contractorsTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM (SELECT contractor,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes, ");
        sb.append(" sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE contractor IN (%s) GROUP BY ");
        sb.append(" contractor ORDER BY contractor)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS contractor,sum(managementFee) AS managementFee, ");
        sb.append(" sum(accountMoney) AS accountMoney,sum(taxes) AS taxes,sum(afterTax) AS afterTax ");
        sb.append(" FROM (SELECT contractor,sum(managementFee) AS managementFee,sum(accountMoney) AS accountMoney, ");
        sb.append(" sum(taxes) AS taxes,sum(afterTax) AS afterTax FROM receivable_receivablesubsidiary a ");
        sb.append(" WHERE contractor IN (%s) GROUP BY ");
        sb.append(" contractor ORDER BY contractor)A ");
        String sql = sb.toString();
        sql = String.format(sql, contractorStr, contractorStr);
        String[] fields = new String[]{"contractor", "managementFee", "accountMoney", "taxes", "afterTax"};
        List<CollectContractorBO> collectContractorBOS = super.findBySql(sql, CollectContractorBO.class, fields);
        return collectContractorBOS;
    }

    @Override
    public List<CollectAreaDetailBO> collectAreaDetail(String[] areas) throws SerException {
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT area,id as id,innerName AS innerName,taskPrice AS taskPrice,pactSize AS pactSize, ");
        sb.append(" finishTime AS finishTime,checkTime AS checkTime,auditTime AS auditTime, ");
        sb.append(" countTime AS countTime,billTime AS billTime,planTime AS planTime,accountTime AS accountTime, ");
        sb.append(" managementFee AS managementFee,accountMoney AS accountMoney,taxes AS taxes,afterTax AS afterTax,contractor_id AS contractor, ");
        sb.append(" is_flow AS is_flow,'详情' AS remark FROM receivable_receivablesubsidiary ");
        sb.append(" WHERE area IN (%s) GROUP BY id,innerName, taskPrice, pactSize, ");
        sb.append(" finishTime, checkTime, auditTime, countTime, billTime, planTime, ");
        sb.append(" accountTime, managementFee, accountMoney, taxes, afterTax, contractor, is_flow, remark, area ORDER BY area ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr);
        String[] fields = new String[]{"area", "id", "innerName", "taskPrice", "pactSize", "finishTime", "checkTime",
                "auditTime", "countTime", "billTime", "planTime", "accountTime", "managementFee", "accountMoney", "taxes", "afterTax",
                "contractor", "is_flow", "remark"};
        List<CollectAreaDetailBO> collectAreaDetailBOS = super.findBySql(sql, CollectAreaDetailBO.class, fields);

        /*ReceivableSubsidiaryTO to = new ReceivableSubsidiaryTO();
        String id = to.getId();
        ReceivableSubsidiary receivableSubsidiaries = super.findById(id);
        List<ReceivableSubsidiaryBO> receivableSubsidiaryBOS = BeanTransform.copyProperties(receivableSubsidiaries,ReceivableSubsidiaryBO.class);*/
        return collectAreaDetailBOS;
    }

    @Override
    public List<CollectProjectNameDetailBO> collectInnerNameDetail(String[] innerNames) throws SerException {
        return null;
    }

    @Override
    public List<CollectContractorBO> collectContractorDetail(String[] contractors) throws SerException {
        return null;
    }

    /*@Override
    public List<ReceivableSubsidiaryBO> collectCompare(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        try {
            if (StringUtils.isBlank(receivableSubsidiaryTO.getStartTime()) && StringUtils.isBlank(receivableSubsidiaryTO.getEndTime())) {
                throw new SerException("对比汇总日期不能为空");
            }
            ReceivableSubsidiaryDTO dto = new ReceivableSubsidiaryDTO();
            String accountTime = receivableSubsidiaryTO.getAccountTime();
            dto.getConditions().add(Restrict.eq("accountDate", accountTime));
            List<ReceivableSubsidiary> list = super.findByCis(dto);
            Double accountAll = list.stream().mapToDouble(ReceivableSubsidiary::getAccountMoney).sum();

            List<ReceivableSubsidiaryBO> pageList = new ArrayList<>();
            if (receivableSubsidiaryTO.equals("area")) {
                List<String> receivableString = this.getAreas();
                for (String rs : receivableString) {
                    List<ReceivableSubsidiary> receivableSubsidiarie = new ArrayList<ReceivableSubsidiary>();
                    for (ReceivableSubsidiary all : list) {
                        if (all.getArea().equals(rs)) {
                            receivableSubsidiarie.add(all);
                        }
                    }
                    Double taskPrice = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskPrice).sum();//派工单价
                    Double pactSize = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//派工数量
                    Double managementFee = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getManagementFee).sum();//管理费
                    Double accountMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getAccountMoney).sum();//到账金额
                    Double taxes = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaxes).sum();//税金
                    Double taskMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskMoney).sum();//税后金额
                    Double minusMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum()
                            - receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//差额
                    Double increase = 0.0;//增长率
                    if (accountMoney != 0) {
                        increase = minusMoney / accountMoney;
                    }
                    Double percentageTemp = 0.0;//百分比转换
                    Double percentage = 0.0;//百分比
                    String percentageStr = "0.00%";//百分比
                    if (accountMoney != 0) {
                        percentageTemp = accountMoney / accountAll * 100;
                        percentage = percentageTemp;
                        DecimalFormat df = new DecimalFormat("0.00");
                        percentageStr = df.format(percentageTemp) + "%";
                    }
                    ReceivableSubsidiaryBO receivableSubsidiaryBO = new ReceivableSubsidiaryBO(rs, taskPrice, pactSize, managementFee, accountMoney, taxes, taskMoney, minusMoney, increase, percentageStr, percentage);
                    pageList.add(receivableSubsidiaryBO);
                }
            } else if (receivableSubsidiaryTO.equals("innerName")) {
                List<String> receivableString = this.getInnerNames();
                for (String rs : receivableString) {
                    List<ReceivableSubsidiary> receivableSubsidiarie = new ArrayList<ReceivableSubsidiary>();
                    for (ReceivableSubsidiary all : list) {
                        if (all.getInnerName().equals(rs)) {
                            receivableSubsidiarie.add(all);
                        }
                    }
                    Double taskPrice = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskPrice).sum();//派工单价
                    Double pactSize = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//派工数量
                    Double managementFee = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getManagementFee).sum();//管理费
                    Double accountMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getAccountMoney).sum();//到账金额
                    Double taxes = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaxes).sum();//税金
                    Double taskMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskMoney).sum();//税后金额
                    Double minusMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum()
                            - receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//差额
                    Double increase = 0.0;//增长率
                    if (accountMoney != 0) {
                        increase = minusMoney / accountMoney;
                    }
                    Double percentageTemp = 0.0;//百分比转换
                    Double percentage = 0.0;//百分比
                    String percentageStr = "0.00%";//百分比
                    if (accountMoney != 0) {
                        percentageTemp = accountMoney / accountAll * 100;
                        percentage = percentageTemp;
                        DecimalFormat df = new DecimalFormat("0.00");
                        percentageStr = df.format(percentageTemp) + "%";
                    }
                    ReceivableSubsidiaryBO receivableSubsidiaryBO = new ReceivableSubsidiaryBO(rs, taskPrice, pactSize, managementFee, accountMoney, taxes, taskMoney, minusMoney, increase, percentageStr, percentage);
                    pageList.add(receivableSubsidiaryBO);
                }

            } else if (receivableSubsidiaryTO.equals("contractor")) {
                List<String> receivableString = this.getContractors();
                for (String rs : receivableString) {
                    List<ReceivableSubsidiary> receivableSubsidiarie = new ArrayList<ReceivableSubsidiary>();
                    for (ReceivableSubsidiary all : list) {
                        if (all.getContractor().equals(rs)) {
                            receivableSubsidiarie.add(all);
                        }
                    }
                    Double taskPrice = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskPrice).sum();//派工单价
                    Double pactSize = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//派工数量
                    Double managementFee = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getManagementFee).sum();//管理费
                    Double accountMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getAccountMoney).sum();//到账金额
                    Double taxes = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaxes).sum();//税金
                    Double taskMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getTaskMoney).sum();//税后金额
                    Double minusMoney = receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum()
                            - receivableSubsidiarie.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();//差额
                    Double increase = 0.0;//增长率
                    if (accountMoney != 0) {
                        increase = minusMoney / accountMoney;
                    }
                    Double percentageTemp = 0.0;//百分比转换
                    Double percentage = 0.0;//百分比
                    String percentageStr = "0.00%";//百分比
                    if (accountMoney != 0) {
                        percentageTemp = accountMoney / accountAll * 100;
                        percentage = percentageTemp;
                        DecimalFormat df = new DecimalFormat("0.00");
                        percentageStr = df.format(percentageTemp) + "%";
                    }
                    ReceivableSubsidiaryBO receivableSubsidiaryBO = new ReceivableSubsidiaryBO(rs, taskPrice, pactSize, managementFee, accountMoney, taxes, taskMoney, minusMoney, increase, percentageStr, percentage);
                    pageList.add(receivableSubsidiaryBO);
                }
            }
            //合计
            Double taskPrice = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getTaskPrice).sum();//派工单价
            Double pactSize = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getPactSize).sum();//派工数量
            Double managementFee = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getManagementFee).sum();//管理费
            Double accountMoney = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getAccountMoney).sum();//到账金额
            Double taxes = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getTaxes).sum();//税金
            Double taskMoney = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getTaskMoney).sum();//税后金额
            Double minusMoney = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getMinusMoney).sum();//差额
            Double increase = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getIncrease).sum();//增长率
            Double percentageDouble = pageList.stream().mapToDouble(ReceivableSubsidiaryBO::getPercentage).sum();//百分比
            DecimalFormat df = new DecimalFormat("0.00");
            String percentageStr = df.format(percentageDouble) + "%";
            Double percentage = 0.0;
            ReceivableSubsidiaryBO receivableSubsidiaryBO = new ReceivableSubsidiaryBO("合计", taskPrice, pactSize, managementFee, accountMoney, taxes, taskMoney, minusMoney, increase, percentageStr, percentage);
            pageList.add(receivableSubsidiaryBO);
            return pageList;
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }
    }
*/
    @Override
    public ReceivableSubsidiaryBO sendReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        //todo:未做发送邮件
        return null;
    }

}


