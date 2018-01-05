package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.HousingContractBO;
import com.bjike.goddess.contractware.dto.HousingContractDTO;
import com.bjike.goddess.contractware.entity.HousingContract;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
import com.bjike.goddess.contractware.to.HousingContractTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 房屋合同业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:42 ]
 * @Description: [ 房屋合同业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class HousingContractSerImpl extends ServiceImpl<HousingContract, HousingContractDTO> implements HousingContractSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private CusPermissionSer cusPermissionSer;

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
                throw new SerException("您不是相应部门的人员，不可以操作");
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
     * 核对查看权限（部门级别）
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
     * 核对添加修改删除审核权限（岗位级别）
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

    @Override
    public Long countHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        Long count = super.count(housingContractDTO);
        return count;
    }

    @Override
    public HousingContractBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        HousingContract housingContract = super.findById(id);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }

    @Override
    public List<HousingContractBO> findListHousingContract(HousingContractDTO housingContractDTO) throws SerException {
        checkSeeIdentity();
        housingContractDTO.getSorts().add("createTime=desc");
        List<HousingContract> housingContracts = super.findByPage(housingContractDTO);
        List<HousingContractBO> housingContractBOS = BeanTransform.copyProperties(housingContracts, HousingContractBO.class);
        return housingContractBOS;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public HousingContractBO insertHousingContract(HousingContractTO housingContractTO) throws SerException {
        checkAddIdentity();
        HousingContract housingContract = BeanTransform.copyProperties(housingContractTO, HousingContract.class, true);
        housingContract.setCreateTime(LocalDateTime.now());
        super.save(housingContract);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public HousingContractBO editHousingContract(HousingContractTO housingContractTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(housingContractTO.getId())) {
            throw new SerException("id不能为空");
        }
        HousingContract housingContract = super.findById(housingContractTO.getId());
        LocalDateTime createTime = housingContract.getCreateTime ();
        housingContract = BeanTransform.copyProperties(housingContractTO, HousingContract.class, true);
        housingContract.setCreateTime ( createTime );
        housingContract.setModifyTime(LocalDateTime.now());
        super.update(housingContract);
        return BeanTransform.copyProperties(housingContract, HousingContractBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeHousingContract(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}