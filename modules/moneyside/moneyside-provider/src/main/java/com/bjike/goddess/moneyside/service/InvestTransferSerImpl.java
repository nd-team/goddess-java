package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.entity.InvestTransfer;
import com.bjike.goddess.moneyside.enums.GuideAddrStatus;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
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

/**
 * 投资转让业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class InvestTransferSerImpl extends ServiceImpl<InvestTransfer, InvestTransferDTO> implements InvestTransferSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Autowired
    private ModuleAPI moduleAPI;

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
    public Long countInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        Long count = super.count(investTransferDTO);
        return count;
    }

    @Override
    public InvestTransferBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        InvestTransfer investTransfer = super.findById(id);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Override
    public List<InvestTransferBO> findListInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        List<InvestTransfer> investTransfers = super.findByPage(investTransferDTO);
        List<InvestTransferBO> investTransferBOS = BeanTransform.copyProperties(investTransfers, InvestTransferBO.class);
        return investTransferBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestTransferBO insertInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        InvestTransfer investTransfer = BeanTransform.copyProperties(investTransferTO, InvestTransfer.class, true);
        investTransfer.setCreateTime(LocalDateTime.now());
        super.save(investTransfer);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestTransferBO editInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        if (StringUtils.isBlank(investTransferTO.getId())) {
            throw new SerException("id不能为空");
        }
        InvestTransfer investTransfer = super.findById(investTransferTO.getId());
        BeanTransform.copyProperties(investTransferTO, investTransfer, true);
        investTransfer.setModifyTime(LocalDateTime.now());
        super.update(investTransfer);
        return BeanTransform.copyProperties(investTransfer, InvestTransferBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeInvestTransfer(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<UserBO> findUserListInOrgan() throws SerException {
        List<UserBO> bos = new ArrayList<>(0);
        if(moduleAPI.isCheck("organize")) {
            String userToken = RpcTransmit.getUserToken();
            RpcTransmit.transmitUserToken(userToken);
            bos = positionDetailUserAPI.findUserListInOrgan();
        }
        return bos;
    }
}