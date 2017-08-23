package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.bo.ContractChangeBO;
import com.bjike.goddess.employeecontract.bo.ContractInfoBO;
import com.bjike.goddess.employeecontract.bo.ContractManageBO;
import com.bjike.goddess.employeecontract.bo.ContractPersonalBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.dto.ContractManageDTO;
import com.bjike.goddess.employeecontract.entity.ContractChange;
import com.bjike.goddess.employeecontract.entity.ContractManage;
import com.bjike.goddess.employeecontract.enums.GuideAddrStatus;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.to.*;
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
 * 合同管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:04 ]
 * @Description: [ 合同管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "employeecontractSerCache")
@Service
public class ContractManageSerImpl extends ServiceImpl<ContractManage, ContractManageDTO> implements ContractManageSer {

    @Autowired
    private ContractNatureSer contractNatureSer;
    @Autowired
    private ContractTypeSer contractTypeSer;
    @Autowired
    private ContractChangeSer contractChangeSer;
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
     * 劳动合同编辑权限(总经办,部门)
     *
     * @throws SerException
     */
    private Boolean checkEditPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        Boolean posinFlag = checkPosin();
        Boolean depFlag = checkDep();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            if (posinFlag || depFlag ) {
                flag = true;
            }
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        {
            List<SonPermissionObject> list = new ArrayList<>();
            String userToken = RpcTransmit.getUserToken();
            Boolean flagSee = guideIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagPosin = guidePosinIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagDep = guideDepIdentity();
            RpcTransmit.transmitUserToken(userToken);
            Boolean flagAllTrue = guideAllTrue();
            RpcTransmit.transmitUserToken(userToken);

            SonPermissionObject obj = new SonPermissionObject();

            obj = new SonPermissionObject();
            obj.setName("contractmanage");
            obj.setDescribesion("人员合同管理");
            if (flagSee || flagPosin || flagDep || flagAllTrue) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("contractmanagedetail");
            obj.setDescribesion("员工合同信息详细");
            if (flagSee || flagPosin || flagDep || flagAllTrue) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagChang = contractChangeSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("contractchange");
            obj.setDescribesion("人员合同变更");
            if (flagChang) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            RpcTransmit.transmitUserToken(userToken);
            Boolean flagNature = contractNatureSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("contractnature");
            obj.setDescribesion("合同性质");
            if (flagNature) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);

            RpcTransmit.transmitUserToken(userToken);
            Boolean flagType = contractTypeSer.sonPermission();
            RpcTransmit.transmitUserToken(userToken);
            obj = new SonPermissionObject();
            obj.setName("contracttype");
            obj.setDescribesion("合同类型");
            if (flagType) {
                obj.setFlag(true);
            } else {
                obj.setFlag(false);
            }
            list.add(obj);


            return list;
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

    private ContractManageBO transformBO(ContractManage entity) throws SerException {
        ContractManageBO bo = BeanTransform.copyProperties(entity, ContractManageBO.class);
        bo.setTypeId(entity.getType().getId());
        bo.setTypeName(entity.getType().getType());
        bo.setNatureId(entity.getNature().getId());
        bo.setNatureName(entity.getNature().getNature());
        return bo;
    }

    private List<ContractManageBO> transformBOList(List<ContractManage> list) throws SerException {
        List<ContractManageBO> bos = new ArrayList<>(list.size());
        for (ContractManage entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    /**
     * 查看权限检测
     *
     * @param dto 合同管理数据传输对象
     * @throws SerException
     */
    private void checkAuthority(ContractManageDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("username", userAPI.currentUser().getUsername()));
    }

    @Override
    public ContractManageBO save(ContractManageTO to) throws SerException {
        checkPermission();
        ContractManage entity = BeanTransform.copyProperties(to, ContractManage.class, true);
        entity.setNature(contractNatureSer.findById(to.getNatureId()));
        if (null == entity.getNature())
            throw new SerException("合同资质不能为空");
        entity.setType(contractTypeSer.findById(to.getTypeId()));
        if (null == entity.getType())
            throw new SerException("合同类型不能为空");
        super.save(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContractManageBO updateDetail(ContractManageTO to) throws SerException {
        checkEditPermission();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setNature(contractNatureSer.findById(to.getNatureId()));
            if (null == entity.getNature())
                throw new SerException("合同资质不能为空");
            entity.setType(contractTypeSer.findById(to.getTypeId()));
            if (null == entity.getType())
                throw new SerException("合同类型不能为空");
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return this.transformBO(entity);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractInfoBO updateInfo(ContractInfoTO to) throws SerException {
        checkPermission();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            entity.setNature(contractNatureSer.findById(to.getNatureId()));
            if (null == entity.getNature())
                throw new SerException("合同资质不能为空");
            entity.setType(contractTypeSer.findById(to.getTypeId()));
            if (null == entity.getType())
                throw new SerException("合同类型不能为空");
            super.update(entity);
            ContractManageBO bo = this.transformBO(entity);
            return BeanTransform.copyProperties(bo, ContractInfoBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractPersonalBO updatePersonal(ContractPersonalTO to) throws SerException {
        checkEditPermission();
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            ContractManage entity = super.findById(to.getId());
            if (entity == null)
                throw new SerException();
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            ContractManageBO bo = this.transformBO(entity);
            return BeanTransform.copyProperties(bo, ContractPersonalBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public ContractInfoBO affirm(String id) throws SerException {
        checkDepPermission();
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        entity.setStatus(false);
        super.update(entity);
        ContractManageBO manageBO = this.transformBO(entity);
        return BeanTransform.copyProperties(manageBO, ContractInfoBO.class);
    }

    @Override
    public ContractManageBO delete(String id) throws SerException {
        checkPermission();
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        ContractChangeDTO dto = new ContractChangeDTO();
        dto.getConditions().add(Restrict.eq("contract.id", entity.getId()));
        if (contractChangeSer.findByCis(dto).size() != 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public ContractManageBO getById(String id) throws SerException {
        ContractManage entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不存在");
        return this.transformBO(entity);
    }

    @Override
    public List<ContractPersonalBO> personalMaps(ContractManageDTO dto) throws SerException {
        Boolean flag = checkSeePermission();
        if(!flag){
            this.checkAuthority(dto);
        }else{
            dto.getSorts().add("status=asc");
            dto.getSorts().add("username=desc");
        }
        List<ContractManage> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, ContractPersonalBO.class);
    }

    @Override
    public List<ContractInfoBO> infoMaps(ContractManageDTO dto) throws SerException {
        Boolean flag = checkSeePermission();
        if(!flag){
            this.checkAuthority(dto);
        }else{
            dto.getSorts().add("status=asc");
            dto.getSorts().add("serialNumber=desc");
        }
        List<ContractManageBO> list = this.transformBOList(super.findByPage(dto));
        return BeanTransform.copyProperties(list, ContractInfoBO.class);
    }

    @Override
    public Long getPersonalTotal() throws SerException {
        ContractManageDTO dto = new ContractManageDTO();
        Boolean flag = checkSeePermission();
        if(!flag){
            this.checkAuthority(dto);
        }
        return super.count(dto);
    }

    @Override
    public Long getInfoTotal() throws SerException {
        ContractManageDTO dto = new ContractManageDTO();
        Boolean flag = checkSeePermission();
        if(!flag){
            this.checkAuthority(dto);
        }
        return super.count(dto);
    }

    @Override
    public List<ContractManageBO> findStatus() throws SerException {
        ContractManageDTO dto = new ContractManageDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Boolean.TRUE));
        List<ContractManage> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public ContractChangeBO saveChange(ContractChangeTO to) throws SerException {
        checkDepPermission();
        ContractChange entity = BeanTransform.copyProperties(to, ContractChange.class, true);
        entity.setContract(super.findById(to.getId()));
        if (null == entity.getContract())
            throw new SerException("合同信息数据对象不存在");
        entity.setId(null);
        contractChangeSer.save(entity);
        return contractChangeSer.transformBO(entity);
    }
}