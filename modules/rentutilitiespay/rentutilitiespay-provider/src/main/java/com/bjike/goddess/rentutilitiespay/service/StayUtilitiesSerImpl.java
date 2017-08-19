package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.CollectNameBO;
import com.bjike.goddess.rentutilitiespay.bo.StayUtilitiesBO;
import com.bjike.goddess.rentutilitiespay.dto.StayUtilitiesDTO;
import com.bjike.goddess.rentutilitiespay.entity.StayUtilities;
import com.bjike.goddess.rentutilitiespay.enums.GuideAddrStatus;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.StayUtilitiesTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 员工住宿水电费业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentutilitiespaySerCache")
@Service
public class StayUtilitiesSerImpl extends ServiceImpl<StayUtilities, StayUtilitiesDTO> implements StayUtilitiesSer {
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
     * 核对综合资源部审核权限（岗位级别）
     */
    private void checkResAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
            if (!flag) {
                throw new SerException("您不是相应综合资源部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 核对运营财务部审核权限（岗位级别）
     */
    private void checkFinAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("5");
            if (!flag) {
                throw new SerException("您不是相应运营财务部门的人员，不可以操作");
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
     * 核对综合资源部审核权限（部门级别）
     */
    private Boolean guideResAuditIdentity() throws SerException {
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
     * 核对运营财务部审核权限（岗位级别）
     */
    private Boolean guideFinAuditIdentity() throws SerException {
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

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        Boolean flagFin = guideFinAuditIdentity();
        Boolean flagRes = guideResAuditIdentity();
        if (flagSee || flagAdd || flagFin || flagRes) {
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
            case FINANCEAUDIT:
                flag = guideFinAuditIdentity();
                break;
            case RESOURCEAUDIT:
                flag = guideResAuditIdentity();
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
    public Long countStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        Long count = super.count(stayUtilitiesDTO);
        return count;
    }

    @Override
    public StayUtilitiesBO getOne(String id) throws SerException {
        StayUtilities stayUtilities = super.findById(id);
        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    @Override
    public List<StayUtilitiesBO> findListStayUtilities(StayUtilitiesDTO stayUtilitiesDTO) throws SerException {
        checkSeeIdentity();
        stayUtilitiesDTO.getSorts().add("createTime=desc");
        List<StayUtilities> stayUtilities = super.findByPage(stayUtilitiesDTO);
        List<StayUtilitiesBO> stayUtilitiesBOS = BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
        return stayUtilitiesBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO insertStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        checkAddIdentity();
        StayUtilities stayUtilities = BeanTransform.copyProperties(stayUtilitiesTO, StayUtilities.class, true, "projectName");
        stayUtilities = count(stayUtilities);
        stayUtilities.setProjectName(StringUtils.join(stayUtilitiesTO.getProjectName(), ","));
        super.save(stayUtilities);
        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO editStayUtilities(StayUtilitiesTO stayUtilitiesTO) throws SerException {
        checkAddIdentity();
        StayUtilities stayUtilities = super.findById(stayUtilitiesTO.getId());
        BeanTransform.copyProperties(stayUtilitiesTO, stayUtilities, true, "projectName");
        stayUtilities.setModifyTime(LocalDateTime.now());
        stayUtilities = count(stayUtilities);
        stayUtilities.setProjectName(StringUtils.join(stayUtilitiesTO.getProjectName(), ","));
        super.update(stayUtilities);
        return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
    }

    /**
     * 计算方法
     */
    public StayUtilities count(StayUtilities stayUtilities) throws SerException {
        //同一住宿地址员工住宿天数总和
        String[] fields = new String[]{"sumDays", "address"};
        String sql = " select cast(sum(stayDay)as SIGNED )as sumDays ,address from rentutilitiespay_stayutilities group by address ";
        List<StayUtilities> sum = super.findBySql(sql, StayUtilities.class, fields);
        Integer sumDays = sum.get(0).getSumDays();

        //个人员工住宿天数
        fields = new String[]{"personalDays", "name"};
        //String name = stayUtilities.getName();
        //sql = "select cast(sum(stayDay)as SIGNED ) as personalDays from rentutilitiespay_stayutilities where name = '"+name+"' ";
        sql = "select cast(sum(stayDay)as SIGNED ) as personalDays,name from rentutilitiespay_stayutilities group by name ";
        List<StayUtilities> num = super.findBySql(sql, StayUtilities.class, fields);
        Integer personalDays = num.get(0).getPersonalDays();
        //水费员工缴纳（(当月应缴水费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double waterStaffPay = (stayUtilities.getWaterAmount() / sumDays) * personalDays;
        stayUtilities.setWaterStaffPay(waterStaffPay);
        //电费员工缴纳（(当月应缴电费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double energyStaffPay = (stayUtilities.getEnergyAmount() / sumDays) * personalDays;
        stayUtilities.setEnergyStaffPay(energyStaffPay);
        //燃气费员工缴纳（(管道燃气费充值额度/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
        Double gasStaffPay = (stayUtilities.getGasRechargeLines() / sumDays) * personalDays;
        stayUtilities.setGasStaffPay(gasStaffPay);

        //员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）
        Double staffPayCollect = waterStaffPay + energyStaffPay + gasStaffPay;
        stayUtilities.setStaffPayCollect(staffPayCollect);
        return stayUtilities;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayUtilities(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }

    @Override
    public List<CollectNameBO> collectName(String[] names) throws SerException {
        String[] namesTemp = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            namesTemp[i] = "'" + names[i] + "'";
        }
        String namesStr = StringUtils.join(namesTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT name,num AS num,area AS area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark ");
        sb.append(" FROM rentutilitiespay_stayutilities WHERE name IN (%s) GROUP BY name,num,area,projectGroup,projectName,address, ");
        sb.append(" name ORDER BY name)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS name,NULL as num,NULL as area,NULL as projectGroup,NULL as projectName,NULL as address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark FROM ");
        sb.append(" (SELECT name,num AS num,area AS area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(waterStaffPay) AS waterStaffPay,sum(energyStaffPay) AS energyStaffPay,sum(gasStaffPay)AS gasStaffPay, ");
        sb.append(" ( sum(waterStaffPay)+sum(energyStaffPay)+sum(gasStaffPay)) AS remark ");
        sb.append(" FROM rentutilitiespay_stayutilities WHERE name IN (%s) GROUP BY name,num,area,projectGroup,projectName,address, ");
        sb.append(" name ORDER BY name)A ");
        String sql = sb.toString();
        sql = String.format(sql, namesStr, namesStr);
        String[] fields = new String[]{"name", "num", "area", "projectGroup", "projectName", "address",
                "waterStaffPay", "energyStaffPay", "gasStaffPay", "remark"};
        List<CollectNameBO> collectNameBOS = super.findBySql(sql, CollectNameBO.class, fields);
        return collectNameBOS;
    }

    @Override
    public List<String> getName() throws SerException {
        String[] fields = new String[]{"name"};
        List<StayUtilitiesBO> stayUtilitiesBOS = super.findBySql("select name from rentutilitiespay_stayutilities order by name asc ", StayUtilitiesBO.class, fields);

        List<String> nameList = stayUtilitiesBOS.stream().map(StayUtilitiesBO::getName)
                .filter(name -> (name != null || !"".equals(name.trim()))).distinct().collect(Collectors.toList());


        return nameList;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO employeeVerify(StayUtilitiesTO to) throws SerException {
        checkResAuditIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            StayUtilities stayUtilities = super.findById(to.getId());
            BeanTransform.copyProperties(to,stayUtilities,true,"projectName");
            //是否需要修改
            if (stayUtilities.getStaffVerify().equals(StaffVerify.ERROR)) {
                stayUtilities.setComprehensiveVerifySituation(true);
            } else if (stayUtilities.getStaffVerify().equals(StaffVerify.CONFIRM)) {
                stayUtilities.setComprehensiveVerifySituation(false);
            }
            stayUtilities.setModifyTime(LocalDateTime.now());
            super.update(stayUtilities);
            return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
        } else {
            throw new SerException("id不能为空");
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayUtilitiesBO financeAudit(StayUtilitiesTO to) throws SerException {
        checkFinAuditIdentity();
        if(StringUtils.isNotBlank(to.getId())){
            StayUtilities stayUtilities = super.findById(to.getId());
            BeanTransform.copyProperties(to, stayUtilities, true,"projectName");
            stayUtilities.setDeductionSituation(to.getDeductionSituation());
            super.update(stayUtilities);
            return BeanTransform.copyProperties(stayUtilities, StayUtilitiesBO.class);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public StayUtilitiesBO findStay(String time, String name) throws SerException {
//        StayUtilitiesDTO dto = new StayUtilitiesDTO();
//        dto.getConditions().add(Restrict.eq("Lo"))
//        StayUtilities stayUtilities = super.find
        return null;
    }
}