package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.CollectCompareBO;
import com.bjike.goddess.staffpay.bo.MoneyReadyBO;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.entity.CusPermission;
import com.bjike.goddess.staffpay.entity.MoneyReady;
import com.bjike.goddess.staffpay.enums.GuideAddrStatus;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;
import com.bjike.goddess.staffpay.to.MoneyReadyTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 资金准备审核表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer {
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
    public Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        Long count = super.count(moneyReadyDTO);
        return count;
    }

    @Override
    public MoneyReadyBO getOne(String id) throws SerException {
        MoneyReady moneyReady = super.findById(id);
        return BeanTransform.copyProperties(moneyReady, MoneyReadyBO.class);
    }

    @Override
    public List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        checkSeeIdentity();
        moneyReadyDTO.getSorts().add("createTime=desc");
        List<MoneyReady> moneyReadies = super.findByPage(moneyReadyDTO);
        List<MoneyReadyBO> moneyReadyBOS = BeanTransform.copyProperties(moneyReadies, MoneyReadyBO.class);
        return moneyReadyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = BeanTransform.copyProperties(moneyReadyTO, MoneyReady.class, true);
        moneyReady.setCreateTime(LocalDateTime.now());
        super.save(moneyReady);
        return BeanTransform.copyProperties(moneyReady, MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        checkAddIdentity();
        MoneyReady moneyReady = super.findById(moneyReadyTO.getId());
        BeanTransform.copyProperties(moneyReadyTO, moneyReady, true);
        moneyReady.setModifyTime(LocalDateTime.now());
        super.update(moneyReady);
        return BeanTransform.copyProperties(moneyReady, MoneyReadyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyReady(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }

    @Override
    public List<CollectCompareBO> collectCompare(Integer month) throws SerException {
        Integer year = LocalDateTime.now().getYear();
        Set<String> projectGroups = findAllProjectGroup();
        MoneyReadyDTO dto = new MoneyReadyDTO();
        List<MoneyReady> list = super.findByCis(dto);
        List<CollectCompareBO> boList = new ArrayList<>();
        Double reserveSum = 0.0;
        Double lastReserveSum = 0.0;
        if (month != 1) {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month - 1)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum - lastReserveSum);
                    bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum * 100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        } else {
            for (String projectGroup : projectGroups) {
                for (MoneyReady m : list) {
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year) && m.getMonth().equals(month)) {
                        reserveSum += m.getReserves();
                    }
                    if (m.getProjectGroup().equals(projectGroup) && m.getYear().equals(year - 1) && m.getMonth().equals(12)) {
                        lastReserveSum += m.getReserves();
                    }
                }
                if ((reserveSum != 0) || (lastReserveSum != 0)) {
                    CollectCompareBO bo = new CollectCompareBO();
                    bo.setProjectGroup(projectGroup);
                    bo.setMonth(month);
                    bo.setReserveSum(reserveSum);
                    bo.setLastReserveSum(lastReserveSum);
                    bo.setBalance(reserveSum - lastReserveSum);
                    bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum * 100);
                    /*if (lastReserveSum != 0) {
                        bo.setIncrease((reserveSum - lastReserveSum) / lastReserveSum);
                    } else {
                        bo.setIncrease(1.00);
                    }*/
                    boList.add(bo);
                    reserveSum = 0.00;
                    lastReserveSum = 0.00;    //置为0
                }
            }
            return boList;
        }
    }

    private Set<String> findAllProjectGroup() throws SerException {
        List<MoneyReady> list = super.findAll();
        Set<String> set = new HashSet<String>();
        for (MoneyReady m : list) {
            set.add(m.getProjectGroup());
        }
        return set;
    }
}