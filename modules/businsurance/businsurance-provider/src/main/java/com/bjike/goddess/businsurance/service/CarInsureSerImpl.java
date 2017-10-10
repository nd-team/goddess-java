package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.businsurance.bo.CarInsureBO;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.entity.CarInsure;
import com.bjike.goddess.businsurance.enums.GuideAddrStatus;
import com.bjike.goddess.businsurance.to.CarInsureTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.CarInsureDTO;
import com.bjike.goddess.businsurance.entity.CarInsure;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 车险信息管理业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 11:00 ]
 * @Description: [ 车险信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businsuranceSerCache")
@Service
public class CarInsureSerImpl extends ServiceImpl<CarInsure, CarInsureDTO> implements CarInsureSer {
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
     * 检查权限(总经办)
     *
     * @throws SerException
     */
    private void checkPonsPermission() throws SerException {
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
            throw new SerException("您不是总经办岗位人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(福利模块审核)
     *
     * @throws SerException
     */
    private void checkModPermission() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是福利模块人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查权限(运营商务部审核)
     *
     * @throws SerException
     */
    private void checkBussPermission() throws SerException {
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
            throw new SerException("您不是运营商务部人员,没有该操作权限");
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

    /**
     * 核对总经办审核权限（岗位级别）
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
     * 核对财务模块审核权限（福利模块审核）
     */
    private Boolean guideMondIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("3");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 核对查看权限（运营商务部）
     */
    private Boolean guideBussIdentity() throws SerException {
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

    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSee = guideIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagMond = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagPosin = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagBuss = guideBussIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagSee || flagMond || flagPosin || flagBuss) {
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
            case MODULEAUDIT:
                flag = guideMondIdentity();
                break;
            case MANAGEAUDIT:
                flag = guidePosinIdentity();
                break;
            case SEE:
                flag = guideIdentity();
                break;
            case EXPORT:
                flag = guideIdentity();
                break;
            case BUSINESSAUDIT:
                flag = guideBussIdentity();
                break;
            case SEEFILE:
                flag = guideIdentity();
                break;
            case UPLOAD:
                flag = guideIdentity();
                break;
            case DOWNLOAD:
                flag = guideIdentity();
                break;
            case COLLECT:
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
    public Long countCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        Long count = super.count(carInsureDTO);
        return count;
    }

    @Override
    public List<CarInsureBO> listCarInsure(CarInsureDTO carInsureDTO) throws SerException {
        checkPermission();
        carInsureDTO.getSorts().add("createTime=desc");
        List<CarInsure> list = super.findByCis(carInsureDTO,true);

        return BeanTransform.copyProperties(list, CarInsureBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO addCarInsure(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        CarInsure carInsure = BeanTransform.copyProperties(carInsureTO,CarInsure.class,true);

        carInsure.setTotalFee( carInsureTO.getTotalFee() == null ? 0d : carInsureTO.getTotalFee());
        carInsure.setCreateTime(LocalDateTime.now());
        super.save( carInsure );
        return BeanTransform.copyProperties(carInsure, CarInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editCarInsure(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank( carInsureTO.getId())){
            throw new SerException("id不能为空");
        }
        CarInsure carInsure = BeanTransform.copyProperties(carInsureTO,CarInsure.class,true);
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        BeanUtils.copyProperties(carInsure , cusLevel ,"id","createTime");
        cusLevel.setTotalFee( carInsureTO.getTotalFee() == null ? 0d : carInsureTO.getTotalFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(carInsure, CarInsureBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCarInsure(String id) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove( id );
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editCar(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setInsurer( carInsureTO.getInsurer());
        cusLevel.setInsureIdCard( carInsureTO.getInsureIdCard());
        cusLevel.setInsureAddr( carInsureTO.getInsureAddr());
        cusLevel.setTel( carInsureTO.getTel());
        cusLevel.setCarNumber( carInsureTO.getCarNumber());
        cusLevel.setBrand( carInsureTO.getBrand());
        cusLevel.setPriceChoice( carInsureTO.getPriceChoice());
        cusLevel.setOwnNature( carInsureTO.getOwnNature());
        cusLevel.setUseNature( carInsureTO.getUseNature());
        cusLevel.setCarType( carInsureTO.getCarType());
        cusLevel.setEngineNumber( carInsureTO.getEngineNumber());
        cusLevel.setTransferCar( carInsureTO.getTransferCar());
        cusLevel.setIdentityCode( carInsureTO.getIdentityCode());
        cusLevel.setApprovePassenger( carInsureTO.getApprovePassenger());
        cusLevel.setApproveLoad( carInsureTO.getApproveLoad());
        cusLevel.setOutputVolume( carInsureTO.getOutputVolume());
        cusLevel.setPower( carInsureTO.getPower());
        cusLevel.setCarInitialDate( StringUtils.isBlank( carInsureTO.getCarInitialDate() )? null: LocalDate.parse( carInsureTO.getCarInitialDate()));
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editContext(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setInsureType( carInsureTO.getInsureType());
        cusLevel.setOrNotFee( carInsureTO.getOrNotFee());
        cusLevel.setRateFloat( carInsureTO.getRateFloat());
        cusLevel.setInsureLimitFee( carInsureTO.getInsureLimitFee());
        cusLevel.setInsureFee( carInsureTO.getInsureFee()== null ? 0d: carInsureTO.getInsureFee());
        cusLevel.setInsureTotalFee( carInsureTO.getInsureTotalFee()== null ? 0d: carInsureTO.getInsureTotalFee());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editAppoint(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setContentDetail( carInsureTO.getContentDetail());
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public CarInsureBO editSale(CarInsureTO carInsureTO) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(carInsureTO.getId())){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( carInsureTO.getId() );

        cusLevel.setOrganName( carInsureTO.getOrganName());
        cusLevel.setOperateName( carInsureTO.getOperateName());
        cusLevel.setOrganAddr( carInsureTO.getOrganAddr());
        cusLevel.setOrganContact( carInsureTO.getOrganContact());
        cusLevel.setInterAddr( carInsureTO.getInterAddr());
        cusLevel.setPostCode( carInsureTO.getPostCode());
        cusLevel.setSignDate( StringUtils.isBlank( carInsureTO.getSignDate() )? null: LocalDate.parse(carInsureTO.getSignDate()));
        cusLevel.setModifyTime(LocalDateTime.now());
        super.update( cusLevel );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }

    @Override
    public CarInsureBO getCarInsure(String id) throws SerException {
        checkPermission();
        if(StringUtils.isBlank(id)){
            throw  new SerException("id不能为空");
        }
        CarInsure cusLevel = super.findById( id  );
        return BeanTransform.copyProperties(cusLevel, CarInsureBO.class);
    }
    
}