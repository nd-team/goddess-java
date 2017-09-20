package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;
import com.bjike.goddess.archive.enums.GuideAddrStatus;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对外人员信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "archiveSerCache")
@Service
public class ForeignStaffingSerImpl extends ServiceImpl<ForeignStaffing, ForeignStaffingDTO> implements ForeignStaffingSer {

    @Autowired
    private ForeignStaffingSetSer foreignStaffingSetSer;

    @Autowired
    private UserAPI userAPI;
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

    private ForeignStaffingBO transformBO(ForeignStaffing entity) throws SerException {
        ForeignStaffingBO bo = BeanTransform.copyProperties(entity, ForeignStaffingBO.class);
        bo.setTypeId(entity.getType().getId());
        bo.setTypeName(entity.getType().getName());
        return bo;
    }

    private List<ForeignStaffingBO> transformBOList(List<ForeignStaffing> list) throws SerException {
        List<ForeignStaffingBO> bos = new ArrayList<>(list.size());
        for (ForeignStaffing entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingBO save(ForeignStaffingTO to) throws SerException {
        ForeignStaffing entity = BeanTransform.copyProperties(to, ForeignStaffing.class, true);
        entity.setType(foreignStaffingSetSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("使用类型不能为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        ForeignStaffing entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        entity.setType(foreignStaffingSetSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("使用类型不能为空");
        entity.setIdentityCard(to.getIdentityCard());
        entity.setBankCard(to.getBankCard());
        entity.setAddress(to.getAddress());
        entity.setBank(to.getBank());
        entity.setEmail(to.getEmail());
        super.update(entity);
        return this.transformBO(entity);
    }

    @Override
    public ForeignStaffingBO delete(String id) throws SerException {
        ForeignStaffing entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public ForeignStaffingBO getById(String id) throws SerException {
        ForeignStaffing entity = super.findById(id);
        if (null == entity)
            throw new SerException("该数据不存在");

        ForeignStaffingBO foreignStaffingBO = BeanTransform.copyProperties(entity, ForeignStaffingBO.class);
        foreignStaffingBO.setTypeName(entity.getType().getName());
        return foreignStaffingBO;
    }

    @Override
    public Long getTotal() throws SerException {
        ForeignStaffingDTO dto = new ForeignStaffingDTO();
        return super.count(dto);
    }

    @Override
    public List<String> getTime() throws SerException {
        List<EntryRegister> entryRegisters = entryRegisterAPI.list();
        List<String> list = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(entryRegisters)) {
            List<LocalDate> localDateList = entryRegisters.stream().map(EntryRegister::getGraduationDate).distinct().collect(Collectors.toList());
            for (LocalDate localDate : localDateList) {
                list.add(localDate.toString());
            }
        }
        return list;
    }

    @Override
    public List<String> getSchool() throws SerException {
        List<EntryRegister> entryRegisters = entryRegisterAPI.list();
        if (!CollectionUtils.isEmpty(entryRegisters)) {
            List<String> list = entryRegisters.stream().map(EntryRegister::getSchoolTag).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }

    @Override
    public List<String> getQQ() throws SerException {
        List<EntryRegister> entryRegisters = entryRegisterAPI.list();
        if (!CollectionUtils.isEmpty(entryRegisters)) {
            List<String> list = entryRegisters.stream().map(EntryRegister::getQq).distinct().collect(Collectors.toList());
            return list;
        }
        return null;
    }
}