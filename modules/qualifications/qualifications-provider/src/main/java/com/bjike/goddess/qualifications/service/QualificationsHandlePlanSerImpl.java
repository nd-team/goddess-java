package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.QualificationsHandlePlanDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandlePlan;
import com.bjike.goddess.qualifications.enums.GuideAddrStatus;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 资质办理计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsHandlePlanSerImpl extends ServiceImpl<QualificationsHandlePlan, QualificationsHandlePlanDTO> implements QualificationsHandlePlanSer {

    @Autowired
    private QualificationsHandleSer handleSer;
    @Autowired
    private HandlePlanStageSer handlePlanStageSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Transactional(rollbackFor = SerException.class)

    private QualificationsHandlePlanBO transformBO(QualificationsHandlePlan entity) throws SerException {
        QualificationsHandlePlanBO bo = BeanTransform.copyProperties(entity, QualificationsHandlePlanBO.class);
        bo.setHandleId(entity.getHandle().getId());
        return bo;
    }

    private List<QualificationsHandlePlanBO> transformBOList(List<QualificationsHandlePlan> list) throws SerException {
        List<QualificationsHandlePlanBO> bos = new ArrayList<>(0);
        for (QualificationsHandlePlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Override
    public QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = BeanTransform.copyProperties(to, QualificationsHandlePlan.class, true);
        entity.setHandle(handleSer.findById(to.getHandleId()));
        if (null == entity.getHandle())
            throw new SerException("资质办理数据为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setHandle(handleSer.findById(to.getHandleId()));
        if (null == entity.getHandle())
            throw new SerException("资质办理数据为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO delete(String id) throws SerException {
        QualificationsHandlePlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        if (handlePlanStageSer.findByPlan(id).size() != 0)
            throw new SerException("存在依赖关系,无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<QualificationsHandlePlanBO> findByHandle(String handleId) throws SerException {
        QualificationsHandlePlanDTO dto = new QualificationsHandlePlanDTO();
        dto.getConditions().add(Restrict.eq("handle.id", handleId));
        List<QualificationsHandlePlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<QualificationsHandlePlanBO> maps(QualificationsHandlePlanDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsHandlePlanBO getById(String id) throws SerException {
        QualificationsHandlePlan entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAdd = guideAddIdentity();
        if( flagSee || flagAdd ){
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
}