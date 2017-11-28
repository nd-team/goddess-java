package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.PersonnelQuaDataBO;
import com.bjike.goddess.archive.bo.PersonnelQualificationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.entity.LaborRelation;
import com.bjike.goddess.archive.entity.PersonnelQualification;
import com.bjike.goddess.archive.entity.SocialSecurityType;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.excel.PersonnelQualificationExportExcel;
import com.bjike.goddess.archive.excel.PersonnelQualificationImportExcel;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.PersonnelQualificationTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.EntryRegisterBO;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
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
    private RotainCusPermissionSer cusPermissionSer;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
            flag = cusPermissionSer.getRotainCusPermission("2");
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
        dto = findData(dto);
        searchCondition(dto);
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
    public Long getTotal(PersonnelQualificationDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        return super.count(dto);
    }

    @Override
    public List<String> getName() throws SerException {
        EntryRegisterDTO dto = new EntryRegisterDTO();
        List<EntryRegisterBO> entryBasicInfoBOs = entryRegisterAPI.listEntryRegister(dto);
        List<String> list = new ArrayList<>();
        if (null != entryBasicInfoBOs && entryBasicInfoBOs.size() > 0) {
            for (EntryRegisterBO bo : entryBasicInfoBOs) {
                String name = "";
                name = bo.getUsername();
                list.add(name);
            }
        }
        return list;
    }

    @Override
    public PersonnelQuaDataBO findByName(String name) throws SerException {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        UserBO user = userAPI.findByUsername(name);
        if (user != null) {
            UserDetailBO detailBO = userDetailAPI.findByUserId(user.getId());
            if (null != detailBO) {
                PersonnelQuaDataBO bo = new PersonnelQuaDataBO();
                bo.setSex(detailBO.getSex() == SexType.MAN ? "男" : "女");
                bo.setIdentityCard(detailBO.getIdCard());
                return bo;
            }
        }
        return null;
    }

    @Override
    public byte[] exportExcel(PersonnelQualificationDTO dto) throws SerException {
        dto = findData(dto);
        searchCondition(dto);
        List<PersonnelQualification> personnelQualifications = super.findByCis(dto);
        List<PersonnelQualificationBO> bos = BeanTransform.copyProperties(personnelQualifications, PersonnelQualificationBO.class, false);
        List<PersonnelQualificationExportExcel> personnelQualificationExportExcels = BeanTransform.copyProperties(bos, PersonnelQualificationExportExcel.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(personnelQualificationExportExcels, excel);
        return bytes;
    }

    @Override
    public byte[] templateExcel() throws SerException {
        PersonnelQualificationImportExcel personnelQualificationImportExcel = new PersonnelQualificationImportExcel();
        List<PersonnelQualificationImportExcel> personnelQualificationImportExcels = new ArrayList<>(0);
        personnelQualificationImportExcels.add(personnelQualificationImportExcel);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(personnelQualificationImportExcels, excel);
        return bytes;
    }

    @Override
    public void upload(List<PersonnelQualificationImportExcel> tos) throws SerException {
        if (null != tos && tos.size() > 0) {
            for (PersonnelQualificationImportExcel personnelQualificationImportExcel : tos) {

                LaborRelationDTO laborRelationDTO = new LaborRelationDTO();
                laborRelationDTO.getConditions().add(Restrict.eq("name", personnelQualificationImportExcel.getLaborName()));
                List<LaborRelation> laborRelations = laborRelationSer.findByCis(laborRelationDTO);
                if (null == laborRelations || laborRelations.size() < 1) {
                    throw new SerException("劳动关系类型");
                }

                SocialSecurityTypeDTO socialSecurityTypeDTO = new SocialSecurityTypeDTO();
                socialSecurityTypeDTO.getConditions().add(Restrict.eq("name", personnelQualificationImportExcel.getSocialName()));
                List<SocialSecurityType> socialSecurityTypes = socialSecurityTypeSer.findByCis(socialSecurityTypeDTO);
                if (null == socialSecurityTypes || socialSecurityTypes.size() < 1) {
                    throw new SerException("公司社保购买类型");
                }

                PersonnelQualificationTO to = BeanTransform.copyProperties(personnelQualificationImportExcel, PersonnelQualificationTO.class, true);
                to.setLaborId(laborRelations.get(0).getId());
                to.setSocialId(socialSecurityTypes.get(0).getId());
                this.save(to);
            }
        }
    }

    private void searchCondition(PersonnelQualificationDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getUsername())) {
            dto.getConditions().add(Restrict.eq("username", dto.getUsername()));
        }
    }

    /**
     * 是否有权限查看所有人的信息(岗位级别)
     */
    private Boolean guideSeePositionIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.guideSeePositionIdentity();
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据岗位查看所有信息或个人信息
     */
    private PersonnelQualificationDTO findData(PersonnelQualificationDTO dto) throws SerException {
        if (!guideSeePositionIdentity()) {
            dto = new PersonnelQualificationDTO();
            String userToken = RpcTransmit.getUserToken();
            UserBO userBO = userAPI.currentUser();
            RpcTransmit.transmitUserToken(userToken);
            dto.getConditions().add(Restrict.eq("username", userBO.getUsername()));
        }
        return dto;
    }
}