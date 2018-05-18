package com.bjike.goddess.foreigntax.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.InvoiceReceiveBO;
import com.bjike.goddess.foreigntax.dto.InvoiceReceiveDTO;
import com.bjike.goddess.foreigntax.entity.InvoiceReceive;
import com.bjike.goddess.foreigntax.enums.GuideAddrStatus;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import com.bjike.goddess.foreigntax.to.InvoiceReceiveTO;
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
 * 发票领用业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-13 05:38 ]
 * @Description: [ 发票领用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "foreigntaxSerCache")
@Service
public class InvoiceReceiveSerImpl extends ServiceImpl<InvoiceReceive, InvoiceReceiveDTO> implements InvoiceReceiveSer {
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
    public Long count(InvoiceReceiveDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public InvoiceReceiveBO getOne(String id) throws SerException {
        InvoiceReceive invoiceReceive = super.findById(id);
        InvoiceReceiveBO bo = BeanTransform.copyProperties(invoiceReceive, InvoiceReceiveBO.class);
        return bo;
    }

    @Override
    public List<InvoiceReceiveBO> list(InvoiceReceiveDTO dto) throws SerException {
        checkSeeIdentity();
        List<InvoiceReceive> invoiceReceives = super.findByCis(dto);
        List<InvoiceReceiveBO> invoiceReceiveBOS = BeanTransform.copyProperties(invoiceReceives, InvoiceReceiveBO.class);
        return invoiceReceiveBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvoiceReceiveBO insert(InvoiceReceiveTO to) throws SerException {
        checkAddIdentity();
        InvoiceReceive invoiceReceive = BeanTransform.copyProperties(to, InvoiceReceive.class, true);
        invoiceReceive.setCreateTime(LocalDateTime.now());
        super.save(invoiceReceive);
        InvoiceReceiveBO bo = BeanTransform.copyProperties(invoiceReceive, InvoiceReceiveBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvoiceReceiveBO edit(InvoiceReceiveTO to) throws SerException {
        checkAddIdentity();
        if (StringUtils.isNotBlank(to.getId())) {
            InvoiceReceive invoiceReceive = super.findById(to.getId());
            LocalDateTime createTime = invoiceReceive.getCreateTime();
            invoiceReceive = BeanTransform.copyProperties(to, InvoiceReceive.class, true);
            invoiceReceive.setCreateTime(createTime);
            invoiceReceive.setModifyTime(LocalDateTime.now());
            super.update(invoiceReceive);
            InvoiceReceiveBO bo = BeanTransform.copyProperties(invoiceReceive, InvoiceReceiveBO.class);
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
}