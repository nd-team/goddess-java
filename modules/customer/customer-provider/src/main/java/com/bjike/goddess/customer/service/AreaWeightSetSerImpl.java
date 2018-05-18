package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.entity.AreaWeightSet;
import com.bjike.goddess.customer.enums.GuideAddrStatus;
import com.bjike.goddess.customer.to.AreaWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.From;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 地区权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class AreaWeightSetSerImpl extends ServiceImpl<AreaWeightSet, AreaWeightSetDTO> implements AreaWeightSetSer {

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
    public Long countAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        Long count = super.count(areaWeightSetDTO);
        return count;
    }

    @Override
    public AreaWeightSetBO getOneAreaWeight(String id) throws SerException {
        AreaWeightSet areaWeightSet = super.findById(id);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }

    @Override
    public List<AreaWeightSetBO> listAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        checkSeeIdentity("1");
        List<AreaWeightSet> areaWeightSetList = super.findByCis(areaWeightSetDTO, true);
        return BeanTransform.copyProperties(areaWeightSetList, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AreaWeightSetBO addAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        checkAddIdentity("3");
        AreaWeightSet areaWeightSet = BeanTransform.copyProperties(areaWeightSetTO, AreaWeightSet.class, true);
        areaWeightSet.setCreateTime(LocalDateTime.now());
        super.save(areaWeightSet);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AreaWeightSetBO editAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        checkAddIdentity("3");
        AreaWeightSet areaWeightSet = super.findById(areaWeightSetTO.getId());
        LocalDateTime date = areaWeightSet.getCreateTime();
        areaWeightSet = BeanTransform.copyProperties(areaWeightSetTO, AreaWeightSet.class, true);
        areaWeightSet.setCreateTime(date);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAreaWeight(String id) throws SerException {
        checkAddIdentity("3");
        super.remove(id);
    }

    @Override
    public AreaWeightSetBO findByProArea(String provinces, String area) throws SerException {
        AreaWeightSetDTO areaWeightSetDTO = new AreaWeightSetDTO();
        areaWeightSetDTO.getConditions().add(Restrict.eq("provinces",provinces));
        areaWeightSetDTO.getConditions().add(Restrict.eq("area",area));
        AreaWeightSet areaWeightSet = super.findOne(areaWeightSetDTO);
        return BeanTransform.copyProperties(areaWeightSet,AreaWeightSetBO.class);
    }

    @Override
    public List<String> findProvinces() throws SerException {
        List<AreaWeightSet> areaWeightSets = super.findAll();
        if(CollectionUtils.isEmpty(areaWeightSets)){
            return Collections.emptyList();
        }
        return areaWeightSets.stream().map(AreaWeightSet::getProvinces).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> findAreaByPro(String provinces) throws SerException {
        AreaWeightSetDTO areaWeightSetDTO = new AreaWeightSetDTO();
        areaWeightSetDTO.getConditions().add(Restrict.eq("provinces",provinces));
        List<AreaWeightSet> areaWeightSets = super.findByCis(areaWeightSetDTO);
        if(CollectionUtils.isEmpty(areaWeightSets)){
            return Collections.emptyList();
        }
        return areaWeightSets.stream().map(AreaWeightSet::getArea).distinct().collect(Collectors.toList());
    }
}