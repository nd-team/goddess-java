package com.bjike.goddess.contractware.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractware.bo.CarRentalAgreementBO;
import com.bjike.goddess.contractware.dto.CarRentalAgreementDTO;
import com.bjike.goddess.contractware.entity.CarRentalAgreement;
import com.bjike.goddess.contractware.enums.GuideAddrStatus;
import com.bjike.goddess.contractware.to.CarRentalAgreementTO;
import com.bjike.goddess.contractware.to.GuidePermissionTO;
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
 * 租车协议业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-04 05:43 ]
 * @Description: [ 租车协议业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractwareSerCache")
@Service
public class CarRentalAgreementSerImpl extends ServiceImpl<CarRentalAgreement, CarRentalAgreementDTO> implements CarRentalAgreementSer {

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
    public Long countCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        Long count = super.count(carRentalAgreementDTO);
        return count;
    }

    @Override
    public CarRentalAgreementBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        CarRentalAgreement carRentalAgreement = super.findById(id);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }

    @Override
    public List<CarRentalAgreementBO> findListCarRentalAgreement(CarRentalAgreementDTO carRentalAgreementDTO) throws SerException {
        checkSeeIdentity();
        carRentalAgreementDTO.getSorts().add("createTime=desc");
        List<CarRentalAgreement> carRentalAgreement = super.findByPage(carRentalAgreementDTO);
        List<CarRentalAgreementBO> carRentalAgreementBOS = BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
        return carRentalAgreementBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarRentalAgreementBO insertCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        checkAddIdentity();
        CarRentalAgreement carRentalAgreement = BeanTransform.copyProperties(carRentalAgreementTO,CarRentalAgreement.class,true);
        carRentalAgreement.setCreateTime(LocalDateTime.now());
        super.save(carRentalAgreement);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarRentalAgreementBO editCarRentalAgreement(CarRentalAgreementTO carRentalAgreementTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(carRentalAgreementTO.getId())) {
            throw new SerException("id不能为空");
        }
        CarRentalAgreement carRentalAgreement = super.findById(carRentalAgreementTO.getId());
        BeanTransform.copyProperties(carRentalAgreementTO,carRentalAgreement,true);
        carRentalAgreement.setModifyTime(LocalDateTime.now());
        super.update(carRentalAgreement);
        return BeanTransform.copyProperties(carRentalAgreement,CarRentalAgreementBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCarRentalAgreement(String id) throws SerException {
        checkAddIdentity();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

}