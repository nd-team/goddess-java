package com.bjike.goddess.rentcar.service;

import com.bjike.goddess.carinfo.api.DriverRecruitAPI;
import com.bjike.goddess.carinfo.bo.DriverRecruitBO;
import com.bjike.goddess.carinfo.dto.DriverRecruitDTO;
import com.bjike.goddess.carinfo.entity.DriverRecruit;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rentcar.bo.DriverInfoBO;
import com.bjike.goddess.rentcar.dto.DriverInfoDTO;
import com.bjike.goddess.rentcar.entity.DriverInfo;
import com.bjike.goddess.rentcar.enums.GuideAddrStatus;
import com.bjike.goddess.rentcar.excel.SonPermissionObject;
import com.bjike.goddess.rentcar.to.CarSendEmailTO;
import com.bjike.goddess.rentcar.to.DriverInfoTO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 司机信息管理业务实现
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:47 ]
 * @Description: [ 司机信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rentcarSerCache")
@Service
public class DriverInfoSerImpl extends ServiceImpl<DriverInfo, DriverInfoDTO> implements DriverInfoSer {

    @Autowired
    private UserAPI userAPI;

    @Autowired
    private CusPermissionSer cusPermissionSer;

//    @Autowired
//    private DriverRecruitSer driverRecruitSer;

    @Autowired
    private DriverRecruitAPI driverRecruitAPI;

    @Autowired
    private CarSendEmailSer carSendEmailSer;

    @Autowired
    private CollectDriverInfoSer collectDriverInfoSer;
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
            flag = cusPermissionSer.busCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

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
            flag = cusPermissionSer.busCusPermission("1");
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
        obj.setDescribesion("租车协议管理");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagStandard = carSendEmailSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("carsendemail");
        obj.setDescribesion("邮件发送对象设置");
        if (flagStandard) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagCollect = collectDriverInfoSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("carsendemail");
        obj.setDescribesion("租车协议汇总设置");
        if (flagStandard) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO insertModel(DriverInfoTO to) throws SerException {
        DriverInfo driverInfo = findByIdCard(to.getIdCard());
        if (driverInfo == null) {
            DriverInfo model = BeanTransform.copyProperties(to, DriverInfo.class, true);
            model.setIfAdd(true);
            super.save(model);
            to.setId(model.getId());
            return BeanTransform.copyProperties(model, DriverInfoBO.class);
        } else {
            throw new SerException("该身份证号码对应的司机已存在!");
        }
    }

    public DriverInfo findByIdCard(String idCard) throws SerException {
        DriverInfoDTO dto = new DriverInfoDTO();
        dto.getConditions().add(Restrict.eq("idCard", idCard));
        return super.findOne(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public DriverInfoBO updateModel(DriverInfoTO to) throws SerException {
        if(to.getId() != null && !"".equals(to.getId())) {
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
        }else{
            DriverInfo model = BeanTransform.copyProperties(to,DriverInfo.class,true);
            super.save(model);
            return BeanTransform.copyProperties(to,DriverInfo.class);
        }
    }

    @Override
    public List<DriverInfoBO> pageList(DriverInfoDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<DriverInfo> list = super.findByPage(dto);
        List<DriverInfo> driverIdList = list.stream().filter(p -> p.getDriverId() != null).collect(Collectors.toList());
        List<DriverInfoBO> driverInfoBOS = new ArrayList<>();
        if(driverIdList !=null && driverIdList.size() >0){
            DriverRecruitDTO driverRecruitDTO1 = new DriverRecruitDTO();
            driverRecruitDTO1.getConditions().add(Restrict.eq("enSureAgreement",true));
            List<DriverRecruitBO> driverRecruitList = driverRecruitAPI.pageList(driverRecruitDTO1);
//            List<DriverRecruitBO> driverRecruitBOS = driverRecruitList.stream().filter( str -> str.getEnSureAgreement() != null && str.getEnSureAgreement().equals(true)).collect(Collectors.toList());
            List<DriverInfo> driverInfos = new ArrayList<>();
            BeanTransform.copyProperties(driverRecruitList,driverInfos,"id");
            driverInfoBOS = BeanTransform.copyProperties(driverInfos,DriverInfoBO.class,false);
        }
        List<DriverInfoBO> driverInfoBOList = BeanTransform.copyProperties(list,DriverInfoBO.class,false);
        driverInfoBOS.addAll(driverInfoBOList);
        return driverInfoBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void audit(String id, String suggest, Boolean audit) throws SerException {
        checkAddIdentity();
        DriverInfo model = super.findById(id);
        if (model != null) {
//            TODO 未明确组织结构信息及账务模块审核对象
//            model.setSuggest(suggest);
//            model.setAudit(audit);
            super.update(model);
        } else {
            throw new SerException("非法Id,司机信息对象不能为空!");
        }
    }

    @Override
    public void recissionAgreement(String id,Boolean breakAgreement, String liftTime) throws SerException {
        DriverInfo model = super.findById(id);
        model.setBreakAgreement(breakAgreement);
        model.setLiftTime(DateUtil.parseDateTime(liftTime));
        super.update(model);
    }

    @Override
    public DriverInfoBO findByName(String driverName) throws SerException {
        DriverInfoDTO driverInfoDTO = new DriverInfoDTO();
        driverInfoDTO.getConditions().add(Restrict.eq("driver",driverName));
        DriverInfo driverInfo = super.findOne(driverInfoDTO);
        DriverInfoBO driverInfoBO = BeanTransform.copyProperties(driverInfo,DriverInfoBO.class);
        return driverInfoBO;
    }
}