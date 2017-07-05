package com.bjike.goddess.reimbursementprepare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.entity.ApplyLend;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import com.bjike.goddess.reimbursementprepare.bo.WaitPayBO;
import com.bjike.goddess.reimbursementprepare.dto.WaitPayDTO;
import com.bjike.goddess.reimbursementprepare.entity.WaitPay;
import com.bjike.goddess.reimbursementprepare.enums.PayStatus;
import com.bjike.goddess.reimbursementprepare.to.WaitPayTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 等待付款业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
 * @Description: [ 等待付款业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reimbursementprepareSerCache")
@Service
public class WaitPaySerImpl extends ServiceImpl<WaitPay, WaitPayDTO> implements WaitPaySer {
    @Autowired
    private ApplyLendAPI applyLendAPI;
    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<WaitPayBO> waitPays(WaitPayDTO dto) throws SerException{
        ApplyLendDTO applyLendDTO=new ApplyLendDTO();
        BeanUtils.copyProperties(dto,applyLendDTO);
        ReimburseRecordDTO reimburseRecordDTO=new ReimburseRecordDTO();
        BeanUtils.copyProperties(dto,reimburseRecordDTO);
        List<ApplyLendBO> applyLendBOs=applyLendAPI.listWaitPay(applyLendDTO);
        List<ReimburseRecordBO> reimburseRecordBOs=reimburseRecordAPI.listWaitPay(reimburseRecordDTO);
        List<WaitPayBO> boList=new ArrayList<>();
        for (ApplyLendBO applyLendBO:applyLendBOs){
            WaitPayBO waitPayBO= BeanTransform.copyProperties(applyLendBO,WaitPayBO.class);
            waitPayBO.setPayStatus(PayStatus.WAITPAY);
            waitPayBO.setType("借款");
            boList.add(waitPayBO);
        }
        for (ReimburseRecordBO reimburseRecordBO:reimburseRecordBOs){
            WaitPayBO waitPayBO=BeanTransform.copyProperties(reimburseRecordBO,WaitPayBO.class);
            waitPayBO.setLendDate(reimburseRecordBO.getOccureDate());
            waitPayBO.setLendMoney(reimburseRecordBO.getReimMoney());
            waitPayBO.setPayDate(reimburseRecordBO.getBudgetPayTime());
            waitPayBO.setPayStatus(PayStatus.WAITPAY);
            waitPayBO.setType("报销");
            boList.add(waitPayBO);
        }
        return boList;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void pay(WaitPayTO to) throws SerException {
        UserBO userBO=userAPI.currentUser();
        if(super.findById(to.getId())==null){
            throw new SerException("该对象不存在");
        }
        if ("借款".equals(to.getType())) {
            ApplyLendTO applyLendTO = BeanTransform.copyProperties(to, ApplyLendTO.class);
            applyLendTO.setPayDate(DateUtil.dateToString(LocalDate.now()));
            ApplyLendBO applyLendBO=applyLendAPI.editPayMoney(applyLendTO);
            WaitPay entity=BeanTransform.copyProperties(applyLendBO,WaitPay.class,true);
            entity.setPayStatus(PayStatus.HAVEPAY);
            super.save(entity);
        } else if ("报销".equals(to.getType())){
            ReimburseRecordTO reimburseRecordTO = BeanTransform.copyProperties(to, ReimburseRecordTO.class);
            reimburseRecordTO.setPayTime(DateUtil.dateToString(LocalDate.now()));
            ReimburseRecordBO reimburseRecordBO=reimburseRecordAPI.waitPay(reimburseRecordTO);
            WaitPay entity=BeanTransform.copyProperties(reimburseRecordBO,WaitPay.class,true);
            entity.setLendDate(DateUtil.parseDate(reimburseRecordBO.getOccureDate()));
            entity.setLendMoney(reimburseRecordBO.getReimMoney());
            entity.setPayDate(LocalDate.now());
            entity.setPayer(userBO.getUsername());
            entity.setPayStatus(PayStatus.HAVEPAY);
            super.save(entity);
        }
    }

    @Override
    public List<WaitPayBO> pays(WaitPayDTO dto) throws SerException{
        List<WaitPay> list=super.findByCis(dto,true);
        return BeanTransform.copyProperties(list,WaitPayBO.class);
    }



}