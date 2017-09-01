package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.*;
import com.bjike.goddess.shareholdersmanage.dto.LogoutEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.entity.LogoutEquity;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordDetailTO;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;
import com.bjike.goddess.shareholdersmanage.to.LogoutEquityTO;
import com.bjike.goddess.shareholdersmanage.type.GuideAddrStatus;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 注销股权业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:51 ]
 * @Description: [ 注销股权业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class LogoutEquitySerImpl extends ServiceImpl<LogoutEquity, LogoutEquityDTO> implements LogoutEquitySer {
    @Autowired
    private EquityTransactRecordSer equityTransactRecordSer;
    @Autowired
    private EquityTransactRecordDetailSer equityTransactRecordDetailSer;
    @Autowired
    private ShareOpenAccountSer shareOpenAccountSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countLogEquity(LogoutEquityDTO logoutEquityDTO) throws SerException {
        Long count = super.count(logoutEquityDTO);
        return count;
    }

    @Override
    public LogoutEquityBO getOne(String id) throws SerException {
        LogoutEquity logoutEquity = super.findById(id);
        return BeanTransform.copyProperties(logoutEquity, LogoutEquityBO.class);
    }

    @Override
    public List<LogoutEquityBO> findList(LogoutEquityDTO logoutEquityDTO) throws SerException {
        checkPermission();
        searchCondi(logoutEquityDTO);
        logoutEquityDTO.getSorts().add("createTime=desc");
        List<LogoutEquity> logoutEquities = super.findByCis(logoutEquityDTO);
        return BeanTransform.copyProperties(logoutEquities, LogoutEquityBO.class);
    }
    /**
     * 根据条件查询数据
     *
     * @param logoutEquityDTO
     * @throws SerException
     */
    public void searchCondi(LogoutEquityDTO logoutEquityDTO) throws SerException {
        if (StringUtils.isNotBlank(logoutEquityDTO.getArea())) {
            logoutEquityDTO.getConditions().add(Restrict.eq("area", logoutEquityDTO.getArea()));
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public LogoutEquityBO save(LogoutEquityTO logoutEquityTO) throws SerException {
        checkPermission();
        //添加本条数据
        LogoutEquity logoutEquity = BeanTransform.copyProperties(logoutEquityTO,LogoutEquity.class,true);
        logoutEquity.setCreateTime(LocalDateTime.now());
        logoutEquity = super.save(logoutEquity);
        //修改交易记录信息
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutEquityTO.getShareholderName());
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setHoldNum(0);
        equityTransactRecordTO.setAmount(0d);
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordSer.updateTrans(equityTransactRecordTO);
        //重新设置所有的占股比例
        equityTransactRecordSer.updateTransList();
        //添加交易记录信息明细
        EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
        equityTransactRecordDetailTO.setShareholderName(equityTransactRecordBO.getShareholderName());
        equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
        equityTransactRecordDetailTO.setHoldNum(-logoutEquityTO.getLogoutHoldNum());
        equityTransactRecordDetailTO.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        equityTransactRecordDetailTO.setAmount(-logoutEquityTO.getAmount());
        equityTransactRecordDetailTO.setTransactType("注销股权");
        equityTransactRecordDetailTO.setTransactId(logoutEquity.getId());
        equityTransactRecordDetailSer.save(equityTransactRecordDetailTO);
        return BeanTransform.copyProperties(logoutEquity,LogoutEquityBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public LogoutEquityBO edit(LogoutEquityTO logoutEquityTO) throws SerException {
       checkPermission();
        //判断是否修改了注销股东名
        LogoutEquity logoutEquity = super.findById(logoutEquityTO.getId());
        String shareholderName = logoutEquity.getShareholderName();
        if(!shareholderName.equals(logoutEquityTO.getShareholderName())){
            //恢复修改前交易信息记录
            EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(shareholderName, logoutEquityTO.getId());
            EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(shareholderName);
            equityTransactRecordSer.reinstate(equityTransactRecordBO,equityTransactRecordDetailBO);
            //修改交易信息记录
            EquityTransactRecordBO equityTransactRecordBO1 = equityTransactRecordSer.getByName(logoutEquityTO.getShareholderName());
            EquityTransactRecordTO equityTransactRecordTO1 = new EquityTransactRecordTO();
            equityTransactRecordTO1.setHoldNum(0);
            equityTransactRecordTO1.setAmount(0d);
            equityTransactRecordTO1.setId(equityTransactRecordBO1.getId());
            equityTransactRecordSer.updateTrans(equityTransactRecordTO1);
            //重新设置所有股东占股比例
            equityTransactRecordSer.updateTransList();
            //修改交易信息明细记录
            EquityTransactRecordDetailTO equityTransactRecordDetailTO = new EquityTransactRecordDetailTO();
            equityTransactRecordDetailTO.setId(equityTransactRecordDetailBO.getId());
            equityTransactRecordDetailTO.setShareholderName(equityTransactRecordBO1.getShareholderName());
            equityTransactRecordDetailTO.setTransactDate(LocalDate.now().toString());
            equityTransactRecordDetailTO.setHoldNum(-logoutEquityTO.getLogoutHoldNum());
            equityTransactRecordDetailTO.setPerSharePrice(equityTransactRecordBO1.getPerSharePrice());
            equityTransactRecordDetailTO.setAmount(-logoutEquityTO.getAmount());
            equityTransactRecordDetailSer.edit(equityTransactRecordDetailTO);
        }
        //修改本条记录
        LocalDateTime date = logoutEquity.getCreateTime();
        logoutEquity = BeanTransform.copyProperties(logoutEquityTO, LogoutEquity.class, true);
        logoutEquity.setCreateTime(date);
        logoutEquity.setModifyTime(LocalDateTime.now());
        super.save(logoutEquity);
        return BeanTransform.copyProperties(logoutEquity, LogoutEquityBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
       checkPermission();
        //恢复交易记录数据
        LogoutEquity logoutEquity = super.findById(id);
        EquityTransactRecordDetailBO equityTransactRecordDetailBO = equityTransactRecordDetailSer.getByNameId(logoutEquity.getShareholderName(), id);
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutEquity.getShareholderName());
        equityTransactRecordSer.reinstate(equityTransactRecordBO,equityTransactRecordDetailBO);
        equityTransactRecordSer.updateTransList();
        //删除交易明细数据
        equityTransactRecordDetailSer.deleteByTransactId(id);
        //删除本条数据
        super.remove(id);
    }

    @Override
    public LogoutEquityLinkDateBO linkDateByName(String logoutShareName) throws SerException {
        EquityTransactRecordBO equityTransactRecordBO = equityTransactRecordSer.getByName(logoutShareName);
        LogoutEquityLinkDateBO logoutEquityLinkDateBO = new LogoutEquityLinkDateBO();
        logoutEquityLinkDateBO.setShareholderName(equityTransactRecordBO.getShareholderName());
        logoutEquityLinkDateBO.setEquityType(equityTransactRecordBO.getEquityType());
        logoutEquityLinkDateBO.setHoldNum(equityTransactRecordBO.getHoldNum());
        logoutEquityLinkDateBO.setPerSharePrice(equityTransactRecordBO.getPerSharePrice());
        logoutEquityLinkDateBO.setAmount(equityTransactRecordBO.getAmount());
        return logoutEquityLinkDateBO;
    }
}