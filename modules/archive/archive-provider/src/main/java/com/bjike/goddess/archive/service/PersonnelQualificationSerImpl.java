package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.PersonnelQualificationBO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.entity.PersonnelQualification;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryBasicInfoAPI;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import com.bjike.goddess.staffentry.dto.EntryBasicInfoDTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import com.bjike.goddess.user.enums.SexType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 人员资质业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class PersonnelQualificationSerImpl extends ServiceImpl<PersonnelQualification, PersonnelQualificationDTO> implements PersonnelQualificationSer {

    @Autowired
    private LaborRelationSer laborRelationSer;
    @Autowired
    private SocialSecurityTypeSer socialSecurityTypeSer;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private UserDetailAPI userDetailAPI;
    @Autowired
    private EntryBasicInfoAPI entryBasicInfoAPI;
    @Autowired
    private RotainCusPermissionSer cusPermissionSer;

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
            flag = cusPermissionSer.getRotainCusPermission("1");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.busRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("1");
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

    private PersonnelQualificationBO transformBO(PersonnelQualification entity) throws SerException {
        PersonnelQualificationBO bo = BeanTransform.copyProperties(entity, PersonnelQualificationBO.class);
        UserBO user = userAPI.findByUsername(entity.getUsername());
        if (user != null) {

            UserDetailBO detailBO = userDetailAPI.findByUserId(user.getId());
            if (null != detailBO) {
                bo.setSex(detailBO.getSex() == SexType.MAN ? "男" : "女");
                bo.setIdentityCard(detailBO.getIdCard());
            }
        }
        bo.setSocialId(entity.getSocial().getId());
        bo.setSocialName(entity.getSocial().getName());
        bo.setLoborId(entity.getLabor().getId());
        bo.setLaborName(entity.getLabor().getName());
        return bo;
    }

    private List<PersonnelQualificationBO> transformBOList(List<PersonnelQualification> list) throws SerException {
        List<PersonnelQualificationBO> bos = new ArrayList<>(list.size());
        for (PersonnelQualification entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelQualificationBO save(PersonnelQualificationTO to) throws SerException {
        PersonnelQualification entity = BeanTransform.copyProperties(to, PersonnelQualification.class);
        entity.setLabor(laborRelationSer.findById(to.getLaborId()));
        entity.setSocial(socialSecurityTypeSer.findById(to.getSocialId()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PersonnelQualificationBO update(PersonnelQualificationTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                PersonnelQualification entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                entity.setLabor(laborRelationSer.findById(to.getLaborId()));
                entity.setSocial(socialSecurityTypeSer.findById(to.getSocialId()));
                super.update(entity);
                return this.transformBO(entity);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public PersonnelQualificationBO delete(String id) throws SerException {
        PersonnelQualification entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<PersonnelQualificationBO> maps(PersonnelQualificationDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public PersonnelQualificationBO getById(String id) throws SerException {
        PersonnelQualification entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        PersonnelQualificationBO personnelQualificationBO = BeanTransform.copyProperties(entity, PersonnelQualificationBO.class);
        personnelQualificationBO.setLaborName(entity.getLabor().getName());
        personnelQualificationBO.setLoborId(entity.getLabor().getId());
        personnelQualificationBO.setSocialName(entity.getSocial().getName());
        personnelQualificationBO.setSocialId(entity.getSocial().getId());
        return personnelQualificationBO;
    }

    @Override
    public Long getTotal() throws SerException {
        PersonnelQualificationDTO dto = new PersonnelQualificationDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getName() throws SerException {
        EntryBasicInfoDTO dto = new EntryBasicInfoDTO();
        List<EntryBasicInfoBO> entryBasicInfoBOs = entryBasicInfoAPI.listEntryBasicInfo(dto);
        List<String> list = new ArrayList<>();
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            for (EntryBasicInfoBO bo : entryBasicInfoBOs) {
                String name = "";
                name = bo.getName();
                list.add(name);
            }
        }
        return list;
    }
}