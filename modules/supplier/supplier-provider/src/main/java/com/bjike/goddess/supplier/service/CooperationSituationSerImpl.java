package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;
import com.bjike.goddess.supplier.enums.GuideAddrStatus;
import com.bjike.goddess.supplier.to.CooperationSituationTO;
import com.bjike.goddess.supplier.to.GuidePermissionTO;
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
import java.util.List;

/**
 * 合作情况业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.664 ]
 * @Description: [ 合作情况业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class CooperationSituationSerImpl extends ServiceImpl<CooperationSituation, CooperationSituationDTO> implements CooperationSituationSer {

    @Autowired
    private SupplierInformationSer supplierInformationSer;
    @Autowired
    private SupCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

//    private static final String idFlag = "supplier-01";

    /**
     * 转换合作情况传输对象
     *
     * @param entity 合作情况实体对象
     * @return
     */
    private CooperationSituationBO transformBO(CooperationSituation entity) {
        CooperationSituationBO bo = BeanTransform.copyProperties(entity, CooperationSituationBO.class);
        bo.setInformationId(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<CooperationSituationBO> findByInformation(String infoId) throws SerException {
        CooperationSituationDTO dto = new CooperationSituationDTO();
        dto.getConditions().add(Restrict.eq("information.id", infoId));
        List<CooperationSituation> list = super.findByCis(dto);
        List<CooperationSituationBO> bos = new ArrayList<>(0);
        for (CooperationSituation entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO save(CooperationSituationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CooperationSituation entity = BeanTransform.copyProperties(to, CooperationSituation.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == entity.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO update(CooperationSituationTO to) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CooperationSituation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        CooperationSituation cooperationSituation = BeanTransform.copyProperties(to, CooperationSituation.class, true);
        cooperationSituation.setInformation(supplierInformationSer.findById(to.getInformationId()));
        if (null == cooperationSituation.getInformation())
            throw new SerException("供应商基本信息id错误,无法查询对应数据");
        BeanUtils.copyProperties(cooperationSituation,entity,"id","createTime");
//        cooperationSituation.setCreateTime(entity.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CooperationSituationBO delete(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CooperationSituation entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不能为空");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public CooperationSituationBO getById(String id) throws SerException {
//        if (!supPermissionSer.getSupPermission(idFlag))
//            throw new SerException("您的帐号没有权限");
        CooperationSituation entity = super.findById(id);
        if (null == entity)
            return null;
        else
            return this.transformBO(entity);
    }

//    @Override
//    public Boolean sonPermission() throws SerException {
//        return supPermissionSer.getSupPermission(idFlag);
//    }

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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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
            flag = cusPermissionSer.getSupCusPermission("1");
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
            flag = cusPermissionSer.busSupCusPermission("2");
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
}