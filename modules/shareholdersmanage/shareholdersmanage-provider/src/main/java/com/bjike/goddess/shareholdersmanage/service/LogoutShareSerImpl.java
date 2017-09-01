package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.*;
import com.bjike.goddess.shareholdersmanage.dto.LogoutShareDTO;
import com.bjike.goddess.shareholdersmanage.entity.LogoutShare;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutShareTO;
import com.bjike.goddess.shareholdersmanage.type.ShareholderStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 注销股东业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 03:55 ]
 * @Description: [ 注销股东业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class LogoutShareSerImpl extends ServiceImpl<LogoutShare, LogoutShareDTO> implements LogoutShareSer {
    @Autowired
    private ShareOpenAccountSer shareOpenAccountSer;
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;

    @Override
    public Long countLogout(LogoutShareDTO logoutShareDTO) throws SerException {
        Long count = super.count(logoutShareDTO);
        return count;
    }

    @Override
    public LogoutShareBO getOne(String id) throws SerException {
        LogoutShare logoutShare = super.findById(id);
        return BeanTransform.copyProperties(logoutShare, LogoutShareBO.class);
    }

    @Override
    public List<LogoutShareBO> findList(LogoutShareDTO logoutShareDTO) throws SerException {
        searchCondi(logoutShareDTO);
        logoutShareDTO.getSorts().add("createTime=desc");
        List<LogoutShare> logoutShares = super.findByCis(logoutShareDTO);
        return BeanTransform.copyProperties(logoutShares, LogoutShareBO.class);
    }

    /**
     * 根据条件查询数据
     *
     * @param logoutShareDTO
     * @throws SerException
     */
    public void searchCondi(LogoutShareDTO logoutShareDTO) throws SerException {
        if (StringUtils.isNotBlank(logoutShareDTO.getArea())) {
            logoutShareDTO.getConditions().add(Restrict.eq("area", logoutShareDTO.getArea()));
        }
    }

    @Override
    public LogoutShareBO save(LogoutShareTO logoutShareTO) throws SerException {
        //添加注销表信息
        LogoutShare logoutShare = BeanTransform.copyProperties(logoutShareTO, LogoutShare.class, true);
        logoutShare.setCreateTime(LocalDateTime.now());
        logoutShare = super.save(logoutShare);
        //添加交易明细数据
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutShareTO.getLogoutShareName());
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(equityTransactRecordBO.getShareholderName());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setHoldNum(-equityTransactRecordBO.getHoldNum());
        equityTransactRecordDetailTO.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        equityTransactRecordDetailTO.setAmount(-equityTransactRecordBO.getAmount());
        equityTransactRecordDetailTO.setTransactType("注销股东");
        equityTransactRecordDetailTO.setTransactId(logoutShare.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
        //将交易记录中的该股东清零并修改其状态
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setHoldNum(0);
        equityTransactRecordTO.setAmount(0d);
        equityTransactRecordTO.setShareholderStatus(ShareholderStatus.HASCANCELLED);
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        //重新设置所有比例
        equityTransactRecordSer.updateTransList();
        //修改开户表中的状态
        reShareholderStatus(logoutShareTO.getLogoutShareName(), ShareholderStatus.HASCANCELLED);
        return BeanTransform.copyProperties(logoutShare, LogoutShareBO.class);
    }

    @Override
    public LogoutShareBO edit(LogoutShareTO logoutShareTO) throws SerException {
        //判断是否注销股东是否被修改
        LogoutShare logoutShare = super.findById(logoutShareTO.getId());
        String logoutShareName = logoutShare.getLogoutShareName();
        if (!logoutShareName.equals(logoutShareTO.getLogoutShareName())) {
            //恢复该股东的交易记录信息
            EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(logoutShareName, logoutShareTO.getId());
            EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutShareName);
            EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
            equityTransactRecordTO.setId(equityTransactRecordBO.getId());
            equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum() - equityTransactRecordDetailBO.getHoldNum());
            equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount() - equityTransactRecordDetailBO.getAmount());
            equityTransactRecordTO.setShareholderStatus(ShareholderStatus.NORMAL);
            equityTransactRecordSer.updateTrans(equityTransactRecordTO);
            //修改交易记录信息明细的数据
            EquityTransactRecordBO equityTransactRecordBO1 = equityTransactRecordSer.getByName(logoutShareTO.getLogoutShareName());
            EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTO.setId(equityTransactRecordDetailBO.getId());
            equityTransactRecordDetailTO.setShareholderName(equityTransactRecordBO1.getShareholderName());
            equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTO.setHoldNum(-equityTransactRecordBO1.getHoldNum());
            equityTransactRecordDetailTO.setPerSharePrice(equityTransactRecordBO1.getPerSharePrice());
            equityTransactRecordDetailTO.setAmount(-equityTransactRecordBO1.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTO);
            //修改修改后的股东交易记录信息
            EquityTransactRecordTO equityTransactRecordTO1 = new EquityTransactRecordTO();
            equityTransactRecordTO1.setHoldNum(0);
            equityTransactRecordTO1.setAmount(0d);
            equityTransactRecordTO1.setShareholderStatus(ShareholderStatus.HASCANCELLED);
            equityTransactRecordTO1.setId(equityTransactRecordBO1.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTO1);
            //恢复开户表中的状态
            ShareOpenAccountBO shareOpenAccountBO = shareOpenAccountSer.findByName(logoutShareName);
            ShareOpenAccount shareOpenAccount = BeanTransform.copyProperties(shareOpenAccountBO, ShareOpenAccount.class, true);
            shareOpenAccount.setShareholderStatus(ShareholderStatus.HASCANCELLED);
            shareOpenAccountSer.update(shareOpenAccount);
            reShareholderStatus(logoutShareName, ShareholderStatus.NORMAL);
            reShareholderStatus(logoutShareTO.getLogoutShareName(), ShareholderStatus.HASCANCELLED);
        }
        //重新设置所有股东的占股比例
        equityTransactRecordSer.updateTransList();
        //修改注销股东信息
        LocalDateTime date = logoutShare.getCreateTime();
        logoutShare = BeanTransform.copyProperties(logoutShareTO, LogoutShare.class, true);
        logoutShare.setCreateTime(date);
        logoutShare.setModifyTime(LocalDateTime.now());
        super.save(logoutShare);
        return BeanTransform.copyProperties(logoutShare, LogoutShareBO.class);
    }

    //恢复股东开户中的股东状态
    public void reShareholderStatus(String logoutShareName, ShareholderStatus status) throws SerException {
        ShareOpenAccountBO shareOpenAccountBO = shareOpenAccountSer.findByName(logoutShareName);
        ShareOpenAccount shareOpenAccount = BeanTransform.copyProperties(shareOpenAccountBO, ShareOpenAccount.class, true);
        shareOpenAccount.setShareholderStatus(status);
        shareOpenAccountSer.update(shareOpenAccount);
    }


    @Override
    public void delete(String id) throws SerException {
        //恢复交易记录数据及状态
        LogoutShare logoutShare = super.findById(id);
        String logoutShareName = logoutShare.getLogoutShareName();
        EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(logoutShareName, id);
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutShareName);
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum() - equityTransactRecordDetailBO.getHoldNum());
        equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount() - equityTransactRecordDetailBO.getAmount());
        equityTransactRecordTO.setShareholderStatus(ShareholderStatus.NORMAL);
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        //重新设置一下所有股东的占股比例
        equityTransactRecordSer.updateTransList();
        //恢复开户状态
        reShareholderStatus(logoutShareName, ShareholderStatus.NORMAL);
        //删除交易记录明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本表本条数据
        super.remove(id);
    }

    @Override
    public LogoutShareLinkDateBO linkDateByName(String logoutShareName) throws SerException {
        ShareOpenAccountBO shareOpenAccountBO = shareOpenAccountSer.findByName(logoutShareName);
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutShareName);
        LogoutShareLinkDateBO logoutShareLinkDateBO = new LogoutShareLinkDateBO();
        logoutShareLinkDateBO.setShareholderName(shareOpenAccountBO.getShareholderName());
        logoutShareLinkDateBO.setTypeName(shareOpenAccountBO.getTypeName());
        logoutShareLinkDateBO.setHoldNum(equityTransactRecordBO.getHoldNum());
        logoutShareLinkDateBO.setAmount(equityTransactRecordBO.getAmount());
        logoutShareLinkDateBO.setCapitalWay(shareOpenAccountBO.getCapitalWay());
        return logoutShareLinkDateBO;
    }
}