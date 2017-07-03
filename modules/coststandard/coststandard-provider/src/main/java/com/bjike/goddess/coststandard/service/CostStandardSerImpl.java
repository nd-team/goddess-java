package com.bjike.goddess.coststandard.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.coststandard.bo.CostStandardBO;
import com.bjike.goddess.coststandard.bo.CostStandardOpinionBO;
import com.bjike.goddess.coststandard.dto.CostStandardContrastDTO;
import com.bjike.goddess.coststandard.dto.CostStandardDTO;
import com.bjike.goddess.coststandard.entity.CostStandard;
import com.bjike.goddess.coststandard.enums.GuideAddrStatus;
import com.bjike.goddess.coststandard.excel.SonPermissionObject;
import com.bjike.goddess.coststandard.to.CostStandardTO;
import com.bjike.goddess.coststandard.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 费用标准业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-24 11:22 ]
 * @Description: [ 费用标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "coststandardSerCache")
@Service
public class CostStandardSerImpl extends ServiceImpl<CostStandard, CostStandardDTO> implements CostStandardSer {

    @Autowired
    private CostStandardContrastSer costStandardContrastSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Override
    public CostStandardBO save(CostStandardTO to) throws SerException {
        CostStandard entity = BeanTransform.copyProperties(to, CostStandard.class, true);
        entity.setStatus(Status.THAW);
        entity.setDraftDate(LocalDate.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO update(CostStandardTO to) throws SerException {
        CostStandard entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO delete(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        CostStandardContrastDTO dto = new CostStandardContrastDTO();
        dto.getConditions().add(Restrict.eq("standard.id", entity.getId()));
        if (costStandardContrastSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO congeal(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO thaw(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public CostStandardBO getById(String id) throws SerException {
        CostStandard entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, CostStandardBO.class);
    }

    @Override
    public List<CostStandardBO> maps(CostStandardDTO dto) throws SerException {
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), CostStandardBO.class);
    }

    @Override
    public List<CostStandardBO> findThaw() throws SerException {
        CostStandardDTO dto = new CostStandardDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        return BeanTransform.copyProperties(super.findByCis(dto), CostStandardBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        CostStandardDTO dto = new CostStandardDTO();
        return super.count(dto);
    }

    @Override
    public List<CostStandardOpinionBO> findOpinion() throws SerException {
        List<CostStandardOpinionBO> bos = new ArrayList<>(0);
        List<CostStandardBO> list = this.findThaw();
        String format = "名称:%s 地区:%s 适用部门:%s 标准:%s";
        for (CostStandardBO bo : list) {
            CostStandardOpinionBO opinionBO = new CostStandardOpinionBO();
            opinionBO.setId(bo.getId());
            opinionBO.setValue(String.format(format, bo.getName(), bo.getArea(), bo.getDepartment(), bo.getStandard()));
            bos.add(opinionBO);
        }
        return bos;
    }


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("coststandard");
        obj.setDescribesion("费用标准");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = costStandardContrastSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("coststandardcontrast");
        obj.setDescribesion(" 费用标准对比");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
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
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }
}