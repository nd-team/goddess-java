package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.entity.NumSpecification;
import com.bjike.goddess.datastore.enums.GuideAddrStatus;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储编号规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "datastoreSerCache")
@Service
public class NumSpecificationSerImpl extends ServiceImpl<NumSpecification, NumSpecificationDTO> implements NumSpecificationSer {
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
    public Long countNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        numSpecificationDTO.getSorts().add("createTime=desc");
        Long counts = super.count(numSpecificationDTO);
        return counts;
    }

    @Override
    public NumSpecificationBO getOne(String id) throws SerException {
        NumSpecification numSpecification = super.findById(id);
        return BeanTransform.copyProperties(numSpecification, NumSpecificationBO.class);
    }


    @Override
    public List<NumSpecificationBO> findListNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        checkSeeIdentity();
        numSpecificationDTO.getSorts().add("createTime=desc");
        List<NumSpecification> numSpecifications = super.findByCis(numSpecificationDTO, true);
        List<NumSpecificationBO> numSpecificationBOS = BeanTransform.copyProperties(numSpecifications, NumSpecificationBO.class, true);
        return numSpecificationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NumSpecificationBO insertNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        checkAddIdentity();
        NumSpecification numSpecification = BeanTransform.copyProperties(numSpecificationTO, NumSpecification.class, true);
        numSpecification.setCreateTime(LocalDateTime.now());
        super.save(numSpecification);
        return BeanTransform.copyProperties(numSpecification, NumSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public NumSpecificationBO editNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        checkAddIdentity();
        NumSpecification numSpecification = super.findById(numSpecificationTO.getId());
        LocalDateTime creatTime = numSpecification.getCreateTime();
        numSpecification = BeanTransform.copyProperties(numSpecificationTO, NumSpecification.class, true);
        numSpecification.setCreateTime(creatTime);
        numSpecification.setModifyTime(LocalDateTime.now());
        super.update(numSpecification);
        return BeanTransform.copyProperties(numSpecificationTO, NumSpecificationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeNumSpecification(String id) throws SerException {
        checkAddIdentity();
        super.remove(id);
    }
}