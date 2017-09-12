package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.BusinessTypeBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessTypeDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessType;
import com.bjike.goddess.marketdevelopment.enums.GuideAddrStatus;
import com.bjike.goddess.marketdevelopment.to.BusinessTypeTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务类型业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:20 ]
 * @Description: [ 业务类型业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class BusinessTypeSerImpl extends ServiceImpl<BusinessType, BusinessTypeDTO> implements BusinessTypeSer {

    @Autowired
    private MarPermissionSer marPermissionSer;

    @Autowired
    private BusinessCourseSer businessCourseSer;
    @Autowired
    private UserAPI userAPI;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";


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
            flag = marPermissionSer.getMarPermission(marketCheck);
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
            flag = marPermissionSer.getMarPermission(marketManage);
        } else {
            flag = true;
        }
        return flag;
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
    public List<String> findDirection() throws SerException {
        BusinessTypeDTO dto = new BusinessTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        dto.getSorts().add("createTime=desc");
        List<BusinessType> list = super.findByCis(dto);
        if (null != list && list.size() > 0) {
            return list.stream().map(BusinessType::getType).distinct().collect(Collectors.toList());
        }
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTypeBO save(BusinessTypeTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessType entity = BeanTransform.copyProperties(to, BusinessType.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, BusinessTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTypeBO update(BusinessTypeTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                BusinessType entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, BusinessTypeBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTypeBO congeal(BusinessTypeTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessType entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, BusinessTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTypeBO thaw(BusinessTypeTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessType entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, BusinessTypeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessTypeBO delete(BusinessTypeTO to) throws SerException {
//        if (!marPermissionSer.getMarPermission(marketManage))
//            throw new SerException("您的帐号没有权限");
        BusinessType entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (businessCourseSer.findByType(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, BusinessTypeBO.class);
    }

    @Override
    public List<BusinessTypeBO> findThaw() throws SerException {
        BusinessTypeDTO dto = new BusinessTypeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        dto.getSorts().add("createTime=desc");
        List<BusinessType> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, BusinessTypeBO.class);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = marPermissionSer.getMarPermission(marketCheck);
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = marPermissionSer.getMarPermission(marketManage);
        if (flagSee || flagAdd) {
            return true;
        } else {
            return false;
        }
    }

}