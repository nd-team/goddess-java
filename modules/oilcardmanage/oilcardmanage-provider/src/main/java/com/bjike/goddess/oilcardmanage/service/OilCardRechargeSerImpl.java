package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.oilcardmanage.bo.AnalyzeBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardBasicBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import com.bjike.goddess.oilcardmanage.enums.GuideAddrStatus;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 油卡充值业务处理类
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午11:07]
 * @Package:[ com.bjike.goddess.oilcardmanage.service ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class OilCardRechargeSerImpl extends ServiceImpl<OilCardRecharge, OilCardRechargeDTO> implements OilCardRechargeSer {

    @Autowired
    private OilCardBasicSer oilCardBasicSer;
    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

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
    @Transactional(rollbackFor = SerException.class)
    public OilCardRechargeBO saveOilCardRecharge(OilCardRechargeTO to) throws SerException {
        OilCardBasic oilCardBasic = oilCardBasicSer.findById(to.getOilCardBasicId());

        if (oilCardBasic != null) {
            OilCardRecharge model = BeanTransform.copyProperties(to, OilCardRecharge.class, true);

            to.setId(model.getId());

            //充值：修改油卡期初金额及余额
            oilCardBasic.setCycleEarlyMoney(oilCardBasic.getBalance() + to.getRechargeMoney()); //油卡期初金额 = 余额 + 充值金额
            oilCardBasic.setBalance(oilCardBasic.getBalance() + to.getRechargeMoney()); //油卡余额 = 余额 + 充值金额
            oilCardBasic.setModifyTime(LocalDateTime.now());
            oilCardBasicSer.update(oilCardBasic);
            model.setOilCardBasic(oilCardBasic);
            //充值信息期初金额 = 期末余额 + 充值金额（即最新充值记录 期初金额 = 油卡期初金额）
            model.setCycleEarlyMoney(oilCardBasic.getCycleEarlyMoney());

            super.save(model);
            return BeanTransform.copyProperties(to, OilCardRechargeBO.class);
        } else {
            throw new SerException("非法oilCardBasicId,油卡信息对象不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardRechargeBO updateOilCardRecharge(OilCardRechargeTO to) throws SerException {
        // 记录修改前、后的数据Model
        OilCardRecharge model = super.findById(to.getId());
        Double currentRecharge = to.getRechargeMoney();//本次充值金额

        if (model != null) {

            Double exCycleEarlyMoney = model.getCycleEarlyMoney(); //修改前期初金额
            Double exRechargeMoney = model.getRechargeMoney(); //修改前充值金额

            BeanTransform.copyProperties(to, model, true);
            model.setModifyTime(LocalDateTime.now());
            //修改油卡信息
            updateOilCard(to.getOilCardBasicId(), exRechargeMoney, currentRecharge);

            // 期初金额 - 原充值余额 + 本次充值金额
            model.setCycleEarlyMoney(exCycleEarlyMoney - exRechargeMoney + currentRecharge);
            super.update(model);
            //修改了本次充值记录，在本次充值记录之后的数据的期初金额都要 - 修改前的充值金额 + 本次充值金额
            updateAfterList(model.getCreateTime(), exRechargeMoney, currentRecharge);

            return BeanTransform.copyProperties(to, OilCardRechargeBO.class);
        } else {
            throw new SerException("更新对象不能为空!");
        }
    }

    // 期初金额 - 原充值余额 + 本次充值金额
    public void updateOilCard(String id, Double exRechargeMoney, Double currentRecharge) throws SerException {
        OilCardBasic model = oilCardBasicSer.findById(id);
        if (model != null) {
            // 油卡期初金额、余额 - 修改前充值金额 + 修改后充值金额
            model.setCycleEarlyMoney(model.getCycleEarlyMoney() - exRechargeMoney + currentRecharge);
            model.setBalance(model.getBalance() - exRechargeMoney + currentRecharge);
            model.setModifyTime(LocalDateTime.now());
            oilCardBasicSer.update(model);
        } else {
            throw new SerException("油卡不存在!");
        }
    }

    // 修改了本次充值记录，在本次充值记录之后的数据的期初金额都要 - 修改前的充值金额 + 本次充值金额
    public void updateAfterList(LocalDateTime time, Double exRechargeMoney, Double currentRecharge) throws SerException {

        OilCardRechargeDTO dto = new OilCardRechargeDTO();
        dto.getConditions().add(Restrict.gt("createTime", time));
        List<OilCardRecharge> list = super.findByCis(dto);
        if (list != null && list.size() > 0) {
            for (OilCardRecharge model : list) {
                //期初金额 = 修改前期初金额 - exRechargeMoney + currentRecharge
                model.setCycleEarlyMoney(model.getCycleEarlyMoney() - exRechargeMoney + currentRecharge);
                model.setModifyTime(LocalDateTime.now());
            }
            super.update(list);
        }

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<OilCardRechargeBO> collect(String oilCardBasicId, String startTime, String endTime) throws SerException {
        OilCardRechargeDTO dto = new OilCardRechargeDTO();
        dto.getConditions().add(Restrict.eq("oilCardBasic.id", oilCardBasicId));

        LocalDateTime startRecharge = LocalDateTime.parse(startTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endRecharge = LocalDateTime.parse(endTime,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime[] queryTime = new LocalDateTime[]{startRecharge, endRecharge};
        dto.getConditions().add(Restrict.between("rechargeDate", queryTime));
        List<OilCardRecharge> list = super.findByPage(dto);
        return querySetCard(list);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<OilCardRechargeBO> pageList(final OilCardRechargeDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<OilCardRecharge> list = super.findByPage(dto);
        return querySetCard(list);
    }

    @Override
    public List<OilCardRechargeBO> findByBasicId(String id) throws SerException {
        OilCardRechargeDTO dto = new OilCardRechargeDTO();
        dto.getConditions().add(Restrict.eq("oilCardBasic.id", id));
        return BeanTransform.copyProperties(super.findByCis(dto), OilCardRechargeBO.class);
    }


    @Override
    public AnalyzeBO analyze(String oilCardCode, Integer year, Integer month) throws SerException {

        double addOilAmount = dispatchCarInfoAPI.findOilAmount(oilCardCode, year, month);
        OilCardBasicBO oilCardBasic = oilCardBasicSer.findByCode(oilCardCode);
        if (oilCardBasic != null) {
            StringBuilder sql = new StringBuilder(" SELECT count(*) as count, sum(rechargeMoney) as rechargeMoney FROM oilcardmanage_recharge WHERE 0 = 0 ");
            sql.append(" and oilCardBasic_id = '" + oilCardBasic + "'");
            sql.append(" and year(rechargeDate) = " + year);
            sql.append(" and month(rechargeDate) = " + month);
            String[] fields = new String[]{"count", "rechargeMoney"};
            List<AnalyzeBO> boList = super.findBySql(sql.toString(), AnalyzeBO.class, fields);
            if (!CollectionUtils.isEmpty(boList)) {
                AnalyzeBO bo = boList.get(0);
                bo.setAddOilAmount(addOilAmount);
                StringBuilder cycleEarlyMoneyStr = new StringBuilder(" SELECT cycleEarlyMoney, FROM oilcardmanage_recharge WHERE 0 = 0 ");
                sql.append(" and oilCardBasic_id = '" + oilCardBasic + "'");
                sql.append(" and year(rechargeDate) = " + year);
                sql.append(" and month(rechargeDate) = " + month);
                sql.append(" order by rechargeDate desc limit 1");
                List<OilCardRecharge> list = super.findBySql(sql.toString(), OilCardRecharge.class, new String[]{"cycleEarlyMoney"});
                if (!CollectionUtils.isEmpty(list)) {
                    bo.setRechargeMoney(list.get(0).getCycleEarlyMoney());
                }
                return bo;
            }
            return null;
        } else {
            throw new SerException("非法油卡编号，油卡对象不能为空!");
        }

    }

    // 由于油卡充值不冗余油卡基本信息，因此需要油卡充值记录set油卡基础信息
    public List<OilCardRechargeBO> querySetCard(List<OilCardRecharge> list) throws SerException {
        if (!CollectionUtils.isEmpty(list)) {
            List<OilCardRechargeBO> boList = new ArrayList<OilCardRechargeBO>();
            for (OilCardRecharge model : list) {
                OilCardRechargeBO bo = BeanTransform.copyProperties(model, OilCardRechargeBO.class);
                bo.setOilCardNumber(model.getOilCardBasic().getOilCardNumber());
                bo.setOilCardCode(model.getOilCardBasic().getOilCardCode());
                boList.add(bo);
            }
            return boList;
        } else {
            return null;
        }
    }
}
