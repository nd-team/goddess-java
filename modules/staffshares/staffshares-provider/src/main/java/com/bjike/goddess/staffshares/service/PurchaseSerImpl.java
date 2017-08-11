package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.bonus.api.DisciplineRecordAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffshares.api.SchemeAPI;
import com.bjike.goddess.staffshares.bo.SchemeIssueBO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.entity.Buyschedule;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.enums.Status1;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * 干股申购表业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffsharesSerCache")
@Service
public class PurchaseSerImpl extends ServiceImpl<Purchase, PurchaseDTO> implements PurchaseSer {
    @Autowired
    private SchemeAPI schemeAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private DisciplineRecordAPI disciplineRecordAPI;
    @Autowired
    private BuyscheduleSer buyscheduleSer;

    @Override
    public void buy(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        SchemeIssueBO schemeIssueBO = schemeAPI.getOne(to.getId());
        if (null == schemeIssueBO) {
            throw new SerException("查无此条交易数据");
        }

        Purchase entity = new Purchase();
        entity.setStatus(Status1.SUBMIT);
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        entity.setName(userBO.getUsername());
        entity.setTime(LocalDateTime.now());
        entity.setCode(schemeIssueBO.getCode());
        entity.setIssueName(schemeIssueBO.getName());
        entity.setFacevalue(schemeIssueBO.getFacevalue());
        entity.setPrice(schemeIssueBO.getPrice());
        //根据用户名得到用户数据
        List<PositionDetailBO> positionDetailBOList = positionDetailUserAPI.getPositionDetail(userBO.getUsername());
        if (null != positionDetailBOList && positionDetailBOList.size() > 0) {
            PositionDetailBO positionDetailBO = positionDetailBOList.get(0);
            entity.setArea(positionDetailBO.getArea());
            // TODO: 17-8-5 每个持股方案有一个对应的项目，目前该项目从哪里拿不清楚，以后有的话得加上去
            entity.setProject("");
            entity.setDepartment(positionDetailBO.getDepartmentName());
            entity.setPosition(positionDetailBO.getPosition());

            List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.getByEmpNumber(positionDetailBO.getSerialNumber());
            if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
                //获取第一条数据
                EntryBasicInfoBO entryBasicInfoBO = entryBasicInfoBOs.get(0);
                String time = entryBasicInfoBO.getEntryTime();
                int months = getMonthSpace(time, LocalDate.now().toString());
                entity.setMonths(months);
            } else {
                entity.setMonths(0);
            }
            entity.setSellName(schemeIssueBO.getPublisher());
            entity.setPurchaseNum(to.getPurchaseNum());
            entity.setMoney(to.getPurchaseNum() * schemeIssueBO.getPrice());
            entity.setPenalty(disciplineRecordAPI.getPushNum(userBO.getUsername()));
            entity.setReward(disciplineRecordAPI.getRewardNum(userBO.getUsername()));
            // TODO: 17-8-7
            //各项晋升的次数
            entity.setPromotion(0);
            entity.setRemark(to.getRemark());
            super.save(entity);
        }
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void update(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        if (StringUtils.isBlank(to.getId())) {
            throw new SerException("id不能为空");
        }
        Purchase temp = super.findById(to.getId());
        if (temp == null) {
            throw new SerException("查无该条数据");
        }
        temp.setPurchaseNum(to.getPurchaseNum());

        String userToken = RpcTransmit.getUserToken();
        String name = userAPI.currentUser().getUsername();
        RpcTransmit.transmitUserToken(userToken);
        temp.setName(name);
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
//        checkAddIdentity();
        Purchase temp = super.findById(id);
        if (temp == null) {
            throw new SerException("查无该条数据");
        }
        if (StringUtils.isNotBlank(temp.getManager())) {
            throw new SerException("数据已提交最终审核，不可撤销");
        }
        super.remove(id);
    }

    @Override
    public void examine(PurchaseTO to) throws SerException {
//        checkAddIdentity();
        Purchase temp = super.findById(to.getId());

        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        String name = userBO.getUsername();
        RpcTransmit.transmitUserToken(userToken);

        List<PositionDetailBO> positionDetailBOs = positionDetailUserAPI.findPositionByUser(userBO.getId());
        if (!CollectionUtils.isEmpty(positionDetailBOs)) {
            for (PositionDetailBO positionDetailBO : positionDetailBOs) {
                if ("财务运营部".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getFinancial())) {
                    temp.setFinancial(name);
                    temp.setOpinion(to.getOpinion());
                }
                if ("规划模块".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getPlanModule())) {
                    temp.setPlanModule(name);
                    temp.setOpinion1(to.getOpinion1());
                }
                if ("协调管理中心（总经办）".equals(positionDetailBO.getDepartmentName()) && StringUtils.isBlank(temp.getManager())) {
                    temp.setManager(name);
                    temp.setOpinion2(to.getOpinion2());
                }
            }
        }
        if (to.getStatus().equals(Status1.ISSUED) && StringUtils.isNotBlank(temp.getManager())) {
            temp.setStatus(to.getStatus());
        } else {
            temp.setStatus(Status1.SUBMIT);
        }
        temp.setModifyTime(LocalDateTime.now());
        super.update(temp);

        //审核通过，则把数据放到交易记录表
        if (temp.getStatus().equals(Status1.ISSUED)) {
            Buyschedule buyschedule = new Buyschedule();
            buyschedule.setShareholder(temp.getName());
            buyschedule.setCode(temp.getCode());
            buyschedule.setName(temp.getIssueName());
            buyschedule.setPurchaseNum(temp.getPurchaseNum());
            buyschedule.setBuyPrice(temp.getMoney() / temp.getPurchaseNum());
            buyschedule.setPrice(temp.getPrice());
            buyschedule.setTotalBuyPrice(temp.getMoney());
            buyschedule.setTotalPrice(temp.getFacevalue());
            buyschedule.setSell(temp.getSellName());
            buyschedule.setTime(LocalDateTime.now());

            buyscheduleSer.save(buyschedule);
        }
    }

    //计算两个日期之间的月数
    public int getMonthSpace(String date1, String date2) throws SerException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(date1));
            c2.setTime(sdf.parse(date2));

            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

            return result == 0 ? 1 : Math.abs(result);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

}