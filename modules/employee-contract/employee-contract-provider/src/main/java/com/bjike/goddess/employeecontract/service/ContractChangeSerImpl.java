package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractChangeBO;
import com.bjike.goddess.employeecontract.bo.ContractManageBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.entity.ContractChange;
import com.bjike.goddess.employeecontract.enums.GuideAddrStatus;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 合同变更详细业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractChangeSerImpl extends ServiceImpl<ContractChange, ContractChangeDTO> implements ContractChangeSer {

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
    public ContractChangeBO transformBO(ContractChange entity) throws SerException {
        ContractChangeBO bo = BeanTransform.copyProperties(entity, ContractChangeBO.class);
        bo.setContractId(entity.getContract().getId());
        ContractManageBO manageBO = contractManageSer.getById(bo.getContractId());
        bo.setUsername(manageBO.getUsername());
        bo.setSerialNumber(manageBO.getSerialNumber());
        bo.setType(manageBO.getTypeName());
        bo.setNature(manageBO.getNatureName());
        bo.setEmployeeUnit(manageBO.getEmployeeUnit());
        return bo;
    }

    private List<ContractChangeBO> transformBOList(List<ContractChange> list) throws SerException {
        List<ContractChangeBO> bos = new ArrayList<>(list.size());
        for (ContractChange entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }


    @Override
    public ContractChangeBO update(ContractChangeTO to) throws SerException {
        checkDepPermission();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractChange entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return this.transformBO(entity);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractChangeBO delete(String id) throws SerException {
        checkDepPermission();
        ContractChange entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<ContractChangeBO> maps(ContractChangeDTO dto) throws SerException {
        checkDepPermission();
        dto.getSorts().add("change=desc");
        return this.transformBOList(super.findByPage(dto));
    }

    @Override
    public Long getTotal() throws SerException {
        ContractChangeDTO dto = new ContractChangeDTO();
        return super.count(dto);
    }

    @Override
    public ContractChangeBO getById(String id) throws SerException {
        ContractChange entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }
}