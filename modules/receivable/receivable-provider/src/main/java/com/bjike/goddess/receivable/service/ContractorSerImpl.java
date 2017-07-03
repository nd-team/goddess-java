package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.enums.GuideAddrStatus;
import com.bjike.goddess.receivable.excel.SonPermissionObject;
import com.bjike.goddess.receivable.to.ContractorTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 承包商列表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "receivableSerCache")
@Service
public class ContractorSerImpl extends ServiceImpl<Contractor, ContractorDTO> implements ContractorSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private ReceivableSubsidiarySer receivableSubsidiarySer;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("contractor");
        obj.setDescribesion("承包商列表");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnswer = receivableSubsidiarySer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("receivablesubsidiary");
        obj.setDescribesion("回款明细");
        if (flagSeeAnswer) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        return list;
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
    public Long countContractor(ContractorDTO contractorDTO) throws SerException {
        Long count = super.count(contractorDTO);
        return count;
    }

    @Override
    public ContractorBO getOne(String id) throws SerException {
        Contractor contractor = super.findById(id);
        return BeanTransform.copyProperties(contractor,ContractorBO.class);
    }
    @Override
    public List<ContractorBO> findListContractor(ContractorDTO contractorDTO) throws SerException {
        List<Contractor> contractors = super.findByPage(contractorDTO);
        List<ContractorBO> contractorBOS = BeanTransform.copyProperties(contractors, ContractorBO.class);
        return contractorBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractorBO insertContractor(ContractorTO contractorTO) throws SerException {
        Contractor contractor = BeanTransform.copyProperties(contractorTO,Contractor.class,true);
        contractor.setCreateTime(LocalDateTime.now());
        super.save(contractor);
        return BeanTransform.copyProperties(contractor,ContractorBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractorBO editContractor(ContractorTO contractorTO) throws SerException {
        if (!StringUtils.isEmpty(contractorTO.getId())) {
            Contractor contractor = super.findById(contractorTO.getId());
            BeanTransform.copyProperties(contractorTO, contractor, true);
            contractor.setModifyTime(LocalDateTime.now());
            super.update(contractor);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(contractorTO, ContractorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeContractor(String id) throws SerException {
        super.remove(id);
    }
}