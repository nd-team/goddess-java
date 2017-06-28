package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.AreaCollectBO;
import com.bjike.goddess.staffpay.bo.DepartmentCollectBO;
import com.bjike.goddess.staffpay.bo.NameCollectBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.PayRecordDTO;
import com.bjike.goddess.staffpay.entity.PayRecord;
import com.bjike.goddess.staffpay.enums.GuideAddrStatus;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 已付款记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:00 ]
 * @Description: [ 已付款记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class PayRecordSerImpl extends ServiceImpl<PayRecord, PayRecordDTO> implements PayRecordSer {
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
    public Long countPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        Long count = super.count(payRecordDTO);
        return count;
    }

    @Override
    public PayRecordBO getOne(String id) throws SerException {
        PayRecord payRecord = super.findById(id);
        return BeanTransform.copyProperties(payRecord, PayRecordBO.class);
    }

    @Override
    public List<PayRecordBO> findListPayRecord(PayRecordDTO payRecordDTO) throws SerException {
        checkSeeIdentity();
        payRecordDTO.getSorts().add("createTime=desc");
        List<PayRecord> payRecords = super.findByPage(payRecordDTO);
        List<PayRecordBO> payRecordBOS = BeanTransform.copyProperties(payRecords, PayRecordBO.class);
        return payRecordBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removePayRecord(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<AreaCollectBO> collectArea(String[] areas) throws SerException {
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areasStr = StringUtils.join(areasTemp, ",");
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT area,months AS months,sum(staffWage) AS staffWage,sum(computerHelp) AS computerHelp, ");
        sb.append(" sum(stayHelp) AS stayHelp,sum(lengthHelp) AS lengthHelp,sum(hyperthermiaHelp) AS hyperthermiaHelp, ");
        sb.append(" sum(socialHelp) AS socialHelp,sum(otherFee) AS otherFee,sum(totalWages) AS totalWages, ");
        sb.append(" sum(holidayOvertimePay) AS holidayOvertimePay,sum(socialDeduction) AS socialDeduction, ");
        sb.append(" sum(utilitiesDeduction) AS utilitiesDeduction,sum(deductionTotal) AS deductionTotal, ");
        sb.append(" sum(bonusPenaltyDeduction) AS bonusPenaltyDeduction,sum(incomeTaxDeduction) AS incomeTaxDeduction, ");
        sb.append(" sum(whatSickLeaveDeduction) AS whatSickLeaveDeduction,sum(absenteeismDeduction) AS absenteeismDeduction, ");
        sb.append(" sum(realWages) AS realWages FROM staffpay_payrecord a ");
        sb.append(" WHERE area IN (%s) GROUP BY months,area ORDER BY area ");
        String sql = sb.toString();
        sql = String.format(sql, areasStr);
        String[] fields = new String[]{"area", "months", "staffWage", "computerHelp", "stikeayHelp", "lengthHelp", "hyperthermiaHelp",
                "socialHelp", "otherFee", "totalWages", "holidayOvertimePay", "socialDeduction", "utilitiesDeduction", "deductionTotal",
                "bonusPenaltyDeduction", "incomeTaxDeduction", "whatSickLeaveDeduction", "absenteeismDeduction", "realWages"};
        List<AreaCollectBO> areaCollectBOS = super.findBySql(sql, AreaCollectBO.class, fields);
        return areaCollectBOS;
    }

    @Override
    public List<String> getAreas() throws SerException {
        String[] fields = new String[]{"area"};
        List<PayRecordBO> payRecordBOS = super.findBySql("select distinct area from staffpay_payrecord group by area order by area asc ", PayRecordBO.class, fields);

        List<String> areasList = payRecordBOS.stream().map(PayRecordBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areasList;
    }

    @Override
    public List<DepartmentCollectBO> collectDepartment(String[] departments) throws SerException {
        String[] departmentsTemp = new String[departments.length];
        for (int i = 0; i < departments.length; i++) {
            departmentsTemp[i] = "'" + departments[i] + "'";
        }
        String departmentsStr = StringUtils.join(departmentsTemp, ",");
        StringBuilder sb = new StringBuilder();

        sb.append(" SELECT department,months AS months,sum(staffWage) AS staffWage,sum(computerHelp) AS computerHelp, ");
        sb.append(" sum(stayHelp) AS stayHelp,sum(lengthHelp) AS lengthHelp,sum(hyperthermiaHelp) AS hyperthermiaHelp, ");
        sb.append(" sum(socialHelp) AS socialHelp,sum(otherFee) AS otherFee,sum(totalWages) AS totalWages, ");
        sb.append(" sum(holidayOvertimePay) AS holidayOvertimePay,sum(socialDeduction) AS socialDeduction, ");
        sb.append(" sum(utilitiesDeduction) AS utilitiesDeduction,sum(deductionTotal) AS deductionTotal, ");
        sb.append(" sum(bonusPenaltyDeduction) AS bonusPenaltyDeduction,sum(incomeTaxDeduction) AS incomeTaxDeduction, ");
        sb.append(" sum(whatSickLeaveDeduction) AS whatSickLeaveDeduction,sum(absenteeismDeduction) AS absenteeismDeduction, ");
        sb.append(" sum(realWages) AS realWages FROM staffpay_payrecord a ");
        sb.append(" WHERE department IN (%s) GROUP BY months,department ORDER BY department ");
        String sql = sb.toString();
        sql = String.format(sql, departmentsStr);
        String[] fields = new String[]{"department", "months", "staffWage", "computerHelp", "stikeayHelp", "lengthHelp", "hyperthermiaHelp",
                "socialHelp", "otherFee", "totalWages", "holidayOvertimePay", "socialDeduction", "utilitiesDeduction", "deductionTotal",
                "bonusPenaltyDeduction", "incomeTaxDeduction", "whatSickLeaveDeduction", "absenteeismDeduction", "realWages"};
        List<DepartmentCollectBO> departmentCollectBOS = super.findBySql(sql, DepartmentCollectBO.class, fields);
        return departmentCollectBOS;
    }

    @Override
    public List<String> getDepartments() throws SerException {
        String[] fields = new String[]{"department"};
        List<PayRecordBO> payRecordBOS = super.findBySql("select distinct department from staffpay_payrecord group by department order by department asc ", PayRecordBO.class, fields);

        List<String> departmentsList = payRecordBOS.stream().map(PayRecordBO::getDepartment)
                .filter(department -> (department != null || !"".equals(department.trim()))).distinct().collect(Collectors.toList());


        return departmentsList;
    }

    @Override
    public List<NameCollectBO> collectName(String[] names) throws SerException {
        String[] namesTemp = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            namesTemp[i] = "'" + names[i] + "'";
        }
        String namesStr = StringUtils.join(namesTemp, ",");
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT name,months AS months,area AS area,department AS department,jobs AS jobs, ");
        sb.append(" sum(staffWage) AS staffWage,sum(computerHelp) AS computerHelp, ");
        sb.append(" sum(stayHelp) AS stayHelp,sum(lengthHelp) AS lengthHelp,sum(hyperthermiaHelp) AS hyperthermiaHelp, ");
        sb.append(" sum(socialHelp) AS socialHelp,sum(otherFee) AS otherFee,sum(totalWages) AS totalWages, ");
        sb.append(" sum(holidayOvertimePay) AS holidayOvertimePay,sum(socialDeduction) AS socialDeduction, ");
        sb.append(" sum(utilitiesDeduction) AS utilitiesDeduction,sum(deductionTotal) AS deductionTotal, ");
        sb.append(" sum(bonusPenaltyDeduction) AS bonusPenaltyDeduction,sum(incomeTaxDeduction) AS incomeTaxDeduction, ");
        sb.append(" sum(whatSickLeaveDeduction) AS whatSickLeaveDeduction,sum(absenteeismDeduction) AS absenteeismDeduction, ");
        sb.append(" sum(realWages) AS realWages FROM staffpay_payrecord a ");
        sb.append(" WHERE name IN (%s) GROUP BY months,area,department,jobs,name ORDER BY name ");
        String sql = sb.toString();
        sql = String.format(sql, namesStr);
        String[] fields = new String[]{"name", "months", "area", "department", "jobs", "staffWage", "computerHelp",
                "stikeayHelp", "lengthHelp", "hyperthermiaHelp",
                "socialHelp", "otherFee", "totalWages", "holidayOvertimePay", "socialDeduction", "utilitiesDeduction", "deductionTotal",
                "bonusPenaltyDeduction", "incomeTaxDeduction", "whatSickLeaveDeduction", "absenteeismDeduction", "realWages"};
        List<NameCollectBO> nameCollectBOS = super.findBySql(sql, NameCollectBO.class, fields);
        return nameCollectBOS;
    }

    @Override
    public List<String> getNames() throws SerException {
        String[] fields = new String[]{"name"};
        List<PayRecordBO> payRecordBOS = super.findBySql("select distinct name from staffpay_payrecord group by name order by name asc ", PayRecordBO.class, fields);

        List<String> namesList = payRecordBOS.stream().map(PayRecordBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return namesList;
    }
}