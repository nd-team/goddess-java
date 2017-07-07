package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.entity.OilCardBasic;
import com.bjike.goddess.oilcardmanage.entity.OilCardRecharge;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardRechargeBO saveOilCardRecharge(OilCardRechargeTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getOilCardBasicId())) {
            OilCardBasic oilCardBasic = oilCardBasicSer.findById(to.getOilCardBasicId());

            if (oilCardBasic != null) {
                OilCardRecharge model = BeanTransform.copyProperties(to, OilCardRecharge.class, true);

                to.setId(model.getId());

                //充值：修改油卡期初金额及余额
                oilCardBasic.setCycleEarlyMoney(oilCardBasic.getBalance() + to.getRechargeMoney()); //油卡期初金额 = 余额 + 充值金额
                oilCardBasic.setBalance(oilCardBasic.getBalance() + to.getRechargeMoney()); //油卡余额 = 余额 + 充值金额
                oilCardBasic.setModifyTime(LocalDateTime.now());
                oilCardBasicSer.update(oilCardBasic);
                //充值信息期初金额 = 期末余额 + 充值金额（即最新充值记录 期初金额 = 油卡期初金额）
                model.setCycleEarlyMoney(oilCardBasic.getCycleEarlyMoney());

                super.save(model);
                return BeanTransform.copyProperties(to, OilCardRechargeBO.class);
            } else {
                throw new SerException("油卡不存在!");
            }
        } else {
            throw new SerException("油卡基本信息ID不能为空!");
        }
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public OilCardRechargeBO updateOilCardRecharge(OilCardRechargeTO to) throws SerException {
        if (!StringUtils.isEmpty(to.getId())) {
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
        } else {
            throw new SerException("更新ID不能为空!");
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
    public List<OilCardRechargeBO> collect(String id, String startTime, String endTime) throws SerException {
        OilCardRechargeDTO dto = new OilCardRechargeDTO();
        dto.getConditions().add(Restrict.eq("oilCardBasicId", id));

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
        dto.getConditions().add(Restrict.eq("oilCardBasicId", id));
        return BeanTransform.copyProperties(super.findByCis(dto), OilCardRechargeBO.class);
    }

    // 由于油卡充值不冗余油卡基本信息，因此需要油卡充值记录set油卡基础信息
    public List<OilCardRechargeBO> querySetCard(List<OilCardRecharge> list) throws SerException {
        List<OilCardRechargeBO> boList = BeanTransform.copyProperties(list, OilCardRechargeBO.class);
        for (OilCardRechargeBO bo : boList) {
            OilCardBasic oilCardBasic = oilCardBasicSer.findById(bo.getOilCardBasicId());
            if (oilCardBasic != null) {
                bo.setOilCardCode(oilCardBasic.getOilCardCode());
                bo.setOilCardNumber(oilCardBasic.getOilCardNumber());
            }
        }
        return boList;
    }
}
