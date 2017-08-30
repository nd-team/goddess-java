package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.ProjectContractBO;
import com.bjike.goddess.contractware.dto.ProjectContractDTO;
import com.bjike.goddess.contractware.entity.ProjectContract;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.excel.SonPermissionObject;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.ProjectContractTO;
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
 * 项目合同业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目合同业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class ProjectContractSerImpl extends ServiceImpl<ProjectContract, ProjectContractDTO> implements ProjectContractSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private HousingContractSer housingContractSer;
    @Autowired
    private CarRentalAgreementSer carRentalAgreementSer;

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

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeInfo = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddInfo = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("projectcontract");
        obj.setDescribesion("项目合同");
        if (flagSeeInfo || flagAddInfo) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        Boolean flagSeeWeb = housingContractSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("housingcontract");
        obj.setDescribesion("房屋合同");
        if (flagSeeWeb) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        Boolean flagSeeOpen = carRentalAgreementSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("carrentalagreement");
        obj.setDescribesion("租车协议");
        if (flagSeeOpen) {
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


    @Override
    public Long countProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        Long count = super.count(projectContractDTO);
        return count;
    }

    @Override
    public ProjectContractBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        ProjectContract projectContract = super.findById(id);
        return BeanTransform.copyProperties(projectContract, ProjectContractBO.class);
    }

    @Override
    public List<ProjectContractBO> findListProjectContract(ProjectContractDTO projectContractDTO) throws SerException {
        checkSeeIdentity();
        projectContractDTO.getSorts().add("createTime=desc");
        List<ProjectContract> projectContracts = super.findByPage(projectContractDTO);
        List<ProjectContractBO> projectContractBOS = BeanTransform.copyProperties(projectContracts, ProjectContractBO.class);
        return projectContractBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectContractBO insertProjectContract(ProjectContractTO projectContractTO) throws SerException {
        checkAddIdentity();
        ProjectContract projectContract = BeanTransform.copyProperties(projectContractTO, ProjectContract.class, true);
        projectContract.setCreateTime(LocalDateTime.now());
        super.save(projectContract);
        return BeanTransform.copyProperties(projectContract, ProjectContractBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public ProjectContractBO editProjectContract(ProjectContractTO projectContractTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(projectContractTO.getId())) {
            throw new SerException("id不能为空");
        }
        ProjectContract projectContract = super.findById(projectContractTO.getId());
        BeanTransform.copyProperties(projectContractTO, projectContract, true);
        projectContract.setModifyTime(LocalDateTime.now());
        super.update(projectContract);
        return BeanTransform.copyProperties(projectContract, ProjectContractBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeProjectContract(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}