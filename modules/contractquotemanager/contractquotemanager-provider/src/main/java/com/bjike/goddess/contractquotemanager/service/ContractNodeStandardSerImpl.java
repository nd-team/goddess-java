package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ColationBO;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.to.FilterTO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.to.GuidePermissionTO;
import com.bjike.goddess.contractquotemanager.type.GuideAddrStatus;
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
import java.util.stream.Collectors;

/**
 * 合同节点标准信息业务实现
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.725 ]
 * @Description: [ 合同节点标准信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractNodeStandardSerCache")
@Service
public class ContractNodeStandardSerImpl extends ServiceImpl<ContractNodeStandard, ContractNodeStandardDTO> implements ContractNodeStandardSer {

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private UserAPI userAPI;


    /**
     * 检查权限
     *
     * @throws SerException
     */
    private void checkPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        //财务模块权限
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是财务人员,没有该操作权限");
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

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
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
            case CONGEL:
                flag = guideIdentity();
                break;
            case THAW:
                flag = guideIdentity();
                break;
            case COLLECT:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case IMPORT:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    /**
     * 分页查询合同节点标准信息
     *
     * @param dto 合同节点标准信息dto
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public List<ContractNodeStandardBO> list(ContractNodeStandardDTO dto) throws SerException {
        checkPermission();
        condiy(dto);
        List<ContractNodeStandard> list = super.findByPage(dto);
        List<ContractNodeStandardBO> boList = BeanTransform.copyProperties(list, ContractNodeStandardBO.class);
        return boList;
    }


    @Override
    public void condiy(ContractNodeStandardDTO dto) throws SerException {
        if(StringUtils.isNotBlank(dto.getArea())){
            dto.getConditions().add(Restrict.eq("area",dto.getArea()));
        }
        if(StringUtils.isNotBlank(dto.getProject())){
            dto.getConditions().add(Restrict.eq("project",dto.getProject()));
        }
    }

    /**
     * 保存合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @return class ContractNodeStandardBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public ContractNodeStandardBO save(ContractNodeStandardTO to) throws SerException {
        checkPermission();
        ContractNodeStandard entity = BeanTransform.copyProperties(to, ContractNodeStandard.class, true);
        entity = super.save(entity);
        ContractNodeStandardBO bo = BeanTransform.copyProperties(entity, ContractNodeStandardBO.class);
        return bo;
    }

    /**
     * 根据id删除合同节点标准信息
     *
     * @param id 合同节点标准信息唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        checkPermission();
        super.remove(id);
    }

    /**
     * 更新合同节点标准信息
     *
     * @param to 合同节点标准信息to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(ContractNodeStandardTO to) throws SerException {
        checkPermission();
        if (StringUtils.isNotEmpty(to.getId())) {
            ContractNodeStandard model = super.findById(to.getId());
            if (model != null) {
                updateContractNodeStandard(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新合同节点标准信息
     *
     * @param to    合同节点标准信息to
     * @param model 合同节点标准信息
     * @throws SerException
     */
    private void updateContractNodeStandard(ContractNodeStandardTO to, ContractNodeStandard model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    @Override
    public List<ContractNodeStandardBO> collect(ContractNodeStandardTO to) throws SerException {
        checkPermission();
        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();

        if (to.getDate() != null && !to.getDate().equals("")) {
            dto.getConditions().add(Restrict.eq("date", to.getDate()));
        }
        if (to.getArea() != null && !to.getArea().equals("")) {
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (to.getProject() != null && !to.getProject().equals("")) {
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        }
        if (to.getNode() != null && !to.getNode().equals("")) {
            dto.getConditions().add(Restrict.eq("node", to.getNode()));
        }
        return BeanTransform.copyProperties(super.findByCis(dto), ContractNodeStandardBO.class);
    }

    @Override
    public List<ContractNodeStandardBO> searchContractNodeStandard(ContractNodeStandardTO to) throws SerException {

        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();

        if (to.getArea() != null && !to.getArea().equals("")) {
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (to.getProject() != null && !to.getProject().equals("")) {
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        }
        return BeanTransform.copyProperties(super.findByCis(dto), ContractNodeStandardBO.class);
    }

    @Override
    public List<ColationBO> findType() throws SerException {
        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();
        dto.getSorts().add("type=desc");
        List<ContractNodeStandard> list = super.findByCis(dto);
        List<ColationBO> bos = new ArrayList<>(0);
        String type = "";
        for (ContractNodeStandard entity : list) {
            if (!type.equals(entity.getType())) {
                type = entity.getType();
                bos.add(new ColationBO(entity.getId(), type));
            }
        }
        return bos;
    }

    @Override
    public List<ColationBO> findNode() throws SerException {
        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();
        dto.getSorts().add("node=desc");
        List<ContractNodeStandard> list = super.findByCis(dto);
        List<ColationBO> bos = new ArrayList<>(0);
        String node = "";
        for (ContractNodeStandard entity : list) {
            if (!node.equals(entity.getNode())) {
                node = entity.getNode();
                bos.add(new ColationBO(entity.getId(), node));
            }
        }
        return bos;
    }

    @Override
    public List<ContractNodeStandardBO> findByTo(FilterTO to) throws SerException {
        ContractNodeStandardDTO dto = new ContractNodeStandardDTO();
        if (StringUtils.isNotBlank(to.getType()))
            dto.getConditions().add(Restrict.eq("type", to.getType()));
        if (StringUtils.isNotBlank(to.getArea()))
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        if (StringUtils.isNotBlank(to.getNode()))
            dto.getConditions().add(Restrict.eq("node", to.getNode()));
        List<ContractNodeStandard> list = super.findByCis(dto);
        if (null != to.getYear() && null != to.getMonth() && list != null) {
            list = list.stream()
                    .filter(c -> c.getDate().getYear() == to.getYear() && c.getDate().getMonthValue() == to.getMonth())
                    .collect(Collectors.toList());
        }
        if (null == list || list.size() == 0){
            return new ArrayList<>(0);
        }
        return BeanTransform.copyProperties(list, ContractNodeStandardBO.class);
    }
}