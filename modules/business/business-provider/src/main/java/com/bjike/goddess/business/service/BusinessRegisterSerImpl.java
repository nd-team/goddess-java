package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.bo.RegisterNaTypeCaBO;
import com.bjike.goddess.business.enums.GuideAddrStatus;
import com.bjike.goddess.business.excel.SonPermissionObject;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.entity.BusinessRegister;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 工商注册业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessRegisterSerImpl extends ServiceImpl<BusinessRegister, BusinessRegisterDTO> implements BusinessRegisterSer {
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private BusinessAnnualInfoSer businessAnnualInfoSer;
    @Autowired
    private BusinessTaxChangeSer businessTaxChangeSer;
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
        Boolean flagSeeRegister = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddRegister = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("businessregister");
        obj.setDescribesion("工商注册");
        if (flagSeeRegister || flagAddRegister) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeAnnual = businessAnnualInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("businessannualinfo");
        obj.setDescribesion("工商年检信息");
        if (flagSeeAnnual) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeTax = businessTaxChangeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("businesstaxchange");
        obj.setDescribesion("工商税务变更");
        if (flagSeeTax) {
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
    public Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        businessRegisterDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessRegisterDTO);
        return counts;
    }
    @Override
    public BusinessRegisterBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        BusinessRegister businessRegister = super.findById(id);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class);
    }
    @Override
    public List<BusinessRegisterBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        checkSeeIdentity();
        List<BusinessRegister> businessRegisters = super.findByCis(businessRegisterDTO,true);
        List<BusinessRegisterBO> businessRegisterBOS = BeanTransform.copyProperties(businessRegisters,BusinessRegisterBO.class,true);
        return businessRegisterBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        checkAddIdentity();
        BusinessRegister businessRegister = BeanTransform.copyProperties(businessRegisterTO,BusinessRegister.class,true);
        businessRegister.setCreateTime(LocalDateTime.now());
        super.save(businessRegister);
        return BeanTransform.copyProperties(businessRegister,BusinessRegisterBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(businessRegisterTO.getId())){
            throw new SerException("id不能为空");
        }
        BusinessRegister businessRegister = super.findById(businessRegisterTO.getId());
        BeanTransform.copyProperties(businessRegisterTO,businessRegister,true);
        businessRegister.setModifyTime(LocalDateTime.now());
        super.update(businessRegister);
        return BeanTransform.copyProperties(businessRegisterTO,BusinessRegisterBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessRegister(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public List<RegisterNaTypeCaBO> findRegiNaTyCa() throws SerException {
        List<BusinessRegister> businessRegisters = super.findAll();
        return BeanTransform.copyProperties(businessRegisters,RegisterNaTypeCaBO.class);
    }

    @Override
    public List<String> findAddress() throws SerException {
        List<BusinessRegister> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (BusinessRegister model : list) {
            String address = model.getAddress();
            if (StringUtils.isNotBlank(model.getAddress())) {
                set.add(address);
            }
        }
        return new ArrayList<>(set);
    }
}