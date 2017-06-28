package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.receivable.bo.*;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.enums.AuditStatus;
import com.bjike.goddess.receivable.enums.CompareStatus;
import com.bjike.goddess.receivable.enums.TimeStatus;
import com.bjike.goddess.receivable.excel.ReceivableSubsidiaryExport;
import com.bjike.goddess.receivable.to.CollectCompareTO;
import com.bjike.goddess.receivable.to.ProgressTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        receivableSubsidiaryDTO.getSorts().add("createTime=desc");
        List<ReceivableSubsidiary> receivableSubsidiaries = super.findByCis(receivableSubsidiaryDTO, true);
        List<ReceivableSubsidiaryBO> bo = BeanTransform.copyProperties(receivableSubsidiaries, ReceivableSubsidiaryBO.class);
        for (int i = 0; i < receivableSubsidiaries.size(); i++) {
            ReceivableSubsidiary temp = receivableSubsidiaries.get(i);
            ContractorBO cbo = BeanTransform.copyProperties(temp.getContractor(), ContractorBO.class);
            bo.get(i).setContractorBO(cbo);
        }
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivableSubsidiaryBO insertReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        Contractor contractor = contractorSer.findById(receivableSubsidiaryTO.getContractorId());
        ReceivableSubsidiary receivableSubsidiary = BeanTransform.copyProperties(receivableSubsidiaryTO, ReceivableSubsidiary.class, true);
        receivableSubsidiary.setContractor(contractor);
        receivableSubsidiary.setCreateTime(LocalDateTime.now());
        //合同规模金额(派工单价*合同规模数)
        Double pactMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactNum();
        receivableSubsidiary.setPactMoney(pactMoney);
        //中兴派工金额(派工单价*已派工量)
        Double taskMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactSize();
        receivableSubsidiary.setTaskMoney(taskMoney);
        //已完工金额(派工单价*已完工量)
        Double finishMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getFinishNum();
        receivableSubsidiary.setFinishMoney(finishMoney);
        //未完工金额(派工单价*未完工量)
        Double unfinishMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getUnfinishNum();
        receivableSubsidiary.setUnfinishMoney(unfinishMoney);
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

        super.save(receivableSubsidiary);
        ReceivableSubsidiaryBO bo = BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
//        bo.setContractorBO( BeanTransform.copyProperties(receivableSubsidiary.getContractor(),ContractorBO.class));
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ReceivableSubsidiaryBO editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        Contractor contractor = contractorSer.findById(receivableSubsidiaryTO.getContractorId());
        ReceivableSubsidiary receivableSubsidiary = super.findById(receivableSubsidiaryTO.getId());
        BeanUtils.copyProperties(receivableSubsidiaryTO, receivableSubsidiary);
        receivableSubsidiary.setContractor(contractor);
        receivableSubsidiary.setModifyTime(LocalDateTime.now());

        //合同规模金额(派工单价*合同规模数)
        Double pactMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactNum();
        receivableSubsidiary.setPactMoney(pactMoney);
        //中兴派工金额(派工单价*已派工量)
        Double taskMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getPactSize();
        receivableSubsidiary.setTaskMoney(taskMoney);
        //已完工金额(派工单价*已完工量)
        Double finishMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getFinishNum();
        receivableSubsidiary.setFinishMoney(finishMoney);
        //未完工金额(派工单价*未完工量)
        Double unfinishMoney = receivableSubsidiary.getTaskPrice() * receivableSubsidiary.getUnfinishNum();
        receivableSubsidiary.setUnfinishMoney(unfinishMoney);
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
        //receivableSubsidiary = count(receivableSubsidiary);
        super.update(receivableSubsidiary);
        return BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
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
    public ReceivableSubsidiaryBO progress(ProgressTO to) throws SerException {
        ReceivableSubsidiary receivableSubsidiary = super.findById(to.getId());
        String a = StringUtils.substring(to.getGroup(), 0, 1);
        //StringUtils.substring("结算进度A","结算进度A".length()-1))
        if (a.equals("A")) {
            receivableSubsidiary.setProgressA(to.getProgress());
        } else if (a.equals("B")) {
            receivableSubsidiary.setProgressB(to.getProgress());
        } else if (a.equals("C")) {
            receivableSubsidiary.setProgressC(to.getProgress());
        } else if (a.equals("D")) {
            receivableSubsidiary.setProgressD(to.getProgress());
        }

        super.update(receivableSubsidiary);
        return BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
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
        List<ContractorBO> contractorBOS = super.findBySql("SELECT b.name FROM receivable_receivablesubsidiary a ,receivable_contractor b where a.contractor_id = b.id GROUP BY b.name ", ContractorBO.class, fields);

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
        sb.append(" SELECT b.name ,sum(a.accountMoney)as accountMoney,sum(a.managementFee)as managementFee, ");
        sb.append(" sum(a.taxes)as taxes,sum(a.afterTax )as afterTax  from receivable_receivablesubsidiary a, ");
        sb.append(" receivable_contractor b where a.contractor_id=b.id and b.name IN (%s) GROUP BY b.name ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' as name,sum(accountMoney)as managementFee,sum(managementFee)as managementFee, ");
        sb.append(" sum(taxes)as taxes,sum(afterTax) as afterTax FROM ");
        sb.append(" (SELECT b.name ,sum(a.accountMoney)as accountMoney,sum(a.managementFee)as managementFee, ");
        sb.append(" sum(a.taxes)as taxes,sum(a.afterTax )as afterTax  from receivable_receivablesubsidiary a, ");
        sb.append(" receivable_contractor b where a.contractor_id=b.id and b.name IN (%s) GROUP BY b.name)c ");
        String sql = sb.toString();
        sql = String.format(sql, contractorStr, contractorStr);
        String[] fields = new String[]{"name", "managementFee", "accountMoney", "taxes", "afterTax"};
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


        sb.append(" select a.area,a.innerName,a.taskPrice,a.pactSize,a.finishTime, ");
        sb.append(" a.checkTime ,a.auditTime,a.countTime,a.billTime,a.planTime,a.accountTime, ");
        sb.append(" a.accountMoney,a.managementFee,a.taxes,a.afterTax,b.name,a.is_flow,a.id as remark ");
        sb.append(" from receivable_receivablesubsidiary a ,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and a.area in (%s) ");
        sb.append(" union ");
        sb.append(" select '合计' as area,null as innerName, ");
        sb.append(" sum(taskPrice) as taskPrice,sum(pactSize) as pactSize, ");
        sb.append(" null as finishTime,null as checkTime, null as auditTime, ");
        sb.append(" null as countTime,null as billTime,null as planTime, ");
        sb.append(" null as accountTime,sum(accountMoney)as accountMoney, ");
        sb.append(" sum(managementFee)as managementFee,sum(taxes)as taxes , ");
        sb.append(" sum(afterTax) as afterTax,null as name,null as is_flow,null as remark ");
        sb.append(" from (select a.area,a.innerName,a.taskPrice,a.pactSize, ");
        sb.append(" a.finishTime,a.checkTime ,a.auditTime,a.countTime,a.billTime, ");
        sb.append(" a.planTime,a.accountTime,a.accountMoney,a.managementFee,a.taxes,a.afterTax,b.name,a.is_flow,a.id as remark ");
        sb.append(" from receivable_receivablesubsidiary a ,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and a.area in (%s) )c ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr);
        String[] fields = new String[]{"area", "innerName", "taskPrice", "pactSize", "finishTime", "checkTime",
                "auditTime", "countTime", "billTime", "planTime", "accountTime", "managementFee", "accountMoney", "taxes", "afterTax",
                "name", "isflow", "remark"};

        List<CollectAreaDetailBO> collectAreaDetailBOS = super.findBySql(sql, CollectAreaDetailBO.class, fields);

        /*ReceivableSubsidiaryTO to = new ReceivableSubsidiaryTO();
        String id = to.getId();
        ReceivableSubsidiary receivableSubsidiaries = super.findById(id);
        List<ReceivableSubsidiaryBO> receivableSubsidiaryBOS = BeanTransform.copyProperties(receivableSubsidiaries,ReceivableSubsidiaryBO.class);*/
        return collectAreaDetailBOS;
    }

    @Override
    public List<CollectProjectNameDetailBO> collectInnerNameDetail(String[] innerNames) throws SerException {
        String[] innerNamesTemp = new String[innerNames.length];
        for (int i = 0; i < innerNames.length; i++) {
            innerNamesTemp[i] = "'" + innerNames[i] + "'";
        }
        String innerNamesStr = StringUtils.join(innerNamesTemp, ",");
        StringBuilder sb = new StringBuilder();


        sb.append(" select a.innerName,a.area,a.taskPrice,a.pactSize,a.finishTime, ");
        sb.append(" a.checkTime ,a.auditTime,a.countTime,a.billTime,a.planTime,a.accountTime, ");
        sb.append(" a.accountMoney,a.managementFee,a.taxes,a.afterTax,b.name,a.is_flow, ");
        sb.append(" a.id as remark from receivable_receivablesubsidiary a ,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and a.innerName in (%s) ");
        sb.append(" union ");
        sb.append(" select '合计' as innerName,null as area, ");
        sb.append(" sum(taskPrice) as taskPrice,sum(pactSize) as pactSize, ");
        sb.append(" null as finishTime,null as checkTime, null as auditTime, ");
        sb.append(" null as countTime,null as billTime,null as planTime, ");
        sb.append(" null as accountTime,sum(accountMoney)as accountMoney, ");
        sb.append(" sum(managementFee)as managementFee,sum(taxes)as taxes , ");
        sb.append(" sum(afterTax) as afterTax,null as name,null as is_flow,null as remark ");
        sb.append(" from (select a.innerName,a.area,a.taskPrice,a.pactSize, ");
        sb.append(" a.finishTime,a.checkTime ,a.auditTime,a.countTime,a.billTime, ");
        sb.append(" a.planTime,a.accountTime,a.accountMoney,a.managementFee, ");
        sb.append(" a.taxes,a.afterTax,b.name,a.is_flow,a.id as remark from receivable_receivablesubsidiary a ,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and a.innerName in (%s) )c ");
        String sql = sb.toString();
        sql = String.format(sql, innerNamesStr, innerNamesStr);
        String[] fields = new String[]{"innerName", "area", "taskPrice", "pactSize", "finishTime", "checkTime",
                "auditTime", "countTime", "billTime", "planTime", "accountTime", "managementFee", "accountMoney", "taxes", "afterTax",
                "name", "isflow", "remark"};
        List<CollectProjectNameDetailBO> collectProjectNameDetailBOS = super.findBySql(sql, CollectProjectNameDetailBO.class, fields);
        return collectProjectNameDetailBOS;
    }

    @Override
    public List<CollectContractorDetailBO> collectContractorDetail(String[] contractors) throws SerException {
        String[] contractorsTemp = new String[contractors.length];
        for (int i = 0; i < contractors.length; i++) {
            contractorsTemp[i] = "'" + contractors[i] + "'";
        }
        String contractorsStr = StringUtils.join(contractorsTemp, ",");
        StringBuilder sb = new StringBuilder();


        sb.append(" select b.name,a.area,a.innerName,a.taskPrice,a.pactSize,a.finishTime, ");
        sb.append(" a.checkTime ,a.auditTime,a.countTime,a.billTime,a.planTime,a.accountTime, ");
        sb.append(" a.accountMoney,a.managementFee,a.taxes,a.afterTax,a.is_flow, ");
        sb.append(" a.id as remark from receivable_receivablesubsidiary a,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and b.name IN (%s) ");
        sb.append(" union ");
        sb.append(" select '合计' as name,null as area,null as innerName, ");
        sb.append(" sum(taskPrice) as taskPrice,sum(pactSize) as pactSize, ");
        sb.append(" null as finishTime,null as checkTime, null as auditTime, ");
        sb.append(" null as countTime,null as billTime,null as planTime, ");
        sb.append(" null as accountTime,sum(accountMoney)as accountMoney, ");
        sb.append(" sum(managementFee)as managementFee,sum(taxes)as taxes, ");
        sb.append(" sum(afterTax) as afterTax,null as is_flow,null as remark ");
        sb.append(" from (select b.name,a.area,a.innerName,a.taskPrice,a.pactSize,a.finishTime,a.checkTime, ");
        sb.append(" a.auditTime,a.countTime,a.billTime,a.planTime,a.accountTime,a.accountMoney,a.managementFee, ");
        sb.append(" a.taxes,a.afterTax,a.is_flow,a.id as remark ");
        sb.append(" from receivable_receivablesubsidiary a ,receivable_contractor b ");
        sb.append(" where a.contractor_id = b.id and b.name IN (%s))c ");
        String sql = sb.toString();
        sql = String.format(sql, contractorsStr, contractorsStr);
        String[] fields = new String[]{"name", "area", "innerName", "taskPrice", "pactSize", "finishTime", "checkTime",
                "auditTime", "countTime", "billTime", "planTime", "accountTime", "managementFee", "accountMoney", "taxes", "afterTax",
                "isflow", "remark"};
        List<CollectContractorDetailBO> collectContractorDetailBOS = super.findBySql(sql, CollectContractorDetailBO.class, fields);
        return collectContractorDetailBOS;
    }

    @Override
    public ReceivableSubsidiaryBO collectId(String id) throws SerException {
        ReceivableSubsidiary receivableSubsidiary = super.findById(id);
        ReceivableSubsidiaryBO bo = BeanTransform.copyProperties(receivableSubsidiary, ReceivableSubsidiaryBO.class);
        ContractorBO cbo = new ContractorBO();
        if (null != receivableSubsidiary.getContractor()) {
            cbo = BeanTransform.copyProperties(receivableSubsidiary.getContractor(), ContractorBO.class);
        }
        bo.setContractorBO(cbo);
        return bo;

    }

    @Override
    public List<CollectCompareBO> collectCompare(CollectCompareTO to) throws SerException {
        ReceivableSubsidiaryDTO dto = new ReceivableSubsidiaryDTO();
        String startTime = to.getStartTime();
        String endTime = to.getEndTime();
        List<ReceivableSubsidiary> receivableSubsidiaries = new ArrayList<>();
        for(ReceivableSubsidiary receivableSubsidiary : receivableSubsidiaries){
            if(receivableSubsidiary.getAccountTime().equals(TimeStatus.MONTH)){
                startTime=DateUtil.getStartMonth().toString();
                endTime=DateUtil.getEndMonth().toString();
            }else if(receivableSubsidiary.getAccountTime().equals(TimeStatus.QUARTER)){
                startTime = DateUtil.getStartMonth().toString();
                endTime = DateUtil.getEndMonth().toString();
            }else if(receivableSubsidiary.getAccountTime().equals(TimeStatus.YEAR)){
                startTime = DateUtil.getStartYear().toString();
                endTime = DateUtil.getEndYear().toString();
            }
        }


        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            dto.getConditions().add(Restrict.between("accountTime", new String[]{startTime, endTime}));
        }
        List<ReceivableSubsidiary> list = super.findByCis(dto);

        return collectCount(list, to.getCompareStatus());

    }

    private List<CollectCompareBO> collectCount(List<ReceivableSubsidiary> list, CompareStatus status) throws SerException {
        List<CollectCompareBO> collectCompareBOS = new ArrayList<>();

        List<String> conis = null;
        if (status.equals(CompareStatus.AREA)) {
            conis = this.getArea();
        } else if (status.equals(CompareStatus.PROJECT)) {
            conis = this.getInnerName();
        } else if (status.equals(CompareStatus.UNIT)) {
            conis = this.getContractor();
        }
        if (null != conis) {
            for (String con : conis) {
                List<ReceivableSubsidiary> receivableSubsidiaries = new ArrayList<>();
                for (ReceivableSubsidiary receivableSubsidiary : list) {
                    if (receivableSubsidiary.getArea().equals(con) || receivableSubsidiary.getInnerName().equals(con)
                            || receivableSubsidiary.getContractor().getName().equals(con)) {
                        receivableSubsidiaries.add(receivableSubsidiary);
                    }

                }
                //派工单价
                Double taskPrice = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getTaskPrice).sum();
                //派工数量
                Double pactSize = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getPactSize).sum();
                //到账金额
                Double accountMoney = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getAccountMoney).sum();
                //管理费
                Double managementFee = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getManagementFee).sum();
                //税金
                Double taxes = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getTaxes).sum();
                //税后金额
                Double afterTax = receivableSubsidiaries.stream().mapToDouble(ReceivableSubsidiary::getAfterTax).sum();

                CollectCompareBO bo = new CollectCompareBO(con, status, taskPrice, pactSize, accountMoney, managementFee, taxes, afterTax);
                collectCompareBOS.add(bo);
            }

        }
        return collectCompareBOS;

    }
   @Override
    public ReceivableSubsidiaryBO importExcel(List<ReceivableSubsidiaryTO> receivableSubsidiaryTOS) throws SerException {
       List<ReceivableSubsidiary> receivableSubsidiaries = BeanTransform.copyProperties(receivableSubsidiaryTOS, ReceivableSubsidiary.class, true);
       receivableSubsidiaries.stream().forEach(str -> {
           str.setCreateTime(LocalDateTime.now());
           str.setModifyTime(LocalDateTime.now());
       });
       super.save(receivableSubsidiaries);

       ReceivableSubsidiaryBO bo = BeanTransform.copyProperties(new ReceivableSubsidiary(), ReceivableSubsidiaryBO.class);
       return bo;
    }

    @Override
    public byte[] exportExcel(ReceivableSubsidiaryDTO dto) throws SerException{
        if(null != dto.getArea()){
            dto.getConditions().add(Restrict.in("area",dto.getArea()));
        }
        String startTime = dto.getStartTime();
        String endTime = dto.getEndTime();
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            String [] condi = new String[]{startTime,endTime};
            dto.getConditions().add(Restrict.between("accountTime",condi));
        }
        List<ReceivableSubsidiary> list = super.findByCis(dto);
        List<ReceivableSubsidiaryExport> exports = new ArrayList<>();
        list.stream().forEach(str->{
            ReceivableSubsidiaryExport export = BeanTransform.copyProperties(str,ReceivableSubsidiaryExport.class,"contractor","pay","frame","pact","flow");
            if(null != str.getContractor().getName()){
                export.setContractor( str.getContractor().getName());
            }
            if(str.getPay().equals(true)){
                export.setPay("是");
            }else{
                export.setPay("否");
            }
            if(str.getFrame().equals(true)){
                export.setFrame("是");
            }else{
                export.setFrame("否");
            }
            if(str.getPact().equals(true)){
                export.setPact("是");
            }else{
                export.setPact("否");
            }
            if(str.getFlow().equals(true)){
                export.setFlow("是");
            }else{
                export.setFlow("否");
            }
            exports.add(export);
        });
        Excel excel = new Excel(0,2);
        byte [] bytes = ExcelUtil.clazzToExcel(exports,excel);
        return bytes;
    }


}


