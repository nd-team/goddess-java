package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.api.SupCusPermissionAPI;
import com.bjike.goddess.supplier.bo.SupplierTypeSetBO;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.entity.SupCusPermission;
import com.bjike.goddess.supplier.entity.SupplierTypeSet;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.SupplierTypeSetTO;
import com.bjike.goddess.supplier.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 供应商类型设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:07 ]
 * @Description: [ 供应商类型设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class SupplierTypeSetSerImpl extends ServiceImpl<SupplierTypeSet, SupplierTypeSetDTO> implements SupplierTypeSetSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SupCusPermissionSer supCusPermissionSer;
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
            flag = supCusPermissionSer.busSupCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
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
            flag = supCusPermissionSer.busSupCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken( userToken );
        if( flagSee ){
            return true;
        }else{
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
                flag = guideSeeIdentity();
                break;
            case EDIT:
                flag = guideSeeIdentity();
                break;
            case DELETE:
                flag = guideSeeIdentity();
                break;
            case COLLECT:
                flag = guideSeeIdentity();
                break;
            case IMPORT:
                flag = guideSeeIdentity();
                break;
            case EXPORT:
                flag = guideSeeIdentity();
                break;
            case UPLOAD:
                flag = guideSeeIdentity();
                break;
            case DOWNLOAD:
                flag = guideSeeIdentity();
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
    public Long countSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        Long count = super.count(supplierTypeSetDTO);
        return count;
    }

    @Override
    public SupplierTypeSetBO getOneById(String id) throws SerException {
        SupplierTypeSet supplierTypeSet = super.findById(id);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public List<SupplierTypeSetBO> listSupplierTypeSet(SupplierTypeSetDTO supplierTypeSetDTO) throws SerException {
        List<SupplierTypeSet> supplierTypeSetList = super.findByCis(supplierTypeSetDTO, true);
        return BeanTransform.copyProperties(supplierTypeSetList, SupplierTypeSetBO.class);
    }

    @Override
    public SupplierTypeSetBO addSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        SupplierTypeSet supplierTypeSet = BeanTransform.copyProperties(supplierTypeSetTO, SupplierTypeSet.class);
        supplierTypeSet.setCreateTime(LocalDateTime.now());
        super.save(supplierTypeSet);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public SupplierTypeSetBO editSupplierTypeSet(SupplierTypeSetTO supplierTypeSetTO) throws SerException {
        SupplierTypeSet supplierTypeSet = super.findById(supplierTypeSetTO.getId());
        LocalDateTime dateTime = supplierTypeSet.getCreateTime();
        supplierTypeSet = BeanTransform.copyProperties(supplierTypeSetTO, SupplierTypeSet.class, true);
        supplierTypeSet.setCreateTime(dateTime);
        supplierTypeSet.setModifyTime(LocalDateTime.now());
        super.update(supplierTypeSet);
        return BeanTransform.copyProperties(supplierTypeSet, SupplierTypeSetBO.class);
    }

    @Override
    public void deleteSupplierInfo(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<String> findAllType() throws SerException {
        List<SupplierTypeSet> supplierTypeSetList = super.findAll();
        if(CollectionUtils.isEmpty(supplierTypeSetList)){
            return Collections.emptyList();
        }
     return supplierTypeSetList.stream().map(SupplierTypeSet::getSupplierType).distinct().collect(Collectors.toList());
    }
}