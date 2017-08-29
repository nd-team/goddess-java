package com.bjike.goddess.attainment.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.attainment.bo.SurveyDemandBO;
import com.bjike.goddess.attainment.dto.SurveyDemandDTO;
import com.bjike.goddess.attainment.entity.SurveyDemand;
import com.bjike.goddess.attainment.enums.GuideAddrStatus;
import com.bjike.goddess.attainment.enums.ScopeType;
import com.bjike.goddess.attainment.enums.SurveyStatus;
import com.bjike.goddess.attainment.to.CloseDemandTO;
import com.bjike.goddess.attainment.to.GuidePermissionTO;
import com.bjike.goddess.attainment.to.SurveyDemandTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 调研需求业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:28 ]
 * @Description: [ 调研需求业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SurveyDemandSerImpl extends ServiceImpl<SurveyDemand, SurveyDemandDTO> implements SurveyDemandSer {

    @Autowired
    private DemandTypeSer demandTypeSer;
    @Autowired
    private AttainmentTypeSer attainmentTypeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private SurveyPlanSer surveyPlanSer;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    private SurveyDemandBO transformBO(SurveyDemand entity) throws SerException {
        SurveyDemandBO bo = BeanTransform.copyProperties(entity, SurveyDemandBO.class);
        bo.setDemandId(entity.getDemand().getId());
        bo.setDemandName(entity.getDemand().getType());
        bo.setTypeId(entity.getType().getId());
        bo.setTypeName(entity.getType().getType());
        return bo;
    }

    private List<SurveyDemandBO> transformBOList(List<SurveyDemand> list) throws SerException {
        List<SurveyDemandBO> bos = new ArrayList<>(list.size());
        for (SurveyDemand entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyDemandBO save(SurveyDemandTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SurveyDemand entity = BeanTransform.copyProperties(to, SurveyDemand.class, true);
        entity.setDemand(demandTypeSer.findById(to.getDemandId()));
        if (null == entity.getDemand())
            throw new SerException("调研需求类型不存在,无法保存");
        entity.setType(attainmentTypeSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("调研类型不存在,无法保存");
        entity.setUsername(user.getUsername());
        entity.setLaunch(LocalDateTime.now());
        entity.setScope(to.getScope());

//        String scope = "";
//        if (entity.getScope().equals(ScopeType.COMPANY))
//            scope = "公司";
//        else
        String scopeName = "";
        for (String name : to.getScopeNames())
            scopeName += name + ",";
        entity.setScopeName(scopeName);

        if (moduleAPI.isCheck("organize")) {
            List<String> list = positionDetailUserAPI.getPosition(user.getUsername());
            if (!CollectionUtils.isEmpty(list)) {
                entity.setGradation(list.get(0));
            }
        }
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyDemandBO update(SurveyDemandTO to) throws SerException {
        SurveyDemand entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setDemand(demandTypeSer.findById(to.getDemandId()));
        if (null == entity.getDemand())
            throw new SerException("调研需求类型不存在,无法保存");
        entity.setType(attainmentTypeSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("调研类型不存在,无法保存");
        entity.setModifyTime(LocalDateTime.now());
        String scope = "";
        if (entity.getScope().equals(ScopeType.COMPANY))
            scope = "公司";
        else
            for (String name : to.getScopeNames())
                scope += name + ",";
        entity.setScopeName(scope);
        if (moduleAPI.isCheck("organize")) {
            List<String> list = positionDetailUserAPI.getPosition(entity.getUsername());
            if (!CollectionUtils.isEmpty(list)) {
                entity.setGradation(list.get(0));
            }
        }
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyDemandBO delete(String id) throws SerException {
        SurveyDemand entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        if (surveyPlanSer.findByDemand(entity.getId()).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SurveyDemandBO close(CloseDemandTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SurveyDemand entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据不存在");
        entity.setCloseReason(to.getCloseReason());
        entity.setHandle(user.getUsername());
        return this.transformBO(entity);
    }

    @Override
    public List<SurveyDemandBO> findByStatus(SurveyStatus status) throws SerException {
        if (null == status)
            return new ArrayList<>(0);
        SurveyDemandDTO dto = new SurveyDemandDTO();
        dto.getConditions().add(Restrict.eq("surveyStatus", status.getValue()));
        List<SurveyDemand> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<SurveyDemandBO> maps(SurveyDemandDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public SurveyDemandBO getById(String id) throws SerException {
        SurveyDemand entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return this.transformBO(entity);
    }

    @Override
    public Long getTotal() throws SerException {
        SurveyDemandDTO dto = new SurveyDemandDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getDemandId() throws SerException {
        List<SurveyDemand> list = super.findAll();
        List<String> stringList = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (SurveyDemand module : list) {
                String str = module.getId();
                stringList.add(str);
            }
        }
        return stringList;
    }

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
                throw new SerException("您不是相应部门的人员，不可以查看");
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