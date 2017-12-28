package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.carinfo.api.DriverAPI;
import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.carinfo.dto.DriverInfoDTO;
import com.bjike.goddess.carinfo.entity.Driver;
import com.bjike.goddess.carinfo.entity.DriverInfo;
import com.bjike.goddess.carinfo.enums.GuideAddrStatus;
import com.bjike.goddess.carinfo.excel.SonPermissionObject;
import com.bjike.goddess.carinfo.to.DriverInfoTO;
import com.bjike.goddess.carinfo.to.GuidePermissionTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentcar.api.DriverInfoAPI;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 司机信息业务实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 车辆信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "carinfoSerCache")
@Service
public class DriverInfoSerImpl extends ServiceImpl<DriverInfo, DriverInfoDTO> implements DriverInfoSer {


    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

    @Autowired
    private DriverRecruitSer driverRecruitSer;

    @Autowired
    private DriverSer driverSer;

    @Autowired
    private DriverInfoAPI driverInfoAPI;

    @Autowired
    private ModuleAPI moduleAPI;

    /**
     * 核对查看权限（层级）
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
     * 核对添加修改删除审核权限（层级）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 导航栏核对查看权限（层级）
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
     * 导航栏核对添加修改删除审核权限（层级）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("driverInfo");
        obj.setDescribesion("司机信息管理");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);


        RpcTransmit.transmitUserToken(userToken);
        Boolean flagDriverRecruit = driverRecruitSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("driverRecruit");
        obj.setDescribesion("司机招聘信息");
        if (flagDriverRecruit) {
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
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO insertModel(DriverInfoTO to) throws SerException {
        checkAddIdentity();
        DriverInfo driverInfo = findByIdCard(to.getIdCard());
        if (driverInfo == null) {
            DriverInfo model = BeanTransform.copyProperties(to, DriverInfo.class, true);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(to, DriverInfoBO.class);
        } else {
            throw new SerException("该身份证号码对应的司机已存在!");
        }
    }

    @Override
    public DriverInfoBO findByName(String name) throws SerException {
        DriverInfoBO model = new DriverInfoBO();
        String userToken = RpcTransmit.getUserToken();
        if (moduleAPI.isCheck("rentcar")){
            RpcTransmit.transmitUserToken(userToken);
            com.bjike.goddess.rentcar.bo.DriverInfoBO driverInfoBO = driverInfoAPI.findByName(name);
            model.setAgreement(driverInfoBO.getAgreement());
            model.setAttachment(driverInfoBO.getAttachment());
            model.setTravel(driverInfoBO.getTravel());
            model.setDriverLicense(driverInfoBO.getDriverLicense());
            model.setCarInsurance(driverInfoBO.getCarInsurance());
        }else {
            throw new SerException("请去模块关联设置模块关联");
        }
        return model;
    }

    public DriverInfo findByIdCard(String idCard) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("idCard", idCard));
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO updateModel(DriverInfoTO to) throws SerException {
        checkAddIdentity();
        DriverInfo model = super.findById(to.getId());
        if (model != null) {
            DriverInfo driverInfo = findByIdCard(to.getIdCard());
            if (driverInfo == null || (driverInfo != null && driverInfo.getId().equals(model.getId()))) {
                BeanTransform.copyProperties(to, model, true);
                model.setRemark(to.getRemark());
                model.setModifyTime(LocalDateTime.now());
                super.update(model);
                return BeanTransform.copyProperties(to, DriverInfoBO.class);
            } else {
                throw new SerException("该身份证号码对应的司机已存在!");
            }
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), DriverInfoBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        checkAddIdentity();
        DriverInfo model = super.findById(id);
        if (model != null) {
            //TODO 未明确组织结构信息及账务模块审核对象
//            model.setSuggest(suggest);
//            model.setAudit(audit);
            super.update(model);
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public DriverInfoBO findByDriver(String driver) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("driver", driver));
        List<DriverInfo> list = super.findByCis(dto);
        if (list != null && list.size() > 0){
            return BeanTransform.copyProperties(list.get(0), DriverInfoBO.class);
        }
        return null;
    }

    @Override
    public void copyDriver() throws SerException {
        List<Driver> drivers = driverSer.findAll();
        List<DriverInfo> driverInfos = new ArrayList<>(drivers.size());
        for (Driver driver : drivers) {
            DriverInfo driverInfo = new DriverInfo();
//            driverInfo.setSuggest(driver.getAuditIdea());
            driverInfo.setArea(driver.getArea());
            driverInfo.setDriver(driver.getDriverName());
            driverInfo.setPhone(driver.getPhoneNum());
            if(!"".equals(driver.getIdCard()) && driver.getIdCard() != null) {
                driverInfo.setIdCard(driver.getIdCard());
            }else{
                driverInfo.setIdCard("无");
            }
            if(!"".equals(driver.getAddress()) && driver.getAddress() != null) {
                driverInfo.setAddress(driver.getAddress());
            }else {
                driverInfo.setAddress("无");
            }
            driverInfo.setCarModel(driver.getCarModel());
            driverInfo.setCarNum(driver.getCarNum());
            driverInfo.setEngineNum(driver.getEngineNUm());
            if(!"".equals(driver.getBuyDate()) && driver.getBuyDate() != null) {
                driverInfo.setBuyDate(DateUtil.parseDate(driver.getBuyDate()));
            }
//            driverInfo.setUseTime(driver.getUseDate());
            driverInfo.setEmissions(driver.getDischarge());
            driverInfo.setCarFuel(driver.getCarFuel());
            driverInfo.setAgreement(driver.getDeal());
            if(!"".equals(driver.getSignDate()) && driver.getSignDate() != null) {
                driverInfo.setSignDate(DateUtil.parseDate(driver.getDealDate()));
            }
            if(!"".equals(driver.getSignDate()) && driver.getSignDate() != null) {
                driverInfo.setStartDate(DateUtil.parseDate(driver.getSignDate()));
            }
            if(!"".equals(driver.getDueDate()) && driver.getDueDate() != null) {
                driverInfo.setEndDate(DateUtil.parseDate(driver.getDueDate()));
            }
            if(!"".equals(driver.getTravel()) && driver.getTravel() != null ) {
                driverInfo.setTravel(driver.getTravel());
            }
            driverInfo.setTravelName(driver.getTravelName());
            if(!"".equals(driver.getDrive()) && driver.getDrive() != null) {
                driverInfo.setDriverLicense(driver.getDrive());
            }
            driverInfo.setLicenseName(driver.getUseName());
            if(!"".equals(driver.getInsure()) && driver.getInsure() != null) {
                driverInfo.setCarInsurance(driver.getInsure());
            }
            driverInfo.setCardUser(driver.getUserName());
            driverInfo.setCardNum(driver.getCarNum());
            driverInfo.setCardBank(driver.getBankCardNum());
            driverInfo.setBreakAgreement(driver.getBreak());
            driverInfo.setRemark(driver.getRemark());
            driverInfos.add(driverInfo);
        }
        super.save(driverInfos);
    }
}