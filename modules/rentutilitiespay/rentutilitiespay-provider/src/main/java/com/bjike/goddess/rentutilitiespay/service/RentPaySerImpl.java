package com.bjike.goddess.rentutilitiespay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.rentutilitiespay.bo.CollectAreaBO;
import com.bjike.goddess.rentutilitiespay.bo.RentPayBO;
import com.bjike.goddess.rentutilitiespay.dto.RentPayDTO;
import com.bjike.goddess.rentutilitiespay.entity.RentPay;
import com.bjike.goddess.rentutilitiespay.enums.GuideAddrStatus;
import com.bjike.goddess.rentutilitiespay.excel.SonPermissionObject;
import com.bjike.goddess.rentutilitiespay.to.GuidePermissionTO;
import com.bjike.goddess.rentutilitiespay.to.RentPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 房租缴费业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:10 ]
 * @Description: [ 房租缴费业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentutilitiespaySerCache")
@Service
public class RentPaySerImpl extends ServiceImpl<RentPay, RentPayDTO> implements RentPaySer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private StayUtilitiesSer stayUtilitiesSer;

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
     * 审核权限（部门级别）
     */
    private void checkAuditIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("3");
            if (!flag) {
                throw new SerException("您不是相应运营财务部的人员，不可以操作");
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

    /**
     * 审核权限（部门级别）
     */
    private Boolean guideAuditIdentity() throws SerException {
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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();
        Boolean flagAudit = guideAuditIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("rentpay");
        obj.setDescribesion("房租缴费");
        if (flagSeeInfo || flagAddInfo || flagAudit) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = stayUtilitiesSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("stayutilities");
        obj.setDescribesion("员工住宿水电费");
        if (flagSeeAnswer) {
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
            case FINANCEAUDIT:
                flag = guideAuditIdentity();
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
        return flag;
    }

    @Override
    public Long countRentPay(RentPayDTO rentPayDTO) throws SerException {
        Long count = super.count(rentPayDTO);
        return count;
    }

    @Override
    public RentPayBO getOne(String id) throws SerException {
        RentPay rentPay = super.findById(id);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }

    @Override
    public List<RentPayBO> findListRentPay(RentPayDTO rentPayDTO) throws SerException {
        List<RentPay> rentPays = super.findByCis(rentPayDTO, true);
        return BeanTransform.copyProperties(rentPays, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO insertRentPay(RentPayTO rentPayTO) throws SerException {
        RentPay rentPay = BeanTransform.copyProperties(rentPayTO, RentPay.class, true, "projectName");
        rentPay.setCreateTime(LocalDateTime.now());
        //用水量（水费期末数目-水费初期数目）
        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
        rentPay.setWater(water);
        //水费缴纳金额（水费计价金额（元/吨）*用水量）
        Double waterPayMoney = rentPay.getWaterValuationMoney() * water;
        rentPay.setWaterPayMoney(waterPayMoney);
        //用电量（电费期末数目-电费初期数目）
        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
        rentPay.setEnergy(energy);
        //电费缴纳金额（电费计价金额（元/吨）*用电量）
        Double energyPayMoney = rentPay.getEnergyValuationMoney() * energy;
        rentPay.setEnergyPayMoney(energyPayMoney);
        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + waterPayMoney + energyPayMoney + rentPay.getGasRechargeLines();
        rentPay.setPayMoneyCollect(payMoneyCollect);
        rentPay.setProjectName(StringUtils.join(rentPayTO.getProjectName(), ","));
        super.save(rentPay);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO editRentPay(RentPayTO rentPayTO) throws SerException {
        RentPay rentPay = super.findById(rentPayTO.getId());
        LocalDateTime createTime = rentPay.getCreateTime();
        rentPay = BeanTransform.copyProperties(rentPayTO, RentPay.class, true, "projectName");
        rentPay.setCreateTime(createTime);
        rentPay.setModifyTime(LocalDateTime.now());
        //用水量（水费期末数目-水费初期数目）
        Double water = rentPay.getWaterEndNum() - rentPay.getWaterBeginNum();
        rentPay.setWater(water);
        //水费缴纳金额（水费计价金额（元/吨）*用水量）
        Double waterPayMoney = rentPay.getWaterValuationMoney() * water;
        rentPay.setWaterPayMoney(waterPayMoney);
        //用电量（电费期末数目-电费初期数目）
        Double energy = rentPay.getEnergyEndNum() - rentPay.getEnergyBeginNum();
        rentPay.setEnergy(energy);
        //电费缴纳金额（电费计价金额（元/吨）*用电量）
        Double energyPayMoney = rentPay.getEnergyValuationMoney() * energy;
        rentPay.setEnergyPayMoney(energyPayMoney);
        //缴纳金额汇总（房租（元/月）+管理费，卫生费+水费缴纳金额+电费缴纳金额+管道燃气费充值额度）
        Double payMoneyCollect = rentPay.getRent() + rentPay.getFee() + waterPayMoney + energyPayMoney + rentPay.getGasRechargeLines();
        rentPay.setPayMoneyCollect(payMoneyCollect);
        rentPay.setProjectName(StringUtils.join(rentPayTO.getProjectName(), ","));
        super.update(rentPay);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeRentPay(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        String[] areasTemp = new String[areas.length];
        for (int i = 0; i < areas.length; i++) {
            areasTemp[i] = "'" + areas[i] + "'";
        }
        String areaStr = StringUtils.join(areasTemp, ",");

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM ");
        sb.append(" (SELECT area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney,sum(gasRechargeLines) as gasRechargeLines, sum(fee)AS fee, ");
        sb.append(" ( sum(rent)+sum(waterPayMoney)+sum(energyPayMoney)+sum(gasRechargeLines)+sum(fee)) AS remark ");
        sb.append(" FROM rentutilitiespay_rentpay WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address, ");
        sb.append(" area ORDER BY area)A ");
        sb.append(" UNION ");
        sb.append(" SELECT '合计' AS area,NULL as projectGroup,NULL as projectName,NULL as address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney, sum(gasRechargeLines) as gasRechargeLines,sum(fee)AS fee , ");
        sb.append(" ( sum(remark)) as remark FROM ");
        sb.append(" (SELECT area,projectGroup AS projectGroup,projectName AS projectName,address AS address, ");
        sb.append(" sum(rent) AS rent,sum(waterPayMoney) AS waterPayMoney, ");
        sb.append(" sum(energyPayMoney)AS energyPayMoney,sum(gasRechargeLines) as gasRechargeLines, sum(fee)AS fee, ");
        sb.append(" ( sum(rent)+sum(waterPayMoney)+sum(energyPayMoney)+sum(gasRechargeLines)+sum(fee)) AS remark ");
        sb.append(" FROM rentutilitiespay_rentpay WHERE area IN (%s) GROUP BY area,projectGroup,projectName,address, ");
        sb.append(" area ORDER BY area)A ");
        String sql = sb.toString();
        sql = String.format(sql, areaStr, areaStr);
        String[] fields = new String[]{"area", "projectGroup", "projectName", "address",
                "rent", "waterPayMoney", "energyPayMoney", "gasRechargeLines","fee", "remark"};
        List<CollectAreaBO> collectAreaBOS = super.findBySql(sql, CollectAreaBO.class, fields);
        return collectAreaBOS;
    }

    @Override
    public List<String> getArea() throws SerException {
        String[] fields = new String[]{"area"};
        List<RentPayBO> rentPayBOS = super.findBySql("select distinct area from rentutilitiespay_rentpay group by area order by area asc ", RentPayBO.class, fields);

        List<String> areaList = rentPayBOS.stream().map(RentPayBO::getArea)
                .filter(area -> (area != null || !"".equals(area.trim()))).distinct().collect(Collectors.toList());


        return areaList;

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public RentPayBO financeAudit(RentPayTO rentPayTO) throws SerException {

        checkAuditIdentity();
        RentPay rentPay = super.findById(rentPayTO.getId());
//        LocalDateTime a = rentPay.getCreateTime();
//        rentPay = BeanTransform.copyProperties(rentPayTO, RentPay.class, true);
//        rentPay.setCreateTime(a);
        rentPay.setOperatingPay(rentPayTO.getOperatingPay());
        rentPay.setModifyTime(LocalDateTime.now());
        super.update(rentPay);
        return BeanTransform.copyProperties(rentPay, RentPayBO.class);
    }
}