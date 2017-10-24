package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.InvoiceInfoBO;
import com.bjike.goddess.foreigntax.dto.InvoiceInfoDTO;
import com.bjike.goddess.foreigntax.entity.InvoiceInfo;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceInfoTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
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
 * 发票基本登记业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:36 ]
 * @Description: [ 发票基本登记业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class InvoiceInfoSerImpl extends ServiceImpl<InvoiceInfo, InvoiceInfoDTO> implements InvoiceInfoSer {
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
    public Long count(InvoiceInfoDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public InvoiceInfoBO getOne(String id) throws SerException {
        InvoiceInfo invoiceInfo = super.findById(id);
        InvoiceInfoBO invoiceInfoBO = BeanTransform.copyProperties(invoiceInfo, InvoiceInfoBO.class);
        return invoiceInfoBO;
    }

    @Override
    public List<InvoiceInfoBO> list(InvoiceInfoDTO dto) throws SerException {
        checkSeeIdentity();
        List<InvoiceInfo> invoiceInfos = super.findByCis(dto);
        List<InvoiceInfoBO> invoiceInfoBOS = BeanTransform.copyProperties(invoiceInfos, InvoiceInfoBO.class);
        return invoiceInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvoiceInfoBO insert(InvoiceInfoTO to) throws SerException {
        checkAddIdentity();
        InvoiceInfo invoiceInfo = BeanTransform.copyProperties(to, InvoiceInfo.class, true);
        invoiceInfo.setCreateTime(LocalDateTime.now());
        super.save(invoiceInfo);
        InvoiceInfoBO bo = BeanTransform.copyProperties(invoiceInfo, InvoiceInfoBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvoiceInfoBO edit(InvoiceInfoTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            InvoiceInfo invoiceInfo = super.findById(to.getId());
            LocalDateTime createTime = invoiceInfo.getCreateTime();
            invoiceInfo = BeanTransform.copyProperties(to, InvoiceInfo.class, true);
            invoiceInfo.setCreateTime(createTime);
            invoiceInfo.setModifyTime(LocalDateTime.now());
            InvoiceInfoBO bo = BeanTransform.copyProperties(invoiceInfo, InvoiceInfoBO.class);
            return bo;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public Set<String> fullTicket() throws SerException {
        Set<String> set = new HashSet<>();
        List<InvoiceInfo> list = new ArrayList<>();
        for (InvoiceInfo invoiceInfo : list) {
            set.add(invoiceInfo.getFullTicket());
        }
        return set;
    }

    @Override
    public Set<String> ticketWay() throws SerException {
        Set<String> set = new HashSet<>();
        List<InvoiceInfo> list = new ArrayList<>();
        for (InvoiceInfo invoiceInfo : list) {
            set.add(invoiceInfo.getTicketWay());
        }
        return set;
    }
}