package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.BussTypeWeightSetBO;
import com.bjike.goddess.customer.dto.BussTypeWeightSetDTO;
import com.bjike.goddess.customer.entity.BussTypeWeightSet;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.BussTypeWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务类型权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class BussTypeWeightSetSerImpl extends ServiceImpl<BussTypeWeightSet, BussTypeWeightSetDTO> implements BussTypeWeightSetSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;


    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity(String flagId) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission(flagId);
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity("1");
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity("3");
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
                flag = guideSeeIdentity("1");
                break;
            case ADD:
                flag = guideAddIdentity("3");
                break;
            case EDIT:
                flag = guideAddIdentity("3");
                break;
            case DELETE:
                flag = guideAddIdentity("3");
                break;
            case UPLOAD:
                flag = guideAddIdentity("3");
                break;
            case SEEFILE:
                flag = guideAddIdentity("3");
                break;
            case DOWNLOAD:
                flag = guideAddIdentity("3");
                break;
            case CONGEL:
                flag = guideAddIdentity("3");
                break;
            case THAW:
                flag = guideAddIdentity("3");
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        Long count = super.count(bussTypeWeightSetDTO);
        return count;
    }

    @Override
    public BussTypeWeightSetBO getOneBussType(String id) throws SerException {
        BussTypeWeightSet bussTypeWeightSet = super.findById(id);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }

    @Override
    public List<BussTypeWeightSetBO> listBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
      checkSeeIdentity("1");
        List<BussTypeWeightSet> bussTypeWeightSetList = super.findByCis(bussTypeWeightSetDTO, true);
        return BeanTransform.copyProperties(bussTypeWeightSetList, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BussTypeWeightSetBO addBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        checkAddIdentity("3");
        BussTypeWeightSet bussTypeWeightSet = BeanTransform.copyProperties(bussTypeWeightSetTO, BussTypeWeightSet.class, true);
        bussTypeWeightSet.setCreateTime(LocalDateTime.now());
        super.save(bussTypeWeightSet);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BussTypeWeightSetBO editBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        checkAddIdentity("3");
        BussTypeWeightSet bussTypeWeightSet = super.findById(bussTypeWeightSetTO.getId());
        LocalDateTime date = bussTypeWeightSet.getCreateTime();
        bussTypeWeightSet = BeanTransform.copyProperties(bussTypeWeightSetTO, BussTypeWeightSet.class);
        bussTypeWeightSet.setCreateTime(date);
        bussTypeWeightSet.setModifyTime(LocalDateTime.now());
        super.update(bussTypeWeightSet);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBussType(String id) throws SerException {
        checkAddIdentity("3");
        super.remove(id);
    }

    @Override
    public BussTypeWeightSetBO findByProArea(String businessType, String businessWay) throws SerException {
       BussTypeWeightSetDTO bussTypeWeightSetDTO = new BussTypeWeightSetDTO();
        bussTypeWeightSetDTO.getConditions().add(Restrict.eq("businessType",businessType));
        bussTypeWeightSetDTO.getConditions().add(Restrict.eq("businessWay",businessWay));
        BussTypeWeightSet bussTypeWeightSet =  super.findOne(bussTypeWeightSetDTO);
        return BeanTransform.copyProperties(bussTypeWeightSet,BussTypeWeightSetBO.class);
    }

    @Override
    public List<String> findBussType() throws SerException {
        List<BussTypeWeightSet> bussTypeWeightSetList = super.findAll();
        if(CollectionUtils.isEmpty(bussTypeWeightSetList)){
            return Collections.emptyList();
        }
        return bussTypeWeightSetList.stream().map(BussTypeWeightSet::getBusinessType).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findBussWayByBussType(String bussType) throws SerException {
        BussTypeWeightSetDTO bussTypeWeightSetDTO = new BussTypeWeightSetDTO();
        bussTypeWeightSetDTO.getConditions().add(Restrict.eq("businessType",bussType));
        List<BussTypeWeightSet> bussTypeWeightSetList = super.findByCis(bussTypeWeightSetDTO);
        if(CollectionUtils.isEmpty(bussTypeWeightSetList)){
            return Collections.emptyList();
        }
        return bussTypeWeightSetList.stream().map(BussTypeWeightSet::getBusinessWay).distinct().collect(Collectors.toList());
    }
}