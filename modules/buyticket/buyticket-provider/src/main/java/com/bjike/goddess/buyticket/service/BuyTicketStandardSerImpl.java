package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketStandardBO;
import com.bjike.goddess.buyticket.dto.BuyTicketStandardDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketStandard;
import com.bjike.goddess.buyticket.enums.GuideAddrStatus;
import com.bjike.goddess.buyticket.to.BuyTicketStandardTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 购票标准业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketStandardSerImpl extends ServiceImpl<BuyTicketStandard, BuyTicketStandardDTO> implements BuyTicketStandardSer {

    @Autowired
    private BuyCusPermissionSer cusPermissionSer;
    @Autowired
    private UserAPI userAPI;



    /**
     * 设置权限表中岗位权限
     *
     * @throws SerException
     */
    private Boolean positCusPermission(String idFlag) throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission(idFlag);
        } else {
            flag = true;
        }
        RpcTransmit.transmitUserToken(userToken);
        return flag;

    }

    /**
     * 检查权限(模块)
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
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员，没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 检查申请查看权限(模块)
     *
     * @throws SerException
     */
    private Boolean checkAppSeePermission() throws SerException {
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
        RpcTransmit.transmitUserToken(userToken);

        return flag;
    }

    /**
     * 检查权限(岗位)
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
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        if (!flag) {
            throw new SerException("您不是相关人员,没有该操作权限");
        }
        RpcTransmit.transmitUserToken(userToken);

    }


    /**
     * 核对模块权限（模块级别）
     */
    private Boolean guideMondIdentity() throws SerException {
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
     * 核对总经办审核权限（岗位级别）
     */
    private Boolean guidePosinIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.jobsCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }


    @Override
    public Boolean sonPermission() throws SerException {
        String userToken = RpcTransmit.getUserToken();
        Boolean flagGuideMod = guideMondIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagGuidePosi = guidePosinIdentity();
        RpcTransmit.transmitUserToken(userToken);
        if (flagGuideMod || flagGuidePosi) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case APPLIST:
                flag = true;
                break;
            case APPADD:
                flag = true;
                break;
            case APPEDIT:
                flag = true;
                break;
            case LIST:
                flag = guideMondIdentity();
                break;
            case ADD:
                flag = guideMondIdentity();
                break;
            case EDIT:
                flag = guideMondIdentity();
                break;
            case DELETE:
                flag = guideMondIdentity();
                break;
            case PLANAUDIT:
                flag = guideMondIdentity();
                break;
            case WELFAUDIT:
                flag = guideMondIdentity();
                break;
            case CONGEL:
                flag = guideMondIdentity();
                break;
            case THAW:
                flag = guideMondIdentity();
                break;
            case RECORDLIST:
                flag = true;
                break;
            default:
                flag = true;
                break;
        }

        RpcTransmit.transmitUserToken(userToken);
        return flag;
    }
    @Override
    public Long countBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {

        buyTicketStandardDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketStandardDTO);
        return count;
    }
    @Override
    public BuyTicketStandardBO getOne(String id) throws SerException {
        BuyTicketStandard buyTicketStandard = super.findById(id);
        return BeanTransform.copyProperties(buyTicketStandard,BuyTicketStandardBO.class,true);
    }

    @Override
    public List<BuyTicketStandardBO> findListBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        checkModPermission();
        List<BuyTicketStandard> buyTicketStandards = super.findByCis(buyTicketStandardDTO,true);
        List<BuyTicketStandardBO> buyTicketStandardBOS = BeanTransform.copyProperties(buyTicketStandards,BuyTicketStandardBO.class);
        return buyTicketStandardBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BuyTicketStandardBO insertBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        checkModPermission();
        BuyTicketStandard buyTicketStandard = BeanTransform.copyProperties(buyTicketStandardTO,BuyTicketStandard.class,true);
        buyTicketStandard.setModifyTime(LocalDateTime.now());
        super.save(buyTicketStandard);
        return BeanTransform.copyProperties(buyTicketStandard,BuyTicketStandardBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public BuyTicketStandardBO editBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        checkModPermission();
        BuyTicketStandard buyTicketStandard = super.findById(buyTicketStandardTO.getId());
        BeanTransform.copyProperties(buyTicketStandardTO,buyTicketStandard, true);
        buyTicketStandard.setModifyTime(LocalDateTime.now());
        super.update(buyTicketStandard);
        return BeanTransform.copyProperties(buyTicketStandardTO,BuyTicketStandardBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeBuyTicketStandard(String id) throws SerException {
        checkModPermission();
        super.remove(id);
    }
}