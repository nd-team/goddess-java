package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.entity.EquityInvest;
import com.bjike.goddess.moneyside.entity.InvestForm;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.EquityInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资条件-股权投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class EquityInvestSerImpl extends ServiceImpl<EquityInvest, EquityInvestDTO> implements EquityInvestSer {
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
        return flag;
    }

    @Override
    public Long countEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        Long count = super.count(equityInvestDTO);
        return count;
    }

    @Override
    public EquityInvestBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        EquityInvest equityInvest = super.findById(id);
        return BeanTransform.copyProperties(equityInvest, EquityInvestBO.class);
    }

    @Override
    public List<EquityInvestBO> findListEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        List<EquityInvest> equityInvests = super.findByPage(equityInvestDTO);
        List<EquityInvestBO> equityInvestBOS = BeanTransform.copyProperties(equityInvests,EquityInvestBO.class);
        return equityInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInvestBO insertEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        EquityInvest equityInvest = BeanTransform.copyProperties(equityInvestTO,EquityInvest.class,true);
        equityInvest.setCreateTime(LocalDateTime.now());
        super.save(equityInvest);
        return BeanTransform.copyProperties(equityInvest,EquityInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInvestBO editEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        if (StringUtils.isBlank(equityInvestTO.getId())) {
            throw new SerException("id不能为空");
        }
        EquityInvest equityInvest = super.findById(equityInvestTO.getId());
        BeanTransform.copyProperties(equityInvestTO,equityInvest,true);
        equityInvest.setModifyTime(LocalDateTime.now());
        super.update(equityInvest);
        return BeanTransform.copyProperties(equityInvest,EquityInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeEquityInvest(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}