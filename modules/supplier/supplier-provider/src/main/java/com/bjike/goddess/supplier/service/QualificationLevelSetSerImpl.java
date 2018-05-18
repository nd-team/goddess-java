package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.QualificationLevelSetBO;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.entity.QualificationLevelSet;
import com.bjike.goddess.supplier.entity.SupCusPermission;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
import com.bjike.goddess.supplier.to.QualificationLevelSetTO;
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
 * 资质等级设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:08 ]
 * @Description: [ 资质等级设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class QualificationLevelSetSerImpl extends ServiceImpl<QualificationLevelSet, QualificationLevelSetDTO> implements QualificationLevelSetSer {

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
    public Long countLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        Long count = super.count(qualificationLevelSetDTO);
        return count;
    }

    @Override
    public QualificationLevelSetBO getOneById(String id) throws SerException {
        QualificationLevelSet qualificationLevelSet = super.findById(id);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSet.class);
    }

    @Override
    public List<QualificationLevelSetBO> listLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
       checkSeeIdentity();
        List<QualificationLevelSet> qualificationLevelSetList = super.findByCis(qualificationLevelSetDTO,true);
        return BeanTransform.copyProperties(qualificationLevelSetList,QualificationLevelSetBO.class);
    }

    @Override
    public QualificationLevelSetBO addLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        checkSeeIdentity();
        QualificationLevelSet qualificationLevelSet = BeanTransform.copyProperties(qualificationLevelSetTO,QualificationLevelSet.class);
        qualificationLevelSet.setCreateTime(LocalDateTime.now());
        super.save(qualificationLevelSet);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSetBO.class);
    }

    @Override
    public QualificationLevelSetBO editLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        checkSeeIdentity();
        QualificationLevelSet qualificationLevelSet = super.findById(qualificationLevelSetTO.getId());
        LocalDateTime dateTime = qualificationLevelSet.getCreateTime();
        qualificationLevelSet = BeanTransform.copyProperties(qualificationLevelSetTO,QualificationLevelSet.class,true);
        qualificationLevelSet.setCreateTime(dateTime);
        qualificationLevelSet.setModifyTime(LocalDateTime.now());
        super.update(qualificationLevelSet);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSetBO.class);
    }

    @Override
    public void deleteLevelSet(String id) throws SerException {
        checkSeeIdentity();
        super.remove(id);
    }

    @Override
    public List<String> findAllLevel() throws SerException {
        List<QualificationLevelSet> qualificationLevelSetList = super.findAll();
        if(CollectionUtils.isEmpty(qualificationLevelSetList)){
            return Collections.emptyList();
        }
        return qualificationLevelSetList.stream().map(QualificationLevelSet::getQualificationLevel).distinct().collect(Collectors.toList());
    }
}