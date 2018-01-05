package com.bjike.goddess.individualvision.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.individualvision.bo.CareerPlanningCustomBO;
import com.bjike.goddess.individualvision.dto.CareerPlanningCustomDTO;
import com.bjike.goddess.individualvision.entity.CareerPlanningCustom;
import com.bjike.goddess.individualvision.enums.GuideAddrStatus;
import com.bjike.goddess.individualvision.to.CareerPlanningCustomTO;
import com.bjike.goddess.individualvision.to.GuidePermissionTO;
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
 * 职业规划定制业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-30 06:45 ]
 * @Description: [ 职业规划定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "individualvisionSerCache")
@Service
public class CareerPlanningCustomSerImpl extends ServiceImpl<CareerPlanningCustom, CareerPlanningCustomDTO> implements CareerPlanningCustomSer {
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
        return flag;
    }


    @Override
    public Long countCareerPlanningCustom(CareerPlanningCustomDTO careerPlanningCustomDTO) throws SerException {
        Long count = super.count(careerPlanningCustomDTO);
        return count;
    }

    @Override
    public CareerPlanningCustomBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CareerPlanningCustom careerPlanningCustom = super.findById(id);
        return BeanTransform.copyProperties(careerPlanningCustom, CareerPlanningCustomBO.class);
    }

    @Override
    public List<CareerPlanningCustomBO> findListCareerPlanningCustom(CareerPlanningCustomDTO careerPlanningCustomDTO) throws SerException {
        checkSeeIdentity();
        careerPlanningCustomDTO.getSorts().add("name=desc");
        careerPlanningCustomDTO.getSorts().add("area=desc");
        careerPlanningCustomDTO.getSorts().add("department=desc");
        careerPlanningCustomDTO.getSorts().add("entryTime=desc");
        careerPlanningCustomDTO.getSorts().add("availableBenefitPackage=desc");
        careerPlanningCustomDTO.getSorts().add("expectedSalaryIncrease=desc");
        careerPlanningCustomDTO.getSorts().add("planningDate=desc");
        careerPlanningCustomDTO.getSorts().add("currentState=desc");
        List<CareerPlanningCustom> careerPlanningCustoms = super.findByCis(careerPlanningCustomDTO, true);
        List<CareerPlanningCustomBO> boList = BeanTransform.copyProperties(careerPlanningCustoms, CareerPlanningCustomBO.class);
        return boList;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CareerPlanningCustomBO insertCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        checkAddIdentity();
        CareerPlanningCustom careerPlanningCustom = BeanTransform.copyProperties(careerPlanningCustomTO, CareerPlanningCustom.class,true);
        careerPlanningCustom.setCreateTime(LocalDateTime.now());
        super.save(careerPlanningCustom);
        return BeanTransform.copyProperties(careerPlanningCustom, CareerPlanningCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CareerPlanningCustomBO editCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(careerPlanningCustomTO.getId())) {
            throw new SerException("id不能为空");
        }
        CareerPlanningCustom careerPlanningCustom = super.findById(careerPlanningCustomTO.getId());
        BeanTransform.copyProperties(careerPlanningCustomTO, careerPlanningCustom, true);
        careerPlanningCustom.setModifyTime(LocalDateTime.now());
        super.update(careerPlanningCustom);
        return BeanTransform.copyProperties(careerPlanningCustomTO, CareerPlanningCustomBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCareerPlanningCustom(String id) throws SerException {
        checkAddIdentity();
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CareerPlanningCustomBO sendCareerPlanningCustom(CareerPlanningCustomTO careerPlanningCustomTO) throws SerException {
        CareerPlanningCustom careerPlanningCustom = super.findById(careerPlanningCustomTO.getId());
        //todo 2017-03-31 未做发送邮件
//        if (careerPlanningCustom.getPlanningDate().equals("15")) {
//            if (careerPlanningCustom.getPositive() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//                careerPlanningCustom.setSurplusTime(careerPlanningCustomTO.getSurplusTime());
//            } else if (careerPlanningCustom.getManagement() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//                careerPlanningCustom.setSurplusTime(careerPlanningCustomTO.getSurplusTime());
//            } else if (careerPlanningCustom.getSkills() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//                careerPlanningCustom.setSurplusTime(careerPlanningCustomTO.getSurplusTime());
//            }
//
//        } else if (careerPlanningCustom.getPlanningDate().equals("planningDate")) {
//            if (careerPlanningCustom.getPositive() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//            } else if (careerPlanningCustom.getManagement() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//            } else if (careerPlanningCustom.getSkills() != null) {
//                careerPlanningCustom.setNotUpStandard(careerPlanningCustomTO.getNotUpStandard());
//                careerPlanningCustom.setCompleteDegree(careerPlanningCustomTO.getCompleteDegree());
//                careerPlanningCustom.setStandard(careerPlanningCustomTO.getStandard());
//            }
//        }
        return BeanTransform.copyProperties(careerPlanningCustom, CareerPlanningCustomBO.class);
    }
}