package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.ContractManagementBO;
import com.bjike.goddess.contractware.bo.InvoiceManagementBO;
import com.bjike.goddess.contractware.dto.ContractManagementDTO;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.entity.ContractManagement;
import com.bjike.goddess.contractware.entity.InvoiceManagement;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.InvoiceManagementTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * 发票管理业务实现
 *
 * @Author: [ jiangzaixuan ]
 * @Date: [ 2017-11-01 11:04 ]
 * @Description: [ 发票管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class InvoiceManagementSerImpl extends ServiceImpl<InvoiceManagement, InvoiceManagementDTO> implements InvoiceManagementSer {
    @Autowired
    private ContractManagementSer contractManagementSer;

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
            if (!flag) {
                throw new SerException ( "您不是相应部门的人员，不可以操作" );
            }
        }
        RpcTransmit.transmitUserToken ( userToken );
    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.getCusPermission ( "1" );
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
        String userToken = RpcTransmit.getUserToken ();
        UserBO userBO = userAPI.currentUser ();
        RpcTransmit.transmitUserToken ( userToken );
        String userName = userBO.getUsername ();
        if (!"admin".equals ( userName.toLowerCase () )) {
            flag = cusPermissionSer.busCusPermission ( "2" );
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        Boolean flagSee = guideSeeIdentity ();
        RpcTransmit.transmitUserToken ( userToken );
        Boolean flagAdd = guideAddIdentity ();
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken ();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus ();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity ();
                break;
            case ADD:
                flag = guideAddIdentity ();
                break;
            case EDIT:
                flag = guideAddIdentity ();
                break;
            case AUDIT:
                flag = guideAddIdentity ();
                break;
            case DELETE:
                flag = guideAddIdentity ();
                break;
            case CONGEL:
                flag = guideAddIdentity ();
                break;
            case THAW:
                flag = guideAddIdentity ();
                break;
            case COLLECT:
                flag = guideAddIdentity ();
                break;
            case IMPORT:
                flag = guideAddIdentity ();
                break;
            case EXPORT:
                flag = guideAddIdentity ();
                break;
            case UPLOAD:
                flag = guideAddIdentity ();
                break;
            case DOWNLOAD:
                flag = guideAddIdentity ();
                break;
            case SEE:
                flag = guideSeeIdentity ();
                break;
            case SEEFILE:
                flag = guideSeeIdentity ();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken ( userToken );
        return flag;
    }


    @Override
    public void add(InvoiceManagementTO invoiceManagementTO) throws SerException {
        InvoiceManagement model = BeanTransform.copyProperties ( invoiceManagementTO, InvoiceManagement.class, true );
        model.setElectronicEdition ( false );
        super.save ( model );
    }

    @Override
    public void delete(String id) throws SerException {
        if (StringUtils.isNotBlank ( id )) {
            InvoiceManagement model = super.findById ( id );
            if (model != null) {
                super.remove ( model );
            } else {
                throw new SerException ( "数据库中没有该数据" );
            }
        } else {
            throw new SerException ( ("id不能为空") );
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void modify(InvoiceManagementTO invoiceManagementTO) throws SerException {
        InvoiceManagement invoiceManagement = super.findById ( invoiceManagementTO.getId () );
        InvoiceManagement model = BeanTransform.copyProperties ( invoiceManagementTO, InvoiceManagement.class, true );
        BeanUtils.copyProperties ( model,invoiceManagement,"id","createTime" );
        invoiceManagement.setModifyTime ( LocalDateTime.now () );
        super.update ( invoiceManagement );
    }

    @Override
    public List<InvoiceManagementBO> pageList(InvoiceManagementDTO invoiceManagementDTO) throws SerException {
        InvoiceManagementDTO dto = new InvoiceManagementDTO ();
        if (StringUtils.isNotBlank ( invoiceManagementDTO.getInternalContractNumber () )) {
            dto.getConditions ().add ( Restrict.eq ( "internalContractNumber", invoiceManagementDTO.getInternalContractNumber () ) );
        }
        List<InvoiceManagement> list = super.findByPage ( dto );
        List<InvoiceManagementBO> boList = BeanTransform.copyProperties ( list, InvoiceManagementBO.class, false );
        return boList;
    }

    @Override
    public InvoiceManagementBO findOne(String id) throws SerException {
        InvoiceManagement model = super.findById ( id );
        InvoiceManagementBO invoiceManagementBO = BeanTransform.copyProperties ( model, InvoiceManagementBO.class, false );
        return invoiceManagementBO;
    }

    @Override
    public ContractManagementBO findByNumber(String number) throws SerException {
        ContractManagementDTO dto = new ContractManagementDTO ();
        dto.getConditions ().add ( Restrict.eq ( "internalContractNumber", number ) );
        ContractManagement contractManagementBO = contractManagementSer.findOne ( dto );
         return BeanTransform.copyProperties ( contractManagementBO, ContractManagementBO.class);
    }

    @Override
    public void updateElectronic(String id) throws SerException {
        InvoiceManagement model = super.findById ( id );
        model.setElectronicEdition ( true );
    }

}