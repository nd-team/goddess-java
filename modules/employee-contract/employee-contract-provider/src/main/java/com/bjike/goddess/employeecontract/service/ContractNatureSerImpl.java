package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractNatureBO;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.entity.ContractNature;
import com.bjike.goddess.employeecontract.enums.GuideAddrStatus;
import com.bjike.goddess.employeecontract.to.ContractNatureTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 合同性质业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 01:58 ]
 * @Description: [ 合同性质业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractNatureSerImpl extends ServiceImpl<ContractNature, ContractNatureDTO> implements ContractNatureSer {

    @Autowired
    private ContractManageSer contractManageSer;

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
     * 检查审核权限(岗位)
     *
     * @throws SerException
     */
    private void checkPosintPermission() throws SerException {
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
            throw new SerException("您不是总经办人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }
    /**
     * 综合资源部权限(部门)
     *
     * @throws SerException
     */
    private void checkDepPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是综合资源部人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 部门
     *
     * @throws SerException
     */
    private Boolean checkDep() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.busCusPermission("1");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 总经办
     *
     * @throws SerException
     */
    private Boolean checkPosin() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("2");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 福利模块负责人
     *
     * @throws SerException
     */
    private Boolean checkModulePosin() throws SerException {
        Boolean flag = true;
        String userToken = RpcTransmit.getUserToken();
        flag = cusPermissionSer.positCusPermission("3");
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }

    /**
     * 劳动合同查看权限(总经办,部门,福利模块负责人)
     *
     * @throws SerException
     */
    private Boolean checkSeePermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean posinFlag = checkPosin();
        Boolean depFlag = checkDep();
        Boolean modPosinFlag = checkModulePosin();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || depFlag || modPosinFlag) {
                flag = true;
            }
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }


    /**
     * 核对权限（部门级别）
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
     * 核对总经办权限（岗位级别）
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
     * 核对综合资源部权限（部门级别）
     */
    private Boolean guideDepIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("4");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 权限
     */
    private Boolean guideAllTrue() throws SerException {
        return true;
    }

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideDepIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee) {
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
            case LDLIST:
                flag = guideAllTrue();
                break;
            case LDEDIT:
                flag = guideIdentity() || guidePosinIdentity();
                break;
            case LDCHANG:
                flag = guideDepIdentity();
                break;
            case LDREMOVE:
                flag = guideDepIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEE:
                flag = guideAllTrue();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public ContractNatureBO save(ContractNatureTO to) throws SerException {
        checkPermission();
        ContractNature entity = BeanTransform.copyProperties(to, ContractNature.class);
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO update(ContractNatureTO to) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractNature entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, ContractNatureBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractNatureBO delete(String id) throws SerException {
        checkPermission();
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        ContractManageDTO dto = new ContractManageDTO();
        dto.getConditions().add(Restrict.eq("nature.id",entity.getId()));
       if(contractManageSer.findByCis(dto).size()!=0)
           throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO congeal(String id) throws SerException {
        checkPermission();
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO thaw(String id) throws SerException {
        checkPermission();
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }

    @Override
    public List<ContractNatureBO> findThaw() throws SerException {
        ContractNatureDTO dto = new ContractNatureDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<ContractNature> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ContractNatureBO.class);
    }

    @Override
    public List<ContractNatureBO> maps(ContractNatureDTO dto) throws SerException {
        checkPermission();
        dto.getSorts().add("status=asc");
        return BeanTransform.copyProperties(super.findByPage(dto), ContractNatureBO.class);
    }

    @Override
    public ContractNatureBO getById(String id) throws SerException {
        ContractNature entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        return BeanTransform.copyProperties(entity, ContractNatureBO.class);
    }
}