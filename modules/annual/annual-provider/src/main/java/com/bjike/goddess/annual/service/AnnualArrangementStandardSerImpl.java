package com.bjike.goddess.annual.service;

import com.bjike.goddess.annual.bo.AnnualArrangementStandardBO;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.entity.AnnualArrangementStandard;
import com.bjike.goddess.annual.entity.AnnualStandard;
import com.bjike.goddess.annual.enums.GuideAddrStatus;
import com.bjike.goddess.annual.to.AnnualArrangementStandardTO;
import com.bjike.goddess.annual.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.ArrangementAPI;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 年假层级标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-27 04:33 ]
 * @Description: [ 年假层级标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "annualSerCache")
@Service
public class AnnualArrangementStandardSerImpl extends ServiceImpl<AnnualArrangementStandard, AnnualArrangementStandardDTO> implements AnnualArrangementStandardSer {

    @Autowired
    private ArrangementAPI arrangementAPI;
    @Autowired
    private AnnualStandardSer annualStandardSer;

    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;

    /**
     * 检查权限(部门)
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是本部门人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(项目经理)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是该岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对查看权限（部门级别）
     */
    private Boolean guideIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.positCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
    /**
     * 权限
     */
    private Boolean guideAllTrueIdentity() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosin = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagTrue = guideAllTrueIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagPosin ) {
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
                flag = guideIdentity();
                break;
            case ADD:
                flag = guideIdentity();
                break;
            case EDIT:
                flag = guideIdentity();
                break;
            case DELETE:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case AUDIT:
                flag = guidePosinIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case XXLIST:
                flag = guideAllTrueIdentity();
                break;
            case XXADD:
                flag = guideAllTrueIdentity();
                break;
            case XXAPPLY:
                flag = guideAllTrueIdentity();
                break;
            case XXSEE:
                flag = guideAllTrueIdentity();
                break;
            case XXMYSELF:
                flag = guideAllTrueIdentity();
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    /**
     * 转换年假层级标准传输对象
     *
     * @param entity 年假层级标准实体数据
     * @return
     * @throws SerException
     */
    private AnnualArrangementStandardBO transformBO(AnnualArrangementStandard entity) throws SerException {
        AnnualArrangementStandardBO bo = BeanTransform.copyProperties(entity, AnnualArrangementStandardBO.class);
        ArrangementBO arrangementBO = arrangementAPI.findById(entity.getArrangementId());
        AnnualStandard annualStandard = entity.getStandard();
        bo.setArrangement(arrangementBO.getArrangement());
        bo.setStartCycle(annualStandard.getStartCycle());
        bo.setEndCycle(annualStandard.getEndCycle());
        bo.setStandardId(annualStandard.getId());
        bo.setAstrict(annualStandard.getAstrict());
        bo.setRemark(annualStandard.getRemark());
        return bo;
    }

    /**
     * 转换年假层级标准传输对象集合
     *
     * @param list 年假层级标准实体数据集合
     * @return
     * @throws SerException
     */
    private List<AnnualArrangementStandardBO> transformBOList(List<AnnualArrangementStandard> list) throws SerException {
        List<AnnualArrangementStandardBO> bos = new ArrayList<>(list.size());
        for (AnnualArrangementStandard entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AnnualArrangementStandardBO update(AnnualArrangementStandardTO to) throws SerException {
        checkPermission();
        AnnualArrangementStandardBO bo = this.findByArrangementStandard(to.getStandardId(), to.getArrangementId());
        AnnualArrangementStandard entity;
        if (bo == null) {
            entity = BeanTransform.copyProperties(to, AnnualArrangementStandard.class);
            entity.setStandard(annualStandardSer.findById(to.getStandardId()));
            if (null == entity.getStandard())
                throw new SerException("年假标准为空,无法保存");
            super.save(entity);
        } else {
            entity = super.findById(bo.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setStandard(annualStandardSer.findById(to.getArrangementId()));
            if (null == entity.getStandard())
                throw new SerException("年假标准为空,无法保存");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
        }
        return this.transformBO(entity);
    }

    @Override
    public AnnualArrangementStandardBO findByArrangementStandard(String standardId, String arrangementId) throws SerException {
        if (StringUtils.isBlank(standardId) && StringUtils.isBlank(arrangementId))
            return new AnnualArrangementStandardBO();
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standardId));
        dto.getConditions().add(Restrict.eq("arrangementId", arrangementId));
        AnnualArrangementStandard entity = super.findOne(dto);
        if (null == entity)
            return null;
        return this.transformBO(entity);
    }

    @Override
    public List<AnnualArrangementStandardBO> findByStandard(String standardId) throws SerException {
        if (StringUtils.isBlank(standardId))
            return new ArrayList<>(0);
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        dto.getConditions().add(Restrict.eq("standard.id", standardId));
        List<AnnualArrangementStandard> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<AnnualArrangementStandardBO> maps(AnnualArrangementStandardDTO dto) throws SerException {
        dto.getSorts().add("standard.id");
        dto.getSorts().add("arrangementId");
        List<AnnualArrangementStandard> list = super.findByPage(dto);
        return this.transformBOList(list);
    }

    @Override
    public AnnualArrangementStandardBO getById(String id) throws SerException {
        AnnualArrangementStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        AnnualArrangementStandardDTO dto = new AnnualArrangementStandardDTO();
        return super.count(dto);
    }
}