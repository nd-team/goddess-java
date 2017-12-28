package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.entity.BusinessAnnualInfo;
import com.bjike.goddess.business.enums.GuideAddrStatus;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.business.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
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
 * 工商年检信息业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessSerCache")
@Service
public class BusinessAnnualInfoSerImpl extends ServiceImpl<BusinessAnnualInfo, BusinessAnnualInfoDTO> implements BusinessAnnualInfoSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

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
    public Long countBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        businessAnnualInfoDTO.getSorts().add("createTime=desc");
        Long counts = super.count(businessAnnualInfoDTO);
        return counts;
    }

    @Override
    public BusinessAnnualInfoBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        BusinessAnnualInfo businessAnnualInfo = super.findById(id);
        return BeanTransform.copyProperties(businessAnnualInfo, BusinessAnnualInfoBO.class);
    }

    @Override
    public List<BusinessAnnualInfoBO> findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        checkSeeIdentity();
        List<BusinessAnnualInfo> businessAnnualInfos = super.findByCis(businessAnnualInfoDTO, true);
        List<BusinessAnnualInfoBO> businessAnnualInfoBOS = BeanTransform.copyProperties(businessAnnualInfos, BusinessAnnualInfoBO.class);
        return businessAnnualInfoBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessAnnualInfoBO insertBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        checkAddIdentity();
        BusinessAnnualInfo businessAnnualInfo = BeanTransform.copyProperties(businessAnnualInfoTO, BusinessAnnualInfo.class, true);
        businessAnnualInfo.setCreateTime(LocalDateTime.now());
        super.save(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfo, BusinessAnnualInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessAnnualInfoBO editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(businessAnnualInfoTO.getId())) {
            throw new SerException("id不能为空");
        }
        BusinessAnnualInfo businessAnnualInfo = super.findById(businessAnnualInfoTO.getId());
        BeanTransform.copyProperties(businessAnnualInfoTO, businessAnnualInfo, true);
        businessAnnualInfo.setModifyTime(LocalDateTime.now());
        super.update(businessAnnualInfo);
        return BeanTransform.copyProperties(businessAnnualInfoTO, BusinessAnnualInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeBusinessAnnualInfo(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}